package com.zzyyaa.test.entity;

import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.springframework.util.MultiValueMap;

public class FileUploadEntity {
	private List<FileItem> file;
	private MultiValueMap<String, String> parameters;

	public List<FileItem> getFile() {
		return file;
	}

	public void setFile(List<FileItem> file) {
		this.file = file;
	}

	public MultiValueMap<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(MultiValueMap<String, String> parameters) {
		this.parameters = parameters;
	}

}
