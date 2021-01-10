package com.filedemo.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.filedemo.model.File;
import com.filedemo.model.UploadFileResponse;
import com.filedemo.service.FileService;

@RestController
@RequestMapping("/api/file")
public class FileController {
	
	private static final Logger logger=LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileService  fileStorageService;
	
	@PostMapping("/upload")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		String fileName = fileStorageService.storeFile(file);
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String fileDirectory = fileStorageService.getFileDirectory(fileName);
		String fileBaseName = fileStorageService.getFileBaseName();
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(fileName)
	                .toUriString();
		 
		File uploadFile=fileStorageService.save(fileName,fileDownloadUri,file.getContentType(),file.getSize(),extension,fileDirectory,fileBaseName);
		return new UploadFileResponse(uploadFile.getId(),fileName,fileDownloadUri,file.getContentType(),file.getSize(),extension,fileDirectory,fileBaseName);
	}
	
	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,HttpServletRequest request) {
		// Load file as Resource
		Resource resource=fileStorageService.loadFileAsResource(fileName);
		
		//Try to determine file's content type
		
		String contentType=null;
		
		try {
			contentType=request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			
		} catch (IOException e) {
			logger.info("Could not determine file type.");
		}
		
		//Fallback to the default content type if could not be determined
		
		if(contentType==null) {
			contentType="application/octet-stream";
		}
		
	
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
		        .body(resource);
	}
	
	@DeleteMapping("/delete/{fileName:.+}")
	public ResponseEntity<Void> deleteFile(@PathVariable String fileName,HttpServletRequest request) {
		// Load file as Resource
		Resource resource=fileStorageService.loadFileAsResource(fileName);
		
	
		try {
			resource.getFile().delete();
			return ResponseEntity.noContent().build();
			
		} catch (IOException e) {
			logger.info("Could not find file.");
		}
		return null;
		
		
	}
	
	
	@GetMapping("/list")
	@ResponseBody
	List<File> listFiles() throws IOException {
		
		List<File>  files=fileStorageService.findAll();
		
		return files;
		
		
	}
	
	

}
