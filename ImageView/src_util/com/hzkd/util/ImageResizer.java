package com.hzkd.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class ImageResizer {
	
	private static int SWIDTH;
	private static int SHEIGHT;
	
	private static int BWIDTH;
	private static int BHEIGHT;
	
	private static String SFORMATIMAGE;
	private static String BFORMATIMAGE;
	
	static {
		try {
			PropertiesParser pp = new PropertiesParser("config.properties");
			
			SFORMATIMAGE = pp.getInfoFromConfiguration("SFORMATIMAGE");
			BFORMATIMAGE = pp.getInfoFromConfiguration("BFORMATIMAGE");
			
			SWIDTH = pp.getInfoFromConfigurationInt("SWIDTH");
			SHEIGHT = pp.getInfoFromConfigurationInt("SHEIGHT");
			
			BWIDTH = pp.getInfoFromConfigurationInt("BWIDTH");
			BHEIGHT = pp.getInfoFromConfigurationInt("BHEIGHT");
		} catch (Exception e) {
			
		}
	}
	
	/*** 
     * 功能 :调整图片大小 开发：wuyechun 2011-7-22 
     * @param srcImgPath 原图片路径 
     * @param distImgPath  转换大小后图片路径 
     * @param width   转换后图片宽度 
     * @param height  转换后图片高度 
     */  
    public static void resizeImage(String srcImgPath, int width, int height, String formatType, OutputStream os) throws IOException {  
        File srcFile = new File(srcImgPath);
        Image srcImg = ImageIO.read(srcFile);
        BufferedImage buffImg = null;
        
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        buffImg.getGraphics().drawImage(srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        ImageIO.write(buffImg, formatType, os);
//        ImageIO.write(buffImg, "JPEG", new File(distImgPath));  
    }  
    
    public static void changeImageToSmall(String srcImgPath, OutputStream os){
    	try {
			resizeImage(srcImgPath, SWIDTH, SHEIGHT, SFORMATIMAGE, os);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void changeImage2Big(String srcImgPath, OutputStream os){
    	try {
			resizeImage(srcImgPath, BWIDTH, BHEIGHT, BFORMATIMAGE, os);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
