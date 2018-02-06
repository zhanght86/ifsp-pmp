package com.ruimin.ifs.po;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("ifs_login_log")
public class TblLoginLog {
	@Id
	private String logId;
	private String tlrNo;
	private String brNo;
	private String loginSucTm;
	private String loginOutTm;
	private String loginFailTm;
	private String loginAddr;
	private String loginRemark;
	private String sessionId;
	private String crtTm;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getTlrNo() {
		return tlrNo;
	}

	public void setTlrNo(String tlrNo) {
		this.tlrNo = tlrNo;
	}

	public String getBrNo() {
		return brNo;
	}

	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}

	public String getLoginSucTm() {
		return loginSucTm;
	}

	public void setLoginSucTm(String loginSucTm) {
		this.loginSucTm = loginSucTm;
	}

	public String getLoginOutTm() {
		return loginOutTm;
	}

	public void setLoginOutTm(String loginOutTm) {
		this.loginOutTm = loginOutTm;
	}

	public String getLoginFailTm() {
		return loginFailTm;
	}

	public void setLoginFailTm(String loginFailTm) {
		this.loginFailTm = loginFailTm;
	}

	public String getLoginAddr() {
		return loginAddr;
	}

	public void setLoginAddr(String loginAddr) {
		this.loginAddr = loginAddr;
	}

	public String getLoginRemark() {
		return loginRemark;
	}

	public void setLoginRemark(String loginRemark) {
		this.loginRemark = loginRemark;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getCrtTm() {
		return crtTm;
	}

	public void setCrtTm(String crtTm) {
		this.crtTm = crtTm;
	}

}