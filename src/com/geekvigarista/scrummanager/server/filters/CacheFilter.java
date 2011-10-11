package com.geekvigarista.scrummanager.server.filters;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CacheFilter implements Filter
{
	private FilterConfig filterConfig;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException
	{
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String requestURI = httpRequest.getRequestURI();
		if(!requestURI.contains(".nocache."))
		{
			long today = new Date().getTime();
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.setDateHeader("Expires", today + 31536000000L);
		}
		filterChain.doFilter(request, response);
		
	}
	
	public void init(FilterConfig filterConfig) throws ServletException
	{
		this.filterConfig = filterConfig;
	}
	
	public void destroy()
	{
		this.filterConfig = null;
	}
}
