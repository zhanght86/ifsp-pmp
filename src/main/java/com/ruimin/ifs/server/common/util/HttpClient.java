/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: HttpClient.java
 * Author:   GH
 * Date:     2015年9月8日 下午2:04:54
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.server.common.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.param.ParamsUtil;
import com.ruimin.ifs.core.util.constant.SnowErrorCode;

/**
 * 报文转发（http+json)
 * 
 * @author zhqy
 * 
 */
public class HttpClient {
	/**
	 * 发送并接收报文
	 * 
	 * @param reqMap
	 * @param channel
	 * @return
	 * @throws SnowException
	 */
	public static String sendAndRec(Object message, String urlStr) throws SnowException {

		String messageStr = "";
		if (message != null) {
			if (message instanceof String) {
				messageStr = (String) message;
			} else {
				Gson gson = new Gson();
				messageStr = gson.toJson(message);
			}
		}

		String result = null;
		PrintWriter conOut =null;
		InputStream inputStream = null;
		HttpURLConnection connection = null;
		try {
			URL url = new URL(urlStr);

			 connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setReadTimeout(Integer.valueOf(ParamsUtil.getBizsParam("outTime")) * 1000);
			connection.setRequestProperty("Content-type", "text/json");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setRequestProperty("contentType", "UTF-8");
			connection.connect();

			SnowLog.getServerLog().info("send:" + message);
			SnowLog.getServerLog().info("报文长度：" + messageStr.getBytes().length);
			conOut=new PrintWriter(new BufferedOutputStream(connection.getOutputStream()));
			conOut.write(messageStr);
			conOut.flush();
			inputStream = connection.getInputStream();
			result = changeInputStreamToString(inputStream, "UTF-8");
			SnowLog.getServerLog().info("ret:" + result);
		} catch (IOException e) {
			SnowLog.getServerLog().error(e.getMessage());
			SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_SYS_0052, e);
		}finally {  
	           if (conOut != null) {  
	               conOut.close(); // 关闭流    
	           }  
	           if(inputStream != null){
	        	   try {
	        		   inputStream.close();
				} catch (Exception e2) {
					SnowLog.getServerLog().error("httpClient---finally关闭流异常：",e2);
				}
	           }
	           /** modify by fengwei 20180116 代码完善,正确的关闭资源  没有jira */
	           if(connection!=null){
	               connection.disconnect();
	           }
	           /** modify end */
	       }  

		return result;
	}

	private static String changeInputStreamToString(InputStream is, String charsetName)
			throws UnsupportedEncodingException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is, charsetName));

		StringBuffer sb = new StringBuffer();
		String str = null;

		while ((str = br.readLine()) != null) {
			sb.append(str);
		}
		br.close();

		return sb.toString();
	}

//	public static void main(String[] args) throws SnowException {
//		 String msg = "{'mchtPhone':'15901985388',
//		 'mchtAddr':'你','mchtCnAbbr':'你','mchtArtifPicNo':['signedimage.png','ignedimage.png','gnedimage.png','nedimage.png','edimage.png','dimage.png','image.png','mage.png','age.png','ge.png','e.png','.png','png','ng','g'],'mchtLicnNo':'123456','mchtLongitude':'31.162664','mchtArtifName':'你','mchtArtifType':'00','rgstId':'100047','mchtLatitude':'121.398187','busCode':'621001','mchtArtifId':'852369854423977','mchtArtifPhone':'15901985388','mchtArtifPicPath':'output','mchtPersonName':'你','oprId':'100047','channel':'61','mchtName':'你','reqSsn':'ededb46fb5e74e2da8345cf4261afb9c','reqDate':'20150902','reqTime':'105946'}";
//		 String urlstr =
//		 "http://localhost:8080/iFinPMPServer/HttpChannelServlet";
//		 sendAndRec(msg,urlstr);
//
//		String encoding = System.getProperty("file.encoding");
//		System.out.println("Default System Encoding:" + encoding);
//	}
}
