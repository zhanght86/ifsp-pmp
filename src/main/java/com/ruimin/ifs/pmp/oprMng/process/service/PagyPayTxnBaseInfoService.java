package com.ruimin.ifs.pmp.oprMng.process.service;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.Page;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.pmp.oprMng.process.bean.PagyPayTxnBaseInfo;
import com.ruimin.ifs.pmp.oprMng.process.bean.PagyPayTxnRel;

public class PagyPayTxnBaseInfoService {
	public static PagyPayTxnBaseInfoService getInstance() throws SnowException {
		return ContextUtil.getSingleService(PagyPayTxnBaseInfoService.class);
	}

	/**
	 * 接入支付交易基础信息全查询
	 * 
	 * @param qpayTxnCode
	 *            支付交易编号 [精确查询]
	 * @param qpayTxnResp
	 *            支付交易名称 [模糊查询]
	 * @param qpayTxnTypeId
	 *            交易类型编号 [精确查询]
	 * @param qpayTxnStat
	 *            交易状态 [精确查询]
	 * @param page
	 *            分页查询
	 * @return 满足条件下的所有信息
	 * @throws SnowException
	 */
	public PageResult queryAll(String qpayTxnCode, String qpayTxnResp, String qpayTxnTypeId, String qpayTxnStat,
			Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.oprMng.rql.pagyPayTxnBaseInfo.queryAll",
				RqlParam.map().set("qpayTxnCode", StringUtils.isEmpty(qpayTxnCode) ? "" : qpayTxnCode.trim())
						.set("qpayTxnResp", StringUtils.isEmpty(qpayTxnResp) ? "" : "%" + qpayTxnResp + "%")
						.set("qpayTxnTypeId", StringUtils.isEmpty(qpayTxnTypeId) ? "" : qpayTxnTypeId)
						.set("qpayTxnStat", StringUtils.isEmpty(qpayTxnStat) ? "" : qpayTxnStat),
				page);
	}

	/**
	 * 查询支付与通道交易关系表中所有数据
	 * 
	 * @param payTxnCode
	 *            支付交易编号
	 * @param page
	 *            分页查询
	 * @return 满足条件下通道交易关系表中的信息
	 * @throws SnowException
	 */
	public PageResult queryPagyPayTxnRel(String payTxnCode, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.oprMng.rql.pagyPayTxnBaseInfo.queryPagyPayTxnRel",
				RqlParam.map().set("payTxnCode", StringUtils.isEmpty(payTxnCode) ? "" : payTxnCode), page);
	}

	/**
	 * 接入支付交易基础信息表新增
	 * 
	 * @param base
	 *            接入支付交易基础信息表实体类对象
	 * @throws SnowException
	 */
	public void addType(PagyPayTxnBaseInfo base) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(base);
	}

	/**
	 * 查询该交易编号是否存在
	 * 
	 * @param payTxnCode
	 *            支付交易编号
	 * @return 该支付交易编号下对应的条数
	 * @throws SnowException
	 */
	public int queryPayTxnCode(String payTxnCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (int) dao.selectOne("com.ruimin.ifs.pmp.oprMng.rql.pagyPayTxnBaseInfo.queryPayTxnCode",
				RqlParam.map().set("payTxnCode", payTxnCode));
	}

	/**
	 * 接入支付交易基础信息表修改
	 * 
	 * @param payTxnCode
	 *            支付交易编号
	 * @param payTxnResp
	 *            支付交易名称
	 * @param crtTlr
	 *            创建柜员
	 * @param crtDateTime
	 *            创建日期时间
	 * @param lastUpdTlr
	 *            最后更新柜员
	 * @param lastUpdDateTime
	 *            最后更新日期时间
	 * @throws SnowException
	 */
	public void updateType(String payTxnCode, String payTxnResp, String crtTlr, String crtDateTime, String lastUpdTlr,
			String lastUpdDateTime) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.oprMng.rql.pagyPayTxnBaseInfo.updateType",
				RqlParam.map().set("payTxnCode", payTxnCode).set("payTxnResp", payTxnResp).set("crtTlr", crtTlr)
						.set("crtDateTime", crtDateTime).set("lastUpdTlr", lastUpdTlr)
						.set("lastUpdDateTime", lastUpdDateTime));
	}

	/**
	 * 接入支付交易基础信息表状态的修改
	 * 
	 * @param payTxnCode
	 *            支付交易编号
	 * @param payTxnStat
	 *            交易状态
	 * @param crtTlr
	 *            创建柜员
	 * @param crtDateTime
	 *            创建日期时间
	 * @throws SnowException
	 */
	public void updateStatus(String payTxnCode, String payTxnStat, String crtTlr, String crtDateTime)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.oprMng.rql.pagyPayTxnBaseInfo.updateStatus",
				RqlParam.map().set("payTxnCode", payTxnCode).set("payTxnStat", payTxnStat).set("crtTlr", crtTlr)
						.set("crtDateTime", crtDateTime));
	}

	/**
	 * 根据 通道交易编号+支付交易编号查重复
	 * 
	 * @param pagyTxnCode
	 *            通道交易编号
	 * @param payTxnCode
	 *            支付交易编号
	 * @throws SnowException
	 */
	public int query(String pagyTxnCode, String payTxnCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (int) dao.selectOne("com.ruimin.ifs.pmp.oprMng.rql.pagyPayTxnBaseInfo.query",
				RqlParam.map().set("pagyTxnCode", pagyTxnCode).set("payTxnCode", payTxnCode));
	}

	/**
	 * 像支付与通道交易关系表中插入数据
	 * 
	 * @param txnRel
	 *            支付与通道交易关系表的实体类对象
	 * @throws SnowException
	 */
	public void addPagyPayTxnRel(PagyPayTxnRel txnRel) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(txnRel);
		/*
		 * dao.executeUpdate(
		 * "com.ruimin.ifs.pmp.oprMng.rql.pagyPayTxnBaseInfo.addPagyPayTxnRel",
		 * RqlParam.map() .set("pagyTxnCode", pagyTxnCode) .set("payTxnCode",
		 * payTxnCode) .set("pagyNo", pagyNo));
		 */
	}

	/**
	 * 根据支付交易编号删除关系表中的数据
	 * 
	 * @param payTxnCode
	 *            支付交易编号
	 * @throws SnowException
	 */
	public void deleteData(String payTxnCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.oprMng.rql.pagyPayTxnBaseInfo.deleteData",
				RqlParam.map().set("payTxnCode", payTxnCode));
	}

	/**
	 * 修改支付与通道交易关系表中的数据
	 * 
	 * @param pagyTxnCode
	 *            通道交易编号
	 * @param payTxnCode
	 *            支付交易编号
	 * @param pagyNo
	 *            通道编号
	 * @throws SnowException
	 */
	public void updateData(String pagyTxnCode, String payTxnCode, String pagyNo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.oprMng.rql.pagyPayTxnBaseInfo.updateData",
				RqlParam.map().set("pagyTxnCode", pagyTxnCode).set("payTxnCode", payTxnCode).set("pagyNo", pagyNo));
	}
}
