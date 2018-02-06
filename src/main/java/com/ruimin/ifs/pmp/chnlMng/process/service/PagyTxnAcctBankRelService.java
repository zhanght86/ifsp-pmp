/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.comp 
 * FileName: PagyTxnAcctBankRelService.java
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
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.sysConf.process.bean.AcctType;

/**
 * 名称：通道信息管理 功能：通道信息管理 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月27日 <br>
 * 作者：zqy <br>
 * 说明：<br>
 */
@SnowDoc(desc = "通道信息管理操作Service")
@Service
public class PagyTxnAcctBankRelService extends SnowService {
	public static PagyTxnAcctBankRelService getInstance() throws SnowException {
		return ContextUtil.getSingleService(PagyTxnAcctBankRelService.class);
	}

	/**
	 * 分页查询通道信息
	 */
	public PageResult queryList(String pagyTxnCode, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.pagyTxnAcctBankRel.queryList",
				RqlParam.map().set("pagyTxnCode", StringUtils.isEmpty(pagyTxnCode) ? "" : "%" + pagyTxnCode + "%"),
				page);

	}

	/**
	 * 通道交易修改时显示数据
	 */
	public PageResult queryRelList(String pagyTxnCode, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.pagyTxnAcctBankRel.queryRelList",
				RqlParam.map().set("pagyTxnCode", StringUtils.isEmpty(pagyTxnCode) ? "" : pagyTxnCode), page);

	}

	/**
	 * 分页查询通道信息
	 */
	public PageResult queryBankList(String pagyTxnCode, String acctTypeNo, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.pagyTxnAcctBankRel.queryBankList",
				RqlParam.map().set("pagyTxnCode", StringUtils.isEmpty(pagyTxnCode) ? "" : "%" + pagyTxnCode + "%")
						.set("acctTypeNo", StringUtils.isEmpty(acctTypeNo) ? "" : "%" + acctTypeNo + "%"),
				page);

	}

	public AcctType queryAcctTypeByNo(String key) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.select(AcctType.class, key);
	}
}
