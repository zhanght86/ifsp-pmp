package com.ruimin.ifs.pmp.mchtMng.comp;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.FormRequestBean;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.google.gson.Gson;

import com.ruim.ifsp.utils.datetime.IfspDateTime;
import com.ruim.ifsp.utils.id.IfspId;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtQrcCodeConstants;
import com.ruimin.ifs.pmp.mchtMng.process.bean.QrcBaseInfo;
import com.ruimin.ifs.pmp.mchtMng.process.service.QrcMngService;
import com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants;
import com.ruimin.ifs.pmp.pubTool.util.HttpTransUtil;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;
import com.ruimin.ifs.util.QrCodeUtil;
import com.ruimin.ifs.util.StringUtil;


@ActionResource
@SnowDoc(desc = "二维码管理")
public class QrcMngAction extends SnowAction {
	
	/** 常量声明 */
	String requestFileKey = "chlFile";
	String RequestChnlKey = "chlNo";// 渠道信息
	String ReauestFileTypeKey = "fileType";// 文件类型
	/**HttpURLConnection模拟post提交form表单**/
	String end = "\r\n";
	String twoHyphens = "--";
	//---823928434是自己构造的字符串，可以使任何字符串
	String boundary = "---------------------------823928434";
	
	//二维码图片本地存放临时路径
	static String QRC_TMP_PATH = SysParamUtil.getParam(MchtChnlRequestConstants.QRC_TMP_SAVE_PATH);
	//二维码图片类型
	static String QRC_IMG_TYPE = SysParamUtil.getParam(MchtQrcCodeConstants.QRC_IMG_FILE_TYPE);
	
	static Logger log = SnowLog.getLogger(QrcMngAction.class);
	
