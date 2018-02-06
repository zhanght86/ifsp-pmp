/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.term.comp 
 * FileName: PaypTermInfoAction.java
 * Author:   wangyl
 * Date:     2016年8月11日 下午3:34:45
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   wangyl           2016年8月11日下午3:34:45                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.term.comp;

import com.ruim.ifsp.signature.utils.IfspSdkDataVerifyUtil;
import com.ruim.ifsp.signature.utils.IfspSdkId;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.FormRequestBean;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;
import com.ruimin.ifs.term.common.constants.TermConstants;
import com.ruimin.ifs.term.process.bean.PaypTermDetailInf;
import com.ruimin.ifs.term.process.bean.PaypTermInf_temp;
import com.ruimin.ifs.term.process.bean.PaypTermKey;
import com.ruimin.ifs.term.process.service.CommonAuditService;
import com.ruimin.ifs.term.process.service.PaypTermInfoService;
import com.ruimin.ifs.util.BaseUtil;
import com.ruimin.ifs.util.KeyGenerate;
import com.ruimin.ifs.util.StringUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.util.Log;

/**
 * 功能：终端信息维护<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月11日 <br>
 * 作者：wangyl <br>
 * 说明：<br>
 */
@SnowDoc(desc = "终端信息")
@ActionResource
public class PaypTermInfoAction extends SnowAction {

	@SnowDoc(desc = "增加终端信息 获取自动生成的终端号码")
	public String getTermId(FieldBean bean, String key, ServletRequest request) throws SnowException {
		return KeyGenerate.generateTermId();
	}

