/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.sysConf.comp 
 * FileName: PmpBankInfoAction.java
 * Author:   ZLJ
 * Date:     2016年7月12日 上午11:22:08
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   ZLJ           2016年7月12日上午11:22:08                     1.0                  
 *===============================================================================================
 */

package com.ruimin.ifs.pmp.sysConf.comp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.CommonUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.sysConf.common.constants.PmpBankInfoConstants;
import com.ruimin.ifs.pmp.sysConf.process.bean.PassInfoVO;
import com.ruimin.ifs.pmp.sysConf.process.bean.PayBankBaseInfo;
import com.ruimin.ifs.pmp.sysConf.process.bean.PmpBankLimitInfoVO;
import com.ruimin.ifs.pmp.sysConf.process.bean.PmpRelBankPassBankVO;
import com.ruimin.ifs.pmp.sysConf.process.service.PmpBankInfoService;

/**
 * 名称：〈银行列表信息管理〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月12日 <br>
 * 作者：RLX <br>
 * 说明：银行列表 <br>
 */
@SnowDoc(desc = "银行列表管理")
@ActionResource
public class PmpBankInfoAction {

	/**
	 * 添加产品银行
	 * 
	 * @param requestMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增/修改 产品银行")
	public void addPmpBankInfo(Map<String, UpdateRequestBean> requestMap) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		UpdateRequestBean requestBean = requestMap.get("PmpBankInfo");
		PmpBankInfoService bankInfoService = PmpBankInfoService.getInstance();// 获取银行列表service

		PayBankBaseInfo bankInfo = new PayBankBaseInfo();
		DataObjectUtils.map2bean(requestBean.next(), bankInfo);

		String msg = "";
		// 判断显示顺序是否重复
		int count = bankInfoService.judgeShowSerExit(bankInfo.getShowSer(), bankInfo.getBankNo());
		if (count > 0) {
			SnowExceptionUtil.throwWarnException("显示顺序已存在，请重新输出!");
		}

		/***************************************
		 * TOP1:新增银行
		 ********************************************/
		if (StringUtils.isBlank(bankInfo.getBankNo())) {
			SnowLog.getLogger(this.getClass()).info("------------ 新增银行 ------------");
			// 最大的银行编号
			String maxBankNo = PmpBankInfoService.getInstance().getMaxBankNo();
			// 判断数据库中是否存在银行编号， 存在使用库中最大加1 ， 否则使用默认
			bankInfo.setBankNo(StringUtils.isEmpty(maxBankNo) ? PmpBankInfoConstants.START_BANK_NO
					: (Long.valueOf(maxBankNo) + 1) + "");
			bankInfo.setDataState(PmpBankInfoConstants.BANK_INFO_STAT_00);// 有效状态
			bankInfo.setCrtTlr(sessionUserInfo.getTlrno());// 创建柜员
			bankInfo.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
			// 获得系统当前日期时间 格式:yyyyMMddHHmmss
			bankInfo.setCrtDateTime(BaseUtil.getCurrentDateTime());// 创建时间
			bankInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新时间

			bankInfoService.save(bankInfo);

			// 日志记录
			msg = "[银行基础列表 ]--新增银行基础信息 :银行编号[BankNo]=" + bankInfo.getBankNo();
		} else {
			/***************************************
			 * TOP2:修改银行
			 ********************************************/
			SnowLog.getLogger(this.getClass()).info("------------ 修改银行 ------------");
			PayBankBaseInfo newBankInfo = new PayBankBaseInfo();
			newBankInfo.setBankNo(bankInfo.getBankNo());
			newBankInfo.setBankName(bankInfo.getBankName());// 银行名称
			newBankInfo.setShowSer(bankInfo.getShowSer());// 显示顺序

			newBankInfo.setSelfOtherFlag(bankInfo.getSelfOtherFlag());// 本行他行标志
			newBankInfo.setPicId(bankInfo.getPicId());// 银行图标
			newBankInfo.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
			// 获得系统当前日期时间 格式:yyyyMMddHHmmss
			newBankInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新时间

			bankInfoService.update(newBankInfo);// 修改银行信息

			SnowLog.getLogger(this.getClass()).info("------------ 删除银行原限额信息 ------------");
			bankInfoService.deleteBankLimitByBankNo(bankInfo.getBankNo());// 按银行编号删除限额信息

			// 日志记录
			msg = "[银行基础列表 ]--修改银行基础信息 :银行编号[BankNo]=" + bankInfo.getBankNo();
		}

