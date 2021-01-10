package com.filedemo.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="file")
public class FileStorageProperties {
	
	
	// File Storage Properties
	//All files uploaded through the REST API will be stored in this directory
	@Value("${app.upload.dir:${user.home}}")
	private String uploadDir;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	
	

}
