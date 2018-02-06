package com.ruimin.ifs.pmp.mchtAppMng.process.bean;

public class MsgVo extends MsgBaseInfo{
	private String messageType;
	private String deviceType;
	private String msgModelType;
	private String msgExpires;
	private String sendTm;
	private String userId;
	private String msgStatus;
	private String sendTime;
	private String successCount;
	
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getMsgModelType() {
		return msgModelType;
	}
	public void setMsgModelType(String msgModelType) {
		this.msgModelType = msgModelType;
	}
	public String getMsgExpires() {
		return msgExpires;
	}
	public void setMsgExpires(String msgExpires) {
		this.msgExpires = msgExpires;
	}
	public String getSendTm() {
		return sendTm;
	}
	public void setSendTm(String sendTm) {
		this.sendTm = sendTm;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMsgStatus() {
		return msgStatus;
	}
	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(String successCount) {
		this.successCount = successCount;
	}
	@Override
	public String toString() {
		return "MsgVo [messageType=" + messageType + ", deviceType=" + deviceType + ", msgModelType=" + msgModelType
				+ ", msgExpires=" + msgExpires + ", sendTm=" + sendTm + ", userId=" + userId + "]";
	}
	
	
	
}
