package com.ruimin.ifs.pmp.report.comp;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruim.ifsp.utils.client.http.IfspURLUtil;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.FormRequestBean;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.mng.process.service.UserAuthorityDownService;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.report.common.utils.CommonBthUtil;
import com.ruimin.ifs.pmp.report.process.service.AmrPerformCountMonthlyService;

@ActionResource
@SnowDoc(desc = "业务员绩效统计月报action")
public class AmrPerformCountMonthlyAction extends SnowAction {
	/**
	 * 
	 * 功能描述: 业务员绩效统计月报信息分页查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Action
	@SnowDoc(desc = "业务员绩效统计月报信息分页查询")
	public PageResult pageQuery(QueryParamBean queryBean) throws SnowException {
		String qStlmMonthStart = queryBean.getParameter("qStlmMonthStart");
		String qStlmMonthEnd = queryBean.getParameter("qStlmMonthEnd");
		String qMchtAmrNo = queryBean.getParameter("qMchtAmrNo");
		String qMchtAmrName = queryBean.getParameter("qMchtAmrName");
		if(StringUtil.isNotBlank(qStlmMonthStart)){
			qStlmMonthStart = qStlmMonthStart.substring(0, 6);
		}
		if(StringUtil.isNotBlank(qStlmMonthEnd)){
			qStlmMonthEnd = qStlmMonthEnd.substring(0, 6);
		}
		return AmrPerformCountMonthlyService.getInstance().pageQuery(qStlmMonthStart, qStlmMonthEnd, qMchtAmrNo, qMchtAmrName,queryBean.getPage());

	}
	 @Action
     @SnowDoc(desc = "业务员绩效统计月报表")
     public void doutAgent(FormRequestBean formRequestBean) throws SnowException, UnsupportedEncodingException {
	   //判断用户是否有权限下载报表
         SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
         String tlrno=sessionUserInfo.getTlrno();
         String str=UserAuthorityDownService.getInstance().qTlrRole(tlrno, "1015004");
         if(tlrno.equals(str)){              
         }else{
             SnowExceptionUtil.throwErrorException("此用户没有权限下载");
         }
	     // 获取查询条件的值
            String qStlmMonthStart = formRequestBean.getParameter("qStlmMonthStart");//统计月份(起始)
            String qStlmMonthEnd = formRequestBean.getParameter("qStlmMonthEnd");//统计月份(截止)
            String qMchtAmrNo = formRequestBean.getParameter("qMchtAmrNo");// 代理商编号
            String qMchtAmrName = formRequestBean.getParameter("qMchtAmrName");// 代理商名称
            if(StringUtil.isNotBlank(qStlmMonthStart)){
                qStlmMonthStart = qStlmMonthStart.substring(0, 6);
            }
            if(StringUtil.isNotBlank(qStlmMonthEnd)){
                qStlmMonthEnd = qStlmMonthEnd.substring(0, 6);
            }
            if (StringUtils.isNotBlank(qMchtAmrName)) {
                qMchtAmrName = IfspURLUtil.URLDecoder(qMchtAmrName, "UTF-8");
            }
            if (StringUtils.isNotBlank(qMchtAmrNo)) {
                qMchtAmrNo = IfspURLUtil.URLDecoder(qMchtAmrNo, "UTF-8");
            }
            try {
                /** 获取Title **/
                String title = "业务员绩效统计月报表";
                /** 获取路径 **/
                String relPath = "/report/jasper/AmrPerformCountMonthly.jasper";
                /** 获取PageHeader **/
                Map<String, Object> pageHeader = new HashMap<String, Object>();
                pageHeader.put("qStlmMonthStart", qStlmMonthStart);
                pageHeader.put("qStlmMonthEnd", qStlmMonthEnd);
                pageHeader.put("qMchtAmrNo", qMchtAmrNo);
                pageHeader.put("qMchtAmrName", qMchtAmrName);
                /** 获取Detail **/
                List<Object> detail =AmrPerformCountMonthlyService.getInstance().doutAgent(qStlmMonthStart, qStlmMonthEnd, qMchtAmrNo, qMchtAmrName);
                /** 获取报表路径 **/
                String path = formRequestBean.getRequest().getSession().getServletContext().getRealPath(relPath);
                /******************************
                 * STEP NO2 组装报表&输出Excel
                 ******************************/
                CommonBthUtil.genBthOutExcel(path, title, pageHeader, detail, formRequestBean);
            } catch (Exception e) {
                SnowExceptionUtil.throwErrorException(e.getMessage());
            }

     }
}
