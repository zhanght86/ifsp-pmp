package com.ruimin.ifs.po;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("ifs_role")
public class TblRole {
	@Id
	private Integer roleId;// 角色编号
	private String roleName;// 角色名称
	private String status;// 状态 00-启用 99-停用
	private String effectDate;// 有效日期开始
	private String expireDate;// 有效日期结束
	private String brclass;// 所属行级别
	private String miscflgs;// 扩展标志级别
	private String misc;// 保留域
	private String roleType;// 角色类型
	private String isLock;// 锁定状态 0-未锁定 1-已锁定
	private String crtDt;// 创建日期
	private String lastUpdTms;// 最后更新时间
	private String lastUpdOper;// 最后更新人
	private String st;// 审核状态

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

}
