package com.ruimin.ifs.pmp.mktActivity.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("TBL_ACTIVE_INF_TMP")
public class ActiveInfTmpVO {
	/**
	 * 表名: TblActiveInfTmp alias 用于页面显示对应中英文信息  .
	 */
	public static final String TABLE_ALIAS = "TblActiveInfTmp";
	/** 列 ACTIVE_NO 的对应中英文信息. */
	public static final String ALIAS_ACTIVE_NO = "activeNo";
	/** 列 ACTIVE_NM 的对应中英文信息. */
	public static final String ALIAS_ACTIVE_NM = "activeNm";
	/** 列 ACTIVE_TYPE 的对应中英文信息. */
	public static final String ALIAS_ACTIVE_TYPE = "10：手续费减免20：实时清算30：消费折扣";
	/** 列 ACTIVE_STAT 的对应中英文信息. */
	public static final String ALIAS_ACTIVE_STAT = "第一位：0-审核流程;1-运行流程;9-结束流程01：审核中02：审核通过03：审核拒绝10：准备完成11：活动中12：暂停中99：已关闭;该状态从审核过后由批量维护";
	/** 列 BUDGET_TP 的对应中英文信息. */
	public static final String ALIAS_BUDGET_TP = "00 - 无（线下送礼）01 - 银行全资（手续费见面，T+0清算）02 - 比例分摊（打折，满减）03 - 顺序分摊（打折，满减）";
	/** 列 ACTIVE_BUDGET 的对应中英文信息. */
	public static final String ALIAS_ACTIVE_BUDGET = "活动总预算，交易时时时检查是否超限";
	/** 列 PLAT_BUDGET 的对应中英文信息. */
	public static final String ALIAS_PLAT_BUDGET = "银行出资上限，当超过上限，由商户承担";
	/** 列 ACTIVE_DESC 的对应中英文信息. */
	public static final String ALIAS_ACTIVE_DESC = "activeDesc";
	/** 列 ACCT_LIMIT_TYPE 的对应中英文信息. */
	public static final String ALIAS_ACCT_LIMIT_TYPE = "acctLimitType";
	/** 列 ACCT_CNT_MAX 的对应中英文信息. */
	public static final String ALIAS_ACCT_CNT_MAX = "acctCntMax";
	/** 列 S_DATE 的对应中英文信息. */
	public static final String ALIAS_S_DATE = "sdate";
	/** 列 E_DATE 的对应中英文信息. */
	public static final String ALIAS_E_DATE = "edate";
	/** 列 CYCLE_FLG 的对应中英文信息. */
	public static final String ALIAS_CYCLE_FLG = "cycleFlg";
	/** 列 ACTIVE_LV 的对应中英文信息. */
	public static final String ALIAS_ACTIVE_LV = "activeLv";
	/** 列 CARD_GP_NO 的对应中英文信息. */
	public static final String ALIAS_CARD_GP_NO = "cardGpNo";
	/** 列 参_与_次_数 的对应中英文信息. */
	public static final String ALIAS_参_与_次_数 = "第一位为类型0：无限制1：全活动周期2：月3：日";
	/** 列 UPDATE_TIME 的对应中英文信息. */
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	/** 列 UPDATE_OPR 的对应中英文信息. */
	public static final String ALIAS_UPDATE_OPR = "updateOpr";
	/** 列 AUDIT_OPR 的对应中英文信息. */
	public static final String ALIAS_AUDIT_OPR = "auditOpr";
	/** 列 AUDIT_TIME 的对应中英文信息. */
	public static final String ALIAS_AUDIT_TIME = "auditTime";
	/** 列 AUDIT_FLAG 的对应中英文信息. */
	public static final String ALIAS_AUDIT_FLAG = "auditFlag";
	/** 列 REMARK 的对应中英文信息. */
	public static final String ALIAS_REMARK = "remark";
	
	//date formats
	
