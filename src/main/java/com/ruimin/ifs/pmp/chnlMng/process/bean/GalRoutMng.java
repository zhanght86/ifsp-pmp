/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.chnlMng.process.bean 
 * FileName: GalRoutMng.java
 * Author:   chenqilei
 * Date:     2016年8月1日 下午8:29:37
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年8月1日下午8:29:37                     1.0                  
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
 * 日期：2016年8月1日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
@Table("PAGY_ROUTE_INFO")
public class GalRoutMng {
	@Id
	private String routeId; // 路由编号
	private String chnlId; // 策略编号
	private String acctTypeNo; // 账户类型编号
	private String payTxnTypeId; // 交易类型编号
	private String ttsCode; // 策号略编
	private String openDate;
	private String routeStat; // 路由状态
	private String crtTlr; // 创建柜员
	private String crtDateTime; // 创建日期时间
	private String lastUpdTlr; // 最近更新柜员
	private String lastUpdDateTime; // 最近更新日期时间

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getChnlId() {
		return chnlId;
	}

	public void setChnlId(String chnlId) {
		this.chnlId = chnlId;
	}

	public String getAcctTypeNo() {
		return acctTypeNo;
	}

	public void setAcctTypeNo(String acctTypeNo) {
		this.acctTypeNo = acctTypeNo;
	}

	public String getPayTxnTypeId() {
		return payTxnTypeId;
	}

	public void setPayTxnTypeId(String payTxnTypeId) {
		this.payTxnTypeId = payTxnTypeId;
	}

	public String getTtsCode() {
		return ttsCode;
	}

	public void setTtsCode(String ttsCode) {
		this.ttsCode = ttsCode;
	}

	public String getRouteStat() {
		return routeStat;
	}

	public void setRouteStat(String routeStat) {
		this.routeStat = routeStat;
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

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

}
