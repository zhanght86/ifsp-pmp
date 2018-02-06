package com.ruimin.ifs.pmp.chnlMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("PAGY_TXN_TYPE_INFO")
public class PagyTxnTypeInfoVO {
	@Id
	private String payTxnTypeId;
	private String payTxnTypeName;
	private String payTxnTypeDesc;
	private String crtTlr;
	private String crtDateTime;
	private String lastUpdTlr;
	private String lastUpdDateTime;

	public String getPayTxnTypeId() {
		return payTxnTypeId;
	}

	public void setPayTxnTypeId(String payTxnTypeId) {
		this.payTxnTypeId = payTxnTypeId;
	}

	public String getPayTxnTypeName() {
		return payTxnTypeName;
	}

	public void setPayTxnTypeName(String payTxnTypeName) {
		this.payTxnTypeName = payTxnTypeName;
	}

	public String getPayTxnTypeDesc() {
		return payTxnTypeDesc;
	}

	public void setPayTxnTypeDesc(String payTxnTypeDesc) {
		this.payTxnTypeDesc = payTxnTypeDesc;
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