	//columns START
	/** 变量 activeNo . */
	@Id
	private String activeNo;
	/** 变量 activeNm . */
	private String activeNm;
	/** 变量 activeType . */
	private String activeType;
	/** 变量 activeStat . */
	private String activeStat;
	/** 变量 budgetTp . */
	private String budgetTp;
	/** 变量 activeBudget . */
	private String activeBudget;
	/** 变量 platBudget . */
	private String platBudget;
	/** 变量 activeDesc . */
	private String activeDesc;
	/** 变量 acctLimitType . */
	private String acctLimitType;
	/** 变量 acctCntMax . */
	private Integer acctCntMax;
	/** 变量 sdate . */
	private String SDate;
	/** 变量 edate . */
	private String EDate;
	/** 变量 cycleFlg . */
	private String cycleFlg;
	/** 变量 activeLv . */
	private String activeLv;
	/** 变量 cardGpNo . */
	private String cardGpNo;
	/** 变量 参与次数 . */
	private String ptkTimes;
	/** 变量 updateTime . */
	private String updateTime;
	/** 变量 updateOpr . */
	private String updateOpr;
	/** 变量 auditOpr . */
	private String auditOpr;
	/** 变量 auditTime . */
	private String auditTime;
	/** 变量 auditFlag . */
	private String auditFlag;
	/** 变量 remark . */
	private String remark;
	private String syncFlag;
	private String mchtCount;
	private String prdId;
	private String auditId;
	//columns END
	
