package com.rc.portal.interceptor;

import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;
import com.rc.portal.webapp.model.UserinfoModel;


public class LoginInterceptor implements Interceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9043978504253311796L;
	protected static final String LOGIN = "login";
	//private UserinfoManager userinfoManager;
	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public String intercept(ActionInvocation invocation) throws Exception {
		String cookiekey="";;
		UserinfoModel userModel=new UserinfoModel();
//		try {
//			HttpSession session = ServletActionContext.getRequest().getSession();
//			userModel =(UserinfoModel) session.getAttribute(ConstantUtil.logincookiename);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return LOGIN;
//		}
//		
//		if(userModel==null){
//			return LOGIN;
//		}
//		else{
//			return invocation.invoke();
//		}
		return invocation.invoke();
	}

}
