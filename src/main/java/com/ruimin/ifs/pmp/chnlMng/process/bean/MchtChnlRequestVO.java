package com.ruimin.ifs.pmp.chnlMng.process.bean;

/**
 * 
 * 商户通道请求-实体类
 * 
 * @author zhangjc
 *
 */
public class MchtChnlRequestVO {
	private String encoding;
	private String chlNo;
	private String applyType;
	private String chlMerName;
	private String chlMerId;
	private String applyMerName;
	private String applyMerShortName;
	private String custservPhone;
	private String contact;
	private String contactPhone;
	private String contactEmail;
	private String wxRemark;
	private String addressCode;
	/** 补充字段 */
	private String pagyNo;// 通道编号
	private String userCode;// 类目使用方
	private String levelOneCode;// 一级行业编码
	private String levelTwoCode;// 二级行业编码
	private String levelThreeCode;// 三级行业编码
	private String mcc;// MCC码
	private String mainMerId;// 通道主商户编号
	
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getChlNo() {
		return chlNo;
	}

	public void setChlNo(String chlNo) {
		this.chlNo = chlNo;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getChlMerName() {
		return chlMerName;
	}

	public void setChlMerName(String chlMerName) {
		this.chlMerName = chlMerName;
	}

	public String getChlMerId() {
		return chlMerId;
	}

	public void setChlMerId(String chlMerId) {
		this.chlMerId = chlMerId;
	}

	public String getApplyMerName() {
		return applyMerName;
	}

	public void setApplyMerName(String applyMerName) {
		this.applyMerName = applyMerName;
	}

	public String getApplyMerShortName() {
		return applyMerShortName;
	}

	public void setApplyMerShortName(String applyMerShortName) {
		this.applyMerShortName = applyMerShortName;
	}

	public String getCustservPhone() {
		return custservPhone;
	}

	public void setCustservPhone(String custservPhone) {
		this.custservPhone = custservPhone;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getWxRemark() {
		return wxRemark;
	}

	public void setWxRemark(String wxRemark) {
		this.wxRemark = wxRemark;
	}

	public String getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}

	public String getPagyNo() {
		return pagyNo;
	}

	public void setPagyNo(String pagyNo) {
		this.pagyNo = pagyNo;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getLevelOneCode() {
		return levelOneCode;
	}

	public void setLevelOneCode(String levelOneCode) {
		this.levelOneCode = levelOneCode;
	}

	public String getLevelTwoCode() {
		return levelTwoCode;
	}

	public void setLevelTwoCode(String levelTwoCode) {
		this.levelTwoCode = levelTwoCode;
	}

	public String getLevelThreeCode() {
		return levelThreeCode;
	}

	public void setLevelThreeCode(String levelThreeCode) {
		this.levelThreeCode = levelThreeCode;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMainMerId() {
		return mainMerId;
	}

	public void setMainMerId(String mainMerId) {
		this.mainMerId = mainMerId;
	}
}
