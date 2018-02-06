package com.ruimin.ifs.pmp.mchtMng.common.constants;

/**
 * 
 * 商户合同-常量类
 * 
 * @author Chenggx
 *
 */
public class MchtContractConstants {
	/************************** 商户合同的状态 ***********************************/
	/** 商户合同状态-执行中-00 **/
	public static final String MCHT_CONTR_STAT_NORMAL = "00";
	/** 商户合同状态-签订中-01 **/
	public static final String MCHT_CONTR_STAT_ON = "01";
	/** 商户合同状态-即将到期-02 **/
	public static final String MCHT_CONTR_STAT_DEADING = "02";
	/** 商户合同状态-已过期-03 **/
	public static final String MCHT_CONTR_STAT_DEADED = "03";
	/** 商户合同状态-中止-99 **/
	public static final String MCHT_CONTR_STAT_OFF = "99";

	/** 商户合同状态: 04-新增待审核 */
	public static final String MCHT_STAT_04 = "04";
	/** 商户合同状态: 05-新增被拒绝 */
	public static final String MCHT_STAT_05 = "05";
	/** 商户合同状态: 06-修改待审核 */
	public static final String MCHT_STAT_06 = "06";
	/** 商户合同状态: 07-修改被拒绝 */
	public static final String MCHT_STAT_07 = "07";
	/** 商户合同状态: 08-中止待审核 */
	public static final String MCHT_STAT_08 = "08";
	/** 商户合同状态: 09-中止被拒绝 */
	public static final String MCHT_STAT_09 = "09";
	/** 商户合同状态: 10-恢复待审核 */
	public static final String MCHT_STAT_10 = "10";
	/** 商户合同状态: 11-恢复被拒绝 */
	public static final String MCHT_STAT_11 = "11";
	/** 商户合同状态: 12-添加待提审 */
	public static final String MCHT_STAT_12 = "12";
	/** 商户合同状态: 13-修改待提审 */
	public static final String MCHT_STAT_13 = "13";

	/*************************
	 * 商户合同的信息 部分 常量
	 ****************************************************/
	/** 合同期限的常量：01 合同期限为1年 */
	public static final String CON_TERM_01 = "01";
	/** 合同期限的常量：02 合同期限为2年 */
	public static final String CON_TERM_02 = "02";

	/** 合同信息的同步的状态：00 已同步 */
	public static final String SYNC_STATE_00 = "00";
	/** 合同信息的同步的状态：01 已变更未同步 */
	public static final String SYNC_STATE_01 = "01";

	/** 合同交易限额以及商户合同关联产品的数据状态:数据有效状态:00-启用，99-停用 */
	public static final String DATA_STATE_00 = "00";

	public static final String DATA_STATE_99 = "99";

	/**************************
	 * 审核信息，业务类型, 1X为商户合同类
	 ***********************************/

	/** 10-商户合同登记； **/
	public static final String AUDIT_TYPE_10 = "10";
	/** 11-商户合同变更； **/
	public static final String AUDIT_TYPE_11 = "11";
	/** 12-商户合同中止 **/
	public static final String AUDIT_TYPE_12 = "12";
	/** 13-商户合同恢复； **/
	public static final String AUDIT_TYPE_13 = "13";

	/************************** 审核信息，审核状态 ***********************************/
	/** 00-未审核； **/
	public static final String AUDIT_STATE_00 = "00";
	/** 01-审核通过； **/
	public static final String AUDIT_STATE_01 = "01";
	/** 02-审核拒绝； **/
	public static final String AUDIT_STATE_02 = "02";
	/********************* 合同图片的内容 ************************/
	/** 第一张图片-01 **/
	public static final String MCHT_CON_PIC_NO1 = "01";
	/** 第二张图片-02 **/
	public static final String MCHT_CON_PIC_NO2 = "02";
	/** 第三张图片-03 **/
	public static final String MCHT_CON_PIC_NO3 = "03";
	/** 第四张图片-04 **/
	public static final String MCHT_CON_PIC_NO4 = "04";
	/** 第五张图片-05 **/
	public static final String MCHT_CON_PIC_NO5 = "05";
	
}
