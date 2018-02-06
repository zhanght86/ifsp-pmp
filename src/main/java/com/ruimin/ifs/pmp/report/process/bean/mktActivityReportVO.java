/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.report.process.bean 
 * FileName: mktActivityReportVO.java
 * Author:   LJY
 * Date:     2017年11月1日 下午12:05:51
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年11月1日下午12:05:51                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.report.process.bean;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年11月1日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
public class mktActivityReportVO {
    private String pointId;//投注机编号（营业员)
    private String activeNo;//活动编号
    private String activeNm;//活动名称
    private String activeAmt;//优惠金额
    private String txnAmt;//实收金额(代表REAL_PAY_AMT)
    private String txnOrderAmt;//订单金额
    private String merId;//商户编号
    private String txnDt;//交易日期
    private String stlmDate;//清算日期
    private String cardNo;//用户号
    private String orgIdRecv;//接受机构号
    private String acctNo;//付款人账号
    private String payerName;//付款人名称
    private String acctName;//账户名称
    
    
    public String getOrgIdRecv() {
        return orgIdRecv;
    }

    public void setOrgIdRecv(String orgIdRecv) {
        this.orgIdRecv = orgIdRecv;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    
    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getStlmDate() {
        return stlmDate;
    }
  
    public void setStlmDate(String stlmDate) {
        this.stlmDate = stlmDate;
    }

    public String getTxnDt() {
        return txnDt;
    }

    public void setTxnDt(String txnDt) {
        this.txnDt = txnDt;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getActiveNo() {
        return activeNo;
    }
 
    public void setActiveNo(String activeNo) {
        this.activeNo = activeNo;
    }

    public String getActiveNm() {
        return activeNm;
    }
  
    public void setActiveNm(String activeNm) {
        this.activeNm = activeNm;
    }
  
    public String getActiveAmt() {
        return activeAmt;
    }

    public void setActiveAmt(String activeAmt) {
        this.activeAmt = activeAmt;
    }
 
    public String getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }
 
    public String getTxnOrderAmt() {
        return txnOrderAmt;
    }

    public void setTxnOrderAmt(String txnOrderAmt) {
        this.txnOrderAmt = txnOrderAmt;
    }
  
    public String getMerId() {
        return merId;
    }
 
    public void setMerId(String merId) {
        this.merId = merId;
    }
    
    
}
