package com.ruimin.ifs.pmp.mktActivity.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("TBL_ACTIVE_PRD_CONF_TMP")
public class ActiveProdConfTmpVO {
	/**
	 * 表名: TblActivePrdConfTmp alias 用于页面显示对应中英文信息  .
	 */
	public static final String TABLE_ALIAS = "TblActivePrdConfTmp";
	/** 列 ACTIVE_NO 的对应中英文信息. */
	public static final String ALIAS_ACTIVE_NO = "activeNo";
	/** 列 PRD_ID 的对应中英文信息. */
	public static final String ALIAS_PRD_ID = "prdId";
	/** 列 UPDATE_TIME 的对应中英文信息. */
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	/** 列 UPDATE_OPR 的对应中英文信息. */
	public static final String ALIAS_UPDATE_OPR = "updateOpr";
	
	//date formats
	
	//columns START
	/** 变量 activeNo . */
	@Id
	private String activeNo;
	/** 变量 prdId . */
	@Id
	private String prdId;
	/** 变量 updateTime . */
	private String updateTime;
	/** 变量 updateOpr . */
	private String updateOpr;
	private String syncFlag;
	private String dataState;
	//columns END
	public String getSyncFlag() {
		return syncFlag;
	}
	public void setSyncFlag(String syncFlag) {
		this.syncFlag = syncFlag;
	}
	public String getDataState() {
		return dataState;
	}
	public void setDataState(String dataState) {
		this.dataState = dataState;
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
	 * PrdId 置值.
	 * @param  java.lang.String
	 */	
	public void setPrdId(String value) {
		this.prdId = value;
	}
	/**
	 * PrdId 取值.
	 * @return java.lang.String
	 */
	public String getPrdId() {
		return this.prdId;
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
