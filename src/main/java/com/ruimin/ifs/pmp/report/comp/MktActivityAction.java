/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.report.comp 
 * FileName: MktActivityAction.java
 * Author:   LJY
 * Date:     2017年11月1日 下午12:57:36
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年11月1日下午12:57:36                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.report.comp;

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
import com.ruimin.ifs.mng.process.service.UserAuthorityDownService;
import com.ruimin.ifs.pmp.report.common.utils.CommonBthUtil;
import com.ruimin.ifs.pmp.report.process.service.MktActivityService;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年11月1日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
@ActionResource
@SnowDoc(desc = "营销活动action")
public class MktActivityAction extends SnowAction{
    public PageResult pageQuery(QueryParamBean queryBean) throws SnowException{
        String qTxnDt = queryBean.getParameter("qTxnDt");
        String qStlmDate = queryBean.getParameter("qStlmDate");
        String qActiveNo = queryBean.getParameter("qActiveNo");
        String qCardNo = queryBean.getParameter("qCardNo");
        return MktActivityService.getInstance().pagequery(qTxnDt,qStlmDate,qActiveNo,qCardNo, queryBean.getPage());
    }
    
    @Action
    @SnowDoc(desc= "营销活动报表下载")
    public void doutAgent(FormRequestBean formRequestBean) throws SnowException, UnsupportedEncodingException{
        //判断用户是否有权限下载报表
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        String tlrno=sessionUserInfo.getTlrno();
        String str=UserAuthorityDownService.getInstance().qTlrRole(tlrno, "1015006");
        if(tlrno.equals(str)){              
        }else{
            SnowExceptionUtil.throwErrorException("此用户没有权限下载");
        }
        String qTxnDt = formRequestBean.getParameter("qTxnDt");//交易日期
        String qStlmDate = formRequestBean.getParameter("qStlmDate");//清算日期
        String qActiveNo = formRequestBean.getParameter("qActiveNo");// 活动编号
        String qCardNo = formRequestBean.getParameter("qCardNo");//用户编号
        try {
            /** 获取Title **/
            String title = "营销活动报表下载";
            /** 获取路径 **/
            String relPath = "/report/jasper/mktActivity.jasper";
            /** 获取PageHeader **/  
            Map<String, Object> pageHeader = new HashMap<String, Object>();
            pageHeader.put("qTxnDt", qTxnDt);
            pageHeader.put("qStlmDate", qStlmDate);
            pageHeader.put("qActiveNo", qActiveNo);
            pageHeader.put("qCardNo", qCardNo);
            /** 获取Detail **/
            List<Object> detail = MktActivityService.getInstance().doutAgent(qTxnDt, qStlmDate, qActiveNo,qCardNo);
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
