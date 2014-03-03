package com.omar.hubino.filer.bo;

import java.io.File;

/**
 * The Class FileVO.
 * 
 * @author Vijayaraja Gnansambandan
 * @version 1.0.0 - The Class FileVO Created
 * 
 */

public class FileVO {

	private String sourceDir = null; // Represent the source directory

	private String targetDir = null; // Refer the Target Directory

	private String handleFile = null; // Refer the currently processing file

	public String getHandleFile() {
		return handleFile;
	}

	public void setHandleFile(String handleFile) {
		this.handleFile = handleFile;
	}

	public String getSourceDir() {
		return sourceDir;
	}

	public void setSourceDir(String sourceDir) {
		this.sourceDir = sourceDir;
	}

	public String getTargetDir() {
		return targetDir;
	}

	public void setTargetDir(String targetDir) {
		this.targetDir = targetDir;
	}

	public File getSourceFile() {
		return new File(this.sourceDir + File.separator + this.handleFile);
	}

	public File getTargetFile() {
		return new File(this.targetDir + File.separator + this.handleFile);
	}

}
