package com.ruimin.ifs.oscf.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import com.ruimin.ifs.channel.server.servlet.listener.AbsSnowChannelServlet;

public class HttpChannelServlet extends AbsSnowChannelServlet {
	private static final long serialVersionUID = 1595588675202889283L;

	private String channelId;

	@Override
	public String getChannelId() {
		return channelId;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// 可配置在web.xml的servlet的参数中
		channelId = config.getInitParameter("channelId");
	}
}
