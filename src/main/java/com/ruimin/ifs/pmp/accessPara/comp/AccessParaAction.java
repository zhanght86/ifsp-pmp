package com.ruimin.ifs.pmp.accessPara.comp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import com.google.gson.Gson;
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
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.accessPara.process.service.AccessParaService;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtCertInfoVO;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtCertService;
import com.ruimin.ifs.pmp.pubTool.util.Base64Coder;
import com.ruimin.ifs.pmp.pubTool.util.HttpClientUtils;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;

@SnowDoc(desc = "证书配置")
@ActionResource
public class AccessParaAction extends SnowAction {

	/**
	 * 证书数据查询
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {

		String certifiId = queryBean.getParameter("qCertifiId"); // 商户编号
		String certifiStatus = queryBean.getParameter("qcertifiStatus"); // 证书状态
		return AccessParaService.getInstance().queryList(certifiId, certifiStatus, queryBean.getPage());
	}

	/**
	 * 修改状态
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "状态启用停用")
	public void setAccStatus(Map<String, UpdateRequestBean> updateMap) throws SnowException {
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
		// 跟新实体类
		AccessParaService.getInstance().update(mchtCertInfoVO);
		if ("99".equals(certifiStatus)) {
			msg = "[证书配置 ]--更改证书状态为启用 :证书编号[CertifiId]=" + mchtCertInfoVO.getCertifiId();
		} else {
			msg = "[证书配置 ]--更改证书状态为停用 :证书编号[CertifiId]=" + mchtCertInfoVO.getCertifiId();
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

	/**
	 * 接入参数信息修改
	 * 
	 * @param updateMap
	 * @throws SnowException
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */

	@SnowDoc(desc = "接入参数信息修改")
	public void updAccInfo(Map<String, UpdateRequestBean> updateMap) throws SnowException, IOException {

		UpdateRequestBean reqBean = updateMap.get("MchtAccessPara");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";
		// 将数据集的值赋给实体类
		MchtCertInfoVO mchtCertInfoVO = new MchtCertInfoVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtCertInfoVO);
		}

		// 给md5进行Base64加密入库
		// String encCertiFi =
		// Base64Coder.encoded(mchtCertInfoVO.getCertifiPasswd());
		// mchtCertInfoVO.setCertifiPasswd(encCertiFi);

		AccessParaService.getInstance().update(mchtCertInfoVO);

		try {
			QueryRequest(mchtCertInfoVO);
		} catch (Exception e) {
			SnowExceptionUtil.throwErrorException(e.getMessage());
		}
		// 日志
		msg = "[证书配置 ]--证书修改 :证书编号[CertifiId]=" + mchtCertInfoVO.getCertifiId();
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}

	
	
	/**
	 * 新增配置信息
	 * 
	 * @param updateMap
	 * @throws Exception
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增")
	public void addAccInfo(Map<String, UpdateRequestBean> updateMap) throws Exception {
		UpdateRequestBean reqBean = updateMap.get("MchtAccessPara");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";
		MchtCertInfoVO mchtCertInfoVO = new MchtCertInfoVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtCertInfoVO);
		}

		// 校验是否重复
    	String certifid = mchtCertInfoVO.getCertifiBl();
		if (MchtCertService.getInstance().queryCert(certifid) > 0) {
			SnowExceptionUtil.throwWarnException(mchtCertInfoVO.getCertifiId() + ":证书编号已存在");
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
		mchtCertInfoVO.setCertifiUseType("3008");// 使用类型
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
		msg = "[接入参数 ]--接入参数新增 :证书编号[CertifiId]=" + mchtCertInfoVO.getCertifiId();
		sessionUserInfo.addBizLog("update.log",
			new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });		
	}
	
	
	@Action
	public void QueryRequest(MchtCertInfoVO mchtCertInfoVO) throws Exception {

		/** 加载SnowLog */
		Logger log = SnowLog.getLogger(this.getClass());

		/******************************************** 计时开始 ********************************************/
		Long startTime = System.currentTimeMillis();
		String requestMsg = null;
		String url = SysParamUtil.getParam(MchtChnlRequestConstants.MchtCERTINFOVO);
		try {
			log.info("刷新缓存【" + url + "】-请求开始...");

			/** 发送报文 */
			Map<String, String> map = new HashMap<String, String>();
			map.put("merId", mchtCertInfoVO.getCertifiId());
			String resMsg = HttpClientUtils.send(map, "210.updateMertCertCache", url, false);

			Map<String, String> serRetMap = new HashMap<String, String>();
			Gson gson = new Gson();
			serRetMap = gson.fromJson(resMsg, serRetMap.getClass());
			String respCode = serRetMap.get("respCode");// 响应码
			if (respCode.substring((respCode.length() - 4), respCode.length())
					.equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS)) {
				log.info("刷新缓存调用成功【" + url + "】-请求开始...");
			} else {
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

}
