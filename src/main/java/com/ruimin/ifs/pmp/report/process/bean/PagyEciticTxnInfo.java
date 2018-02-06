package com.ruimin.ifs.pmp.report.process.bean;

import java.io.Serializable;
/**
 * @describle 中信通道流水表
 * @author fengwei 20180116
 *
 */
public class PagyEciticTxnInfo implements Serializable {
    private String pagySeqId;

    private String pagySeqTm;

    private String pagyType;

    private String pagyTxnType;

    private String paySysId;

    private String origSeqId;

    private String origSeqTm;

    private String payTxnSsn;

    private String payTxnTime;

    private String chlNo;

    private String chlMchtId;

    private String chlTxnSsn;

    private String chlTxnTime;

    private String payTxnTimeEnd;

    private String payBankId;

    private String payAccTypeNo;

    private String payTxnAccNo;

    private String payTxnCustomerInfo;

    private String pagyBindId;

    private String pagyCetAccNo;

    private String pagyRfdMode;

    private String pagyRfdAmtSum;

    private String pagyRfdCount;

    private String payTxnAmt;

    private String payCcyType;

    private String pagySteType;

    private String pagySteAmt;

    private String pagySteCcyType;

    private String pagySteDate;

    private String pagyState;

    private String pagyRespCode;

    private String pagyRespMsg;

    private String pagyTmEnd;

    private String tpamType;

    private String tpamAcsType;

    private String tpamVersion;

    private String tpamStatus;

    private String tpamMessage;

    private String tpamResultCode;

    private String tpamErrCode;

    private String tpamErrMsg;

    private String tpamTransactionId;

    private String tpamOutTransactionId;

    private String tpamOutTradeNo;

    private String tpamRefundId;

    private String tpamTradeType;

    private String tpamOrgId;

    private String tpamMchtId;

    private String tpamBody;

    private String tpamRefundChannel;

    private String tpamRefundFee;

    private String tpamCouponRefundFee;

    private String tpamRefundTime;

    private String tpamRefundStatus;

    private String tpamDeviceInfo;

    private String tpamSubIsSubscribe;

    private String tpamIsSubscribe;

    private String tpamOpenId;

    private String tpamSubOpenId;

    private String tpamAppid;

    private String tpamSubAppid;

    private String tpamCodeUrl;

    private String tpamImgUrl;

    private String tpamBankType;

    private String tpamStlmDate;

    private String tpamStlmTime;

    private String tpamStlmAmt;

    private String tpamSettleFee;

    private String tpamStlmCecyCode;

    private String tpamCouponFee;

    private String tpamTotalFee;

    private String tpamFeeType;

    private String tpamPayResult;

    private String tpamPayInfo;

    private String tpamAttach;

    private String tpamMchCreateId;

    private String tpamBillno;

    private String recUpdTm;

    private String misc1;

    private String misc2;

    private String misc3;

    private String misc4;

    public String getPagySeqId() {
        return pagySeqId;
    }

    public void setPagySeqId(String pagySeqId) {
        this.pagySeqId = pagySeqId == null ? null : pagySeqId.trim();
    }

    public String getPagySeqTm() {
        return pagySeqTm;
    }

    public void setPagySeqTm(String pagySeqTm) {
        this.pagySeqTm = pagySeqTm == null ? null : pagySeqTm.trim();
    }

    public String getPagyType() {
        return pagyType;
    }

    public void setPagyType(String pagyType) {
        this.pagyType = pagyType == null ? null : pagyType.trim();
    }

    public String getPagyTxnType() {
        return pagyTxnType;
    }

    public void setPagyTxnType(String pagyTxnType) {
        this.pagyTxnType = pagyTxnType == null ? null : pagyTxnType.trim();
    }

    public String getPaySysId() {
        return paySysId;
    }

    public void setPaySysId(String paySysId) {
        this.paySysId = paySysId == null ? null : paySysId.trim();
    }

    public String getOrigSeqId() {
        return origSeqId;
    }

    public void setOrigSeqId(String origSeqId) {
        this.origSeqId = origSeqId == null ? null : origSeqId.trim();
    }

    public String getOrigSeqTm() {
        return origSeqTm;
    }

    public void setOrigSeqTm(String origSeqTm) {
        this.origSeqTm = origSeqTm == null ? null : origSeqTm.trim();
    }

    public String getPayTxnSsn() {
        return payTxnSsn;
    }

