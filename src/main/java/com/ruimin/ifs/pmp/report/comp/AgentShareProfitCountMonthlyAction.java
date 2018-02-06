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
import com.ruimin.ifs.pmp.report.process.service.AgentShareProfitCountMonthlyService;

@ActionResource
@SnowDoc(desc = "代理商分润统计月报action")
public class AgentShareProfitCountMonthlyAction extends SnowAction {
	/**
	 * 
	 * 功能描述: 代理商分润统计月报信息分页查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Action
	@SnowDoc(desc = "代理商分润统计月报信息分页查询")
	public PageResult pageQuery(QueryParamBean queryBean) throws SnowException {
		String qStlmMonthStart = queryBean.getParameter("qStlmMonthStart");
		String qStlmMonthEnd = queryBean.getParameter("qStlmMonthEnd");
		String qAgentId = queryBean.getParameter("qAgentId");
		String qAgentName = queryBean.getParameter("qAgentName");
		if(StringUtil.isNotBlank(qStlmMonthStart)){
			qStlmMonthStart = qStlmMonthStart.substring(0, 6);
		}
		if(StringUtil.isNotBlank(qStlmMonthEnd)){
			qStlmMonthEnd = qStlmMonthEnd.substring(0, 6);
		}
		return AgentShareProfitCountMonthlyService.getInstance().pageQuery(qStlmMonthStart,qStlmMonthEnd,qAgentId,qAgentName, queryBean.getPage());

	}
	 @Action
     @SnowDoc(desc = "代理商分润统计月报表下载")
     public void doutAgent(FormRequestBean formRequestBean) throws SnowException, UnsupportedEncodingException {
	  // 获取查询条件的值 
	     //判断用户是否有权限下载报表
	      SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
	      String tlrno=sessionUserInfo.getTlrno();
	      String str=UserAuthorityDownService.getInstance().qTlrRole(tlrno, "1015003");
	      if(tlrno.equals(str)){	          
	      }else{
	          SnowExceptionUtil.throwErrorException("此用户没有权限下载");
	      }
	        String qStlmMonthStart = formRequestBean.getParameter("qStlmMonthStart");//统计月份(起始)
	        String qStlmMonthEnd = formRequestBean.getParameter("qStlmMonthEnd");//统计月份(截止)
	        String qAgentId = formRequestBean.getParameter("qAgentId");// 代理商编号
	        String qAgentName = formRequestBean.getParameter("qAgentName");// 代理商名称 
	           if(StringUtil.isNotBlank(qStlmMonthStart)){
	                qStlmMonthStart = qStlmMonthStart.substring(0, 6);
	            }
	            if(StringUtil.isNotBlank(qStlmMonthEnd)){
	                qStlmMonthEnd = qStlmMonthEnd.substring(0, 6);
	            }
	            if (StringUtils.isNotBlank(qAgentName)) {
	                qAgentName = IfspURLUtil.URLDecoder(qAgentName, "UTF-8");
	            }
	            
	        try {
	            /** 获取Title **/
	            String title = "代理商分润统计月报表下载";
	            /** 获取路径 **/
	            String relPath = "/report/jasper/agentShareProfitCountMonthly.jasper";
	            /** 获取PageHeader **/  
	            Map<String, Object> pageHeader = new HashMap<String, Object>();
	            pageHeader.put("qStlmMonthStart", qStlmMonthStart);
	            pageHeader.put("qStlmMonthEnd", qStlmMonthEnd);
	            pageHeader.put("qAgentId", qAgentId);
	            pageHeader.put("qAgentName", qAgentName);
	            
	            /** 获取Detail **/
	            List<Object> detail = AgentShareProfitCountMonthlyService.getInstance().doutAgent(qStlmMonthStart, qStlmMonthEnd, qAgentId, qAgentName);
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
