package com.ruimin.ifs.pmp.baseParaMng.common.constants;

/**
 * 银联卡表-常量类
 * 
 * @author chenqilei
 *
 */
public class BankBinConstants {
	/*********************** 账户类型 ****************************/
	/** 账户类型-A001：本行借记卡 **/
	public static final String SELF_DEBIT = "A001";
	/** 账户类型-A002：本行贷记卡 **/
	public static final String SELF_CREDIT = "A002";
	/** 账户类型-A003：他行借记卡 **/
	public static final String OTHERS_DEBIT = "A003";
	/** 账户类型-A004：他行贷记卡 **/
	public static final String OTHERS_CREDIT = "A004";
	/*********************** 是否银联品牌卡 **********************/
	/** 是否银联品牌卡-0-非银联品牌卡 **/
	public static final String NO_UNIONPAY_BRAND = "0";
	/** 是否银联品牌卡-1-银联品牌卡 **/
	public static final String UNIONPAY_BRAND = "1";
	/************************ 卡种 *******************************/
	/** 卡种-1-借记卡 **/
	public static final String DEBIT_CARD = "1";
	/** 卡种-2-贷记卡 **/
	public static final String CREDIT_CARD = "2";
	/** 卡种-3-准贷记卡 **/
	public static final String QUASI_DEBIT_CARD = "3";
	/** 卡种-4-预付卡 **/
	public static final String PREPAY_CARD = "4";
	/************************* 卡类型 ***************************/
	/** 卡类型-00-借记 **/
	public static final String DEBIT = "00";
	/** 卡类型-01-贷记 **/
	public static final String CREDIT = "01";
	/** 卡类型-02-准贷记卡 **/
	public static final String QUASI_DEBIT = "02";
	/** 卡类型-03-预付卡 **/
	public static final String PREPAY = "03";
}
