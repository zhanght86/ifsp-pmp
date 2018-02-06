package com.ruimin.ifs.pmp.report.process.bean;

import java.io.Serializable;
/**
 * @describle 中信通道对账文件流水表
 * @author fengwei 20180116
 *
 */
public class PagyEciticTxnBillInfo implements Serializable {
    private String dateSettlmt;

    private String txnTime;

    private String merOrderId;

    private String merOrigOrderId;

    private String publicNo;

    private String thirdMchtNo;

    private String mainMchtNo;

    private String subMchtNo;

    private String deviceNo;

    private String swiftOrderId;

    private String thirdOrderId;

    private String userFlg;

    private String txnType;

    private String txnState;

    private String payBank;

    private String txnCoin;

    private String amtSumTrans;

    private String amtEntRedBack;

    private String swiftOrigOrderId;

    private String amtRefund;

    private String amtRefundEntRedBack;

    private String refundType;

    private String refundState;

    private String desOfGoods;

    private String merDataPack;

    private String amtFee;

    private String feeRate;

    private String termType;

    private String balanceAcctTag;

    private String storeNo;

    private String mchtName;

    private String cashier;

    private String subMchtId;

    private String freeRechargeTicketAmt;

    private String relGatherAmt;

    private String backup1;

    private String backup2;

    private String backup3;

    private String backup4;

    private String orderAmt;

    private String applyRefundAmt;

    private String feeRateRemark;

    private String pagyTxnType;

    public String getDateSettlmt() {
        return dateSettlmt;
    }

    public void setDateSettlmt(String dateSettlmt) {
        this.dateSettlmt = dateSettlmt == null ? null : dateSettlmt.trim();
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime == null ? null : txnTime.trim();
    }

    public String getMerOrderId() {
        return merOrderId;
    }

    public void setMerOrderId(String merOrderId) {
        this.merOrderId = merOrderId == null ? null : merOrderId.trim();
    }

    public String getMerOrigOrderId() {
        return merOrigOrderId;
    }

    public void setMerOrigOrderId(String merOrigOrderId) {
        this.merOrigOrderId = merOrigOrderId == null ? null : merOrigOrderId.trim();
    }

    public String getPublicNo() {
        return publicNo;
    }

    public void setPublicNo(String publicNo) {
        this.publicNo = publicNo == null ? null : publicNo.trim();
    }

    public String getThirdMchtNo() {
        return thirdMchtNo;
    }

    public void setThirdMchtNo(String thirdMchtNo) {
        this.thirdMchtNo = thirdMchtNo == null ? null : thirdMchtNo.trim();
    }

    public String getMainMchtNo() {
        return mainMchtNo;
    }

    public void setMainMchtNo(String mainMchtNo) {
        this.mainMchtNo = mainMchtNo == null ? null : mainMchtNo.trim();
    }

    public String getSubMchtNo() {
        return subMchtNo;
    }

    public void setSubMchtNo(String subMchtNo) {
        this.subMchtNo = subMchtNo == null ? null : subMchtNo.trim();
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo == null ? null : deviceNo.trim();
    }

    public String getSwiftOrderId() {
        return swiftOrderId;
    }

    public void setSwiftOrderId(String swiftOrderId) {
        this.swiftOrderId = swiftOrderId == null ? null : swiftOrderId.trim();
    }

    public String getThirdOrderId() {
        return thirdOrderId;
    }

    public void setThirdOrderId(String thirdOrderId) {
        this.thirdOrderId = thirdOrderId == null ? null : thirdOrderId.trim();
    }

    public String getUserFlg() {
        return userFlg;
    }

    public void setUserFlg(String userFlg) {
        this.userFlg = userFlg == null ? null : userFlg.trim();
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType == null ? null : txnType.trim();
    }

    public String getTxnState() {
        return txnState;
    }

    public void setTxnState(String txnState) {
        this.txnState = txnState == null ? null : txnState.trim();
    }

    public String getPayBank() {
        return payBank;
    }

    public void setPayBank(String payBank) {
        this.payBank = payBank == null ? null : payBank.trim();
    }

    public String getTxnCoin() {
        return txnCoin;
    }

    public void setTxnCoin(String txnCoin) {
        this.txnCoin = txnCoin == null ? null : txnCoin.trim();
    }

    public String getAmtSumTrans() {
        return amtSumTrans;
    }

    public void setAmtSumTrans(String amtSumTrans) {
        this.amtSumTrans = amtSumTrans == null ? null : amtSumTrans.trim();
    }

    public String getAmtEntRedBack() {
        return amtEntRedBack;
    }

