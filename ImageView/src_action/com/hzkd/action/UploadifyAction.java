package com.hzkd.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UploadifyAction extends ActionSupport{
	
	private File files = null;
	private String title = null;
	private String filesFileName = null;
	private String filesContentType = null;
	
	public String execute(){
		
		String savePath = "e:/uploadFiles";
		
		File savefile = new File(new File(savePath), filesFileName);
		
		try {
			FileUtils.copyFile(files, savefile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public void setFiles(File files) {
		this.files = files;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setFilesFileName(String filesFileName) {
		this.filesFileName = filesFileName;
	}

	public void setFilesContentType(String filesContentType) {
		this.filesContentType = filesContentType;
	}


}
