package com.filedemo.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.filedemo.exception.FileStorageException;
import com.filedemo.exception.InvalidFileException;
import com.filedemo.exception.MyFileNotFoundException;
import com.filedemo.model.File;
import com.filedemo.property.FileStorageProperties;
import com.filedemo.repository.FileRepository;
import com.filedemo.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileRepository fileRepository;

	private final Path fileStorageLocation;

	@Value("${upload.file.extensions}")
	private String validExtensions;

	@Autowired
	public FileServiceImpl(FileStorageProperties fileStorageProperties) throws FileStorageException {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	@Override
	public String storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			if (!isValidExtension(fileName)) {
				throw new InvalidFileException("Invalid File Extension" + fileName);
			}
			;

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	@Override
	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}

	@Override
	public File save(String fileName, String fileDownloadUri, String contentType, long size, String extension,
			String fileDirectory, String fileBaseName) {
		File entity = new File(fileName, fileDownloadUri, contentType, size, extension, fileDirectory, fileBaseName);
		return fileRepository.save(entity);

	}

	@Override
	public String getFileDirectory(String fileName) throws IOException {

		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource.getFile().getAbsolutePath();
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}

	@Override
	public String getFileBaseName() {
		return this.fileStorageLocation.toString();
	}

	boolean isValidExtension(String fileName) throws InvalidFileException {
		String fileExtension = FilenameUtils.getExtension(fileName);

		if (fileExtension == null) {
			throw new InvalidFileException("No File Extension");
		}

		fileExtension = fileExtension.toLowerCase();

		for (String validExtension : validExtensions.split(",")) {
			if (fileExtension.equals(validExtension)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<File> findAll() {
		return fileRepository.findAll();
	}

}
