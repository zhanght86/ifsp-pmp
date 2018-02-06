package com.ruimin.ifs.pmp.mchtAppMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;
@Table("MSS_VER_MNG")
public class VerBaseInfo {
	@Id
	private String deviceType;
	@Id
	private String appVer;
	private String updFlag;
	private String updMsg;
	private String lastUpdDateTime;
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getAppVer() {
		return appVer;
	}
	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}
	public String getUpdFlag() {
		return updFlag;
	}
	public void setUpdFlag(String updFlag) {
		this.updFlag = updFlag;
	}
	public String getUpdMsg() {
		return updMsg;
	}
	public void setUpdMsg(String updMsg) {
		this.updMsg = updMsg;
	}
	public String getLastUpdDateTime() {
		return lastUpdDateTime;
	}
	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}
	
}
