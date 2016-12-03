package com.rc.portal.webapp.util;

import java.security.MessageDigest;

public class MD5 {
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args){
		//2d979608bf78796bb69c717941d78bee
		System.out.println(MD5("2088202643066726"+"fengyun@wozhongla.com"));
	}
	/**
	 * 随即取 MD5 码的 前两位 +后两位 转换为 十进制 
	 */
	public static String getIntegerStr(String md5str){
		try{
		StringBuffer result=new StringBuffer();
		result.append(md5str.substring(0, 2));
		result.append(md5str.substring(30));
		return Integer.valueOf(result.toString(),16).toString();
		}catch(Exception e){
			return null;
		}
	}
}