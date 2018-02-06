package com.ruimin.ifs.pmp.oprMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("PAGY_PAY_TXN_REL")
public class PagyPayTxnRel {
	@Id
	private String pagyTxnCode; // 通道交易编号
	@Id
	private String payTxnCode; // 支付交易编号
	@Id
	private String pagyNo; // 通道编号

	public String getPagyTxnCode() {
		return pagyTxnCode;
	}

	public void setPagyTxnCode(String pagyTxnCode) {
		this.pagyTxnCode = pagyTxnCode;
	}

	public String getPayTxnCode() {
		return payTxnCode;
	}

	public void setPayTxnCode(String payTxnCode) {
		this.payTxnCode = payTxnCode;
	}

	public String getPagyNo() {
		return pagyNo;
	}

	public void setPagyNo(String pagyNo) {
		this.pagyNo = pagyNo;
	}

}
