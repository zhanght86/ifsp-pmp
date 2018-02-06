/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.mchtMng.process.bean 
 * FileName: MchtContractAcctRate.java
 * Author:   zrx
 * Date:     2016年7月28日 上午10:11:48
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月28日上午10:11:48                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mchtMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司 Author: chengGX Date:
 * 2016年9月21日下午2:15:13 History: //修改记录 Version: 0.2 Desc:
 */
@Table("PBS_MCHT_TXN_LIMIT_INFO")
public class MchtTxnLimitInfo {

	@Id
	private String mchtId; // 商户号

	private String limitOne; // 单笔支付限额

	private String limitDay; // 单日限额

	private String dataState; // 数据有效状态

	// 创建柜员
	private String crtTlr;
	// 创建日期时间
	private String crtDateTime;
	// 最近更新柜员
	private String lastUpdTlr;
	// 最近更新日期时间
	private String lastUpdDateTime;

	public String getMchtId() {
		return mchtId;
	}

	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}

	public String getLimitOne() {
		return limitOne;
	}

	public void setLimitOne(String limitOne) {
		this.limitOne = limitOne;
	}

	public String getLimitDay() {
		return limitDay;
	}

	public void setLimitDay(String limitDay) {
		this.limitDay = limitDay;
	}

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
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
