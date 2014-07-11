package com.hzkd.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hzkd.bean.ImageView;
import com.hzkd.bean.Page;

public class ImageInfroLoad {
	
	private static Logger log = Logger.getLogger(ImageInfroLoad.class);
	
	private static List<ImageView> allImages = new ArrayList<ImageView>();
	private static String imagesPath;
	private static int LOADNUMBER;
	
	static{
		try {
			log.info("初始化图片信息工具类。。。");
			PropertiesParser pp = new PropertiesParser("config.properties");
			imagesPath = pp.getInfoFromConfiguration("imagesPath");
			LOADNUMBER = pp.getInfoFromConfigurationInt("LOADNUMBER");
		} catch (Exception e) {
			log.error("初始化图片信息工具类异常", e);
		}
	}
	
	public static void load(String imagesPath){
		ImageInfroLoad.imagesPath = imagesPath;
		
		log.info("图片载入路径是：" + ImageInfroLoad.imagesPath);
		
		File imagesFile = new File(imagesPath);
		File[] files = imagesFile.listFiles();
		if(files != null){
			for(int i = 0, j = files.length; i < j; i++){
				File image = files[i];
				ImageView iv = new ImageView();
				iv.setName(image.getName());
				iv.setPath(image.getAbsolutePath());
				iv.setRealName(trimExtension(image.getName()));
				allImages.add(iv);
			}
		}
	}
	
	/**
	 * 去除扩展名
	 * @param filename
	 * @return
	 */
	public static String trimExtension(String filename) {   
	    if ((filename != null) && (filename.length() > 0)) {   
	        int i = filename.lastIndexOf('.');   
	        if ((i >-1) && (i < (filename.length()))) {   
	            return filename.substring(0, i);   
	        }   
	    }   
	    return filename;   
	} 
	
	public static void reload(){
		log.info("重新载入图片信息，载入路径为：" + ImageInfroLoad.imagesPath);
		load(ImageInfroLoad.imagesPath);
	}
	
	
	public static List<ImageView> loadSomeImage(Page page){
		if(allImages.size() == 0){
			reload();
		}
		int MAXPAGE = (allImages.size() / LOADNUMBER) + 1;
		
		if(page.getCurrentPage() > MAXPAGE){
			page.setCurrentPage(MAXPAGE);
		}
		
		int startIndex = (page.getCurrentPage() - 1) * LOADNUMBER;
		int endIndex = page.getCurrentPage() * LOADNUMBER;
		
		if(endIndex > allImages.size()){
			endIndex = allImages.size();
		}
		
		log.info("截取图片列表，开始坐标为：" + startIndex + ", 结束坐标为：" + endIndex);
		return allImages.subList(startIndex, endIndex);
	}

}
