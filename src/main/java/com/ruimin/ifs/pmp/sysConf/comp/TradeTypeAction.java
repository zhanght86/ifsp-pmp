/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.sysConf.comp 
 * FileName: TradeTypeAction.java
 * Author:   chenqilei
 * Date:     2016年7月12日 上午9:21:58
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月12日上午9:21:58                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.sysConf.comp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.chnlMng.process.bean.SaveDataUser;
import com.ruimin.ifs.pmp.chnlMng.process.service.GalRoutMngService;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.sysConf.process.bean.AcctAndTrade;
import com.ruimin.ifs.pmp.sysConf.process.bean.AcctType;
import com.ruimin.ifs.pmp.sysConf.process.bean.TradeType;
import com.ruimin.ifs.pmp.sysConf.process.service.TradeTypeService;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月12日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
public class TradeTypeAction {
	/**
	 * 交易类型全查询
	 * 
	 * @param queryBean
	 * @return 数据库查询到的信息
	 */
	@Action
	@SnowDoc(desc = "分页查询交易信息")
	public PageResult queryTradeByCode(QueryParamBean queryBean) throws SnowException {
		// 操作员登陆session
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		Map<String, String> queryMap = new HashMap<String, String>();
		// 交易类型编号
		String qtxnTypeCode = queryBean.getParameter("qtxnTypeCode");
		// 交易类型名称
		String qtxnTypeName = queryBean.getParameter("qtxnTypeName");
		// 查询交易信息
		return TradeTypeService.getInstance().queryListTmp(qtxnTypeCode, qtxnTypeName, queryBean.getPage());
	}

