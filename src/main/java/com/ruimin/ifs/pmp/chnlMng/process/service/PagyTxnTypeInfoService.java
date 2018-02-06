/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.chnlMng.process.service 
 * FileName: ChannelInfoService.java
 * Author:   zrx
 * Date:     2016年7月21日 上午10:51:06
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月21日上午10:51:06                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;

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
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagyTxnTypeInfoVO;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月21日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
@SnowDoc(desc = "接入支付通道交易类型Service")
@Service
public class PagyTxnTypeInfoService extends SnowService {
	public static PagyTxnTypeInfoService getInstance() throws SnowException {
		return ContextUtil.getSingleService(PagyTxnTypeInfoService.class);
	}

	/**
	 * 分页查询接入支付通道交易类型列表信息
	 * 
	 * @param qpayTxnTypeId
	 *            交易类型编号 [精确查询]
	 * @param qpayTxnTypeName
	 *            交易类型名称 [模糊查询]
	 * @param qpayTxnTypeDesc
	 *            交易类型描述 [模糊查询]
	 * @param page
	 *            分页查询
	 * @return 满足条件下的接入支付通道交易类型信息
	 */
	public PageResult queryAll(String qpayTxnTypeId, String qpayTxnTypeName, String qpayTxnTypeDesc, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.pagyTxnTypeInfo.queryList",
				RqlParam.map().set("qpayTxnTypeId", StringUtils.isEmpty(qpayTxnTypeId) ? "" : qpayTxnTypeId.trim())
						.set("qpayTxnTypeName", StringUtils.isEmpty(qpayTxnTypeName) ? ""
								: "%" + qpayTxnTypeName + "%")
						.set("qpayTxnTypeDesc", StringUtils.isEmpty(qpayTxnTypeDesc) ? ""
								: "%" + qpayTxnTypeDesc + "%"),
				page);
	}

	/**
	 * 查询最大序列号
	 * 
	 * @return 返回最大序列号
	 * @throws SnowException
	 */
	public String selectReqMax() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.pagyTxnTypeInfo.selectReqMax");
	}

	/**
	 * 接入支付通道交易新增
	 * 
	 * @param pagy
	 *            接入支付通道交易类型实体类的对象
	 * @throws SnowException
	 */
	public void addType(PagyTxnTypeInfoVO pagy) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(pagy);
	}

	/**
	 * 查询交易类型编号是否存在
	 * 
	 * @param payTxnTypeId
	 *            交易类型编号
	 * @return 该交易类型编号下对应的条数
	 * @throws SnowException
	 */
	public int queryPayTxnTypeId(String payTxnTypeId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (int) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.pagyTxnTypeInfo.queryPayTxnTypeId",
				RqlParam.map().set("payTxnTypeId", payTxnTypeId));
	}

	/**
	 * 接入支付通道交易修改
	 * 
	 * @param payTxnTypeId
	 *            交易类型编号
	 * @param payTxnTypeName
	 *            交易类型名称
	 * @param payTxnTypeDesc
	 *            交易类型描述
	 * @param crtTlr
	 *            创建柜员
	 * @param crtDateTime
	 *            创建日期时间
	 * @param lastUpdTlr
	 *            最后更新柜员
	 * @param lastCrtDateTime
	 *            最后更新日期时间
	 * @throws SnowException
	 */
	public void updateType(String payTxnTypeId, String payTxnTypeName, String payTxnTypeDesc, String crtTlr,
			String crtDateTime, String lastUpdTlr, String lastCrtDateTime) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.chnlMng.rql.pagyTxnTypeInfo.updateType",
				RqlParam.map().set("payTxnTypeId", payTxnTypeId).set("payTxnTypeName", payTxnTypeName)
						.set("payTxnTypeDesc", payTxnTypeDesc).set("crtTlr", crtTlr).set("crtDateTime", crtDateTime)
						.set("lastUpdTlr", lastUpdTlr).set("lastCrtDateTime", lastCrtDateTime));
	}

	/**
	 * 根据接入通道交易类型编号获取名称
	 * 
	 * @param chnlIds
	 *            单个接入通道交易编号或多个以","分割的通道交易类型编号
	 * @return
	 * @throws SnowException
	 */
	public List<Object> getPagyTxnTypeNames(String pagyTxnTypeIds) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String[] pagyTxnTypeIdArray = pagyTxnTypeIds.split(",");

		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.pagyTxnTypeInfo.getNameByTypeIds",
				RqlParam.map().set("pagyTxnTypeIdArray", pagyTxnTypeIdArray));
	}

	/**
	 * 接入支付交易类型查询，下拉选，渠道权限使用
	 * 
	 * @param page
	 *            分页查询
	 * @return
	 */
	public PageResult queryTxnTpyeInfo(Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.pagyTxnTypeInfo.queryTxnTpyeInfo", page);
	}

	/**
	 * 获取交易类型名字
	 * 
	 * @param prodIds
	 *            交易类型编号
	 * @return 交易类型名称
	 * @throws SnowException
	 */
	public List<Object> getPayTxnTypeIdName(String payTxnTypeIds) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String[] payTxnTypeId = payTxnTypeIds.split(",");

		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.pagyTxnTypeInfo.getPayTxnTypeIdName",
				RqlParam.map().set("payTxnTypeIdArray", payTxnTypeId));
	}
}
