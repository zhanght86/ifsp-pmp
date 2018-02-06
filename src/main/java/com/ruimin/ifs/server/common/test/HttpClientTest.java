package com.ruimin.ifs.server.common.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;



/**
 * http通讯测试客户端
 * 
 * @author pengning
 * @date 2015-6-25 上午10:35:47
 * @Description
 */

public class HttpClientTest {
//	public static void main(String[] args) {
//
//		Map<String, Object> messageMap = new HashMap<String, Object>();
//		// 公共部分
//		messageMap.put("channel", "61");
//		// messageMap.put("busCode", "616004");
//		messageMap.put("busCode", "621001");
//		messageMap.put("reqSsn", "12C59F0A1700100057306C855c102");
//		messageMap.put("oprId", "99999999");
//		messageMap.put("reqDate", "20150908");
//		messageMap.put("reqTime", "152459");
//
//		// 业务部分
//		// messageMap.put("pageNo", "1");
//		// messageMap.put("pageSize", "10");
//		// messageMap.put("mchtLongitude", "29.03");
//		// messageMap.put("mchtLatitude", "50.03");
//		// messageMap.put("distance", "50");
//		messageMap.put("rgstId", "100057");
//		messageMap.put("mchtName", "美特好美特好");
//		messageMap.put("mchtCnAbbr", "美特好");
//		messageMap.put("mchtArtifName", "包子包子肉肉");
//		messageMap.put("mchtArtifType", "00");
//		messageMap.put("mchtArtifId", "234123198803040018");
//		messageMap.put("mchtArtifPhone", "13344445555");
//		List<String> picList = new ArrayList<String>();
//		picList.add("0002dd46.jpg");
//		picList.add("0002dd47.jpg");
//		picList.add("0002dd48.jpg");
//		picList.add("0002dd49.jpg");
//		messageMap.put("mchtArtifPicNo", picList);
//		messageMap.put("mchtArtifPicPath", "/home/pic/info");
//		messageMap.put("mchtPersonName", "100057");
//		messageMap.put("mchtPhone", "13344445555");
//		// messageMap.put("mchtTrcnNo", "100057");
//		List<String> liveList = new ArrayList<String>();
//		liveList.add("0002dd55.jpg");
//		liveList.add("0002dd56.jpg");
//		liveList.add("0002dd57.jpg");
//		messageMap.put("livePicNo", liveList);
//		messageMap.put("livePicPath", "/home/pic/info");
//		messageMap.put("mchtAddr", "南关大道188号");
//		messageMap.put("logoPicNo", "00023337.jpg");
//		messageMap.put("logoPicPath", "100057");
//		messageMap.put("mchtLicnNo", "100057");
//		messageMap.put("mchtLicnPicPath", "/home/pic/info");
//		messageMap.put("mchtLongitude", "29.03");
//		messageMap.put("mchtLatitude", "50.03");
//		messageMap.put("mchtAmrNo", "333444555");
//		messageMap.put("mchtAmrName", "黄晶晶");
//		messageMap.put("mchtAmrPhone", "13344556677");
//
//		Gson gson = new Gson();
//		String message = gson.toJson(messageMap);
//		System.out.println("\n message=" + message + "\n");
//		String urlstr = "http://localhost:8080/ifsPMP/HttpChannelServlet";
//		// String urlstr =
//		// "http://10.23.0.246:8080/iFinPMPServer/HttpChannelServlet";
//		// String urlstr =
//		// "http://localhost:8090/iFinPMPServer/HttpChannelServlet";
//		// String urlstr =
//		// "http://10.20.155.3:8081/iFinPMPServer/HttpChannelServlet";
//		// Map<String, String> handMap = new HashMap<String, String>();
//		// handMap.put("sda", "IFSP01");
//		// handMap.put("_tn", "1EE5C59F0A1700100057306C855BFBB0");
//		// handMap.put("_si", "TRA003010101");
//		try {
//
//			for (int i = 0; i < 1; i++) {
//				URL url = new URL(urlstr);
//				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//				connection.setDoOutput(true);
//				connection.setDoInput(true);
//				// for (Entry<String, String> entry : handMap.entrySet()) {
//				// connection.addRequestProperty(entry.getKey(),
//				// entry.getValue());
//				// }
//				connection.connect();
//				System.out.println("send:" + message);
//				System.out.println(message.getBytes().length);
//
//				PrintWriter conOut = new PrintWriter(new BufferedOutputStream(connection.getOutputStream()));
//				conOut.write(message);
//				conOut.flush();
//				conOut.close();
//
//				System.out.println(connection.getResponseCode());
//				System.out.println(connection.getContentLength());
//				Map<String, List<String>> map = connection.getHeaderFields();
//				for (Entry<String, List<String>> entry : map.entrySet()) {
//					System.out.println("key=" + entry.getKey());
//					for (String string : entry.getValue()) {
//						System.out.println("------" + string);
//					}
//				}
//				// System.out.println(connection.getHeaderField("Content-Disposition"));
//				// String s = "attachment; FileName='1.properties'";//
//				// connection.getHeaderField("Content-Disposition");
//				// System.out.println(getFileName(s));
//				String result = changeInputStreamToString(connection.getInputStream(), "UTF-8");
//				System.out.println("ret:" + result);
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	private static String getFileName(String contentDisposition) {
		String fileName = null;
		if (StringUtils.isNotBlank(contentDisposition)) {
			String tmp = contentDisposition.toLowerCase();
			String sign = "filename=";
			int idx = tmp.lastIndexOf(sign);
			if (idx >= 0) {
				fileName = contentDisposition.substring(idx + sign.length());
			}
		}
		if (fileName != null) {
			fileName = fileName.replace("\"", "").replace("'", "");
		}
		return fileName;
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
}
