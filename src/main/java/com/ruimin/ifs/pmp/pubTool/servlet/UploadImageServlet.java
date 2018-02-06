package com.ruimin.ifs.pmp.pubTool.servlet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;

import com.google.gson.Gson;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants;
import com.ruimin.ifs.pmp.pubTool.common.servlet.BaseUploadServlet;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;

public class UploadImageServlet extends BaseUploadServlet {
	/** 常量声明 */
	String requestFileKey = "chlFile";
	String RequestChnlKey = "chlNo";// 渠道信息
	String ReauestFileTypeKey = "fileType";// 文件类型
	String end = "\r\n";
	String twoHyphens = "--";
	String boundary = "---------------------------823928434";

	/** 配置化声明 */
	private String urlStr = SysParamUtil.getParam(ImportPicConstants.UPLOAD_IMAGE);// 上传地址
	private String displayUrl = SysParamUtil.getParam(ImportPicConstants.SHOW_IMAGE);// 展示地址
	private String RequestChnlNo = SysParamUtil.getParam(ImportPicConstants.REQUEST_CHNL_NO);// 请求渠道号
	private String ReauestFileType = ImportPicConstants.FILE_TYPE_IMAGE;// 文件类型-图片

	/** 加载SnowLog */
	Logger log = SnowLog.getLogger(UploadImageServlet.class);

	
	/** servlet配置参数 */

	public static final String PARM_NAME_SINGLE_FILE_MAX_SIZE = "singleFileMaxSize";
	public static final String PARM_NAME_ONE_BATCH_MAX_SIZE = "oneBatchMaxSize";
	private long singleFileMaxSize;
	private long oneBatchMaxSize;
	
	
	public void init() throws ServletException {
		String temp = getInitParameter(PARM_NAME_SINGLE_FILE_MAX_SIZE);
		if (!StringUtil.isBlank(temp)) {
			singleFileMaxSize = Integer.valueOf(temp);
		}else{
			singleFileMaxSize = default_singleFileMaxSize;
		}
		log.debug("UploadImageServlet初始化参数【singleFileMaxSize】=" + singleFileMaxSize+"MB");
		temp = getInitParameter(PARM_NAME_ONE_BATCH_MAX_SIZE);
		if (!StringUtil.isBlank(temp)) {
			oneBatchMaxSize = Integer.valueOf(temp);
		}else{
			oneBatchMaxSize = default_onceFileMaxSize;
		}
		log.debug("UploadImageServlet初始化参数【oneBatchMaxSize】=" + singleFileMaxSize+"MB");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileCode = "";// 文件码
		String picType = request.getParameter("picType");
		String picNum = request.getParameter("picNum");
		String message = "";
		String successFlag = "true";
		try {
			// 设置请求数据格式
			request.setCharacterEncoding("UTF-8");

			// 设置返回数据格式
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.setContentType("image/jpeg");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Expires", "0");
			response.setDateHeader("Expires", 0);

			DiskFileItemFactory dfif = new DiskFileItemFactory();// 磁盘文件工厂

			ServletFileUpload sfu = new ServletFileUpload(dfif);// 用以上工厂实例化上传组件

			List<FileItem> fileList = null;// 从request得到 所有 上传域的列表
			try {
				fileList = sfu.parseRequest(request);
			} catch (FileUploadException e) {
				log.error("文件解析失败");
			}

			/** 获取文件列表 */
			Iterator<FileItem> fileItr = fileList.iterator();
			while (fileItr.hasNext()) {
				// 得到当前文件
				FileItem fileItem = (FileItem) fileItr.next();
				// 得到文件属性：【数据流】、【文件名】、【文件大小】
				String dataStream = fileItem.getString();
				String fileName = fileItem.getName();
				long size = fileItem.getSize();
				
				if(size>singleFileMaxSize*ONE_MB){
					SnowExceptionUtil.throwErrorException("上传文件过大（最大限制"+singleFileMaxSize+"MB）");
				}
				
				// 装入文件容器
				String result = sendImage(dataStream, fileName);
				// 获取文件码
				Gson gson = new Gson();
				Map<String, Object> resultMap = gson.fromJson(result, HashMap.class);
				String respCode = (String) resultMap.get("respCode");// 服务器响应码
				if (!respCode.equals("0000")) {
					SnowExceptionUtil.throwErrorException("文件服务器响应错误，请重新尝试！");
				}

				/*
				 * List<Map<String,String>> list = (List<Map<String,String>>)
				 * resultMap.get("fileDataList"); Map<String, String> map =
				 * list.get(0); System.out.println(map.toString()); fileCode =
				 * map.get("downloadUrlCode");.replace("\\", "/")
				 */
				String fileDataList = resultMap.get("fileDataList").toString().replace("[", "").replace("]", "")
						.replace("{", "{\"").replace("}", "\"}").replace("=", "\":\"").replace(", ", "\"" + ", " + "\"")
						.replace("\\", "/");
				log.info("fileData:" + fileDataList);
				Map<String, Object> fileDataListMap = gson.fromJson(fileDataList, HashMap.class);// 文件信息列表
				fileCode = (String) fileDataListMap.get("downloadUrlCode");
				// 打印日志
				log.info("组装完成,结果为:" + result);
				log.info("文件保存成功:  文件名: " + fileName + "|| 文件大小: " + size + "字节.");
				log.info("文件码:" + fileCode);
			}
		} catch (SnowException se) {
			successFlag ="false";
			log.error("业务校验不通过：",se);
			message=se.getMessage();
		}catch (Exception e) {
			successFlag ="false";
			log.error("[UploadImageServlet]系统异常：",e);
			message="系统异常";
		} finally {
			request.setAttribute("fileCode", fileCode);
			request.setAttribute("result", successFlag);
			request.setAttribute("displayUrl", displayUrl);
			request.setAttribute("picNum", picNum);
			request.setAttribute("message", message);
			if (picType.equals("pmpBank")) {
				request.getRequestDispatcher("/pages/payPmp/pubTool/showImageForBank.jsp").forward(request, response);
			} else if (picType.equals("App")) {
				request.getRequestDispatcher("/pages/payPmp/pubTool/showImageForApp.jsp").forward(request, response);
			} else if (picType.equals("Gateway")) {
				request.getRequestDispatcher("/pages/payPmp/pubTool/showImageForGateway.jsp").forward(request,
						response);
			} else {
				request.getRequestDispatcher("/pages/payPmp/pubTool/showImage.jsp").forward(request, response);
			}
		}
	}

