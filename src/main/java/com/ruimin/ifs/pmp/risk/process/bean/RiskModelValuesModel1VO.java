
package com.ruimin.ifs.pmp.risk.process.bean;

/**
 * 该类只作为模型阈值表的map映射值
 * @author fengjunfeng
 *
 */
public class RiskModelValuesModel1VO {
    // 模型编号
    private String riskModelId;
    //阈值编号
    private String valueId;
    private String valueExplain;
    private String param1;
    private String param2;
    private String param3;
    private String crtTlr; 
    private String crtDateTime; 
    private String lastUpdTlr;
    private String lastUpdDateTime;
    private String valueIdString;//当前风控模型被风控原型引用的所有风控规则阈值操作表下面的阈值编号
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
	public String getValueExplain() {
		return valueExplain;
	}
	public void setValueExplain(String valueExplain) {
		this.valueExplain = valueExplain;
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
