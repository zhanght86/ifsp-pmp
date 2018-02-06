/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.sysConf.process.bean 
 * FileName: passBankVo.java
 * Author:   Administrator
 * Date:     2016年7月14日 下午6:37:18
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   Administrator           2016年7月14日下午6:37:18                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.sysConf.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月14日 <br>
 * 作者：Administrator <br>
 * 说明：<br>
 */
@Table("CHL_PASS_BANK_INFO")
public class PassBankVO {
	@Id
	private String passNo; // 通道编号
	@Id
	private String bankNo; // 银行编号
	private String bankName; // 银行名称
	private String dataState; // 数据有效状态

	private String crtTlr; // 创建柜员
	private String crtDateTime; // 创建日期时间
	private String lastUpdTlr; // 最近更新柜员
	private String lastUpdDateTime; // 最近更新日期时间

	/**
	 * @return the passNo
	 */
	public String getPassNo() {
		return passNo;
	}

	/**
	 * @param passNo
	 *            the passNo to set
	 */
	public void setPassNo(String passNo) {
		this.passNo = passNo;
	}

	/**
	 * @return the bankNo
	 */
	public String getBankNo() {
		return bankNo;
	}

	/**
	 * @param bankNo
	 *            the bankNo to set
	 */
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the dataState
	 */
	public String getDataState() {
		return dataState;
	}

	/**
	 * @param dataState
	 *            the dataState to set
	 */
	public void setDataState(String dataState) {
		this.dataState = dataState;
	}

	/**
	 * @return the crtTlr
	 */
	public String getCrtTlr() {
		return crtTlr;
	}

	/**
	 * @param crtTlr
	 *            the crtTlr to set
	 */
	public void setCrtTlr(String crtTlr) {
		this.crtTlr = crtTlr;
	}

	/**
	 * @return the crtDateTime
	 */
	public String getCrtDateTime() {
		return crtDateTime;
	}

	/**
	 * @param crtDateTime
	 *            the crtDateTime to set
	 */
	public void setCrtDateTime(String crtDateTime) {
		this.crtDateTime = crtDateTime;
	}

	/**
	 * @return the lastUpdTlr
	 */
	public String getLastUpdTlr() {
		return lastUpdTlr;
	}

	/**
	 * @param lastUpdTlr
	 *            the lastUpdTlr to set
	 */
	public void setLastUpdTlr(String lastUpdTlr) {
		this.lastUpdTlr = lastUpdTlr;
	}

	/**
	 * @return the lastUpdDateTime
	 */
	public String getLastUpdDateTime() {
		return lastUpdDateTime;
	}

	/**
	 * @param lastUpdDateTime
	 *            the lastUpdDateTime to set
	 */
	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}

	/**
	 * @param passNo
	 * @param bankNo
	 * @param bankName
	 * @param dataState
	 * @param crtTlr
	 * @param crtDateTime
	 * @param lastUpdTlr
	 * @param lastUpdDateTime
	 */
	public PassBankVO(String passNo, String bankNo, String bankName, String dataState, String crtTlr,
			String crtDateTime, String lastUpdTlr, String lastUpdDateTime) {
		super();
		this.passNo = passNo;
		this.bankNo = bankNo;
		this.bankName = bankName;
		this.dataState = dataState;
		this.crtTlr = crtTlr;
		this.crtDateTime = crtDateTime;
		this.lastUpdTlr = lastUpdTlr;
		this.lastUpdDateTime = lastUpdDateTime;
	}

	public PassBankVO() {

	}

}
