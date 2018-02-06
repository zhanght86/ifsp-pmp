package com.ruimin.ifs.pmp.pubTool.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.pubTool.common.constants.HttpTransConstants;

/**
 * 
 * Http通信-工具类【编码方式，UTF-8】
 * 
 * @author zhangjc
 *
 */
public class HttpTransUtil {
	/** 加载SnowLog */
	static Logger log = SnowLog.getLogger(HttpTransUtil.class);

	/********************************** 配置参数 **************************************/
	/** 等待服务器响应超时时间（毫秒） */
	private int readTimeOut;
	/** 连接服务器超时时间，默认30秒 */
	private int connectionTimeout;

	/*****************************************************************************/

	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static HttpTransUtil getInstance() throws SnowException {
		return ContextUtil.getSingleService(HttpTransUtil.class);
	}

	/** 初始化 */
	public void init() {
		this.readTimeOut = (StringUtils.isNotBlank(SysParamUtil.getParam((HttpTransConstants.readTimeOut)))
				? Integer.valueOf(SysParamUtil.getParam((HttpTransConstants.readTimeOut))) : 30000);
		this.connectionTimeout = (StringUtils.isNotBlank(SysParamUtil.getParam((HttpTransConstants.connectionTimeout)))
				? Integer.valueOf(SysParamUtil.getParam((HttpTransConstants.connectionTimeout))) : 30000);
	}

	/**
	 * 建立连接
	 * 
	 * @param url
	 *            资源地址
	 * @param requestMehtod
	 *            请求方式
	 * @return urlConnection HttpURLConnection实例
	 * @throws Exception
	 */
	public HttpURLConnection buildConnect(URL url, String requestMehtod) throws Exception {
		HttpURLConnection urlConnection = null;// 初始化
		try {
			Long startTime = System.currentTimeMillis();
			/** 初始化 */
			log.info("服务器初始化链接【开始】...");
			init();

			if (StringUtils.isBlank(url.toString())) {
				SnowExceptionUtil.throwErrorException("服务器地址为空！");
			}
			log.info("服务器初始化链接【完成】");

			/** 建立连接 */
			log.info("建立连接【开始】...");
			log.info("url：" + url.toString() + ";请求方式：" + requestMehtod);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod(requestMehtod);
			urlConnection.setConnectTimeout(connectionTimeout);
			urlConnection.setReadTimeout(readTimeOut);
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.connect();
			long endTime = System.currentTimeMillis();
			log.info("建立连接【成功】，耗时：" + (endTime - startTime) + " ms ");
		} catch (Exception e) {
			log.error("建立连接【失败】，原因：" + e.getMessage());
			SnowExceptionUtil.throwErrorException(e.getMessage());
		}
		return urlConnection;
	}

	/**
	 * 发送报文
	 * 
	 * @param urlConnection
	 *            HttpURLConnection实例
	 * @param msgStr
	 *            报文内容
	 * @throws Exception
	 */
	public void sendMsg(HttpURLConnection urlConnection, String msgStr) throws Exception {
		try {
			Long startTime = System.currentTimeMillis();
			log.info("发送报文【开始】...");
			log.info("报文内容：" + msgStr);
			OutputStream os = urlConnection.getOutputStream();// 输出流
			os.write(msgStr.getBytes("UTF-8"));
			os.flush();
			os.close();
			long endTime = System.currentTimeMillis();
			log.info("发送报文【成功】，耗时：" + (endTime - startTime) + " ms ");
		} catch (Exception e) {
			log.error("发送报文【失败】，原因：" + e.getMessage());
			SnowExceptionUtil.throwErrorException(e.getMessage());
		}
	}

	/**
	 * 读取服务器响应
	 * 
	 * @param urlConnection
	 *            HttpURLConnection实例
	 * @throws Exception
	 */
	public String recvResponse(HttpURLConnection urlConnection) throws Exception {
		String result = "";// 返回结果
		try {
			Long startTime = System.currentTimeMillis();
			log.info("读取服务器响应【开始】...");
			InputStream is = urlConnection.getInputStream();// 输入流
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			result = br.readLine();
			is.close();
			if (StringUtils.isBlank(result)) {
				SnowExceptionUtil.throwErrorException("服务器无响应，请尝试再次发送请求！");
			}
			long endTime = System.currentTimeMillis();
			log.info("读取服务器响应【成功】，耗时：" + (endTime - startTime) + " ms ");
			log.info("响应内容：" + result);
		} catch (Exception e) {
			log.error("读取服务器响应【失败】，原因：" + e.getMessage());
			SnowExceptionUtil.throwErrorException(e.getMessage());
		}
		return result;
	}
}
