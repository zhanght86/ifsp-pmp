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
 * 作者：baiqike <br>
 * 说明：<br>
 */
@Table("PMP_AUDIT_PROC_STEP")
public class PmpAuditProcStep {
	@Id
	private String id;
	private String auditProcId;
	private Integer stepNo;
	private String stepName;
	private String stepDesc;
	private Integer auditRoleId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuditProcId() {
		return auditProcId;
	}

	public void setAuditProcId(String auditProcId) {
		this.auditProcId = auditProcId;
	}

	public Integer getStepNo() {
		return stepNo;
	}

	public void setStepNo(Integer stepNo) {
		this.stepNo = stepNo;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getStepDesc() {
		return stepDesc;
	}

	public void setStepDesc(String stepDesc) {
		this.stepDesc = stepDesc;
	}

	public Integer getAuditRoleId() {
		return auditRoleId;
	}

	public void setAuditRoleId(Integer auditRoleId) {
		this.auditRoleId = auditRoleId;
	}

}
