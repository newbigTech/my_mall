package com.d2c.boss.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XssHttpFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) servletRequest);
		((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Origin", "*");
		((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Headers",
				"Content-Type,X-Requested-With,accesstoken,Accept");
		((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Methods", "POST, GET,DELETE,PUT");
		filterChain.doFilter(xssRequest, servletResponse);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
