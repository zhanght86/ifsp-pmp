package com.ruimin.ifs.pmp.sysConf.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("CHL_PASS_BASE_INFO")
public class PassInfoVO {
	@Id
	private String passNo; // 通道编号
	private String passName; // 通道名称
	private String passStat; // 通道状态
	private String openDate; // 开通日期
	private String passAccessType; // 接入方式
	private String passSeltMode; // 清算类型
	private String passSetlCycle; // 清算周期
	private String passSetlAcct; // 清算账户
	private String passseltTime; // 清算时间

	private String remark; // 备注
	private String crtTlr; // 创建柜员
	private String crtDateTime; // 创建日期时间
	private String lastUpdTlr; // 最近更新柜员
	private String lastUpdDateTime; // 最近更新日期时间

	/**
	 * @return the passNo
	 */
	public String getPassNo() {
		return passNo;
	}

	/**
	 * @param passNo
	 *            the passNo to set
	 */
	public void setPassNo(String passNo) {
		this.passNo = passNo;
	}

	/**
	 * @return the passName
	 */
	public String getPassName() {
		return passName;
	}

	/**
	 * @param passName
	 *            the passName to set
	 */
	public void setPassName(String passName) {
		this.passName = passName;
	}

	/**
	 * @return the passStat
	 */
	public String getPassStat() {
		return passStat;
	}

	/**
	 * @param passStat
	 *            the passStat to set
	 */
	public void setPassStat(String passStat) {
		this.passStat = passStat;
	}

	/**
	 * @return the openDate
	 */
	public String getOpenDate() {
		return openDate;
	}

	/**
	 * @param openDate
	 *            the openDate to set
	 */
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	/**
	 * @return the passAccessType
	 */
	public String getPassAccessType() {
		return passAccessType;
	}

	/**
	 * @param passAccessType
	 *            the passAccessType to set
	 */
	public void setPassAccessType(String passAccessType) {
		this.passAccessType = passAccessType;
	}

	/**
	 * @return the passSeltMode
	 */
	public String getPassSeltMode() {
		return passSeltMode;
	}

	/**
	 * @param passSeltMode
	 *            the passSeltMode to set
	 */
	public void setPassSeltMode(String passSeltMode) {
		this.passSeltMode = passSeltMode;
	}

	/**
	 * @return the passSetlCycle
	 */
	public String getPassSetlCycle() {
		return passSetlCycle;
	}

	/**
	 * @param passSetlCycle
	 *            the passSetlCycle to set
	 */
	public void setPassSetlCycle(String passSetlCycle) {
		this.passSetlCycle = passSetlCycle;
	}

	/**
	 * @return the passSetlAcct
	 */
	public String getPassSetlAcct() {
		return passSetlAcct;
	}

	/**
	 * @param passSetlAcct
	 *            the passSetlAcct to set
	 */
	public void setPassSetlAcct(String passSetlAcct) {
		this.passSetlAcct = passSetlAcct;
	}

	/**
	 * @return the passseltTime
	 */
	public String getPassseltTime() {
		return passseltTime;
	}

	/**
	 * @param passseltTime
	 *            the passseltTime to set
	 */
	public void setPassseltTime(String passseltTime) {
		this.passseltTime = passseltTime;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the crtTlr
	 */
	public String getCrtTlr() {
		return crtTlr;
	}

	/**
	 * @param crtTlr
	 *            the crtTlr to set
	 */
	public void setCrtTlr(String crtTlr) {
		this.crtTlr = crtTlr;
	}

	/**
	 * @return the crtDateTime
	 */
	public String getCrtDateTime() {
		return crtDateTime;
	}

	/**
	 * @param crtDateTime
	 *            the crtDateTime to set
	 */
	public void setCrtDateTime(String crtDateTime) {
		this.crtDateTime = crtDateTime;
	}

	/**
	 * @return the lastUpdTlr
	 */
	public String getLastUpdTlr() {
		return lastUpdTlr;
	}

	/**
	 * @param lastUpdTlr
	 *            the lastUpdTlr to set
	 */
	public void setLastUpdTlr(String lastUpdTlr) {
		this.lastUpdTlr = lastUpdTlr;
	}

	/**
	 * @return the lastUpdDateTime
	 */
	public String getLastUpdDateTime() {
		return lastUpdDateTime;
	}

	/**
	 * @param lastUpdDateTime
	 *            the lastUpdDateTime to set
	 */
	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}

}
