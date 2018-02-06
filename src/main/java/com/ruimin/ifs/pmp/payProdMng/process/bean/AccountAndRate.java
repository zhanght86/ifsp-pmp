package com.ruimin.ifs.pmp.payProdMng.process.bean;

public class AccountAndRate {
	/**
	 * 此实体类 仅仅作为页面接收数据转换使用
	 */
	private String acctTypeCode;// 交易类型
	private String rateId;// 费率编号
	private String reteName;// 费率名称

	public String getAcctTypeCode() {
		return acctTypeCode;
	}

	public void setAcctTypeCode(String acctTypeCode) {
		this.acctTypeCode = acctTypeCode;
	}

	public String getRateId() {
		return rateId;
	}

	public void setRateId(String rateId) {
		this.rateId = rateId;
	}

	public String getReteName() {
		return reteName;
	}

	public void setReteName(String reteName) {
		this.reteName = reteName;
	}

}
