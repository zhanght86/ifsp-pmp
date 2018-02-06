package com.ruimin.ifs.pmp.mchtMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("PBS_MCHT_PIC_INFO")
public class PbsMchtPicInfoReal {
	@Id
	/** 变量 mchtId . */
	private String mchtId;
	@Id
	/** 变量 mchtPicType . */
	private String mchtPicType;
	@Id
	/** 变量 picSeqNo . */
	private String picSeqNo;
	/** 变量 mchtPicId . */
	private String mchtPicId;
	/** 变量 mchtPicPath . */
	private String mchtPicPath;
	/** 变量 crtTlr . */
	private String crtTlr;
	/** 变量 crtDateTime . */
	private String crtDateTime;
	/** 变量 lastUpdTlr . */
	private String lastUpdTlr;
	/** 变量 lastUpdAteTime . */
	private String lastUpdAteTime;

	public String getMchtId() {
		return mchtId;
	}

	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}

	public String getMchtPicType() {
		return mchtPicType;
	}

	public void setMchtPicType(String mchtPicType) {
		this.mchtPicType = mchtPicType;
	}

	public String getPicSeqNo() {
		return picSeqNo;
	}

	public void setPicSeqNo(String picSeqNo) {
		this.picSeqNo = picSeqNo;
	}

	public String getMchtPicId() {
		return mchtPicId;
	}

	public void setMchtPicId(String mchtPicId) {
		this.mchtPicId = mchtPicId;
	}

	public String getMchtPicPath() {
		return mchtPicPath;
	}

	public void setMchtPicPath(String mchtPicPath) {
		this.mchtPicPath = mchtPicPath;
	}

	public String getCrtTlr() {
		return crtTlr;
	}

	public void setCrtTlr(String crtTlr) {
		this.crtTlr = crtTlr;
	}

	public String getCrtDateTime() {
		return crtDateTime;
	}

	public void setCrtDateTime(String crtDateTime) {
		this.crtDateTime = crtDateTime;
	}

	public String getLastUpdTlr() {
		return lastUpdTlr;
	}

	public void setLastUpdTlr(String lastUpdTlr) {
		this.lastUpdTlr = lastUpdTlr;
	}

	public String getLastUpdAteTime() {
		return lastUpdAteTime;
	}

	public void setLastUpdAteTime(String lastUpdAteTime) {
		this.lastUpdAteTime = lastUpdAteTime;
	}

}
