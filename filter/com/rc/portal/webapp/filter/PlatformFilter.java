package com.rc.portal.webapp.filter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

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

import com.rc.portal.service.TAdmOperationLogsManager;
import com.rc.portal.service.TAdmUsersManager;
import com.rc.portal.service.impl.TAdmUsersManagerImpl;
import com.rc.portal.vo.TAdmUsers;
import com.rc.portal.vo.TAdmUsersExample;
import com.rc.portal.webapp.model.Domain;
import com.rc.portal.webapp.util.MD5;

/**
 * @Title: CodeFilter.java
 * @Description:
 * @author yinbinhome@163.com
 * @date 2011-4-12 下午06:34:06
 * @version V1.0
 */

public class PlatformFilter extends HttpServlet implements Filter {
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
		String username = request.getParameter("j_username");
		String password = request.getParameter("j_password");
		String webid = request.getParameter("webid");
		
		Integer flag = (Integer) request.getSession().getAttribute("domain_flag");
		if (flag == 1) {
			if (checkUer(request, response)) {
				List<Domain> list=(List) request.getSession().getAttribute("domain_list");
				Domain domain=new Domain();
				for(Domain d:list){
					if(d.getId().toString().equals(webid)){
						domain=d;
					}
				}
				if (domain.getId() == 1) {
					filterChain.doFilter(request, response);
				} else {
					String url = domain.getDomain() + "/j_spring_security_check?j_username=" + username + "&j_password=" + password + "&source=system";
					response.sendRedirect(url);
				}
			} else {
				response.sendRedirect("login/showLogin.action?errorMessage=999");
			}
		} else {
			filterChain.doFilter(request, response);
		}

	}

	/**
	 * 验证用户名密码
	 * 
	 * @param request
	 * @return
	 */
	public boolean checkUer(HttpServletRequest request, HttpServletResponse response) {
		TAdmUsersManager tadmusersmanager = (TAdmUsersManagerImpl) getSpringBean("tadmusersmanager");
		TAdmOperationLogsManager tadmoperationlogsmanager = (TAdmOperationLogsManager) getSpringBean("tadmoperationlogsmanager");
		try {
			String username = request.getParameter("j_username");
			String password = request.getParameter("j_password");
			password = MD5.MD5(password);
			TAdmUsersExample te = new TAdmUsersExample();
			te.createCriteria().andLoginNameEqualTo(username).andPasswordEqualTo(password);
			TAdmUsers ta = new TAdmUsers();
			try {
				List<TAdmUsers> list = tadmusersmanager.selectByRepository(te);
				if (list == null || list.size() == 0) {
					return false;
				} else {
					ta = list.get(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (new Date().after(ta.getExpireTime())) {
				log.info("【登陆】用户过期");
				return false;
			} else if (ta.getStatus() != 1) {
				log.info("【登陆】用户状态为不可使用");
				return false;
			} else {
				tadmoperationlogsmanager.record(request, ta.getId(), "用户统一登录-用户名密码验证成功");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

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
