package com.ruimin.ifs.pmp.txnQuery.comp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruim.ifsp.utils.verify.IfspDataVerifyUtil;
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
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.report.common.utils.CommonBthUtil;
import com.ruimin.ifs.pmp.txnQuery.process.service.PbsOrderInfoService;
import com.ruimin.ifs.rql.page.RqlPage;

/**
 * 名称：交易查询页面<br> 
 * 功能：交易查询1<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2018年2月5日 <br>
 * 作者：LJY <br>
 * 说明：modify by lengjingyu 20180205 交易查询界面变更需求 jira-1865 <br>
 */

@SnowDoc(desc = "支付业务交易查询")
@ActionResource
public class TxnQueryAction extends SnowAction {
    @Action
    @SnowDoc(desc = "支付业务交易列表查询")
    public PageResult queryAll(QueryParamBean queryBean) throws SnowException, ParseException {
        // 接收查询参数-交易日期起始
        String qTxnDateStart = queryBean.getParameter("qTxnDateStart");
        // 接收查询参数-交易日期截至
        String qTxnDateEnd = queryBean.getParameter("qTxnDateEnd");
        // 接收查询参数-商户编号
        String qMchtId = queryBean.getParameter("qMchtId");
        // 接收查询参数-商户订单号
        String qMchtOrderId = queryBean.getParameter("qMchtOrderId");
        // 接收查询参数-平台交易流水号
        String qTxnSeqId = queryBean.getParameter("qTxnSeqId");
        // 接收查询参数-支付凭证号
        String qtpamOutId = queryBean.getParameter("qtpamOutId");
        // 接收查询参数-支付产品
        String qPayProduct = queryBean.getParameter("qPayProduct");
        // 接收查询参数-交易状态
        String qTxnState = queryBean.getParameter("qTxnState");
        // 接收查询参数-通道流水号
        String qPagySeqId = queryBean.getParameter("qPagySeqId");
        
        // 校验   1.开始日期与结束日期不能为空
        if (IfspDataVerifyUtil.isBlank(qTxnDateStart)||IfspDataVerifyUtil.isBlank(qTxnDateEnd)) {
            SnowExceptionUtil.throwWarnException("交易日期起始与截止不能为空!!");
        }
        //  2. 算两个日期间隔小于等于31天
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date1 = format.parse(qTxnDateStart);
        Date date2 = format.parse(qTxnDateEnd);
        int a = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        if (a>31||a<0) {
            SnowExceptionUtil.throwWarnException("交易日期(起始)不能大于交易日期(截止)且跨度最大31天!!");
        }
        //  3.如果商户订单号不为空,则商户号必须输入
        if (IfspDataVerifyUtil.isNotBlank(qMchtOrderId)) {
            if (IfspDataVerifyUtil.isBlank(qMchtId)) {
                SnowExceptionUtil.throwWarnException("商户订单号只支持与商户号一起作为检索条件,请输入商户号!!");
            }
        }
        
        // 拼装查询Map
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("qTxnDateStart", qTxnDateStart);
        paramMap.put("qTxnDateEnd", qTxnDateEnd);
        paramMap.put("qMchtId", StringUtil.isBlank(qMchtId) ? "" : "%" + qMchtId + "%");
        paramMap.put("qMchtOrderId", StringUtil.isBlank(qMchtOrderId) ? "" : "%" + qMchtOrderId + "%");
        paramMap.put("qTxnSeqId", StringUtil.isBlank(qTxnSeqId) ? "" : "%" + qTxnSeqId + "%");
        paramMap.put("qPayProduct", StringUtil.isBlank(qPayProduct) ? "" : qPayProduct);
        paramMap.put("qTxnState", StringUtil.isBlank(qTxnState) ? "" : qTxnState);
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        paramMap.put("currentBrCode",sessionUserInfo.getBrCode());
       
        //判断是否同时填写了支付凭证号与通道流水号
        if (IfspDataVerifyUtil.isNotBlank(qtpamOutId)&&IfspDataVerifyUtil.isNotBlank(qPagySeqId)) {
            //根据通道流水号查询通道号
            String pagyNo = PbsOrderInfoService.getInstance().getPagyNo2(qPagySeqId);
            // 为空说明没有走通道,没有支付凭证号,返回0记录
            if (IfspDataVerifyUtil.isBlank(pagyNo)) {
                return new RqlPage();
            }
            String tpamOutIdQ = null;
            //走了通道,查询对应的支付凭证号,再跟前台得到的支付凭证号做比较,相同则根据通道流水号查询结果返回,不同就返回0记录
            switch (pagyNo) {
            // 304----关联支付宝直连
            case "304":
                tpamOutIdQ = PbsOrderInfoService.getInstance().getTpamOutIdAli(qPagySeqId);
                if (String.valueOf(tpamOutIdQ).indexOf(qtpamOutId) !=-1) {
                    paramMap.put("qPagySeqId", qPagySeqId+"%");
                    PageResult res = PbsOrderInfoService.getInstance().queryAllByPagyTxnSsn(paramMap,queryBean.getPage());
                    return res;
                }else{
                    return new RqlPage();
                }
            // 311----关联威富通通道
            case "311":
                tpamOutIdQ = PbsOrderInfoService.getInstance().getTpamOutIdSwf(qPagySeqId);
                if (String.valueOf(tpamOutIdQ).indexOf(qtpamOutId) !=-1) {
                    paramMap.put("qPagySeqId", qPagySeqId+"%");
                    PageResult res = PbsOrderInfoService.getInstance().queryAllByPagyTxnSsn(paramMap,queryBean.getPage());
                    return res;
                }else{
                    return new RqlPage();
                }
            // 312---关联平安银行通道
            case "312":
                tpamOutIdQ = PbsOrderInfoService.getInstance().getTpamOutIdMix(qPagySeqId);
                if (String.valueOf(tpamOutIdQ).indexOf(qtpamOutId) !=-1) {
                    paramMap.put("qPagySeqId",qPagySeqId+"%");
                    PageResult res = PbsOrderInfoService.getInstance().queryAllByPagyTxnSsn(paramMap,queryBean.getPage());
                    return res;
                }else{
                    return new RqlPage();
                }
            // 313---关联中信通道
            case "313":
                tpamOutIdQ = PbsOrderInfoService.getInstance().getTpamOutIdEci(qPagySeqId);
                if (String.valueOf(tpamOutIdQ).indexOf(qtpamOutId) !=-1) {
                    paramMap.put("qPagySeqId", qPagySeqId+"%");
                    PageResult res = PbsOrderInfoService.getInstance().queryAllByPagyTxnSsn(paramMap,queryBean.getPage());
                    return res;
                }else{
                    return new RqlPage();
                }
            // 504---关联联网通汇
            case "504":
                tpamOutIdQ = PbsOrderInfoService.getInstance().getTpamOutIdNet(qPagySeqId);
                if (String.valueOf(tpamOutIdQ).indexOf(qtpamOutId) !=-1) {
                    paramMap.put("qPagySeqId", qPagySeqId+"%");
                    PageResult res = PbsOrderInfoService.getInstance().queryAllByPagyTxnSsn(paramMap,queryBean.getPage());
                    return res;
                }else{
                    return new RqlPage();
                }
            // 不关联通道
            default:
                return new RqlPage();
            }
            
        // 填写了通道流水,没填支付凭证号
        }else if (IfspDataVerifyUtil.isNotBlank(qPagySeqId)&&IfspDataVerifyUtil.isBlank(qtpamOutId)) {
             paramMap.put("qPagySeqId", qPagySeqId+"%");
             PageResult res = PbsOrderInfoService.getInstance().queryAllByPagyTxnSsn(paramMap,queryBean.getPage());
             return res;
        // 填了支付凭证号,没填通道流水
        } else if (IfspDataVerifyUtil.isNotBlank(qtpamOutId)&&IfspDataVerifyUtil.isBlank(qPagySeqId) ) {
            paramMap.put("qTpamOutId", qtpamOutId+"%");
            return PbsOrderInfoService.getInstance().selectByTpamOutId(paramMap,queryBean.getPage());
        // 都没有则执行下面查询
        } else {
            return PbsOrderInfoService.getInstance().queryAll(paramMap,queryBean.getPage());
        }
    }
    @Action
    @SnowDoc(desc = "支付业务交易详情查询")
    public PageResult queryDetil(QueryParamBean queryBean) throws SnowException {
        
        // 接收查询参数-平台交易流水号
        String qTxnSeqId = queryBean.getParameter("qTxnSeqId");
        // 拼装查询Map
        String txnSeqId = StringUtil.isBlank(qTxnSeqId) ? "" :  qTxnSeqId ;
        // 根据平台交易流水号关联通道核心表查询所走通道
        String pagyNo = PbsOrderInfoService.getInstance().getPagyNo(txnSeqId);
        String flag = StringUtil.isBlank(pagyNo) ? "" :  pagyNo ;
        switch (flag) {
        // 304----关联支付宝直连
        case "304":
            return PbsOrderInfoService.getInstance().queryAliDetil(txnSeqId,queryBean.getPage());
        // 311----关联威富通通道
        case "311":
            return PbsOrderInfoService.getInstance().querySwfDetil(txnSeqId,queryBean.getPage());
        // 312---关联平安银行通道
        case "312":
            return PbsOrderInfoService.getInstance().queryMixDetil(txnSeqId,queryBean.getPage());
        // 313---关联中信通道
        case "313":
            return PbsOrderInfoService.getInstance().queryEciDetil(txnSeqId,queryBean.getPage());
        // 504---关联联网通汇
        case "504":
            return PbsOrderInfoService.getInstance().queryNetDetil(txnSeqId,queryBean.getPage());
        // 不关联通道
        default:
            return PbsOrderInfoService.getInstance().queryDetil(txnSeqId,queryBean.getPage());
        }
        
    }
    
    
//    @Action
//    @SnowDoc(desc = "支付业务交易列表下载")
//    public void doutAgent(FormRequestBean formRequestBean) throws SnowException, ParseException {
//        // 接收查询参数-交易日期起始
//        String qTxnDateStart = formRequestBean.getParameter("qTxnDateStart");
//        // 接收查询参数-交易日期截至
//        String qTxnDateEnd = formRequestBean.getParameter("qTxnDateEnd");
//        // 接收查询参数-商户编号
//        String qMchtId = formRequestBean.getParameter("qMchtId");
//        // 接收查询参数-商户订单号
//        String qMchtOrderId = formRequestBean.getParameter("qMchtOrderId");
//        // 接收查询参数-平台交易流水号
//        String qTxnSeqId = formRequestBean.getParameter("qTxnSeqId");
//        // 接收查询参数-支付凭证号
//        String qTpamOutId = formRequestBean.getParameter("qtpamOutId");
//        // 接收查询参数-支付产品
//        String qPayProduct = formRequestBean.getParameter("qPayProduct");
//        // 接收查询参数-交易状态
//        String qTxnState = formRequestBean.getParameter("qTxnState");
//        // 接收查询参数-通道流水号
//        String qPagySeqId = formRequestBean.getParameter("qPagySeqId");
//               
//        
//        // 校验   1.开始日期与结束日期不能为空
//        if (IfspDataVerifyUtil.isBlank(qTxnDateStart)||IfspDataVerifyUtil.isBlank(qTxnDateEnd)) {
//            SnowExceptionUtil.throwWarnException("交易日期起始与截止不能为空!!");
//        }
//        //  2. 算两个日期间隔小于等于31天
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//        Date date1 = format.parse(qTxnDateStart);
//        Date date2 = format.parse(qTxnDateEnd);
//        int a = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
//        if (a>31||a<0) {
//            SnowExceptionUtil.throwWarnException("交易日期(起始)不能大于交易日期(截止)且跨度最大31天!!");
//        }
//        //  3.如果商户订单号不为空,则商户号必须输入
//        if (IfspDataVerifyUtil.isNotBlank(qMchtOrderId)) {
//            if (IfspDataVerifyUtil.isBlank(qMchtId)) {
//                SnowExceptionUtil.throwWarnException("商户订单号只支持与商户号一起作为检索条件,请输入商户号!!");
//            }
//        }
//        
//        
//        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
//        try {
//            /** 获取Title **/
//            String title = "交易查询报表下载";
//            /** 获取路径 **/
//            String relPath = "/report/jasper/transaction.jasper";
//            /** 获取PageHeader **/  
//            Map<String, Object> pageHeader = new HashMap<String, Object>();
//            pageHeader.put("qTxnDateStart",  qTxnDateStart);
//            pageHeader.put("qTxnDateEnd", qTxnDateEnd);
//            pageHeader.put("qMchtId", StringUtil.isBlank(qMchtId) ? "" :qMchtId);
//            pageHeader.put("qMchtOrderId", StringUtil.isBlank(qMchtOrderId) ? "" :  qMchtOrderId);
//            pageHeader.put("qTxnSeqId", StringUtil.isBlank(qTxnSeqId) ? "" :qTxnSeqId );
//            pageHeader.put("qTpamOutId", StringUtil.isBlank(qTpamOutId) ? "" :qTpamOutId );
//            pageHeader.put("qPayProduct", StringUtil.isBlank(qPayProduct) ? "" :qPayProduct );
//            pageHeader.put("qTxnState", StringUtil.isBlank(qTxnState) ? "" :qTxnState );
//            pageHeader.put("qPagySeqId", StringUtil.isBlank(qPagySeqId) ? "" :qPagySeqId );
//
//            
//            /** 获取Detail **/
//            List<Object> detail =PbsOrderInfoService.getInstance().doutAgent(qTxnDateStart,qTxnDateEnd,qMchtId,qTxnState,qTpamOutId,qMchtOrderId,qTxnSeqId,qPayProduct,qPagySeqId,sessionUserInfo.getBrCode());
//            /** 获取报表路径 **/
//            
//            if(qTxnState!=null&&qTxnState!=""){
//                String typeNo="1836";
//                qTxnState=PbsOrderInfoService.getInstance().selectTxnState(typeNo,qTxnState);
//            }
//            //pageHeader.put("qTxnType", StringUtil.isBlank(qTxnType) ? "" : qTxnType);
//            //pageHeader.put("qAccessType", StringUtil.isBlank(qAccessType) ? "" : qAccessType);
//            pageHeader.put("qTxnState", StringUtil.isBlank(qTxnState) ? "" : qTxnState);
//            String path = formRequestBean.getRequest().getSession().getServletContext().getRealPath(relPath);
//            /******************************
//             * STEP NO2 组装报表&输出Excel
//             ******************************/
//            CommonBthUtil.genBthOutExcel(path, title, pageHeader, detail, formRequestBean);
//        } catch (Exception e) {
//            SnowExceptionUtil.throwErrorException(e.getMessage());
//        }
//    }
}
