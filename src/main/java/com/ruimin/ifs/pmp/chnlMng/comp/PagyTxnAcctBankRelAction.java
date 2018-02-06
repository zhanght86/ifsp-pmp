/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.comp 
 * FileName: PagyTxnAcctBankRelAction.java
 * Author:   zqy
 * Date:     2016年7月27日 上午10:39:09
 * Description: 通道交易账户类型银行关系    
 * History: 
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zqy           2016年7月27日上午10:39:09                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.comp;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.chnlMng.process.service.PagyTxnAcctBankRelService;
import com.ruimin.ifs.pmp.sysConf.process.bean.AcctType;

/**
 * 名称：通道交易账户类型银行关系 功能：通道交易账户类型银行关系 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月27日 <br>
 * 作者：zqy <br>
 * 说明：<br>
 */
@SnowDoc(desc = "通道交易账户类型银行关系操作Action")
@ActionResource
public class PagyTxnAcctBankRelAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询通道交易账户类型银行关系")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		// 获取查询参数
		String uptRel = queryBean.getParameter("uptRel");// 通道交易修改时显示数据
		if (uptRel != null) {
			if (uptRel.equals("NoData")) {
				uptRel = "";
			}
			return PagyTxnAcctBankRelService.getInstance().queryRelList(uptRel, queryBean.getPage());
		}
		String pagyTxnCode = queryBean.getParameter("qpagyTxnCode");// 通道编号
		// 分页查询银行信息
		return PagyTxnAcctBankRelService.getInstance().queryList(pagyTxnCode, queryBean.getPage());

	}

	@Action
	@SnowDoc(desc = "查询银行列表")
	public PageResult queryBankList(QueryParamBean queryBean) throws SnowException {
		// 获取查询参数
		String pagyTxnCode = queryBean.getParameter("qpagyTxnCode");// 通道编号
		String acctTypeNo = queryBean.getParameter("qacctTypeNo");// 通道编号
		// 分页查询银行信息
		return PagyTxnAcctBankRelService.getInstance().queryBankList(pagyTxnCode, acctTypeNo, queryBean.getPage());

	}

	/**
	 * 根据账户代码获取名称method
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 */
	public static String getAcctTypeName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		if (StringUtils.isNotBlank(key)) {
			AcctType acctType = PagyTxnAcctBankRelService.getInstance().queryAcctTypeByNo(key);
			if (acctType != null) {
				return acctType.getAcctTypeName();
			}
		}
		return key;
	}
}
