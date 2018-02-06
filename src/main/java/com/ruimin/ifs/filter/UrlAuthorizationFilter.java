package com.ruimin.ifs.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.framework.core.UserAuthorityUtil;
import com.ruimin.ifs.framework.session.SessionUtil;
import com.ruimin.ifs.pmp.pubTool.common.constants.HttpTransConstants;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;

public class UrlAuthorizationFilter implements Filter {
	private String noCheckSuffix;
	private String errorPage;
	private String loginPage;
	private FilterConfig fconf;
	private Set<String> suffixSet;

	public UrlAuthorizationFilter() {
	}

	public FilterConfig getFconf() {
		return this.fconf;
	}

	public void setFconf(FilterConfig fconf) {
		this.fconf = fconf;
	}

	public String getNoCheckSuffix() {
		return this.noCheckSuffix;
	}

	public void setNoCheckSuffix(String noCheckSuffix) {
		this.noCheckSuffix = noCheckSuffix;
	}

	public String getErrorPage() {
		return this.errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	private boolean isNoChcek(String url) {
		String tmp = url.toLowerCase();
		boolean bl = false;
		if (this.suffixSet == null) {
			bl = true;
		} else {
			Iterator<String> it = this.suffixSet.iterator();
			while (it.hasNext()) {
				String suffix = (String) it.next();
				if (tmp.endsWith(suffix)) {
					bl = false;
					break;
				}
			}
		}
		return bl;
	}

	@Autowired
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 获取referer
		String referer = req.getHeader("Referer");
		String url = req.getRequestURI();
		SnowLog.getLogger(UrlAuthorizationFilter.class).info("referer验证地址：[ " + url + " ]");
		if ("/ifsp-pmp/pages/login/login.jsp".equals(url) || "/ifsp-pmp/".equals(url)) {
			filterChain.doFilter(request, response);
			return;
		}
		if (referer == null || referer.length() < 1 || "null".equals(referer)) {
			// referer为空 进入登录页面
			SnowLog.getLogger(UrlAuthorizationFilter.class).info("referer验证失败：" + referer);
			// res.sendRedirect(url+getLoginPage());
			req.getRequestDispatcher(getLoginPage()).forward(req, res);

		} else {

			// 服务域名
			String urlName1 = SysParamUtil.getParam(HttpTransConstants.LOCAL_URL_NAME1);
			String urlName2 = SysParamUtil.getParam(HttpTransConstants.LOCAL_URL_NAME2);
			// 验证referer
			boolean checkReferer = checkReferer(referer, urlName1, urlName2);
			if (!checkReferer) {
				SnowLog.getLogger(UrlAuthorizationFilter.class).info("referer验证失败：" + referer);
				// res.sendRedirect(getLoginPage());
				req.getRequestDispatcher(getLoginPage()).forward(req, res);
			} else {
				SnowLog.getLogger(UrlAuthorizationFilter.class).info("---------referer验证成功：" + referer + "----------");

				// 下载xls文件 验证用户是否登录
				boolean noChcek = isNoChcek(url);
				if (noChcek) {
					String tlrno = SessionUtil.getSessionUserInfo(req).getTlrno();
					if (StringUtils.isBlank(tlrno)) {
						SnowLog.getLogger(UrlAuthorizationFilter.class).info("用户未登录");
						// res.sendRedirect(getLoginPage());
						req.getRequestDispatcher(getLoginPage()).forward(req, res);
						return;
					}
				}

				boolean bl = UserAuthorityUtil.urlAuthority(url, req);
				if (bl) {
					filterChain.doFilter(request, response);
				} else {
					req.getRequestDispatcher(getErrorPage()).forward(req, res);
				}

			}
		}

	}

	private boolean checkReferer(String referer, String urlName, String urlName2) {

		// 判断urlName1 长度不同 referer错误
		if (referer.length() < urlName.length()) {
			return false;
		}
		String subRef = referer.substring(0, urlName.length());
		if (urlName.equals(subRef)) {
			return true;
		}
		// 判断urlName1 长度不同 referer错误
		if (referer.length() < urlName2.length()) {
			return false;
		}
		String subRef2 = referer.substring(0, urlName2.length());
		if (urlName2.equals(subRef2)) {
			return true;
		}
		return false;
	}

	public void destroy() {
		if (this.suffixSet != null) {
			this.suffixSet.clear();
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		setFconf(arg0);
		setNoCheckSuffix(arg0.getInitParameter("noCheckSuffix"));
		setErrorPage(arg0.getInitParameter("erorrPage"));
		setLoginPage(arg0.getInitParameter("loginPage"));

		if (StringUtils.isNotBlank(getNoCheckSuffix())) {
			this.suffixSet = new HashSet();
			String[] tmps = getNoCheckSuffix().split(",");
			for (String suffix : tmps) {
				if (StringUtils.isNotBlank(suffix)) {
					this.suffixSet.add("." + suffix.toLowerCase());
				}
			}
		}
	}
}
