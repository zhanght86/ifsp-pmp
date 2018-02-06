package com.ruimin.ifs.pmp.report.comp;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
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
import com.ruimin.ifs.framework.session.SessionUtil;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.mng.process.service.UserAuthorityDownService;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.report.common.utils.CommonBthUtil;
import com.ruimin.ifs.pmp.report.process.service.MchtTxnCountMonthlyService;

@ActionResource
@SnowDoc(desc = "商户交易统计月报action")
public class MchtTxnCountMonthlyAction extends SnowAction {
	/**
	 * 
	 * 功能描述: 商户交易统计月报信息分页查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Action
	@SnowDoc(desc = "商户交易统计月报信息分页查询")
	public PageResult pageQuery(QueryParamBean queryBean) throws SnowException {
		String qStleMonthStart = queryBean.getParameter("qStleMonthStart");
		String qStleMonthEnd = queryBean.getParameter("qStleMonthEnd");
		String qMchtInfo = queryBean.getParameter("qMchtInfo");
		String qMchtOrg = queryBean.getParameter("qMchtOrg");
		if(StringUtil.isNotBlank(qStleMonthStart)){
			qStleMonthStart = qStleMonthStart.substring(0, 6);
		}
		if(StringUtil.isNotBlank(qStleMonthStart)){
			qStleMonthStart = qStleMonthStart.substring(0, 6);
		}
		// 如果查询选择的机构为空，则默认查询本机构和本机构下的子级机构商户
		if (StringUtils.isBlank(qMchtOrg)) {
			qMchtOrg = SessionUtil.getSessionUserInfo().getBrCode();
		}
		return MchtTxnCountMonthlyService.getInstance().pageQuery(qStleMonthStart, qStleMonthEnd, qMchtInfo, qMchtOrg,queryBean.getPage());

	}
	 @Action
     @SnowDoc(desc = "商户交易统计月报表")
     public void doutAgent(FormRequestBean formRequestBean) throws SnowException, UnsupportedEncodingException {
      
	   //判断用户是否有权限下载报表
         SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
         String tlrno=sessionUserInfo.getTlrno();
         String str=UserAuthorityDownService.getInstance().qTlrRole(tlrno, "1015002");
         if(tlrno.equals(str)){              
         }else{
             SnowExceptionUtil.throwErrorException("此用户没有权限下载");
         }
	     // 获取查询条件的值
            String qStleMonthStart = formRequestBean.getParameter("qStleMonthStart");//统计月份(起始)
            String qStleMonthEnd = formRequestBean.getParameter("qStleMonthEnd");//统计月份(截止)
            String qMchtInfo = formRequestBean.getParameter("qMchtInfo");// 代理商编号
            String qMchtOrg = formRequestBean.getParameter("qMchtOrg");// 代理商名称
            if(StringUtil.isNotBlank(qStleMonthStart)){
                qStleMonthStart = qStleMonthStart.substring(0, 6);
            }
            if(StringUtil.isNotBlank(qStleMonthEnd)){
                qStleMonthEnd = qStleMonthEnd.substring(0, 6);
            }
            // 如果查询选择的机构为空，则默认查询本机构和本机构下的子级机构商户
            if (StringUtils.isBlank(qMchtOrg)) {
                qMchtOrg = SessionUtil.getSessionUserInfo().getBrCode();
            }else{
                qMchtOrg = IfspURLUtil.URLDecoder(qMchtOrg, "UTF-8");

            }
            if (StringUtils.isNotBlank(qMchtInfo)) {
                qMchtInfo = IfspURLUtil.URLDecoder(qMchtInfo, "UTF-8");
            }
            try {
                /** 获取Title **/
                String title = "商户交易统计月报表";
                /** 获取路径 **/
                String relPath = "/report/jasper/mchtTxnCountMonthly.jasper";
                /** 获取PageHeader **/
                Map<String, Object> pageHeader = new HashMap<String, Object>();
                pageHeader.put("qStleMonthStart", qStleMonthStart);
                pageHeader.put("qStleMonthEnd", qStleMonthEnd);
                pageHeader.put("qMchtInfo", qMchtInfo);
                pageHeader.put("qMchtOrg", qMchtOrg);
                System.out.println(qMchtOrg);
                /** 获取Detail **/
                List<Object> detail = MchtTxnCountMonthlyService.getInstance().doutAgent(qStleMonthStart, qStleMonthEnd, qMchtInfo, qMchtOrg);
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
