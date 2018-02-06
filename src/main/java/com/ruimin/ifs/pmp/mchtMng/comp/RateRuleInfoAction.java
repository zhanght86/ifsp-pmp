/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.mchtMng.comp 
 * FileName: RateRuleInfoAction.java
 * Author:   zrx
 * Date:     2016年7月18日 下午11:31:06
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月18日下午11:31:06                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mchtMng.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.pmp.mchtMng.process.service.RateRuleInfoService;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月18日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
@SnowDoc(desc = "费率规则表操作Action")
@ActionResource
public class RateRuleInfoAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询列表")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		String rateId = queryBean.getParameter("rateId");
		String chargeType = queryBean.getParameter("chargeType");
		rateId = StringUtil.isEmpty(rateId) ? "" : rateId;
		chargeType = StringUtil.isEmpty(chargeType) ? "" : chargeType;
		PageResult result = RateRuleInfoService.getInstance().queryList(rateId, chargeType, queryBean.getPage());
		return result;
	}

}
