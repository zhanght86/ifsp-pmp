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
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.report.common.constants.ReportConstants;
import com.ruimin.ifs.pmp.report.common.utils.CommonBthUtil;
import com.ruimin.ifs.pmp.report.process.service.PagyClearingRsltService;

@ActionResource
@SnowDoc(desc = "通道清算汇总信息action")
public class PagyClearingRsltAction extends SnowAction {
	private static Logger log = SnowLog.getLogger(PagyClearingRsltAction.class);
	/**
	 * 
	 * 功能描述: 通道清算汇总信息分页查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Action
	@SnowDoc(desc = "通道清算汇总信息分页查询")
	public PageResult pageQuery(QueryParamBean queryBean) throws SnowException {
		String qStlmDateStart = queryBean.getParameter("qStlmDateStart");
		String qStlmDateEnd = queryBean.getParameter("qStlmDateEnd");
		String qPagyNo = queryBean.getParameter("qPagyNo"); 
		if("504".equals(qPagyNo)){
		    qPagyNo="303";
		}
		return PagyClearingRsltService.getInstance().pageQuery(qStlmDateStart, qStlmDateEnd, qPagyNo, queryBean.getPage());

	}
	@Action
	@SnowDoc(desc = "通道清算查询报表下载")
	public void downloadReport(FormRequestBean requestBean) throws UnsupportedEncodingException, SnowException {
		String qStlmDateStart = requestBean.getParameter("qStlmDateStart");
		String qStlmDateEnd = requestBean.getParameter("qStlmDateEnd");
		String qPagyNo = requestBean.getParameter("qPagyNo");
		String qPagyName = requestBean.getParameter("qPagyName");
		//机构号为空，去当前操作员所属机构
		if (!StringUtils.isBlank(qPagyNo)) {
			qPagyName = IfspURLUtil.URLDecoder(qPagyName, "UTF-8");
		}
		if("504".equals(qPagyNo)){
            qPagyNo="303";
        }
		/** 报表文件里面需要进行处理的参数 */
		Map<String, Object> reportParam = new HashMap<String, Object>();
		reportParam.put("qStlmDateStart", qStlmDateStart);
		reportParam.put("qStlmDateEnd", qStlmDateEnd);
		reportParam.put("qPagyName", qPagyName);
		
		/** 查询报表数据 */
		
		// 如果查询选择的机构为空，则默认查询本机构和本机构下的子级机构商户
		
		List<Object> dataList = PagyClearingRsltService.getInstance().queryForExport(qStlmDateStart, qStlmDateEnd, qPagyNo);
		
		if (IfspDataVerifyUtil.isEmptyList(dataList)) {
//		    SnowExceptionUtil.throwWarnException("查询数据为空，无法生成报表");
		}
		try {

			/** 获取报表路径 **/
			String path = requestBean.getRequest().getSession().getServletContext()
					.getRealPath(ReportConstants.JASPER_PATH_PAGY_CLEAR_RSLT);

			/******************************
			 * STEP NO2 组装报表&输出Excel
			 ******************************/
			CommonBthUtil.genBthOutExcel(path, ReportConstants.EXPORT_NAME_PAGY_CLEAR_RSLT, reportParam, dataList,
					requestBean);

		} catch (Exception e) {
			log.error("下载对账差错报表异常：", e);
			SnowExceptionUtil.throwWarnException(e.getMessage());
		}

		
	}
}
