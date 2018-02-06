package com.ruimin.ifs.pmp.chnlMng.process.bean;

/**
 * 
 * 【通道交易账户类型银行关系】实体类-继承【通道交易账户类型银行关系表】
 * 
 * @author zhangjc
 *
 */
public class PagyTxnAcctBankRelVO extends PagyTxnAcctBankRel {
	// 银行名称
	private String bankName;
	// 银行列表
	private String bankCount;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCount() {
		return bankCount;
	}

	public void setBankCount(String bankCount) {
		this.bankCount = bankCount;
	}

}
