package com.ruimin.ifs.pmp.mktActivity.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("TBL_MCHT_LV_INF_TMP")
public class MchtLvInfTmpVO {
	
	/**
	 * 表名: TblMchtLvInfTmp alias 用于页面显示对应中英文信息  .
	 */
	public static final String TABLE_ALIAS = "TblMchtLvInfTmp";
	/** 列 MHCT_ID 的对应中英文信息. */
	public static final String ALIAS_MHCT_ID = "mchtId";
	/** 列 MHCT_NM 的对应中英文信息. */
	public static final String ALIAS_MHCT_NM = "mchtNm";
	/** 列 MCHT_LV_L 的对应中英文信息. */
	public static final String ALIAS_MCHT_LV_L = "内管配置静态参数：01 ： 等级 —1；（最高）02 ： 等级 —2；03 ： 等级 —3；04 ： 等级 —4；05 ： 等级 —5。（最低）";
	/** 列 MCHT_LV_C 的对应中英文信息. */
	public static final String ALIAS_MCHT_LV_C = "内管配置静态参数：01 ： 等级 —1；（最高）02 ： 等级 —2；03 ： 等级 —3；04 ： 等级 —4；05 ： 等级 —5。（最低）";
	/** 列 DAY_DEPOSIT_L 的对应中英文信息. */
	public static final String ALIAS_DAY_DEPOSIT_L = "dayDepositL";
	/** 列 BUSI_CNT_L 的对应中英文信息. */
	public static final String ALIAS_BUSI_CNT_L = "busiCntL";
	/** 列 BUSI_AMT_L 的对应中英文信息. */
	public static final String ALIAS_BUSI_AMT_L = "busiAmtL";
	/** 列 M_FLG 的对应中英文信息. */
	public static final String ALIAS_M_FLG = "0：未变更1：已变更";
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
	/** 列 METHOD_STAT 的对应中英文信息. */
	public static final String ALIAS_METHOD_STAT = "lvStat";
	
	//date formats
	
	//columns START
	/** 变量 mhctId . */
	@Id
	private String mchtId;
	/** 变量 mhctNm . */
	private String mchtNm;
	/** 变量 mchtLvL . */
	private String mchtLvL;
	/** 变量 mchtLvC . */
	private String mchtLvC;
	/** 变量 dayDepositL . */
	private String dayDepositL;
	/** 变量 busiCntL . */
	private java.math.BigDecimal busiCntL;
	/** 变量 busiAmtL . */
	private String busiAmtL;
	/** 变量 mflg . */
	private String MFlg;
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
	/** 变量 methodStat . */
	private String lvStat;
	/** 变量 syncFlag . */
	private String syncFlag;
	//columns END


	/**
	 * MhctId 置值.
	 * @param  java.lang.String
	 */	
	public void setMchtId(String value) {
		this.mchtId = value;
	}
	/**
	 * MhctId 取值.
	 * @return java.lang.String
	 */
	public String getMchtId() {
		return this.mchtId;
	}
	/**
	 * MhctNm 置值.
	 * @param  java.lang.String
	 */	
	public void setMchtNm(String value) {
		this.mchtNm = value;
	}
	/**
	 * MhctNm 取值.
	 * @return java.lang.String
	 */
	public String getMchtNm() {
		return this.mchtNm;
	}
	/**
	 * MchtLvL 置值.
	 * @param  java.lang.String
	 */	
	public void setMchtLvL(String value) {
		this.mchtLvL = value;
	}
	/**
	 * MchtLvL 取值.
	 * @return java.lang.String
	 */
	public String getMchtLvL() {
		return this.mchtLvL;
	}
	/**
	 * MchtLvC 置值.
	 * @param  java.lang.String
	 */	
	public void setMchtLvC(String value) {
		this.mchtLvC = value;
	}
	/**
	 * MchtLvC 取值.
	 * @return java.lang.String
	 */
	public String getMchtLvC() {
		return this.mchtLvC;
	}
	/**
	 * DayDepositL 置值.
	 * @param  java.lang.String
	 */	
	public void setDayDepositL(String value) {
		this.dayDepositL = value;
	}
	/**
	 * DayDepositL 取值.
	 * @return java.lang.String
	 */
	public String getDayDepositL() {
		return this.dayDepositL;
	}
	/**
	 * BusiCntL 置值.
	 * @param  java.math.BigDecimal
	 */	
	public void setBusiCntL(java.math.BigDecimal value) {
		this.busiCntL = value;
	}
	/**
	 * BusiCntL 取值.
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getBusiCntL() {
		return this.busiCntL;
	}
	/**
	 * BusiAmtL 置值.
	 * @param  java.lang.String
	 */	
	public void setBusiAmtL(String value) {
		this.busiAmtL = value;
	}
	/**
	 * BusiAmtL 取值.
	 * @return java.lang.String
	 */
	public String getBusiAmtL() {
		return this.busiAmtL;
	}
	/**
	 * MFlg 置值.
	 * @param  java.lang.String
	 */	
	public void setMFlg(String value) {
		this.MFlg = value;
	}
	/**
	 * MFlg 取值.
	 * @return java.lang.String
	 */
	public String getMFlg() {
		return this.MFlg;
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
	/**
	 * MethodStat 置值.
	 * @param  java.lang.String
	 */	
	public void setLvStat(String value) {
		this.lvStat = value;
	}
	/**
	 * MethodStat 取值.
	 * @return java.lang.String
	 */
	public String getLvStat() {
		return this.lvStat;
	}
	/**
	 * SyncFlag 置值.
	 * @param  java.lang.String
	 */	
	public void setSyncFlag(String value) {
		this.syncFlag = value;
	}
	/**
	 * MethodStat 取值.
	 * @return java.lang.String
	 */
	public String getSyncFlag() {
		return this.syncFlag;
	}
}
