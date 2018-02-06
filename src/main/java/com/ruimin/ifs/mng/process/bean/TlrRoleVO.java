package com.ruimin.ifs.mng.process.bean;

public class TlrRoleVO {
	private String tlrno;
	private String tlrName;
	private String roleName;

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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public TlrRoleVO() {
	}

	public TlrRoleVO(String tlrno, String tlrName, String roleName) {
		super();
		this.tlrno = tlrno;
		this.tlrName = tlrName;
		this.roleName = roleName;
	}

}
