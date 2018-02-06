package com.ruimin.ifs.pmp.risk.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 商户证书配置表 名称：<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月1日 <br>
 * 作者：Administrator <br>
 * 说明：<br>
 */
@Table("RISK_MODEL_CFG")
public class RiskModelVO {

    @Id
    private String riskModelId; // 模型编号

    private String riskModelType; // 模型类型
    
    private String riskModelName; // 模型名称
    private String riskModelDesc;
    private String actionBitmap; // 风控动作位图
 
    private String status; // 使用状态
    
    private String lastUpdTlr;//最后修改操作员
    
    private String lastUpdDateTime;//最后修改日期时间
    
	public String getRiskModelId() {
		return riskModelId;
	}
	public void setRiskModelId(String riskModelId) {
		this.riskModelId = riskModelId;
	}
	public String getRiskModelType() {
		return riskModelType;
	}
	public void setRiskModelType(String riskModelType) {
		this.riskModelType = riskModelType;
	}
	public String getRiskModelName() {
		return riskModelName;
	}
	public void setRiskModelName(String riskModelName) {
		this.riskModelName = riskModelName;
	}
	public String getActionBitmap() {
		return actionBitmap;
	}
	public void setActionBitmap(String actionBitmap) {
		this.actionBitmap = actionBitmap;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRiskModelDesc() {
		return riskModelDesc;
	}
	public void setRiskModelDesc(String riskModelDesc) {
		this.riskModelDesc = riskModelDesc;
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
