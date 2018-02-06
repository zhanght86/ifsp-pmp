package com.ruimin.ifs.server.common.bean;

import com.ruimin.ifs.server.common.annotation.VerifyInterface;

/**
 * 审批进度查询:返回报文
 */
@VerifyInterface()
public class MerQueryVerifyProgResp extends BaseResponse {

	/** 商户号 */
	private String mchtId;
	/** 审批结果 */
	public String confirmResult;
	/** 审批类型 */
	public String confirmType;
	/** 未通过原因 */
	public String FailReason;

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
	 * @return the confirmResult
	 */
	public String getConfirmResult() {
		return confirmResult;
	}

	/**
	 * @param confirmResult
	 *            the confirmResult to set
	 */
	public void setConfirmResult(String confirmResult) {
		this.confirmResult = confirmResult;
	}

	/**
	 * @return the confirmType
	 */
	public String getConfirmType() {
		return confirmType;
	}

	/**
	 * @param confirmType
	 *            the confirmType to set
	 */
	public void setConfirmType(String confirmType) {
		this.confirmType = confirmType;
	}

	/**
	 * @return the failReason
	 */
	public String getFailReason() {
		return FailReason;
	}

	/**
	 * @param failReason
	 *            the failReason to set
	 */
	public void setFailReason(String failReason) {
		FailReason = failReason;
	}

}
