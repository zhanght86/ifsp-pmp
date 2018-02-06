/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: PrdPrptInfo.java
 * Author:   MJ
 * Date:     2015年11月12日 下午2:38:40
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.pmp.mktActivity.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("PRD_PRPT_INFO")
public class PrdPrptInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 产品编号
	 */
	@Id
	private String prdId;
	/**
	 * 产品名称
	 */
	private String prdName;
	/**
	 * 产品描述
	 */
	private String prdDesc;
	/**
	 * 产品状态
	 */
	private String prdStat;
	/**
	 * 产品类型
	 */
	private String prdType;
	/**
	 * 应用场景
	 */
	private String prdPaySence;
	
	private String crtTlr;
	private String crtDateTime;
	private String lastUpdTlr;
	private String lastUpdDateTime;
	
	/**
	 * @return the prdId
	 */
	public String getPrdId() {
		return prdId;
	}
	/**
	 * @param prdId the prdId to set
	 */
	public void setPrdId(String prdId) {
		this.prdId = prdId;
	}
	/**
	 * @return the prdName
	 */
	public String getPrdName() {
		return prdName;
	}
	/**
	 * @param prdName the prdName to set
	 */
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	/**
	 * @return the prdDesc
	 */
	public String getPrdDesc() {
		return prdDesc;
	}
	/**
	 * @param prdDesc the prdDesc to set
	 */
	public void setPrdDesc(String prdDesc) {
		this.prdDesc = prdDesc;
	}
	/**
	 * @return the prdType
	 */
	public String getPrdType() {
		return prdType;
	}
	/**
	 * @param prdType the prdType to set
	 */
	public void setPrdType(String prdType) {
		this.prdType = prdType;
	}
	/**
	 * @return the prdPaySence
	 */
	public String getPrdPaySence() {
		return prdPaySence;
	}
	/**
	 * @param prdPaySence the prdPaySence to set
	 */
	public void setPrdPaySence(String prdPaySence) {
		this.prdPaySence = prdPaySence;
	}
	/**
	 * @return the crtTlr
	 */
	public String getCrtTlr() {
		return crtTlr;
	}
	/**
	 * @param crtTlr the crtTlr to set
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
	 * @param crtDateTime the crtDateTime to set
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
	 * @param lastUpdTlr the lastUpdTlr to set
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
	 * @param lastUpdDateTime the lastUpdDateTime to set
	 */
	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}
	/**
	 * @return the prdStat
	 */
	public String getPrdStat() {
		return prdStat;
	}
	/**
	 * @param prdStat the prdStat to set
	 */
	public void setPrdStat(String prdStat) {
		this.prdStat = prdStat;
	}
	
	
}
