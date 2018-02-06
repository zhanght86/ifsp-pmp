/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.sysConf.comp 
 * FileName: AcctTypeAction.java
 * Author:   chenqilei
 * Date:     2016年7月13日 下午1:44:56
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月13日下午1:44:56                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.sysConf.comp;

import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;

import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.pmp.sysConf.process.service.AcctTypeService;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月13日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 * 查询账户表的所有信息
 */
public class AcctTypeAction {
	@SnowDoc(desc = "查询账户表所有信息")
	public PageResult queryAcctByAll(QueryParamBean queryBean) throws SnowException {
		// 获取账户类型编号
		String acctTypeNo = queryBean.getParameter("qacctTypeNo");
		// 获取账户类型名称
		String acctTypeName = queryBean.getParameter("qacctTypeName");
		// 查询标志
		String param = queryBean.getParameter("param");
		// 获取交易类型编号
		String txnTypeCode = queryBean.getParameter("txnTypeCode");
		if ("detail".equals(param)) {
			return AcctTypeService.getInstance().queryAcctByTxnTypeCode(txnTypeCode, queryBean.getPage());
		} else {
			return AcctTypeService.getInstance().queryByAll(acctTypeNo, acctTypeName, queryBean.getPage());
		}

	}

}
