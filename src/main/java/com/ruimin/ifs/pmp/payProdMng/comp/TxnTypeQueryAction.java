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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.payProdMng.process.bean.AccessAndTxnType;
import com.ruimin.ifs.pmp.payProdMng.process.bean.PbsProdInfo;
import com.ruimin.ifs.pmp.payProdMng.process.service.TxnTypeQueryService;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

@SnowDoc(desc = "指定接入方式下的交易类型")
@ActionResource
public class TxnTypeQueryAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询指定接入方式下的交易类型")
	public PageResult queryTxnTypeInfo(QueryParamBean queryBean) throws SnowException {
		// 获取接入方式编号
	    
		String qaccessTypeCode = queryBean.getParameter("qaccessTypeCode");
		return TxnTypeQueryService.getInstance().queryTxnTypeInfo(qaccessTypeCode, queryBean.getPage());
	}

	@Action
	@SnowDoc(desc = "查询商品编号下的交易类型")
	public List<AccessAndTxnType> queryTxnTypeInfoByProdId(QueryParamBean queryBean) throws SnowException {
		// 获取接入方式编号
		String qprodId = queryBean.getParameter("qprodId");
		return TxnTypeQueryService.getInstance().queryTxnTypeInfoByProdId(qprodId, queryBean.getPage());
	}

	//2017-04-10修改
	@Action
	@SnowDoc(desc = "根绝产品编号，查询交易类型，账户类型")
	public List<PbsProdInfo> query(QueryParamBean queryBean) throws SnowException {
		String qprodId = queryBean.getParameter("qprodId");
		List<PbsProdInfo> result = new ArrayList<>();
		List<Object> r = TxnTypeQueryService.getInstance().query(qprodId);
		for (Object object : r) {
			result.add((PbsProdInfo)object);
		}
		return result;
	}
//	public PageResult query(QueryParamBean queryBean) throws SnowException {
//		String qprodId = queryBean.getParameter("qprodId");
//		return TxnTypeQueryService.getInstance().query(qprodId, queryBean.getPage());
//	}

	/**
	 * 查询账户类型名称
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String getAcctTypeName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		StringBuffer strBuf = new StringBuffer();
		if (StringUtil.isEmpty(key)) {
			return strBuf.toString();
		}
		List<Object> prodList = TxnTypeQueryService.getInstance().getAcctTypeName(key);
		
		if (prodList == null || prodList.size() == 0) {
			return strBuf.toString();
		}
		for (Object prodObj : prodList) {
			PbsProdInfo prod = (PbsProdInfo) prodObj;
			strBuf.append(prod.getAcctTypeCodeName()).append(",");
		}
		return strBuf.toString().substring(0, strBuf.toString().length() - 1);
	}

	/**
	 * 查询交易类型名称
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String getTxnTypeName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		StringBuffer strBuf = new StringBuffer();
		if (StringUtil.isEmpty(key)) {
			return strBuf.toString();
		}
		List<Object> prodList = TxnTypeQueryService.getInstance().getTxnTypeName(key);

		if (prodList == null || prodList.size() == 0) {
			return strBuf.toString();
		}
		for (Object prodObj : prodList) {
			PbsProdInfo prod = (PbsProdInfo) prodObj;
			strBuf.append(prod.getTxnTypeCodeName()).append(",");
		}
		return strBuf.toString().substring(0, strBuf.toString().length() - 1);
	}

}
