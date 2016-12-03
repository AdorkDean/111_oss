package com.rc.portal.webapp.util.qrcode;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import jp.sourceforge.qrcode.exception.DecodingFailedException;



/**
 * 解析二维码
 * 项目名称：ErWeima      
 * 创建时间：2014-2-8 上午10:26:05    
 * 修改备注：
 */
public class SynCode {
	
	public static void main(String[] args) {

		QRCodeDecoder decoder = new QRCodeDecoder();
		File imageFile = new File("C:/Michael_QRCode.png");
		BufferedImage image = null;
		try{
			image = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.out.println("Error: "+ e.getMessage());
		}
	
	    try {
			String decodedData = new String(decoder.decode(new J2SEImage(image)),"utf-8");
			System.out.println("解析二维码结果："+decodedData);
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}

	class J2SEImage implements QRCodeImage {
		BufferedImage image;
	
		public J2SEImage(BufferedImage image) {
			this.image = image;
		}
	     public int getWidth() {
		   return image.getWidth();
	     }
		public int getHeight() {
			return image.getHeight();
		}
	
		public int getPixel(int x, int y) {
			return image.getRGB(x, y);
		}
	}
	
	
	
