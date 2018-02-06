/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.mchtMng.process.bean 
 * FileName: RateRuleInfo.java
 * Author:   zrx
 * Date:     2016年7月18日 下午2:36:49
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx          2016年7月18日下午2:36:49                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mchtMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月18日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
@Table("PBS_RATE_RULE_INFO")
public class RateRuleInfo {
	@Id
	// 费率规则序号
	private String rateRuleNo;
	// 费率编号
	private String rateId;
	// 分段最小值
	private String sectionMin;
	// 分段最大值
	private String sectionMax;
	// 收费类型
	private String chargeType;
	// 收费值
	private String chargeValue;
	// 最小手续费
	private String feeMin;
	// 最大手续费
	private String feeMax;
	// 数据有效状态
	private String dateState;
	// 创建柜员
	private String crtTlr;
	// 创建日期时间
	private String crtDateTime;
	// 最近更新柜员
	private String lastUpdTlr;
	// 最近更新日期时间
	private String lastUpdDateTime;

	public String getRateRuleNo() {
		return rateRuleNo;
	}

	public void setRateRuleNo(String rateRuleNo) {
		this.rateRuleNo = rateRuleNo;
	}

	public String getRateId() {
		return rateId;
	}

	public void setRateId(String rateId) {
		this.rateId = rateId;
	}

	public String getSectionMin() {
		return sectionMin;
	}

	public void setSectionMin(String sectionMin) {
		this.sectionMin = sectionMin;
	}

	public String getSectionMax() {
		return sectionMax;
	}

	public void setSectionMax(String sectionMax) {
		this.sectionMax = sectionMax;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getChargeValue() {
		return chargeValue;
	}

	public void setChargeValue(String chargeValue) {
		this.chargeValue = chargeValue;
	}

	public String getFeeMin() {
		return feeMin;
	}

	public void setFeeMin(String feeMin) {
		this.feeMin = feeMin;
	}

	public String getFeeMax() {
		return feeMax;
	}

	public void setFeeMax(String feeMax) {
		this.feeMax = feeMax;
	}

	public String getDateState() {
		return dateState;
	}

	public void setDateState(String dateState) {
		this.dateState = dateState;
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
