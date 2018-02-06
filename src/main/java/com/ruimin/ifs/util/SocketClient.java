/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: SocketUnit.java
 * Author:   iFinStudio
 * Date:     2015-6-1 下午8:20:11
 * Description: //模块目的、功能描述      
 * History: //修改记录
 */
package com.ruimin.ifs.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class SocketClient {

	private Socket socketClient = null;

	/** 默认socket超时时间(S) */
	private int readTimeout = 30;

	public SocketClient(String serverIp, int serverPort, int timeout) throws Exception {
		readTimeout = timeout;
		System.out.println("SocketClient:" + serverIp + ":" + serverPort);
		try {
			socketClient = new Socket(serverIp, serverPort);
			socketClient.setSoLinger(true, 5);
			System.out.println("创建socket连接:" + socketClient.getInetAddress() + ":" + socketClient.getLocalPort());
		} catch (ConnectException e) {
			// TODO Auto-generated catch block
			System.out.println("创建socket短连接异常：" + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param sendMsg
	 *            发送消息
	 * @param lenFmt
	 *            长度格式:04X:4位16进制数，压缩成2个字节;08d:8位10进制数，左补0
	 * @return
	 * @throws Exception
	 *             2015-8-25 下午02:28:26
	 * @author zhangqi
	 */
	public String sendAndRec(String sendMsg, String encoding, String lenFmt) throws Exception {
		String recXml = null;
		byte[] readByte = new byte[0];
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			socketClient.setSoTimeout(readTimeout * 1000);
			outputStream = socketClient.getOutputStream();
			inputStream = socketClient.getInputStream();

			// 转换编码，组装发送长度
			byte[] writeBytes = getWriteByte(sendMsg, encoding, lenFmt);
			// 将报文发出
			outputStream.write(writeBytes);
			outputStream.flush();

			System.out.println("***************接收应答信息****************");
			// 接收报文
			readByte = readMsg(inputStream, lenFmt);
		} catch (SocketTimeoutException e) {
			// TODO Auto-generated catch block
			System.out.println("socket发送接收超时");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
				if (socketClient != null) {
					socketClient.close();
					socketClient = null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("关闭socket连接异常");
				e.printStackTrace();
			}
		}
		recXml = new String(readByte, encoding);
		System.out.println("接收应答信息 ,长度:[ " + readByte.length + "],内容:[" + recXml + "]");
		return recXml;
	}

	/**
	 * 读取返回信息
	 * 
	 * @param inputStream
	 * @param lenFmt
	 *            长度格式:04X:4位16进制数，压缩成2个字节;08d:8位10进制数，左补0
	 * @return
	 * @throws IOException
	 *             2015-10-27 下午04:27:28
	 * @author zhangqi
	 */
	public static byte[] readMsg(InputStream inputStream, String lenFmt) throws IOException {
		byte[] readByte = new byte[0];
		if (lenFmt.equals("04X")) {
			byte[] lenByte = new byte[2];
			inputStream.read(lenByte);

			int readLen = Integer.valueOf(byte2hex(lenByte), 16);

			System.out.println("读取长度:" + readLen);

			readByte = new byte[readLen];
			inputStream.read(readByte);
		} else if (lenFmt.equals("08d")) {
			byte[] lenByte = new byte[8];
			inputStream.read(lenByte);
			String len = new String(lenByte);

			System.out.println("读取长度:" + len);

			int readLen = Integer.valueOf(len);

			readByte = new byte[readLen];
			inputStream.read(readByte);
		} else {
			byte[] temp = new byte[1];
			while (inputStream.read(temp) > 0) {
				readByte = copyArrayTwo(readByte, temp);
			}
		}
		return readByte;
	}

	/**
	 * 
	 * @param sendMsg
	 *            信息
	 * @param encoding
	 *            编码
	 * @param lenFmt
	 *            长度格式:04X:4位16进制数，压缩成2个字节;08d:8位10进制数，左补0
	 * @return
	 * @throws IOException
	 *             2015-10-27 下午04:12:11
	 * @author zhangqi
	 */
	public static byte[] getWriteByte(String sendMsg, String encoding, String lenFmt) throws IOException {
		byte[] writeBytes = sendMsg.getBytes(encoding);
		int msgLen = writeBytes.length;
		System.out.println("发送请求信息 ,长度:[" + msgLen + "],内容:[" + sendMsg + "]");
		String len = "";
		if (lenFmt.equals("04X")) {
			len = String.format("%04X", msgLen);
			writeBytes = copyArrayTwo(hex2byte(len), writeBytes);
		} else if (lenFmt.equals("08d")) {
			len = String.format("%08d", msgLen);
			writeBytes = copyArrayTwo(len.getBytes(), writeBytes);
		}
		return writeBytes;
	}

	/**
	 * 字节数组转化成HEX
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			sb.append(String.format("%02X", b[i]));
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param obj1
	 * @param obj2
	 * @return obj1+obj2 2015-11-2 下午02:12:09
	 * @author zhangqi
	 */
	public static byte[] copyArrayTwo(byte[] obj1, byte[] obj2) {
		int len1 = obj1.length;
		int len2 = obj2.length;
		byte[] tmp = new byte[len1 + len2];

		System.arraycopy(obj1, 0, tmp, 0, len1);
		System.arraycopy(obj2, 0, tmp, len1, len2);

		return tmp;
	}

	/**
	 * 
	 * @param hex
	 *            必须是偶数位
	 * @return 2015-10-28 下午02:13:41
	 * @author zhangqi
	 */
	public static byte[] hex2byte(String hex) {
		byte[] res = new byte[hex.length() / 2];
		for (int i = 0; i < res.length; i++) {
			res[i] = (byte) (int) Integer.valueOf(hex.substring(i * 2, (i + 1) * 2), 16);
		}
		return res;
	}
}
