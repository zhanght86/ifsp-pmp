package com.ruimin.ifs.pmp.txnQuery.process.bean;

/**
 * 统计结果实体对象
 */
public class CountResultVO {
	// 交易日期
	private String txnDate;
	// 接入方式
	private String txnTemlType;
	// 统计总数
	private String totalItems;
	// 成功数
	private String successItems;
	// 失败数
	private String failItems;
	// 成功率
	private String successRate;
	// 失败率
	private String failRate;
	// 支付通道
	private String pagyNo;
	// 通道核心交易时间
	private String coreTxnTime;

	public String getPagyNo() {
		return pagyNo;
	}

	public void setPagyNo(String pagyNo) {
		this.pagyNo = pagyNo;
	}

	public String getCoreTxnTime() {
		return coreTxnTime;
	}

	public void setCoreTxnTime(String coreTxnTime) {
		this.coreTxnTime = coreTxnTime;
	}

	/**
	 * @return the txnDate
	 */
	public String getTxnDate() {
		return txnDate;
	}

	/**
	 * @param txnDate
	 *            the txnDate to set
	 */
	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}

	/**
	 * @return the txnTemlType
	 */
	public String getTxnTemlType() {
		return txnTemlType;
	}

	/**
	 * @param txnTemlType
	 *            the txnTemlType to set
	 */
	public void setTxnTemlType(String txnTemlType) {
		this.txnTemlType = txnTemlType;
	}

	/**
	 * @return the totalItems
	 */
	public String getTotalItems() {
		return totalItems;
	}

	/**
	 * @param totalItems
	 *            the totalItems to set
	 */
	public void setTotalItems(String totalItems) {
		this.totalItems = totalItems;
	}

	/**
	 * @return the successItems
	 */
	public String getSuccessItems() {
		return successItems;
	}

	/**
	 * @param successItems
	 *            the successItems to set
	 */
	public void setSuccessItems(String successItems) {
		this.successItems = successItems;
	}

	/**
	 * @return the failItems
	 */
	public String getFailItems() {
		return failItems;
	}

	/**
	 * @param failItems
	 *            the failItems to set
	 */
	public void setFailItems(String failItems) {
		this.failItems = failItems;
	}

	/**
	 * @return the successRate
	 */
	public String getSuccessRate() {
		return successRate;
	}

	/**
	 * @param successRate
	 *            the successRate to set
	 */
	public void setSuccessRate(String successRate) {
		this.successRate = successRate;
	}

	/**
	 * @return the failRate
	 */
	public String getFailRate() {
		return failRate;
	}

	/**
	 * @param failRate
	 *            the failRate to set
	 */
	public void setFailRate(String failRate) {
		this.failRate = failRate;
	}

}
