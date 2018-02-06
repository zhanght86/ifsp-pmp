package com.ruimin.ifs.pmp.pubTool.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.pmp.chnlMng.process.service.MchtChnlRequestService;

public class DealMchtChnlReqServlet extends HttpServlet {
	/** 加载SnowLog */
	Logger log = SnowLog.getLogger(MchtChnlRequestService.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			// 接收报文
			InputStream is = request.getInputStream();
			String rcvMsg = "";// 接收内容
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			rcvMsg = br.readLine();
			log.info("服务器成功获取报文：" + rcvMsg);
			is.close();

			// 返回报文
			String retMsg = DealMchtChnlReqAction.getInstance().retTest(rcvMsg);
			log.info("服务器将返回：" + retMsg);
			response.getOutputStream().write(retMsg.getBytes("UTF-8"));
			response.flushBuffer();
			log.info("服务器响应已发送!");
			response.getOutputStream().close();
		} catch (SnowException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
