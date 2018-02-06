package com.ruimin.ifs.pmp.mchtMng.common.constants;

/**
 * 
 * 商户信息管理-常量类
 * 
 * @author zhangjc
 *
 */
public class MchtMngConstants {
	/************************** 商户状态 ***********************************/
	/** 商户状态-正常-00 **/
	public static final String MCHT_STAT_NORMAL = "00";
	/** 商户状态-冻结-01 **/
	public static final String MCHT_STAT_FRZ = "01";
	/** 商户状态-注销-02 **/
	public static final String MCHT_STAT_OFF = "02";
	/** 商户状态: 03-添加待审核 */
	public static final String MCHT_STAT_03 = "03";
	/** 商户状态: 04-修改待审核 */
	public static final String MCHT_STAT_04 = "04";
	/** 商户状态: 05-冻结待审核 */
	public static final String MCHT_STAT_05 = "05";
	/** 商户状态: 06-恢复待审核 */
	public static final String MCHT_STAT_06 = "06";
	/** 商户状态: 07-注销待审核 */
	public static final String MCHT_STAT_07 = "07";
	/** 商户状态: 08-新增被拒绝 */
	public static final String MCHT_STAT_08 = "08";
	/** 商户状态: 09-修改被拒绝 */
	public static final String MCHT_STAT_09 = "09";
	/** 商户状态-入网中-98 **/
	public static final String MCHT_STAT_NET = "98";
	/** 商户状态-待补录-97 **/
    public static final String MCHT_STAT_97 = "97";
    /** 商户状态-商户进件新增-10 **/
    public static final String MCHT_STAT_10 = "10";
    /** 商户状态-商户进件修改-11 **/
    public static final String MCHT_STAT_11 = "11";

	/************************** 商户类型 ***********************************/
	/** 商户类型-企业法人-01 **/
	public static final String MCHT_TYPE_BUS = "01";
	/** 商户类型-政府机构-02 **/
	public static final String MCHT_TYPE_GOV = "02";
	/** 商户类型-事业单位-03 **/
	public static final String MCHT_TYPE_IST = "03";
	/** 商户类型-个体工商-04 **/
	public static final String MCHT_TYPE_IDV = "04";
	/** 商户类型-虚拟商户-05 **/
	public static final String MCHT_TYPE_FIC = "05";

	/************************** 导入图片-标签名称 ***********************************/
	/** 图片类型-营业执照-01 **/
	public static final String MCHT_PIC_TYPE_BSI = "01";
	/** 图片类型-法人证件-02 **/
	public static final String MCHT_PIC_TYPE_COP = "02";
	/** 图片类型-组织机构-03 **/
	public static final String MCHT_PIC_TYPE_IST = "03";
	/** 图片类型-ICP许可-04 **/
	public static final String MCHT_PIC_TYPE_ICP = "04";
	/** 图片类型-税务许可-05 **/
	public static final String MCHT_PIC_TYPE_TAX = "05";

	/**************************
	 * 审核信息，业务类型,0X为商户信息类 1X为商户合同类
	 ***********************************/
	/** 00-商户信息登记； **/
	public static final String AUDIT_TYPE_00 = "00";
	/** 01-商户信息变更； **/
	public static final String AUDIT_TYPE_01 = "01";
	/** 02-商户冻结； **/
	public static final String AUDIT_TYPE_02 = "02";
	/** 03-商户解冻； **/
	public static final String AUDIT_TYPE_03 = "03";
	/** 04-商户注销； **/
	public static final String AUDIT_TYPE_04 = "04";

	/************************** 审核信息，审核状态 ***********************************/
	/** 00-未审核； **/
	public static final String AUDIT_STATE_00 = "00";
	/** 01-审核通过； **/
	public static final String AUDIT_STATE_01 = "01";
	/** 02-审核拒绝； **/
	public static final String AUDIT_STATE_02 = "02";

	/********************* 商户信息的同步状态 **************************/
	/** 商户信息的同步的状态：00 已同步 **/
	public static final String SYNC_STATE_00 = "00";
	/** 商户信息的同步的状态：01 已变更未同步 **/
	public static final String SYNC_STATE_01 = "01";

	/********************* 生成商户编号 **************************/
	/** 生成商户编号-9-前缀 **/
	public static final String GEN_MCHTID_FRONT = "9";
	/** 生成商户编号-000001-后缀 **/
	public static final String GEN_MCHTID_BEHIND = "000001";

	/********************* 生成图片序号 **************************/
	/** 生成图片序号-100000-默认序号 **/
	public static final String DEFAULT_MCHT_PIC_ID = "100000";
	
	public static final String ROLE_ID_AMR = "ROLE_ID_AMR";
}
