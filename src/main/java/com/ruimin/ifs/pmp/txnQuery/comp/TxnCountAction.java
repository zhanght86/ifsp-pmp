package com.ruimin.ifs.pmp.txnQuery.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.txnQuery.process.service.txnCountService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;

@SnowDoc(desc = "支付业务交易统计")
@ActionResource
public class TxnCountAction extends SnowAction {
	@Action
	@SnowDoc(desc = "支付业务交易统计")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException, ParseException {
		// 接收查询参数-交易日期起始
		String qTxnDateStart = queryBean.getParameter("qTxnDateStart");
		// 接收查询参数-交易日期截至
		String qTxnDateEnd = queryBean.getParameter("qTxnDateEnd");
		// 接收查询参数-接入方式
		String qAccessType = queryBean.getParameter("qAccessType");

		// 取当前日期的前一天
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		String beforeOneDay = dateFormat.format(cal.getTime());

		// 拼装查询Map
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("qTxnDateStart", StringUtil.isBlank(qTxnDateStart) ? "" : qTxnDateStart);
		paramMap.put("qTxnDateEnd", StringUtil.isBlank(qTxnDateEnd) ? beforeOneDay : qTxnDateEnd);
		paramMap.put("qAccessType", StringUtil.isBlank(qAccessType) ? "" : qAccessType);

		return txnCountService.getInstance().queryAll(paramMap, queryBean.getPage());
	}
}
