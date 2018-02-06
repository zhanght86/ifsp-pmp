package com.ruimin.ifs.login.process.auth;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.login.process.LoginService;

/**
 *
 */
public class AuthorizingRealmExt extends AuthorizingRealm {

	@Override
	/**
	 * 
	 * 当用户进行访问链接时的授权方法
	 * 
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.fromRealm(getName()).iterator().next();
		if (username != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			return info;
		}
		return null;
	}

	@Override
	/**
	 * 用户登录的认证方法
	 * 
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) arg0;

		String username = usernamePasswordToken.getUsername();
		char[] password = usernamePasswordToken.getPassword();
		String brhno = usernamePasswordToken.getHost();
		// 用户校验\保存用户会话信息
		SessionUserInfo sessionInfo = null;
		try {
			LoginService service = ContextUtil.getSingleService(LoginService.class);
			sessionInfo = service.loginUserSessionInfo(username,password, brhno);

		} catch (SnowException cex) {
			throw new AuthenticationException(cex.getMessage(), cex);
		}
		return new SimpleAuthenticationInfo(sessionInfo, password, username);
	}

}
