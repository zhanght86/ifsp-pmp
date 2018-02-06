package com.ruimin.ifs.pmp.mchtMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("PBS_MCHT_CONTRACT_PIC_INFO")
public class PbsMchtContractPicInfo {
	@Id
	private String conId;
	@Id
	private String picSerNum;
	private String picId;
	private String picPath;
	/* 创建柜员 */
	private String crtTlr;
	/* 创建日期 */
	private String crtDateTime;
	/* 最后更新柜员 */
	private String lastUpdTlr;
	/* 最后更新时间 */
	private String lastUpdDateTime;

	public String getConId() {
		return conId;
	}

	public void setConId(String conId) {
		this.conId = conId;
	}

	public String getPicSerNum() {
		return picSerNum;
	}

	public void setPicSerNum(String picSerNum) {
		this.picSerNum = picSerNum;
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
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

	public String getLastUpdDateTime() {
		return lastUpdDateTime;
	}

	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}

	public PbsMchtContractPicInfo(String conId, String picSerNum, String picId, String picPath, String crtTlr,
			String crtDateTime, String lastUpdTlr, String lastUpdDateTime) {
		super();
		this.conId = conId;
		this.picSerNum = picSerNum;
		this.picId = picId;
		this.picPath = picPath;
		this.crtTlr = crtTlr;
		this.crtDateTime = crtDateTime;
		this.lastUpdTlr = lastUpdTlr;
		this.lastUpdDateTime = lastUpdDateTime;
	}

	public PbsMchtContractPicInfo() {

	}

}
