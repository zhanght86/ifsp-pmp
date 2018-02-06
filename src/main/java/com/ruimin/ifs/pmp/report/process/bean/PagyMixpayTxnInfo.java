package com.ruimin.ifs.pmp.report.process.bean;


import java.io.Serializable;
import java.util.Date;

public class PagyMixpayTxnInfo implements Serializable {
    private String pagySeqId;

    private String pagySeqTm;

    private String pagyType;

    private String pagyTxnType;

    private String paySysId;

    private String origSeqId;

    private String origSeqTm;

    private String payTxnSsn;

    private String payTxnTime;

    private String pagyAcqNo;

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

    private String pagyExpireTime;

    private String tpamOpenId;

    private String tpamOpenKey;

    private String tpamTimestamp;

    private String tpamOutNo;

    private String tpamPmtTag;

    private String tpamPmtName;

    private String tpamOrdName;

    private Integer tpamOriginalAmount;

    private Integer tpamDiscountAmount;

    private Integer tpamIgnoreAmount;

    private Integer tpamTradeAmount;

    private String tpamTradeAccount;

    private String tpamTradeNo;

    private String tpamRemark;

    private String tpamTag;

    private String tpamNotifyUrl;

    private String tpamAuthCode;

    private String tpamJumpUrl;

    private String tpamBuyerid;

    private String tpamWxOpenId;

    private String tpamWxSubOpenId;

    private String tpamWxAppid;

    private String tpamWxSubAppid;

    private String tpamGoodsTag;

    private String tpamLimitPay;

    private String tpamGoodsDetail;

    private String tpamExtendParams;

    private String tpamErrcode;

    private String tpamMsg;

    private Integer tpamOrdMctId;

    private Integer tpamOrdShopId;

    private String tpamOrdNo;

    private Integer tpamOrdType;

    private String tpamTradeQrcode;

    private Date tpamTradePayTime;

    private Integer tpamStatus;

    private String tpamJsapiPayUrl;

    private String tpamWxappPartnerid;

    private String tpamWxappPrepayid;

    private String tpamWxappPackage;

    private String tpamWxappNoncestr;

    private String tpamSwxappTimestamp;

    private String tpamWxappSign;

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

    public String getPagyAcqNo() {
        return pagyAcqNo;
    }

