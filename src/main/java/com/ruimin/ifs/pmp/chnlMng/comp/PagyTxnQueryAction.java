/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.chnlMng.comp 
 * FileName: ChannelInfoAction.java
 * Author:   zrx
 * Date:     2016年7月21日 上午10:39:09
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月21日上午10:39:09                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.comp;

import java.util.HashMap;
import java.util.Map;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.pmp.chnlMng.process.service.PagyCoreTxnRecordService;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月21日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
@SnowDoc(desc = "通道交易查询Action")
@ActionResource
public class PagyTxnQueryAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询通道交易列表")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		// 获取查询参数
		String qTxnDateStart = queryBean.getParameter("qTxnDateStart");// 交易日期起始
		String qTxnDateEnd = queryBean.getParameter("qTxnDateEnd");// 交易日期截至
		String qChnlNo = queryBean.getParameter("qChnlNo");// 渠道编号
		String qPagyNo = queryBean.getParameter("qPagyNo");// 通道编号
		String qTxnType = queryBean.getParameter("qTxnType");// 交易类型
		String qTxnState = queryBean.getParameter("qTxnState");// 交易状态
		String qChnlTxnSsn = queryBean.getParameter("qChnlTxnSsn");// 渠道交易流水号
		String qPagyCoreTxnSsn = queryBean.getParameter("qPagyCoreTxnSsn");// 通道核心流水号
		String qThirdPagyTxnSsn = queryBean.getParameter("qThirdPagyTxnSsn");// 支付通道交易流水号
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("qTxnDateStart", StringUtil.isBlank(qTxnDateStart) ? "" : qTxnDateStart);
		paramMap.put("qTxnDateEnd", StringUtil.isBlank(qTxnDateEnd) ? "" : qTxnDateEnd);
		paramMap.put("qChnlNo", StringUtil.isBlank(qChnlNo) ? "" : qChnlNo);
		paramMap.put("qPagyNo", StringUtil.isBlank(qPagyNo) ? "" : qPagyNo);
		paramMap.put("qTxnType", StringUtil.isBlank(qTxnType) ? "" : qTxnType);
		paramMap.put("qTxnState", StringUtil.isBlank(qTxnState) ? "" : qTxnState);
		paramMap.put("qChnlTxnSsn", StringUtil.isBlank(qChnlTxnSsn) ? "" : "%" + qChnlTxnSsn + "%");
		paramMap.put("qPagyCoreTxnSsn", StringUtil.isBlank(qPagyCoreTxnSsn) ? "" : "%" + qPagyCoreTxnSsn + "%");
		paramMap.put("qThirdPagyTxnSsn", StringUtil.isBlank(qThirdPagyTxnSsn) ? "" : "%" + qThirdPagyTxnSsn + "%");
		//当前操作员所属机构编号
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		paramMap.put("currentBrCode",sessionUserInfo.getBrCode());
		// 分页查询渠道信息
		return PagyCoreTxnRecordService.getInstance().queryList(paramMap, queryBean.getPage());

	}
}
