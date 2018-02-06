/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.report.comp 
 * FileName: MchtNetExpenseDailyAction.java
 * Author:   LJY
 * Date:     2017年8月21日 下午12:54:52
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年8月21日下午12:54:52                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.report.comp;

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
import com.ruimin.ifs.pmp.report.common.utils.CommonBthUtil;
import com.ruimin.ifs.pmp.report.process.service.MchtNetExpenseDailyService;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 功能：商户网点费用信息分页查询<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年8月21日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
@ActionResource
@SnowDoc(desc = "商户网点费用信息日报action")
public class MchtNetExpenseDailyAction extends SnowAction{
    
    
    public PageResult pageQuery(QueryParamBean queryBean) throws SnowException{
        String qTxnDtStart = queryBean.getParameter("qTxnDtStart");
        String qTxnDtEnd = queryBean.getParameter("qTxnDtEnd");
        String qInAcctMerId = queryBean.getParameter("qInAcctMerId");
        String qInAcctMerName = queryBean.getParameter("qInAcctMerName");
       
        return MchtNetExpenseDailyService.getInstance().pagequery(qTxnDtStart, qTxnDtEnd, qInAcctMerId, qInAcctMerName, queryBean.getPage());
    }
    
    @Action
    @SnowDoc(desc= "商户网点费用信息日报下载")
    public void doutAgent(FormRequestBean formRequestBean) throws SnowException, UnsupportedEncodingException{
        //判断用户是否有权限下载报表
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        String tlrno=sessionUserInfo.getTlrno();
        String str=UserAuthorityDownService.getInstance().qTlrRole(tlrno, "1015005");
        if(tlrno.equals(str)){              
        }else{
            SnowExceptionUtil.throwErrorException("此用户没有权限下载");
        }
        String qTxnDtStart = formRequestBean.getParameter("qTxnDtStart");//交易日期(起始)
        String qTxnDtEnd = formRequestBean.getParameter("qTxnDtEnd");//交易日期(截止)
        String qInAcctMerId = formRequestBean.getParameter("qInAcctMerId");// 商户编号
        String qInAcctMerName = formRequestBean.getParameter("qInAcctMerName");// 商户名称 

            if (StringUtils.isNotBlank(qInAcctMerName)) {
                qInAcctMerName = IfspURLUtil.URLDecoder(qInAcctMerName, "UTF-8");
            }
            
        try {
            /** 获取Title **/
            String title = "商户网点费用报表下载";
            /** 获取路径 **/
            String relPath = "/report/jasper/mchtNetExpenseDaily.jasper";
            /** 获取PageHeader **/  
            Map<String, Object> pageHeader = new HashMap<String, Object>();
            pageHeader.put("qTxnDtStart", qTxnDtStart);
            pageHeader.put("qTxnDtEnd", qTxnDtEnd);
            pageHeader.put("qInAcctMerId", qInAcctMerId);
            pageHeader.put("qInAcctMerName", qInAcctMerName);
            
            /** 获取Detail **/
            List<Object> detail = MchtNetExpenseDailyService.getInstance().doutAgent(qTxnDtStart, qTxnDtEnd, qInAcctMerId, qInAcctMerName);
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
