/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.chnlMng.process.bean 
 * FileName: SaveDataUser.java
 * Author:   chenqilei
 * Date:     2016年8月2日 下午3:06:28
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年8月2日下午3:06:28                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.bean;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月2日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
public class SaveDataUser {
	private String payTxnTypeId;
	private String payTxnTypeName;
	private String acctTypeNo;
	private String acctTypeName;
	private String ttsCode;
	private String ttsResp;
	private String ttsCodeCount;
	private String chlId;
	private String chlName;

	public String getPayTxnTypeId() {
		return payTxnTypeId;
	}

	public void setPayTxnTypeId(String payTxnTypeId) {
		this.payTxnTypeId = payTxnTypeId;
	}

	public String getPayTxnTypeName() {
		return payTxnTypeName;
	}

	public void setPayTxnTypeName(String payTxnTypeName) {
		this.payTxnTypeName = payTxnTypeName;
	}

	public String getAcctTypeNo() {
		return acctTypeNo;
	}

	public void setAcctTypeNo(String acctTypeNo) {
		this.acctTypeNo = acctTypeNo;
	}

	public String getAcctTypeName() {
		return acctTypeName;
	}

	public void setAcctTypeName(String acctTypeName) {
		this.acctTypeName = acctTypeName;
	}

	public String getTtsCode() {
		return ttsCode;
	}

	public void setTtsCode(String ttsCode) {
		this.ttsCode = ttsCode;
	}

	public String getTtsResp() {
		return ttsResp;
	}

	public void setTtsResp(String ttsResp) {
		this.ttsResp = ttsResp;
	}

	public String getTtsCodeCount() {
		return ttsCodeCount;
	}

	public void setTtsCodeCount(String ttsCodeCount) {
		this.ttsCodeCount = ttsCodeCount;
	}

	public String getChlId() {
		return chlId;
	}

	public void setChlId(String chlId) {
		this.chlId = chlId;
	}

	public String getChlName() {
		return chlName;
	}

	public void setChlName(String chlName) {
		this.chlName = chlName;
	}

}
