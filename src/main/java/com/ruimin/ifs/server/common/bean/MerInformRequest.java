package com.ruimin.ifs.server.common.bean;

import com.ruimin.ifs.server.common.annotation.Empty;
import com.ruimin.ifs.server.common.annotation.VerifyInterface;
import com.ruimin.ifs.server.common.annotation.Empty.AllowEmpty;
import com.ruimin.ifs.server.common.annotation.VerifyInterface.Type;

/**
 * 商户审核结果通知:请求报文
 */
@VerifyInterface
public class MerInformRequest extends BaseRequest {

	/** 商户号 */
	private String mchtId;
	/** 审批结果 */
	private String confirmResult;
	/** 审批类型 */
	private String confirmType;
	/** 未通过原因 */
	@Empty(empty = AllowEmpty.NULL)
	private String failReason;
	/** 原请求流水 */
	private String txnSeqId;

	/**
	 * @return the txnSeqId
	 */
	public String getTxnSeqId() {
		return txnSeqId;
	}

	/**
	 * @param txnSeqId
	 *            the txnSeqId to set
	 */
	public void setTxnSeqId(String txnSeqId) {
		this.txnSeqId = txnSeqId;
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
		return failReason;
	}

	/**
	 * @param failReason
	 *            the failReason to set
	 */
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

}