	/**
	 * 交易类型新增
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增交易")
	public void addTrade(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateMap.get("tradeType");
		// 创建实体类对象
		TradeType tradeType = new TradeType();
		// 交易表中的最大序列号
		String nextSeq = selectMaxSeqTrade();
		// 关联表中的最大序列号
		String nextSeq1 = selectMaxSeqAnd();

		// 获取操作员信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		int maxseq = Integer.parseInt(nextSeq1);
		String s = reqBean.getParameter("s");
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tradeType);
			String[] ss = s.split(",");
			List lists = Arrays.asList(ss);
			int size = lists.size();

			for (int i = 0; i < size; i++) {
				AcctAndTrade aat = new AcctAndTrade();
				// 像关联表中设置数据
				aat.setRelNo(Integer.toString(maxseq));
				aat.setTxnTypeCode(nextSeq);
				aat.setAcctTypeNo(ss[i]);
				aat.setDataState("00");
				aat.setCrtTlr(sessionUserInfo.getTlrno());
				aat.setCrtDateTime(BaseUtil.getCurrentDateTime());
				// 像关联表中插入数据
				TradeTypeService.getInstance().insertAcctAndTrade(aat);
				maxseq++;
			}
		}
		tradeType.setTxnTypeCode(nextSeq);
		// 设置记录状态为启用("00"启用,"99"停用)
		tradeType.setDataState("00");
		tradeType.setCrtTlr(sessionUserInfo.getTlrno());
		tradeType.setCrtDateTime(BaseUtil.getCurrentDateTime());
		// 调用添加的方法
		TradeTypeService.getInstance().addSupply(tradeType);

		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[交易类型管理]--新增 : 交易类型编号[txnTypeCode]=" + tradeType.getTxnTypeCode() });
	}

	/**
	 * 交易类型修改
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改交易")
	public void updateTrade(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateMap.get("tradeType");

		// 跟新相关操作信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		TradeType tradeType = new TradeType();

		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tradeType);
		}
		// 跟新相关操作
		tradeType.setLastUpdTlr(sessionUserInfo.getTlrno());
		tradeType.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		// 调用修改的方法 修改交易类型信息
		TradeTypeService.getInstance().updateTrade(tradeType);
		// 获取最大序列号
		String nextSeq = selectMaxSeqAnd();
		int maxSeq = Integer.parseInt(nextSeq);
		// 获取界面上传的分段信息
		UpdateRequestBean reqBean1 = updateMap.get("acctAndTrade");
		String txnTypeCode = reqBean.getParameter("txnTypeCode");
		// 创建关系表对象
		AcctAndTrade trade = new AcctAndTrade();
		while (reqBean1.hasNext()) {
			DataObjectUtils.map2bean(reqBean1.next(), trade);
			// 当本条记录未改动时,跳过本条
			if (reqBean1.getRecodeState() == reqBean1.NONE) {
				continue;
			}
			// 当本条数据为删除时
			if (reqBean1.getRecodeState() == reqBean1.DELETE) {
				// 删除关系表中的数据
				TradeTypeService.getInstance().deleteData(trade);
			} else if (reqBean1.getRecodeState() == reqBean1.INSERT) {
				// 如果本条记录状态为新增时
				String acctTypeNo = trade.getAcctTypeNo();
				// 根据交易类型和账户类型去查询该条数据是否重复添加
				List<Object> list = TradeTypeService.getInstance().queryAcctTypeNo(txnTypeCode, acctTypeNo);
				if (list.size() != 0) {
					SnowExceptionUtil.throwErrorException("该账户类型已经添加过，请重新选择!");
				}
				trade.setRelNo(Integer.toString(maxSeq));
				trade.setAcctTypeNo(acctTypeNo);
				trade.setTxnTypeCode(txnTypeCode);
				trade.setDataState("00");
				trade.setCrtTlr(sessionUserInfo.getTlrno());
				trade.setCrtDateTime(BaseUtil.getCurrentDateTime());
				trade.setLastUpdTlr(sessionUserInfo.getTlrno());
				trade.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
				// 调用新增的关系表的方法
				TradeTypeService.getInstance().insertAcctAndTrade(trade);
				maxSeq++;
			} else if (reqBean1.getRecodeState() == reqBean1.MODIFY) {
				// 先删除原有的数据
				// TradeTypeService.getInstance().deleteOldData(txnTypeCode);
				String acctTypeNo = trade.getAcctTypeNo();
				List<Object> list = TradeTypeService.getInstance().queryAcctTypeNo(txnTypeCode, acctTypeNo);
				if (list.size() != 0) {
					SnowExceptionUtil.throwErrorException("该账户类型已经添加过，请重新选择!");
				}
				// trade.setRelNo(Integer.toString(maxSeq));
				trade.setTxnTypeCode(txnTypeCode);
				trade.setAcctTypeNo(acctTypeNo);
				trade.setDataState("00");
				trade.setCrtTlr(sessionUserInfo.getTlrno());
				trade.setCrtDateTime(BaseUtil.getCurrentDateTime());
				trade.setLastUpdTlr(sessionUserInfo.getTlrno());
				trade.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
				// 条用修改的方法修改关联表中的数据
				TradeTypeService.getInstance().updateAcctAndTrade(trade);
				// 打印日志
				sessionUserInfo.addBizLog("update.log",
						new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
								"[交易类型管理]--修改 : 交易类型编号[txnTypeCode]=" + tradeType.getTxnTypeCode() });
			}
		}

	}

	/**
	 * 交易类型状态的修改
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "设置交易状态为停用/启用")
	public void updateTradeStatus(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateMap.get("tradeType");
		String dataState = reqBean.getParameter("dataState");
		TradeTypeService tradeTypeService = TradeTypeService.getInstance();
		TradeType tradeType = new TradeType();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tradeType);
			AcctAndTrade aat = new AcctAndTrade();
			String txnTypeCode = tradeType.getTxnTypeCode();
			aat.setTxnTypeCode(txnTypeCode);
			aat.setDataState(dataState);
			tradeTypeService.updateTradeStatusAcctAndTrade(aat);
		}

		// 更新相关操作信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		tradeType.setLastUpdTlr(sessionUserInfo.getTlrno());
		tradeType.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		tradeType.setDataState(dataState);

		// 调用删除方法
		tradeTypeService.updateTradeStatus(tradeType);

		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[交易类型管理]--修改状态 : 交易类型编号[txnTypeCode]=" + tradeType.getTxnTypeCode() });
	}

	public String selectMaxSeqTrade() throws SnowException {
		// 查询最大序列号
		String maxSeq = TradeTypeService.getInstance().queryMaxSeq();
		String nextSeq;
		if (maxSeq != null) {
			int num = Integer.valueOf(maxSeq);
			nextSeq = StringUtil.leftPad(String.valueOf(num + 1), 2, "0");
		} else {
			nextSeq = "1001";
		}
		return nextSeq;
	}

	public String selectMaxSeqAnd() throws SnowException {
		// 关联表中查询最大序列号
		String maxSeq1 = TradeTypeService.getInstance().queryMaxSeq1();
		String nextSeq1;
		if (maxSeq1 != null) {
			int num1 = Integer.valueOf(maxSeq1);
			nextSeq1 = StringUtil.leftPad(String.valueOf(num1 + 1), 2, "0");
		} else {
			nextSeq1 = "10";
		}
		return nextSeq1;
	}

	/**
	 * 根据账户类型编号去查询账户类型名称
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	public static String queryByAcctTypeNo(FieldBean bean, String key, ServletRequest request) throws SnowException {
		StringBuffer strBuf = new StringBuffer();

		if (StringUtil.isEmpty(key)) {
			return strBuf.toString();
		}
		List<Object> prodList = TradeTypeService.getInstance().queryByAcctTypeNo(key);

		if (prodList == null || prodList.size() == 0) {
			return strBuf.toString();
		}
		for (Object prodObj : prodList) {
			AcctType prod = (AcctType) prodObj;
			strBuf.append(prod.getAcctTypeName()).append(",");
		}
		return strBuf.toString().substring(0, strBuf.toString().length() - 1);

	}

}