    public void setPayTxnSsn(String payTxnSsn) {
        this.payTxnSsn = payTxnSsn == null ? null : payTxnSsn.trim();
    }

    public String getPayTxnTime() {
        return payTxnTime;
    }

    public void setPayTxnTime(String payTxnTime) {
        this.payTxnTime = payTxnTime == null ? null : payTxnTime.trim();
    }

    public String getChlNo() {
        return chlNo;
    }

    public void setChlNo(String chlNo) {
        this.chlNo = chlNo == null ? null : chlNo.trim();
    }

    public String getChlMchtId() {
        return chlMchtId;
    }

    public void setChlMchtId(String chlMchtId) {
        this.chlMchtId = chlMchtId == null ? null : chlMchtId.trim();
    }

    public String getChlTxnSsn() {
        return chlTxnSsn;
    }

    public void setChlTxnSsn(String chlTxnSsn) {
        this.chlTxnSsn = chlTxnSsn == null ? null : chlTxnSsn.trim();
    }

    public String getChlTxnTime() {
        return chlTxnTime;
    }

    public void setChlTxnTime(String chlTxnTime) {
        this.chlTxnTime = chlTxnTime == null ? null : chlTxnTime.trim();
    }

    public String getPayTxnTimeEnd() {
        return payTxnTimeEnd;
    }

    public void setPayTxnTimeEnd(String payTxnTimeEnd) {
        this.payTxnTimeEnd = payTxnTimeEnd == null ? null : payTxnTimeEnd.trim();
    }

    public String getPayBankId() {
        return payBankId;
    }

    public void setPayBankId(String payBankId) {
        this.payBankId = payBankId == null ? null : payBankId.trim();
    }

    public String getPayAccTypeNo() {
        return payAccTypeNo;
    }

    public void setPayAccTypeNo(String payAccTypeNo) {
        this.payAccTypeNo = payAccTypeNo == null ? null : payAccTypeNo.trim();
    }

    public String getPayTxnAccNo() {
        return payTxnAccNo;
    }

    public void setPayTxnAccNo(String payTxnAccNo) {
        this.payTxnAccNo = payTxnAccNo == null ? null : payTxnAccNo.trim();
    }

    public String getPayTxnCustomerInfo() {
        return payTxnCustomerInfo;
    }

    public void setPayTxnCustomerInfo(String payTxnCustomerInfo) {
        this.payTxnCustomerInfo = payTxnCustomerInfo == null ? null : payTxnCustomerInfo.trim();
    }

    public String getPagyBindId() {
        return pagyBindId;
    }

    public void setPagyBindId(String pagyBindId) {
        this.pagyBindId = pagyBindId == null ? null : pagyBindId.trim();
    }

    public String getPagyCetAccNo() {
        return pagyCetAccNo;
    }

    public void setPagyCetAccNo(String pagyCetAccNo) {
        this.pagyCetAccNo = pagyCetAccNo == null ? null : pagyCetAccNo.trim();
    }

    public String getPagyRfdMode() {
        return pagyRfdMode;
    }

    public void setPagyRfdMode(String pagyRfdMode) {
        this.pagyRfdMode = pagyRfdMode == null ? null : pagyRfdMode.trim();
    }

    public String getPagyRfdAmtSum() {
        return pagyRfdAmtSum;
    }

    public void setPagyRfdAmtSum(String pagyRfdAmtSum) {
        this.pagyRfdAmtSum = pagyRfdAmtSum == null ? null : pagyRfdAmtSum.trim();
    }

    public String getPagyRfdCount() {
        return pagyRfdCount;
    }

    public void setPagyRfdCount(String pagyRfdCount) {
        this.pagyRfdCount = pagyRfdCount == null ? null : pagyRfdCount.trim();
    }

    public String getPayTxnAmt() {
        return payTxnAmt;
    }

    public void setPayTxnAmt(String payTxnAmt) {
        this.payTxnAmt = payTxnAmt == null ? null : payTxnAmt.trim();
    }

    public String getPayCcyType() {
        return payCcyType;
    }

    public void setPayCcyType(String payCcyType) {
        this.payCcyType = payCcyType == null ? null : payCcyType.trim();
    }

    public String getPagySteType() {
        return pagySteType;
    }

    public void setPagySteType(String pagySteType) {
        this.pagySteType = pagySteType == null ? null : pagySteType.trim();
    }

