package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.oss.service.impl.OpenSqlManager;
import com.rc.oss.service.impl.TAdmOperationLogsManager;
import com.rc.oss.util.Compar;
import com.rc.oss.vo.TAdmModules;
import com.rc.oss.vo.TAdmUsers;
import com.rc.oss.vo.TAdmUsersModules;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.impl.TAdmModulesManagerImpl;
import com.rc.portal.service.impl.TAdmOperationLogsManagerImpl;
import com.rc.portal.service.impl.TAdmUsersManagerImpl;
import com.rc.portal.service.impl.TAdmUsersModulesManagerImpl;
import com.rc.portal.util.ConstantUtil;
import com.rc.portal.util.DateUtil;
import com.rc.portal.webapp.model.UserinfoModel;

/**
 * @Title: LoginAction.java
 * @Description: 登陆
 * @author yinbinhome@163.com
 * @date 2011-4-14 上午11:14:59
 * @version V1.0
 */

public class LoginAction extends BaseAction {
	private static final String TOLOGIN = "login";
	private static final String TOP = "top";
	private static final String CENTER = "center";
	private static final String DOWN = "down";
	private static final String RIGHT = "right";
	private static final String LEFT = "left";
	private static final String LOADPAGES = "loadPages";
	private static final String LOGOUT = "logout";
	private UserinfoModel user;
	private String j_code;// 验证码
	private String fromurl;// 返回url BOSS系统暂时不用返回url
	private TAdmUsersManagerImpl tadmusersmanager;
	private TAdmModulesManagerImpl tadmmodulesmanager;
	private TAdmUsersModulesManagerImpl tadmusersmodulesmanager;
	private TAdmOperationLogsManagerImpl tadmoperationlogsmanager;
	private OpenSqlManage opensqlmanage;
	private OpenSqlManager openSqlManager;
	private TAdmOperationLogsManager tAdmOperationLogsManager;

	public String showLogin() {
		String ip = getIpAddr(getRequest());
		//domainLogin();
		log.info("【登陆信息】showLogin()初始化进入 IP:" + ip);
		return TOLOGIN;
	}

	/**
	 * 说明:判读联合登陆,取平台
	 */
	public void domainLogin(){
		Map map=new HashMap();
		map.put("id", 1);
		Integer flag=(Integer) opensqlmanage.selectForObjectByMap_drug(map, "T_ADM_MODULES.getUnionLogin");
		getRequest().getSession().setAttribute("domain_flag", flag);
		if(flag==1){
			List list=opensqlmanage.selectForListByMap_drug(map, "T_ADM_MODULES.listDomain");
			getRequest().setAttribute("doaminList", list);
			getRequest().getSession().setAttribute("domain_list", list);
		}
	}
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public String login() {
		String ip = getIpAddr(getRequest());
		log.info("【登陆信息】login()初始化进入 IP:" + ip);
		// boolean f=loginDo(getResponse());
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		if (userDetails != null) {
			UserinfoModel userinfo = (UserinfoModel) request.getSession().getAttribute(ConstantUtil.logincookiename);
			List listModule = (List) request.getSession().getAttribute(ConstantUtil.ModuleList);
			boolean f = true;
			if (userinfo == null || listModule == null) {
				f = loginDo(response);
			}
			if (f) {
				return LOADPAGES;
			} else {
				return LOGOUT;
			}

		} else {
			request.getSession().removeAttribute(ConstantUtil.logincookiename);
			request.getSession().removeAttribute(ConstantUtil.ModuleList);
			setErrorMessage("登录失败！");
			return LOGOUT;
		}

	}

