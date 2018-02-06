package com.ruimin.ifs.mng.process.bean;

public class TblStaffRoleRelVO {
//	private boolean select = false;
	private boolean select;
	private Integer roleId;
	private String roleName;
	private String status;
	private String effectDate;
	private String expireDate;
	private String brclass;
	private String miscflgs;
	private String misc;
	private String roleType;
	private String isLock;
	private String crtDt;
	private String lastUpdTms;
	private String lastUpdOper;
	private String st;
	private String opr;

//	public boolean isSelect() {
//		return select;
//	}
//
	
	public boolean getSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}
	
	

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getBrclass() {
		return brclass;
	}

	public void setBrclass(String brclass) {
		this.brclass = brclass;
	}

	public String getMiscflgs() {
		return miscflgs;
	}

	public void setMiscflgs(String miscflgs) {
		this.miscflgs = miscflgs;
	}

	public String getMisc() {
		return misc;
	}

	public void setMisc(String misc) {
		this.misc = misc;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
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

	public String getOpr() {
		return opr;
	}

	public void setOpr(String opr) {
		this.opr = opr;
	}

}
