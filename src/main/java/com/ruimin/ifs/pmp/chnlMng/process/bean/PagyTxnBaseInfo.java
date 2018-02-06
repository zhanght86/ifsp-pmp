/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.chnlMng.comp 
 * FileName: PagyTxnBaseInfo.java
 * Author:   zqy
 * Date:     2016年7月27日 上午10:39:09
 * Description: 通道信息管理     
 * History: 
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zqy           2016年7月27日上午10:39:09                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 名称：通道信息管理 功能：通道信息管理 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月27日 <br>
 * 作者：zqy <br>
 * 说明：<br>
 */
@Table("PAGY_TXN_BASE_INFO")
public class PagyTxnBaseInfo {
	@Id
	// 交易编号
	private String pagyTxnCode;
	// 交易名称
	private String pagyTxnName;
	// 通道编号
	private String pagyNo;
	// 交易状态
	private String pagyTxnStat;
	// 交易类型
	private String txnType;
	// 绑定检测
	private String bindCheck;
	// 撤销交易编号
	private String cancelTxnCode;
	// 退货交易编号
	private String refundTxnCode;
	// 正交易编号
	private String queryTxnCode;
	// 验证交易编号
	private String validateTxnCode;
	// 创建柜员
	private String crtTlr;
	// 创建日期时间
	private String crtDateTime;
	// 最近更新柜员
	private String lastUpdTlr;
	// 最近更新日期时间
	private String lastUpdDateTime;

	/**
	 * @return the pagyTxnCode
	 */
	public String getPagyTxnCode() {
		return pagyTxnCode;
	}

	/**
	 * @param pagyTxnCode
	 *            the pagyTxnCode to set
	 */
	public void setPagyTxnCode(String pagyTxnCode) {
		this.pagyTxnCode = pagyTxnCode;
	}

	/**
	 * @return the pagyTxnName
	 */
	public String getPagyTxnName() {
		return pagyTxnName;
	}

	/**
	 * @param pagyTxnName
	 *            the pagyTxnName to set
	 */
	public void setPagyTxnName(String pagyTxnName) {
		this.pagyTxnName = pagyTxnName;
	}

	/**
	 * @return the pagyNo
	 */
	public String getPagyNo() {
		return pagyNo;
	}

	/**
	 * @param pagyNo
	 *            the pagyNo to set
	 */
	public void setPagyNo(String pagyNo) {
		this.pagyNo = pagyNo;
	}

	/**
	 * @return the pagyTxnStat
	 */
	public String getPagyTxnStat() {
		return pagyTxnStat;
	}

	/**
	 * @param pagyTxnStat
	 *            the pagyTxnStat to set
	 */
	public void setPagyTxnStat(String pagyTxnStat) {
		this.pagyTxnStat = pagyTxnStat;
	}

	/**
	 * @return the txnType
	 */
	public String getTxnType() {
		return txnType;
	}

	/**
	 * @param txnType
	 *            the txnType to set
	 */
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	/**
	 * @return the bindCheck
	 */
	public String getBindCheck() {
		return bindCheck;
	}

	/**
	 * @param bindCheck
	 *            the bindCheck to set
	 */
	public void setBindCheck(String bindCheck) {
		this.bindCheck = bindCheck;
	}

	/**
	 * @return the cancelTxncode
	 */
	public String getCancelTxnCode() {
		return cancelTxnCode;
	}

	/**
	 * @param cancelTxncode
	 *            the cancelTxncode to set
	 */
	public void setCancelTxnCode(String cancelTxnCode) {
		this.cancelTxnCode = cancelTxnCode;
	}

	/**
	 * @return the refundTxnCode
	 */
	public String getRefundTxnCode() {
		return refundTxnCode;
	}

	/**
	 * @param refundTxnCode
	 *            the refundTxnCode to set
	 */
	public void setRefundTxnCode(String refundTxnCode) {
		this.refundTxnCode = refundTxnCode;
	}

	/**
	 * @return the queryTxnCode
	 */
	public String getQueryTxnCode() {
		return queryTxnCode;
	}

	/**
	 * @param queryTxnCode
	 *            the queryTxnCode to set
	 */
	public void setQueryTxnCode(String queryTxnCode) {
		this.queryTxnCode = queryTxnCode;
	}

	/**
	 * @return the validateTxnCode
	 */
	public String getValidateTxnCode() {
		return validateTxnCode;
	}

	/**
	 * @param validateTxnCode
	 *            the validateTxnCode to set
	 */
	public void setValidateTxnCode(String validateTxnCode) {
		this.validateTxnCode = validateTxnCode;
	}

	/**
	 * @return the crtTlr
	 */
	public String getCrtTlr() {
		return crtTlr;
	}

	/**
	 * @param crtTlr
	 *            the crtTlr to set
	 */
	public void setCrtTlr(String crtTlr) {
		this.crtTlr = crtTlr;
	}

	/**
	 * @return the crtDateTime
	 */
	public String getCrtDateTime() {
		return crtDateTime;
	}

	/**
	 * @param crtDateTime
	 *            the crtDateTime to set
	 */
	public void setCrtDateTime(String crtDateTime) {
		this.crtDateTime = crtDateTime;
	}

	/**
	 * @return the lastUpdTlr
	 */
	public String getLastUpdTlr() {
		return lastUpdTlr;
	}

	/**
	 * @param lastUpdTlr
	 *            the lastUpdTlr to set
	 */
	public void setLastUpdTlr(String lastUpdTlr) {
		this.lastUpdTlr = lastUpdTlr;
	}

	/**
	 * @return the lastUpdDateTime
	 */
	public String getLastUpdDateTime() {
		return lastUpdDateTime;
	}

	/**
	 * @param lastUpdDateTime
	 *            the lastUpdDateTime to set
	 */
	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}

}
