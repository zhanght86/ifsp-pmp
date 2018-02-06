package com.ruimin.ifs.pmp.mktActivity.process.bean;

import com.ruimin.ifs.rql.annotation.Table;


@Table("TBL_ACCT_GP_CONF")
public class AcctGpConfVO {
	private String gpTpNo;//分组编号
	private String gpTpNm;//分组名称
	private String gpSeq;//小组序号
	private String gpNm;//小组名称
	private String memTp;//成员类型
	private String memNm;//账户类型名称
	private String acctTpLen;//账户类型长度
	private String acctTp;//账户类型
	
	private String gpNumber;//小组数量
	private String acState;//活动状态
	
	/** 变量 activeNo . */
	private String activeNo;
	/** 变量 activeNm . */
	private String activeNm;
	/** 变量 activeType . */
	private String activeType;
	/** 变量 sdate . */
	private String SDate;
	/** 变量 edate . */
	private String EDate;
	
	public String getActiveNo() {
		return activeNo;
	}
	public void setActiveNo(String activeNo) {
		this.activeNo = activeNo;
	}
	public String getActiveNm() {
		return activeNm;
	}
	public void setActiveNm(String activeNm) {
		this.activeNm = activeNm;
	}
	public String getActiveType() {
		return activeType;
	}
	public void setActiveType(String activeType) {
		this.activeType = activeType;
	}
	public String getSDate() {
		return SDate;
	}
	public void setSDate(String sDate) {
		SDate = sDate;
	}
	public String getEDate() {
		return EDate;
	}
	public void setEDate(String eDate) {
		EDate = eDate;
	}
	public String getAcState() {
		return acState;
	}
	public void setAcState(String acState) {
		this.acState = acState;
	}
	public String getGpTpNo() {
		return gpTpNo;
	}
	public void setGpTpNo(String gpTpNo) {
		this.gpTpNo = gpTpNo;
	}
	public String getGpTpNm() {
		return gpTpNm;
	}
	public void setGpTpNm(String gpTpNm) {
		this.gpTpNm = gpTpNm;
	}
	public String getGpSeq() {
		return gpSeq;
	}
	public void setGpSeq(String gpSeq) {
		this.gpSeq = gpSeq;
	}
	public String getGpNm() {
		return gpNm;
	}
	public void setGpNm(String gpNm) {
		this.gpNm = gpNm;
	}
	public String getMemTp() {
		return memTp;
	}
	public void setMemTp(String memTp) {
		this.memTp = memTp;
	}
	public String getMemNm() {
		return memNm;
	}
	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}
	public String getAcctTpLen() {
		return acctTpLen;
	}
	public void setAcctTpLen(String acctTpLen) {
		this.acctTpLen = acctTpLen;
	}
	public String getAcctTp() {
		return acctTp;
	}
	public void setAcctTp(String acctTp) {
		this.acctTp = acctTp;
	}
	public String getGpNumber() {
		return gpNumber;
	}
	public void setGpNumber(String gpNumber) {
		this.gpNumber = gpNumber;
	}

	
	
}
