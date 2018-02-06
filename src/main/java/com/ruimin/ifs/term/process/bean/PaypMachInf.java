/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.po 
 * FileName: PaypMachInf.java
 * Author:   wangyl
 * Date:     2016年8月8日 下午3:48:56
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   wangyl           2016年8月8日下午3:48:56                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.term.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 名称：〈设备库存信息〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月8日 <br>
 * 作者：wangyl <br>
 * 说明：<br>
 */
@Table("PAYP_MACH_INF")
public class PaypMachInf {
	@Id
	private String machId;// 设备库存编号
	private String machType; // 设备类型
	private String machStat; // 设备状态
	private String batchNo; // 批次号
	private String machInst; // 所属机构
	private String propertyType; // 产权归属
	private String propertyInst; // 产权机构名
	private String companyName; // 厂商名
	private String lastAudTlr; // 最近审核操作员
	private String lastAudDateTime; // 最近审核日期时间
	private String crtTlr; // 创建操作员
	private String crtDateTime; // 创建日期时间
	private String lastUpdTlr; // 最近更新操作员
	private String lastUpdDateTime; // 最近更新日期时间

	/** 20170114 新增字段 */
	private String machNo;
	private String confNo;
	private String machVersion;
	
	public String getMachNo() {
		return machNo;
	}

	public void setMachNo(String machNo) {
		this.machNo = machNo;
	}

	public String getConfNo() {
		return confNo;
	}

	public void setConfNo(String confNo) {
		this.confNo = confNo;
	}

	public String getMachVersion() {
		return machVersion;
	}

	public void setMachVersion(String machVersion) {
		this.machVersion = machVersion;
	}

	/**
	 * @return the machId
	 */
	public String getMachId() {
		return machId;
	}

	/**
	 * @param machId
	 *            the machId to set
	 */
	public void setMachId(String machId) {
		this.machId = machId;
	}

	/**
	 * @return the machType
	 */
	public String getMachType() {
		return machType;
	}

	/**
	 * @param machType
	 *            the machType to set
	 */
	public void setMachType(String machType) {
		this.machType = machType;
	}

	/**
	 * @return the machStat
	 */
	public String getMachStat() {
		return machStat;
	}

	/**
	 * @param machStat
	 *            the machStat to set
	 */
	public void setMachStat(String machStat) {
		this.machStat = machStat;
	}

	/**
	 * @return the batchNo
	 */
	public String getBatchNo() {
		return batchNo;
	}

	/**
	 * @param batchNo
	 *            the batchNo to set
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	/**
	 * @return the machInst
	 */
	public String getMachInst() {
		return machInst;
	}

	/**
	 * @param machInst
	 *            the machInst to set
	 */
	public void setMachInst(String machInst) {
		this.machInst = machInst;
	}

	/**
	 * @return the propertyType
	 */
	public String getPropertyType() {
		return propertyType;
	}

	/**
	 * @param propertyType
	 *            the propertyType to set
	 */
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	/**
	 * @return the propertyInst
	 */
	public String getPropertyInst() {
		return propertyInst;
	}

	/**
	 * @param propertyInst
	 *            the propertyInst to set
	 */
	public void setPropertyInst(String propertyInst) {
		this.propertyInst = propertyInst;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the lastAudTlr
	 */
	public String getLastAudTlr() {
		return lastAudTlr;
	}

	/**
	 * @param lastAudTlr
	 *            the lastAudTlr to set
	 */
	public void setLastAudTlr(String lastAudTlr) {
		this.lastAudTlr = lastAudTlr;
	}

	/**
	 * @return the lastAudDateTime
	 */
	public String getLastAudDateTime() {
		return lastAudDateTime;
	}

	/**
	 * @param lastAudDateTime
	 *            the lastAudDateTime to set
	 */
	public void setLastAudDateTime(String lastAudDateTime) {
		this.lastAudDateTime = lastAudDateTime;
	}

	/**
	 * @return the crtTlr
	 */
	public String getCrtTlr() {
		return crtTlr;
	}

	/**
	 * @param crtTlr
	 *            the crtTlr to set
	 */
	public void setCrtTlr(String crtTlr) {
		this.crtTlr = crtTlr;
	}

	/**
	 * @return the crtDateTime
	 */
	public String getCrtDateTime() {
		return crtDateTime;
	}

	/**
	 * @param crtDateTime
	 *            the crtDateTime to set
	 */
	public void setCrtDateTime(String crtDateTime) {
		this.crtDateTime = crtDateTime;
	}

	/**
	 * @return the lastUpdTlr
	 */
	public String getLastUpdTlr() {
		return lastUpdTlr;
	}

	/**
	 * @param lastUpdTlr
	 *            the lastUpdTlr to set
	 */
	public void setLastUpdTlr(String lastUpdTlr) {
		this.lastUpdTlr = lastUpdTlr;
	}

	/**
	 * @return the lastUpdDateTime
	 */
	public String getLastUpdDateTime() {
		return lastUpdDateTime;
	}

	/**
	 * @param lastUpdDateTime
	 *            the lastUpdDateTime to set
	 */
	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}

}
