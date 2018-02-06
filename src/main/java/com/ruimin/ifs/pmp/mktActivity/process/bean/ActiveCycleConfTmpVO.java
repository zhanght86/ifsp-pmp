package com.ruimin.ifs.pmp.mktActivity.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("TBL_ACTIVE_CYCLE_CONF_TMP")
public class ActiveCycleConfTmpVO {
	/**
	 * 表名: 当周期标志存在时使用，该表使用 alias 用于页面显示对应中英文信息  .
	 */
	public static final String TABLE_ALIAS = "当周期标志存在时使用，该表使用";
	/** 列 ACTIVE_NO 的对应中英文信息. */
	public static final String ALIAS_ACTIVE_NO = "activeNo";
	/** 列 DATE_TP 的对应中英文信息. */
	public static final String ALIAS_DATE_TP = "01：具体日期02：每月X日03：星期X04：工作日节假日区分";
	/** 列 DATE_DATE 的对应中英文信息. */
	public static final String ALIAS_DATE_DATE = "当周期类型为（01：具体日期）时MMDD;当周期类型为（02：每月X日）时NN（01~31）;当周期类型为（03：星期X）时N（1~7）;当周期类型为（04：工作日节假日区分）时N（1-工作日；0-节假日）";
	/** 列 UPDATE_TIME 的对应中英文信息. */
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	/** 列 UPDATE_OPR 的对应中英文信息. */
	public static final String ALIAS_UPDATE_OPR = "updateOpr";
	
	//date formats
	
	//columns START
	/** 变量 activeNo . */
	@Id
	private String activeNo;
	/** 变量 dateTp . */
	private String dateTp;
	/** 变量 dateDate . */
	private String dateData;
	/** 变量 updateTime . */
	private String updateTime;
	/** 变量 updateOpr . */
	private String updateOpr;
	private String syncFlag;
	@Id
	private String dateSeq;
	private String dataState;
	//columns END
	
	public String getDataState() {
		return dataState;
	}
	public String getDateSeq() {
		return dateSeq;
	}
	public void setDateSeq(String dateSeq) {
		this.dateSeq = dateSeq;
	}
	public void setDataState(String dataState) {
		this.dataState = dataState;
	}
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
	 * DateTp 置值.
	 * @param  java.lang.String
	 */	
	public void setDateTp(String value) {
		this.dateTp = value;
	}
	/**
	 * DateTp 取值.
	 * @return java.lang.String
	 */
	public String getDateTp() {
		return this.dateTp;
	}
	/**
	 * DateDate 置值.
	 * @param  java.lang.String
	 */	
	public void setDateData(String value) {
		this.dateData = value;
	}
	/**
	 * DateDate 取值.
	 * @return java.lang.String
	 */
	public String getDateData() {
		return this.dateData;
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
