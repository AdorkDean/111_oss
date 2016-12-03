
package com.rc.portal.webapp.model;




     /**
      * 图片 上传相关配置
	 *  images.filepath=/www/web/www/image.jkr99.com/image/goods/ 注释

		images.publicpath=/www/web/www/image.jkr99.com/image/public/ 注释
		
		images.filetype=.jpg,.png 
		
		images.maxsize=10485760
		
		images.lessentypes=300,140,55
		
		images.watermark=/www/web/www/image.jkr99.com/image/goods/water.png 注释
		
		images.watermark_x=100
		
		images.watermark_y=100
	 */

public class ImagesParam{
  
	public static String filepath="";
	public static String publicpath="";
	public static String filetype="";
	public static String[] FILE_TYPES;
	public static String maxsize="";
	public static int FILE_MAXSIZE=0;
	public static String lessentypes="";
	public static String[] LESSEN_TYPES;
	public static String watermark="";
	public static String watermarkx="";
	public static String watermarky="";
	public static String watermarkopen="";
	
	
	
	public String getWatermarkopen() {
		return watermarkopen;
	}
	public  void setWatermarkopen(String watermarkopen) {
		this.watermarkopen = watermarkopen;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getPublicpath() {
		return publicpath;
	}
	public void setPublicpath(String publicpath) {
		this.publicpath = publicpath;
	}

	public void setFiletype(String filetype) {
		if(filetype!=null){
			FILE_TYPES=filetype.split(",");
		}
		this.filetype = filetype;
	}
	
	
	

	public String getMaxsize() {
		return maxsize;
	}
	public void setMaxsize(String maxsize) {
		if(maxsize!=null){
			
			this.FILE_MAXSIZE=Integer.parseInt(maxsize);
		}
		
		this.maxsize = maxsize;
	}
	public void setLessentypes(String lessentypes) {
		if(lessentypes!=null){
			LESSEN_TYPES=lessentypes.split(",");
		}
		
		this.lessentypes = lessentypes;
	}
	public String getWatermark() {
		return watermark;
	}
	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}
	public String getWatermarkx() {
		return watermarkx;
	}
	public void setWatermarkx(String watermarkx) {
		this.watermarkx = watermarkx;
	}
	public String getWatermarky() {
		return watermarky;
	}
	public void setWatermarky(String watermarky) {
		this.watermarky = watermarky;
	}
	public String[] getFILE_TYPES() {
		return FILE_TYPES;
	}
	public void setFILE_TYPES(String[] file_types) {
		FILE_TYPES = file_types;
	}
	public int getFILE_MAXSIZE() {
		return FILE_MAXSIZE;
	}
	public void setFILE_MAXSIZE(int file_maxsize) {
		FILE_MAXSIZE = file_maxsize;
	}
	public String[] getLESSEN_TYPES() {
		return LESSEN_TYPES;
	}
	public void setLESSEN_TYPES(String[] lessen_types) {
		LESSEN_TYPES = lessen_types;
	}
	

    
}