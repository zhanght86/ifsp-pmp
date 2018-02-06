/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.chnlMng.process.bean 
 * FileName: BasicTest.java
 * Author:   chenqilei
 * Date:     2016年7月28日 下午7:07:24
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月28日下午7:07:24                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.bean;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月28日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
public class PagyCoreTxnRecord {
	private String coreTxnSsn;
	private String coreTxnTime;
	private String status;
	private String chlTxnSsn;
	private String chlTxnTime;
	private String pagyTxnSsn;
	private String pagyTxnTime;
	private String chlNo;
	private String payTxnCode;
	private String payTxnName;
	private String payTxnTypeId;
	private String acctTypeNo;
	private String txnAmt;
	private String chlMerId;
	private String acctNo;
	private String currencyCode;
	private String pagyTxnCode;
	private String pagyTxnName;
	private String txnType;
	private String pagyNo;
	private String pagyType;
	private String pagyMerId;
	private String pagySubMerId;
	private String pagySubMerName;
	private String pagyMerMcc;
	private String respCode;
	private String respMsg;
	private String spendTime;
	private String pagySpendTime;
	private String serverName;
	private String serverIp;
	private String origPagyTxnSsn;
	private String origPagyTxnTime;
	private String crtTlr;
	private String crtDateTime;
	private String lastUpdTlr;
	private String lastUpdDateTime;

	/** 附加字段 */
	private String pagyName;
	private String chlName;
	private String acctTypeName;
	private String payTxnTypeName;

	public String getPayTxnTypeName() {
		return payTxnTypeName;
	}

	public void setPayTxnTypeName(String payTxnTypeName) {
		this.payTxnTypeName = payTxnTypeName;
	}

	public String getCoreTxnSsn() {
		return coreTxnSsn;
	}

	public void setCoreTxnSsn(String coreTxnSsn) {
		this.coreTxnSsn = coreTxnSsn;
	}

	public String getCoreTxnTime() {
		return coreTxnTime;
	}

	public void setCoreTxnTime(String coreTxnTime) {
		this.coreTxnTime = coreTxnTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChlTxnSsn() {
		return chlTxnSsn;
	}

	public void setChlTxnSsn(String chlTxnSsn) {
		this.chlTxnSsn = chlTxnSsn;
	}

	public String getChlTxnTime() {
		return chlTxnTime;
	}

	public void setChlTxnTime(String chlTxnTime) {
		this.chlTxnTime = chlTxnTime;
	}

	public String getPagyTxnSsn() {
		return pagyTxnSsn;
	}

	public void setPagyTxnSsn(String pagyTxnSsn) {
		this.pagyTxnSsn = pagyTxnSsn;
	}

	public String getPagyTxnTime() {
		return pagyTxnTime;
	}

	public void setPagyTxnTime(String pagyTxnTime) {
		this.pagyTxnTime = pagyTxnTime;
	}

	public String getChlNo() {
		return chlNo;
	}

	public void setChlNo(String chlNo) {
		this.chlNo = chlNo;
	}

	public String getPayTxnCode() {
		return payTxnCode;
	}

	public void setPayTxnCode(String payTxnCode) {
		this.payTxnCode = payTxnCode;
	}

	public String getPayTxnName() {
		return payTxnName;
	}

	public void setPayTxnName(String payTxnName) {
		this.payTxnName = payTxnName;
	}

	public String getPayTxnTypeId() {
		return payTxnTypeId;
	}

	public void setPayTxnTypeId(String payTxnTypeId) {
		this.payTxnTypeId = payTxnTypeId;
	}

	public String getAcctTypeNo() {
		return acctTypeNo;
	}

	public void setAcctTypeNo(String acctTypeNo) {
		this.acctTypeNo = acctTypeNo;
	}

	public String getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(String txnAmt) {
		this.txnAmt = txnAmt;
	}

	public String getChlMerId() {
		return chlMerId;
	}

	public void setChlMerId(String chlMerId) {
		this.chlMerId = chlMerId;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getPagyTxnCode() {
		return pagyTxnCode;
	}

	public void setPagyTxnCode(String pagyTxnCode) {
		this.pagyTxnCode = pagyTxnCode;
	}

	public String getPagyTxnName() {
		return pagyTxnName;
	}

	public void setPagyTxnName(String pagyTxnName) {
		this.pagyTxnName = pagyTxnName;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getPagyNo() {
		return pagyNo;
	}

	public void setPagyNo(String pagyNo) {
		this.pagyNo = pagyNo;
	}

	public String getPagyType() {
		return pagyType;
	}

	public void setPagyType(String pagyType) {
		this.pagyType = pagyType;
	}

	public String getPagyMerId() {
		return pagyMerId;
	}

	public void setPagyMerId(String pagyMerId) {
		this.pagyMerId = pagyMerId;
	}

	public String getPagySubMerId() {
		return pagySubMerId;
	}

	public void setPagySubMerId(String pagySubMerId) {
		this.pagySubMerId = pagySubMerId;
	}

	public String getPagySubMerName() {
		return pagySubMerName;
	}

	public void setPagySubMerName(String pagySubMerName) {
		this.pagySubMerName = pagySubMerName;
	}

	public String getPagyMerMcc() {
		return pagyMerMcc;
	}

	public void setPagyMerMcc(String pagyMerMcc) {
		this.pagyMerMcc = pagyMerMcc;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getSpendTime() {
		return spendTime;
	}

	public void setSpendTime(String spendTime) {
		this.spendTime = spendTime;
	}

	public String getPagySpendTime() {
		return pagySpendTime;
	}

	public void setPagySpendTime(String pagySpendTime) {
		this.pagySpendTime = pagySpendTime;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getOrigPagyTxnSsn() {
		return origPagyTxnSsn;
	}

	public void setOrigPagyTxnSsn(String origPagyTxnSsn) {
		this.origPagyTxnSsn = origPagyTxnSsn;
	}

	public String getOrigPagyTxnTime() {
		return origPagyTxnTime;
	}

	public void setOrigPagyTxnTime(String origPagyTxnTime) {
		this.origPagyTxnTime = origPagyTxnTime;
	}

	public String getCrtTlr() {
		return crtTlr;
	}

	public void setCrtTlr(String crtTlr) {
		this.crtTlr = crtTlr;
	}

	public String getCrtDateTime() {
		return crtDateTime;
	}

	public void setCrtDateTime(String crtDateTime) {
		this.crtDateTime = crtDateTime;
	}

	public String getLastUpdTlr() {
		return lastUpdTlr;
	}

	public void setLastUpdTlr(String lastUpdTlr) {
		this.lastUpdTlr = lastUpdTlr;
	}

	public String getLastUpdDateTime() {
		return lastUpdDateTime;
	}

	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}

	public String getPagyName() {
		return pagyName;
	}

	public void setPagyName(String pagyName) {
		this.pagyName = pagyName;
	}

	public String getChlName() {
		return chlName;
	}

	public void setChlName(String chlName) {
		this.chlName = chlName;
	}

	public String getAcctTypeName() {
		return acctTypeName;
	}

	public void setAcctTypeName(String acctTypeName) {
		this.acctTypeName = acctTypeName;
	}

}
