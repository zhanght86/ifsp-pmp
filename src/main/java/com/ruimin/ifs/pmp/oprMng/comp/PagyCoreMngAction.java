package com.ruimin.ifs.pmp.oprMng.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.pmp.oprMng.process.service.PagyCoreMngService;

@ActionResource
@SnowDoc(desc = "通道核心配置管理")
public class PagyCoreMngAction {
	/**
	 * 查询【通道核心配置管理】
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		/** 查询条件 */
		String qpagyNo = queryBean.getParameter("qpagyNo");// 通道编号
		String qpagyTxnCode = queryBean.getParameter("qpagyTxnCode");// 通道交易编号
		String qacctTypeNo = queryBean.getParameter("qacctTypeNo");// 账户类型编号
		String qmainMchtNo = queryBean.getParameter("qmainMchtNo");// 主商户编号
		String qpayTxnCode = queryBean.getParameter("qpayTxnCode");// 接入交易编号

		/** 返回查询结果 */
		return PagyCoreMngService.getInstance().queryAll(qpagyNo, qpagyTxnCode, qacctTypeNo, qmainMchtNo, qpayTxnCode,
				queryBean.getPage());
	}
}
