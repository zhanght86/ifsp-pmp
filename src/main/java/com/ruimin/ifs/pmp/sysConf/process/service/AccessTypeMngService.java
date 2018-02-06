/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.accessTypeMng.process.service 
 * FileName: AccessTypeMngService.java
 * Author:   zrx
 * Date:     2016年7月8日 下午3:18:23
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月8日下午3:18:23                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.sysConf.process.service;

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
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.sysConf.process.bean.AccessTypeInfo;
import com.ruimin.ifs.pmp.sysConf.process.bean.RelAccessTypeTxnType;
import com.ruimin.ifs.pmp.sysConf.process.bean.TxnTypeInfo;

import java.util.List;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月8日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */

@SnowDoc(desc = "接入方式管理操作Service")
@Service
public class AccessTypeMngService extends SnowService {
	public static AccessTypeMngService getInstance() throws SnowException {
		return ContextUtil.getSingleService(AccessTypeMngService.class);
	}

	/**
	 * 分页查询接入方式信息
	 * 
	 * @param accessTypeCode
	 *            接入方式编号
	 * @param accessTypeName
	 *            接入方式名称
	 * @param page
	 *            分页对象
	 */
	public PageResult queryList(String accessTypeCode, String accessTypeName, String dataState, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.sysConf.rql.accessTypeInfo.queryList",
				RqlParam.map().set("accessTypeCode", StringUtils.isEmpty(accessTypeCode) ? "" : accessTypeCode)
						.set("dataState", StringUtils.isEmpty(dataState) ? "" : dataState).set("accessTypeName",
								StringUtils.isEmpty(accessTypeName) ? "" : "%" + accessTypeName + "%"),
				page);
	}

	/**
	 * 添加接入方式
	 * 
	 * @param accessTypeInfo
	 * @throws SnowException
	 */
	public void addAccessTypeInfo(AccessTypeInfo accessTypeInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(accessTypeInfo);
	}

	/**
	 * 修改接入方式信息
	 * 
	 * @param accessTypeInfo
	 * @throws SnowException
	 */
	public void updateAccessTypeInfo(AccessTypeInfo accessTypeInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(accessTypeInfo);
	}

	/**
	 * 修改接入方式（00-启用；99-停用）
	 * 
	 * @throws SnowException
	 * @parameter accessTypeInfo
	 */
	public void deleteAccessTypeInfo(AccessTypeInfo accessTypeInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String dataState = accessTypeInfo.getDataState();
		String lastUpdTlr = accessTypeInfo.getLastUpdTlr();
		String lastUpdDate = accessTypeInfo.getLastUpdDateTime();
		String accessTypeCode = accessTypeInfo.getAccessTypeCode();
		String sql = "update pbs_access_type_info set data_state= '" + dataState + "',last_upd_tlr= '" + lastUpdTlr
				+ "',last_upd_date_time= '" + lastUpdDate + "' where access_type_code= '" + accessTypeCode + "'";
		dao.executeUpdateSql(sql);
	}

	/**
	 * 向接入方式与交易类型关联表中插入数据
	 * 
	 * @param list
	 * @throws SnowException
	 */
	public void insertRelAccessTypeTxnType(List<RelAccessTypeTxnType> list) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(list);
	}

	public void insertRelAccessTypeTxnType1(RelAccessTypeTxnType relAccessTypeTxnType) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(relAccessTypeTxnType);
	}

	/**
	 * 根据接入方式编号删除接入方式与交易类型关联表中的数据
	 * 
	 * @param accessTypeCode
	 * @return
	 * @throws SnowException
	 */
	public int deleteRelAccessTypeTxnType(String accessTypeCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.executeUpdate("com.ruimin.ifs.pmp.sysConf.rql.accessTypeInfo.deleteRelAccessTxnByCode",
				RqlParam.map().set("accessTypeCode", accessTypeCode == null ? "" : accessTypeCode));
	}

	/**
	 * 修改接入方式与交易类型关联表中的数据状态（00-启用；99-停用）
	 * 
	 * @throws SnowException
	 * @parameter rat
	 */
	public void deleteRelAccessTypeTxnType(RelAccessTypeTxnType rat) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String accessTypeCode = rat.getAccessTypeCode();
		String dataState = rat.getDataState();
		String sql = "update pbs_rel_access_type_txn_type set data_state= '" + dataState + "' where access_type_code= '"
				+ accessTypeCode + "'";
		dao.executeUpdateSql(sql);
	}

	/**
	 * 
	 * @return 查询接入方式表的最大序列号
	 * @throws SnowException
	 */
	public String queryMaxSeq() {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.sysConf.rql.accessTypeInfo.queryMaxSeq");
	}

	/**
	 * 
	 * @return 查询接入方式与交易类型关联表的最大序列号
	 * @throws SnowException
	 */
	public String queryMaxSeq1() {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.sysConf.rql.accessTypeInfo.queryMaxSeq1");
	}

	// 交易类型下拉查询
	public List<TxnTypeInfo> querySelect() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.queryAll("select txn_type_code,txn_type_name from pbs_txn_type_info where  DATA_STATE='00' ",
				TxnTypeInfo.class);
	}

}
