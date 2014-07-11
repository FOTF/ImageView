package com.hzkd.action;

import java.io.File;

import org.apache.log4j.Logger;

import com.hzkd.util.FileUpload;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UploadifyAction extends ActionSupport{
	
	private static Logger log = Logger.getLogger(UploadifyAction.class);
	
	private File files = null;
	private String title = null;
	private String filesFileName = null;
	private String filesContentType = null;
	
	public String execute(){
		log.info("开始保存文件。。。 ");
		log.info("保存的文件信息是：文件标题：" + title + ", 文件名称是：" + filesFileName + ", 文件类型是：" + filesContentType);
		FileUpload.saveFile(filesFileName, files);
		log.info("保存文件结束");
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
