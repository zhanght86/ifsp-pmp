/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: ImpAuditInfo.java
 * Author:   MJ
 * Date:     2015年12月7日 下午2:17:14
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.mng.process.bean;

import java.io.Serializable;
import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 〈 审核任务管理 〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author MJ
 */
@Table("IMP_AUDIT_INFO")
public class ImpAuditInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 任务编号 */
	@Id
	private String auditId;
	/** 任务类型 */
	private String auditType;
	/** 任务描述 */
	private String auditDesc;
	/** 任务状态 */
	private String auditStat;

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
	 * @return the auditType
	 */
	public String getAuditType() {
		return auditType;
	}

	/**
	 * @param auditType
	 *            the auditType to set
	 */
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	/**
	 * @return the auditDesc
	 */
	public String getAuditDesc() {
		return auditDesc;
	}

	/**
	 * @param auditDesc
	 *            the auditDesc to set
	 */
	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}

	/**
	 * @return the auditStat
	 */
	public String getAuditStat() {
		return auditStat;
	}

	/**
	 * @param auditStat
	 *            the auditStat to set
	 */
	public void setAuditStat(String auditStat) {
		this.auditStat = auditStat;
	}

}
