/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: SocketUnit.java
 */
package com.ruimin.ifs.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.constant.SnowErrorCode;
import com.ruimin.ifs.gov.util.StringUtils;

public class SocketUnit {

	private Socket socket = null;

	public SocketUnit(String channel) {
		// Properties ps =
		// FSToolUtil.readProperties("genconf.resources.sysParam");
		String ip = FSToolUtil.getProperty(channel + "Ip");
		int port = Integer.parseInt(FSToolUtil.getProperty(channel + "Port"));
		int timeout = Integer.parseInt(FSToolUtil.getProperty("SocketTimeout")) * 1000;
		SnowLog.getServerLog().info("socket:[" + ip + ":" + port + "]" + timeout);
		try {
			socket = new Socket(ip, port);
			socket.setSoLinger(true, 5);
			socket.setSoTimeout(timeout);
		} catch (Exception e) {
			try {
				throw new Exception(e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public String sendAndRec(String sendMsg, String channel) throws Exception {
		String recXml = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		InputStreamReader reader = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		try {
			outputStream = socket.getOutputStream();
			inputStream = socket.getInputStream();

			// 接收报文
			StringBuffer bf = new StringBuffer();
			if (channel.equals("USER00001") || channel.equals("PAYEX00001")) {
				// 将报文发出
				sendMsg = StringUtils.leftPad(sendMsg.getBytes("GB18030").length + "", 8, "0") + sendMsg;

				SnowLog.getServerLog().info("send:" + sendMsg);

				byte[] writeBytes = sendMsg.getBytes("GB18030");
				outputStream.write(writeBytes);
				outputStream.flush();

				byte[] lenByte = new byte[8];
				inputStream.read(lenByte);

				int readLen = Integer.valueOf(new String(lenByte));

				byte[] readByte = new byte[readLen];
				inputStream.read(readByte);

				recXml = new String(readByte, "GB18030");
			} else {
				SnowLog.getServerLog().info("send:" + sendMsg);
				// 将报文发出
				byte[] writeBytes = sendMsg.getBytes("utf-8");
				outputStream.write(writeBytes);
				outputStream.flush();

				reader = new InputStreamReader(inputStream, "utf-8");
				int c = 0;
				while ((c = reader.read()) != -1) {
					bf.append((char) c);
				}
				recXml = bf.toString();
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
			if (reader != null) {
				reader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (socket != null) {
				socket.close();
				socket = null;
			}
		}
		// pw = new PrintWriter(outputStream,true);
		// br = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
		// pw.println(sendMsg);
		// recXml = br.readLine();
		// System.out.println(recXml);
		//
		// br.close();
		// pw.close();

		return recXml;
	}

	public static String makeSendMsg(String msg, String channel) throws SnowException {
		long st = System.currentTimeMillis();
		// StringBuffer sendMsg = new StringBuffer();
		// String s = "";
		// int msgLen = msg.getBytes("utf-8").length;
		// SnowLog.getServerLog().info("发送的报文字节长度："+msgLen);
		// sendMsg.append(StringUtils.leftPad(String.valueOf(msgLen), 8, '0'));
		// sendMsg.append(msg);
		String result = null;
		/*
		 * Gson gson = new Gson(); String msg = gson.toJson(senMap);
		 */
		SocketUnit sc = new SocketUnit(channel);

		try {
			result = sc.sendAndRec(msg, channel);

			// String result =s.substring(8);
			SnowLog.getServerLog().info("return:" + result);
			System.out.println(System.currentTimeMillis() - st);
		} catch (Exception e) {
			SnowLog.getServerLog().error(e.getMessage());
			SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_SYS_0052, e);
		}
		return result;
	}

	public String mapToXml(Map paramMap) {
		return "";
	}

	public Map<String, String> xmlToMap(String xml) {
		return null;
	}
}