	public boolean loginDo(HttpServletResponse response) {

		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = userDetails.getUsername();
			String password = userDetails.getPassword();
//			TAdmUsersExample te = new TAdmUsersExample();
//			te.createCriteria().andLoginNameEqualTo(username).andPasswordEqualTo(password);
			TAdmUsers ta = new TAdmUsers();
			Map map=new HashMap();
			map.put("username", username);
			map.put("password", password);
			try {
				List<TAdmUsers> list =openSqlManager.getListByDynamicsHql("users.login", map);
				ta = list.get(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (new Date().after(ta.getExpireTime())) {
				log.info("【登陆】用户过期");
				return false;
			} else if (ta.getStatus() != 1) {
				log.info("【登陆】用户状态为不可使用");
				return false;
			} else {
				UserinfoModel userinfo = new UserinfoModel();
				userinfo.setUserid(ta.getId().toString());
				userinfo.setUsername(username);// 登陆名字
				userinfo.setName(ta.getName());// 真是姓名
				userinfo.setPaypasswd(password);
				// HttpSessionUtil.setAttribute(response,
				// ConstantUtil.logincookiename, userinfo);
				this.getRequest().getSession().setAttribute(ConstantUtil.logincookiename, userinfo);
				getModules(userinfo.getUserid());
				tAdmOperationLogsManager.record(getRequest(), ta.getId(), "用户登录-登陆成功");
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public void getModules(String userid) throws SQLException {
		Map map = new HashMap();
		map.put("userid", new Long(userid));
//		map.put("webid", 1L);
		List <TAdmModules> Mlist =new ArrayList<TAdmModules>();
		TAdmUsers user = new TAdmUsers();
		List<TAdmUsers> list=openSqlManager.getListByDynamicsHql("users.login", map);
		if(list != null || list.size() != 0){
			user=list.get(0);
			List<TAdmUsersModules> admModules = new ArrayList<TAdmUsersModules>(user.getTAdmUsersModuleses());
			for(TAdmUsersModules tum:admModules){
				Mlist.add(tum.getTAdmModules());
		}
	}
		Compar com=new Compar();
		Collections.sort(Mlist, com);
//		List Mlist = opensqlmanage.selectForListByMap_drug(map, "T_ADM_MODULES.getModuleList");
		this.getRequest().getSession().setAttribute(ConstantUtil.ModuleList, Mlist);
		
	}

	public String logout() {
		try {
			this.getRequest().getSession().removeAttribute(ConstantUtil.logincookiename);
			return LOGOUT;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return LOADPAGES;
		}
	}

	/**
	 * 取时间
	 * @throws IOException 
	 */
	public void showTime() throws IOException{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out=null;
		try {
			out = this.getResponse().getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date date=new Date();
		String strDate=DateUtil.dateTypeToString(date,"yyyy-MM-dd HH:mm");
		
		out.print(strDate);
//		out.close();
		
//		 for (int i = 0; i < 100; i++) {  
//	            try {  
//	                Thread.sleep(1000 * Integer.valueOf(1));  
//	            } catch (NumberFormatException e) {  
//	                e.printStackTrace();  
//	            } catch (InterruptedException e) {  
//	                e.printStackTrace();  
//	            }  
//	              
//	            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss E");  
//	            String date_str = df.format(new Date());  
//	              
//	            writerResponse(this.getResponse(), date_str, "showServerTime");// msg是test.jsp中的那个js方法的名称  
//	        }  		
	}
	
	
	protected void writerResponse(HttpServletResponse response, String body, String client_method) throws IOException {  
        StringBuffer sb = new StringBuffer();  
        sb.append("<script type=\"text/javascript\">//<![CDATA[\n");  
        sb.append("     parent.").append(client_method).append("(\"").append(body).append("\");\n");  
        sb.append("//]]></script>");  
        System.out.println(sb.toString());  
  
        response.setContentType("text/html;charset=GBK");  
        response.addHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");  
        response.setHeader("Cache-Control", "pre-check=0,post-check=0");  
        response.setDateHeader("Expires", 0);  
        response.getWriter().write(sb.toString());  
        response.flushBuffer();  
    } 	
	public String loadTop() {
		log.info("【登陆信息】载入top!");
		// user=HttpSessionUtil.getLoginUserModel();
		user = (UserinfoModel) getRequest().getSession().getAttribute(ConstantUtil.logincookiename);
		this.getRequest().setAttribute("nowDate", new Date());
		return TOP;
	}

	public String loadCenter() {
		log.info("【登陆信息】载入center!");
		return CENTER;
	}

	public String loadRight() {
		user = (UserinfoModel) getRequest().getSession().getAttribute(ConstantUtil.logincookiename);
		log.info("【登陆信息】载入right!");
		return RIGHT;
	}

	public String loadDown() {
		log.info("【登陆信息】载入down!");
		return DOWN;
	}

	public String loadLeft() {
		log.info("【登陆信息】载入left!");
		return LEFT;
	}

	public String getJ_code() {
		return j_code;
	}

	public void setJ_code(String j_code) {
		this.j_code = j_code;
	}

	public UserinfoModel getUser() {
		return user;
	}

	public void setUser(UserinfoModel user) {
		this.user = user;
	}

	public String getFromurl() {
		return fromurl;
	}

	public void setFromurl(String fromurl) {
		this.fromurl = fromurl;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public TAdmUsersManagerImpl getTadmusersmanager() {
		return tadmusersmanager;
	}

	public void setTadmusersmanager(TAdmUsersManagerImpl tadmusersmanager) {
		this.tadmusersmanager = tadmusersmanager;
	}

	public TAdmOperationLogsManagerImpl getTadmoperationlogsmanager() {
		return tadmoperationlogsmanager;
	}

	public void setTadmoperationlogsmanager(TAdmOperationLogsManagerImpl tadmoperationlogsmanager) {
		this.tadmoperationlogsmanager = tadmoperationlogsmanager;
	}

	public TAdmModulesManagerImpl getTadmmodulesmanager() {
		return tadmmodulesmanager;
	}

	public void setTadmmodulesmanager(TAdmModulesManagerImpl tadmmodulesmanager) {
		this.tadmmodulesmanager = tadmmodulesmanager;
	}

	public TAdmUsersModulesManagerImpl getTadmusersmodulesmanager() {
		return tadmusersmodulesmanager;
	}

	public void setTadmusersmodulesmanager(TAdmUsersModulesManagerImpl tadmusersmodulesmanager) {
		this.tadmusersmodulesmanager = tadmusersmodulesmanager;
	}

	public OpenSqlManager getOpenSqlManager() {
		return openSqlManager;
	}

	public void setOpenSqlManager(OpenSqlManager openSqlManager) {
		this.openSqlManager = openSqlManager;
	}

	public TAdmOperationLogsManager gettAdmOperationLogsManager() {
		return tAdmOperationLogsManager;
	}

	public void settAdmOperationLogsManager(TAdmOperationLogsManager tAdmOperationLogsManager) {
		this.tAdmOperationLogsManager = tAdmOperationLogsManager;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModel(Object o) {
		// TODO Auto-generated method stub

	}

}
