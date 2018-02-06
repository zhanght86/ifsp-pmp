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
import com.ruim.ifsp.signature.IfspSdkSignAtureUtil;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.pubTool.common.constants.ImportCertConstants;
import com.ruimin.ifs.pmp.pubTool.common.servlet.BaseUploadServlet;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;

/**
 * 上传证书<br>
 */
public class UploadCertServlet extends BaseUploadServlet {
	/** 常量声明 */
	String requestFileKey = "chlFile";
	String RequestChnlKey = "chlNo";// 渠道信息
	String ReauestFileTypeKey = "fileType";// 文件类型
	String end = "\r\n";
	String twoHyphens = "--";
	String boundary = "---------------------------839012832";

	/** 配置化声明 */
	private String urlStr = SysParamUtil.getParam(ImportCertConstants.UPLOAD_FILE);// 上传地址
	private String RequestChnlNo = SysParamUtil.getParam(ImportCertConstants.REQUEST_CHNL_NO);// 请求渠道号
	private String ReauestFileType = "";// 文件类型
	String errorMsg = "";// 错误提示

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
		log.debug("UploadCertServlet初始化参数【singleFileMaxSize】=" + singleFileMaxSize+"MB");
		temp = getInitParameter(PARM_NAME_ONE_BATCH_MAX_SIZE);
		if (!StringUtil.isBlank(temp)) {
			oneBatchMaxSize = Integer.valueOf(temp);
		}else{
			oneBatchMaxSize = default_onceFileMaxSize;
		}
		log.debug("UploadCertServlet初始化参数【oneBatchMaxSize】=" + singleFileMaxSize+"MB");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileCode = "";// 文件码
		String certType = request.getParameter("certType");
		int flag = 0;// 标识上传是否成功，0-失败，1-成功
		String fileName = "";
		String certifiId = "";// 证书ID，仅当公钥时有效
		String filePath = "";// 文件路径

		try {
			// 判断证书类型
			if (StringUtils.isBlank(certType)) {
				SnowExceptionUtil.throwErrorException("证书类型无法获取！");
			} else if (certType.equals(ImportCertConstants.FILE_TYPE_PRIVATE_CERT)) {
				ReauestFileType = ImportCertConstants.FILE_TYPE_PRIVATE_CERT;
			} else if (certType.equals(ImportCertConstants.FILE_TYPE_PUBLIC_CERT)) {
				ReauestFileType = ImportCertConstants.FILE_TYPE_PUBLIC_CERT;
			} else {
				SnowExceptionUtil.throwErrorException("证书类型无法识别！");
			}

			// 设置请求数据格式
			request.setCharacterEncoding("UTF-8");

			// 设置返回数据格式
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.setContentType("application/octet-stream");
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
				fileName = fileItem.getName();
				long size = fileItem.getSize();
				if(size>singleFileMaxSize*ONE_MB){
					SnowExceptionUtil.throwErrorException("上传文件过大（最大限制"+singleFileMaxSize+"MB）");
				}
				if (certType.equals(ImportCertConstants.FILE_TYPE_PUBLIC_CERT)) {
					certifiId = IfspSdkSignAtureUtil.getRSAValidateCertInfo(fileItem.getInputStream()).getCertId();
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
				String fileDataList = resultMap.get("fileDataList").toString().replace("[", "").replace("]", "")
						.replace("{", "{\"").replace("}", "\"}").replace("=", "\":\"")
						.replace(", ", "\"" + ", " + "\"");
				Map<String, Object> fileDataListMap = gson.fromJson(fileDataList, HashMap.class);// 文件信息列表
				fileCode = (String) fileDataListMap.get("downloadUrlCode");
				fileName = (String) fileDataListMap.get("fileName");
				filePath = (String) fileDataListMap.get("filePath");
				flag = 1;

				// 打印日志
				log.info("组装完成,结果为:" + result);
				log.info("文件保存成功:  文件名: " + fileName + "|| 文件大小: " + size + "字节.");
				log.info("文件码:" + fileCode);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();// 错误提示
			log.error(errorMsg);
		} finally {
			int type = 1;
			request.setAttribute("certName", fileName); // 文件名
			if (certType.equals(ImportCertConstants.FILE_TYPE_PUBLIC_CERT)) {
				type = 2;
				request.setAttribute("certType", type);// 证书类型-公钥
				request.setAttribute("certifiId", certifiId); // 证书ID，仅当公钥时有效
			} else {
				request.setAttribute("certType", type);// 证书类型-私钥
				request.setAttribute("certifiId", "nouse"); // 证书ID，仅当公钥时有效
			}
			request.setAttribute("certPath", filePath); // 路径
			request.setAttribute("certCode", fileCode); // 文件编号
			if (flag == 1) {
				request.setAttribute("message", "上传成功"); // 消息
			} else {
				request.setAttribute("message", "上传失败，原因：【" + errorMsg + "】"); // 消息
			}

			request.setAttribute("flag", flag); // 成功标志
			request.getRequestDispatcher("/pages/payPmp/pubTool/importCer.jsp").forward(request, response);
		}
	}

	private String sendImage(String dataStream, String fileName) throws SnowException, IOException {
		/** 变量声明 */
		HttpURLConnection httpURLConnection = null;// http连接器
		DataOutputStream dos = null;// 输出流
		InputStream is = null;// 输入流
		String result = "";// 返回结果
		InputStreamReader isr =null;
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
			/** 证书信息 */
			String fileNameMessage = "Content-Disposition: form-data; name=\"" + requestFileKey + "\"; filename=\""
					+ fileName + "\"" + end;
			dos.write(fileNameMessage.getBytes("UTF-8"));
			dos.writeBytes("Content-Type:application/octet-stream" + end + end);
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
			SnowExceptionUtil.throwErrorException("上传证书失败，请重新尝试！  原因：" + e.getMessage());
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

	
}
