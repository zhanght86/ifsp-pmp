package com.ruimin.ifs.pmp.paPay.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("PAGY_MIXPAY_PAY_METHOD_INFO")
public class PagyMixpayPayMethodInfo {

	@Id
	private String pmtId;
	@Id
	private String pmtTag;
	private String pmtType;
	private String pmtName;
	private String pmtInternalName;
	private String pmtIcon;
	private String pmtOpt10;
	private String pmtOpt9;
	private String pmtOpt8;
	private String pmtOpt7;
	private String pmtOpt6;
	private String pmtOpt5;
	private String pmtOpt4;
	private String pmtOpt3;
	private String pmtOpt2;
	private String pmtOpt1;
	private String backup1;
	private String backup2;
	private String usedTag;
	
	
	public String getUsedTag() {
		return usedTag;
	}
	public void setUsedTag(String usedTag) {
		this.usedTag = usedTag;
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
	public String getPmtType() {
		return pmtType;
	}
	public void setPmtType(String pmtType) {
		this.pmtType = pmtType;
	}
	public String getPmtName() {
		return pmtName;
	}
	public void setPmtName(String pmtName) {
		this.pmtName = pmtName;
	}
	public String getPmtInternalName() {
		return pmtInternalName;
	}
	public void setPmtInternalName(String pmtInternalName) {
		this.pmtInternalName = pmtInternalName;
	}
	public String getPmtIcon() {
		return pmtIcon;
	}
	public void setPmtIcon(String pmtIcon) {
		this.pmtIcon = pmtIcon;
	}
	public String getPmtOpt10() {
		return pmtOpt10;
	}
	public void setPmtOpt10(String pmtOpt10) {
		this.pmtOpt10 = pmtOpt10;
	}
	public String getPmtOpt9() {
		return pmtOpt9;
	}
	public void setPmtOpt9(String pmtOpt9) {
		this.pmtOpt9 = pmtOpt9;
	}
	public String getPmtOpt8() {
		return pmtOpt8;
	}
	public void setPmtOpt8(String pmtOpt8) {
		this.pmtOpt8 = pmtOpt8;
	}
	public String getPmtOpt7() {
		return pmtOpt7;
	}
	public void setPmtOpt7(String pmtOpt7) {
		this.pmtOpt7 = pmtOpt7;
	}
	public String getPmtOpt6() {
		return pmtOpt6;
	}
	public void setPmtOpt6(String pmtOpt6) {
		this.pmtOpt6 = pmtOpt6;
	}
	public String getPmtOpt5() {
		return pmtOpt5;
	}
	public void setPmtOpt5(String pmtOpt5) {
		this.pmtOpt5 = pmtOpt5;
	}
	public String getPmtOpt4() {
		return pmtOpt4;
	}
	public void setPmtOpt4(String pmtOpt4) {
		this.pmtOpt4 = pmtOpt4;
	}
	public String getPmtOpt3() {
		return pmtOpt3;
	}
	public void setPmtOpt3(String pmtOpt3) {
		this.pmtOpt3 = pmtOpt3;
	}
	public String getPmtOpt2() {
		return pmtOpt2;
	}
	public void setPmtOpt2(String pmtOpt2) {
		this.pmtOpt2 = pmtOpt2;
	}
	public String getPmtOpt1() {
		return pmtOpt1;
	}
	public void setPmtOpt1(String pmtOpt1) {
		this.pmtOpt1 = pmtOpt1;
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
