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
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.FormRequestBean;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.session.SessionUtil;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.mng.process.service.UserAuthorityDownService;
import com.ruimin.ifs.pmp.report.common.constants.ReportConstants;
import com.ruimin.ifs.pmp.report.common.utils.CommonBthUtil;
import com.ruimin.ifs.pmp.report.process.service.MchtInAcctRsltService;

@ActionResource
@SnowDoc(desc = "商户入账结果action")
public class MchtInAcctRsltAction extends SnowAction {
	private static Logger log = SnowLog.getLogger(MchtInAcctRsltAction.class);
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
	@SnowDoc(desc = "商户入账结果信息分页查询")
	public PageResult pageQuery(QueryParamBean queryBean) throws SnowException {
		String qStlmDateStart = queryBean.getParameter("qStlmDateStart");
		String qStlmDateEnd = queryBean.getParameter("qStlmDateEnd");
		String qMchtInfo = queryBean.getParameter("qMchtInfo");
		String qMchtOrg = queryBean.getParameter("qMchtOrg");
		String qInAcctNo = queryBean.getParameter("qInAcctNo");
		String qInAcctStat = queryBean.getParameter("qInAcctStat");
		// 如果查询选择的机构为空，则默认查询本机构和本机构下的子级机构商户
		if (StringUtils.isBlank(qMchtOrg)) {
			qMchtOrg = SessionUtil.getSessionUserInfo().getBrCode();
		}
		return MchtInAcctRsltService.getInstance().pageQuery(qStlmDateStart, qStlmDateEnd, qMchtInfo, qMchtOrg,
				qInAcctNo, qInAcctStat, queryBean.getPage());

	}
	
	
	@Action
	@SnowDoc(desc = "商户入账结果报表下载")
	public void downloadReport(FormRequestBean requestBean) throws UnsupportedEncodingException, SnowException {
	  //判断用户是否有权限下载报表
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        String tlrno=sessionUserInfo.getTlrno();
        String str=UserAuthorityDownService.getInstance().qTlrRole(tlrno, "1022006");
        if(tlrno.equals(str)){              
        }else{
            SnowExceptionUtil.throwErrorException("此用户没有权限下载");
        }
	    String qStlmDateStart = requestBean.getParameter("qStlmDateStart");
		String qStlmDateEnd = requestBean.getParameter("qStlmDateEnd");
		String qMchtInfo = requestBean.getParameter("qMchtInfo");
		String qMchtOrg = requestBean.getParameter("qMchtOrg");
		String qMchtOrgName = requestBean.getParameter("qMchtOrgName");
		String qInAcctNo = requestBean.getParameter("qInAcctNo");
		String qInAcctStat = requestBean.getParameter("qInAcctStat");
		//机构号为空，去当前操作员所属机构
		if (StringUtils.isBlank(qMchtOrg)) {
			qMchtOrg = SessionUtil.getSessionUserInfo().getBrCode();
		}else{
			//机构号不为空，转换机构名称
			qMchtOrgName = IfspURLUtil.URLDecoder(qMchtOrgName, "UTF-8");
		}
		//商户信息不为空，转换商户信息
		if (StringUtils.isNotBlank(qMchtInfo)) {
			qMchtInfo = IfspURLUtil.URLDecoder(qMchtInfo, "UTF-8");
		}
		
		try {
			/** 报表文件里面需要进行处理的参数 */
			Map<String, Object> reportParam = new HashMap<String, Object>();
			reportParam.put("qStlmDateStart", qStlmDateStart);
			reportParam.put("qStlmDateEnd", qStlmDateEnd);
			reportParam.put("qMchtInfo", qMchtInfo);
			reportParam.put("qMchtOrg", qMchtOrgName);
			reportParam.put("qInAcctNo", qInAcctNo);
			reportParam.put("qInAcctStat", qInAcctStat);

			/** 查询报表数据 */
			List<Object> dataList = MchtInAcctRsltService.getInstance().queryForExport(qStlmDateStart, qStlmDateEnd, qMchtInfo, qMchtOrg,
					qInAcctNo, qInAcctStat);

			if (IfspDataVerifyUtil.isEmptyList(dataList)) {
				SnowExceptionUtil.throwErrorException("查询数据为空，无法生成报表");
			}

			/** 获取报表路径 **/
			String path = requestBean.getRequest().getSession().getServletContext()
					.getRealPath(ReportConstants.JASPER_PATH_MCHT_IN_ACCT_RSLT);

			/******************************
			 * STEP NO2 组装报表&输出Excel
			 ******************************/
			CommonBthUtil.genBthOutExcel(path, ReportConstants.EXPORT_NAME_MCHT_IN_ACCT_RSLT, reportParam, dataList,
					requestBean);

		} catch (Exception e) {
			log.error("下载对账差错报表异常：", e);
			SnowExceptionUtil.throwErrorException(e.getMessage());
		}

		
	}

}
