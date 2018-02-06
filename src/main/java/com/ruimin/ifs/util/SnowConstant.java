package com.ruimin.ifs.util;

public class SnowConstant {
	/**
	 * 机构级别-BCTL.BRCLASS
	 */
	public static final String BRCODE_CLASS_HEAD = "1"; // 1-总行
	public static final String BRCODE_CLASS_BRANCH = "2"; // 2-分行
	public static final String BRCODE_CLASS_SUBBRANCH = "5"; // 5-支行
	public static final String BRCODE_CLASS_MNGBRANCH = "3"; // 3-管理行
	public static final String BRCODE_CLASS_PL_CENTER = "3"; // 个贷中心
	public static final String BRCODE_CLASS_SUB_PL_CENTER = "5"; // 个贷分中心

	/** {0}不能为空 ***/
	public static final String WEB_ERROR_CODE_E0040 = "WEB_E0040";

	/**
	 * SYS_INFO表ID
	 */
	public static final int TABLE_SYS_INFO_ID = 1;

	/**
	 * 批量状态
	 */
	public static final String SYS_INFO_STATE_ONLINE = "0"; // 联机状态
	public static final String SYS_INFO_STATE_BATCH = "1"; // 批量状态

	public static final String FILE_BANK_BIN_LST = "BankBin"; // 卡bin批量导入
	public static final String FILE_BANK_BIN2_LST = "BankBin2"; // 卡bin批量导入

	public static final String FILE_TERM_KEY = "termKey"; // 终端密钥预申请批量导入

	public static final String FILE_ORG_LST = "org"; // 机构批量导入
	public static final String FILE_CITY_LST = "city"; // 地区批量导入
	public static final String FILE_HOLIDAY_LST = "holiday";// 节假日导入
	public static final String FILE_OPENORG_LST = "openOrg";// 开户机构批量导入
	public static final String FILE_BATHADD_LST = "bathAdd";// 开户机构批量导入
	
	//---------------新增黑名单导入
	public static final String FILE_BLACKLIST_LST = "blacklist";//黑名单导入
	//---------------新增巡检信息导入
	public static final String FILE_POLLING_LIST = "serviceMchtPolling";//服务机构商户巡检基本信息批量导入
	public static final String PBS_MCHT_BASE_INFO_TMP ="merchantMessage";//商户信息批量导入
	public static final String FILE_TXN_WHITE_LST = "txnWhiteList";// 特殊商户交易白名单批量导入
}
