package com.ruimin.ifs.pmp.mktActivity.process.bean;

import com.ruimin.ifs.rql.annotation.Table;

@Table("TBL_ACTIVE_SHOW_INF")
public class ActiveShowInfoVO {
	/**
	 * 表名: TblActiveShowInf alias 用于页面显示对应中英文信息  .
	 */
	public static final String TABLE_ALIAS = "TblActiveShowInf";
	/** 列 ACTIVE_SHOW_NO 的对应中英文信息. */
	public static final String ALIAS_ACTIVE_SHOW_NO = "activeShowNo";
	/** 列 ACTIVE_NO 的对应中英文信息. */
	public static final String ALIAS_ACTIVE_NO = "activeNo";
	/** 列 ACTIVE_DES1C 的对应中英文信息. */
	public static final String ALIAS_ACTIVE_DES1C = "activeDes1c";
	/** 列 PIC_1_ID 的对应中英文信息. */
	public static final String ALIAS_PIC_1_ID = "LOG图片";
	/** 列 PIC_2_ID 的对应中英文信息. */
	public static final String ALIAS_PIC_2_ID = "APP小图片";
	/** 列 PIC_3_ID 的对应中英文信息. */
	public static final String ALIAS_PIC_3_ID = "门户大图片";
	
	//date formats
	
	//columns START
	/** 变量 activeShowNo . */
	private String activeShowNo;
	/** 变量 activeNo . */
	private String activeNo;
	/** 变量 activeDes1c . */
	private String activeDes1c;
	/** 变量 pic1id . */
	private String pic1id;
	/** 变量 pic2id . */
	private String pic2id;
	/** 变量 pic3id . */
	private String pic3id;
	
	private String dataState;
	//columns END


	/**
	 * ActiveShowNo 置值.
	 * @param  java.lang.String
	 */	
	public void setActiveShowNo(String value) {
		this.activeShowNo = value;
	}
	public String getDataState() {
		return dataState;
	}
	public void setDataState(String dataState) {
		this.dataState = dataState;
	}
	/**
	 * ActiveShowNo 取值.
	 * @return java.lang.String
	 */
	public String getActiveShowNo() {
		return this.activeShowNo;
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
	 * ActiveDes1c 置值.
	 * @param  java.lang.String
	 */	
	public void setActiveDes1c(String value) {
		this.activeDes1c = value;
	}
	/**
	 * ActiveDes1c 取值.
	 * @return java.lang.String
	 */
	public String getActiveDes1c() {
		return this.activeDes1c;
	}
	/**
	 * Pic1id 置值.
	 * @param  java.lang.String
	 */	
	public void setPic1id(String value) {
		this.pic1id = value;
	}
	/**
	 * Pic1id 取值.
	 * @return java.lang.String
	 */
	public String getPic1id() {
		return this.pic1id;
	}
	/**
	 * Pic2id 置值.
	 * @param  java.lang.String
	 */	
	public void setPic2id(String value) {
		this.pic2id = value;
	}
	/**
	 * Pic2id 取值.
	 * @return java.lang.String
	 */
	public String getPic2id() {
		return this.pic2id;
	}
	/**
	 * Pic3id 置值.
	 * @param  java.lang.String
	 */	
	public void setPic3id(String value) {
		this.pic3id = value;
	}
	/**
	 * Pic3id 取值.
	 * @return java.lang.String
	 */
	public String getPic3id() {
		return this.pic3id;
	}
}
