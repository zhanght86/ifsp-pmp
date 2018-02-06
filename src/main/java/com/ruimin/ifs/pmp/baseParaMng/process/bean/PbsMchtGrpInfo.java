package com.ruimin.ifs.pmp.baseParaMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 商户组别管理VO类
 */
@Table("PBS_MCHT_GRP_INFO")
public class PbsMchtGrpInfo {
	@Id
	private String mchtGrpNo; // 商户组别编号
	private String grpDesc; // 商户组别描述
	private String grpState; // 组别状态
	private String crtTlr; // 创建柜员
	private String crtDateTime;// 创建日期时间
	private String lastUpdTlr; // 最近更新柜员
	private String lastUpdDateTime;// 最近更新日期时间

	/**
	 * @return the mchtGrpNo
	 */
	public String getMchtGrpNo() {
		return mchtGrpNo;
	}

	/**
	 * @param mchtGrpNo
	 *            the mchtGrpNo to set
	 */
	public void setMchtGrpNo(String mchtGrpNo) {
		this.mchtGrpNo = mchtGrpNo;
	}

	/**
	 * @return the grpDesc
	 */
	public String getGrpDesc() {
		return grpDesc;
	}

	/**
	 * @param grpDesc
	 *            the grpDesc to set
	 */
	public void setGrpDesc(String grpDesc) {
		this.grpDesc = grpDesc;
	}

	/**
	 * @return the grpState
	 */
	public String getGrpState() {
		return grpState;
	}

	/**
	 * @param grpState
	 *            the grpState to set
	 */
	public void setGrpState(String grpState) {
		this.grpState = grpState;
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
