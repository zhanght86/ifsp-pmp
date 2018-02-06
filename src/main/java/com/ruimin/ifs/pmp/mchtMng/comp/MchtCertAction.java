package com.ruimin.ifs.pmp.mchtMng.comp;

import com.google.gson.Gson;
import com.ruim.ifsp.signature.IfspSdkSignAtureUtil;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.chnlMng.comp.MchtChnlRequestAction;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtCertInfoVO;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaseInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaseInfoReal;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtCertService;
import com.ruimin.ifs.pmp.pubTool.util.Base64Coder;
import com.ruimin.ifs.pmp.pubTool.util.HttpClientUtils;
import com.ruimin.ifs.pmp.pubTool.util.HttpTransUtil;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;
import com.ruimin.ifs.pmp.report.process.bean.BthBalErrorsVO;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

@SuppressWarnings("unused")
@SnowDoc(desc = "商户证书配置")
@ActionResource
public class MchtCertAction extends SnowAction {

	private String encCertiFi;

	/**************************************** QUERY **************************************/

	/**
	 * 商户证书数据查询
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {

		String mchtId = queryBean.getParameter("qMchtId"); // 商户编号
		String mchtSimpleName = queryBean.getParameter("qmchtSimpleName"); // 商户简称
		String certifiStatus = queryBean.getParameter("qcertifiStatus"); // 证书状态
		return MchtCertService.getInstance().queryList(mchtId, mchtSimpleName, certifiStatus, queryBean.getPage());
	}

	/**
	 * 通过商户号查询商户简称(临时表)
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String queryMchtSimpNameById(FieldBean bean, String key, ServletRequest request)
			throws SnowException {

		if (StringUtils.isNotBlank(key)) {
			PbsMchtBaseInfo pbsMchtBaseInfo = MchtCertService.getInstance().queryMchtSimpNameById(key);
			if (pbsMchtBaseInfo != null) {
				return pbsMchtBaseInfo.getMchtSimpleName();
			}
		}
		return key;
	}

	/**
	 * 通过商户号查询商户简称(正式表)
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String queryMchtSimpNameRealById(FieldBean bean, String key, ServletRequest request)
			throws SnowException {

		if (StringUtils.isNotBlank(key)) {
			PbsMchtBaseInfoReal pbsMchtBaseInfoReal = MchtCertService.getInstance().queryMchtSimpNameRealById(key);
			if (pbsMchtBaseInfoReal != null) {
				return pbsMchtBaseInfoReal.getMchtSimpleName();
			}
		}
		return key;
	}

	/**
	 * 通过证书编号获取证书名称
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String queryMchtCertNameById(FieldBean bean, String key, ServletRequest request)
			throws SnowException {

		if (StringUtils.isNotBlank(key)) {
			MchtCertInfoVO mchtCertInfoVO = MchtCertService.getInstance().queryMchtCertNameById(key, "01");
			if (mchtCertInfoVO != null) {
				String path = mchtCertInfoVO.getCertifiPath();
				if (path != null) {
					String certName = path.substring(path.lastIndexOf('/') + 1);
					return certName;
				}
			}
		}
		return key;
	}

	/***************************************** ADD *********************************************/

	/**
	 * 商户证书：新增配置信息
	 * 
	 * @param updateMap
	 * @throws Exception
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增")
	public void addMchtCertInfo(Map<String, UpdateRequestBean> updateMap) throws Exception {
		UpdateRequestBean reqBean = updateMap.get("MchtCert");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";

		MchtCertInfoVO mchtCertInfoVO = new MchtCertInfoVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtCertInfoVO);
		}

		// 校验是否重复
		if (MchtCertService.getInstance().queryMcht(mchtCertInfoVO.getCertifiBl()) > 0) {
			SnowExceptionUtil.throwWarnException(mchtCertInfoVO.getCertifiBl() + ":同一商户只能配置一条证书信息");
		}
		String certifid = mchtCertInfoVO.getCertifiId();
		if (MchtCertService.getInstance().queryCert(certifid) > 0) {
			SnowExceptionUtil.throwWarnException(mchtCertInfoVO.getCertifiId() + ":证书编号已存在");
		}

		SnowLog.getLogger(this.getClass()).info("------------ 新增配置 ------------");

		// 默认参数配置
		mchtCertInfoVO.setCertifiType("01"); // 证书类型
		mchtCertInfoVO.setCertifiUseType("3000");// 使用类型
		mchtCertInfoVO.setEncryptType("01"); // 加密方式
		mchtCertInfoVO.setEncryptWayType("01"); // 加密类型

		// 新增
		MchtCertService.getInstance().addMchtCertInfo(mchtCertInfoVO);

		// 日志
		msg = "[商户证书配置 ]--商户证书新增 :证书编号[CertifiId]=" + mchtCertInfoVO.getCertifiId();
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}

	/**
	 * 接入参数：新增配置信息
	 * 
	 * @param updateMap
	 * @throws SnowException
	 * @throws IOException
	 */

