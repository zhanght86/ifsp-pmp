package com.ruimin.ifs.pmp.mktActivity.common.constants;

/**
 * 活动信息constant类
 */
public class ActiveInfConstants {
									/**********活动状态***********/
	/**活动状态-10-准备完成*/
	public static final String ACTIVE_STATE_READY_DONE = "10";
	/**活动状态-11-活动中*/
	public static final String ACTIVE_STATE_ONGOING = "11";
	/**活动状态-12-暂停*/
	public static final String ACTIVE_STATE_PAUSE = "12";
	/**活动状态-99-结束*/
	public static final String ACTIVE_STATE_END = "99";
	
                                    /**********活动类型***********/
	/**活动类型-11-首单立减*/
	public static final String ACTIVE_TYPE_11 = "11";
	/**活动类型-21-红包立减*/
	public static final String ACTIVE_TYPE_21 = "21";
									/********** 数据同步状态 ***********/
	/** 数据同步标志-0：已变更，未同步 */
	public static final String SYNC_STATE_UNDONE = "0";
	/** 数据同步标志-1：已同步 */
	public static final String SYNC_STATE_DONE = "1";
	
	/**信息数据状态-1：有效*/
	public static final String DATA_STATE_VALID = "1";
	/**信息数据状态-0：无效*/
	public static final String DATA_STATE_INVALID = "0";
	
	/**活动编号前缀-AT*/
	public static final String ACTIVE_NO_PRE = "AT";
	
	/**活动编号-数字序号长度-5*/
	public static int ACTIVE_NO_SEQ_LENGTH = 5;
	
	/**活动周期标志-1-是*/
	public static String ACTIVE_CYCLE_FLAG_YES="1";
	
	/**活动周期标志-0-不是*/
	public static String ACTIVE_CYCLE_FLAG_NOT="0";
	
	
	/**状态操作标志-1-恢复*/
	public static String STATUS_FLAG_RESUME="1";

	/**状态操作标志-0-暂停*/
	public static String STATUS_FLAG_PAUSE="0";
	/**********审核状态***********/
    /**审核状态-00：正常*/
    public static final String AUDIT_FLAG_00="00";
    /**审核状态-01：新增待审核*/
    public static final String AUDIT_FLAG_01="01";
    /**审核状态-02：新增被拒绝*/
    public static final String AUDIT_FLAG_02="02";
    /**审核状态-03：修改待审核*/
    public static final String AUDIT_FLAG_03="03";
    /**审核状态-04：配置待审核*/
    public static final String AUDIT_FLAG_04="04";
    /**审核状态-05：参与商户待审核*/
    public static final String AUDIT_FLAG_05="05";
    /**审核状态-06：暂停待审核*/
    public static final String AUDIT_FLAG_06="06";
    /**审核状态-07：恢复待审核*/
    public static final String AUDIT_FLAG_07="07";
    
    /************************* 审核业务类型 ****************************/
    /** 活动管理新增审核 */
    public static final String AUDIT_TYPE_50 = "50"; 
    /** 活动管理修改审核 */
    public static final String AUDIT_TYPE_51 = "51";
    /** 活动方法配置审核 */
    public static final String AUDIT_TYPE_52 = "52";
    /** 活动方法参与商户审核 */
    public static final String AUDIT_TYPE_53 = "53";
    /** 活动方法暂停审核 */
    public static final String AUDIT_TYPE_54 = "54";
    /** 活动方法恢复审核 */
    public static final String AUDIT_TYPE_55 = "55";
    /************************* 审核信息表审核状态 ****************************/
    /** 未审核*/
    public static final String AUDIT_STATE_00 = "00";
    /** 审核通过*/
    public static final String AUDIT_STATE_01 = "01";
    /** 审核拒绝*/
    public static final String AUDIT_STATE_02 = "02";
	
	
}
