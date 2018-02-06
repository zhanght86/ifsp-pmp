package com.ruimin.ifs.pmp.baseParaMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 商户Mcc管理VO类s
 */
@Table("PBS_MCHT_MCC_INFO")
public class PbsMchtMccInfo {
	@Id
	private String mccId; // MCC编号
	private String mchtGrpId; // 商户组别编号
	private String mccDesc; // 组别状态
	private String mccState; // MCC状态
	private String crtTlr; // 创建柜员
	private String crtDateTime;// 创建日期时间
	private String lastUpdTlr; // 最近更新柜员
	private String lastUpdDateTime;// 最近更新日期时间

	/**
	 * @return the mccId
	 */
	public String getMccId() {
		return mccId;
	}

	/**
	 * @param mccId
	 *            the mccId to set
	 */
	public void setMccId(String mccId) {
		this.mccId = mccId;
	}

	/**
	 * @return the mchtGrpId
	 */
	public String getMchtGrpId() {
		return mchtGrpId;
	}

	/**
	 * @param mchtGrpId
	 *            the mchtGrpId to set
	 */
	public void setMchtGrpId(String mchtGrpId) {
		this.mchtGrpId = mchtGrpId;
	}

	/**
	 * @return the mccDesc
	 */
	public String getMccDesc() {
		return mccDesc;
	}

	/**
	 * @param mccDesc
	 *            the mccDesc to set
	 */
	public void setMccDesc(String mccDesc) {
		this.mccDesc = mccDesc;
	}

	/**
	 * @return the mccState
	 */
	public String getMccState() {
		return mccState;
	}

	/**
	 * @param mccState
	 *            the mccState to set
	 */
	public void setMccState(String mccState) {
		this.mccState = mccState;
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
