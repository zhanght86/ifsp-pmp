package com.ruimin.ifs.mng.process.bean;

/**
 * 用户权限列表
 * 
 * @author hyurain_yang
 * 
 */

public class UserAuthority {

	private String tlrno;
	private String tlrName;
	private String funcname;

	public String getTlrName() {
		return tlrName;
	}

	public void setTlrName(String tlrName) {
		this.tlrName = tlrName;
	}

	public String getTlrno() {
		return tlrno;
	}

	public void setTlrno(String tlrno) {
		this.tlrno = tlrno;
	}

	public String getFuncname() {
		return funcname;
	}

	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}

}
