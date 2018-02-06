package com.ruimin.ifs.pmp.chnlMng.common.constants;

/**
 * 
 * 通道接入信息-常量类
 * 
 * @author zhangjc
 *
 */
public class ChnlAcsInfoConstants {
	/************************** 证书类型 ***********************************/
	/** 证书类型-01-公钥证书 **/
	public static final String CERT_TYPE_PUB = "01";
	/** 证书类型-02-私钥证书 **/
	public static final String CERT_TYPE_PRV = "02";
	/** 证书类型-03-MD5 **/
	public static final String CERT_TYPE_MD5 = "03";

	/************************** 加密类型 ***********************************/
	/** 加密类型-01-RSA **/
	public static final String ENC_TYPE_RSA = "01";
	/** 加密类型-02-MD5 **/
	public static final String ENC_TYPE_MD5 = "02";

	/************************** 需要签名 ***********************************/
	/** 需要签名-00-不需要 **/
	public static final String SIGN_NO = "00";
	/** 需要签名-01-需要 **/
	public static final String SIGN_NEED = "01";

	/************************** 加签方式 ***********************************/
	/** 加签方式-01-签名 **/
	public static final String ENC_WAY_TYPE_SIGN = "01";
	/** 加签方式-02-加密 **/
	public static final String ENC_WAY_TYPE_PASSWORD = "02";

	/************************** 证书使用类型 ***********************************/
	/** 证书使用类型-3000-支付平台 **/
	public static final String CERT_USE_PAYPLAT = "3000";

	/************************** 证书状态 ***********************************/
	/** 证书状态-01-启用 **/
	public static final String CERT_STAT_ON = "00";
	/** 证书状态-02-关闭 **/
	public static final String CERT_STAT_OFF = "01";

	/************************** 操作标志 ***********************************/
	/** 操作标志-ADD-新增 **/
	public static final String OPR_FLAG_ADD = "ADD";
	/** 操作标志-UPD-修改 **/
	public static final String OPR_FLAG_UPD = "UPD";
	/** 操作标志-DETAIL-详情 **/
	public static final String OPR_FLAG_DETAIL = "DETAIL";
}
