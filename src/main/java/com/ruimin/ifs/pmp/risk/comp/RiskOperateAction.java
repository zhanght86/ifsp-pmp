package com.ruimin.ifs.pmp.risk.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.pmp.risk.process.service.RiskOperateService;
@SnowDoc(desc = "风控操作")
@ActionResource
public class RiskOperateAction extends SnowAction {
	
	@Action
	@SnowDoc(desc = "查询风控操作")
	public PageResult queryOperateVo(QueryParamBean queryBean) throws SnowException{
		String actionBitmap = queryBean.getParameter("actionBitmap");
		return RiskOperateService.getInstance().queryOperateVo(actionBitmap,queryBean.getPage());
	}
}
