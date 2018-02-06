package com.ruimin.ifs.pmp.risk.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("RISK_VALUE_OPERATE")
public class RiskValueOperateVo {
	@Id
	private String riskId;
	@Id
	private String valueId;
	@Id
	private String riskLevel;
	private String riskOperate;
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
	
}