	private String RequestChnlNo = SysParamUtil.getParam(ImportPicConstants.REQUEST_CHNL_NO);// 请求渠道号
	private String ReauestFileType = ImportPicConstants.FILE_TYPE_IMAGE;// 文件类型-图片
	
	
	/**
	 * 主页面查询
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		String mchtId = queryBean.getParameter("qMchtId"); // 商户编号
		String mchtSimpleName = queryBean.getParameter("qMchtSimpleName"); // 商户简称
		String mchtAmrName = queryBean.getParameter("qMchtAmrName"); // 客户经理姓名
	      String mchtAmrNo = queryBean.getParameter("qmchtAmrNo"); // 客户经理姓名
		String qrcCodeId = queryBean.getParameter("qQrcCodeId"); // 二维码序号
		String qrcStat = queryBean.getParameter("qQrcStat"); // 二维码状态
		String mchtUseStat = queryBean.getParameter("qMchtUseStat");//使用状态
		String qrcType = queryBean.getParameter("qQrcType");//二维码类型
		// 获取当前操作员信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String qbrcode = sessionUserInfo.getBrCode();
		return QrcMngService.getInstance().queryList(mchtId, mchtSimpleName,mchtAmrNo, mchtAmrName,qrcCodeId,qrcStat,mchtUseStat,qbrcode,qrcType,
				queryBean.getPage());
	}
	
	
	
	/**
	 * 启用停用二维码
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "启用停用")
	public void stautsActive(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		//获取当前用户
		 SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		 //获取页面传递的信息
		 UpdateRequestBean reqBean = updateMap.get("qrcBaseInfoMng");
		 String qrcCodeId = reqBean.getParameter("qrcCodeId");
		 String mchtUseStat = reqBean.getParameter("mchtUseStat");
		 String mchtId = reqBean.getParameter("mchtId");//商户号
	     String qrcType = reqBean.getParameter("qrcType");//二维码类型
	       mchtUseStat = "0".equals(mchtUseStat) ? "1" : "0";
	      //根据商户号获取该商户正式表中签约的支付产品
	        String dealTypeString = new ContractAuditAction().getMchtDealTypeString(mchtId);
	        try {
    		 if("0".equals(mchtUseStat)){
    		   //将已有的二维码作废
    	            stopMchtQrc(mchtId,qrcType);
    	            log.info("已经将商户号为:"+mchtId+",二维码类型："+qrcType+"--原有的二维码信息作废");
    	            QrcBaseInfo baseInfo = new QrcBaseInfo();
    	            baseInfo.setQrcCodeId(qrcCodeId);
    	            baseInfo.setMchtUseStat(mchtUseStat);
    	            baseInfo.setLastUpdTlr(sessionUserInfo.getTlrno());
    	            baseInfo.setLastUpdDateTime(DateUtil.get14Date());
    	            //数据持久化
    	            QrcMngService.getInstance().statusChange(baseInfo);
    	            
    		 }else{
    		   //生成新的二维码信息
                 QrcMngService.getInstance().modifyMchtQrcStateByMchtId(mchtId,qrcType);
    	            new ContractAuditAction().modifyMchtQrc(mchtId, "000", dealTypeString);
    	            log.info("给商户号为:"+mchtId+",二维码类型："+qrcType+"--生成新的二维码完成");
    		 }
	        } catch (Exception e) {
	            log.error("重新申请商户二维码失败,原因:"+e.getMessage());
	            SnowExceptionUtil.throwWarnException("重新申请商户二维码失败,原因:"+e);
	        }   
	      //记日志
		 sessionUserInfo.addBizLog("update.log",
		 new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
		 "[商户二维码]--启用/停用:二维码序号=[" + qrcCodeId + "]" });
	}
	
	/**
	 * 二维码重新申请
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "重新申请")
	public void againApplyActive(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		log.info("进入重新申请方法");
		//获取当前用户
		 SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		//获取页面传递的信息
		UpdateRequestBean reqBean = updateMap.get("qrcBaseInfoMng");
		String mchtId = reqBean.getParameter("mchtId");//商户号
		String qrcType = reqBean.getParameter("qrcType");//二维码类型
	    String qrcStat = reqBean.getParameter("qrcStat");//二维码状态状态
	       String mchtUseStat = reqBean.getParameter("mchtUseStat");//二维码使用状态
		log.info("重新申请二维码的商户编号:"+mchtId+",二维码类型："+qrcType);
		//根据商户号获取该商户正式表中签约的支付产品
		String dealTypeString = new ContractAuditAction().getMchtDealTypeString(mchtId);
		log.info("获取该商户签约的支付产品中的交易类型完毕");
		try {
		    if(("1".equals(qrcStat))&&("1".equals(mchtUseStat))){
		        //将已有的二维码作废
		        stopMchtQrc(mchtId,qrcType);
		        log.info("已经将商户号为:"+mchtId+",二维码类型："+qrcType+"--原有的二维码信息作废");		        
		    }
            QrcMngService.getInstance().modifyMchtQrcStateByMchtId(mchtId,qrcType);

 //           QrcMngService.getInstance().modifyMchtId(mchtId);
			//生成新的二维码信息
			new ContractAuditAction().modifyMchtQrc(mchtId, "000", dealTypeString);          
			log.info("给商户号为:"+mchtId+",二维码类型："+qrcType+"--生成新的二维码完成");
		} catch (Exception e) {
			log.error("重新申请商户二维码失败,原因:"+e.getMessage());
			SnowExceptionUtil.throwWarnException("重新申请商户二维码失败,原因:"+e);
		}		
		//记日志
		 sessionUserInfo.addBizLog("update.log",
		 new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
		 "[商户二维码]--重新申请:商户号=[" + mchtId + "]" });
	}
	/**
	 * 给商户新增二维码信息
	 * @param baseInfo
	 * @param qrcBackGroundImageName   二维码背景图片名
	 * @throws Exception
	 */
	public  void addMchtQrc(QrcBaseInfo baseInfo, String qrcBackGroundImageName) throws Exception{
		try {
			//---------------验证数据里的数据是否正确
			//根据商户号查询商户二维码基本信息表
			List<Object>  baseInfoList=QrcMngService.getInstance().selectMchtQrcBaseInfo(baseInfo.getMchtId(),baseInfo.getQrcType());
			if(baseInfoList.size() > 0){//商户已经存在有效二维码信息
//				SnowExceptionUtil.throwWarnException("该商户存在有效的二维码信息，无法新增新的二维码信息");
//				return ;
				
				//将已有的二维码作废
//				stopMchtQrc(baseInfo.getMchtId(),baseInfo.getQrcType());
				log.info("已经将商户号为:"+baseInfo.getMchtId()+"下原有的类型编号为："+baseInfo.getQrcType()+"二维码信息作废");
			}
			String qrCode = "";
			//获取生成的二维码信息
			Map map = getQrcInfo(baseInfo);
			List list = (List) map.get("txnTokenCodeList");
			 for (int i = 0; i < list.size(); i++) {
				 Map listMap = (Map) list.get(i);
				 qrCode = (String) listMap.get("txnTokenCode");
			 }
		          
			
			log.info("获取生成的二维码信息成功");
			SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
			//---------生成二维码编号-----------------
			//String codeId = QrcMngService.getInstance().getCodeId();
			String codeId = IfspId.getObjectId();
			//补充二维码基本信息表内容
			baseInfo.setQrcCodeId(codeId);//二维码编号
			baseInfo.setQrcStat("1");//二维码状态
			baseInfo.setCrtDate(DateUtil.get14Date());//创建日期时间
			baseInfo.setTlrno(sessionUserInfo.getTlrno());//创建操作员
			baseInfo.setMchtUseStat("1");//使用状态
			baseInfo.setLastUpdDateTime(baseInfo.getCrtDate());//最后更新时间
			baseInfo.setLastUpdTlr(baseInfo.getTlrno());//最后更新操作员	
			baseInfo.setBrcode(sessionUserInfo.getBrCode());//存入机构号
			//baseInfo.setQrcType(MchtQrcCodeConstants.QRC_TYPE_ONE_CODE);//二维码类型-一码付
			log.info("二维码信息生成成功,商户号:"+baseInfo.getMchtId()+",类型："+baseInfo.getQrcType()+"商户简称:"+baseInfo.getMchtSimpleName());
			if(StringUtil.isBlank(baseInfo.getMchtSimpleName())){
				log.info("二维码信息生成================商户简称为空");
			}
			/***************生成商户二维码图片*********************/
			String tempFileName=baseInfo.getMchtId()+baseInfo.getQrcType()+"."+QRC_IMG_TYPE;
			String tempFileFullName = QRC_TMP_PATH+tempFileName;
			
			log.info("生成临时图片存放位置和路径："+tempFileFullName);
			new QrCodeUtil().genMchtOneCodeImage(qrCode, tempFileFullName, QRC_IMG_TYPE,
					baseInfo.getMchtId(), baseInfo.getMchtSimpleName(),qrcBackGroundImageName);
			log.info("===========================商户二维码图片生成成功");
			/**将生成的二维码图片信息存到图片服务器**/
			String qrcPicId = uploadImageToIfspFiles(tempFileFullName,tempFileName);
			log.info("===========================商户二维码图片上传文件服务器成功成功");
			baseInfo.setQrcPicId(qrcPicId);
			//数据持久化
			QrcMngService.getInstance().updBaseInfo(baseInfo, map);
			//删除二维码图片临时文件
			if(QrCodeUtil.deleteDir(new File(tempFileFullName))){
				log.info("删除二维码图片临时文件成功,删除文件："+tempFileFullName);
			}
			
		} catch (SnowException e) {
			log.error("错误,原因:"+e.getMessage());
			SnowExceptionUtil.throwWarnException("错误原因:"+e);
			SnowExceptionUtil.throwErrorException(e.getMessage());
		}
	}
	
