package com.ruimin.ifs.pmp.mktActivity.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActvMethodSectionTmpService;
import com.ruimin.ifs.util.StringUtil;
@SnowDoc(desc = "活动方法分段信息临时表操作Action")
@ActionResource
public class actvMethodSectionTmpAction extends SnowAction {
	 @Action
	 @SnowDoc(desc = "查询列表")
	 public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		String methodNo = queryBean.getParameter("methodNo");
		String methodTp = queryBean.getParameter("methodTp");
		methodNo = StringUtil.isEmpty(methodNo)?"":methodNo;
		methodTp = StringUtil.isEmpty(methodTp)?"":methodTp;
		PageResult result = ActvMethodSectionTmpService.getInstance().queryList(methodNo, methodTp,queryBean.getPage());
		return result;
	}
	
}
