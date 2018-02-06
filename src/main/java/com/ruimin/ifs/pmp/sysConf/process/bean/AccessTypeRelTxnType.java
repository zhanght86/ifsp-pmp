package com.ruimin.ifs.pmp.sysConf.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("pbs_rel_access_type_txn_type")
public class AccessTypeRelTxnType {

	// 关联编号
	@Id
	private String relNo;
	// 接入方式编号
	private String accessTypeCode;
	// 交易类型编号
	private String txnTypeCode;
	// 交易类型名称
	private String txnTypeName;
	// 数据有效状态
	private String dataState;
	// 创建柜员
	private String crtTlr;
	// 创建日期时间
	private String crtDateTime;
	// 最近更新柜员
	private String lastUpdTlr;
	// 最近更新日期时间
	private String lastUpdDateTime;

	public String getAccessTypeCode() {
		return accessTypeCode;
	}

	public void setAccessTypeCode(String accessTypeCode) {
		this.accessTypeCode = accessTypeCode;
	}

	public String getTxnTypeCode() {
		return txnTypeCode;
	}

	public void setTxnTypeCode(String txnTypeCode) {
		this.txnTypeCode = txnTypeCode;
	}

	public String getTxnTypeName() {
		return txnTypeName;
	}

	public void setTxnTypeName(String txnTypeName) {
		this.txnTypeName = txnTypeName;
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

	public String getRelNo() {
		return relNo;
	}

	public void setRelNo(String relNo) {
		this.relNo = relNo;
	}

}
