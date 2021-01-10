package com.filedemo.model;

public class UploadFileResponse  {

	private Integer id;
	
	private String fileName;
	
	private String fileDownloadUri;
	
	private String fileType;
	
	private long size;
	
	private String extension;
	
	private String fileDirectory;
	

	private String fileBaseName;


	public UploadFileResponse(Integer id, String fileName, String fileDownloadUri, String fileType, long size,
			String extension, String fileDirectory, String fileBaseName) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
		this.extension = extension;
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


	public String getExtension() {
		return extension;
	}


	public void setExtension(String extension) {
		this.extension = extension;
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

	
	

}
