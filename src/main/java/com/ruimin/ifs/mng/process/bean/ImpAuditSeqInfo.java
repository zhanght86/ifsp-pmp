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
 * 〈 审核流水表 〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author MJ
 */
@Table("IMP_AUDIT_SEQ_INFO")
public class ImpAuditSeqInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 审核流水 */
	@Id
	private String seqId;
	/** 审核类型 */
	private String auditType;
	/** 审核索引 */
	private String auditIndex;
	/** 任务编号 */
	private String auditId;
	/** 审核级别 */
	private String auditLevel;
	/** 审核角色 */
	private String auditRole;
	/** 审核状态 */
	private String auditStat;
	/** 审核操作员 */
	private String auditOpr;
	/** 创建时间 */
	private String auditCrt;
	/** 审核时间 */
	private String auditUpd;
	/** 操作描述 */
	private String oprDesc;

	/**
	 * @return the seqId
	 */
	public String getSeqId() {
		return seqId;
	}

	/**
	 * @param seqId
	 *            the seqId to set
	 */
	public void setSeqId(String seqId) {
		this.seqId = seqId;
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
	 * @return the auditIndex
	 */
	public String getAuditIndex() {
		return auditIndex;
	}

	/**
	 * @param auditIndex
	 *            the auditIndex to set
	 */
	public void setAuditIndex(String auditIndex) {
		this.auditIndex = auditIndex;
	}

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

	/**
	 * @return the auditOpr
	 */
	public String getAuditOpr() {
		return auditOpr;
	}

	/**
	 * @param auditOpr
	 *            the auditOpr to set
	 */
	public void setAuditOpr(String auditOpr) {
		this.auditOpr = auditOpr;
	}

	/**
	 * @return the auditCrt
	 */
	public String getAuditCrt() {
		return auditCrt;
	}

	/**
	 * @param auditCrt
	 *            the auditCrt to set
	 */
	public void setAuditCrt(String auditCrt) {
		this.auditCrt = auditCrt;
	}

	/**
	 * @return the auditUpd
	 */
	public String getAuditUpd() {
		return auditUpd;
	}

	/**
	 * @param auditUpd
	 *            the auditUpd to set
	 */
	public void setAuditUpd(String auditUpd) {
		this.auditUpd = auditUpd;
	}

	/**
	 * @return the oprDesc
	 */
	public String getOprDesc() {
		return oprDesc;
	}

	/**
	 * @param oprDesc
	 *            the oprDesc to set
	 */
	public void setOprDesc(String oprDesc) {
		this.oprDesc = oprDesc;
	}

}