		/***************************************
		 * TOP3:添加银行银行限额信息
		 ********************************************/
		SnowLog.getLogger(this.getClass()).info("------------ 添加银行银行限额信息 ------------");

		UpdateRequestBean bankLimitBean = requestMap.get("PmpBankLimitInfo");
		List<Map<String, String>> bankLimitList = bankLimitBean.getTotalList();// 银行限额信息列表

		PmpBankLimitInfoVO bankLimitInfo = null;

		// 最大的限额编号
		String strMaxBankLimitNo = PmpBankInfoService.getInstance().getMaxBankLimitNo();
		Long maxBankLimitNo = StringUtil.isEmpty(strMaxBankLimitNo) ? 10000000L : Long.valueOf(strMaxBankLimitNo);
		for (Map<String, String> map : bankLimitList) {
			if ("delete".equals(map.get("recordState")))
				continue;
			bankLimitInfo = DataObjectUtils.map2bean(map, PmpBankLimitInfoVO.class);

			bankLimitInfo.setLimitNo(++maxBankLimitNo + "");// 限额编号
			bankLimitInfo.setBankNo(bankInfo.getBankNo());// 银行编号
			bankLimitInfo.setDataState(PmpBankInfoConstants.BANK_INFO_STAT_00);// 数据有效状态
			bankLimitInfo.setCrtTlr(sessionUserInfo.getTlrno());// 创建柜员
			bankLimitInfo.setCrtDateTime(BaseUtil.getCurrentDateTime());// 创建时间
			bankLimitInfo.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
			bankLimitInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新时间

			bankLimitInfo.setLimitOne(CommonUtil.transYuanToFen(bankLimitInfo.getLimitOne()));// 单笔限额元转分
			bankLimitInfo.setLimitDay(CommonUtil.transYuanToFen(bankLimitInfo.getLimitDay()));// 当日限额元转分
			bankLimitInfo.setLimitMon(CommonUtil.transYuanToFen(bankLimitInfo.getLimitMon()));// 当月限额元转分

			bankInfoService.save(bankLimitInfo);// 持久化限额信息
		}

