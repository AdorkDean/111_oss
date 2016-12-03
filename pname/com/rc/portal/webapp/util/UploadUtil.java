package com.rc.portal.webapp.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.rc.commons.img.ImageUtils;
import com.rc.portal.webapp.model.ImagesParam;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * 文件上传 管理类
 * @author cui jinbao
 *
 */
public class UploadUtil {
	 
	
	/**
	 * 图片上传
	 * @param ImageName 图片名称
	 * @param FileName  目录名称
	 * @param file      文件对象
	 * @return          FileName
	 * 	0  上传文件为空
	 	-1 文件写入失败
	 	-2 上传文件太大
	 	-3 不支持的上传文件格式
	 	-4 传入参数缺失
	 * @throws Exception
	 */
	public static String upload(String ImageName,String FileName,File file,String oldFileName) {
		return upload(ImageName,FileName,file,oldFileName,null,false); 
		
	}
	/**
	 * 图片上传修改 //yangjieliang
	 * @param ImageName 图片名称
	 * @param FileName  目录名称
	 * @param file      文件对象
	 * @return          FileName
	 * 	0  上传文件为空
	 	-1 文件写入失败
	 	-2 上传文件太大
	 	-3 不支持的上传文件格式
	 	-4 传入参数缺失
	 * @throws Exception
	 */
	public static String uploadTemp(String ImageName,String FileName,File file,String oldFileName,String path) {
		return uploadTemp(ImageName,FileName,file,oldFileName,path,null,false); 
		
	}
	/**
	 * 
	 * @param ImageName 图片名称
	 * @param FileName  目录名称
	 * @param file      文件对象
	 * @param MY_FILE_PATH 自定义上传目录
	 * @param 是否印刷 水印 true 印刷 false 不印刷
	 * @return          FileName
	 * 	0  上传文件为空
	 	-1 文件写入失败
	 	-2 上传文件太大
	 	-3 不支持的上传文件格式
	 	-4 传入参数缺失
	 * @throws Exception
	 */
	public static String upload(String ImageName,String FileName,File file,String oldFileName,String MY_FILE_PATH,boolean isPress) {
		
		FileOutputStream outputStream = null;
		String result="";
		String realPath="";
		/**
		 * 验证上传文件有效性
		 */
		if( (ImageName==null || ImageName.equals("")) || (FileName==null || FileName.equals(""))) return "-4";
		if (null == file)  return "0";
		
		if(!checkType(oldFileName)) return "-3";
		Long size=file.length();
		if(!checkSize(size)) return "-2";
		/**
		 * 以文件流的形式写入 目录
		 */
		try {
			String newFilePath;
			if(MY_FILE_PATH==null){
				newFilePath=ImagesParam.filepath+FileName;
			}else{
				newFilePath=MY_FILE_PATH+FileName; 
			}
			
			mkdir(newFilePath);//判断目录是否存在，不存在就创建
			result="/"+FileName+"/"+ImageName+ getFileNameSuffix(oldFileName);
			realPath=newFilePath+"/"+ImageName+ getFileNameSuffix(oldFileName);
			outputStream = new FileOutputStream(newFilePath+"/"+ImageName+ getFileNameSuffix(oldFileName));
			FileInputStream fileIn = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = fileIn.read(buffer)) > 0) {
				outputStream.write(buffer, 0, len);
			}
			fileIn.close();
			outputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1"; 
		}
		//印刷水印
		//pressImage("D:\\water.png", "D:\\hoad.jpg", 100, 100);
		
//		if("true".equals(ImagesParam.watermarkopen)){
			if(isPress){
				ImageUtils.pressImage(ImagesParam.watermark, realPath,Integer.parseInt(ImagesParam.watermarkx) , Integer.parseInt(ImagesParam.watermarky));
			}
//		}
		
		return result;
	}
	/**
	 * 
	 * @param ImageName 图片名称
	 * @param FileName  目录名称
	 * @param file      文件对象
	 * @param MY_FILE_PATH 自定义上传目录
	 * @param 是否印刷 水印 true 印刷 false 不印刷
	 * @return          FileName
	 * 	0  上传文件为空
	 	-1 文件写入失败
	 	-2 上传文件太大
	 	-3 不支持的上传文件格式
	 	-4 传入参数缺失
	 * @throws Exception
	 */
	public static String uploadTemp(String ImageName,String FileName,File file,String oldFileName,String path,String MY_FILE_PATH,boolean isPress) {
		
		FileOutputStream outputStream = null;
		String result="";
		String realPath="";
		/**
		 * 验证上传文件有效性
		 */
		if( (ImageName==null || ImageName.equals("")) || (FileName==null || FileName.equals(""))) return "-4";
		if (null == file)  return "0";
		
		if(!checkType(oldFileName)) return "-3";
		Long size=file.length();
		if(!checkSize(size)) return "-2";
		/**
		 * 以文件流的形式写入 目录
		 */
		try {
			String newFilePath;
			if(MY_FILE_PATH==null){
				newFilePath=path+FileName;
			}else{
				newFilePath=path+FileName; 
			}
			
			mkdir(newFilePath);//判断目录是否存在，不存在就创建
			result=FileName+"/"+ImageName+ getFileNameSuffix(oldFileName);
			realPath=newFilePath+"/"+ImageName+ getFileNameSuffix(oldFileName);
			outputStream = new FileOutputStream(newFilePath+"/"+ImageName+ getFileNameSuffix(oldFileName));
			FileInputStream fileIn = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = fileIn.read(buffer)) > 0) {
				outputStream.write(buffer, 0, len);
			}
			fileIn.close();
			outputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1"; 
		}
		//印刷水印
		//pressImage("D:\\water.png", "D:\\hoad.jpg", 100, 100);
		
