package com.ruimin.ifs.pmp.oprMng.process.bean;

/**
 * 
 * 通道核心配置管理-实体类
 * 
 * @author zhangjc
 *
 */
public class PagyCoreMngVO {
	private String pagyNo; // 通道编号
	private String pagyName; // 通道名称
	private String pagyTxnCode; // 通道交易编号
	private String pagyTxnName; // 通道交易名称
	private String acctTypeNo; // 账户类型编号
	private String acctTypeName; // 账户类型名称
	private String mainMchtNo; // 主商户编号
	private String mainMchtName; // 主商户名称
	private String payTxnCode; // 接入交易编号
	private String payTxnResp; // 接入交易名称
	private String needSetCol; // 需配置的列

	public String getPagyNo() {
		return pagyNo;
	}

	public void setPagyNo(String pagyNo) {
		this.pagyNo = pagyNo;
	}

	public String getPagyName() {
		return pagyName;
	}

	public void setPagyName(String pagyName) {
		this.pagyName = pagyName;
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

	public String getAcctTypeNo() {
		return acctTypeNo;
	}

	public void setAcctTypeNo(String acctTypeNo) {
		this.acctTypeNo = acctTypeNo;
	}

	public String getAcctTypeName() {
		return acctTypeName;
	}

	public void setAcctTypeName(String acctTypeName) {
		this.acctTypeName = acctTypeName;
	}

	public String getMainMchtNo() {
		return mainMchtNo;
	}

	public void setMainMchtNo(String mainMchtNo) {
		this.mainMchtNo = mainMchtNo;
	}

	public String getMainMchtName() {
		return mainMchtName;
	}

	public void setMainMchtName(String mainMchtName) {
		this.mainMchtName = mainMchtName;
	}

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

	public String getNeedSetCol() {
		return needSetCol;
	}

	public void setNeedSetCol(String needSetCol) {
		this.needSetCol = needSetCol;
	}
}
