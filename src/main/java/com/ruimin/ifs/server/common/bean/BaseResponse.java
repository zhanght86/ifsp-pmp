package com.ruimin.ifs.server.common.bean;

public class BaseResponse {

	private String respCode;
	private String respMsg;
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
	 * @return the respCode
	 */
	public String getRespCode() {
		return respCode;
	}

	/**
	 * @param respCode
	 *            the respCode to set
	 */
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	/**
	 * @return the respMsg
	 */
	public String getRespMsg() {
		return respMsg;
	}

	/**
	 * @param respMsg
	 *            the respMsg to set
	 */
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

}
