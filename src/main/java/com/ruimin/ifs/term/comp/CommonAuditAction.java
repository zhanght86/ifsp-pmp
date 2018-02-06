package com.ruimin.ifs.term.comp;

import java.util.HashMap;
import java.util.Map;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.term.process.bean.PaypTermInf_temp;
import com.ruimin.ifs.term.process.service.CommonAuditService;

/**
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司 Author: chenggx Date:
 * 2016年10月24日下午4:28:45 History: //修改记录 Version: 0.1 Desc: 审核的action
 */
public class CommonAuditAction extends SnowAction {
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "审核通过")
	public synchronized Map<String, Object> pass(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取商户信息
		UpdateRequestBean reqBean = updateMap.get("paypTermInf");
		PaypTermInf_temp paypTermInf = new PaypTermInf_temp();
		if (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), paypTermInf);
		}
		/* 获取前台传过来的auditView字段 */
		String auditView = reqBean.getParameter("auditView");
		// 封装返回到页面的提示标志
		Map<String, Object> map = new HashMap<String, Object>();
		// 调用审核通用的service入口
		CommonAuditService.getInstance().passAuditEntry(paypTermInf, auditView, map);

		return map;
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "审核拒绝")
	public void refuse(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取商户信息
		UpdateRequestBean reqBean = updateMap.get("paypTermInf");
		PaypTermInf_temp paypTermInf = new PaypTermInf_temp();
		if (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), paypTermInf);
		}
		/* 获取前台传过来的auditView字段 */
		String auditView = reqBean.getParameter("auditView");
		// 调用审核通用的service入口
		CommonAuditService.getInstance().refuseAuditEntry(paypTermInf, auditView);

	}
}
