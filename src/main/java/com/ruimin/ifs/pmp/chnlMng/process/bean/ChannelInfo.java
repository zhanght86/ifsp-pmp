/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.chnlMng.process.bean 
 * FileName: ChannelInfo.java
 * Author:   zrx
 * Date:     2016年7月21日 上午10:07:53
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月21日上午10:07:53                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月21日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
@Table("PAGY_CHANNEL_INFO")
public class ChannelInfo {
	/** 变量 chlId . */
	@Id
	private String chlId;
	/** 变量 chlName . */
	private String chlName;
	/** 变量 chlStat . */
	private String chlStat;
	/** 变量 chlType . */
	private String chlType;
	/** 变量 chlAccNo . */
	private String chlAccNo;
	/** 变量 chlRateCode . */
	private String chlRateCode;
	/** 变量 chlSetlCycleType . */
	private String chlSetlCycleType;
	/** 变量 chlSetlCycle . */
	private String chlSetlCycle;
	/** 变量 chlSetlTm . */
	private String chlSetlTm;
	/** 变量 chlOpenDate . */
	private String chlOpenDate;
	/** 变量 crtTlr . */
	private String crtTlr;
	/** 变量 crtDateTime . */
	private String crtDateTime;
	/** 变量 lastUpdTlr . */
	private String lastUpdTlr;
	/** 变量 lastUpdDateTime . */
	private String lastUpdDateTime;

	public String getChlId() {
		return chlId;
	}

	public void setChlId(String chlId) {
		this.chlId = chlId;
	}

	public String getChlName() {
		return chlName;
	}

	public void setChlName(String chlName) {
		this.chlName = chlName;
	}

	public String getChlStat() {
		return chlStat;
	}

	public void setChlStat(String chlStat) {
		this.chlStat = chlStat;
	}

	public String getChlType() {
		return chlType;
	}

	public void setChlType(String chlType) {
		this.chlType = chlType;
	}

	public String getChlAccNo() {
		return chlAccNo;
	}

	public void setChlAccNo(String chlAccNo) {
		this.chlAccNo = chlAccNo;
	}

	public String getChlRateCode() {
		return chlRateCode;
	}

	public void setChlRateCode(String chlRateCode) {
		this.chlRateCode = chlRateCode;
	}

	public String getChlSetlCycleType() {
		return chlSetlCycleType;
	}

	public void setChlSetlCycleType(String chlSetlCycleType) {
		this.chlSetlCycleType = chlSetlCycleType;
	}

	public String getChlSetlCycle() {
		return chlSetlCycle;
	}

	public void setChlSetlCycle(String chlSetlCycle) {
		this.chlSetlCycle = chlSetlCycle;
	}

	public String getChlSetlTm() {
		return chlSetlTm;
	}

	public void setChlSetlTm(String chlSetlTm) {
		this.chlSetlTm = chlSetlTm;
	}

	public String getChlOpenDate() {
		return chlOpenDate;
	}

	public void setChlOpenDate(String chlOpenDate) {
		this.chlOpenDate = chlOpenDate;
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
