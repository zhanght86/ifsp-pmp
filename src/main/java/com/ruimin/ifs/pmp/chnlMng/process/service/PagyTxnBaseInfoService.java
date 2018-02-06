/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.comp 
 * FileName: PagyTxnBaseInfoService.java
 * Author:   zqy
 * Date:     2016年7月27日 上午10:39:09
 * Description: 通道信息管理     
 * History: 
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zqy           2016年7月27日上午10:39:09                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.service;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagyTxnAcctBankRel;
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagyTxnBaseInfo;

import java.util.Map;

/**
 * 名称：通道信息管理 功能：通道信息管理 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月27日 <br>
 * 作者：zqy <br>
 * 说明：<br>
 */
@SnowDoc(desc = "通道信息管理操作Service")
@Service
public class PagyTxnBaseInfoService extends SnowService {
	public static PagyTxnBaseInfoService getInstance() throws SnowException {
		return ContextUtil.getSingleService(PagyTxnBaseInfoService.class);
	}

	/**
	 * 分页查询通道信息
	 */
	public PageResult queryList(Map<String, String> paraMap, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.pagyTxnBaseInfo.queryList", paraMap, page);
	}

	/**
	 * 查询通道信息
	 */
	public PagyTxnBaseInfo queryByTxnCode(String pagyTxnCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.select(PagyTxnBaseInfo.class, pagyTxnCode);
	}

	/**
	 * 查询该通道交易编号是否存在
	 * 
	 * @param payTxnCode
	 * @return
	 * @throws SnowException
	 */
	public int queryPagyTxnCode(String pagyTxnCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (int) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.pagyTxnBaseInfo.queryPagyTxnCode",
				RqlParam.map().set("pagyTxnCode", pagyTxnCode));
	}

	/**
	 * 查询该支付通道交易账户银行关系是否存在
	 * 
	 * @param payTxnCode
	 * @return
	 * @throws SnowException
	 */
	public int queryRel(String pagyTxnCode, String acctTypeNo, String bankNo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		int count = (int) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.pagyTxnBaseInfo.queryRel",
				RqlParam.map().set("pagyTxnCode", pagyTxnCode).set("acctTypeNo", acctTypeNo).set("bankNo", bankNo));
		return count;
	}

	/**
	 * 添加支付通道交易
	 * 
	 * @param pagyTxnBaseInfo
	 *            支付通道交易
	 * @throws SnowException
	 */
	public void addPagyTxn(PagyTxnBaseInfo pagyTxnBaseInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 将银行信息持久化到数据库中
		dao.insert(pagyTxnBaseInfo);
	}

	/**
	 * 添加支付通道交易账户银行关系
	 * 
	 * @param pagyTxnBaseInfo
	 *            支付通道交易
	 * @throws SnowException
	 */
	public void saveRel(PagyTxnAcctBankRel pagyTxnAcctBankRel) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 将银行信息持久化到数据库中
		dao.insert(pagyTxnAcctBankRel);
	}

	/**
	 * 修改支付通道交易
	 * 
	 * @param pagyTxnBaseInfo
	 * @throws SnowException
	 */
	public void uptPagyTxn(PagyTxnBaseInfo pagyTxnBaseInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 将银行信息持久化到数据库中
		dao.update(pagyTxnBaseInfo);
	}

	/**
	 * 删除支付通道交易账户银行关系
	 * 
	 * @param pagyTxnBaseInfo
	 * @throws SnowException
	 */
	public void delRel(String pagyTxnCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 将银行信息持久化到数据库中
		dao.executeUpdate("com.ruimin.ifs.pmp.chnlMng.rql.pagyTxnBaseInfo.delRel", pagyTxnCode);
	}

	/**
	 * 修改通道交易状态
	 * 
	 * @param map
	 * @throws SnowException
	 */
	public void updateState(Map<String, String> map) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.chnlMng.rql.pagyTxnBaseInfo.updateState", map);
	}

	/**
	 * 根据通道交易编号获取通道交易名称
	 * 
	 * @param prodIds
	 * @return
	 * @throws SnowException
	 */
	public String getPagyTxnNames(String pagyTxnCodes) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String[] pagyTxnCode = pagyTxnCodes.split(",");

		return (String) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.pagyTxnBaseInfo.getPagyTxnNames",
				RqlParam.map().set("pagyTxnCodeArray", pagyTxnCode));
	}
}
