package com.ruimin.ifs.pmp.risk.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;
@Table("RISK_USER_BLACKLIST")
public class UserBlackListVo {
	@Id
	private String blacklistId;
	private String blacklistType;
	private String blacklistValue;
	private String blacklistStatus;
	private String startDate;
	private String endDate;
	private String crtTlr;
	private String crtDateTime;
	private String lastUpdTlr;
	private String lastUpdDateTime;
	public String getBlacklistType() {
		return blacklistType;
	}
	public void setBlacklistType(String blacklistType) {
		this.blacklistType = blacklistType;
	}
	public String getBlacklistValue() {
		return blacklistValue;
	}
	public void setBlacklistValue(String blacklistValue) {
		this.blacklistValue = blacklistValue;
	}
	public String getBlacklistStatus() {
		return blacklistStatus;
	}
	public void setBlacklistStatus(String blacklistStatus) {
		this.blacklistStatus = blacklistStatus;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	public String getBlacklistId() {
		return blacklistId;
	}
	public void setBlacklistId(String blacklistId) {
		this.blacklistId = blacklistId;
	}
	
	
}
