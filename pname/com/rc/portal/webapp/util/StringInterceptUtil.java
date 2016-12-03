package com.rc.portal.webapp.util;


/**
 * 定义字符串载取的方法
 * @author zhouzhou
 *
 */
public class StringInterceptUtil {
	/**
	 * 得到模板的路径
	 * @param s
	 * @return
	 */
	public static String getTemplateName(String s){
		int lastIndex=s.lastIndexOf("/");
		String str=s.substring(lastIndex+1, s.length());
		return str;
	}
	
	/**
	 * 得以模板文件的名字
	 * @param s
	 * @return
	 */
	public static String getTemplatePath(String s){
	
		int lastIndex=s.lastIndexOf("/");
		String str=s.substring(0, lastIndex);
		return str;
	}
	
	/*public static void main(String args[]){
		String s="E:\ftlmc\tuijian1.ftl";
		//System.out.println("模板的路径是："+getTemplatePath(s));
		//System.out.println("模板的名字是："+getTemplateName(s));
		System.out.println(s.replaceAll("\"", "/"));
	}*/

}