    public String getPagySteAmt() {
        return pagySteAmt;
    }

    public void setPagySteAmt(String pagySteAmt) {
        this.pagySteAmt = pagySteAmt == null ? null : pagySteAmt.trim();
    }

    public String getPagySteCcyType() {
        return pagySteCcyType;
    }

    public void setPagySteCcyType(String pagySteCcyType) {
        this.pagySteCcyType = pagySteCcyType == null ? null : pagySteCcyType.trim();
    }

    public String getPagySteDate() {
        return pagySteDate;
    }

    public void setPagySteDate(String pagySteDate) {
        this.pagySteDate = pagySteDate == null ? null : pagySteDate.trim();
    }

    public String getPagyState() {
        return pagyState;
    }

    public void setPagyState(String pagyState) {
        this.pagyState = pagyState == null ? null : pagyState.trim();
    }

    public String getPagyRespCode() {
        return pagyRespCode;
    }

    public void setPagyRespCode(String pagyRespCode) {
        this.pagyRespCode = pagyRespCode == null ? null : pagyRespCode.trim();
    }

    public String getPagyRespMsg() {
        return pagyRespMsg;
    }

    public void setPagyRespMsg(String pagyRespMsg) {
        this.pagyRespMsg = pagyRespMsg == null ? null : pagyRespMsg.trim();
    }

    public String getPagyTmEnd() {
        return pagyTmEnd;
    }

    public void setPagyTmEnd(String pagyTmEnd) {
        this.pagyTmEnd = pagyTmEnd == null ? null : pagyTmEnd.trim();
    }

    public String getTpamType() {
        return tpamType;
    }

    public void setTpamType(String tpamType) {
        this.tpamType = tpamType == null ? null : tpamType.trim();
    }

    public String getTpamAcsType() {
        return tpamAcsType;
    }

    public void setTpamAcsType(String tpamAcsType) {
        this.tpamAcsType = tpamAcsType == null ? null : tpamAcsType.trim();
    }

    public String getTpamVersion() {
        return tpamVersion;
    }

    public void setTpamVersion(String tpamVersion) {
        this.tpamVersion = tpamVersion == null ? null : tpamVersion.trim();
    }

    public String getTpamStatus() {
        return tpamStatus;
    }

    public void setTpamStatus(String tpamStatus) {
        this.tpamStatus = tpamStatus == null ? null : tpamStatus.trim();
    }

    public String getTpamMessage() {
        return tpamMessage;
    }

    public void setTpamMessage(String tpamMessage) {
        this.tpamMessage = tpamMessage == null ? null : tpamMessage.trim();
    }

    public String getTpamResultCode() {
        return tpamResultCode;
    }

    public void setTpamResultCode(String tpamResultCode) {
        this.tpamResultCode = tpamResultCode == null ? null : tpamResultCode.trim();
    }

    public String getTpamErrCode() {
        return tpamErrCode;
    }

    public void setTpamErrCode(String tpamErrCode) {
        this.tpamErrCode = tpamErrCode == null ? null : tpamErrCode.trim();
    }

    public String getTpamErrMsg() {
        return tpamErrMsg;
    }

    public void setTpamErrMsg(String tpamErrMsg) {
        this.tpamErrMsg = tpamErrMsg == null ? null : tpamErrMsg.trim();
    }

    public String getTpamTransactionId() {
        return tpamTransactionId;
    }

    public void setTpamTransactionId(String tpamTransactionId) {
        this.tpamTransactionId = tpamTransactionId == null ? null : tpamTransactionId.trim();
    }

    public String getTpamOutTransactionId() {
        return tpamOutTransactionId;
    }

    public void setTpamOutTransactionId(String tpamOutTransactionId) {
        this.tpamOutTransactionId = tpamOutTransactionId == null ? null : tpamOutTransactionId.trim();
    }

    public String getTpamOutTradeNo() {
        return tpamOutTradeNo;
    }

    public void setTpamOutTradeNo(String tpamOutTradeNo) {
        this.tpamOutTradeNo = tpamOutTradeNo == null ? null : tpamOutTradeNo.trim();
    }

    public String getTpamRefundId() {
        return tpamRefundId;
    }

    public void setTpamRefundId(String tpamRefundId) {
        this.tpamRefundId = tpamRefundId == null ? null : tpamRefundId.trim();
    }

