package com.hzkd.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hzkd.util.ImageResizer;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ReadImageStreamAction extends ActionSupport {

	private String imagePath;
	private int imageType;

	public String execute() {
		
		System.out.println("imagePath = " + imagePath);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

//		BufferedInputStream bis = null;
		OutputStream os = null;

		try {
			/*FileInputStream fis = new FileInputStream(new File(imagePath));
			bis = new BufferedInputStream(fis);
			byte[] buffer = new byte[512];*/

			response.reset();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("image/*");
//			response.setContentLength(bis.available());
			os = response.getOutputStream();

			/*int n;
			while ((n = bis.read(buffer)) != -1) {
				os.write(buffer, 0, n);
			}*/
			
			if(imageType == 1){
				ImageResizer.changeImageToSmall(imagePath, os);
			}else if(imageType == 2){
				ImageResizer.changeImage2Big(imagePath, os);
			}
//			bis.close();
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void setImageType(int imageType) {
		this.imageType = imageType;
	}

}
