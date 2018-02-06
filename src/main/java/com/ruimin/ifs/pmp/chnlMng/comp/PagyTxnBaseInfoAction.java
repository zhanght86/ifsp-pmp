/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.comp 
 * FileName: PagyTxnBaseInfoAction.java
 * Author:   zqy
 * Date:     2016年7月27日 上午10:39:09
 * Description: 支付通道交易信息管理     
 * History: 
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zqy           2016年7月27日上午10:39:09                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.comp;

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
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.chnlMng.common.constants.PagyMchtMngConstants;
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagyTxnAcctBankRel;
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagyTxnBaseInfo;
import com.ruimin.ifs.pmp.chnlMng.process.service.PagyTxnBaseInfoService;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.util.BaseUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

/**
 * 名称：支付通道交易信息管理 功能：支付通道交易信息管理 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月27日 <br>
 * 作者：zqy <br>
 * 说明：<br>
 */
@SnowDoc(desc = "支付通道交易信息管理操作Action")
@ActionResource
public class PagyTxnBaseInfoAction extends SnowAction {

	@Action
	@SnowDoc(desc = "查询支付通道交易信息")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		// 获取查询参数
		String qpagyNo = queryBean.getParameter("qpagyNo");// 通道编号
		String qpagyTxnCode = queryBean.getParameter("qpagyTxnCode");// 交易编号
		String qpagyTxnName = queryBean.getParameter("qpagyTxnName");// 交易名称

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("qpagyNo", StringUtil.isBlank(qpagyNo) ? "" : "%" + qpagyNo + "%");
		paramMap.put("qpagyTxnCode", StringUtil.isBlank(qpagyTxnCode) ? "" : qpagyTxnCode);
		paramMap.put("qpagyTxnName", StringUtil.isBlank(qpagyTxnName) ? "" : "%" + qpagyTxnName + "%");
		// 分页查询支付通道交易信息
		return PagyTxnBaseInfoService.getInstance().queryList(paramMap, queryBean.getPage());
	}

	/**
	 * 支付通道交易新增
	 * 
	 * @param updateBean
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "支付通道交易新增")
	public void addPagyTxn(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("pagyTxnBaseInfo");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 创建实体类对象
		PagyTxnBaseInfo pagyTxnBaseInfo = new PagyTxnBaseInfo();
		// 1. 保存通道交易基础信息
		// 获取通道编号
		String pagyNo = reqBean.getParameter("pagyNo");
		// 获取交易序号
		String txnCode = reqBean.getParameter("txnCode");
		// 设置通道交易编号
		String pagyTxnCode = pagyNo + txnCode;
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), pagyTxnBaseInfo);
			// 查询该交易编号是否存在 存在就提示错误
			int count = PagyTxnBaseInfoService.getInstance().queryPagyTxnCode(pagyTxnCode);
			if (count > 0) {
				SnowExceptionUtil.throwErrorException("通道交易编号已存在不允许重复");
			}
			String crtTlr = sessionUserInfo.getTlrno();
			String crtDateTime = BaseUtil.getCurrentDateTime();
			pagyTxnBaseInfo.setPagyTxnCode(pagyTxnCode);
			pagyTxnBaseInfo.setCrtTlr(crtTlr);
			pagyTxnBaseInfo.setCrtDateTime(crtDateTime);
			// 调用新增的方法
			PagyTxnBaseInfoService.getInstance().addPagyTxn(pagyTxnBaseInfo);
			// 打印日志
			sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
					sessionUserInfo.getBrno(), "[支付通道交易]--新增 : 交易编号[pagyTxnCode]=" + pagyTxnCode });
		}

		// 2. 保存通道交易账户类型银行关系信息
		UpdateRequestBean RelBean = updateBean.get("pagyTxnAcctBankRel");
		List<Map<String, String>> relList = RelBean.getTotalList();

		PagyTxnAcctBankRel PagyTxnAcctBankRel = new PagyTxnAcctBankRel();
		for (Map<String, String> map : relList) {
			// 查询该通道交易账户类型银行信息是否存在 存在就提示错误
			String acctTypeNo = map.get("acctTypeNoRel");
			String bankNo = map.get("bankNoRel");
			int countRel = PagyTxnBaseInfoService.getInstance().queryRel(pagyTxnCode, acctTypeNo, bankNo);
			if (countRel > 0) {
				SnowExceptionUtil.throwErrorException("通道交易账户类型银行信息已存在,不允许重复");
			}
			PagyTxnAcctBankRel.setPagyTxnCode(pagyTxnCode);
			PagyTxnAcctBankRel.setAcctTypeNo(acctTypeNo);
			PagyTxnAcctBankRel.setBankNo(bankNo);
			PagyTxnAcctBankRel.setCrtTlr(sessionUserInfo.getTlrno());// 创建柜员
			PagyTxnAcctBankRel.setCrtDateTime(BaseUtil.getCurrentDateTime());// 创建时间
			PagyTxnAcctBankRel.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
			PagyTxnAcctBankRel.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新时间
			PagyTxnBaseInfoService.getInstance().saveRel(PagyTxnAcctBankRel);// 持久化限额信息
		}
	}

	/**
	 * 支付通道交易修改
	 * 
	 * @param updateBean
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "支付通道交易修改")
	public void uptPagyTxn(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("pagyTxnBaseInfo");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 创建实体类对象
		PagyTxnBaseInfo pagyTxnBaseInfo = new PagyTxnBaseInfo();
		DataObjectUtils.map2bean(reqBean.next(), pagyTxnBaseInfo);

		// 1. 保存通道交易基础信息
		// 获取该交易编号的通道交易信息存入实体类
		PagyTxnBaseInfo queryData = PagyTxnBaseInfoService.getInstance()
				.queryByTxnCode(pagyTxnBaseInfo.getPagyTxnCode());

		queryData.setPagyTxnName(pagyTxnBaseInfo.getPagyTxnName());
		queryData.setTxnType(pagyTxnBaseInfo.getTxnType());
		queryData.setPagyTxnStat(pagyTxnBaseInfo.getPagyTxnStat());
		queryData.setBindCheck(pagyTxnBaseInfo.getBindCheck());

		queryData.setCancelTxnCode(pagyTxnBaseInfo.getCancelTxnCode());
		queryData.setQueryTxnCode(pagyTxnBaseInfo.getQueryTxnCode());
		queryData.setRefundTxnCode(pagyTxnBaseInfo.getRefundTxnCode());
		queryData.setValidateTxnCode(pagyTxnBaseInfo.getValidateTxnCode());

		queryData.setLastUpdTlr(sessionUserInfo.getTlrno());
		queryData.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		// 调用修改的方法
		PagyTxnBaseInfoService.getInstance().uptPagyTxn(queryData);
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[支付通道交易]--修改 : 交易编号[pagyTxnCode]=" + pagyTxnBaseInfo.getPagyTxnCode() });

		/*************************
		 * TOP2. 保存通道交易账户类型银行关系信息
		 *************************************/
		// 删除已存在的通道交易账户类型银行关系
		PagyTxnBaseInfoService.getInstance().delRel(pagyTxnBaseInfo.getPagyTxnCode());

		UpdateRequestBean RelBean = updateBean.get("pagyTxnAcctBankRel");
		List<Map<String, String>> relList = RelBean.getTotalList();
		PagyTxnAcctBankRel pagyTxnAcctBankRel = new PagyTxnAcctBankRel();
		for (Map<String, String> map : relList) {
			if ("delete".equals(map.get("recordState")))
				continue;
			// 查询该通道交易账户类型银行信息是否存在 存在就提示错误
			String acctTypeNo = map.get("acctTypeNoRel");
			String bankNo = map.get("bankNoRel");
			pagyTxnAcctBankRel.setPagyTxnCode(pagyTxnBaseInfo.getPagyTxnCode());
			pagyTxnAcctBankRel.setAcctTypeNo(acctTypeNo);
			pagyTxnAcctBankRel.setBankNo(bankNo);
			pagyTxnAcctBankRel.setCrtTlr(sessionUserInfo.getTlrno());// 创建柜员
			pagyTxnAcctBankRel.setCrtDateTime(BaseUtil.getCurrentDateTime());// 创建时间
			pagyTxnAcctBankRel.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
			pagyTxnAcctBankRel.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新时间
			PagyTxnBaseInfoService.getInstance().saveRel(pagyTxnAcctBankRel);// 持久化限额信息
		}
	}

	/**
	 * 修改通道交易状态
	 * 
	 * @param requestMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "启用/停用")
	public void uptState(Map<String, UpdateRequestBean> requestMap) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";
		UpdateRequestBean requestBean = requestMap.get("pagyTxnBaseInfo");
		Map<String, String> map = new HashMap<String, String>();
		map.put("pagyTxnCode", requestBean.getParameter("pagyTxnCode"));// 通道交易编号
		map.put("pagyTxnStat", requestBean.getParameter("pagyTxnStat"));// 通道交易状态
		map.put("lastUpdTlr", sessionUserInfo.getTlrno());// 最近更新柜员
		map.put("lastUpdDateTime", BaseUtil.getCurrentDateTime());// 最近更新时间
		PagyTxnBaseInfoService.getInstance().updateState(map);// 修改通道交易状态
		StringBuilder builder = new StringBuilder();
		builder.append("------------ ");
		builder.append("更改通道交易状态为");
		builder.append(map.get("pagyTxnStat").equals(PagyMchtMngConstants.PAGY_TXN_STAT_99) ? "停用" : "启用");
		builder.append(", 通道交易编号[pagyTxnCode] = " + map.get("pagyTxnCode"));
		builder.append(" ------------");

		// 日志
		msg = "[通道交易列表 ]--" + builder.toString();
		SnowLog.getLogger(this.getClass()).info(builder.toString());
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}

	/**
	 * 根据通道交易编号获取通道交易名称
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String getPagyTxnNames(FieldBean bean, String key, ServletRequest request) throws SnowException {
		StringBuffer strBuf = new StringBuffer();
		if (StringUtil.isEmpty(key)) {
			return strBuf.toString();
		}
		String prodList = PagyTxnBaseInfoService.getInstance().getPagyTxnNames(key);

		if (prodList == null || "".equals(prodList)) {
			return strBuf.toString();
		}
		strBuf.append(prodList);
		return strBuf.toString();
	}

}