	private String sendImage(String dataStream, String fileName) throws SnowException, IOException {
		/** 变量声明 */
		HttpURLConnection httpURLConnection = null;// http连接器
		DataOutputStream dos = null;// 输出流
		InputStream is = null;// 输入流
		String result = "";// 返回结果
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			URL url = new URL(urlStr);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

			dos = new DataOutputStream(httpURLConnection.getOutputStream());
			/** 渠道信息 */
			dos.writeBytes(twoHyphens + boundary + end);
			String messageWrite = "Content-Disposition: form-data; name=\"" + RequestChnlKey + "\"" + end + end
					+ RequestChnlNo + end + twoHyphens + boundary + end;
			dos.writeBytes(messageWrite);
			/** 文件类型 */
			messageWrite = "Content-Disposition: form-data; name=\"" + ReauestFileTypeKey + "\"" + end + end
					+ ReauestFileType + end + twoHyphens + boundary + end;
			dos.writeBytes(messageWrite);
			/** 图片信息 */
			String fileNameMessage = "Content-Disposition: form-data; name=\"" + requestFileKey + "\"; filename=\""
					+ fileName + "\"" + end;
			dos.write(fileNameMessage.getBytes("UTF-8"));
			dos.writeBytes("Content-Type:image/jpeg" + end + end);
			dos.writeBytes(dataStream);
			dos.writeBytes(end + twoHyphens + boundary + twoHyphens + end);
			dos.flush();

			// 读取服务器返回结果
			is = httpURLConnection.getInputStream();
			 isr = new InputStreamReader(is, "utf-8");
			 br = new BufferedReader(isr);
			result = br.readLine();
			is.close();
			return result;

		} catch (Exception e) {
			SnowExceptionUtil.throwErrorException("上传图片失败，请重新尝试！  原因：" + e.getMessage());
			return result;
		} finally {
		    /** modify by fengwei 20180116 代码完善,正确的关闭资源  没有jira */
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            /** modify end */
			if (is != null) {
				is.close();
			}
			if (dos != null) {
				dos.close();
			}
			if (httpURLConnection != null) {
				httpURLConnection.disconnect();
			}
		}
	}

//	public static void main(String[] args) {
//		Gson gson = new Gson();
//		String json = "{'downloadUrlCode':'CBD5280E242A6FEF38D33D5C76940780D7D3D912E6CBDC8CBEA58BF355714AD79506896CCEE592424DA3FDCF30726481FCFE1CEE39B8D4DE3F29317CD804E6D1B10C4232B0993B47ED5652E130CEC2DD', 'fileName':'20161205112621B4CADB250B3143F1A36E4277A2EC839C.bmp', 'filePath':'/home/was/files/03/401/20161205112621B4CADB250B3143F1A36E4277A2EC839C.bmp', 'fileSize':'443552', 'fileType':'image/jpeg', 'fileUploadPath':'/CTXNAS/Redirection$/shrm_tyzf007/Desktop/111.bmp', 'origFileName':'111.bmp'}";
//		HashMap map = gson.fromJson(json, HashMap.class);
//		System.out.println(map.toString());
//
//		String result = "[{downloadUrlCode=CBD5280E242A6FEF38D33D5C76940780D7D3D912E6CBDC8CBEA58BF355714AD79506896CCEE592424DA3FDCF30726481FCFE1CEE39B8D4DE3F29317CD804E6D1B10C4232B0993B47ED5652E130CEC2DD, fileName=20161205112621B4CADB250B3143F1A36E4277A2EC839C.bmp, filePath=/home/was/files/03/401/20161205112621B4CADB250B3143F1A36E4277A2EC839C.bmp, fileSize=443552, fileType=image/jpeg, fileUploadPath=/CTXNAS/Redirection$/shrm_tyzf007/Desktop/111.bmp, origFileName=111.bmp}]";
//		String fileDataList = result.replace("[", "").replace("]", "").replace("{", "{\"").replace("}", "\"}")
//				.replace("=", "\":\"").replace(", ", "\"" + ", " + "\"");
//
//		System.out.println(fileDataList);
//		Map<String, Object> fileDataListMap = gson.fromJson(fileDataList, HashMap.class);// 文件信息列表
//		System.out.println(fileDataListMap.toString());
//
//	}

}
