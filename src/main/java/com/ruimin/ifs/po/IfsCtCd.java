package com.ruimin.ifs.po;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("ifs_ct_cd")
public class IfsCtCd {
	@Id
	private String ctCode;
	private String ctFlg;
	private String ctName;
	private String upCtCode;
	private String tlCtCode;
	private String crtOprId;
	private String lstUpdOprId;
	private String recCrtTm;
	private String recUpdTm;

	public String getCtCode() {
		return ctCode;
	}

	public void setCtCode(String ctCode) {
		this.ctCode = ctCode;
	}

	public String getCtFlg() {
		return ctFlg;
	}

	public void setCtFlg(String ctFlg) {
		this.ctFlg = ctFlg;
	}

	public String getCtName() {
		return ctName;
	}

	public void setCtName(String ctName) {
		this.ctName = ctName;
	}

	public String getUpCtCode() {
		return upCtCode;
	}

	public void setUpCtCode(String upCtCode) {
		this.upCtCode = upCtCode;
	}

	public String getCrtOprId() {
		return crtOprId;
	}

	public void setCrtOprId(String crtOprId) {
		this.crtOprId = crtOprId;
	}

	public String getLstUpdOprId() {
		return lstUpdOprId;
	}

	public void setLstUpdOprId(String lstUpdOprId) {
		this.lstUpdOprId = lstUpdOprId;
	}

	public String getRecCrtTm() {
		return recCrtTm;
	}

	public void setRecCrtTm(String recCrtTm) {
		this.recCrtTm = recCrtTm;
	}

	public String getRecUpdTm() {
		return recUpdTm;
	}

	public void setRecUpdTm(String recUpdTm) {
		this.recUpdTm = recUpdTm;
	}

	public String getTlCtCode() {
		return tlCtCode;
	}

	public void setTlCtCode(String tlCtCode) {
		this.tlCtCode = tlCtCode;
	}

}
