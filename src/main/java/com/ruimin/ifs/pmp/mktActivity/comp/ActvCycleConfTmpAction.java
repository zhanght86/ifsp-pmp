package com.ruimin.ifs.pmp.mktActivity.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActvCycleConfTmpService;
import com.ruimin.ifs.util.StringUtil;
@SnowDoc(desc = "活动信息临时表操作Action")
@ActionResource
public class ActvCycleConfTmpAction extends SnowAction {
	@Action
	 @SnowDoc(desc = "查询列表")
	 public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		String methodNo = queryBean.getParameter("activeNo");
		methodNo = StringUtil.isEmpty(methodNo)?"":methodNo;
		PageResult result = ActvCycleConfTmpService.getInstance().queryAll(methodNo, queryBean.getPage());
		return result;
	}
}
