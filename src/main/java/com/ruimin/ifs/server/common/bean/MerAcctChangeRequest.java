package com.ruimin.ifs.server.common.bean;

import com.ruimin.ifs.server.common.annotation.Stand;
import com.ruimin.ifs.server.common.annotation.Stand.AllowEmpty;
import com.ruimin.ifs.server.common.annotation.VerifyInterface;
import com.ruimin.ifs.server.common.annotation.VerifyInterface.Type;

/**
 * 商户银行账户变更:请求报文
 */
@VerifyInterface
public class MerAcctChangeRequest extends BaseRequest {

	/** 商户号 */
	@Stand(maxLen = 16)
	private String mchtId;
	/** 银行账号 */
	@Stand(maxLen = 40)
	public String setlAcct;
	/** 户名 */
	@Stand(maxLen = 64)
	public String setlAcctName;
	/** 行号 */
	@Stand(maxLen = 16)
	public String setlBankNo;
	/** 行名 */
	@Stand(maxLen = 80, empty = AllowEmpty.NULL)
	public String setlBankName;
	/** 对公/对私 */
	@Stand(scope = { "A", "B" })
	public String setlAcctAttr;

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
	 * @return the setlAcct
	 */
	public String getSetlAcct() {
		return setlAcct;
	}

	/**
	 * @param setlAcct
	 *            the setlAcct to set
	 */
	public void setSetlAcct(String setlAcct) {
		this.setlAcct = setlAcct;
	}

	/**
	 * @return the setlAcctName
	 */
	public String getSetlAcctName() {
		return setlAcctName;
	}

	/**
	 * @param setlAcctName
	 *            the setlAcctName to set
	 */
	public void setSetlAcctName(String setlAcctName) {
		this.setlAcctName = setlAcctName;
	}

	/**
	 * @return the setlBankNo
	 */
	public String getSetlBankNo() {
		return setlBankNo;
	}

	/**
	 * @param setlBankNo
	 *            the setlBankNo to set
	 */
	public void setSetlBankNo(String setlBankNo) {
		this.setlBankNo = setlBankNo;
	}

	/**
	 * @return the setlBankName
	 */
	public String getSetlBankName() {
		return setlBankName;
	}

	/**
	 * @param setlBankName
	 *            the setlBankName to set
	 */
	public void setSetlBankName(String setlBankName) {
		this.setlBankName = setlBankName;
	}

	/**
	 * @return the setlAcctAttr
	 */
	public String getSetlAcctAttr() {
		return setlAcctAttr;
	}

	/**
	 * @param setlAcctAttr
	 *            the setlAcctAttr to set
	 */
	public void setSetlAcctAttr(String setlAcctAttr) {
		this.setlAcctAttr = setlAcctAttr;
	}

}
