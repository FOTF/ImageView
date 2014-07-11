package com.hzkd.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hzkd.bean.ImageView;

public class ImageInfroLoad {
	
	private static Logger log = Logger.getLogger(ImageInfroLoad.class);
	
	private static List<ImageView> allImages = new ArrayList<ImageView>();
	private static String imagesPath;
	private static int LOADNUMBER;
	
	static{
		try {
			PropertiesParser pp = new PropertiesParser("config.properties");
			imagesPath = pp.getInfoFromConfiguration("imagesPath");
			LOADNUMBER = pp.getInfoFromConfigurationInt("LOADNUMBER");
			log.info("���������ļ�������");
		} catch (Exception e) {
			log.error("���������ļ�ʧ��", e);
		}
	}
	
	public static void load(String imagesPath){
		ImageInfroLoad.imagesPath = imagesPath;
		
		log.info("����ͼƬ��Ϣ��ͼƬ����·��Ϊ��" + ImageInfroLoad.imagesPath);
		
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
	 * ������չ��
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
		log.info("��������ͼƬ��Ϣ��ͼƬ����·��Ϊ��" + ImageInfroLoad.imagesPath);
		load(ImageInfroLoad.imagesPath);
	}
	
	
	public static List<ImageView> loadSomeImage(int currentPage){
		if(allImages.size() == 0){
			reload();
		}
		int MAXPAGE = (allImages.size() / LOADNUMBER) + 1;
		
		if(currentPage > MAXPAGE){
			currentPage = MAXPAGE;
		}
		
		int startIndex = (currentPage - 1) * LOADNUMBER;
		int endIndex = currentPage * LOADNUMBER;
		
		if(endIndex > allImages.size()){
			endIndex = allImages.size();
		}
		
		log.info("���벿��ͼƬ��Ϣ�������� ��ʼ���꣺" + startIndex + ", ��������Ϊ��" + endIndex);
		return allImages.subList(startIndex, endIndex);
	}

}
