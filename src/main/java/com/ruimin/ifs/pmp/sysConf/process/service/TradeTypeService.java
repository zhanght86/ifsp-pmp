/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.mng.process.service 
 * FileName: TradeTypeService.java
 * Author:   chenqilei
 * Date:     2016年7月12日 上午10:10:13
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月12日上午10:10:13                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.sysConf.process.service;

import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;

import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;

import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.sysConf.process.bean.AcctAndTrade;
import com.ruimin.ifs.pmp.sysConf.process.bean.TradeType;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月12日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
@SnowDoc(desc = "交易操作")
public class TradeTypeService extends SnowService {
	public static TradeTypeService getInstance() throws SnowException {
		return ContextUtil.getSingleService(TradeTypeService.class);
	}

	/**
	 * 交易类型全查询
	 * 
	 * @param txnTypeCode
	 *            交易类型编号 [精确查询]
	 * @param txnTypeName
	 *            交易类型名称 [模糊查询]
	 * @param page
	 *            查询结果条数
	 * @return
	 */
	public PageResult queryListTmp(String txnTypeCode, String txnTypeName, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.sysConf.rql.tradeType.queryTradeByParam",
				RqlParam.map().set("txnTypeCode", StringUtils.isEmpty(txnTypeCode) ? "" : txnTypeCode)
						.set("txnTypeName", StringUtils.isEmpty(txnTypeName) ? "" : "%" + txnTypeName + "%"),
				page);
	}

	/**
	 * 查询最大序列号
	 * 
	 * @param qtxnTypeName
	 * @return 指定名称下的最大序列号
	 * @throws SnowException
	 */

	public String queryMaxSeq() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String maxSeq = (String) dao.selectOne("com.ruimin.ifs.pmp.sysConf.rql.tradeType.queryMaxSeq");
		return maxSeq;
	}

	/**
	 * 交易类型新增
	 * 
	 * @param tradeType
	 *            交易类型实体类对象
	 * @throws SnowException
	 */
	public void addSupply(TradeType tradeType) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(tradeType);
	}

	/**
	 * 交易类型修改
	 * 
	 * @param tradeType
	 *            交易类型实体类对象
	 * @throws SnowException
	 */
	public void updateTrade(TradeType tradeType) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(tradeType);
	}

	/**
	 * 根据主键获取交易信息
	 * 
	 * @param txnTypeCode
	 *            交易类型编号
	 * @return
	 * @throws SnowException
	 */
	public TradeType queryTradeById(String txnTypeCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.select(TradeType.class, txnTypeCode);
	}

	/**
	 * 新增关联表信息
	 * 
	 * @param aat
	 *            交易类型与账户类型关系表实体类对象
	 * @throws SnowException
	 */
	public void insertAcctAndTrade(AcctAndTrade aat) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(aat);
	}

	/**
	 * 修改关联表信息
	 * 
	 * @param aat
	 *            交易类型与账户类型关系表实体类对象
	 * @throws SnowException
	 */
	public void updateAcctAndTrade(AcctAndTrade aat) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(aat);
	}

	/**
	 * 修改交易类型状态（00-启用；99-停用）
	 * 
	 * @param tradeType
	 *            交易类型实体类对象
	 * @throws SnowException
	 */
	public void updateTradeStatus(TradeType tradeType) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String dataState = tradeType.getDataState();
		String lastUpdTlr = tradeType.getLastUpdTlr();
		String lastUpdDate = tradeType.getLastUpdDateTime();
		String txnTypeCode = tradeType.getTxnTypeCode();
		dao.executeUpdate("com.ruimin.ifs.pmp.sysConf.rql.tradeType.updateTradeStatus",
				RqlParam.map().set("dataState", dataState).set("lastUpdTlr", lastUpdTlr).set("lastUpdDate", lastUpdDate)
						.set("txnTypeCode", txnTypeCode));
	}

	/**
	 * 修改交易类型与关联表中的数据状态（00-启用；99-停用）
	 * 
	 * @param aat
	 *            交易类型与账户类型关系表实体类对象
	 * @throws SnowException
	 */
	public void updateTradeStatusAcctAndTrade(AcctAndTrade aat) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String txnTypeCode = aat.getTxnTypeCode();
		String dataState = aat.getDataState();
		dao.executeUpdate("com.ruimin.ifs.pmp.sysConf.rql.tradeType.updateTradeStatusAcctAndTrade",
				RqlParam.map().set("dataState", dataState).set("txnTypeCode", txnTypeCode));
	}

	/**
	 * 交易类型与账户类型关系最大序列号查询
	 * 
	 * @return 最大序列号
	 * @throws SnowException
	 */
	public String queryMaxSeq1() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		Object result = dao.selectOne("com.ruimin.ifs.pmp.sysConf.rql.tradeType.queryMaxSeq1");
		if(result == null ){return "0";}
		return result.toString();
	}

	/**
	 * 删除交易类型表中的数据
	 * 
	 * @param txnTypeCode
	 *            交易类型编号
	 * @throws SnowException
	 */
	public void deleteOldData(String txnTypeCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.sysConf.rql.tradeType.deleteByTxnTypeCode",
				RqlParam.map().set("txnTypeCode", txnTypeCode == null ? "" : txnTypeCode));
	}

	/**
	 * 删除关系表中的数据
	 * 
	 * @param trade
	 *            交易类型与账户类型关系表实体类对象
	 * @throws SnowException
	 */
	public void deleteData(AcctAndTrade trade) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(trade);
	}

	/**
	 * 根据交易类型和账户类型去查询该条数据是否重复添加
	 * 
	 * @param txnTypeCode
	 *            交易类型编号
	 * @param acctTypeNo
	 *            账户类型编号
	 * @return 根据交易类型和账户类型查询返回的条数
	 * @throws SnowException
	 */
	public List<Object> queryAcctTypeNo(String txnTypeCode, String acctTypeNo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.sysConf.rql.tradeType.queryAcctTypeNo",
				RqlParam.map().set("txnTypeCode", txnTypeCode).set("acctTypeNo", acctTypeNo));
	}

	/**
	 * 根据账户类型编号去查询账户类型名称
	 * 
	 * @param payTxnTypeId
	 * @return
	 * @throws SnowException
	 */
	public List<Object> queryByAcctTypeNo(String prodIds) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String[] prodIdArray = prodIds.split(",");
		return dao.selectList("com.ruimin.ifs.pmp.sysConf.rql.tradeType.queryByAcctTypeNo",
				RqlParam.map().set("prodIdArray", prodIdArray));
	}

}
