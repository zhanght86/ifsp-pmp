package com.ruimin.ifs.pmp.mktActivity.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("TBL_ACTIVE_METHOD_INF_TMP")
public class ActiveMethodInfTmpVO {
	/**
	 * 表名: TblActiveMethodInfTmp alias 用于页面显示对应中英文信息  .
	 */
	public static final String TABLE_ALIAS = "TblActiveMethodInfTmp";
	/** 列 METHOD_NO 的对应中英文信息. */
	public static final String ALIAS_METHOD_NO = "methodNo";
	/** 列 METHOD_TP 的对应中英文信息. */
	public static final String ALIAS_METHOD_TP = "methodTp";
	/** 列 METHOD_NM 的对应中英文信息. */
	public static final String ALIAS_METHOD_NM = "methodNm";
	/** 列 PARAM_1_TP 的对应中英文信息. */
	public static final String ALIAS_PARAM_1_TP = "param1tp";
	/** 列 PARAM_1_DATA 的对应中英文信息. */
	public static final String ALIAS_PARAM_1_DATA = "param1data";
	/** 列 PARAM_2_TP 的对应中英文信息. */
	public static final String ALIAS_PARAM_2_TP = "param2tp";
	/** 列 PARAM_3_TP 的对应中英文信息. */
	public static final String ALIAS_PARAM_3_TP = "param3tp";
	/** 列 PARAM_3_DATA 的对应中英文信息. */
	public static final String ALIAS_PARAM_3_DATA = "param3data";
	/** 列 PARAM_2_DATA 的对应中英文信息. */
	public static final String ALIAS_PARAM_2_DATA = "param2data";
	/** 列 PARAM_4_TP 的对应中英文信息. */
	public static final String ALIAS_PARAM_4_TP = "param4tp";
	/** 列 PARAM_4_DATA 的对应中英文信息. */
	public static final String ALIAS_PARAM_4_DATA = "param4data";
	/** 列 UPDATE_TIME 的对应中英文信息. */
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	/** 列 UPDATE_OPR 的对应中英文信息. */
	public static final String ALIAS_UPDATE_OPR = "updateOpr";
	/** 列 AUDIT_OPR 的对应中英文信息. */
	public static final String ALIAS_AUDIT_OPR = "auditOpr";
	/** 列 AUDIT_TIME 的对应中英文信息. */
	public static final String ALIAS_AUDIT_TIME = "auditTime";
	/** 列 AUDIT_FLAG 的对应中英文信息. */
	public static final String ALIAS_AUDIT_FLAG = "auditFlag";
	/** 列 REMARK 的对应中英文信息. */
	public static final String ALIAS_REMARK = "remark";
	/** 列 METHOD_STAT 的对应中英文信息. */
	public static final String ALIAS_METHOD_STAT = "methodStat";
	
	//date formats
	
	//columns START
	/** 变量 methodNo . */
	@Id
	private String methodNo;
	/** 变量 methodTp . */
	private String methodTp;
	/** 变量 methodNm . */
	private String methodNm;
	/** 变量 param1tp . */
	private String param1Tp;
	/** 变量 param1data . */
	private String param1Data;
	/** 变量 param2tp . */
	private String param2Tp;
	/** 变量 param3tp . */
	private String param3Tp;
	/** 变量 param3data . */
	private String param3Data;
	/** 变量 param2data . */
	private String param2Data;
	/** 变量 param4tp . */
	private String param4Tp;
	/** 变量 param4data . */
	private String param4Data;
	/** 变量 updateTime . */
	private String updateTime;
	/** 变量 updateOpr . */
	private String updateOpr;
	/** 变量 auditOpr . */
	private String auditOpr;
	/** 变量 auditTime . */
	private String auditTime;
	/** 变量 auditFlag . */
	private String auditFlag;
	/** 变量 remark . */
	private String remark;
	/** 变量 methodStat . */
	private String methodStat;
	private String syncFlag;
	
	
	//存储使用状态所用字段
	private String useState;
	//新增审核id字段
	private String auditId;
	
	//columns END
	public String getSyncFlag() {
		return syncFlag;
	}
	public void setSyncFlag(String syncFlag) {
		this.syncFlag = syncFlag;
	}
	
