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
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月28日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
@Table("PBS_MCHT_CONTRACT_ACCT_RATE")
public class PbsMchtContractAcctRate {

	// 商户合同编号
	@Id
	private String conId;
	// 产品编号
	@Id
	private String prodId;
	// 账户类型编号
	@Id
	private String acctTypeCode;
	// 费率编号
	private String rateId;
	// 数据有效状态
	private String dataState;
	// 创建柜员
	private String crtTlr;
	// 创建日期时间
	private String crtDateTime;
	// 最近更新柜员
	private String lastUpdTlr;
	// 最近更新日期时间
	private String lastUpdDateTime;

	public String getConId() {
		return conId;
	}

	public void setConId(String conId) {
		this.conId = conId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getAcctTypeCode() {
		return acctTypeCode;
	}

	public void setAcctTypeCode(String acctTypeCode) {
		this.acctTypeCode = acctTypeCode;
	}

	public String getRateId() {
		return rateId;
	}

	public void setRateId(String rateId) {
		this.rateId = rateId;
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

	@Override
	public String toString() {
		return "MchtContractAcctRate [conId=" + conId + ", prodId=" + prodId + ", acctTypeCode=" + acctTypeCode
				+ ", rateId=" + rateId + ", dataState=" + dataState + ", crtTlr=" + crtTlr + ", crtDateTime="
				+ crtDateTime + ", lastUpdTlr=" + lastUpdTlr + ", lastUpdDateTime=" + lastUpdDateTime + "]";
	}

}