//		if("true".equals(ImagesParam.watermarkopen)){
		if(isPress){
			ImageUtils.pressImage(ImagesParam.watermark, realPath,Integer.parseInt(ImagesParam.watermarkx) , Integer.parseInt(ImagesParam.watermarky));
		}
//		}
		
		return result;
	}
	
	
	//文件夹不存在 将创建文件夹
	private static void mkdir(String FileName){
		File newFilePathFile = new File(FileName); //创建文件夹对象
		//如果文件夹不存在将创建文件夹
	    if(!newFilePathFile.exists()){
	    	newFilePathFile.mkdir();
	    	newFilePathFile.mkdirs();
	    }
	}
	//删除文件
	public static void delFromFile(String fileName)
    throws NullPointerException, SecurityException {
		File file = new File(fileName);
		if (file.exists())
			file.delete();
		}
	//判断文件大小
	
	private static boolean checkSize(Long size) {
        if (size >ImagesParam.FILE_MAXSIZE)  return false;
        	return true;
        }
	//判断格式
	private static boolean checkType(String fileName) {
        for (int i = 0; i < ImagesParam.FILE_TYPES.length; i++) {
            if (fileName.toLowerCase().endsWith(ImagesParam.FILE_TYPES[i])) {
                return true;
            }
        }
        return false;
    }
    // 分割文件名 如a.txt 返回 a
    public static String getFileNamePrefix(String fileName){
         int dot = fileName.lastIndexOf(".");
         return fileName.substring(0,dot);

    }
    //获得文件后缀
    public static String getFileNameSuffix(String fileName)   {
         int dot = fileName.lastIndexOf(".");
         return fileName.substring(dot).toLowerCase();
    }
    
    public static void main(String[] args) {
    	UploadUtil.shrinImage(100,100,"C:\\Users\\laihama\\Desktop\\temp\\logo.jpg","C:\\Users\\laihama\\Desktop\\temp\\logo_100x100.jpg","C:\\Users\\laihama\\Desktop\\temp");
	}
    /**
     * 
     * @param w 宽
     * @param h 高
     * @param oldFile 原图片名称
     * @param newFile 新图片名称
     * @param newFilePath 新目录名称
     * @throws Exception
     */

     public static void shrinImage(int w, int h, String oldFile, String newFile,String newFilePath)
          
       {
           Arith arith = new Arith();
           double wd = 0.0D;
           double hd = 0.0D;
           String ws = "";
           String hs = "";
           try
           {
        	   //String filePath = request.getRealPath("/");
        	   String filePath = ImagesParam.filepath;
        	   filePath=filePath +newFilePath+"/";
        	   newFile=newFile+ getFileNameSuffix(oldFile);
        	   //System.out.print(filePath + oldFile);
        	   //System.out.print(newFile);
               File myFile = new File(filePath + oldFile);
              // System.out.print(filePath + oldFile);
               Image src = ImageIO.read(myFile);
               int wideth = src.getWidth(null);
               int height = src.getHeight(null);
               if(w > 0 && h == 0 && w < wideth)
               {
                   wd = arith.div(Double.parseDouble(String.valueOf(w)), Double.parseDouble(String.valueOf(wideth)), 10);
                   hd = arith.mul(wd, Double.parseDouble(String.valueOf(height)));
                   hd = arith.round(hd, 0);
                   hs = String.valueOf(hd);
                   hs = hs.substring(0, hs.indexOf("."));
                   h = Integer.parseInt(hs);
               } else
               if(h > 0 && w == 0 && h < height)
               {
                   hd = arith.div(Double.parseDouble(String.valueOf(h)), Double.parseDouble(String.valueOf(height)), 10);
                   wd = arith.mul(hd, Double.parseDouble(String.valueOf(wideth)));
                   wd = arith.round(wd, 0);
                   ws = String.valueOf(wd);
                   ws = ws.substring(0, ws.indexOf("."));
                   w = Integer.parseInt(ws);
               } else
               {
                   w = wideth;
                   h = height;
               }
               BufferedImage tag = new BufferedImage(w, h, 1);
               //tag.getGraphics().drawImage(src, 0, 0, w, h, null);
               
               tag.getGraphics().drawImage( src.getScaledInstance(w, h,Image.SCALE_SMOOTH), 0, 0, null);
               
               
               FileOutputStream out = new FileOutputStream(filePath + newFile);
               JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
               
               //JPEGEncodeParam   param   =   encoder.getDefaultJPEGEncodeParam(tag);     
               //float   quality=600f;
             
               //param.setQuality(quality,   false);     
               //encoder.encode(tag,param);
               encoder.encode(tag);
               out.close();
           }
           catch(Exception e)
           {
                System.out.print(e.toString());
           }
       }
     /**
      * 
      * @param w 宽
      * @param h 高
      * @param oldFile 原图片名称
      * @param newFile 新图片名称
      * @param newFilePath 新目录名称
      * @throws Exception
      */
     
     public static void shrinImageTemp(int w, int h, String oldFile, String newFile,String newFilePath)
     
     {
    	 Arith arith = new Arith();
    	 double wd = 0.0D;
    	 double hd = 0.0D;
    	 String ws = "";
    	 String hs = "";
    	 try
    	 {
    		 //String filePath = request.getRealPath("/");
    		 newFile=newFile+ getFileNameSuffix(oldFile);
    		 //System.out.print(filePath + oldFile);
    		 //System.out.print(newFile);
    		 File myFile = new File(newFilePath +oldFile);
    		 // System.out.print(filePath + oldFile);
    		 Image src = ImageIO.read(myFile);
    		 int wideth = src.getWidth(null);
    		 int height = src.getHeight(null);
    		 if(w > 0 && h == 0 && w < wideth)
    		 {
    			 wd = arith.div(Double.parseDouble(String.valueOf(w)), Double.parseDouble(String.valueOf(wideth)), 10);
    			 hd = arith.mul(wd, Double.parseDouble(String.valueOf(height)));
    			 hd = arith.round(hd, 0);
    			 hs = String.valueOf(hd);
    			 hs = hs.substring(0, hs.indexOf("."));
    			 h = Integer.parseInt(hs);
    		 } else
    			 if(h > 0 && w == 0 && h < height)
    			 {
    				 hd = arith.div(Double.parseDouble(String.valueOf(h)), Double.parseDouble(String.valueOf(height)), 10);
    				 wd = arith.mul(hd, Double.parseDouble(String.valueOf(wideth)));
    				 wd = arith.round(wd, 0);
    				 ws = String.valueOf(wd);
    				 ws = ws.substring(0, ws.indexOf("."));
    				 w = Integer.parseInt(ws);
    			 } else
    			 {
    				 w = wideth;
    				 h = height;
    			 }
    		 BufferedImage tag = new BufferedImage(w, h, 1);
    		 //tag.getGraphics().drawImage(src, 0, 0, w, h, null);
    		 
    		 tag.getGraphics().drawImage( src.getScaledInstance(w, h,Image.SCALE_SMOOTH), 0, 0, null);
    		 
    		 
    		 FileOutputStream out = new FileOutputStream(newFilePath + newFile);
    		 JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
    		 
    		 //JPEGEncodeParam   param   =   encoder.getDefaultJPEGEncodeParam(tag);     
    		 //float   quality=600f;
    		 
    		 //param.setQuality(quality,   false);     
    		 //encoder.encode(tag,param);
    		 encoder.encode(tag);
    		 out.close();
    	 }
    	 catch(Exception e)
    	 {
    		 System.out.print(e.toString());
    	 }
     }
     
	public static String uploadFile(String ImageName,String FileName,File file,String oldFileName) {
		
		FileOutputStream outputStream = null;
		String result="";
		String realPath="";
		/**
		 * 验证上传文件有效性
		 */
		if( (ImageName==null || ImageName.equals("")) || (FileName==null || FileName.equals(""))) return "-4";
		if (null == file)  return "0";
		
	
		Long size=file.length();
		if(!checkSize(size)) return "-2";
		/**
		 * 以文件流的形式写入 目录
		 */
		try {
			String newFilePath;
			newFilePath=FileName;
			
			
			mkdir(newFilePath);//判断目录是否存在，不存在就创建
			result="/"+FileName+"/"+ImageName+ getFileNameSuffix(oldFileName);
			realPath=newFilePath+"/"+ImageName+ getFileNameSuffix(oldFileName);
			outputStream = new FileOutputStream(newFilePath+"/"+ImageName+ getFileNameSuffix(oldFileName));
			FileInputStream fileIn = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = fileIn.read(buffer)) > 0) {
				outputStream.write(buffer, 0, len);
			}
			fileIn.close();
			outputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1"; 
		}
		//印刷水印
		//pressImage("D:\\water.png", "D:\\hoad.jpg", 100, 100);
		

		
		return result;
	}
}
