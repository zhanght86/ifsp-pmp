package com.ruimin.ifs.pmp.oprMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("PAGY_PAY_TXN_BASE_INFO")
public class PagyPayTxnBaseInfo {
	/** 变量 payTxnCode . */
	@Id
	private String payTxnCode;
	/** 变量 payTxnResp . */
	private String payTxnResp;
	/** 变量 payTxnTypeId . */
	private String payTxnTypeId;
	/** 变量 payTxnStat . */
	private String payTxnStat;
	/** 变量 crtTlr . */
	private String crtTlr;
	/** 变量 crtDateTime . */
	private String crtDateTime;
	/** 变量 lastUpdTlr . */
	private String lastUpdTlr;
	/** 变量 lastUpdDateTime . */
	private String lastUpdDateTime;

	public String getPayTxnCode() {
		return payTxnCode;
	}

	public void setPayTxnCode(String payTxnCode) {
		this.payTxnCode = payTxnCode;
	}

	public String getPayTxnResp() {
		return payTxnResp;
	}

	public void setPayTxnResp(String payTxnResp) {
		this.payTxnResp = payTxnResp;
	}

	public String getPayTxnTypeId() {
		return payTxnTypeId;
	}

	public void setPayTxnTypeId(String payTxnTypeId) {
		this.payTxnTypeId = payTxnTypeId;
	}

	public String getPayTxnStat() {
		return payTxnStat;
	}

	public void setPayTxnStat(String payTxnStat) {
		this.payTxnStat = payTxnStat;
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

}
