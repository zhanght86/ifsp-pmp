package com.ruimin.ifs.pmp.payProdMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("PBS_PROD_REL_TXN_TYPE")
public class PbsProdRelTxnTypeVO {
	@Id
	private String prodId;
	private String txnTypeCode;
	private String dataState;
	private String crtTlr;
	private String crtDateTime;
	private String lastUpdDateTime;
	private String lastUptTlr;
	private String signedNum; // 签约总数

	public String getSignedNum() {
		return signedNum;
	}

	public void setSignedNum(String signedNum) {
		this.signedNum = signedNum;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getTxnTypeCode() {
		return txnTypeCode;
	}

	public void setTxnTypeCode(String txnTypeCode) {
		this.txnTypeCode = txnTypeCode;
	}

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
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

	public String getLastUpdDateTime() {
		return lastUpdDateTime;
	}

	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}

	public String getLastUptTlr() {
		return lastUptTlr;
	}

	public void setLastUptTlr(String lastUptTlr) {
		this.lastUptTlr = lastUptTlr;
	}

}
