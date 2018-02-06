package com.ruimin.ifs.pmp.mchtMng.process.bean;

/**
 * 
 * 商户信息管理
 * 
 * @author wuhd
 *
 */
public class MchtMngVO2 extends PbsMchtBaseInfo {
	private String mchtArea;// 所属地区
	private String mchtMng;// 上级商户
	private String mchtOrg;// 所属机构
	private String brcode;// 机构代码
	private String brname;// 机构名称
	private String picCrtTlr;// 图片-创建操作员
	private String picCrtDateTime;// 图片-创建日期时间
	private String picId1;// 图片编号[1]
	private String picId2;// 图片编号[2]
	private String picId3;// 图片编号[3]
	private String picId4;// 图片编号[4]
	private String picId5;// 图片编号[5]
	private String mchtNo;// 图片编号[5]

	public String getMchtNo() {
		return mchtNo;
	}

	public void setMchtNo(String mchtNo) {
		this.mchtNo = mchtNo;
	}

	public String getPicId1() {
		return picId1;
	}

	public void setPicId1(String picId1) {
		this.picId1 = picId1;
	}

	public String getPicId2() {
		return picId2;
	}

	public void setPicId2(String picId2) {
		this.picId2 = picId2;
	}

	public String getPicId3() {
		return picId3;
	}

	public void setPicId3(String picId3) {
		this.picId3 = picId3;
	}

	public String getPicId4() {
		return picId4;
	}

	public void setPicId4(String picId4) {
		this.picId4 = picId4;
	}

	public String getPicId5() {
		return picId5;
	}

	public void setPicId5(String picId5) {
		this.picId5 = picId5;
	}

	public String getPicCrtTlr() {
		return picCrtTlr;
	}

	public void setPicCrtTlr(String picCrtTlr) {
		this.picCrtTlr = picCrtTlr;
	}

	public String getPicCrtDateTime() {
		return picCrtDateTime;
	}

	public void setPicCrtDateTime(String picCrtDateTime) {
		this.picCrtDateTime = picCrtDateTime;
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

	public String getMchtArea() {
		return mchtArea;
	}

	public void setMchtArea(String mchtArea) {
		this.mchtArea = mchtArea;
	}

	public String getMchtMng() {
		return mchtMng;
	}

	public void setMchtMng(String mchtMng) {
		this.mchtMng = mchtMng;
	}

	public String getMchtOrg() {
		return mchtOrg;
	}

	public void setMchtOrg(String mchtOrg) {
		this.mchtOrg = mchtOrg;
	}

}
