/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.accessTypeMng.comp 
 * FileName: TxnTypeMngAction.java
 * Author:   zrx
 * Date:     2016年7月14日 上午10:00:50
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月14日上午10:00:50                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.payProdMng.comp;

import java.util.List;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.pmp.payProdMng.process.bean.TxnTypeAndAccount;
import com.ruimin.ifs.pmp.payProdMng.process.service.AccountTypeQueryService;
import com.ruimin.ifs.pmp.sysConf.process.bean.AccountType;

@SnowDoc(desc = "指定交易方式下的账户类型")
@ActionResource
public class AccountTypeQueryAction extends SnowAction {
	@Action
	@SnowDoc(desc = "指定交易方式下的账户类型")
	public PageResult queryAccountType(QueryParamBean queryBean) throws SnowException {
		// 获取接入方式编号
		String qtxnTypeCode = queryBean.getParameter("qtxnTypeCode");
		return AccountTypeQueryService.getInstance().queryAccountTypeInfo(qtxnTypeCode, queryBean.getPage());
	}

	@Action
	@SnowDoc(desc = "根据产品编号查询账户类型")
	public List<TxnTypeAndAccount> queryAccountTypeByProdId(QueryParamBean queryBean) throws SnowException {
		// 获取接入方式编号
		String qprodId = queryBean.getParameter("qprodId");
		return AccountTypeQueryService.getInstance().queryAccountTypeByProdId(qprodId, queryBean.getPage());
	}

}
