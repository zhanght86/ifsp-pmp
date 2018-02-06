/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.sysConf.comp 
 * FileName: AcctAndTrade.java
 * Author:   chenqilei
 * Date:     2016年7月13日 下午2:24:54
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月13日下午2:24:54                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.sysConf.comp;

import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.pmp.sysConf.process.service.AcctAndTradeService;
import com.ruimin.ifs.pmp.sysConf.process.service.AcctTypeService;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月13日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 * 查询关联表所有信息
 */
public class AcctAndTradeAction {
	@SnowDoc(desc = "查询关联表所有信息")
	public PageResult queryByAll(QueryParamBean queryBean) throws SnowException {
		return AcctAndTradeService.getInstance().query(queryBean.getPage());
	}

	public PageResult queryAcctByAll(QueryParamBean queryBean) throws SnowException {
		String txnTypeCode = queryBean.getParameter("txnTypeCode");
		return AcctTypeService.getInstance().queryOnSelect(txnTypeCode, queryBean.getPage());
	}
}
