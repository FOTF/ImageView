package com.hzkd.action;

import java.util.ArrayList;
import java.util.List;

import com.hzkd.bean.ImageView;
import com.hzkd.bean.Page;
import com.hzkd.util.ImageInfroLoad;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ImageViewAction extends ActionSupport{
	
	private List<ImageView> images = new ArrayList<ImageView>();;
	private Integer currentPage = 1;
	private int number;
	
	public String execute(){
		
		currentPage = currentPage + number;
		if(currentPage <= 0){
			currentPage = 1;
		}
		
		Page page = new Page(currentPage);
		
		images = ImageInfroLoad.loadSomeImage(page);
		
		currentPage = page.getCurrentPage();
		
		return SUCCESS;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<?> getImages() {
		return images;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
