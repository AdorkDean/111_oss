package com.rc.portal.webapp.filter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.rc.oss.service.impl.OpenSqlManager;
import com.rc.oss.service.impl.TAdmOperationLogsManager;
import com.rc.oss.util.Compar;
import com.rc.oss.vo.TAdmModules;
import com.rc.oss.vo.TAdmUsers;
import com.rc.oss.vo.TAdmUsersModules;
import com.rc.portal.util.ConstantUtil;
import com.rc.portal.webapp.model.UserinfoModel;
import com.rc.portal.webapp.util.MD5;

/**
 * @Title: CodeFilter.java
 * @Description:
 * @author yinbinhome@163.com
 * @date 2011-4-12 下午06:34:06
 * @version V1.0
 */

public class CodeFilter extends HttpServlet implements Filter {
	protected transient final Log log = LogFactory.getLog(getClass());
	private WebApplicationContext wac = null;
	/**
	 * 判断用户输入的验证码是否正确
	 */
	private static final long serialVersionUID = -5838154525730151323L;

	public void init(FilterConfig config) throws ServletException {

		wac = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String code = request.getParameter("j_code");
		String codes = (String) request.getSession().getAttribute("rand");

		if (!"".equals(codes) && codes != null) {
			if (code.equalsIgnoreCase(codes)) {
				if (setSessionModel(request, response)) {
					filterChain.doFilter(request, response);
				} else {
					response.sendRedirect("login/showLogin.action?errorMessage=999");
				}
			} else {
				response.sendRedirect("login/showLogin.action?errorMessage=888");
			}
		} else {
			response.sendRedirect("login/showLogin.action?errorMessage=888");
		}

	}

	/**
	 * 验证用户名密码
	 * 
	 * @param request
	 * @return
	 */
	public boolean setSessionModel(HttpServletRequest request, HttpServletResponse response) {
//		TAdmUsersManager tadmusersmanager = (TAdmUsersManagerImpl) getSpringBean("tadmusersmanager");
//		TAdmOperationLogsManager tadmoperationlogsmanager = (TAdmOperationLogsManager) getSpringBean("tadmoperationlogsmanager");
		OpenSqlManager openSqlManager = (OpenSqlManager)getSpringBean("openSqlManager");
		TAdmOperationLogsManager tAdmOperationLogsManager = (TAdmOperationLogsManager)getSpringBean("tAdmOperationLogsManager");
		Map map=new HashMap();
		try {
			String username = request.getParameter("j_username");
			String password = request.getParameter("j_password");
			password = MD5.MD5(password);
			map.put("username", username);
			map.put("password", password);
//			TAdmUsersExample te = new TAdmUsersExample();
//			te.createCriteria().andLoginNameEqualTo(username).andPasswordEqualTo(password);
			TAdmUsers ta = new TAdmUsers();
			try {
				
				List<TAdmUsers> list =openSqlManager.getListByDynamicsHql("users.login", map);
				if (list == null || list.size() == 0) {
					return false;
				} else {
					ta = list.get(0);
				}
			} catch (Exception e) {
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
				request.getSession().setAttribute(ConstantUtil.logincookiename, userinfo);
				getModules(userinfo.getUserid(), request);
				tAdmOperationLogsManager.record(request, ta.getId(), "用户登录-登陆成功");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 取模块信息
	 * 
	 * @param userid
	 * @param request
	 * @throws SQLException
	 */
	public boolean getModules(String userid, HttpServletRequest request) {
//		OpenSqlManage opensqlmanage = (OpenSqlManage) getSpringBean("opensqlmanage");
		OpenSqlManager openSqlManager = (OpenSqlManager)getSpringBean("openSqlManager");
		Map map = new HashMap();
		map.put("userid", new Long(userid));
//		map.put("webid", 1L);
		List<TAdmModules> Mlist =new ArrayList<TAdmModules>();
			TAdmUsers user = new TAdmUsers();
			List<TAdmUsers> list=openSqlManager.getListByDynamicsHql("users.login", map);
			if(list == null || list.size() != 1){
				return false;
			}else{
				user=list.get(0);
				List<TAdmUsersModules> admModules = new ArrayList<TAdmUsersModules>(user.getTAdmUsersModuleses());
				for(TAdmUsersModules tum:admModules){
					Mlist.add(tum.getTAdmModules());
				}
			}
			Compar com=new Compar();
			Collections.sort(Mlist, com);
//		 opensqlmanage.selectForListByMap_drug(map, "T_ADM_MODULES.getModuleList");
		request.getSession().setAttribute(ConstantUtil.ModuleList, Mlist);
		return true;
	}

	/**
	 * 取bean
	 * 
	 * @param beanName
	 * @return
	 */
	public Object getSpringBean(String beanName) {
		if (beanName != null && beanName.trim().length() > 0) {
			return wac.getBean(beanName);
		} else {
			return null;
		}
	}

}
