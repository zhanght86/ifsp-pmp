package com.ruimin.ifs.pmp.mktActivity.process.bean;

import com.ruimin.ifs.rql.annotation.Table;

@Table("TBL_ACTIVE_REFUSE_REASON")
public class ActiveRefuseReasonVO {
	/**
	 * 表名: TblActiveRefuseReason alias 用于页面显示对应中英文信息  .
	 */
	public static final String TABLE_ALIAS = "TblActiveRefuseReason";
	/** 列 ACTIVE_NO 的对应中英文信息. */
	public static final String ALIAS_ACTIVE_NO = "activeNo";
	/** 列 REFUSE_ID 的对应中英文信息. */
	public static final String ALIAS_REFUSE_ID = "refuseId";
	/** 列 TXN_CHNL 的对应中英文信息. */
	public static final String ALIAS_TXN_CHNL = "txnChnl";
	/** 列 TXN_TIME 的对应中英文信息. */
	public static final String ALIAS_TXN_TIME = "txnTime";
	/** 列 OPR_ID 的对应中英文信息. */
	public static final String ALIAS_OPR_ID = "oprId";
	/** 列 AUDIT_FLAG 的对应中英文信息. */
	public static final String ALIAS_AUDIT_FLAG = "auditFlag";
	/** 列 REFUSE_INFO 的对应中英文信息. */
	public static final String ALIAS_REFUSE_INFO = "refuseInfo";
	/** 列 AUDIT_TYPE 的对应中英文信息. */
	public static final String ALIAS_AUDIT_TYPE = "auditType";
	/** 列 AUDIT_LEVEL 的对应中英文信息. */
	public static final String ALIAS_AUDIT_LEVEL = "auditLevel";
	
	//date formats
	
	//columns START
	/** 变量 activeNo . */
	private String activeNo;
	/** 变量 refuseId . */
	private String refuseId;
	/** 变量 txnChnl . */
	private String txnChnl;
	/** 变量 txnTime . */
	private String txnTime;
	/** 变量 oprId . */
	private String oprId;
	/** 变量 auditFlag . */
	private String auditFlag;
	/** 变量 refuseInfo . */
	private String refuseInfo;
	/** 变量 auditType . */
	private String auditType;
	/** 变量 auditLevel . */
	private java.math.BigDecimal auditLevel;
	//columns END

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
	 * RefuseId 置值.
	 * @param  java.lang.String
	 */	
	public void setRefuseId(String value) {
		this.refuseId = value;
	}
	/**
	 * RefuseId 取值.
	 * @return java.lang.String
	 */
	public String getRefuseId() {
		return this.refuseId;
	}
	/**
	 * TxnChnl 置值.
	 * @param  java.lang.String
	 */	
	public void setTxnChnl(String value) {
		this.txnChnl = value;
	}
	/**
	 * TxnChnl 取值.
	 * @return java.lang.String
	 */
	public String getTxnChnl() {
		return this.txnChnl;
	}
	/**
	 * TxnTime 置值.
	 * @param  java.lang.String
	 */	
	public void setTxnTime(String value) {
		this.txnTime = value;
	}
	/**
	 * TxnTime 取值.
	 * @return java.lang.String
	 */
	public String getTxnTime() {
		return this.txnTime;
	}
	/**
	 * OprId 置值.
	 * @param  java.lang.String
	 */	
	public void setOprId(String value) {
		this.oprId = value;
	}
	/**
	 * OprId 取值.
	 * @return java.lang.String
	 */
	public String getOprId() {
		return this.oprId;
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
	 * RefuseInfo 置值.
	 * @param  java.lang.String
	 */	
	public void setRefuseInfo(String value) {
		this.refuseInfo = value;
	}
	/**
	 * RefuseInfo 取值.
	 * @return java.lang.String
	 */
	public String getRefuseInfo() {
		return this.refuseInfo;
	}
	/**
	 * AuditType 置值.
	 * @param  java.lang.String
	 */	
	public void setAuditType(String value) {
		this.auditType = value;
	}
	/**
	 * AuditType 取值.
	 * @return java.lang.String
	 */
	public String getAuditType() {
		return this.auditType;
	}
	/**
	 * AuditLevel 置值.
	 * @param  java.math.BigDecimal
	 */	
	public void setAuditLevel(java.math.BigDecimal value) {
		this.auditLevel = value;
	}
	/**
	 * AuditLevel 取值.
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getAuditLevel() {
		return this.auditLevel;
	}
}
