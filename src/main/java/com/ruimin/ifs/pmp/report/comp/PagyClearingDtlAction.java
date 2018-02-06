package com.ruimin.ifs.pmp.report.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.pmp.report.process.service.PagyClearingDtlService;

@ActionResource
@SnowDoc(desc = "通道清算明细信息action")
public class PagyClearingDtlAction extends SnowAction {
	/**
	 * 
	 * 功能描述: 通道清算汇总信息分页查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Action
	@SnowDoc(desc = "通道清算明细信息分页查询")
	public PageResult pageQuery(QueryParamBean queryBean) throws SnowException {
		String qSettleDate = queryBean.getParameter("qSettleDate");
		String qPagyNo = queryBean.getParameter("qPagyNo");
		//String qPagyMainMchtNo = queryBean.getParameter("qPagyMainMchtNo");
		return PagyClearingDtlService.getInstance().pageQuery(qSettleDate, qPagyNo, queryBean.getPage());

	}
	
}
