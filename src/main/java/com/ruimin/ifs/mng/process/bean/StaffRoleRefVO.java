package com.ruimin.ifs.mng.process.bean;

public class StaffRoleRefVO {
	private String tlrno;
	private String tlrName;
	private String flag;
	private String lastaccesstm;
	
	//2017-06-01 add
	private String brname;

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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getLastaccesstm() {
		return lastaccesstm;
	}

	public void setLastaccesstm(String lastaccesstm) {
		this.lastaccesstm = lastaccesstm;
	}

	public String getBrname() {
		return brname;
	}

	public void setBrname(String brname) {
		this.brname = brname;
	}

	

}
