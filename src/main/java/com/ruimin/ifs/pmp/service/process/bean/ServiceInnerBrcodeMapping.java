package com.ruimin.ifs.pmp.service.process.bean;
/**
 * 
 * 内部机构服务机构关联表的映射实体类
 * 只用于映射查询，不作为插入数据使用
 *
 */
public class ServiceInnerBrcodeMapping {
	private String code;
	private String mchtId;
	private String mchtName;
	private String brcode;
	private String brname;
	private String inFlag;
	private boolean select;
	private String brno;
	
	public String getBrno() {
		return brno;
	}
	public void setBrno(String brno) {
		this.brno = brno;
	}
	public String getMchtId() {
		return mchtId;
	}
	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}
	public String getMchtName() {
		return mchtName;
	}
	public void setMchtName(String mchtName) {
		this.mchtName = mchtName;
	}
	
	public String getBrcode() {
		return brcode;
	}
	public void setBrcode(String brcode) {
		this.brcode = brcode;
	}
	public String getBrname() {
		return brname;
	}
	public void setBrname(String brname) {
		this.brname = brname;
	}
	
	
	public String getInFlag() {
		return inFlag;
	}
	public void setInFlag(String inFlag) {
		this.inFlag = inFlag;
	}
	public boolean getSelect() {
		return select;
	}
	public void setSelect(boolean select) {
		this.select = select;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
