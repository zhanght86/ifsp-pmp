package com.ruimin.ifs.pmp.payProdMng.process.bean;

import com.ruimin.ifs.pmp.sysConf.process.bean.TradeType;

public class AccessAndTxnType extends TradeType {
	private String accessTypeCode;// 接入方式类型，用于详情展示的时候，显示关联关系
	private String accessTypeName;

	public String getAccessTypeCode() {
		return accessTypeCode;
	}

	public void setAccessTypeCode(String accessTypeCode) {
		this.accessTypeCode = accessTypeCode;
	}

	public String getAccessTypeName() {
		return accessTypeName;
	}

	public void setAccessTypeName(String accessTypeName) {
		this.accessTypeName = accessTypeName;
	}

}
