package com.ruimin.ifs.pmp.chnlMng.common.constants;

/**
 * 
 * 通道商户登记-常量类
 * 
 * @author zhangjc
 *
 */
public class PagyMchtMngConstants {
	/************************** 申请类型 ***********************************/
	/** 申请类型-0-渠道申请 **/
	public static final String APPLY_TYPE_CHL = "1";
	/** 申请类型-1-渠道商户申请 **/
	public static final String APPLY_TYPE_MCHT = "2";

	/************************** 申请状态 ***********************************/
	/** 申请状态-00-未申请 **/
	public static final String APPLY_STAT_NO = "00";
	/** 申请状态-01-申请中 **/
	public static final String APPLY_STAT_APPLYING = "01";
	/** 申请状态-02-申请成功 **/
	public static final String APPLY_STAT_SUCCESS = "02";
	/** 申请状态-03-申请失败 **/
	public static final String APPLY_STAT_FAIL = "03";

	/************************* 通道交易状态 **************************/
	/** 通道交易状态-00-启用 **/
	public static final String PAGY_TXN_STAT_00 = "00";
	/** 通道交易状态-01-待启用 **/
	public static final String PAGY_TXN_STAT_02 = "02";
	/** 通道交易状态-02-停用 **/
	public static final String PAGY_TXN_STAT_99 = "99";
}
