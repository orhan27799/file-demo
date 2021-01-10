package com.filedemo.model;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="files")
public class File implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String fileName;
	
	private String fileDownloadUri;
	
	private String fileType;
	
	private long size;
	
	private String fileExtension;
	
	
	private String fileDirectory;
	
		
	@Transient
	private String fileBaseName;
	
	public File() {
	}


	public File(String fileName, String fileDownloadUri, String fileType, long size, String fileExtension,
			String fileDirectory, String fileBaseName) {
		super();
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
		this.fileExtension = fileExtension;
		this.fileDirectory = fileDirectory;
		this.fileBaseName = fileBaseName;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFileDownloadUri() {
		return fileDownloadUri;
	}


	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}


	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public long getSize() {
		return size;
	}


	public void setSize(long size) {
		this.size = size;
	}


	public String getFileExtension() {
		return fileExtension;
	}


	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}


	public String getFileDirectory() {
		return fileDirectory;
	}


	public void setFileDirectory(String fileDirectory) {
		this.fileDirectory = fileDirectory;
	}


	public String getFileBaseName() {
		return fileBaseName;
	}


	public void setFileBaseName(String fileBaseName) {
		this.fileBaseName = fileBaseName;
	}

	
	
	public Path getFilePath() {
		if (fileName == null || fileDirectory == null) {
			return null;
		}

		return Paths.get(fileDirectory, fileName);
	}
	

}