    public String getTpamTradeType() {
        return tpamTradeType;
    }

    public void setTpamTradeType(String tpamTradeType) {
        this.tpamTradeType = tpamTradeType == null ? null : tpamTradeType.trim();
    }

    public String getTpamOrgId() {
        return tpamOrgId;
    }

    public void setTpamOrgId(String tpamOrgId) {
        this.tpamOrgId = tpamOrgId == null ? null : tpamOrgId.trim();
    }

    public String getTpamMchtId() {
        return tpamMchtId;
    }

    public void setTpamMchtId(String tpamMchtId) {
        this.tpamMchtId = tpamMchtId == null ? null : tpamMchtId.trim();
    }

    public String getTpamBody() {
        return tpamBody;
    }

    public void setTpamBody(String tpamBody) {
        this.tpamBody = tpamBody == null ? null : tpamBody.trim();
    }

    public String getTpamRefundChannel() {
        return tpamRefundChannel;
    }

    public void setTpamRefundChannel(String tpamRefundChannel) {
        this.tpamRefundChannel = tpamRefundChannel == null ? null : tpamRefundChannel.trim();
    }

    public String getTpamRefundFee() {
        return tpamRefundFee;
    }

    public void setTpamRefundFee(String tpamRefundFee) {
        this.tpamRefundFee = tpamRefundFee == null ? null : tpamRefundFee.trim();
    }

    public String getTpamCouponRefundFee() {
        return tpamCouponRefundFee;
    }

    public void setTpamCouponRefundFee(String tpamCouponRefundFee) {
        this.tpamCouponRefundFee = tpamCouponRefundFee == null ? null : tpamCouponRefundFee.trim();
    }

    public String getTpamRefundTime() {
        return tpamRefundTime;
    }

    public void setTpamRefundTime(String tpamRefundTime) {
        this.tpamRefundTime = tpamRefundTime == null ? null : tpamRefundTime.trim();
    }

    public String getTpamRefundStatus() {
        return tpamRefundStatus;
    }

    public void setTpamRefundStatus(String tpamRefundStatus) {
        this.tpamRefundStatus = tpamRefundStatus == null ? null : tpamRefundStatus.trim();
    }

    public String getTpamDeviceInfo() {
        return tpamDeviceInfo;
    }

    public void setTpamDeviceInfo(String tpamDeviceInfo) {
        this.tpamDeviceInfo = tpamDeviceInfo == null ? null : tpamDeviceInfo.trim();
    }

    public String getTpamSubIsSubscribe() {
        return tpamSubIsSubscribe;
    }

    public void setTpamSubIsSubscribe(String tpamSubIsSubscribe) {
        this.tpamSubIsSubscribe = tpamSubIsSubscribe == null ? null : tpamSubIsSubscribe.trim();
    }

    public String getTpamIsSubscribe() {
        return tpamIsSubscribe;
    }

    public void setTpamIsSubscribe(String tpamIsSubscribe) {
        this.tpamIsSubscribe = tpamIsSubscribe == null ? null : tpamIsSubscribe.trim();
    }

    public String getTpamOpenId() {
        return tpamOpenId;
    }

    public void setTpamOpenId(String tpamOpenId) {
        this.tpamOpenId = tpamOpenId == null ? null : tpamOpenId.trim();
    }

    public String getTpamSubOpenId() {
        return tpamSubOpenId;
    }

    public void setTpamSubOpenId(String tpamSubOpenId) {
        this.tpamSubOpenId = tpamSubOpenId == null ? null : tpamSubOpenId.trim();
    }

    public String getTpamAppid() {
        return tpamAppid;
    }

    public void setTpamAppid(String tpamAppid) {
        this.tpamAppid = tpamAppid == null ? null : tpamAppid.trim();
    }

    public String getTpamSubAppid() {
        return tpamSubAppid;
    }

    public void setTpamSubAppid(String tpamSubAppid) {
        this.tpamSubAppid = tpamSubAppid == null ? null : tpamSubAppid.trim();
    }

    public String getTpamCodeUrl() {
        return tpamCodeUrl;
    }

    public void setTpamCodeUrl(String tpamCodeUrl) {
        this.tpamCodeUrl = tpamCodeUrl == null ? null : tpamCodeUrl.trim();
    }

    public String getTpamImgUrl() {
        return tpamImgUrl;
    }

