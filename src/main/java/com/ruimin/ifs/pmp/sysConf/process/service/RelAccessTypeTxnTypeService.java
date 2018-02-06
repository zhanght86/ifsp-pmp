/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.sysConf.process.service 
 * FileName: RelAccessTypeTxnTypeService.java
 * Author:   zrx
 * Date:     2016年7月28日 下午3:20:08
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月28日下午3:20:08                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.sysConf.process.service;

import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.sysConf.process.bean.RelAccessTypeTxnType;

import java.util.List;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月28日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
@SnowDoc(desc = "接入方式与交易类型信息操作Service")
public class RelAccessTypeTxnTypeService {
	public static RelAccessTypeTxnTypeService getInstance() throws SnowException {
		return ContextUtil.getSingleService(RelAccessTypeTxnTypeService.class);
	}

	// 查询交易类型信息
	public List<Object> queryList(String accessTypeCode) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.sysConf.rql.relAccessTypeTxnType.query",
				RqlParam.map().set("accessTypeCode", StringUtils.isEmpty(accessTypeCode) ? "" : accessTypeCode));
	}

	/**
	 * 新增接入方式与交易类型关联表信息
	 * 
	 * @throws SnowException
	 */
	public void addRelAccessTxn(RelAccessTypeTxnType relAccessTypeTxnType) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(relAccessTypeTxnType);

	}

	/**
	 * 修改接入方式与交易类型关联表信息
	 * 
	 * @throws SnowException
	 */
	public void updateRelAccessTxn(RelAccessTypeTxnType relAccessTypeTxnType) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(relAccessTypeTxnType);

	}

	/**
	 * 删除接入方式与交易类型关联表信息
	 * 
	 * @throws SnowException
	 */
	public void deleteRelAccessTxn(RelAccessTypeTxnType relAccessTypeTxnType) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(relAccessTypeTxnType);
	}

	/**
	 * 增加或修改记录前查询接入方式与交易类型关联表信息是否已经有相关的账户交易类型存在
	 * 
	 * @throws SnowException
	 */
	public List<Object> queryByTxnTypeCode(String accessTypeCode, String txnTypeCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.sysConf.rql.relAccessTypeTxnType.queryByTxnTypeCode",
				RqlParam.map().set("accessTypeCode", accessTypeCode).set("txnTypeCode", txnTypeCode));
	}

}