		msg += ",添加银行银行限额信息";
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}

	/**
	 * 分页查询产品银行信息
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "分页查询产品银行")
	public PageResult pageQueryAll(QueryParamBean queryBean) throws SnowException {
		// SessionUserInfo sessionUserInfo =
		// SessionUserInfo.getSessionUserInfo();

		// 查询标志
		String param = queryBean.getParameter("param", "");

		// 查询详情
		if ("detail".equals(param)) {

		}
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("bankNo", queryBean.getParameter("qBankNo", ""));// 银行编号
		queryMap.put("bankName", "%" + queryBean.getParameter("qBankName", "") + "%");// 银行名称
		queryMap.put("dataState", queryBean.getParameter("qDataState", ""));// 有效状态
		queryMap.put("selfOtherFlag", queryBean.getParameter("qselfOtherFlag", ""));// 本行他行标志

		return PmpBankInfoService.getInstance().listBank(queryMap, queryBean.getPage());
	}

	/**
	 * 按银行编号查询限额列表
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "按银行编号查询限额列表")
	public PageResult queryBankLimitByBankNo(QueryParamBean queryBean) throws SnowException {
		String bankNo = queryBean.getParameter("bankNo");
		bankNo = StringUtil.isEmpty(bankNo) ? "" : bankNo;
		return PmpBankInfoService.getInstance().listBankLimit(bankNo, queryBean.getPage());
	}

	/**
	 * 修改银行状态
	 * 
	 * @param requestMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "启用/停用")
	public void modifyDataState(Map<String, UpdateRequestBean> requestMap) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";
		UpdateRequestBean requestBean = requestMap.get("PmpBankInfo");
		Map<String, String> map = new HashMap<String, String>();
		map.put("bankNo", requestBean.getParameter("bankNo"));// 银行编号
		map.put("dataState", requestBean.getParameter("dataState"));// 有效状态
		map.put("lastUpdTlr", sessionUserInfo.getTlrno());// 最近更新柜员
		map.put("lastUpdDateTime", BaseUtil.getCurrentDateTime());// 最近更新时间
		PmpBankInfoService.getInstance().updateStateByBankNo(map);// 修改有效状态

		StringBuilder builder = new StringBuilder();
		builder.append("------------ ");
		builder.append("更改银行状态为");
		builder.append(map.get("dataState").equals(PmpBankInfoConstants.BANK_INFO_STAT_99) ? "停用" : "启用");
		builder.append(", 银行编号[BankNo] = ");
		builder.append(map.get("bankNo"));
		builder.append(" ------------");

		// 日志
		msg = "[银行基础列表 ]--" + builder.toString();
		SnowLog.getLogger(this.getClass()).info(builder.toString());
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}

	/**
	 * 配置通道银行
	 * 
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "配置通道银行")
	public void confPassBank(Map<String, UpdateRequestBean> requestMap) throws SnowException {
		PmpBankInfoService bankInfoService = PmpBankInfoService.getInstance();// 获取银行service
		UpdateRequestBean requestBean = requestMap.get("PmpBankInfo");// 获取银行信息
		UpdateRequestBean passBankReqBean = requestMap.get("PmpRelBankPassBank");// 获取通道银行配置信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		String bankNo = requestBean.next().get("bankNo");// 产品银行编号

		SnowLog.getLogger(this.getClass()).info("------------ 删除银行原通道配置信息 ------------");
		bankInfoService.deletePassBankByBankNo(bankNo);// 按产品银行删除原配置通道

		SnowLog.getLogger(this.getClass()).info("------------ 添加通道银行信息 ------------");
		List<Map<String, String>> passBankList = passBankReqBean.getTotalList();// 通道银行配置信息

		// 最大的限额编号
		String strMaxRelNo = bankInfoService.getMaxRelNo();
		Long maxRelNo = StringUtil.isEmpty(strMaxRelNo) ? 10000000L : Long.valueOf(strMaxRelNo);

		PmpRelBankPassBankVO passBank = null;

		for (Map<String, String> map : passBankList) {
			if ("delete".equals(map.get("recordState")))
				continue;
			passBank = DataObjectUtils.map2bean(map, PmpRelBankPassBankVO.class);
			passBank.setRelNo(++maxRelNo + "");// 记录编号
			passBank.setDataState(PmpBankInfoConstants.BANK_INFO_STAT_00);// 数据有效状态
			passBank.setCrtTlr(sessionUserInfo.getTlrno());// 创建柜员
			passBank.setCrtDateTime(BaseUtil.getCurrentDateTime());// 创建时间
			passBank.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
			passBank.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新时间
			passBank.setProdBankNo(bankNo);// 产品银行编号
			bankInfoService.save(passBank);// 持久化通道配置
		}

	}

	/**
	 * 按产品银行编号查询通道信息
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@SnowDoc(desc = "查询通道银行配置")
	public PageResult queryPmpBankPassBank(QueryParamBean queryBean) throws SnowException {
		String bankNo = queryBean.getParameter("BankNo", "");
		return PmpBankInfoService.getInstance().listPmpRelBankPassBank(bankNo, queryBean.getPage());
	}

	/**
	 * 按通道名称查询全部有效通道
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@SnowDoc(desc = "查询有效通道")
	public PageResult qeuryAllPassInfo(QueryParamBean queryBean) throws SnowException {
		return PmpBankInfoService.getInstance().listPassInfo("%" + queryBean.getParameter("qPassName", "") + "%",
				queryBean.getPage());
	}

	/**
	 * 按通道编号查询全部有效通道
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@SnowDoc(desc = "查询有效的通道银行")
	public PageResult queryAllPassBank(QueryParamBean queryBean) throws SnowException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("bankName", "%" + queryBean.getParameter("qBankName", "") + "%");// 银行名称
		map.put("passNo", queryBean.getParameter("qPassNo", ""));// 通道编号
		return PmpBankInfoService.getInstance().listPassBank(map, queryBean.getPage());
	}

	/**
	 * 按通道编号获取通道名称
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String getPassName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		String passName = "";
		if (StringUtil.isEmpty(key)) {
			return passName;
		}

		PassInfoVO vo = PmpBankInfoService.getInstance().getPassNameByNo(key);
		if (vo != null) {
			passName = vo.getPassName();
		}
		return passName;
	}

	/**
	 * 按银行编号获取通道名称
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String getBankName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		String bankName = "";
		if (StringUtil.isEmpty(key)) {
			return bankName;
		}

		PayBankBaseInfo vo = PmpBankInfoService.getInstance().getBankInfo(key);
		if (vo != null) {
			bankName = vo.getBankName();
		}
		return bankName;
	}

}
