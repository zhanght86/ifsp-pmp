package com.ruimin.ifs.pmp.mchtAppMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("MSS_EVENT_BASE_INFO")
public class EventBaseInfo {
	@Id
	private String eventId;
	private String mchtId;
	private String mchtSimpleName;
	private String userId;
	private String phoneNo;
	private String crtDate;
	private String eventTitle;
	private String eventInfo;
	private String userName;
	private String eventStat;
	private String eventFdback;
	private String updDate;
	private String updTlr;
	
	
	public String getMchtId() {
		return mchtId;
	}
	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}
	public String getMchtSimpleName() {
		return mchtSimpleName;
	}
	public void setMchtSimpleName(String mchtSimpleName) {
		this.mchtSimpleName = mchtSimpleName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventInfo() {
		return eventInfo;
	}
	public void setEventInfo(String eventInfo) {
		this.eventInfo = eventInfo;
	}
	public String getEventStat() {
		return eventStat;
	}
	public void setEventStat(String eventStat) {
		this.eventStat = eventStat;
	}
	
	public String getEventFdback() {
        return eventFdback;
    }
    public void setEventFdback(String eventFdback) {
        this.eventFdback = eventFdback;
    }
    public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}
	public String getUpdTlr() {
		return updTlr;
	}
	public void setUpdTlr(String updTlr) {
		this.updTlr = updTlr;
	}
	
	
	
	
}
