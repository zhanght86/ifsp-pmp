/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.mng.process.bean 
 * FileName: TradeType.java
 * Author:   chenqilei
 * Date:     2016年7月8日 上午9:35:50
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月8日上午9:35:50                     1.0                  
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
 * 日期：2016年7月8日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
@Table("PBS_TXN_TYPE_INFO")
public class TradeType {
	@Id
	private String txnTypeCode; // 交易类型编号
	private String txnTypeName; // 交易类型名称
	private String txnTypeDesc; // 交易类型描述
	private String dataState; // 数据状态
	private String crtTlr; // 创建柜员
	private String crtDateTime; // 创建日期时间
	private String lastUpdTlr; // 最近更新柜员
	private String lastUpdDateTime; // 最近更新日期时间

	public String getTxnTypeCode() {
		return txnTypeCode;
	}

	public void setTxnTypeCode(String txnTypeCode) {
		this.txnTypeCode = txnTypeCode;
	}

	public String getTxnTypeName() {
		return txnTypeName;
	}

	public void setTxnTypeName(String txnTypeName) {
		this.txnTypeName = txnTypeName;
	}

	public String getTxnTypeDesc() {
		return txnTypeDesc;
	}

	public void setTxnTypeDesc(String txnTypeDesc) {
		this.txnTypeDesc = txnTypeDesc;
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
