package com.ruimin.ifs.pmp.payProdMng.process.bean;

public class PbsProdInfo {
	/**
	 * 此实体类 仅仅作为页面接收数据转换使用
	 */
	private String txnTypeCode;// 交易类型
	private String txnTypeCodeName;// 交易类型名称
	private String acctTypeCode;// 账户类型
	private String acctTypeCodeName;// 账户类型名称
	private String recordState;// 数据状态

	public String getRecordState() {
		return recordState;
	}

	public void setRecordState(String recordState) {
		this.recordState = recordState;
	}

	public String getTxnTypeCode() {
		return txnTypeCode;
	}

	public void setTxnTypeCode(String txnTypeCode) {
		this.txnTypeCode = txnTypeCode;
	}

	public String getAcctTypeCode() {
		return acctTypeCode;
	}

	public void setAcctTypeCode(String acctTypeCode) {
		this.acctTypeCode = acctTypeCode;
	}

	public String getTxnTypeCodeName() {
		return txnTypeCodeName;
	}

	public void setTxnTypeCodeName(String txnTypeCodeName) {
		this.txnTypeCodeName = txnTypeCodeName;
	}

	public String getAcctTypeCodeName() {
		return acctTypeCodeName;
	}

	public void setAcctTypeCodeName(String acctTypeCodeName) {
		this.acctTypeCodeName = acctTypeCodeName;
	}

}
