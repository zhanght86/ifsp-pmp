package com.ruimin.ifs.pmp.risk.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 
 * 风控规则基础配置表-实体类
 * @author zhangjc
 *
 */
@Table("RISK_BASE_CFG")
public class RiskBaseCfg {
	@Id
	/** 变量 riskId . */
	private String riskId;
	/** 变量 riskName . */
	private String riskName;
	/** 变量 riskModelId . */
	private String riskModelId;
	/** 变量 riskStauts . */
	private String riskStauts;
	/** 变量 openDate . */
	private String openDate;
	/** 变量 endDate . */
	private String endDate;
	/** 变量 crtTlr . */
	private String crtTlr;
	/** 变量 crtDateTime . */
	private String crtDateTime;
	/** 变量 lastUpdTlr . */
	private String lastUpdTlr;
	/** 变量 lastUpdDateTime . */
	private String lastUpdDateTime;
	
	private String prodId;//产品编号
	
	private String  actionBitmap;//风控动作
	
	private String valueId;//模型阈值

	public String getRiskId() {
		return riskId;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getRiskModelId() {
		return riskModelId;
	}

	public void setRiskModelId(String riskModelId) {
		this.riskModelId = riskModelId;
	}

	public String getRiskStauts() {
		return riskStauts;
	}

	public void setRiskStauts(String riskStauts) {
		this.riskStauts = riskStauts;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCrtTlr() {
		return crtTlr;
	}

	public void setCrtTlr(String crtTlr) {
		this.crtTlr = crtTlr;
	}

	public String getCrtDateTime() {
		return crtDateTime;
	}

	public void setCrtDateTime(String crtDateTime) {
		this.crtDateTime = crtDateTime;
	}

	public String getLastUpdTlr() {
		return lastUpdTlr;
	}

	public void setLastUpdTlr(String lastUpdTlr) {
		this.lastUpdTlr = lastUpdTlr;
	}

	public String getLastUpdDateTime() {
		return lastUpdDateTime;
	}

	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getActionBitmap() {
		return actionBitmap;
	}

	public void setActionBitmap(String actionBitmap) {
		this.actionBitmap = actionBitmap;
	}

	public String getValueId() {
		return valueId;
	}

	public void setValueId(String valueId) {
		this.valueId = valueId;
	}

	
	
	
	
	
}