	@SnowDoc(desc = "新增")
	public void addMchtAccInfo(Map<String, UpdateRequestBean> updateMap) throws SnowException, IOException {
		UpdateRequestBean reqBean = updateMap.get("MchtAccessPara");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";
		MchtCertInfoVO mchtCertInfoVO = new MchtCertInfoVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtCertInfoVO);
		}

		// 校验是否重复
        if (MchtCertService.getInstance().queryMcht(mchtCertInfoVO.getCertifiBl()) > 0) {
            SnowExceptionUtil.throwWarnException("WEB_E0623",new String[] { mchtCertInfoVO.getCertifiBl()});
        }

		SnowLog.getLogger(this.getClass()).info("------------ 新增配置 ------------");

		// 给md5进行Base64加密入库
		String encCertiFi = Base64Coder.encoded(mchtCertInfoVO.getCertifiPasswd());
		mchtCertInfoVO.setCertifiPasswd(encCertiFi);

		// 生成证书编号
		String CertifiId = mchtCertInfoVO.getCertifiBl();
		mchtCertInfoVO.setCertifiId(CertifiId);

		// 默认参数配置
		mchtCertInfoVO.setCertifiType("03"); // 证书类型
		mchtCertInfoVO.setCertifiUseType("3000");// 使用类型
		mchtCertInfoVO.setEncryptType("02"); // 加密方式
		mchtCertInfoVO.setEncryptWayType("01"); // 加密类型

		// 新增
		MchtCertService.getInstance().addMchtCertInfo(mchtCertInfoVO);
		//刷新缓存
		try {
            QueryRequest(mchtCertInfoVO);
        } catch (Exception e) {
            SnowExceptionUtil.throwErrorException(e.getMessage());
        }
		// 日志
		msg = "[商户接入参数 ]--商户接入参数新增 :证书编号[CertifiId]=" + mchtCertInfoVO.getCertifiId();
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}
	 @Action
	    public void QueryRequest(MchtCertInfoVO mchtCertInfoVO) throws Exception {
	        
	        /** 加载SnowLog */
	        Logger log = SnowLog.getLogger(this.getClass());

	        /******************************************** 计时开始 ********************************************/
	        Long startTime = System.currentTimeMillis();
	        String requestMsg=null;
            String url =SysParamUtil.getParam(MchtChnlRequestConstants.MchtCERTINFOVO);
	        try {
	            log.info("刷新缓存【" + url + "】-请求开始...");	                                      
	
	            /** 发送报文 */
	            Map<String, String> map = new HashMap<String, String>();
	            map.put("merId", mchtCertInfoVO.getCertifiId());
	            String resMsg  = HttpClientUtils.send(map,"210.updateMertCertCache", url,false);
	            
	            Map<String, String> serRetMap = new HashMap<String, String>();
	            Gson gson = new Gson();
	            serRetMap = gson.fromJson(resMsg, serRetMap.getClass());
	            String respCode = serRetMap.get("respCode");// 响应码 
	            if (respCode.substring((respCode.length() - 4), respCode.length())
	                    .equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS)) {
	                log.info("刷新缓存调用成功【" + url + "】-请求开始...");
	            }else{
	                log.info("刷新缓存调用失败【" + url + "】-请求开始...");
	                SnowExceptionUtil.throwErrorException(serRetMap.get("respMsg"));
	            }	          
	            /******************************************** 计时结束 ********************************************/
	            log.info("刷新缓存请求【" + url + "】-请求完成，耗时：" + (System.currentTimeMillis() - startTime) + " ms ");

	        } catch (SnowException se) {
	            SnowExceptionUtil.throwErrorException(se.getMessage());
                log.info("刷新缓存调用失败【" + url + "】-请求开始...");
	            log.info("耗时：" + (System.currentTimeMillis() - startTime) + " ms ");
	        }
	    }
	/*************************************** UPDATE *****************************************/

	/**
	 * 商户证书信息修改
	 * 
	 * @param updateMap
	 * @throws SnowException
	 * @throws ParseException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "信息修改")
	public void updMchtCertInfo(Map<String, UpdateRequestBean> updateMap) throws SnowException, ParseException {

		UpdateRequestBean reqBean = updateMap.get("MchtCert");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";
		// 将数据集的值赋给实体类
		MchtCertInfoVO mchtCertInfoVO = new MchtCertInfoVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtCertInfoVO);
		}

		MchtCertService.getInstance().update(mchtCertInfoVO);

		// 日志
		msg = "[商户证书配置 ]--商户证书新增 :证书编号[CertifiId]=" + mchtCertInfoVO.getCertifiId();
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}

	/**
	 * 商户接入参数信息修改
	 * 
	 * @param updateMap
	 * @throws SnowException
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */

	@SnowDoc(desc = "商户接入参数信息修改")
	public void updMchtAccInfo(Map<String, UpdateRequestBean> updateMap) throws SnowException, IOException {

		UpdateRequestBean reqBean = updateMap.get("MchtAccessPara");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";
		// 将数据集的值赋给实体类
		MchtCertInfoVO mchtCertInfoVO = new MchtCertInfoVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtCertInfoVO);
		}

		// 给md5进行Base64加密入库
