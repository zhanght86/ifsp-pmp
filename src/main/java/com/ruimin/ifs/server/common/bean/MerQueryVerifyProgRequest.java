package com.ruimin.ifs.server.common.bean;

import com.ruimin.ifs.server.common.annotation.Stand;
import com.ruimin.ifs.server.common.annotation.VerifyInterface;
import com.ruimin.ifs.server.common.annotation.Stand.AllowEmpty;
import com.ruimin.ifs.server.common.annotation.VerifyInterface.Type;

/**
 * 审批进度查询:请求报文
 */
@VerifyInterface
public class MerQueryVerifyProgRequest extends BaseRequest {

	/** 商户号 */
	@Stand(maxLen = 16)
	private String mchtId;
	@Stand(maxLen = 32, empty = AllowEmpty.NULL)
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

}
