package com.ruimin.ifs.pmp.brokerageAppMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("BBS_APP_INFO")
public class BbsAppInfoVO {
	@Id
	private String appid;
	private String appName;
	private String traderName;
	private String freeAmt;
	private String orgId;
	private String commAddr;
	private String md5Key;
	private String stat;
	private String crtTlr;
	private String crtDateTime;
	private String lastUpdTlr;
	private String lastUpdDateTime;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getTraderName() {
		return traderName;
	}
	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}
	public String getFreeAmt() {
		return freeAmt;
	}
	public void setFreeAmt(String freeAmt) {
		this.freeAmt = freeAmt;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getCommAddr() {
		return commAddr;
	}
	public void setCommAddr(String commAddr) {
		this.commAddr = commAddr;
	}
	public String getMd5Key() {
		return md5Key;
	}
	public void setMd5Key(String md5Key) {
		this.md5Key = md5Key;
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
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	
}
