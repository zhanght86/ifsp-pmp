/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.platMng.process.bean 
 * FileName: PmpAuditStepInfo.java
 * Author:   chenqilei
 * Date:     2016年8月16日 上午11:21:25
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年8月16日上午11:21:25                     1.0                  
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
@Table("PMP_AUDIT_STEP_INFO")
public class PmpAuditStepInfo {
	@Id
	private String seqId; // 记录序号
	private String auditId; // 审核信息编号
	private Integer stepNo; // 步骤编号
	private String auditState; // 审核状态
	private Integer roleId; // 审核角色
	private String auditOprNo; // 审核人编号
	private String auditDatetIme; // 审核日期时间
	private String auditView; // 审核意见

	public String getSeqId() {
		return seqId;
	}

	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public Integer getStepNo() {
		return stepNo;
	}

	public void setStepNo(Integer stepNo) {
		this.stepNo = stepNo;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getAuditOprNo() {
		return auditOprNo;
	}

	public void setAuditOprNo(String auditOprNo) {
		this.auditOprNo = auditOprNo;
	}

	public String getAuditDatetIme() {
		return auditDatetIme;
	}

	public void setAuditDatetIme(String auditDatetIme) {
		this.auditDatetIme = auditDatetIme;
	}

	public String getAuditView() {
		return auditView;
	}

	public void setAuditView(String auditView) {
		this.auditView = auditView;
	}

}
