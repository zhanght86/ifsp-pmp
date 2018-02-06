/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.sysConf.process.bean 
 * FileName: PMP_BANK_LIMIT_INFO.java
 * Author:   ZLJ
 * Date:     2016年7月11日 上午11:19:33
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   ZLJ           2016年7月11日上午11:19:33                     1.0                  
 *===============================================================================================
 */

package com.ruimin.ifs.pmp.sysConf.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

import java.io.Serializable;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月11日 <br>
 * 作者：ZLJ <br>
 * 说明：产品银行限额信息<br>
 */
@Table("PBS_BANK_LIMIT_INFO")
public class PmpBankLimitInfoVO implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 7342956830957576035L;

	@Id
	private String limitNo;// 限额编号
	private String bankNo;// 银行编号
	private String custType;// 客户类型
	private String limitOne;// 单笔交易限额
	private String limitDay;// 当天交易限额
	private String limitMon;// 当月交易限额
	private String dataState;// 数据有效状态
	private String crtTlr;// 创建柜员
	private String crtDateTime;// 创建日期时间
	private String lastUpdTlr;// 最近更新柜员
	private String lastUpdDateTime;// 最近更新日期时间

	public String getLimitNo() {
		return limitNo;
	}

	public void setLimitNo(String limitNo) {
		this.limitNo = limitNo;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
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

	public String getLimitMon() {
		return limitMon;
	}

	public void setLimitMon(String limitMon) {
		this.limitMon = limitMon;
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
