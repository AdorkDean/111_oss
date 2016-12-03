package com.rc.portal.commons;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class DataUtil {
	/**
	 * 
	*    
	* 方法描述： 验证是否是数字，包含小数 
	* 创建人：hexiaoliang  
	* 创建时间：2010-3-25 下午05:29:39    
	* @param str
	* @return
	* @version   1.0
	*
	 */
	public static boolean isNumeric(String str){
		String pattern = "[0-9]+(.[0-9]+)?";  
		Pattern p = Pattern.compile(pattern);  
		Matcher m = p.matcher(str);  
		
		boolean flag =true;	
		if(str.indexOf(".")==-1&&"0".equals(String.valueOf(str.charAt(0))))
			flag=false;
		
		return  m.matches()&&flag; 
	} 
	
	/**
	 * 
	*    
	* 方法描述： 验证是否是正整数 
	* 创建人：hexiaoliang  
	* 创建时间：2010-3-26 下午03:51:53    
	* @param str
	* @return
	* @version   1.0
	*
	 */
	public static boolean isInteger(String str){
		String pattern = "[0-9]+?";  
		Pattern p = Pattern.compile(pattern);  
		Matcher m = p.matcher(str);  
		
		boolean flag =true;	
		if(str.indexOf(".")==-1&&"0".equals(String.valueOf(str.charAt(0))))
			flag=false;
		
		return  m.matches()&&flag; 
	}
	
	/**
	 * 
	*    
	* 方法描述：获取集群环境下客户端真实的ip地址  
	* 创建人：hexiaoliang  
	* 创建时间：2010-6-22 上午10:02:55    
	* @param request
	* @return
	* @version   1.0
	*
	 */
	public static String getIpAddr(HttpServletRequest request) {    
	      String ip = request.getHeader("x-forwarded-for");    
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	         ip = request.getHeader("Proxy-Client-IP");    
	     }    
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	         ip = request.getHeader("WL-Proxy-Client-IP");    
	      }    
	     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	          ip = request.getRemoteAddr();    
	     }    
	     return ip;    
	}    
	
	
	public static String getTrimValue(String str){
		if(str == null){
			return null;
		}
		return str.trim();
	}
	public static void main(String[] args){
		System.out.println(DataUtil.isInteger("-213"));
	}
	
	
	public static int getIntValueFromObject(Object obj){
		try{
			return Integer.valueOf(obj.toString());
		}catch(Exception e){
			return -1;
		}
	}
	
	public static long getLongValueFromObject(Object obj){
		try{
			return Long.valueOf(obj.toString());
		}catch(Exception e){
			return -1;
		}
	}
	
	public static String removeLastStr(StringBuilder sb,String delStr){
		if(sb == null || delStr == null){
			return null;
		}
		int lastIndex = sb.lastIndexOf(delStr);
		if(lastIndex > 0){
			return sb.substring(0,lastIndex);
		}else{
			return sb.toString();
		}
		
	}
}
