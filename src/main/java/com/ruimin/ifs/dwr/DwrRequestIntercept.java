package com.ruimin.ifs.dwr;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.extend.Call;
import org.directwebremoting.extend.Calls;
import org.directwebremoting.extend.Replies;
import org.directwebremoting.extend.Reply;
import org.directwebremoting.impl.DefaultRemoter;

import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.session.SessionUtil;

/**
 * dwr请求拦截
 * 
 * @author pengning
 * @date 2015-7-2 下午12:58:56
 * @Description
 */
public class DwrRequestIntercept extends DefaultRemoter {
	@Override
	public Replies execute(Calls calls) {
		SnowLog.getServerLog().debug("dwr request intercept....");
		HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
		SessionUserInfo userInfo = SessionUtil.getSessionUserInfo(request);
		if (userInfo == null) {// session超时处理
			WebContext wct = WebContextFactory.get();
			wct.getScriptSession().addScript(new ScriptBuffer("top.location.reload()"));
			return super.execute(new Calls());
		} else {
			return super.execute(calls);
		}
	}

	/**
	 * 放入sessionuserinfo
	 */
	@Override
	public Reply execute(Call call) {
		HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
		try {
			SessionUtil.getRequest2ThreadLocal(request);// 将SessionUserInfo放入threadLoacl
		} catch (Exception ex) {
			throw new SecurityException(ex.getMessage());
		}
		return super.execute(call);
	}
}
