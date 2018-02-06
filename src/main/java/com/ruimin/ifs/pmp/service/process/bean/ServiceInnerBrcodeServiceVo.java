package com.ruimin.ifs.pmp.service.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;
//服务机构内部机构关联表
@Table("SERVICE_INNER_BRCODE_SERVICE")
public class ServiceInnerBrcodeServiceVo {
	@Id
	private String serviceCode;
	@Id
	private String brcode;

	private String inFlag;

	private String crtTlr;

	private String crtDateTime;

	private String lastUpdTlr;

	private String lastUpdDateTime;

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getBrcode() {
		return brcode;
	}

	public void setBrcode(String brcode) {
		this.brcode = brcode;
	}

	public String getInFlag() {
		return inFlag;
	}

	public void setInFlag(String inFlag) {
		this.inFlag = inFlag;
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

	
	
}
