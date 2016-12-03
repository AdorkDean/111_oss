package com.rc.portal.webapp.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author luoweifeng
 * 
 */
public class ParameterFilter implements Filter {
	protected final Logger log = Logger.getLogger(getClass());
	private static Pattern p = Pattern.compile("[a-zA-Z0-9\\.\\]\\[_'\\s]+");
	
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		request.setCharacterEncoding("utf-8");
		HttpServletResponse response = (HttpServletResponse) res;
		Enumeration<?> pStr = request.getParameterNames();

		while (pStr.hasMoreElements()) {
			Object pv = pStr.nextElement();
			if (pv != null) {
				Matcher m = p.matcher(pv.toString());
				if(m.matches()) {
					continue;
				} else {
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
					out.println("<HTML>");
					out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
					out.println("  <BODY>");
					out.println("  request is denied!!!");
					out.println("  </BODY>");
					out.println("</HTML>");
					out.flush();
					out.close();
					return;
				}
			}
		}

		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}

	public static void main(String[] args) {
		System.out.println(Pattern.matches("[a-zA-Z0-9\\.\\]\\[_'\\s]+", "cv@"));
	}

}