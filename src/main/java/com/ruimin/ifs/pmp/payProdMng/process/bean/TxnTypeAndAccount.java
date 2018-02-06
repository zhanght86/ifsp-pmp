package com.ruimin.ifs.pmp.payProdMng.process.bean;

import com.ruimin.ifs.pmp.sysConf.process.bean.AccountType;

public class TxnTypeAndAccount extends AccountType {
	private String txnTypeCode;// 交易类型，用于详情展示的时候，显示关联关系

	public String getTxnTypeCode() {
		return txnTypeCode;
	}

	public void setTxnTypeCode(String txnTypeCode) {
		this.txnTypeCode = txnTypeCode;
	}

}
