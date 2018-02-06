package com.ruimin.ifs.pmp.risk.process.bean;

/**
 * RISK_MODEL_VALUES  风控模型对应阈值表映射实体类
 */
public class RiskModelValueMapping{
	
	 private String riskModelId;
	 private String valueId;
	 private String value;
	 private String crtTlr;  
	 private String crtDateTime;   
	 private String lastUpdTlr;
	 private String lastUpdDateTime;
	 private String valueIdString;//查询当前模型被哪些风控引用，这些引用当者配置了哪些当前模型里面的阈值
	public String getRiskModelId() {
		return riskModelId;
	}
	public void setRiskModelId(String riskModelId) {
		this.riskModelId = riskModelId;
	}
	public String getValueId() {
		return valueId;
	}
	public void setValueId(String valueId) {
		this.valueId = valueId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
	public String getValueIdString() {
		return valueIdString;
	}
	public void setValueIdString(String valueIdString) {
		this.valueIdString = valueIdString;
	}
	 
	
}