//		String encCertiFi = Base64Coder.encoded(mchtCertInfoVO.getCertifiPasswd());
//		mchtCertInfoVO.setCertifiPasswd(encCertiFi);

		MchtCertService.getInstance().update(mchtCertInfoVO);

		try {
            QueryRequest(mchtCertInfoVO);
        } catch (Exception e) {
            SnowExceptionUtil.throwErrorException(e.getMessage());
        }
		// 日志
		msg = "[商户证书配置 ]--商户证书新增 :证书编号[CertifiId]=" + mchtCertInfoVO.getCertifiId();
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}

	/**
	 * 修改状态
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "状态启用停用")
	public void setMchtCertStatus(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";
		MchtCertInfoVO mchtCertInfoVO = new MchtCertInfoVO();
		// 取最新证书状态
		UpdateRequestBean reqBean = updateMap.get("MchtCert");
		String certifiStatus = reqBean.getParameter("certifiStatus");

		// 获取新增字段并写入实体类中
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtCertInfoVO);
		}

		mchtCertInfoVO.setCertifiStatus(certifiStatus);
		// 跟新实体类
		MchtCertService.getInstance().update(mchtCertInfoVO);
		if ("99".equals(certifiStatus)) {
			msg = "[商户证书配置 ]--更改商户证书状态为启用 :证书编号[CertifiId]=" + mchtCertInfoVO.getCertifiId();
		} else {
			msg = "[商户证书配置 ]--更改商户证书状态为停用 :证书编号[CertifiId]=" + mchtCertInfoVO.getCertifiId();
		}
	     
		// 日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });

	}

	/**
	 * 修改接入参数的状态
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */	
	@SnowDoc(desc = "状态启用停用")
	public void setMchtAccStatus(Map<String, UpdateRequestBean> updateMap) throws SnowException, IOException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";
		MchtCertInfoVO mchtCertInfoVO = new MchtCertInfoVO();
		// 取最新证书状态
		UpdateRequestBean reqBean = updateMap.get("MchtAccessPara");
		String certifiStatus = reqBean.getParameter("certifiStatus");

		// 获取新增字段并写入实体类中
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtCertInfoVO);
		}

		mchtCertInfoVO.setCertifiStatus(certifiStatus);

		// 给md5进行Base64加密入库
//		String encCertiFi = Base64Coder.encoded(mchtCertInfoVO.getCertifiPasswd());
//		mchtCertInfoVO.setCertifiPasswd(encCertiFi);

		// 跟新实体类
		
		MchtCertService.getInstance().update(mchtCertInfoVO);

		if ("99".equals(certifiStatus)) {
			msg = "[商户接入参数配置 ]--更改商户接入参数状态为启用 :证书编号[CertifiId]=" + mchtCertInfoVO.getCertifiId();
		} else {
			msg = "[商户接入参数配置 ]--更改商户接入参数状态为停用 :证书编号[CertifiId]=" + mchtCertInfoVO.getCertifiId();
		}
		 //刷新缓存
        try {
            QueryRequest(mchtCertInfoVO);
        } catch (Exception e) {
            SnowExceptionUtil.throwErrorException(e.getMessage());
        }
		// 日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}
}
