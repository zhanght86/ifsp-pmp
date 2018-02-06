package com.ruimin.ifs.pmp.service.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

//商户巡检信息map映射实体类
@Table("SERVICE_MCHT_POLLING")
public class ServiceMchtPollingVo {
	@Id
	private String serviceId;//巡检信息序号
	private String mchtId;//商户号
	private String pollingDate;//巡检日期
	private String serviceCode;//巡检服务机构号
	private String crtTlr;//创建人
	private String crtDateTime;//创建时间
	private String termId;//终端编号
	private String manageName;//经营名称
	private String manageAddr;//经营地址
	private String equipmentId;//设备号
	private String configurationCode;//配置码
	private String keyboardCode;//键盘号
	private String versionsCode;//版本号
	private String pollingResult;//巡检结果
	private String remark;//备注
	private String mchtAmrNo;//客户经理工号
	private String mchtPersonName;//联系人
	private String mchtPhone;//联系电话
	private String mchtName;//商户名称
	/**表格之外的字段**/
	private String serviceName;//服务机构名称
	private String brname;//所属机构
	
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getMchtId() {
		return mchtId;
	}
	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}
	public String getPollingDate() {
		return pollingDate;
	}
	public void setPollingDate(String pollingDate) {
		this.pollingDate = pollingDate;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getBrname() {
		return brname;
	}
	public void setBrname(String brname) {
		this.brname = brname;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
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
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getManageName() {
		return manageName;
	}
	public void setManageName(String manageName) {
		this.manageName = manageName;
	}
	public String getManageAddr() {
		return manageAddr;
	}
	public void setManageAddr(String manageAddr) {
		this.manageAddr = manageAddr;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getConfigurationCode() {
		return configurationCode;
	}
	public void setConfigurationCode(String configurationCode) {
		this.configurationCode = configurationCode;
	}
	public String getKeyboardCode() {
		return keyboardCode;
	}
	public void setKeyboardCode(String keyboardCode) {
		this.keyboardCode = keyboardCode;
	}
	public String getVersionsCode() {
		return versionsCode;
	}
	public void setVersionsCode(String versionsCode) {
		this.versionsCode = versionsCode;
	}
	public String getPollingResult() {
		return pollingResult;
	}
	public void setPollingResult(String pollingResult) {
		this.pollingResult = pollingResult;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMchtAmrNo() {
		return mchtAmrNo;
	}
	public void setMchtAmrNo(String mchtAmrNo) {
		this.mchtAmrNo = mchtAmrNo;
	}
	public String getMchtPersonName() {
		return mchtPersonName;
	}
	public void setMchtPersonName(String mchtPersonName) {
		this.mchtPersonName = mchtPersonName;
	}
	public String getMchtPhone() {
		return mchtPhone;
	}
	public void setMchtPhone(String mchtPhone) {
		this.mchtPhone = mchtPhone;
	}
	public String getMchtName() {
		return mchtName;
	}
	public void setMchtName(String mchtName) {
		this.mchtName = mchtName;
	}

	
	
	
}
