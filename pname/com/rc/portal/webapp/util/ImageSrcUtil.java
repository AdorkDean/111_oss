package com.rc.portal.webapp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Image标签SRC属性值替换
 * @author LGP
 * @date 2016-04-25
 */
public class ImageSrcUtil 
{
	public static void main(String[] args) 
	{
	   String s1 = "<p><img src=\"/static/image/public/20160328/1459135359308091512.jpg\" style=\"\" title=\"1459135359308091512.jpg\"/></p><p><img src=\"/static/image/public/20160328/1459135366077034907.jpg\" style=\"\" title=\"1459135366077034907.jpg\"/></p><p><img src=\"/static/image/public/20160328/1459135370407094081.jpg\" style=\"\" title=\"1459135370407094081.jpg\"/></p><p><img src=\"/static/image/public/20160328/1459135378039045982.jpg\" style=\"\" title=\"1459135378039045982.jpg\"/></p><p><img src=\"/static/image/public/20160328/1459135378023048080.jpg\" style=\"\" title=\"1459135378023048080.jpg\"/></p><p><br/></p>";
	   String s2 = "<img src=\"http://www.111yao.com//static/image/public/20160425/1461552301252054980.jpg\" title=\"1461552301252054980.jpg\" alt=\"侧边栏.jpg\"/>alkjkdjasjkf<img src=\"http://www.111yao.com//static/image/public/20160425/1461552301252054980.jpg\" title=\"1461552301252054980.jpg\" alt=\"侧边栏.jpg\"/>aljkdlafjaskljf";
	  
	    String s3 = "<p>"
	    +"<img title=\"201509161442391682861028519.jpg\" alt=\"0503118阿昔洛韦乳膏-790_01.jpg\" src=\"/static/image/upload/images/201509161442391682861028519.jpg\"/>" 
	    +"<img title=\"201509161442391687174046765.jpg\" alt=\"0503118阿昔洛韦乳膏-790_02.jpg\" src=\"/static/image/upload/images/201509161442391687174046765.jpg\"/>" 
	    +"<img title=\"201509161442391691974031743.jpg\" alt=\"0503118阿昔洛韦乳膏-790_03.jpg\" src=\"/static/image/upload/images/201509161442391691974031743.jpg\"/>" 
	    +"<img title=\"201509161442391697416009331.jpg\" alt=\"0503118阿昔洛韦乳膏-790_04.jpg\" src=\"/static/image/upload/images/201509161442391697416009331.jpg\"/>" 
	    +"<img title=\"201509161442391701638044942.jpg\" alt=\"0503118阿昔洛韦乳膏-790_05.jpg\" src=\"/static/image/upload/images/201509161442391701638044942.jpg\"/>"
	    +"<img title=\"201509161442391701638044942.jpg\" alt=\"0503118阿昔洛韦乳膏-790_05.jpg\" src=\"/static/image/upload/images/201509161442391701638044942.jpg\"/>"
	    +"</p>";
	   
	    System.out.println(replaceAllImageSrcPros("带域名："+s3, "http://www.111yao.com/", 1));
	   //System.out.println(replaceAllImageSrcPros("不带域名："+s2, "http://www.111yao.com/", 2));
	}
	
	/**
	 * Image标签SRC属性值替换
	 * @param sinfo ：字符串原文
	 * @param imageDomain ：图片访问域名
	 * @param type ：1、图片路径字符串添加域名   2、图片路径字符串去掉域名
	 * @return 结果字符串
	 */
	public static String replaceAllImageSrcPros(String sinfo, String imageDomain, int type)
	{
		String temp = "<img src=\"/static/image/upload/images/test.jpg\"/>" ;
		sinfo = sinfo + temp;
		String result = "";
		Pattern p = Pattern.compile("(?:src=\"?)(.*?)\"?\\s");
		Matcher m = p.matcher(sinfo);
		List<String> strArray = new ArrayList<String>();
		while(m.find()) 
		{
			strArray.add(m.group(1));
		}
		int lg = strArray.size();
		if(type == 1)
		{
			for(int i=0; i<lg; i++)
			{
				String str = strArray.get(i);
				result = sinfo.replaceAll(str, imageDomain+str);
				sinfo = result;
			}
		}
		if(type == 2)
		{
			if(sinfo.indexOf(imageDomain) > 0)
			{
				result = sinfo.replaceAll(imageDomain, "");
			}
			else
			{
				return sinfo;
			}
		}
		result = result.replaceAll(temp, "");
	    return result;
	}
}