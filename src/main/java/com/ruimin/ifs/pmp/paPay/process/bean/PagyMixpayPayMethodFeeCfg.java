package com.ruimin.ifs.pmp.paPay.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("PAGY_MIXPAY_PAY_METHOD_FEE_CFG")
public class PagyMixpayPayMethodFeeCfg {

	@Id
	private String pmtId;
	@Id
	private String pmtTag;
	@Id
	private String pmfId;
	
	private String pmtOptHide;
//	是否开启智能扣率(1是;0否)
	private String pmtDiscountFee;
	private String pmfName;
	private String pmfMinFee;
	private String pmfMaxFee;
	private String pmfLimit;
	private String opmFeeType;
	private String opmFee;
	private String userDefined1;
	private String userDefined2;
	private String userDefined3;
	private String userDefined4;
	private String userDefined5;
	private String usedTag;
	private String backup1;
	private String backup2;
	
	
	public String getUserDefined1() {
		return userDefined1;
	}
	public void setUserDefined1(String userDefined1) {
		this.userDefined1 = userDefined1;
	}
	public String getUserDefined2() {
		return userDefined2;
	}
	public void setUserDefined2(String userDefined2) {
		this.userDefined2 = userDefined2;
	}
	public String getUserDefined3() {
		return userDefined3;
	}
	public void setUserDefined3(String userDefined3) {
		this.userDefined3 = userDefined3;
	}
	public String getUserDefined4() {
		return userDefined4;
	}
	public void setUserDefined4(String userDefined4) {
		this.userDefined4 = userDefined4;
	}
	public String getUserDefined5() {
		return userDefined5;
	}
	public void setUserDefined5(String userDefined5) {
		this.userDefined5 = userDefined5;
	}
	public String getPmtId() {
		return pmtId;
	}
	public void setPmtId(String pmtId) {
		this.pmtId = pmtId;
	}
	public String getPmtTag() {
		return pmtTag;
	}
	public void setPmtTag(String pmtTag) {
		this.pmtTag = pmtTag;
	}
	public String getPmfId() {
		return pmfId;
	}
	public void setPmfId(String pmfId) {
		this.pmfId = pmfId;
	}
	public String getPmtOptHide() {
		return pmtOptHide;
	}
	public void setPmtOptHide(String pmtOptHide) {
		this.pmtOptHide = pmtOptHide;
	}
	public String getPmtDiscountFee() {
		return pmtDiscountFee;
	}
	public void setPmtDiscountFee(String pmtDiscountFee) {
		this.pmtDiscountFee = pmtDiscountFee;
	}
	public String getPmfName() {
		return pmfName;
	}
	public void setPmfName(String pmfName) {
		this.pmfName = pmfName;
	}
	public String getPmfMinFee() {
		return pmfMinFee;
	}
	public void setPmfMinFee(String pmfMinFee) {
		this.pmfMinFee = pmfMinFee;
	}
	public String getPmfMaxFee() {
		return pmfMaxFee;
	}
	public void setPmfMaxFee(String pmfMaxFee) {
		this.pmfMaxFee = pmfMaxFee;
	}
	public String getPmfLimit() {
		return pmfLimit;
	}
	public void setPmfLimit(String pmfLimit) {
		this.pmfLimit = pmfLimit;
	}
	public String getOpmFeeType() {
		return opmFeeType;
	}
	public void setOpmFeeType(String opmFeeType) {
		this.opmFeeType = opmFeeType;
	}
	public String getOpmFee() {
		return opmFee;
	}
	public void setOpmFee(String opmFee) {
		this.opmFee = opmFee;
	}
	public String getUsedTag() {
		return usedTag;
	}
	public void setUsedTag(String usedTag) {
		this.usedTag = usedTag;
	}
	public String getBackup1() {
		return backup1;
	}
	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}
	public String getBackup2() {
		return backup2;
	}
	public void setBackup2(String backup2) {
		this.backup2 = backup2;
	}
	
}
