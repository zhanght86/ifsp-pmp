package com.ruimin.ifs.pmp.mktActivity.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("TBL_METHOD_SECTION_INF")
public class MethodSectionInfVO {
	/**
	 * 表名: TblMethodSectionInf alias 用于页面显示对应中英文信息  .
	 */
	public static final String TABLE_ALIAS = "TblMethodSectionInf";
	/** 列 METHOD_NO 的对应中英文信息. */
	public static final String ALIAS_METHOD_NO = "methodNo";
	/** 列 SECTION_SEQ 的对应中英文信息. */
	public static final String ALIAS_SECTION_SEQ = "sectionSeq";
	/** 列 SECTION_LEFT 的对应中英文信息. */
	public static final String ALIAS_SECTION_LEFT = "sectionLeft";
	/** 列 SECTION_RIGHT 的对应中英文信息. */
	public static final String ALIAS_SECTION_RIGHT = "sectionRight";
	/** 列 SECTION_PARAM1 的对应中英文信息. */
	public static final String ALIAS_SECTION_PARAM1 = "sectionParam1";
	/** 列 SECTION_PARAM2 的对应中英文信息. */
	public static final String ALIAS_SECTION_PARAM2 = "sectionParam2";
	/** 列 UPDATE_TIME 的对应中英文信息. */
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	/** 列 UPDATE_OPR 的对应中英文信息. */
	public static final String ALIAS_UPDATE_OPR = "updateOpr";
	
	//date formats
	
	//columns START
	/** 变量 methodNo . */
	@Id
	private String methodNo;
	/** 变量 sectionSeq . */
	@Id
	private String sectionSeq;
	/** 变量 sectionLeft . */
	private String sectionLeft;
	/** 变量 sectionRight . */
	private String sectionRight;
	/** 变量 sectionParam1 . */
	private String sectionParam1;
	/** 变量 sectionParam2 . */
	private String sectionParam2;
	/** 变量 updateTime . */
	private String updateTime;
	/** 变量 updateOpr . */
	private String updateOpr;
	private String dataState;	

	//columns END

	public String getDataState() {
		return dataState;
	}
	public void setDataState(String dataState) {
		this.dataState = dataState;
	}
	/**
	 * MethodNo 置值.
	 * @param  java.lang.String
	 */	
	public void setMethodNo(String value) {
		this.methodNo = value;
	}
	/**
	 * MethodNo 取值.
	 * @return java.lang.String
	 */
	public String getMethodNo() {
		return this.methodNo;
	}
	/**
	 * SectionSeq 置值.
	 * @param  java.lang.String
	 */	
	public void setSectionSeq(String value) {
		this.sectionSeq = value;
	}
	/**
	 * SectionSeq 取值.
	 * @return java.lang.String
	 */
	public String getSectionSeq() {
		return this.sectionSeq;
	}
	/**
	 * SectionLeft 置值.
	 * @param  java.lang.String
	 */	
	public void setSectionLeft(String value) {
		this.sectionLeft = value;
	}
	/**
	 * SectionLeft 取值.
	 * @return java.lang.String
	 */
	public String getSectionLeft() {
		return this.sectionLeft;
	}
	/**
	 * SectionRight 置值.
	 * @param  java.lang.String
	 */	
	public void setSectionRight(String value) {
		this.sectionRight = value;
	}
	/**
	 * SectionRight 取值.
	 * @return java.lang.String
	 */
	public String getSectionRight() {
		return this.sectionRight;
	}
	/**
	 * SectionParam1 置值.
	 * @param  java.lang.String
	 */	
	public void setSectionParam1(String value) {
		this.sectionParam1 = value;
	}
	/**
	 * SectionParam1 取值.
	 * @return java.lang.String
	 */
	public String getSectionParam1() {
		return this.sectionParam1;
	}
	/**
	 * SectionParam2 置值.
	 * @param  java.lang.String
	 */	
	public void setSectionParam2(String value) {
		this.sectionParam2 = value;
	}
	/**
	 * SectionParam2 取值.
	 * @return java.lang.String
	 */
	public String getSectionParam2() {
		return this.sectionParam2;
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
