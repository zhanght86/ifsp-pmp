
package com.ruimin.ifs.pmp.risk.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("RISK_MODEL_VALUES")
public class RiskModelValuesVO {
    @Id
    // 模型编号
    private String riskModelId;
    @Id
    //阈值编号
    private String valueId;

    //阈值
    private String value;
 
    private String crtTlr;
    
    private String crtDateTime;
    
    private String lastUpdTlr;
    
    private String lastUpdDateTime;

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



}
