/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.filter 
 * FileName: CookieHttpOnlyFilter.java
 * Author:   zhaodk
 * Date:     2016年12月14日 上午11:38:18
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zhaodk           2016年12月14日上午11:38:18                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 名称：〈Cookie设置HttpOnly，Secure，Expire属性〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年12月14日 <br>
 * 作者：zhaodk <br>
 * 说明：<br>
 */
public class CookieHttpOnlyFilter implements Filter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		Cookie[] cookies = req.getCookies();

		if (cookies != null) {
			Cookie cookie = cookies[0];
			if (cookie != null) {
				// Servlet 2.5不支持在Cookie上直接设置HttpOnly属性
				String value = cookie.getValue();
				StringBuilder builder = new StringBuilder();
				builder.append("JSESSIONID=" + value + "; ");
				builder.append("Secure; ");
				builder.append("HttpOnly; ");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.HOUR, 1);
				Date date = cal.getTime();
				Locale locale = Locale.CHINA;
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", locale);
				builder.append("Expires=" + sdf.format(date));
				resp.setHeader("Set-Cookie", builder.toString());
			}
		}
		chain.doFilter(req, resp);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
