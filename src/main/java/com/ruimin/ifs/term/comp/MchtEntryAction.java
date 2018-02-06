/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.term.comp 
 * FileName: MchtEntryAction.java
 * Author:   wangyl
 * Date:     2016年8月12日 下午5:15:19
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   wangyl           2016年8月12日下午5:15:19                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.term.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.term.process.service.PaypTermInfoService;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月12日 <br>
 * 作者：wangyl <br>
 * 说明：<br>
 */
public class MchtEntryAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询商户信息信息")
	public PageResult queryAllMchtChnl(QueryParamBean queryBean) throws SnowException {
		String mchtId = queryBean.getParameter("mchtId2");
		return PaypTermInfoService.getInstance().listAllMC(mchtId, queryBean.getPage());
	}

	// ttt 根据商户号来查找商户名称
	public static String getMchtCnAbbr(FieldBean bean, String key, ServletRequest request) throws SnowException {

		return "金汉斯aaaa";
	}
}
