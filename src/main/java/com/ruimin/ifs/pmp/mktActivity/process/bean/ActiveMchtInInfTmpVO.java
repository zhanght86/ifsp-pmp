package com.ruimin.ifs.pmp.mktActivity.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("TBL_ACTIVE_MCHT_IN_INF_TMP")
public class ActiveMchtInInfTmpVO {
	/**
	 * 表名: TblActiveMchtInInfTmp alias 用于页面显示对应中英文信息  .
	 */
	public static final String TABLE_ALIAS = "TblActiveMchtInInfTmp";
	/** 列 ACTIVE_NO 的对应中英文信息. */
	public static final String ALIAS_ACTIVE_NO = "activeNo";
	/** 列 MHCT_ID 的对应中英文信息. */
	public static final String ALIAS_MHCT_ID = "mhctId";
	/** 列 IN_FLG 的对应中英文信息. */
	public static final String ALIAS_IN_FLG = "01：参加02：退出";
	/** 列 IN_DATE 的对应中英文信息. */
	public static final String ALIAS_IN_DATE = "inDate";
	/** 列 OUT_DATE 的对应中英文信息. */
	public static final String ALIAS_OUT_DATE = "outDate";
	/** 列 UPDATE_TIME 的对应中英文信息. */
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	/** 列 UPDATE_OPR 的对应中英文信息. */
	public static final String ALIAS_UPDATE_OPR = "updateOpr";
	
	//date formats
	
	//columns START
	/** 变量 activeNo . */
	@Id
	private String activeNo;
	/** 变量 mhctId . */
	@Id
	private String mchtId;
	/** 变量 inFlg . */
	private String inFlg;
	/** 变量 inDate . */
	private String inDate;
	/** 变量 outDate . */
	private String outDate;
	/** 变量 updateTime . */
	private String updateTime;
	/** 变量 updateOpr . */
	private String updateOpr;
	private String syncFlag;
	
	//columns END

	public String getSyncFlag() {
		return syncFlag;
	}
	public void setSyncFlag(String syncFlag) {
		this.syncFlag = syncFlag;
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
	 * InFlg 置值.
	 * @param  java.lang.String
	 */	
	public void setInFlg(String value) {
		this.inFlg = value;
	}
	/**
	 * InFlg 取值.
	 * @return java.lang.String
	 */
	public String getInFlg() {
		return this.inFlg;
	}
	/**
	 * InDate 置值.
	 * @param  java.lang.String
	 */	
	public void setInDate(String value) {
		this.inDate = value;
	}
	/**
	 * InDate 取值.
	 * @return java.lang.String
	 */
	public String getInDate() {
		return this.inDate;
	}
	/**
	 * OutDate 置值.
	 * @param  java.lang.String
	 */	
	public void setOutDate(String value) {
		this.outDate = value;
	}
	/**
	 * OutDate 取值.
	 * @return java.lang.String
	 */
	public String getOutDate() {
		return this.outDate;
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
}