	/**
	 * 根据地区代码获取地区名称method
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 */
	public static String getCityName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		return "000022222";
	}

	/**
	 * 根据商户号 查询产品信息
	 * 
	 * @param bean
	 * @return
	 */
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryMchtProdInfoByMchtId(QueryParamBean queryBean) throws SnowException {
		String mchtId = queryBean.getParameter("mchtId", "");// 商户号
		Page page = queryBean.getPage();
		page.setEveryPage(20);
		PageResult p = PaypTermInfoService.getInstance().queryMchtProdInfoByMchtId(mchtId, page);
		return p;
	}

	/**
	 * 根据商户号 查询产品信息总数
	 * 
	 * @param bean
	 * @return
	 */
	@Action
	@SnowDoc(desc = "查询")
	public String queryMchtProdInfoByMchtIdCount(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		String mchtId = "";
		return PaypTermInfoService.getInstance().queryMchtProdInfoByMchtIdCount(mchtId) + "";
	}

	/**
	 * 根据请求条件查询终端信息
	 * 
	 * @paramqueryBean QueryParamBean查询类交易的请求参数
	 * @return查询结果，返回类型可以为List/PageResult（包含分页信息）/JavaBean对象
	 * @throwsSnowException
	 */
	// Action注解表示一个组件，查询类不需要事务
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		// 获取查询参数，分页信息已经封装在QueryParamBean对象中了
		String termId = queryBean.getParameter("qtermId", "");// 终端编号
		String mchtId = queryBean.getParameter("mchtId", "");// 商户编号
		String termStat = queryBean.getParameter("qtermStat", "");// 终端状态
		String termType = queryBean.getParameter("qtermType", "");// 终端类型
		String mchtName = queryBean.getParameter("qmchtName", "");// 商户简称
		String machInst = queryBean.getParameter("qmachInst", ""); // 所属分行
		String auditId = queryBean.getParameter("auditId", ""); // 所属分行

		// 调用服务层
		return PaypTermInfoService.getInstance().queryList(termId, mchtId, termStat, termType, mchtName, machInst,
				auditId, queryBean.getPage());
	}

	/**
	 * 终端信息增加 或修改
	 * 
	 * @param updateMap
	 * @throws SnowException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Action
	@SnowDoc(desc = "新增或者修改")
	public void saveOrUpdatePaypTermInf(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		UpdateRequestBean reqBean = updateMap.get("paypTermInf");
		PaypTermInf_temp paypTermInf = new PaypTermInf_temp();
		if (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), paypTermInf);
		}
		String paramCmd = reqBean.getParameter("paramCmd");
		if ("add".equals(paramCmd)) {// 增加终端信息操作
			// 终端信息新增默认状态为00-新增待审核
			paypTermInf.setTermStat(TermConstants.TERM_STAT_00);
			paypTermInf.setCrtTlr(sessionUserInfo.getTlrno());
			paypTermInf.setCrtDateTime(BaseUtil.getCurrentDateTime());
			paypTermInf.setTermId(KeyGenerate.generateTermId());
			paypTermInf.setIcParmVer(TermConstants.TERM_STAT_01);
			paypTermInf.setKeyVer(TermConstants.TERM_STAT_01);
			// 加入审核的入口
			CommonAuditService.getInstance().addAuditEntry(paypTermInf);// 调用方法加入到审核中
			PaypTermInfoService.getInstance().savePaypTermInf(paypTermInf);
		} else if ("update".equals(paramCmd)) {
			String tlrNo = sessionUserInfo.getTlrno();
			String currentDateTime = BaseUtil.getCurrentDateTime();
			// 终端信息修改默认状态为03-修改待审核
			paypTermInf.setTermStat(TermConstants.TERM_STAT_03);
			paypTermInf.setLastUpdTlr(tlrNo);
			paypTermInf.setLastUpdDateTime(currentDateTime);
			// 加入审核的入口
			CommonAuditService.getInstance().addAuditEntry(paypTermInf);// 调用方法加入到审核中
			PaypTermInfoService.getInstance().updatePaypTermInf(paypTermInf);
		}

	}

	/**
	 * 终端信息停用 或启用
	 * 
	 * @param updateMap
	 * @throws SnowException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Action
	@SnowDoc(desc = "启用或停用终端")
	public void updatePaypTermInfTermStat(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("paypTermInf");
		PaypTermInf_temp paypTermInf = new PaypTermInf_temp();
		if (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), paypTermInf);
		}
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String tlrNo = sessionUserInfo.getTlrno(); // 操作员
		String currentDateTime = BaseUtil.getCurrentDateTime(); // 最后操作时间
		String termStat = reqBean.getParameter("termStat");// 01 启用 99 停用
		String termId = reqBean.getParameter("termId");
		if (TermConstants.TERM_STAT_01.equals(termStat)) {
			paypTermInf.setTermStat(TermConstants.TERM_STAT_05);
		} else if (TermConstants.TERM_STAT_99.equals(termStat)) {
			paypTermInf.setTermStat(TermConstants.TERM_STAT_07);
		}
		// 加入审核的入口
		CommonAuditService.getInstance().addAuditEntry(paypTermInf);// 调用方法加入到审核中
		PaypTermInfoService.getInstance().updatePaypTermInfTermStat(paypTermInf, termId, tlrNo, currentDateTime);
	}

	public void downLoadFile(FormRequestBean formRequestBean) throws SnowException {
		String termId = formRequestBean.getParameter("termId");
		String mchtId = formRequestBean.getParameter("mchtId");
		// 得到秘钥实体
		PaypTermKey paypTermKey = PaypTermInfoService.getInstance().queryKey(termId, mchtId);
		String keyValue = StringUtil.rightPad(paypTermKey.getTermZmk(), 48);
		String text = "\"" + mchtId + "\",\"" + termId + "\",\"04354910     \",\"" + keyValue + "\",\"" + keyValue
				+ "\"";
		SnowLog.getServerLog().info("文件内容" + text);
		// 得到文件存放路径
		String path = SysParamUtil.getParam(MchtChnlRequestConstants.PATH) + DateUtil.get8Date();
		File file = new File(path);
		if (!file.exists()) {
			// 如果文件不存在创建文件目录
			file.mkdirs();
		}
		// 写入文件
		byte[] b = text.getBytes();
		file = new File(path + File.separator + termId + mchtId + ".txt");
		try {
			OutputStream out = new FileOutputStream(file);
			out.write(b);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 开始下载文件
		String downLoadPath = path + File.separator + termId + mchtId + ".txt";
		File downFile = new File(downLoadPath);
		String fileName = downFile.getName();
		HttpServletResponse response = formRequestBean.getResponse();
		try {
			InputStream fis = new BufferedInputStream(new FileInputStream(downLoadPath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			OutputStream os = new BufferedOutputStream(response.getOutputStream());
			os.write(buffer);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 批量下载
	public void batDownLoadFile(FormRequestBean formRequestBean) throws SnowException {

		// mchtId,qmchtName,qtermId,qtermStat,qtermType,qmachInst
		String termId = formRequestBean.getParameter("qtermId");
		String mchtId = formRequestBean.getParameter("mchtId");
		String mchtName = formRequestBean.getParameter("qmchtName");
		String termStat = formRequestBean.getParameter("qtermStat");
		String termType = formRequestBean.getParameter("qtermType");
		String machInst = formRequestBean.getParameter("qmachInst");

		List<Object> list = PaypTermInfoService.getInstance().querybatDownLoadFile(termId, mchtId, termStat, termType,
				mchtName, machInst);

		String text = "";
		if (list != null && list.size() > 0) {
			int count = 0;
			for (int j = 0; j < list.size(); j++) {
				PaypTermDetailInf paypTermKey = (PaypTermDetailInf) list.get(j);
				if (paypTermKey.getZmk() != null && paypTermKey.getZak() != null && paypTermKey.getZpk() != null) {
					count++;
					// 得到秘钥实体
					String keyValue = StringUtil.rightPad(paypTermKey.getZmk(), 48);
					text += "\"" + paypTermKey.getMchtId() + "\",\"" + paypTermKey.getTermId()
							+ "\",\"04354910     \",\"" + keyValue + "\",\"" + keyValue + "\"\r\n";

				}
			}
			SnowLog.getServerLog().info("需要下载的密钥信息条数：" + count);
			SnowLog.getServerLog().info("文件内容" + text);
			// 得到文件存放路径
			String path = SysParamUtil.getParam(MchtChnlRequestConstants.PATH) + DateUtil.get8Date();
			File file = new File(path);
			if (!file.exists()) {
				// 如果文件不存在创建文件目录
				file.mkdirs();
			}
			// 写入文件
			String name = "batKeyDownLoad";
			byte[] b = text.getBytes();
			file = new File(path + File.separator + name + ".txt");
			try {
				OutputStream out = new FileOutputStream(file);
				out.write(b);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 开始下载文件
			String downLoadPath = path + File.separator + name + ".txt";
			File downFile = new File(downLoadPath);
			String fileName = downFile.getName();
			HttpServletResponse response = formRequestBean.getResponse();
			try {
				InputStream fis = new BufferedInputStream(new FileInputStream(downLoadPath));
				byte[] buffer = new byte[fis.available()];
				fis.read(buffer);
				fis.close();
				response.reset();
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
				OutputStream os = new BufferedOutputStream(response.getOutputStream());
				os.write(buffer);
				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// 申请秘钥
	public Map<String, String> makeKey(Map<String, UpdateRequestBean> updateMap) {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("errorCode", "00000");
		resultMap.put("errorMsg", "");
		UpdateRequestBean reqBean = updateMap.get("paypTermInf");
		Map<String, String> map = reqBean.next();
		String termId = map.get("termId");
		String mchtId = map.get("mchtId");
		// CommonAuditService.getInstance().addTermKey(mchtId, termId);//申请秘钥
		try {
			PaypTermKey paypTermKey = PaypTermInfoService.getInstance().queryKey(termId, mchtId);
			if (paypTermKey == null) {
				CommonAuditService.getInstance().addTermKey(mchtId, termId);// 申请秘钥
			} else {
				CommonAuditService.getInstance().updateTermKey(paypTermKey);
			}
		} catch (SnowException e) {
			e.printStackTrace();
			resultMap.put("errorCode", "-1");
			return resultMap;
		}

		return resultMap;
	}
}
