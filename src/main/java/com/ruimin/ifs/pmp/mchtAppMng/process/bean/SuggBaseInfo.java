package com.ruimin.ifs.pmp.mchtAppMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("MSS_SUGG_BASE_INFO")
public class SuggBaseInfo {
    @Id
	private String suggId;
	private String crtDate;
	private String suggStat;
	private String suggInfo;
	private String rate;
	private String userEmail;
	
	public String getSuggId() {
		return suggId;
	}
	public void setSuggId(String suggId) {
		this.suggId = suggId;
	}
	public String getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}
	public String getSuggStat() {
		return suggStat;
	}
	public void setSuggStat(String suggStat) {
		this.suggStat = suggStat;
	}
	public String getSuggInfo() {
		return suggInfo;
	}
	public void setSuggInfo(String suggInfo) {
		this.suggInfo = suggInfo;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
	
	
	
}
