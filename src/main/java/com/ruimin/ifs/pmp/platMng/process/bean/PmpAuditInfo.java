/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.platMng.process.bean 
 * FileName: PmpAuditInfo.java
 * Author:   chenqilei
 * Date:     2016年8月16日 上午11:11:52
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年8月16日上午11:11:52                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.platMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月16日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
@Table("PMP_AUDIT_INFO")
public class PmpAuditInfo {
	@Id
	private String auditId; // 审核信息编号
	private String auditType; // 审核业务类型
	private String auditProcId; // 审核流程编号
	private String applyDateTime; // 申请日期时间
	private String applyTlrNo; // 申请柜员编号
	private String applyBrNo; // 申请机构编号
	private String auditUrl; // 审核信息路径
	private String auditDesc; // 审核信息描述
	private String auditState; // 审核状态
	private String crtDateTime; // 创建日期时间
	private String lastUpdDateTime; // 最后更新日期时间

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public String getAuditProcId() {
		return auditProcId;
	}

	public void setAuditProcId(String auditProcId) {
		this.auditProcId = auditProcId;
	}

	public String getApplyDateTime() {
		return applyDateTime;
	}

	public void setApplyDateTime(String applyDateTime) {
		this.applyDateTime = applyDateTime;
	}

	public String getApplyTlrNo() {
		return applyTlrNo;
	}

	public void setApplyTlrNo(String applyTlrNo) {
		this.applyTlrNo = applyTlrNo;
	}

	public String getApplyBrNo() {
		return applyBrNo;
	}

	public void setApplyBrNo(String applyBrNo) {
		this.applyBrNo = applyBrNo;
	}

	public String getAuditUrl() {
		return auditUrl;
	}

	public void setAuditUrl(String auditUrl) {
		this.auditUrl = auditUrl;
	}

	public String getAuditDesc() {
		return auditDesc;
	}

	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public String getCrtDateTime() {
		return crtDateTime;
	}

	public void setCrtDateTime(String crtDateTime) {
		this.crtDateTime = crtDateTime;
	}

	public String getLastUpdDateTime() {
		return lastUpdDateTime;
	}

	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}

}
