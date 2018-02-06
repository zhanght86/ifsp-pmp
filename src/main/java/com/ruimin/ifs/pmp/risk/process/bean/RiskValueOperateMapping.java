package com.ruimin.ifs.pmp.risk.process.bean;
/**
 *该类仅用于查询封装数据对象用，并不作为数据库表映射
 *风控规则阈值操作表
 *RISK_VALUE_OPERATE
 */
public class RiskValueOperateMapping {
	private String riskId;
	private String valueId;
	private String riskLevel;
	private String riskOperate;
	private String param1;
	private String param2;
	private String param3;
	private String value;
	public String getRiskId() {
		return riskId;
	}
	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}
	public String getValueId() {
		return valueId;
	}
	public void setValueId(String valueId) {
		this.valueId = valueId;
	}
	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	public String getRiskOperate() {
		return riskOperate;
	}
	public void setRiskOperate(String riskOperate) {
		this.riskOperate = riskOperate;
	}
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public String getParam2() {
		return param2;
	}
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	public String getParam3() {
		return param3;
	}
	public void setParam3(String param3) {
		this.param3 = param3;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
