package com.ruimin.ifs.po;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("ifs_org")
public class TblBctl {
	@Id
	private String brcode;
	private String brno;
	private String brname;
	private String brclass;
	private String brattr;
	private String blnBranchClass;
	private String blnBranchBrcode;
	private String blnManageBrcode;
	private String blnUpBrcode;
	private String txnBrcode;
	private String authlvl;
	private String linkman;
	private String teleno;
	private String address;
	private String postno;
	private String otherAreaFlag;
	private String regionalism;
	private String financeCode;
	private String status;
	private String miscflgs;
	private String misc;
	private String lastUpdTlr;
	private String lastUpdFunc;
	private String lastUpdDate;
	private String st;
	private String isLock;
	private String isDel;
	// 增加3个字段
	private String cupBrhId;
	private String hostBrhId;
	private String areaCd;

	public String getBrcode() {
		return brcode;
	}

	public void setBrcode(String brcode) {
		this.brcode = brcode;
	}

	public String getBrno() {
		return brno;
	}

	public void setBrno(String brno) {
		this.brno = brno;
	}

	public String getBrname() {
		return brname;
	}

	public void setBrname(String brname) {
		this.brname = brname;
	}

	public String getBrclass() {
		return brclass;
	}

	public void setBrclass(String brclass) {
		this.brclass = brclass;
	}

	public String getBrattr() {
		return brattr;
	}

	public void setBrattr(String brattr) {
		this.brattr = brattr;
	}

	public String getBlnBranchClass() {
		return blnBranchClass;
	}

	public void setBlnBranchClass(String blnBranchClass) {
		this.blnBranchClass = blnBranchClass;
	}

	public String getBlnBranchBrcode() {
		return blnBranchBrcode;
	}

	public void setBlnBranchBrcode(String blnBranchBrcode) {
		this.blnBranchBrcode = blnBranchBrcode;
	}

	public String getBlnManageBrcode() {
		return blnManageBrcode;
	}

	public void setBlnManageBrcode(String blnManageBrcode) {
		this.blnManageBrcode = blnManageBrcode;
	}

	public String getBlnUpBrcode() {
		return blnUpBrcode;
	}

	public void setBlnUpBrcode(String blnUpBrcode) {
		this.blnUpBrcode = blnUpBrcode;
	}

	public String getTxnBrcode() {
		return txnBrcode;
	}

	public void setTxnBrcode(String txnBrcode) {
		this.txnBrcode = txnBrcode;
	}

	public String getAuthlvl() {
		return authlvl;
	}

	public void setAuthlvl(String authlvl) {
		this.authlvl = authlvl;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTeleno() {
		return teleno;
	}

	public void setTeleno(String teleno) {
		this.teleno = teleno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostno() {
		return postno;
	}

	public void setPostno(String postno) {
		this.postno = postno;
	}

	public String getOtherAreaFlag() {
		return otherAreaFlag;
	}

	public void setOtherAreaFlag(String otherAreaFlag) {
		this.otherAreaFlag = otherAreaFlag;
	}

	public String getRegionalism() {
		return regionalism;
	}

	public void setRegionalism(String regionalism) {
		this.regionalism = regionalism;
	}

	public String getFinanceCode() {
		return financeCode;
	}

	public void setFinanceCode(String financeCode) {
		this.financeCode = financeCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMiscflgs() {
		return miscflgs;
	}

	public void setMiscflgs(String miscflgs) {
		this.miscflgs = miscflgs;
	}

	public String getMisc() {
		return misc;
	}

	public void setMisc(String misc) {
		this.misc = misc;
	}

	public String getLastUpdTlr() {
		return lastUpdTlr;
	}

	public void setLastUpdTlr(String lastUpdTlr) {
		this.lastUpdTlr = lastUpdTlr;
	}

	public String getLastUpdFunc() {
		return lastUpdFunc;
	}

	public void setLastUpdFunc(String lastUpdFunc) {
		this.lastUpdFunc = lastUpdFunc;
	}

	public String getLastUpdDate() {
		return lastUpdDate;
	}

	public void setLastUpdDate(String lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getCupBrhId() {
		return cupBrhId;
	}

	public void setCupBrhId(String cupBrhId) {
		this.cupBrhId = cupBrhId;
	}

	public String getHostBrhId() {
		return hostBrhId;
	}

	public void setHostBrhId(String hostBrhId) {
		this.hostBrhId = hostBrhId;
	}

	public String getAreaCd() {
		return areaCd;
	}

	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
	}

}
