/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.process.service 
 * FileName: GalRoutMngService.java
 * Author:   chenqilei
 * Date:     2016年8月2日 下午12:42:40
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年8月2日下午12:42:40                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.service;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.Page;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.chnlMng.process.bean.GalRoutMng;
import com.ruimin.ifs.pmp.chnlMng.process.bean.SaveDataUser;

import java.util.List;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月2日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
public class GalRoutMngService {
	public static GalRoutMngService getInstance() throws SnowException {
		return ContextUtil.getSingleService(GalRoutMngService.class);
	}

	/**
	 * 通道路由的全查询
	 * 
	 * @param routeId
	 *            路由编号 [模糊查询]
	 * @param payTxnTypeId
	 *            交易类型编号 [精确查询]
	 * @param acctTypeNo
	 *            账户类型编号 [精确查询]
	 * @param routeStat
	 *            路由状态 [精确查询]
	 * @param chnlId
	 *            接入渠道编号 [精确查询]
	 * @param page
	 *            分页查询条数
	 * @return 符合条件的信息
	 * @throws SnowException
	 */
	public PageResult queryAll(String routeId, String payTxnTypeId, String acctTypeNo, String routeStat, String chnlId,
			Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.galRoutMng.queryAll",
				RqlParam.map().set("routeId", StringUtils.isEmpty(routeId) ? "" : "%" + routeId + "%")
						.set("payTxnTypeId", StringUtils.isEmpty(payTxnTypeId) ? "" : payTxnTypeId.trim())
						.set("acctTypeNo", StringUtils.isEmpty(acctTypeNo) ? "" : acctTypeNo)
						.set("routeStat", StringUtils.isEmpty(routeStat) ? "" : routeStat)
						.set("chnlId", StringUtils.isEmpty(chnlId) ? "" : chnlId),
				page);
	}

	/**
	 * 查询最大序列号
	 * 
	 * @return
	 * @throws SnowException
	 */
	public String queryMaxSeq() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.galRoutMng.queryMaxSeq");
	}

	/**
	 * 查询账户类型+交易类型+渠道名称是否重复
	 * 
	 * @param payTxnTypeId
	 *            交易类型编号
	 * @param acctTypeNo
	 *            账户类型编号
	 * @param chnlId
	 *            接入渠道编号
	 * @return 根据条件查询的条数
	 * @throws SnowException
	 */
	public GalRoutMng queryExist(String payTxnTypeId, String acctTypeNo, String chnlId) throws SnowException {
		DBDao dao = DBDaos.newInstance();

		GalRoutMng integer = (GalRoutMng) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.galRoutMng.queryExist",
				RqlParam.map().set("payTxnTypeId", StringUtils.isEmpty(payTxnTypeId) ? "" : payTxnTypeId.trim())
						.set("acctTypeNo", StringUtils.isEmpty(acctTypeNo) ? "" : acctTypeNo)
						.set("chnlId", StringUtils.isEmpty(chnlId) ? "" : chnlId));

		return integer;
	}

	/**
	 * 通道路由的新增
	 * 
	 * @param rout
	 *            通道路由实体类的对象
	 * @throws SnowException
	 */
	public void addRoutMng(GalRoutMng rout) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(rout);
	}

	/**
	 * 通道路由的修该功能
	 * 
	 * @param rout
	 *            通道路由实体类的对象
	 * @throws SnowException
	 */
	public void updateRoutMng(GalRoutMng rout) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(rout);
	}

	/**
	 * 通道路由状态的修改 修改通道路由状态为'00'
	 * 
	 * @param rout
	 *            通道路由实体类的对象
	 * @throws SnowException
	 */
	public void updateStatus(GalRoutMng rout) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String routeStat = rout.getRouteStat();
		String routeId = rout.getRouteId();
		dao.executeUpdate("com.ruimin.ifs.pmp.chnlMng.rql.galRoutMng.updateStatus",
				RqlParam.map().set("routeStat", routeStat).set("routeId", routeId));
	}

	/**
	 * 通道路由状态的修改 修改通道路由状态为'00' 并设置首次开通时间
	 * 
	 * @param rout
	 *            通道路由实体类的对象
	 * @throws SnowException
	 */
	public void updateRoutMngStatus(GalRoutMng rout) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String routeStat = rout.getRouteStat();
		String openDate = rout.getOpenDate();
		String routeId = rout.getRouteId();
		dao.executeUpdate("com.ruimin.ifs.pmp.chnlMng.rql.galRoutMng.updateRoutMngStatus",
				RqlParam.map().set("routeStat", routeStat).set("openDate", openDate).set("routeId", routeId));
	}

	/**
	 * 根据通道Id查询该条记录的首次的开通时间
	 * 
	 * @param routeId
	 *            路由编号
	 * @return 查询该路由编号下的信息
	 * @throws SnowException
	 */
	public GalRoutMng selectOpenTime(String routeId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		GalRoutMng rout = (GalRoutMng) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.galRoutMng.selectOpenTime",
				RqlParam.map().set("routeId", StringUtils.isEmpty(routeId) ? "" : routeId));
		return rout;
	}

	/**
	 * 在路由是 A000,A001,A002,A003,A004的下 找账户是A000,A001,A002,A003,A004的+交易类型+策略类型
	 * 下的通道
	 * 
	 * @param payTxnTypeId
	 *            交易类型编号
	 * @param acctTypeNo
	 *            账户类型编号
	 * @param ttsCode
	 *            策略类型编号
	 * @param page
	 *            分页查询
	 * @return 查询条件下的信息
	 * @throws SnowException
	 */
	public PageResult queryThreeTerm(String payTxnTypeId, String acctTypeNo, String ttsCode, Page page)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.galRoutMng.queryThreeTerm",
				RqlParam.map().set("payTxnTypeId", StringUtils.isEmpty(payTxnTypeId) ? "" : payTxnTypeId)
						.set("acctTypeNo", StringUtils.isEmpty(acctTypeNo) ? "" : acctTypeNo)
						.set("ttsCode", StringUtils.isEmpty(ttsCode) ? "" : ttsCode),
				page);
	}

	/**
	 * 在路由是 A005的账户下 找账户是A005的+交易类型+策略类型 下的通道
	 * 
	 * @param payTxnTypeId
	 *            交易类型编号
	 * @param acctTypeNo
	 *            账户类型编号
	 * @param ttsCode
	 *            策略类型编号
	 * @param page
	 *            分页查询
	 * @return 查询条件下的信息
	 * @throws SnowException
	 */
	public PageResult queryAliply(String payTxnTypeId, String acctTypeNo, String ttsCode, Page page)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.galRoutMng.queryAliply",
				RqlParam.map().set("payTxnTypeId", StringUtils.isEmpty(payTxnTypeId) ? "" : payTxnTypeId)
						.set("acctTypeNo", StringUtils.isEmpty(acctTypeNo) ? "" : acctTypeNo)
						.set("ttsCode", StringUtils.isEmpty(ttsCode) ? "" : ttsCode),
				page);
	}

	/**
	 * 在路由是 A006的账户下 找账户是A006的+交易类型+策略类型 下的通道
	 * 
	 * @param payTxnTypeId
	 *            交易类型编号
	 * @param acctTypeNo
	 *            账户类型编号
	 * @param ttsCode
	 *            策略类型编号
	 * @param page
	 *            分页查询
	 * @return 查询条件下的信息
	 * @throws SnowException
	 */
	public PageResult queryWeChat(String payTxnTypeId, String acctTypeNo, String ttsCode, Page page)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.galRoutMng.queryWeChat",
				RqlParam.map().set("payTxnTypeId", StringUtils.isEmpty(payTxnTypeId) ? "" : payTxnTypeId)
						.set("acctTypeNo", StringUtils.isEmpty(acctTypeNo) ? "" : acctTypeNo)
						.set("ttsCode", StringUtils.isEmpty(ttsCode) ? "" : ttsCode),
				page);
	}

	/**
	 * 分页查询通道基本信息表
	 * 
	 * @param page
	 *            分页查询
	 * @return 查询条件下的信息
	 * @throws SnowException
	 */
	public PageResult queryName(Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.galRoutMng.queryName", page);
	}

	/**
	 * 如果不是这几中类型的路由那么就返回空的通道
	 * 
	 * @param payTxnTypeId
	 *            交易类型编号
	 * @param acctTypeNo
	 *            账户类型编号
	 * @param ttsCode
	 *            策略类型编号
	 * @param page
	 *            分页查询
	 * @return 查询条件下的信息
	 * @throws SnowException
	 */
	public PageResult queryThree(String payTxnTypeId, String acctTypeNo, String ttsCode, Page page)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.galRoutMng.queryThree",
				RqlParam.map().set("payTxnTypeId", StringUtils.isEmpty(payTxnTypeId) ? "" : payTxnTypeId)
						.set("acctTypeNo", StringUtils.isEmpty(acctTypeNo) ? "" : acctTypeNo)
						.set("ttsCode", StringUtils.isEmpty(ttsCode) ? "" : ttsCode),
				page);
	}

}
