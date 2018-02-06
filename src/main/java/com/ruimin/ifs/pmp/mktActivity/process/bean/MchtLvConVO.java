package com.ruimin.ifs.pmp.mktActivity.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("TBL_MCHT_LV_CON")
public class MchtLvConVO {
	/**
	 * 表名: TblMchtLvCon alias 用于页面显示对应中英文信息  .
	 */
	public static final String TABLE_ALIAS = "TblMchtLvCon";
	/** 列 MCHT_LV 的对应中英文信息. */
	public static final String ALIAS_MCHT_LV = "内管配置静态参数：01  ： 等级 —1；（最高）02 ： 等级 —2；03 ： 等级 —3；99 ：无等级。";
	/** 列 MCHT_LV_SEQ 的对应中英文信息. */
	public static final String ALIAS_MCHT_LV_SEQ = "自动生成，从01开始";
	/** 列 LV_DESC 的对应中英文信息. */
	public static final String ALIAS_LV_DESC = "该序号等级的说明";
	/** 列 DAY_DEPOSIT_MIX 的对应中英文信息. */
	public static final String ALIAS_DAY_DEPOSIT_MIX = "dayDepositMix";
	/** 列 BUSI_CNT_MIX 的对应中英文信息. */
	public static final String ALIAS_BUSI_CNT_MIX = "busiCntMix";
	/** 列 BUSI_AMT_MIX 的对应中英文信息. */
	public static final String ALIAS_BUSI_AMT_MIX = "busiAmtMix";
	/** 列 RESV_1 的对应中英文信息. */
	//public static final String ALIAS_RESV_1 = "resv1";
	/** 列 RESV_2 的对应中英文信息. */
	//public static final String ALIAS_RESV_2 = "resv2";
	/** 列 RD_STAT 的对应中英文信息. */
	public static final String ALIAS_RD_STAT = "00-无效01-生效";
	/** 列 UPDATE_TIME 的对应中英文信息. */
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	/** 列 UPDATE_OPR 的对应中英文信息. */
	public static final String ALIAS_UPDATE_OPR = "updateOpr";
	
	//date formats
	
	//columns START
	/** 变量 mchtLv 商户等级. */
	private String mchtLv;
	@Id
	/** 变量 mchtLvSeq 序号. */
	private String mchtLvSeq;
	/** 变量 lvDesc 评级说明. */
	private String lvDesc;
	/** 变量 dayDepositMix 最低日均存款. */
	private String dayDepositMix;
	/** 变量 busiCntMix 最低消费笔数. */
	private java.math.BigDecimal busiCntMix;
	/** 变量 busiAmtMix 最低消费金额. */
	private String busiAmtMix;
	/** 变量 resv1 预留字段1. */
	//private java.lang.String resv1;
	/** 变量 resv2 预留字段2. */
	//private java.lang.String resv2;
	/** 变量 rdStat 记录状态. */
	private String rdStat;
	/** 变量 updateTime 更新时间. */
	private String updateTime;
	/** 变量 updateOpr .更新操作员 */
	private String updateOpr;
	
	
	
	
	//columns END


	
	
	/**
	 * MchtLv 置值.
	 * @param  java.lang.String
	 */	
	public void setMchtLv(String value) {
		this.mchtLv = value;
	}
	/**
	 * MchtLv 取值.
	 * @return java.lang.String
	 */
	public String getMchtLv() {
		return this.mchtLv;
	}
	/**
	 * MchtLvSeq 置值.
	 * @param  java.lang.String
	 */	
	public void setMchtLvSeq(String value) {
		this.mchtLvSeq = value;
	}
	/**
	 * MchtLvSeq 取值.
	 * @return java.lang.String
	 */
	public String getMchtLvSeq() {
		return this.mchtLvSeq;
	}
	/**
	 * LvDesc 置值.
	 * @param  java.lang.String
	 */	
	public void setLvDesc(String value) {
		this.lvDesc = value;
	}
	/**
	 * LvDesc 取值.
	 * @return java.lang.String
	 */
	public String getLvDesc() {
		return this.lvDesc;
	}
	/**
	 * DayDepositMix 置值.
	 * @param  java.lang.String
	 */	
	public void setDayDepositMix(String value) {
		this.dayDepositMix = value;
	}
	/**
	 * DayDepositMix 取值.
	 * @return java.lang.String
	 */
	public String getDayDepositMix() {
		return this.dayDepositMix;
	}
	/**
	 * BusiCntMix 置值.
	 * @param  java.math.BigDecimal
	 */	
	public void setBusiCntMix(java.math.BigDecimal value) {
		this.busiCntMix = value;
	}
	/**
	 * BusiCntMix 取值.
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getBusiCntMix() {
		return this.busiCntMix;
	}
	/**
	 * BusiAmtMix 置值.
	 * @param  java.lang.String
	 */	
	public void setBusiAmtMix(String value) {
		this.busiAmtMix = value;
	}
	/**
	 * BusiAmtMix 取值.
	 * @return java.lang.String
	 */
	public String getBusiAmtMix() {
		return this.busiAmtMix;
	}
	/**
	 * Resv1 置值.
	 * @param  java.lang.String
	 *//*
	public void setResv1(java.lang.String value) {
		this.resv1 = value;
	}
	*//**
	 * Resv1 取值.
	 * @return java.lang.String
	 *//*
	public java.lang.String getResv1() {
		return this.resv1;
	}
	*//**
	 * Resv2 置值.
	 * @param  java.lang.String
	 *//*	
	public void setResv2(java.lang.String value) {
		this.resv2 = value;
	}
	*//**
	 * Resv2 取值.
	 * @return java.lang.String
	 *//*
	public java.lang.String getResv2() {
		return this.resv2;
	}*/
	/**
	 * RdStat 置值.
	 * @param  java.lang.String
	 */	
	public void setRdStat(String value) {
		this.rdStat = value;
	}
	/**
	 * RdStat 取值.
	 * @return java.lang.String
	 */
	public String getRdStat() {
		return this.rdStat;
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
