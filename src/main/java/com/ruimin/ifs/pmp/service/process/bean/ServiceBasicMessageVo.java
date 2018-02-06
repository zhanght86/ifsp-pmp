package com.ruimin.ifs.pmp.service.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;
//服务机构信息表
@Table("SERVICE_BASIC_MESSAGE")
public class ServiceBasicMessageVo {
	@Id
	private String serviceCode;
	private String serviceName;
	private String splitType;
	private String splitValue;
	private String contactName;
	private String contactTel;
	private String contactAdd;
	private String zipCode;
	private String crtTlr;
	private String crtDateTime;
	private String lastUpdTlr;
	private String lastUpdDateTime;
	private String stat;
	
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getSplitType() {
		return splitType;
	}
	public void setSplitType(String splitType) {
		this.splitType = splitType;
	}
	public String getSplitValue() {
		return splitValue;
	}
	public void setSplitValue(String splitValue) {
		this.splitValue = splitValue;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getContactAdd() {
		return contactAdd;
	}
	public void setContactAdd(String contactAdd) {
		this.contactAdd = contactAdd;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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
