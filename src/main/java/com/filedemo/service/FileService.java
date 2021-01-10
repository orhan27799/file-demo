package com.filedemo.service;



import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.filedemo.model.File;

public interface FileService {

	String storeFile(MultipartFile file) ;

	Resource loadFileAsResource(String fileName);

	File save(String fileName, String fileDownloadUri, String contentType, long size, String extension, String fileDirectory, String fileBaseName);

	String getFileDirectory(String fileName) throws IOException;

	String getFileBaseName();
	
	List<File> findAll();

}
