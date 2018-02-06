package com.ruimin.ifs.oscf.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.IFinSnow;
import com.ruimin.ifs.core.global.GlobalUtil;
import com.ruimin.ifs.core.util.constant.SnowConstants;
import com.ruimin.ifs.core.util.constant.SnowEnum.ProjectType;

public class iFinSnowListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// 停止
		IFinSnow.getInstance().stop(false);
		// 卸载
		IFinSnow.getInstance().destory();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 设置运行类型及contextpath
		GlobalUtil.setProjectRunType(ProjectType.J2EE);
		GlobalUtil.setProjectRootPath(event.getServletContext().getRealPath(SnowConstants.J2EE_CLASS_PATH));
		try {
			String nodeId = event.getServletContext().getInitParameter(SnowConstants.JVM_NODE_ID);
			if (StringUtils.isNotBlank(nodeId)) {
				IFinSnow.getInstance(nodeId).start();
			} else {
				IFinSnow.getInstance().start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
