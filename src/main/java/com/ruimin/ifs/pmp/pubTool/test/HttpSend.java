package com.ruimin.ifs.pmp.pubTool.test;

//import java.io.BufferedReader;

//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//import java.io.BufferedOutputStream;
//import java.io.UnsupportedEncodingException;
//
//import com.ruimin.ifs.core.exception.SnowException;
//import com.ruimin.ifs.core.log.SnowLog;
//
//
//
//
//
///**
// * 
// * 名称：〈测试快捷支付〉<br> 
// * 功能：〈功能详细描述〉<br>
// * 版本：1.0 <br>
// * 日期：2016年5月13日 <br>
// * 作者：yema <br>
// * 说明：<br>
// */
//public class HttpSend {
//    
//
//	public String sendMessage(String data,String urlParam)throws Exception{
//		
//		String str = new String();		
//		str = data;
//		
//		URL url = new URL(urlParam);
//		HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
//		httpURLConnection.setDoOutput(true);
//		httpURLConnection.setDoInput(true);
//		httpURLConnection.setConnectTimeout(300000);
//		httpURLConnection.setReadTimeout(30000);
//		httpURLConnection.setRequestMethod("POST");
//		httpURLConnection.addRequestProperty("Content-Length",str.length() + "");
//		httpURLConnection.addRequestProperty("Content-Type", "application/xml;charset=utf-8");
//		httpURLConnection.connect();
//		BufferedOutputStream out=new BufferedOutputStream(httpURLConnection.getOutputStream());
//		
//		out.write(str.getBytes("UTF-8"));
//		out.flush();
//			
//		String respenseCode = "" +httpURLConnection.getResponseCode();
//		SnowLog.getLogger(this.getClass()).info("respenseCode:="+respenseCode+"\n");
//		String result = changeInputStreamToString(httpURLConnection.getInputStream(), "UTF-8");
//		return result;
//	}
//    
//    /**
//	 * 功能描述:将返回的strem流转换为字符串<br>
//	 * 功能详细描述〉
//	 *
//	 * @param is stream流,charsetName 编码方式
//	 * @return Map 应答信息
//	 * @throws SnowException
//	 * @see [相关类/方法](可选)
//	 * @since [产品/模块版本](可选)
//	 */
//	private static String changeInputStreamToString(InputStream is, String charsetName)
//			throws UnsupportedEncodingException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(is,charsetName));
//
//		StringBuffer sb = new StringBuffer();
//		String str = null;
//
//		while ((str = br.readLine()) != null) {
//			sb.append(str);
//		}
//		br.close();
//
//		return sb.toString();
//	}
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.ruim.ifsp.utils.client.http.IfspHttpClientUtil;
import com.ruim.ifsp.utils.verify.IfspDataVerifyUtil;
import com.ruimin.ifs.core.log.SnowLog;

public class HttpSend {
	public static String sendMessage(String data, String urlParam) throws Exception {
		SnowLog.getGroupLog().info("支付管理平台手动触发支付批量系统跑批http请求业务处理：[ -------------------开始--------------------- ]");
		if (IfspDataVerifyUtil.isBlank(urlParam)) {
			SnowLog.getGroupLog().info("支付管理平台手动触发支付批量系统跑批http请求业务处理失败：[ urlParam is null ]");
			return null;
		}
		if (IfspDataVerifyUtil.isBlank(data)) {
			SnowLog.getGroupLog().info("支付管理平台手动触发支付批量系统跑批http请求业务处理失败：[ data is null ]");
			return null;
		}
		IfspHttpClientUtil http = new IfspHttpClientUtil("支付平台批量系统", urlParam, 20000, 20000, "UTF-8", false, false,
				null, 0);
		int httpCode = http.send(data);
		if (httpCode == 200) {
			SnowLog.getGroupLog().info("支付管理平台手动触发支付批量系统跑批http请求业务处理：[ respData：" + http.getSendData() + " ]");
			SnowLog.getGroupLog().info("支付管理平台手动触发支付批量系统跑批http请求业务处理：[ -------------------完成--------------------- ]");
			return http.getSendData();
		} else {
			SnowLog.getGroupLog().info("支付管理平台手动触发支付批量系统跑批http请求业务处理失败：[ http通讯异常：" + http.getSendData() + " ]");
			SnowLog.getGroupLog().info("支付管理平台手动触发支付批量系统跑批http请求业务处理：[ -------------------完成--------------------- ]");
			return null;
		}

		//
		//
		// String result = null;
		// try{
		// //组装报文
		//// Map<String, String> map = new HashMap<String, String>();
		//// map.put("chlNo", "60001");
		//// map.put("reqData", "上海市徐汇区古美路1515号19号22层");
		//// String sendMsg = new Gson().toJson(map);//发送报文
		// String len = String.valueOf(data.length());
		// SnowLog.getLogger(this.getClass()).info("报文待发送：" + data + "；长度：" +
		// len);
		//
		// //建立连接
		// URL url = new URL(urlParam);
		// HttpURLConnection urlConnection =
		// (HttpURLConnection)url.openConnection();
		// urlConnection.setRequestMethod("POST");
		// urlConnection.setDoInput(true);
		// urlConnection.setDoOutput(true);
		// urlConnection.connect();
		//
		// //发送报文
		// urlConnection.getOutputStream().write(data.getBytes("UTF-8"));
		// urlConnection.getOutputStream().flush();
		//
		//
		// //读取服务器返回结果
		// InputStream is = urlConnection.getInputStream();//输入流
		// result = "";//返回结果
		// InputStreamReader isr = new InputStreamReader(is, "utf-8");
		// BufferedReader br = new BufferedReader(isr);
		// result = br.readLine();
		// SnowLog.getLogger(this.getClass()).info("接收成功：" + result);
		// is.close();
		//
		// }catch(IOException e){
		// e.printStackTrace();
		// }
		// return result;
	}

}