	/**
	 * 请求二维码系统接口获取必要数据(新增二维码信息)
	 * @param baseInfo
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getQrcInfo(QrcBaseInfo baseInfo) throws Exception{
		Map<String,String> serRetMap = new HashMap<String, String>();
		Long startTime = System.currentTimeMillis();
		HttpURLConnection urlConnection = null;
		try {
			//-------------------------配置传送基本信息-------------------------
			URL url = new URL(SysParamUtil.getParam(MchtChnlRequestConstants.QRC_SEVR_URL));//二维码接口地址
			String requestMehtod = SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_METHOD);//请求方式
			urlConnection = HttpTransUtil.getInstance().buildConnect(url, requestMehtod);//建立服务器连接
			//-----------------拼接报文参数-------------------------------------
			Map<String,Object> params = new HashMap<>();
			params.put("txnType", "503001");//503001代表新增二维码
            params.put("appId",MchtChnlRequestConstants.REQUEST_CHNL_NO);//应用编号，401代表内管
            params.put("qrcTypeNo", "QR03");   //二维码类型为: 商户收款二维码(根据内管二维码类型管理页面配置的编号)
            params.put("txnSsn", IfspId.getUUID32());//交易流水号
            params.put("txnSsnTm", IfspDateTime.getYYYYMMDDHHMMSS());//交易流水时间
            Map<String, Object> qrcTxnInfoMap = new HashMap<>();//存放二维码原始信息
            qrcTxnInfoMap.put("merId", baseInfo.getMchtId());
            qrcTxnInfoMap.put("merName", baseInfo.getMchtSimpleName());
            Gson gson = new Gson();
            String qrcTxnJson = gson.toJson(qrcTxnInfoMap);//将Map转换成json格式字符串
            params.put("qrcTxnInfo", qrcTxnJson);//二维码原始信息
            //---------------------发送报文--------------------
            HttpTransUtil.getInstance().sendMsg(urlConnection, gson.toJson(params));
            //--------------------获取响应----------------------
            String retMsg = HttpTransUtil.getInstance().recvResponse(urlConnection);
            
            serRetMap = gson.fromJson(retMsg, serRetMap.getClass());
            String respCode = serRetMap.get("respCode");//响应码
          //截取响应码后四位，判断请求是否成功，如果失败，返回失败原因
            if(!respCode.substring((respCode.length()-4), respCode.length()).equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS)){
                SnowExceptionUtil.throwWarnException("错误原因:"+serRetMap.get("respMsg"));
            }  
		} catch (SnowException se) {
			 log.error("二维码申请接口请求失败：商户号【"+baseInfo.getMchtId()+"】，原因："+se.getMessage());
	         log.info("耗时：" + (System.currentTimeMillis() - startTime) + " ms ");
			SnowExceptionUtil.throwWarnException("二维码申请接口请求失败：",se);
			SnowExceptionUtil.throwErrorException(se.getMessage());
		}finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
		return serRetMap;
	}
	/**
	 * (二维码接口)商户二维码停用   根据商户号调用二维码接口停用功能停用该商户的二维码信息
	 * @param mchtId
	 * @param qrcType
	 * @return
	 * @throws Exception
	 */
	public void stopMchtQrc(String mchtId,String qrcType) throws Exception{
		if(mchtId == null){
			log.error("需要停用的商户号为空");
		}
		Map<String,String> serRetMap = new HashMap<String, String>();
		Long startTime = System.currentTimeMillis();
		HttpURLConnection urlConnection = null;
		QrcBaseInfo baseInfo = null;
		try {
			//-------------------------配置传送基本信息-------------------------
			URL url = new URL(SysParamUtil.getParam(MchtChnlRequestConstants.QRC_STOP_URL));//二维码接口地址
			String requestMehtod = SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_METHOD);//请求方式
			urlConnection = HttpTransUtil.getInstance().buildConnect(url, requestMehtod);//建立服务器连接
			//-----------------拼接报文参数-------------------------------------
			
			//根据商户号查询商户二维码基本信息表
			List<Object>  baseInfoList=QrcMngService.getInstance().selectMchtQrcBaseInfo(mchtId,qrcType);
			if(baseInfoList.size() == 0){//商户不存在有效的二维码信息
				log.info("商户不存在有效的二维码信息,无法停用二维码信息");
				return ;
			}else if(baseInfoList.size() > 1){//查出来的商户的二维码有效信息如果不止一条
				SnowExceptionUtil.throwWarnException("数据错误,商户："+mchtId+"下类型为："+qrcType+"的记录不只一条，不允许存在这种情况！");
				SnowExceptionUtil.throwWarnException("数据错误,一个商户不允许同时存在两条以上有效的二维码信息");
				return ;
			}
			for (Object object : baseInfoList) {
				baseInfo = (QrcBaseInfo)object;
			}
			Map<String,Object> params = new HashMap<>();
			params.put("txnType", "503004");//503004代表停用二维码
			params.put("txnSsn", IfspId.getUUID32());//交易流水号
			params.put("txnSsnTm", IfspDateTime.getYYYYMMDDHHMMSS());//交易流水时间
			params.put("respTxnSsn", baseInfo.getRespTxnSsn());//二维码系统流水号
			params.put("respTxnTime", baseInfo.getRespTxnTime());//二维码系统受理时间
			
			Gson gson = new Gson();
			
			//---------------------发送报文--------------------
            HttpTransUtil.getInstance().sendMsg(urlConnection, gson.toJson(params));
            //--------------------获取响应----------------------
            String retMsg = HttpTransUtil.getInstance().recvResponse(urlConnection);
            serRetMap = gson.fromJson(retMsg, serRetMap.getClass());
            String respCode = serRetMap.get("respCode");//响应码
            //截取响应码后四位，判断请求是否成功，如果失败，返回失败原因
            if(!respCode.substring((respCode.length()-4), respCode.length()).equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS)){
                SnowExceptionUtil.throwWarnException("错误原因:"+serRetMap.get("respMsg"));
                log.error("二维码停用接口请求失败,失败原因："+serRetMap.get("respMsg"));
            }  
            //数据持久化，根据商户号将商户二维码基本信息表中的二维码状态改为无效
		}catch (SnowException se) {
			 log.error("二维码停用接口请求失败：商户号【"+baseInfo.getMchtId()+"】，原因："+se.getMessage());
	         log.info("耗时：" + (System.currentTimeMillis() - startTime) + " ms ");
			 SnowExceptionUtil.throwErrorException("二维码停用接口请求失败：",se);
			SnowExceptionUtil.throwWarnException("错误原因:"+se);
	        
		}finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
	}
	/**
	 * 上传商户二维码图片到文件服务器
	 * @param qrcImgFilePath
	 * @throws Exception
	 */
	public String uploadImageToIfspFiles(String fileFullName,String fileName) throws Exception{
		log.info("进入上传文件服务器方法");
		HttpURLConnection urlConnection = null;
		DataOutputStream dos = null;// 输出流
		InputStream is = null;// 输入流
		InputStream inputStream = null;
		String result = "";// 返回结果
		String downloadUrlCodeStr = "";
		DataInputStream in = null;
		InputStreamReader isr =null;
		BufferedReader br =null;
		try {
			URL url = new URL(SysParamUtil.getParam(MchtChnlRequestConstants.UPLOAD_FILE));//图片上传服务器接口地址
			log.info("获取文件上传服务器的地址url:"+url);
			urlConnection = (HttpURLConnection) url.openConnection();
			log.info("打开连链接");
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Connection", "Keep-Alive");//保持长连接
			urlConnection.setRequestProperty("Charset", "UTF-8");
			log.info("连接文件服务器成功");
			//设定传送内容类型(form表单)
			urlConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			dos = new DataOutputStream(urlConnection.getOutputStream());
			//因为公司文件服务器接口只提供了post提交form表单的方式，额Java代码里没办法制定form提交，
			//所以就像从页面form提交时候真正的请求内容格式入手，拿流去按格式write出来。以下格式不能错误，错一个回车或者-都不行
			/** 渠道信息 ,接口中401代表内管*/
			dos.writeBytes(twoHyphens + boundary + end);
			String messageWrite = "Content-Disposition: form-data; name=\"" + RequestChnlKey + "\"" + end + end
					+ RequestChnlNo + end + twoHyphens + boundary + end;
			dos.writeBytes(messageWrite);
			/** 文件类型,接口中03代表图片类型 */
			messageWrite = "Content-Disposition: form-data; name=\"" + ReauestFileTypeKey + "\"" + end + end
					+ ReauestFileType + end + twoHyphens + boundary + end;
			dos.writeBytes(messageWrite);
			/** 图片信息 */
			String fileNameMessage = "Content-Disposition: form-data; name=\"" + requestFileKey + "\"; filename=\""
					+ fileName + "\"" + end;
			
			dos.write(fileNameMessage.getBytes("UTF-8"));
			dos.writeBytes("Content-Type:image/jpeg" + end + end);
			
			File file = new File(fileFullName);
			in = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while((bytes = in.read(bufferOut)) != -1){
				dos.write(bufferOut,0,bytes);
			}
			dos.writeBytes(end + twoHyphens + boundary + twoHyphens + end);
			dos.flush();
			
			// 读取服务器返回结果
			is = urlConnection.getInputStream();
			isr= new InputStreamReader(is, "utf-8");
			br= new BufferedReader(isr);
			result = br.readLine();
			is.close();
			// 获取文件码
			Gson gson = new Gson();
			Map<String, Object> resultMap = gson.fromJson(result, HashMap.class);
			String respCode = (String) resultMap.get("respCode");// 服务器响应码
			if (!respCode.equals("0000")) {
				SnowExceptionUtil.throwErrorException("文件服务器响应错误，请重新尝试！");
			}
			log.info("获取文件服务器响应码成功,响应码:"+respCode);
			String fileDataList = resultMap.get("fileDataList").toString().replace("[", "").replace("]", "")
					.replace("{", "{\"").replace("}", "\"}").replace("=", "\":\"").replace(", ", "\"" + ", " + "\"")
					.replace("\\", "/");
			log.info("fileData:" + fileDataList);
			Map<String, Object> fileDataListMap = gson.fromJson(fileDataList, HashMap.class);// 文件信息列表
			//下载图片的唯一标示
			downloadUrlCodeStr = (String) fileDataListMap.get("downloadUrlCode");
			log.info("获取下载图片的唯一标示成功,唯一标示为:"+downloadUrlCodeStr);
		} catch (SnowException se) {
			log.error("商户二维码图片上传文件服务器失败：原因："+se.getMessage()); 
			SnowExceptionUtil.throwErrorException("商户二维码图片上传文件服务器失败：",se);
			SnowExceptionUtil.throwWarnException("错误原因:"+se);
		}finally {
			if (inputStream != null) {
				inputStream.close();
			}
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
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
			if(in != null){
				in.close();
			}
			
		}
		return downloadUrlCodeStr;
	}
	 
    /** 
     * 根据地址获得数据的字节流 
     * @param strUrl 网络连接地址 
     * @return 
     * @throws SnowException 
     */  
    public static byte[] getImageFromNetByUrl(String strUrl) throws SnowException{  
        InputStream inStream =null;
        HttpURLConnection conn = null;
        try {  
            URL url = new URL(strUrl);  
             conn = (HttpURLConnection)url.openConnection();  
            conn.setRequestMethod("GET");  
            conn.setConnectTimeout(5 * 1000);  
             inStream = conn.getInputStream();//通过输入流获取图片数据  
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据  
            return btImg;  
        } catch (Exception e) {  
        	log.error("获取商户二维码图片失败:"+e.getMessage());
        	SnowExceptionUtil.throwErrorException("获取商户二维码图片失败,原因:"+e); 
			SnowExceptionUtil.throwWarnException("错误原因:"+e);
		/** modify by fengwei 20180116 代码完善,正确的关闭资源  没有jira */
        }  finally {
            if(inStream!=null){
                try {
                    inStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(conn!=null){
                conn.disconnect();
            }
        }
        /** modify end */
        return null;  
    }  
    /** 
     * 从输入流中获取数据 
     * @param inStream 输入流 
     * @return 
     * @throws Exception 
     */  
    public static byte[] readInputStream(InputStream inStream) throws Exception{  
        /** modify by fengwei 20180116 代码完善,正确的关闭资源  没有jira */
        ByteArrayOutputStream outStream=null;
        try{
            outStream= new ByteArrayOutputStream();  
            byte[] buffer = new byte[1024];  
            int len = 0;  
            while( (len=inStream.read(buffer)) != -1 ){  
                outStream.write(buffer, 0, len);  
            }  
            outStream.flush();
        }catch(Exception e){
            log.error("从输入流中获取数据 :"+e.getMessage());
            SnowExceptionUtil.throwWarnException("从输入流中获取数据:"+e);
        }finally {
            if(inStream!=null){
                inStream.close();
            }
            if(outStream!=null){
                outStream.close();
            }
        }
        /** modify end */
        return outStream.toByteArray();  
    }  
    

    @Action
	@SnowDoc(desc = "下载商户二维码图片")
	public void downLoadTest(FormRequestBean formRqeust) throws SnowException {
		try {
			
			//获得浏览器代理信息
			final String userAgent = formRqeust.getRequest().getHeader("USER-AGENT");
	    	HttpServletResponse response = formRqeust.getResponse();
			String qrcPicId = formRqeust.getParameter("picId");
			//String mchtSimpleName = new String(formRqeust.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
			String mchtId = formRqeust.getParameter("mchtId");
		
			if("".equals(qrcPicId)){
				SnowExceptionUtil.throwWarnException("要下载的商户二维码图片信息不存在!");
			}
			
			//按道理商户简称应该从后台传过来，而不应在去数据库中查，当时途简单，没去考虑转码(转码可参考BthBalRsltAction类中用的URLDecoder.decode转码方式)
			//根据商户号查询商户简称
			String mchtSimpleName = QrcMngService.getInstance(). getMchtSimpleNameByMchtId(mchtId);
			//判断浏览器代理并分别设置响应给浏览器的编码格式
			String finalFileName = null;
            if(StringUtils.contains(userAgent, "MSIE")||StringUtils.contains(userAgent,"Trident")){//IE浏览器
                finalFileName = URLEncoder.encode(mchtSimpleName,"UTF8");
            }else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
                finalFileName = new String(mchtSimpleName.getBytes(), "ISO8859-1");
            }else{
                finalFileName = URLEncoder.encode(mchtSimpleName,"UTF8");//其他浏览器
            }
			
			//获取下载二维码图片的地址
			String url = SysParamUtil.getParam(ImportPicConstants.SHOW_IMAGE_PMP)+qrcPicId;
			byte[] btImg = QrcMngAction.getImageFromNetByUrl(url);//将二维码图片从url变成byte数组
			response.reset();//重置响应头
			//文件下载保存在电脑中的名字
			response.addHeader("Content-Disposition", "attachment;filename="+finalFileName+".png");
			OutputStream ous = new BufferedOutputStream(response.getOutputStream());
			//告知浏览器下载文件而不是直接打开，因为浏览器默认认为打开
			response.setContentType("application/octet-stream");
			ous.write(btImg);
			ous.flush();
			ous.close();
		} catch (IOException e) {
			log.error("错误,原因:"+e.getMessage());
			SnowExceptionUtil.throwWarnException("错误,原因"+e.getMessage());
			SnowExceptionUtil.throwErrorException(e.getMessage());
		}
    }
}
