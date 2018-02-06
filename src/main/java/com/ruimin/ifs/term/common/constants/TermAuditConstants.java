package com.ruimin.ifs.term.common.constants;

/**
 * 
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司 Author: chenggx Date:
 * 2016年10月24日下午3:59:03 History: //修改记录 Version: 0.1 Desc: 审核步骤的常量
 */
public class TermAuditConstants {

	/********************** 终端信息审核的业务 2X为终端信息审核类 ******************************/
	/** 20-终端信息登记 **/
	public static final String AUDIT_TYPE_20 = "20";
	/** 21-终端信息变更 **/
	public static final String AUDIT_TYPE_21 = "21";
	/** 22-终端信息启用 **/
	public static final String AUDIT_TYPE_22 = "22";
	/** 23-终端信息停用 **/
	public static final String AUDIT_TYPE_23 = "23";
	/** 24-终端信息绑定 **/
	public static final String AUDIT_TYPE_24 = "24";
	/** 25-终端信息解绑 **/
	public static final String AUDIT_TYPE_25 = "25";

	/********************** 终端信息审核的同步状态 ******************************/

	/** 合同信息的同步的状态：00 已同步 */
	public static final String SYNC_STATE_00 = "00";
	/** 合同信息的同步的状态：01 已变更未同步 */
	public static final String SYNC_STATE_01 = "01";

	/************************** 审核信息，审核状态 ***********************************/
	/** 00-未审核； **/
	public static final String AUDIT_STATE_00 = "00";
	/** 01-审核通过； **/
	public static final String AUDIT_STATE_01 = "01";
	/** 02-审核拒绝； **/
	public static final String AUDIT_STATE_02 = "02";

}
