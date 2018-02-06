/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.sysConf.process.service 
 * FileName: AcctTypeService.java
 * Author:   chenqilei
 * Date:     2016年7月13日 下午1:55:51
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月13日下午1:55:51                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.sysConf.process.service;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月13日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
public class AcctTypeService {
	public static AcctTypeService getInstance() throws SnowException {
		return ContextUtil.getSingleService(AcctTypeService.class);
	}

	public PageResult queryByAll(String acctTypeNo, String acctTypeName, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.sysConf.rql.acctType.queryByAll",
				RqlParam.map().set("acctTypeNo", acctTypeNo == null ? "" : acctTypeNo).set("acctTypeName",
						acctTypeName == null ? "" : acctTypeName),
				page);
	}

	public PageResult queryAcctByTxnTypeCode(String txnTypeCode, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.sysConf.rql.acctType.queryByCode",
				RqlParam.map().set("txnTypeCode", txnTypeCode == null ? "" : txnTypeCode), page);
	}

	public PageResult queryOnSelect(String txnTypeCode, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.sysConf.rql.acctType.queryOnSelect",
				RqlParam.map().set("txnTypeCode", txnTypeCode == null ? "" : txnTypeCode), page);
	}
}
