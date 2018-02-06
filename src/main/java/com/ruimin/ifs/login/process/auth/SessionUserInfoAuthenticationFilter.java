package com.ruimin.ifs.login.process.auth;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.AuthenticationFilter;

import com.ruimin.ifs.framework.session.SessionUtil;

public class SessionUserInfoAuthenticationFilter extends AuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (isLoginRequest(request, response)) {
			return true;
		}
		saveRequestAndRedirectToLogin(request, response);
		return false;
	}

	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		boolean result = super.preHandle(request, response);
		if (!result) {
			return result;
		}
		boolean gi = false;
		try {
			SessionUtil.getRequest2ThreadLocal((HttpServletRequest) request);
			gi = true;
		} catch (Exception ex) {
			throw ex;
		}
		return result && gi;
	}

}