	public String getSyncFlag() {
		return syncFlag;
	}
	public String getPrdId() {
		return prdId;
	}
	public void setPrdId(String prdId) {
		this.prdId = prdId;
	}
	public String getMchtCount() {
		return mchtCount;
	}
	public void setMchtCount(String mchtCount) {
		this.mchtCount = mchtCount;
	}
	public void setSyncFlag(String syncFlag) {
		this.syncFlag = syncFlag;
	}
    public String getAuditId() {
        return auditId;
    }
    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }
    /**
	 * ActiveNo 置值.
	 * @param  java.lang.String
	 */	
	public void setActiveNo(String value) {
		this.activeNo = value;
	}
	/**
	 * ActiveNo 取值.
	 * @return java.lang.String
	 */
	public String getActiveNo() {
		return this.activeNo;
	}
	/**
	 * ActiveNm 置值.
	 * @param  java.lang.String
	 */	
	public void setActiveNm(String value) {
		this.activeNm = value;
	}
	/**
	 * ActiveNm 取值.
	 * @return java.lang.String
	 */
	public String getActiveNm() {
		return this.activeNm;
	}
	/**
	 * ActiveType 置值.
	 * @param  java.lang.String
	 */	
	public void setActiveType(String value) {
		this.activeType = value;
	}
	/**
	 * ActiveType 取值.
	 * @return java.lang.String
	 */
	public String getActiveType() {
		return this.activeType;
	}
	/**
	 * ActiveStat 置值.
	 * @param  java.lang.String
	 */	
	public void setActiveStat(String value) {
		this.activeStat = value;
	}
	/**
	 * ActiveStat 取值.
	 * @return java.lang.String
	 */
	public String getActiveStat() {
		return this.activeStat;
	}
	/**
	 * BudgetTp 置值.
	 * @param  java.lang.String
	 */	
	public void setBudgetTp(String value) {
		this.budgetTp = value;
	}
	/**
	 * BudgetTp 取值.
	 * @return java.lang.String
	 */
	public String getBudgetTp() {
		return this.budgetTp;
	}
	/**
	 * ActiveBudget 置值.
	 * @param  java.lang.String
	 */	
	public void setActiveBudget(String value) {
		this.activeBudget = value;
	}
	/**
	 * ActiveBudget 取值.
	 * @return java.lang.String
	 */
	public String getActiveBudget() {
		return this.activeBudget;
	}
	/**
	 * PlatBudget 置值.
	 * @param  java.lang.String
	 */	
	public void setPlatBudget(String value) {
		this.platBudget = value;
	}
	/**
	 * PlatBudget 取值.
	 * @return java.lang.String
	 */
	public String getPlatBudget() {
		return this.platBudget;
	}
	/**
	 * ActiveDesc 置值.
	 * @param  java.lang.String
	 */	
	public void setActiveDesc(String value) {
		this.activeDesc = value;
	}
	/**
	 * ActiveDesc 取值.
	 * @return java.lang.String
	 */
	public String getActiveDesc() {
		return this.activeDesc;
	}
	/**
	 * AcctLimitType 置值.
	 * @param  java.lang.String
	 */	
	public void setAcctLimitType(String value) {
		this.acctLimitType = value;
	}
	/**
	 * AcctLimitType 取值.
	 * @return java.lang.String
	 */
	public String getAcctLimitType() {
		return this.acctLimitType;
	}
	/**
	 * AcctCntMax 置值.
	 * @param  java.lang.String
	 */	
	public void setAcctCntMax(Integer value) {
		this.acctCntMax = value;
	}
	/**
	 * AcctCntMax 取值.
	 * @return java.lang.String
	 */
	public Integer getAcctCntMax() {
		return this.acctCntMax;
	}
	/**
	 * Sdate 置值.
	 * @param  java.lang.String
	 */	
	public void setSDate(String value) {
		this.SDate = value;
	}
	/**
	 * Sdate 取值.
	 * @return java.lang.String
	 */
	public String getSDate() {
		return this.SDate;
	}
	/**
	 * Edate 置值.
	 * @param  java.lang.String
	 */	
	public void setEDate(String value) {
		this.EDate = value;
	}
	/**
	 * Edate 取值.
	 * @return java.lang.String
	 */
	public String getEDate() {
		return this.EDate;
	}
	/**
	 * CycleFlg 置值.
	 * @param  java.lang.String
	 */	
	public void setCycleFlg(String value) {
		this.cycleFlg = value;
	}
	/**
	 * CycleFlg 取值.
	 * @return java.lang.String
	 */
	public String getCycleFlg() {
		return this.cycleFlg;
	}
	/**
	 * ActiveLv 置值.
	 * @param  java.lang.String
	 */	
	public void setActiveLv(String value) {
		this.activeLv = value;
	}
	/**
	 * ActiveLv 取值.
	 * @return java.lang.String
	 */
	public String getActiveLv() {
		return this.activeLv;
	}
	/**
	 * CardGpNo 置值.
	 * @param  java.lang.String
	 */	
	public void setCardGpNo(String value) {
		this.cardGpNo = value;
	}
	/**
	 * CardGpNo 取值.
	 * @return java.lang.String
	 */
	public String getCardGpNo() {
		return this.cardGpNo;
	}
	
	public String getPtkTimes() {
		return ptkTimes;
	}
	public void setPtkTimes(String ptkTimes) {
		this.ptkTimes = ptkTimes;
	}
	/**
	 * UpdateTime 置值.
	 * @param  java.lang.String
	 */	
	public void setUpdateTime(String value) {
		this.updateTime = value;
	}
	/**
	 * UpdateTime 取值.
	 * @return java.lang.String
	 */
	public String getUpdateTime() {
		return this.updateTime;
	}
	/**
	 * UpdateOpr 置值.
	 * @param  java.lang.String
	 */	
	public void setUpdateOpr(String value) {
		this.updateOpr = value;
	}
	/**
	 * UpdateOpr 取值.
	 * @return java.lang.String
	 */
	public String getUpdateOpr() {
		return this.updateOpr;
	}
	/**
	 * AuditOpr 置值.
	 * @param  java.lang.String
	 */	
	public void setAuditOpr(String value) {
		this.auditOpr = value;
	}
	/**
	 * AuditOpr 取值.
	 * @return java.lang.String
	 */
	public String getAuditOpr() {
		return this.auditOpr;
	}
	/**
	 * AuditTime 置值.
	 * @param  java.lang.String
	 */	
	public void setAuditTime(String value) {
		this.auditTime = value;
	}
	/**
	 * AuditTime 取值.
	 * @return java.lang.String
	 */
	public String getAuditTime() {
		return this.auditTime;
	}
	/**
	 * AuditFlag 置值.
	 * @param  java.lang.String
	 */	
	public void setAuditFlag(String value) {
		this.auditFlag = value;
	}
	/**
	 * AuditFlag 取值.
	 * @return java.lang.String
	 */
	public String getAuditFlag() {
		return this.auditFlag;
	}
	/**
	 * Remark 置值.
	 * @param  java.lang.String
	 */	
	public void setRemark(String value) {
		this.remark = value;
	}
	/**
	 * Remark 取值.
	 * @return java.lang.String
	 */
	public String getRemark() {
		return this.remark;
	}
}