    public void setAmtEntRedBack(String amtEntRedBack) {
        this.amtEntRedBack = amtEntRedBack == null ? null : amtEntRedBack.trim();
    }

    public String getSwiftOrigOrderId() {
        return swiftOrigOrderId;
    }

    public void setSwiftOrigOrderId(String swiftOrigOrderId) {
        this.swiftOrigOrderId = swiftOrigOrderId == null ? null : swiftOrigOrderId.trim();
    }

    public String getAmtRefund() {
        return amtRefund;
    }

    public void setAmtRefund(String amtRefund) {
        this.amtRefund = amtRefund == null ? null : amtRefund.trim();
    }

    public String getAmtRefundEntRedBack() {
        return amtRefundEntRedBack;
    }

    public void setAmtRefundEntRedBack(String amtRefundEntRedBack) {
        this.amtRefundEntRedBack = amtRefundEntRedBack == null ? null : amtRefundEntRedBack.trim();
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType == null ? null : refundType.trim();
    }

    public String getRefundState() {
        return refundState;
    }

    public void setRefundState(String refundState) {
        this.refundState = refundState == null ? null : refundState.trim();
    }

    public String getDesOfGoods() {
        return desOfGoods;
    }

    public void setDesOfGoods(String desOfGoods) {
        this.desOfGoods = desOfGoods == null ? null : desOfGoods.trim();
    }

    public String getMerDataPack() {
        return merDataPack;
    }

    public void setMerDataPack(String merDataPack) {
        this.merDataPack = merDataPack == null ? null : merDataPack.trim();
    }

    public String getAmtFee() {
        return amtFee;
    }

    public void setAmtFee(String amtFee) {
        this.amtFee = amtFee == null ? null : amtFee.trim();
    }

    public String getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(String feeRate) {
        this.feeRate = feeRate == null ? null : feeRate.trim();
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType == null ? null : termType.trim();
    }

    public String getBalanceAcctTag() {
        return balanceAcctTag;
    }

    public void setBalanceAcctTag(String balanceAcctTag) {
        this.balanceAcctTag = balanceAcctTag == null ? null : balanceAcctTag.trim();
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo == null ? null : storeNo.trim();
    }

    public String getMchtName() {
        return mchtName;
    }

    public void setMchtName(String mchtName) {
        this.mchtName = mchtName == null ? null : mchtName.trim();
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier == null ? null : cashier.trim();
    }

    public String getSubMchtId() {
        return subMchtId;
    }

    public void setSubMchtId(String subMchtId) {
        this.subMchtId = subMchtId == null ? null : subMchtId.trim();
    }

    public String getFreeRechargeTicketAmt() {
        return freeRechargeTicketAmt;
    }

    public void setFreeRechargeTicketAmt(String freeRechargeTicketAmt) {
        this.freeRechargeTicketAmt = freeRechargeTicketAmt == null ? null : freeRechargeTicketAmt.trim();
    }

    public String getRelGatherAmt() {
        return relGatherAmt;
    }

    public void setRelGatherAmt(String relGatherAmt) {
        this.relGatherAmt = relGatherAmt == null ? null : relGatherAmt.trim();
    }

    public String getBackup1() {
        return backup1;
    }

    public void setBackup1(String backup1) {
        this.backup1 = backup1 == null ? null : backup1.trim();
    }

    public String getBackup2() {
        return backup2;
    }

    public void setBackup2(String backup2) {
        this.backup2 = backup2 == null ? null : backup2.trim();
    }

    public String getBackup3() {
        return backup3;
    }

    public void setBackup3(String backup3) {
        this.backup3 = backup3 == null ? null : backup3.trim();
    }

    public String getBackup4() {
        return backup4;
    }

    public void setBackup4(String backup4) {
        this.backup4 = backup4 == null ? null : backup4.trim();
    }

    public String getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(String orderAmt) {
        this.orderAmt = orderAmt == null ? null : orderAmt.trim();
    }

    public String getApplyRefundAmt() {
        return applyRefundAmt;
    }

    public void setApplyRefundAmt(String applyRefundAmt) {
        this.applyRefundAmt = applyRefundAmt == null ? null : applyRefundAmt.trim();
    }

    public String getFeeRateRemark() {
        return feeRateRemark;
    }

    public void setFeeRateRemark(String feeRateRemark) {
        this.feeRateRemark = feeRateRemark == null ? null : feeRateRemark.trim();
    }

    public String getPagyTxnType() {
        return pagyTxnType;
    }

    public void setPagyTxnType(String pagyTxnType) {
        this.pagyTxnType = pagyTxnType == null ? null : pagyTxnType.trim();
    }
}