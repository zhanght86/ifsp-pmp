/**
 * 
 */
package com.ruimin.ifs.pmp.chnlMng.common.constants;

/**
 * 通道路由管理-常量类
 * 
 * @author chenqilei
 *
 */
public class PagyRoutMngConstants {
	/*********************** 路由状态 **************************/
	/** 路由状态-00-启用 **/
	public static final String ROUTE_STAT_ON = "00";
	/** 路由状态-02-未启用 **/
	public static final String ROUTE_STAT_NO_START = "02";
	/** 路由状态-02-暂停使用 **/
	public static final String ROUTE_STAT_LAY_UP = "99";
	/************************* 路由选择 ************************/
	/** 路由选择-A000-全部卡种 **/
	public static final String ROUTE_CHOOSE_ALL = "A000";
	/** 路由选择-A001-本行借记卡 **/
	public static final String ROUTE_CHOOSE_SELF_DEBIT_CARD = "A001";
	/** 路由选择-A002-本行贷记卡 **/
	public static final String ROUTE_CHOOSE_SELF_CREDIT_CARD = "A002";
	/** 路由选择-A003-他行借记卡 **/
	public static final String ROUTE_CHOOSE_OTHERS_DEBIT_CARD = "A003";
	/** 路由选择-A004-他行贷记卡 **/
	public static final String ROUTE_CHOOSE_OTHERS_CREDIT_CARD = "A004";
	/** 路由选择-A005-支付宝 **/
	public static final String ROUTE_CHOOSE_ALI_PAY = "A005";
	/** 路由选择-A006-微信 **/
	public static final String ROUTE_CHOOSE_WECHAT = "A006";
	/*********************** 策略状态 ***********************/
	/** 策略状态-00-启用 **/
	public static final String TTS_STAT_ON = "00";
	/** 策略状态-02-未启用 **/
	public static final String TTS_STAT_NO_START = "02";
	/** 策略状态-99-停用 **/
	public static final String TTS_STAT_BLOCK_UP = "99";
}
