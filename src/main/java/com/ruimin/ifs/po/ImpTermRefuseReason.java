package com.ruimin.ifs.po;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("IMP_TERM_REFUSE_REASON")
public class ImpTermRefuseReason implements java.io.Serializable {

	private static final long serialVersionUID = 7618525119763820128L;
	@Id
	private String id;
	private String termId;
	private String mchtId;
	private String reason;
	private String lastAudTlr;
	private String lastAudDateTime;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the termId
	 */
	public String getTermId() {
		return termId;
	}

	/**
	 * @param termId
	 *            the termId to set
	 */
	public void setTermId(String termId) {
		this.termId = termId;
	}

	/**
	 * @return the mchtId
	 */
	public String getMchtId() {
		return mchtId;
	}

	/**
	 * @param mchtId
	 *            the mchtId to set
	 */
	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the lastAudTlr
	 */
	public String getLastAudTlr() {
		return lastAudTlr;
	}

	/**
	 * @param lastAudTlr
	 *            the lastAudTlr to set
	 */
	public void setLastAudTlr(String lastAudTlr) {
		this.lastAudTlr = lastAudTlr;
	}

	/**
	 * @return the lastAudDateTime
	 */
	public String getLastAudDateTime() {
		return lastAudDateTime;
	}

	/**
	 * @param lastAudDateTime
	 *            the lastAudDateTime to set
	 */
	public void setLastAudDateTime(String lastAudDateTime) {
		this.lastAudDateTime = lastAudDateTime;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}