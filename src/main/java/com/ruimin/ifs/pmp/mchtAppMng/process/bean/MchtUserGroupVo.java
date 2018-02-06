package com.ruimin.ifs.pmp.mchtAppMng.process.bean;

public class MchtUserGroupVo extends UserGroupDtlBaseInfo{
	private String mchtId;
	private String deviceType;
	private String userName;
	private String channelId;
	public String getMchtId() {
		return mchtId;
	}
	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getChannelId() {
		int length = channelId.length();
		if(length > 5){
			channelId = channelId.substring(0, 5);
			for (int i = 0; i < length - 5; i++) {
				channelId += "*";
			}
		}
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
	
}
