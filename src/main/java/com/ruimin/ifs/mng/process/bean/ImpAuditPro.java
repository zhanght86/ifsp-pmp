/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: ImpAuditPro.java
 * Author:   MJ
 * Date:     2015年11月19日 下午4:05:44
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.mng.process.bean;

import java.io.Serializable;
import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 〈 审核流程表 〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author MJ
 */
@Table("IMP_AUDIT_PRO")
public class ImpAuditPro implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 任务编号 */
	@Id
	private String auditId;
	/** 审核级别 */
	@Id
	private String auditLevel;
	/** 任务名称 */
	private String auditName;
	/** 审核角色 */
	private String auditRole;

	/**
	 * @return the auditId
	 */
	public String getAuditId() {
		return auditId;
	}

	/**
	 * @param auditId
	 *            the auditId to set
	 */
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	/**
	 * @return the auditLevel
	 */
	public String getAuditLevel() {
		return auditLevel;
	}

	/**
	 * @param auditLevel
	 *            the auditLevel to set
	 */
	public void setAuditLevel(String auditLevel) {
		this.auditLevel = auditLevel;
	}

	/**
	 * @return the auditName
	 */
	public String getAuditName() {
		return auditName;
	}

	/**
	 * @param auditName
	 *            the auditName to set
	 */
	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	/**
	 * @return the auditRole
	 */
	public String getAuditRole() {
		return auditRole;
	}

	/**
	 * @param auditRole
	 *            the auditRole to set
	 */
	public void setAuditRole(String auditRole) {
		this.auditRole = auditRole;
	}

}
