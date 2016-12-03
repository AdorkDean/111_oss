package com.rc.portal.util;



public class HttpSessionUtil{
//	private static MemCachedClient memClient = MemCached.getmcc();
//	public static String setAttribute(HttpServletResponse response,String key,Object value){	
//		RandomGUID random = new RandomGUID();
//		String guid="";
//		if(random!=null)
//			guid = random.toString();
//		
//		memClient.set(guid, value,new Date(30*60*1000));//默认缓存半个小时
//		
//		CookieUtils.setCookie(response, key, guid, 60*60*24);//cookie有效期1天
//		return guid;
//	}
//		
//	public static Object getAttribute(String key){
//		return memClient.get(key);
//	}
//	
//	public static boolean deleteMemContent(String key){
//		return memClient.delete(key);
//	}
//	
//	public static String setMemCache(HttpServletResponse response,Object value,int times){	
//		RandomGUID random = new RandomGUID();
//		String guid="";
//		if(random!=null)
//			guid = random.toString();
//		
//		if(times==0)
//			memClient.set(guid, value);
//		else
//			memClient.set(guid, value,new Date(times*60*1000));//指定缓存时间
//				
//		return guid;
//	}
//	
//	/**
//	 * 取登陆用户信息
//	 * @return
//	 */
//	public static UserinfoModel getLoginUserModel(){
//		return (UserinfoModel)getAttribute(CookieUtils.getCookieValue(ServletActionContext.getRequest(), ConstantUtil.logincookiename));
//	}
//	
//	
//	/**
//	 * 删除memecached用户信息
//	 * @param respone
//	 * @return
//	 */
//	public static boolean delLoginInfo(HttpServletResponse respone){
//		try {
//			String memkey=CookieUtils.getCookieValue(ServletActionContext.getRequest(), ConstantUtil.logincookiename);
//			deleteMemContent(memkey);
//			return true;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}		
//		
//	}
	
	
	public static void main(String[] args){
		//System.out.println(setAttribute("123"));
	}
		
}
