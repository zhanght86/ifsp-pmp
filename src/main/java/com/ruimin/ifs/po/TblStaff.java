package com.ruimin.ifs.po;

import java.io.Serializable;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("ifs_staff")
public class TblStaff implements Serializable {
	@Id
	private String tlrno;
	private String tlrName;
	private String tlrType;
	private String email;
	private String brcode;
	private String password;
	private String status;
	private String roleid;
	private Integer msrno;
	private String flag;
	private String loginIp;
	private String sessionId;
	private String chekDpwdFlg;
	private String createDate;
	private String lastaccesstm;
	private String lastlogouttm;
	private String lastpwdchgtm;
	private String lastfailedtm;
	private Integer pswderrcnt;
	private Integer totpswderrcnt;
	private String pswderrdate;
	private String passwdenc;
	private Integer failmaxlogin;
	private Integer passwdchginterval;
	private Integer passwdwarninterval;
	private String isLock;
	private String lockReason;
	private String isLockModify;
	private String crtDt;
	private String lastUpdTms;
	private String lastUpdOper;
	private String st;
	private String workNo;
	private String phone;
	private String mobile;

	public TblStaff() {
		// TODO Auto-generated constructor stub
	}

	public String getTlrno() {
		return tlrno;
	}

	public void setTlrno(String tlrno) {
		this.tlrno = tlrno;
	}

	public String getTlrName() {
		return tlrName;
	}

	public void setTlrName(String tlrName) {
		this.tlrName = tlrName;
	}

	public String getTlrType() {
		return tlrType;
	}

	public void setTlrType(String tlrType) {
		this.tlrType = tlrType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBrcode() {
		return brcode;
	}

	public void setBrcode(String brcode) {
		this.brcode = brcode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public Integer getMsrno() {
		return msrno;
	}

	public void setMsrno(Integer msrno) {
		this.msrno = msrno;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getChekDpwdFlg() {
		return chekDpwdFlg;
	}

	public void setChekDpwdFlg(String chekDpwdFlg) {
		this.chekDpwdFlg = chekDpwdFlg;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastaccesstm() {
		return lastaccesstm;
	}

	public void setLastaccesstm(String lastaccesstm) {
		this.lastaccesstm = lastaccesstm;
	}

	public String getLastlogouttm() {
		return lastlogouttm;
	}

	public void setLastlogouttm(String lastlogouttm) {
		this.lastlogouttm = lastlogouttm;
	}

	public String getLastpwdchgtm() {
		return lastpwdchgtm;
	}

	public void setLastpwdchgtm(String lastpwdchgtm) {
		this.lastpwdchgtm = lastpwdchgtm;
	}

	public String getLastfailedtm() {
		return lastfailedtm;
	}

	public void setLastfailedtm(String lastfailedtm) {
		this.lastfailedtm = lastfailedtm;
	}

	public Integer getPswderrcnt() {
		return pswderrcnt;
	}

	public void setPswderrcnt(Integer pswderrcnt) {
		this.pswderrcnt = pswderrcnt;
	}

	public Integer getTotpswderrcnt() {
		return totpswderrcnt;
	}

	public void setTotpswderrcnt(Integer totpswderrcnt) {
		this.totpswderrcnt = totpswderrcnt;
	}

	public String getPswderrdate() {
		return pswderrdate;
	}

	public void setPswderrdate(String pswderrdate) {
		this.pswderrdate = pswderrdate;
	}

	public String getPasswdenc() {
		return passwdenc;
	}

	public void setPasswdenc(String passwdenc) {
		this.passwdenc = passwdenc;
	}

	public Integer getFailmaxlogin() {
		return failmaxlogin;
	}

	public void setFailmaxlogin(Integer failmaxlogin) {
		this.failmaxlogin = failmaxlogin;
	}

	public Integer getPasswdchginterval() {
		return passwdchginterval;
	}

	public void setPasswdchginterval(Integer passwdchginterval) {
		this.passwdchginterval = passwdchginterval;
	}

	public Integer getPasswdwarninterval() {
		return passwdwarninterval;
	}

	public void setPasswdwarninterval(Integer passwdwarninterval) {
		this.passwdwarninterval = passwdwarninterval;
	}

	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	public String getLockReason() {
		return lockReason;
	}

	public void setLockReason(String lockReason) {
		this.lockReason = lockReason;
	}

	public String getIsLockModify() {
		return isLockModify;
	}

	public void setIsLockModify(String isLockModify) {
		this.isLockModify = isLockModify;
	}

	public String getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}

	public String getLastUpdTms() {
		return lastUpdTms;
	}

	public void setLastUpdTms(String lastUpdTms) {
		this.lastUpdTms = lastUpdTms;
	}

	public String getLastUpdOper() {
		return lastUpdOper;
	}

	public void setLastUpdOper(String lastUpdOper) {
		this.lastUpdOper = lastUpdOper;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
