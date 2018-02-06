package com.ruimin.ifs.pmp.mktActivity.common.constants;
/**
 * 活动方法Constants类
 * */
public class ActiveMethodConstants {
	
	                          /**********活动方法状态***********/
	/** 方法状态: 00-正常 */
    public static final String MTHD_STAT_NORMAL = "00";
    /** 方法状态: 01-删除 */
    public static final String MTHD_STAT_DELETE = "01";
   
    						/**********数据同步状态***********/
	/**数据同步标志-0：已变更，未同步*/
	public static final String SYNC_STATE_UNDONE = "0";
	/**数据同步标志-1：已同步*/
	public static final String SYNC_STATE_DONE = "1";
	
							/**********数据同步状态***********/
	/**方法类型-11：收单立减*/
	public static final String MTHD_TYPE_FEE_DISC="11";
	/**方法类型-21：红包立减*/
	public static final String MTHD_TYPE_FEE_21="21";
	/**方法类型-31：打折*/
	public static final String MTHD_TYPE_DISC="31";
	/**方法类型-32：折后减后凑整*/
	public static final String MTHD_TYPE_DISC_INT="32";
	/**方法类型-33：满额顺序抽奖*/
	public static final String MTHD_TYPE_SEQ_LOTY="33";
	/**方法类型-34：满额随机抽奖*/
	public static final String MTHD_TYPE_RAND_LOTY="34";
	/**方法类型-41：代金券*/
	public static final String MTHD_TYPE_RAND_VOUC="41";
	                        /**********审核状态***********/
	/**审核状态-00：正常*/
	public static final String AUDIT_FLAG_00="00";
	/**审核状态-01：新增待审核*/
    public static final String AUDIT_FLAG_01="01";
    /**审核状态-02：新增被拒绝*/
    public static final String AUDIT_FLAG_02="02";
    /**审核状态-03：修改待审核*/
    public static final String AUDIT_FLAG_03="03";
    /**审核状态-04：删除待审核*/
    public static final String AUDIT_FLAG_04="04";
    
    /************************* 审核业务类型 ****************************/
    /** 活动方法新增审核 */
    public static final String AUDIT_TYPE_40 = "40"; 
    /** 活动方法修改审核 */
    public static final String AUDIT_TYPE_41 = "41";
    /** 活动方法删除审核 */
    public static final String AUDIT_TYPE_42 = "42";
    /************************* 审核信息表审核状态 ****************************/
    /** 未审核*/
    public static final String AUDIT_STATE_00 = "00";
    /** 审核通过*/
    public static final String AUDIT_STATE_01 = "01";
    /** 审核拒绝*/
    public static final String AUDIT_STATE_02 = "02";
}