    public void setTpamImgUrl(String tpamImgUrl) {
        this.tpamImgUrl = tpamImgUrl == null ? null : tpamImgUrl.trim();
    }

    public String getTpamBankType() {
        return tpamBankType;
    }

    public void setTpamBankType(String tpamBankType) {
        this.tpamBankType = tpamBankType == null ? null : tpamBankType.trim();
    }

    public String getTpamStlmDate() {
        return tpamStlmDate;
    }

    public void setTpamStlmDate(String tpamStlmDate) {
        this.tpamStlmDate = tpamStlmDate == null ? null : tpamStlmDate.trim();
    }

    public String getTpamStlmTime() {
        return tpamStlmTime;
    }

    public void setTpamStlmTime(String tpamStlmTime) {
        this.tpamStlmTime = tpamStlmTime == null ? null : tpamStlmTime.trim();
    }

    public String getTpamStlmAmt() {
        return tpamStlmAmt;
    }

    public void setTpamStlmAmt(String tpamStlmAmt) {
        this.tpamStlmAmt = tpamStlmAmt == null ? null : tpamStlmAmt.trim();
    }

    public String getTpamSettleFee() {
        return tpamSettleFee;
    }

    public void setTpamSettleFee(String tpamSettleFee) {
        this.tpamSettleFee = tpamSettleFee == null ? null : tpamSettleFee.trim();
    }

    public String getTpamStlmCecyCode() {
        return tpamStlmCecyCode;
    }

    public void setTpamStlmCecyCode(String tpamStlmCecyCode) {
        this.tpamStlmCecyCode = tpamStlmCecyCode == null ? null : tpamStlmCecyCode.trim();
    }

    public String getTpamCouponFee() {
        return tpamCouponFee;
    }

    public void setTpamCouponFee(String tpamCouponFee) {
        this.tpamCouponFee = tpamCouponFee == null ? null : tpamCouponFee.trim();
    }

    public String getTpamTotalFee() {
        return tpamTotalFee;
    }

    public void setTpamTotalFee(String tpamTotalFee) {
        this.tpamTotalFee = tpamTotalFee == null ? null : tpamTotalFee.trim();
    }

    public String getTpamFeeType() {
        return tpamFeeType;
    }

    public void setTpamFeeType(String tpamFeeType) {
        this.tpamFeeType = tpamFeeType == null ? null : tpamFeeType.trim();
    }

    public String getTpamPayResult() {
        return tpamPayResult;
    }

    public void setTpamPayResult(String tpamPayResult) {
        this.tpamPayResult = tpamPayResult == null ? null : tpamPayResult.trim();
    }

    public String getTpamPayInfo() {
        return tpamPayInfo;
    }

    public void setTpamPayInfo(String tpamPayInfo) {
        this.tpamPayInfo = tpamPayInfo == null ? null : tpamPayInfo.trim();
    }

    public String getTpamAttach() {
        return tpamAttach;
    }

    public void setTpamAttach(String tpamAttach) {
        this.tpamAttach = tpamAttach == null ? null : tpamAttach.trim();
    }

    public String getTpamMchCreateId() {
        return tpamMchCreateId;
    }

    public void setTpamMchCreateId(String tpamMchCreateId) {
        this.tpamMchCreateId = tpamMchCreateId == null ? null : tpamMchCreateId.trim();
    }

    public String getTpamBillno() {
        return tpamBillno;
    }

    public void setTpamBillno(String tpamBillno) {
        this.tpamBillno = tpamBillno == null ? null : tpamBillno.trim();
    }

    public String getRecUpdTm() {
        return recUpdTm;
    }

    public void setRecUpdTm(String recUpdTm) {
        this.recUpdTm = recUpdTm == null ? null : recUpdTm.trim();
    }

    public String getMisc1() {
        return misc1;
    }

    public void setMisc1(String misc1) {
        this.misc1 = misc1 == null ? null : misc1.trim();
    }

    public String getMisc2() {
        return misc2;
    }

    public void setMisc2(String misc2) {
        this.misc2 = misc2 == null ? null : misc2.trim();
    }

    public String getMisc3() {
        return misc3;
    }

    public void setMisc3(String misc3) {
        this.misc3 = misc3 == null ? null : misc3.trim();
    }

    public String getMisc4() {
        return misc4;
    }

    public void setMisc4(String misc4) {
        this.misc4 = misc4 == null ? null : misc4.trim();
    }

}