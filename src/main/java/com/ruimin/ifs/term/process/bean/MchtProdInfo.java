/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.term.process.bean 
 * FileName: MchtProdInfo.java
 * Author:   wangyl
 * Date:     2016年9月9日 上午9:31:33
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   wangyl           2016年9月9日上午9:31:33                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.term.process.bean;

/**
 * 名称： 商户开通的终端功能<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年9月9日 <br>
 * 作者：wangyl <br>
 * 说明：<br>
 */
public class MchtProdInfo {
	private String txnTypeCode; // 交易类型编号
	private String txnTypeName; // 交易类型名称
	private String txnTypeDesc;// 交易类型描述
	private String prodId;// 产品ID
	private String prodName;// 产品名称
	private String prodDesc;// 产品描述
	private String accessTypeCode;// 接入方式编号
	private String accessTypeName;// 接入方式名称
	private String accesstypeDesc;// 接入方式描述

	/**
	 * @return the txnTypeCode
	 */
	public String getTxnTypeCode() {
		return txnTypeCode;
	}

	/**
	 * @param txnTypeCode
	 *            the txnTypeCode to set
	 */
	public void setTxnTypeCode(String txnTypeCode) {
		this.txnTypeCode = txnTypeCode;
	}

	/**
	 * @return the txnTypeName
	 */
	public String getTxnTypeName() {
		return txnTypeName;
	}

	/**
	 * @param txnTypeName
	 *            the txnTypeName to set
	 */
	public void setTxnTypeName(String txnTypeName) {
		this.txnTypeName = txnTypeName;
	}

	/**
	 * @return the txnTypeDesc
	 */
	public String getTxnTypeDesc() {
		return txnTypeDesc;
	}

	/**
	 * @param txnTypeDesc
	 *            the txnTypeDesc to set
	 */
	public void setTxnTypeDesc(String txnTypeDesc) {
		this.txnTypeDesc = txnTypeDesc;
	}

	/**
	 * @return the prodId
	 */
	public String getProdId() {
		return prodId;
	}

	/**
	 * @param prodId
	 *            the prodId to set
	 */
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	/**
	 * @return the prodName
	 */
	public String getProdName() {
		return prodName;
	}

	/**
	 * @param prodName
	 *            the prodName to set
	 */
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	/**
	 * @return the prodDesc
	 */
	public String getProdDesc() {
		return prodDesc;
	}

	/**
	 * @param prodDesc
	 *            the prodDesc to set
	 */
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}

	/**
	 * @return the accessTypeCode
	 */
	public String getAccessTypeCode() {
		return accessTypeCode;
	}

	/**
	 * @param accessTypeCode
	 *            the accessTypeCode to set
	 */
	public void setAccessTypeCode(String accessTypeCode) {
		this.accessTypeCode = accessTypeCode;
	}

	/**
	 * @return the accessTypeName
	 */
	public String getAccessTypeName() {
		return accessTypeName;
	}

	/**
	 * @param accessTypeName
	 *            the accessTypeName to set
	 */
	public void setAccessTypeName(String accessTypeName) {
		this.accessTypeName = accessTypeName;
	}

	/**
	 * @return the accesstypeDesc
	 */
	public String getAccesstypeDesc() {
		return accesstypeDesc;
	}

	/**
	 * @param accesstypeDesc
	 *            the accesstypeDesc to set
	 */
	public void setAccesstypeDesc(String accesstypeDesc) {
		this.accesstypeDesc = accesstypeDesc;
	}

}
