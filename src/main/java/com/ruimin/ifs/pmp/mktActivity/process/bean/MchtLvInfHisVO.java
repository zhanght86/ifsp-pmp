package com.ruimin.ifs.pmp.mktActivity.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("TBL_MCHT_LV_INF_HIS")
public class MchtLvInfHisVO {
	/**
	 * 表名: TblMchtLvInfHis alias 用于页面显示对应中英文信息  .
	 */
	public static final String TABLE_ALIAS = "TblMchtLvInfHis";
	/** 列 RANK_DATE 的对应中英文信息. */
	public static final String ALIAS_RANK_DATE = "rankDate";
	/** 列 MHCT_ID 的对应中英文信息. */
	public static final String ALIAS_MHCT_ID = "mchtId";
	/** 列 MHCT_NM 的对应中英文信息. */
	public static final String ALIAS_MHCT_NM = "mchtNm";
	/** 列 MCHT_LV_C 的对应中英文信息. */
	public static final String ALIAS_MCHT_LV_C = "内管配置静态参数：01 ： 等级 —1；（最高）02 ： 等级 —2；03 ： 等级 —3；04 ： 等级 —4；05 ： 等级 —5。（最低）";
	/** 列 DAY_DEPOSIT_L 的对应中英文信息. */
	public static final String ALIAS_DAY_DEPOSIT_L = "dayDepositL";
	/** 列 BUSI_CNT_L 的对应中英文信息. */
	public static final String ALIAS_BUSI_CNT_L = "busiCntL";
	/** 列 BUSI_AMT_L 的对应中英文信息. */
	public static final String ALIAS_BUSI_AMT_L = "busiAmtL";
	/** 列 M_FLG 的对应中英文信息. */
	public static final String ALIAS_M_FLG = "0：未变更;1：已变更";
	/** 列 UPDATE_TIME 的对应中英文信息. */
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	/** 列 UPDATE_OPR 的对应中英文信息. */
	public static final String ALIAS_UPDATE_OPR = "updateOpr";
	
	//date formats
	
	//columns START
	/** 变量 rankDate . */
	private String rankDate;
	/** 变量 mhctId . */
	@Id
	private String mchtId;
	/** 变量 mhctNm . */
	private String mchtNm;
	/** 变量 mchtLvC . */
	private String mchtLvC;
	/** 变量 dayDepositL . */
	private String dayDepositL;
	/** 变量 busiCntL . */
	private java.math.BigDecimal busiCntL;
	/** 变量 busiAmtL . */
	private String busiAmtL;
	/** 变量 mFlg . */
	private String MFlg;
	/** 变量 updateTime . */
	private String updateTime;
	/** 变量 updateOpr . */
	private String updateOpr;
	//columns END


	/**
	 * RankDate 置值.
	 * @param  java.lang.String
	 */	
	public void setRankDate(String value) {
		this.rankDate = value;
	}
	/**
	 * RankDate 取值.
	 * @return java.lang.String
	 */
	public String getRankDate() {
		return this.rankDate;
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
}
