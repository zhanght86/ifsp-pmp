package com.ruimin.ifs.pmp.chnlMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 
 * 通道商户交易关系表-实体类
 * 
 * @author zhangjc
 *
 */
@Table("PAGY_MCHT_TXN_REL")
public class PagyMchtTxnRel {
	@Id
	/** 变量 pagyTxnCode . */
	private String pagyTxnCode;
	@Id
	/** 变量 mainMchtNo . */
	private String mainMchtNo;
	@Id
	/** 变量 pagyNo . */
	private String pagyNo;
	@Id
	/** 变量 acctTypeNo . */
	private String acctTypeNo;

	public String getPagyTxnCode() {
		return pagyTxnCode;
	}

	public void setPagyTxnCode(String pagyTxnCode) {
		this.pagyTxnCode = pagyTxnCode;
	}

	public String getMainMchtNo() {
		return mainMchtNo;
	}

	public void setMainMchtNo(String mainMchtNo) {
		this.mainMchtNo = mainMchtNo;
	}

	public String getPagyNo() {
		return pagyNo;
	}

	public void setPagyNo(String pagyNo) {
		this.pagyNo = pagyNo;
	}

	public String getAcctTypeNo() {
		return acctTypeNo;
	}

	public void setAcctTypeNo(String acctTypeNo) {
		this.acctTypeNo = acctTypeNo;
	}
}
