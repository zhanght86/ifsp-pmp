package com.ruimin.ifs.server.common.bean;

public class BaseRequest {
	public String reqSsn;
	public String reqDate;
	public String reqTime;
	public String channel;
	public String busCode;
	public String oprId;

	public String getReqSsn() {
		return reqSsn;
	}

	public void setReqSsn(String reqSsn) {
		this.reqSsn = reqSsn;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	public String getReqTime() {
		return reqTime;
	}

	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getBusCode() {
		return busCode;
	}

	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}

	public String getOprId() {
		return oprId;
	}

	public void setOprId(String oprId) {
		this.oprId = oprId;
	}

}
