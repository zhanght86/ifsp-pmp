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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.pmp.chnlMng.process.service.PagyCoreTxnCountService;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月29日 <br>
 * 作者：bqk <br>
 * 说明：<br>
 */
@SnowDoc(desc = "通道交易统计Action")
@ActionResource
public class PagyTxnCountAction extends SnowAction {
	@Action
	@SnowDoc(desc = "通道交易统计")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException, ParseException {
		// 获取查询参数
		String qTxnDateStart = queryBean.getParameter("qTxnDateStart");// 交易日期起始
		String qTxnDateEnd = queryBean.getParameter("qTxnDateEnd");// 交易日期截至
		String qPagyNo = queryBean.getParameter("qPagyNo");// 通道编号
		// ***********对查询条件进行处理************//
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String qTxnDateStartReal = qTxnDateStart;
		String qTxnDateEndReal = qTxnDateEnd;

		// *********1当输入开始时间非空
		if (qTxnDateStart != null && qTxnDateStart.length() != 0) {
			// 如果截止日期为空，这设定截止日期为当前日期的前一天
			if (qTxnDateEnd == null || qTxnDateEnd.length() == 0) {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_MONTH, -1); // 取当前日期的前一天
				qTxnDateEndReal = dateFormat.format(cal.getTime());
			}
		}
		// *********2当输入开始时间为空
		if (qTxnDateStart == null || qTxnDateStart.length() == 0) {
			// 如果截止日期也为空
			if (qTxnDateEnd == null || qTxnDateEnd.length() == 0) {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_MONTH, -1); // 取当前日期的前一天
				qTxnDateEndReal = dateFormat.format(cal.getTime());
			}
		}

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("qTxnDateStart", StringUtil.isBlank(qTxnDateStartReal) ? "" : qTxnDateStartReal.trim());
		paramMap.put("qTxnDateEnd", StringUtil.isBlank(qTxnDateEndReal) ? "" : qTxnDateEndReal.trim());
		paramMap.put("qPagyNo", StringUtil.isBlank(qPagyNo) ? "" : qPagyNo);
		// 通道交易统计
		return PagyCoreTxnCountService.getInstance().queryList(paramMap, queryBean.getPage());

	}

}