    public String getAuditId() {
        return auditId;
    }
   
    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }
    
    /**
	 * MethodNo 置值.
	 * @param  java.lang.String
	 */	
	public void setMethodNo(String value) {
		this.methodNo = value;
	}
	/**
	 * MethodNo 取值.
	 * @return java.lang.String
	 */
	public String getMethodNo() {
		return this.methodNo;
	}
	/**
	 * MethodTp 置值.
	 * @param  java.lang.String
	 */	
	public void setMethodTp(String value) {
		this.methodTp = value;
	}
	/**
	 * MethodTp 取值.
	 * @return java.lang.String
	 */
	public String getMethodTp() {
		return this.methodTp;
	}
	/**
	 * MethodNm 置值.
	 * @param  java.lang.String
	 */	
	public void setMethodNm(String value) {
		this.methodNm = value;
	}
	/**
	 * MethodNm 取值.
	 * @return java.lang.String
	 */
	public String getMethodNm() {
		return this.methodNm;
	}
	
	public String getParam1Tp() {
		return param1Tp;
	}
	public void setParam1Tp(String param1Tp) {
		this.param1Tp = param1Tp;
	}
	public String getParam1Data() {
		return param1Data;
	}
	public void setParam1Data(String param1Data) {
		this.param1Data = param1Data;
	}
	public String getParam2Tp() {
		return param2Tp;
	}
	public void setParam2Tp(String param2Tp) {
		this.param2Tp = param2Tp;
	}
	public String getParam3Tp() {
		return param3Tp;
	}
	public void setParam3Tp(String param3Tp) {
		this.param3Tp = param3Tp;
	}
	public String getParam3Data() {
		return param3Data;
	}
	public void setParam3Data(String param3Data) {
		this.param3Data = param3Data;
	}
	public String getParam2Data() {
		return param2Data;
	}
	public void setParam2Data(String param2Data) {
		this.param2Data = param2Data;
	}
	public String getParam4Tp() {
		return param4Tp;
	}
	public void setParam4Tp(String param4Tp) {
		this.param4Tp = param4Tp;
	}
	public String getParam4Data() {
		return param4Data;
	}
	public void setParam4Data(String param4Data) {
		this.param4Data = param4Data;
	}
	
	/**
	 * UpdateTime 置值.
	 * @param  java.lang.String
	 */	
	public void setUpdateTime(String value) {
		this.updateTime = value;
	}
	/**
	 * UpdateTime 取值.
	 * @return java.lang.String
	 */
	public String getUpdateTime() {
		return this.updateTime;
	}
	/**
	 * UpdateOpr 置值.
	 * @param  java.lang.String
	 */	
	public void setUpdateOpr(String value) {
		this.updateOpr = value;
	}
	/**
	 * UpdateOpr 取值.
	 * @return java.lang.String
	 */
	public String getUpdateOpr() {
		return this.updateOpr;
	}
	/**
	 * AuditOpr 置值.
	 * @param  java.lang.String
	 */	
	public void setAuditOpr(String value) {
		this.auditOpr = value;
	}
	/**
	 * AuditOpr 取值.
	 * @return java.lang.String
	 */
	public String getAuditOpr() {
		return this.auditOpr;
	}
	/**
	 * AuditTime 置值.
	 * @param  java.lang.String
	 */	
	public void setAuditTime(String value) {
		this.auditTime = value;
	}
	/**
	 * AuditTime 取值.
	 * @return java.lang.String
	 */
	public String getAuditTime() {
		return this.auditTime;
	}
	/**
	 * AuditFlag 置值.
	 * @param  java.lang.String
	 */	
	public void setAuditFlag(String value) {
		this.auditFlag = value;
	}
	/**
	 * AuditFlag 取值.
	 * @return java.lang.String
	 */
	public String getAuditFlag() {
		return this.auditFlag;
	}
	/**
	 * Remark 置值.
	 * @param  java.lang.String
	 */	
	public void setRemark(String value) {
		this.remark = value;
	}
	/**
	 * Remark 取值.
	 * @return java.lang.String
	 */
	public String getRemark() {
		return this.remark;
	}
	/**
	 * MethodStat 置值.
	 * @param  java.lang.String
	 */	
	public void setMethodStat(String value) {
		this.methodStat = value;
	}
	/**
	 * MethodStat 取值.
	 * @return java.lang.String
	 */
	public String getMethodStat() {
		return this.methodStat;
	}
	public String getUseState() {
		return useState;
	}
	public void setUseState(String useState) {
		this.useState = useState;
	}
	
}