    public void setPagyAcqNo(String pagyAcqNo) {
        this.pagyAcqNo = pagyAcqNo == null ? null : pagyAcqNo.trim();
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

    public String getPagyExpireTime() {
        return pagyExpireTime;
    }

    public void setPagyExpireTime(String pagyExpireTime) {
        this.pagyExpireTime = pagyExpireTime == null ? null : pagyExpireTime.trim();
    }

    public String getTpamOpenId() {
        return tpamOpenId;
    }

    public void setTpamOpenId(String tpamOpenId) {
        this.tpamOpenId = tpamOpenId == null ? null : tpamOpenId.trim();
    }

    public String getTpamOpenKey() {
        return tpamOpenKey;
    }

    public void setTpamOpenKey(String tpamOpenKey) {
        this.tpamOpenKey = tpamOpenKey == null ? null : tpamOpenKey.trim();
    }

    public String getTpamTimestamp() {
        return tpamTimestamp;
    }

    public void setTpamTimestamp(String tpamTimestamp) {
        this.tpamTimestamp = tpamTimestamp == null ? null : tpamTimestamp.trim();
    }

    public String getTpamOutNo() {
        return tpamOutNo;
    }

    public void setTpamOutNo(String tpamOutNo) {
        this.tpamOutNo = tpamOutNo == null ? null : tpamOutNo.trim();
    }

    public String getTpamPmtTag() {
        return tpamPmtTag;
    }

    public void setTpamPmtTag(String tpamPmtTag) {
        this.tpamPmtTag = tpamPmtTag == null ? null : tpamPmtTag.trim();
    }

    public String getTpamPmtName() {
        return tpamPmtName;
    }

    public void setTpamPmtName(String tpamPmtName) {
        this.tpamPmtName = tpamPmtName == null ? null : tpamPmtName.trim();
    }

    public String getTpamOrdName() {
        return tpamOrdName;
    }

    public void setTpamOrdName(String tpamOrdName) {
        this.tpamOrdName = tpamOrdName == null ? null : tpamOrdName.trim();
    }

    public Integer getTpamOriginalAmount() {
        return tpamOriginalAmount;
    }

    public void setTpamOriginalAmount(Integer tpamOriginalAmount) {
        this.tpamOriginalAmount = tpamOriginalAmount;
    }

    public Integer getTpamDiscountAmount() {
        return tpamDiscountAmount;
    }

    public void setTpamDiscountAmount(Integer tpamDiscountAmount) {
        this.tpamDiscountAmount = tpamDiscountAmount;
    }

    public Integer getTpamIgnoreAmount() {
        return tpamIgnoreAmount;
    }

    public void setTpamIgnoreAmount(Integer tpamIgnoreAmount) {
        this.tpamIgnoreAmount = tpamIgnoreAmount;
    }

    public Integer getTpamTradeAmount() {
        return tpamTradeAmount;
    }

    public void setTpamTradeAmount(Integer tpamTradeAmount) {
        this.tpamTradeAmount = tpamTradeAmount;
    }

    public String getTpamTradeAccount() {
        return tpamTradeAccount;
    }

    public void setTpamTradeAccount(String tpamTradeAccount) {
        this.tpamTradeAccount = tpamTradeAccount == null ? null : tpamTradeAccount.trim();
    }

    public String getTpamTradeNo() {
        return tpamTradeNo;
    }

    public void setTpamTradeNo(String tpamTradeNo) {
        this.tpamTradeNo = tpamTradeNo == null ? null : tpamTradeNo.trim();
    }

    public String getTpamRemark() {
        return tpamRemark;
    }

    public void setTpamRemark(String tpamRemark) {
        this.tpamRemark = tpamRemark == null ? null : tpamRemark.trim();
    }

    public String getTpamTag() {
        return tpamTag;
    }

    public void setTpamTag(String tpamTag) {
        this.tpamTag = tpamTag == null ? null : tpamTag.trim();
    }

    public String getTpamNotifyUrl() {
        return tpamNotifyUrl;
    }

    public void setTpamNotifyUrl(String tpamNotifyUrl) {
        this.tpamNotifyUrl = tpamNotifyUrl == null ? null : tpamNotifyUrl.trim();
    }

    public String getTpamAuthCode() {
        return tpamAuthCode;
    }

    public void setTpamAuthCode(String tpamAuthCode) {
        this.tpamAuthCode = tpamAuthCode == null ? null : tpamAuthCode.trim();
    }

    public String getTpamJumpUrl() {
        return tpamJumpUrl;
    }

    public void setTpamJumpUrl(String tpamJumpUrl) {
        this.tpamJumpUrl = tpamJumpUrl == null ? null : tpamJumpUrl.trim();
    }

    public String getTpamBuyerid() {
        return tpamBuyerid;
    }

    public void setTpamBuyerid(String tpamBuyerid) {
        this.tpamBuyerid = tpamBuyerid == null ? null : tpamBuyerid.trim();
    }

    public String getTpamWxOpenId() {
        return tpamWxOpenId;
    }

    public void setTpamWxOpenId(String tpamWxOpenId) {
        this.tpamWxOpenId = tpamWxOpenId == null ? null : tpamWxOpenId.trim();
    }

    public String getTpamWxSubOpenId() {
        return tpamWxSubOpenId;
    }

    public void setTpamWxSubOpenId(String tpamWxSubOpenId) {
        this.tpamWxSubOpenId = tpamWxSubOpenId == null ? null : tpamWxSubOpenId.trim();
    }

    public String getTpamWxAppid() {
        return tpamWxAppid;
    }

    public void setTpamWxAppid(String tpamWxAppid) {
        this.tpamWxAppid = tpamWxAppid == null ? null : tpamWxAppid.trim();
    }

    public String getTpamWxSubAppid() {
        return tpamWxSubAppid;
    }

    public void setTpamWxSubAppid(String tpamWxSubAppid) {
        this.tpamWxSubAppid = tpamWxSubAppid == null ? null : tpamWxSubAppid.trim();
    }

    public String getTpamGoodsTag() {
        return tpamGoodsTag;
    }

    public void setTpamGoodsTag(String tpamGoodsTag) {
        this.tpamGoodsTag = tpamGoodsTag == null ? null : tpamGoodsTag.trim();
    }

    public String getTpamLimitPay() {
        return tpamLimitPay;
    }

    public void setTpamLimitPay(String tpamLimitPay) {
        this.tpamLimitPay = tpamLimitPay == null ? null : tpamLimitPay.trim();
    }

    public String getTpamGoodsDetail() {
        return tpamGoodsDetail;
    }

    public void setTpamGoodsDetail(String tpamGoodsDetail) {
        this.tpamGoodsDetail = tpamGoodsDetail == null ? null : tpamGoodsDetail.trim();
    }

    public String getTpamExtendParams() {
        return tpamExtendParams;
    }

    public void setTpamExtendParams(String tpamExtendParams) {
        this.tpamExtendParams = tpamExtendParams == null ? null : tpamExtendParams.trim();
    }

    public String getTpamErrcode() {
        return tpamErrcode;
    }

    public void setTpamErrcode(String tpamErrcode) {
        this.tpamErrcode = tpamErrcode == null ? null : tpamErrcode.trim();
    }

    public String getTpamMsg() {
        return tpamMsg;
    }

    public void setTpamMsg(String tpamMsg) {
        this.tpamMsg = tpamMsg == null ? null : tpamMsg.trim();
    }

    public Integer getTpamOrdMctId() {
        return tpamOrdMctId;
    }

    public void setTpamOrdMctId(Integer tpamOrdMctId) {
        this.tpamOrdMctId = tpamOrdMctId;
    }

    public Integer getTpamOrdShopId() {
        return tpamOrdShopId;
    }

    public void setTpamOrdShopId(Integer tpamOrdShopId) {
        this.tpamOrdShopId = tpamOrdShopId;
    }

    public String getTpamOrdNo() {
        return tpamOrdNo;
    }

    public void setTpamOrdNo(String tpamOrdNo) {
        this.tpamOrdNo = tpamOrdNo == null ? null : tpamOrdNo.trim();
    }

    public Integer getTpamOrdType() {
        return tpamOrdType;
    }

    public void setTpamOrdType(Integer tpamOrdType) {
        this.tpamOrdType = tpamOrdType;
    }

    public String getTpamTradeQrcode() {
        return tpamTradeQrcode;
    }

    public void setTpamTradeQrcode(String tpamTradeQrcode) {
        this.tpamTradeQrcode = tpamTradeQrcode == null ? null : tpamTradeQrcode.trim();
    }

    public Date getTpamTradePayTime() {
        return tpamTradePayTime;
    }

    public void setTpamTradePayTime(Date tpamTradePayTime) {
        this.tpamTradePayTime = tpamTradePayTime;
    }

    public Integer getTpamStatus() {
        return tpamStatus;
    }

    public void setTpamStatus(Integer tpamStatus) {
        this.tpamStatus = tpamStatus;
    }

    public String getTpamJsapiPayUrl() {
        return tpamJsapiPayUrl;
    }

    public void setTpamJsapiPayUrl(String tpamJsapiPayUrl) {
        this.tpamJsapiPayUrl = tpamJsapiPayUrl == null ? null : tpamJsapiPayUrl.trim();
    }

    public String getTpamWxappPartnerid() {
        return tpamWxappPartnerid;
    }

    public void setTpamWxappPartnerid(String tpamWxappPartnerid) {
        this.tpamWxappPartnerid = tpamWxappPartnerid == null ? null : tpamWxappPartnerid.trim();
    }

    public String getTpamWxappPrepayid() {
        return tpamWxappPrepayid;
    }

    public void setTpamWxappPrepayid(String tpamWxappPrepayid) {
        this.tpamWxappPrepayid = tpamWxappPrepayid == null ? null : tpamWxappPrepayid.trim();
    }

    public String getTpamWxappPackage() {
        return tpamWxappPackage;
    }

    public void setTpamWxappPackage(String tpamWxappPackage) {
        this.tpamWxappPackage = tpamWxappPackage == null ? null : tpamWxappPackage.trim();
    }

    public String getTpamWxappNoncestr() {
        return tpamWxappNoncestr;
    }

    public void setTpamWxappNoncestr(String tpamWxappNoncestr) {
        this.tpamWxappNoncestr = tpamWxappNoncestr == null ? null : tpamWxappNoncestr.trim();
    }

    public String getTpamSwxappTimestamp() {
        return tpamSwxappTimestamp;
    }

    public void setTpamSwxappTimestamp(String tpamSwxappTimestamp) {
        this.tpamSwxappTimestamp = tpamSwxappTimestamp == null ? null : tpamSwxappTimestamp.trim();
    }

    public String getTpamWxappSign() {
        return tpamWxappSign;
    }

    public void setTpamWxappSign(String tpamWxappSign) {
        this.tpamWxappSign = tpamWxappSign == null ? null : tpamWxappSign.trim();
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