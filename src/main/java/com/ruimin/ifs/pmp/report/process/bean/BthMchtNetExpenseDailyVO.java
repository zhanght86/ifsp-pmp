/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.report.process.bean 
 * FileName: BthMchtNetExpenseDailyVO.java
 * Author:   LJY
 * Date:     2017年8月21日 下午7:00:51
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年8月21日下午7:00:51                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.report.process.bean;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年8月21日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
public class BthMchtNetExpenseDailyVO {
    private String txnDt;//交易日期
    private String inAcctDate;//结算日期
    private double txnAmt;//交易金额
    private double setlAmt;//清算金额
    private double setlFeeAmt;//清算手续费
    private String inAcctMerId;//入账商户号
    private String inAcctMerName;//入账商户名
    private String pointId;//网点编号
    private String pagyName;//通道名称
    private String txnNum;//交易笔数
    private String prodName;//支付产品
    /**
     * @return the txnDt
     */
    public String getTxnDt() {
        return txnDt;
    }
    /**
     * @param txnDt the txnDt to set
     */
    public void setTxnDt(String txnDt) {
        this.txnDt = txnDt;
    }
    
 
    /**
     * @return the inAcctDate
     */
    public String getInAcctDate() {
        return inAcctDate;
    }
    /**
     * @param inAcctDate the inAcctDate to set
     */
    public void setInAcctDate(String inAcctDate) {
        this.inAcctDate = inAcctDate;
    }
    /**
     * @return the txnAmt
     */
    public double getTxnAmt() {
        return txnAmt;
    }
    /**
     * @param txnAmt the txnAmt to set
     */
    public void setTxnAmt(double txnAmt) {
        this.txnAmt = txnAmt;
    }
   
   
    /**
     * @return the setlAmt
     */
    public double getSetlAmt() {
        return setlAmt;
    }
    /**
     * @param setlAmt the setlAmt to set
     */
    public void setSetlAmt(double setlAmt) {
        this.setlAmt = setlAmt;
    }
    /**
     * @return the setlFeeAmt
     */
    public double getSetlFeeAmt() {
        return setlFeeAmt;
    }
    /**
     * @param setlFeeAmt the setlFeeAmt to set
     */
    public void setSetlFeeAmt(double setlFeeAmt) {
        this.setlFeeAmt = setlFeeAmt;
    }
    /**
     * @return the inAcctMerId
     */
    public String getInAcctMerId() {
        return inAcctMerId;
    }
    /**
     * @param inAcctMerId the inAcctMerId to set
     */
    public void setInAcctMerId(String inAcctMerId) {
        this.inAcctMerId = inAcctMerId;
    }
    /**
     * @return the inAcctMerName
     */
    public String getInAcctMerName() {
        return inAcctMerName;
    }
    /**
     * @param inAcctMerName the inAcctMerName to set
     */
    public void setInAcctMerName(String inAcctMerName) {
        this.inAcctMerName = inAcctMerName;
    }
    /**
     * @return the pointId
     */
    public String getPointId() {
        return pointId;
    }
    /**
     * @param pointId the pointId to set
     */
    public void setPointId(String pointId) {
        this.pointId = pointId;
    }
    /**
     * @return the pagyName
     */
    public String getPagyName() {
        return pagyName;
    }
    /**
     * @param pagyName the pagyName to set
     */
    public void setPagyName(String pagyName) {
        this.pagyName = pagyName;
    }
    /**
     * @return the txnNum
     */
    public String getTxnNum() {
        return txnNum;
    }
    /**
     * @param txnNum the txnNum to set
     */
    public void setTxnNum(String txnNum) {
        this.txnNum = txnNum;
    }
    /**
     * @return the prodName
     */
    public String getProdName() {
        return prodName;
    }
    /**
     * @param prodName the prodName to set
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

  
    
    
    
}
