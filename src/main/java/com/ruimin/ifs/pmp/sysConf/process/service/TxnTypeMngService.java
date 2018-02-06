/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.accessTypeMng.process.service 
 * FileName: TxnTypeMngService.java
 * Author:   zrx
 * Date:     2016年7月14日 上午10:18:26
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月14日上午10:18:26                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.sysConf.process.service;

import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.Page;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.sysConf.process.bean.TxnTypeInfo;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月14日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
@SnowDoc(desc = "交易类型信息操作Service")
public class TxnTypeMngService extends SnowService {
	public static TxnTypeMngService getInstance() throws SnowException {
		return ContextUtil.getSingleService(TxnTypeMngService.class);
	}

	// 查询交易类型信息
	public PageResult queryList(String txnTypeCode, String txnTypeName, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.sysConf.rql.txnTypeInfo.queryTxnTypeInfo",
				RqlParam.map().set("txnTypeCode", StringUtils.isEmpty(txnTypeCode) ? "" : txnTypeCode)
						.set("txnTypeName", StringUtils.isEmpty(txnTypeName) ? "" : "%" + txnTypeName + "%"),
				page);
	}

	public PageResult queryTxnByAccessTypeCode(String accessTypeCode, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.sysConf.rql.txnTypeInfo.queryByCode",
				RqlParam.map().set("accessTypeCode", accessTypeCode == null ? "" : accessTypeCode), page);
	}

	public TxnTypeInfo getTxnTypeName(String txnTypeCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.select(TxnTypeInfo.class, txnTypeCode);
	}

}
