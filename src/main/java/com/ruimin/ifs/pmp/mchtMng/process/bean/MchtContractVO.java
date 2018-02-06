package com.ruimin.ifs.pmp.mchtMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("PBS_MCHT_CONTR_INFO_TMP")
public class MchtContractVO {

	@Id
	private String conId;/* 合同编号 */
	/* 商户编号 */
	private String mchtId;
	// /*商户简称*/
	// private String mchtSimpleName;
	/* 纸质合同编号 */
	private String paperConId;
	/* 合同生效日期 */
	private String startDate;
	/* 合同失效日期 */
	private String endDate;
	/* 合同延期标志 */
	private String extendFlag;
	/* 合同期限 */
	private String conTerm;
	/* 合同状态 */
	private String conState;
	/* 结算方式 */
	private String setlType;
	/* 结算标志 */
	private String setlSymbol;
	/* 结算周期 */
	private String setlCycle;
	/* 指定结算日 */
	private String specSetlDay;
	/* 结算账户类型 */
	private String setlAcctType;
	/* 结算账户户名 */
	private String setlAcctName;
	/* 结算账户账号 */
	private String setlAcctNo;
	/* 结算账户证件类 */
	private String setlCertType;
	/* 结算账户证件号 */
	private String setlCertNo;
	/**2017-06-15 证通需求新增*/
	/* 结算账户开户行号 */
	private String setlAcctBankNo;
	/* 结算账户开户行名 */
	private String setlAcctBankName;
	/* 结算账户开户行区划代码 */
	private String setlAcctAreaCode;
	   
	
	
	private String auditId; // 审核信息编号
	private String syncState;// 同步标志

	/* 开户机构 */
	private String setlAcctInstitute;
	/* 创建柜员 */
	private String crtTlr;
	/* 创建日期 */
	private String crtDateTime;
	/* 最后更新柜员 */
	private String lastUpdTlr;
	/* 最后更新时间 */
	private String lastUpdDateTime;
	
	private String setlBankPhone;//银行预留手机号
	
	private String accountType;
	
	private String accountBoss;
	
	
	
    public String getAccountType() {
        return accountType;
    }


    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }


    public String getAccountBoss() {
        return accountBoss;
    }


    public void setAccountBoss(String accountBoss) {
        this.accountBoss = accountBoss;
    }


    public String getSetlBankPhone() {
        return setlBankPhone;
    }

    
    public void setSetlBankPhone(String setlBankPhone) {
        this.setlBankPhone = setlBankPhone;
    }

    public String getConId() {
		return conId;
	}

	public void setConId(String conId) {
		this.conId = conId;
	}

	public String getMchtId() {
		return mchtId;
	}

	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}

	public String getPaperConId() {
		return paperConId;
	}

	public void setPaperConId(String paperConId) {
		this.paperConId = paperConId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getExtendFlag() {
		return extendFlag;
	}

	public void setExtendFlag(String extendFlag) {
		this.extendFlag = extendFlag;
	}

	public String getConTerm() {
		return conTerm;
	}

	public void setConTerm(String conTerm) {
		this.conTerm = conTerm;
	}

	public String getConState() {
		return conState;
	}

	public void setConState(String conState) {
		this.conState = conState;
	}

	public String getSetlType() {
		return setlType;
	}

	public void setSetlType(String setlType) {
		this.setlType = setlType;
	}

	public String getSetlSymbol() {
		return setlSymbol;
	}

	public void setSetlSymbol(String setlSymbol) {
		this.setlSymbol = setlSymbol;
	}

	public String getSetlCycle() {
		return setlCycle;
	}

	public void setSetlCycle(String setlCycle) {
		this.setlCycle = setlCycle;
	}

	public String getSpecSetlDay() {
		return specSetlDay;
	}

	public void setSpecSetlDay(String specSetlDay) {
		this.specSetlDay = specSetlDay;
	}

	public String getSetlAcctType() {
		return setlAcctType;
	}

	public void setSetlAcctType(String setlAcctType) {
		this.setlAcctType = setlAcctType;
	}

	public String getSetlAcctName() {
		return setlAcctName;
	}

	public void setSetlAcctName(String setlAcctName) {
		this.setlAcctName = setlAcctName;
	}

	public String getSetlAcctNo() {
		return setlAcctNo;
	}

	public void setSetlAcctNo(String setlAcctNo) {
		this.setlAcctNo = setlAcctNo;
	}

	public String getSetlCertType() {
		return setlCertType;
	}

	public void setSetlCertType(String setlCertType) {
		this.setlCertType = setlCertType;
	}

	public String getSetlCertNo() {
		return setlCertNo;
	}

	public void setSetlCertNo(String setlCertNo) {
		this.setlCertNo = setlCertNo;
	}

	public String getSetlAcctInstitute() {
		return setlAcctInstitute;
	}

	public void setSetlAcctInstitute(String setlAcctInstitute) {
		this.setlAcctInstitute = setlAcctInstitute;
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

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public String getSyncState() {
		return syncState;
	}

	public void setSyncState(String syncState) {
		this.syncState = syncState;
	}

	public String getSetlAcctBankNo() {
		return setlAcctBankNo;
	}

	public void setSetlAcctBankNo(String setlAcctBankNo) {
		this.setlAcctBankNo = setlAcctBankNo;
	}

	public String getSetlAcctBankName() {
		return setlAcctBankName;
	}

	public void setSetlAcctBankName(String setlAcctBankName) {
		this.setlAcctBankName = setlAcctBankName;
	}

	public String getSetlAcctAreaCode() {
		return setlAcctAreaCode;
	}

	public void setSetlAcctAreaCode(String setlAcctAreaCode) {
		this.setlAcctAreaCode = setlAcctAreaCode;
	}

}
