package com.ruimin.ifs.pmp.report.comp;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import com.ruim.ifsp.utils.client.http.IfspURLUtil;
import com.ruim.ifsp.utils.verify.IfspDataVerifyUtil;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.framework.dataobject.FormRequestBean;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.report.common.constants.ReportConstants;
import com.ruimin.ifs.pmp.report.common.utils.CommonBthUtil;
import com.ruimin.ifs.pmp.report.process.service.MchtInAcctDtlService;

@ActionResource
@SnowDoc(desc = "商户入账明细action")
public class MchtInAcctDtlAction extends SnowAction {
	private static Logger log = SnowLog.getLogger(MchtInAcctDtlAction.class);
	/**
	 * 
	 * 功能描述: 商户入账结果信息分页查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Action
	@SnowDoc(desc = "商户入账明细信息分页查询")
	public PageResult pageQuery(QueryParamBean queryBean) throws SnowException {
		String qInAcctDate = queryBean.getParameter("qSettleDate");
		String qMchtId = queryBean.getParameter("qMchtId");
		String qAmtFlg = queryBean.getParameter("qAmtFlg");
		
		return MchtInAcctDtlService.getInstance().pageQuery(qInAcctDate,qMchtId,qAmtFlg, queryBean.getPage());
	}

	
	@Action
	@SnowDoc(desc = "商户入账明细报表下载")
	public void downloadReport(FormRequestBean requestBean) throws UnsupportedEncodingException, SnowException {
		String qStlmDate = requestBean.getParameter("qStlmDate");
		String qChlMerId = requestBean.getParameter("qChlMerId");
		String qChlMerName = requestBean.getParameter("qChlMerName");
		String qTxnCount = requestBean.getParameter("qTxnCount");
		String qTxnAmt = requestBean.getParameter("qTxnAmt");
		String qFeeAmt = requestBean.getParameter("qFeeAmt");
		String qInAcctNo = requestBean.getParameter("qInAcctNo");
		String qInAcctAmt = requestBean.getParameter("qInAcctAmt");
		String qPlanInAcctDate = requestBean.getParameter("qPlanInAcctDate");
		String qInAcctStat = requestBean.getParameter("qInAcctStat");
		String qInAcctTime = requestBean.getParameter("qInAcctTime");
		String qStatMark = requestBean.getParameter("qStatMark");
		String qAmtFlg = requestBean.getParameter("qAmtFlg");
		
		qChlMerName = IfspURLUtil.URLDecoder(qChlMerName, "UTF-8");
		qStatMark = IfspURLUtil.URLDecoder(qStatMark, "UTF-8");
		if(StringUtil.isNotBlank(qInAcctTime)){
			qInAcctTime = IfspURLUtil.URLDecoder(qInAcctTime, "UTF-8");
		}
		try {
			/** 报表文件里面需要进行处理的参数 */
			Map<String, Object> reportParam = new HashMap<String, Object>();
			reportParam.put("qStlmDate", qStlmDate);
			reportParam.put("qChlMerId", qChlMerId);
			reportParam.put("qChlMerName", qChlMerName);
			reportParam.put("qTxnCount", qTxnCount);
			reportParam.put("qTxnAmt", qTxnAmt);
			reportParam.put("qFeeAmt", qFeeAmt);
			reportParam.put("qInAcctNo", qInAcctNo);
			reportParam.put("qInAcctAmt", qInAcctAmt);
			reportParam.put("qPlanInAcctDate", qPlanInAcctDate);
			reportParam.put("qInAcctStat", qInAcctStat);
			reportParam.put("qInAcctTime", qInAcctTime);
			reportParam.put("qStatMark", qStatMark);
			reportParam.put("qAmtFlg", qAmtFlg);

			/** 查询报表数据 */
			List<Object> dataList = MchtInAcctDtlService.getInstance().queryForExport(qStlmDate,qChlMerId,qAmtFlg);
			if (IfspDataVerifyUtil.isEmptyList(dataList)) {
				SnowExceptionUtil.throwErrorException("查询数据为空，无法生成报表");
			}

			/** 获取报表路径 **/
			String path = requestBean.getRequest().getSession().getServletContext()
					.getRealPath(ReportConstants.JASPER_PATH_MCHT_IN_ACCT_DTL);

			/******************************
			 * STEP NO2 组装报表&输出Excel
			 ******************************/
			CommonBthUtil.genBthOutExcel(path, ReportConstants.EXPORT_NAME_MCHT_IN_ACCT_DTL, reportParam, dataList,
					requestBean);

		} catch (Exception e) {
			log.error("下载对账差错报表异常：", e);
			SnowExceptionUtil.throwErrorException(e.getMessage());
		}

		
	}
}
