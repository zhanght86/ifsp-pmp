package com.ruimin.ifs.server.common.bean;

/**
 * 商户银行账户变更:返回报文
 */
public class MerAcctChangResp extends BaseResponse {

	/** 商户号 */
	private String mchtId;

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
