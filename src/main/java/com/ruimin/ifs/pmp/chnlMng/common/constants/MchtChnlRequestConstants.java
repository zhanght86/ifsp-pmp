package com.ruimin.ifs.pmp.chnlMng.common.constants;

/**
 * 
 * 商户通道请求-常量类
 * 
 * @author zhangjc
 *
 */
public class MchtChnlRequestConstants {
	/************************** 服务器URL ***********************************/
	/** 预申请服务器 */
	public static final String MCHT_CHL_REQUEST_PER_SEVR_URL = "MCHT_CHL_REQUEST_PER_SEVR_URL";
	/** 指定通道申请服务器 */
	public static final String MCHT_CHL_REQUEST_CHL_SEVR_URL = "MCHT_CHL_REQUEST_CHL_SEVR_URL";
	
	/************************** 请求方式 ***********************************/
	public static final String MCHT_CHL_REQUEST_METHOD = "MCHT_CHL_REQUEST_METHOD";

	/************************** 渠道编号 ***********************************/
	public static final String MCHT_CHL_REQUEST_CHNLNO = "MCHT_CHL_REQUEST_CHNLNO";

	/************************** 编码方式 ***********************************/
	public static final String MCHT_CHL_REQUEST_ENCODING = "MCHT_CHL_REQUEST_ENCODING";

	/**************************
	 * 申请方式【用于选择接口分支】
	 ***********************************/
	/** 申请方式-预申请 **/
	public static final String APPLY_WAY_PER = "预申请";
	/** 申请方式-指定通道申请 **/
	public static final String APPLY_WAY_CHL = "指定通道申请";

	/************************** 申请类型 ***********************************/
	/** 申请类型-1-渠道申请 **/
	public static final String APPLY_TYPE_CHL = "1";
	/** 申请类型-2-渠道商户申请 **/
	public static final String APPLY_TYPE_CHL_MCHT = "2";

	/************************** 申请通道 ***********************************/
	/** 申请通道-301-银联全渠道 **/
	public static final String APPLY_PAGY_UNION_PAY = "301";
	/** 申请通道-302-微信通道 **/
	public static final String APPLY_PAGY_WECHAT = "302";
	/** 申请通道-304-支付宝通道 **/
	public static final String APPLY_PAGY_ALI_PAY = "304";
	/** 申请通道-303-本行通道 **/
	public static final String APPLY_PAGY_BH_PAY = "303";

	/************************** 类目使用方 ***********************************/
	/** 类目使用方-01-微信 **/
	public static final String USER_CODE_WECHAT = "01";
	/** 类目使用方-02-支付宝 **/
	public static final String USER_CODE_ALI_PAY = "02";

	/************************** 行业编码【微信】 ***********************************/
	/** 一级行业编码-00000-默认 **/
	public static final String LEVEL_ONE_CODE_DEFAULT = "00000";
	/** 二级行业编码-00000-默认 **/
	public static final String LEVEL_TWO_CODE_DEFAULT = "00000";
	/** 三级行业编码-00000-默认 **/
	public static final String LEVEL_THREE_CODE_DEFAULT = "00000";

	/************************** 通道申请成功 ***********************************/
	public static final String CHL_APPLY_SUCCESS = "0000";
	public static final String CHL_APPLY_SUCCESS_0002 = "0002";
    public static final String CHL_APPLY_SUCCESS_0005 = "0005";
    public static final String CHL_APPLY_SUCCESS_0008 = "0008";    
    public static final String CHL_APPLY_SUCCESS_1020 = "1020";
	/************************** 终端密钥 ***********************************/
	public static final String IP = "unionIp";
	public static final String PORT = "unionPort";
	public static final String TIME_OUT = "unionTimeOut";
	public static final String TLV_OR_XML_FLAG = "tlvOrXmlflag";
	public static final String SYS_ID = "sysID";
	public static final String APP_ID = "appID";
	public static final String COUNT = "unionCount";

	/************************** 终端文件下载路径 ***********************************/
	public static final String PATH = "path";
	
	
	/****************二维码接口服务器地址********************/
	
	public static final String QRC_SEVR_URL = "QRC_SEVR_URL";//申请
	public static final String QRC_STOP_URL = "QRC_STOP_URL";//停用
	
	/**********************支付前值地址******************/
	public static final String PAY_PER_URL = "PAY_PER_URL";
	
	/**二维码图片存放路径**/
	public static final String QRC_TMP_SAVE_PATH = "QRC_TMP_SAVE_PATH";
	public static final String QRC_STY = "QRC_STY";
	public static final String QRC_NAME_SIZE = "QRC_NAME_SIZE";
	/**二维码图片存放路径**/
	public static final String QRC_BACKGROUND_PATH = "QRC_BACKGROUND_PATH";
	
	/**请求图片上传文件服务器地址**/
	public static final String UPLOAD_FILE = "UPLOAD_FILE";
	/**请求图片访问服务器地址**/
	public static final String SHOW_FILE = "SHOW_FILE";
	/**请求渠道号401-支付管理平台**/
	public static final String REQUEST_CHNL_NO = "REQUEST_CHNL_NO";
	
	//20170417fjf  新增  
	/**请求图片访问服务器地址另一个网段,因生产环境网段有两个的原因**/
	public static final String SHOW_FILE_WITHIN = "SHOW_FILE_WITHIN";
	/**差错退款(311)**/
    public static final String ERRORS_REFUND_311 = "ERRORS_REFUND_311";
    /**差错退款(304)**/
    public static final String ERRORS_REFUND_304 = "ERRORS_REFUND_304";
    /**差错退款(312)**/
    public static final String ERRORS_REFUND_312 = "ERRORS_REFUND_312";
    /**差错退款(313)**/
    public static final String ERRORS_REFUND_313 = "ERRORS_REFUND_313";
    /**差错退款查询(311)**/
    public static final String Query_REFUND_311 = "Query_REFUND_311";
    /**差错退款查询(312)**/
    public static final String Query_REFUND_312 = "Query_REFUND_312";
    /**差错退款查询(313)**/
    public static final String Query_REFUND_313 = "Query_REFUND_313";
    
    /**调度系统**/
    public static final String CACHE_SYNCHRONIZATION = "CACHE_SYNCHRONIZATION";
    
    
    /**平安通道联机申请**/
    public static final String PAGY_MCHT_MNG_PA = "PAGY_MCHT_MNG_PA";
    
    /**平安通道联机申请修改**/
    public static final String PAGY_MCHT_MNG_PA_UPDATE = "PAGY_MCHT_MNG_PA_UPDATE";
    
	/**平安通道标准进件**/
    public static final String PAGY_MCHT_MNG_STANDARD = "PAGY_MCHT_MNG_STANDARD";
    
    /**交款记账**/
    public static final String CHATINACCT= "CHATINACCT";

    /**二维码路由请求soa服务*/
    public static final String Qrs_Rout_Mng_REQUEST_SEVR_URL = "Qrs_Rout_Mng_REQUEST_SEVR_URL";

    /**刷新商户接入参数缓存*/
    public static final String MchtCERTINFOVO = "MchtCERTINFOVO";
    
    /**营销活动资金初始化请求*/
    public static final String AMT_TOTAL_URL = "AMT_TOTAL_URL";
    
    /**发送短信*/
    public static final String MCHT_ID_NOTE = "MCHT_ID_NOTE";
    
    /**商户进件*/
    public static final String MCHT_IN = "MCHT_IN";
    
    /**mchtInput 验签值*/
    public static final String MCHTIN_SIGN = "MCHTIN_SIGN";
}
