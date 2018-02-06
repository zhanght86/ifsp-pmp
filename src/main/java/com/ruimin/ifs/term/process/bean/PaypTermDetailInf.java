/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.po 
 * FileName: PaypTermInf.java
 * Author:   wangyl
 * Date:     2016年8月5日 上午9:32:42
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   wangyl           2016年8月5日上午9:32:42                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.term.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年10月20日 <br>
 * 作者：wuhd <br>
 * 说明：<br>
 */

public class PaypTermDetailInf {

	private String mchtId;// 商户id
	// 终端号
	@Id
	private String termId;
	// 绑定设备号
	private String machId;
	// 终端状态
	private String termStat;
	// 终端类型
	private String termType;
	// 终端签到状态
	private String termSignStat;
	// 参数下载标志
	private String parmDownFlag;
	// 交易权限
	private String termParm;
	// CA公钥版本
	private String keyVer;
	// IC卡参数版本
	private String icParmVer;
	// 是否支持IC卡
	private String icFlag;
	// 终端批次号
	private String termBatchNo;
	// 终端批结日期
	private String termBatchDate;
	// 绑定电话
	private String bindTelNo;
	// 绑定电话1
	private String bindTelNo1;
	// 终端安装地址
	private String termInstAdrr;
	// 最近审核操作员
	private String lastAudTlr;
	// 最近审核日期时间
	private String lastAudDateTime;
	// 创建操作员
	private String crtTlr;
	// 创建日期时间
	private String crtDateTime;
	// 最近更新操作员
	private String lastUpdTlr;
	// 最近更新日期时间
	private String lastUpdDateTime;
	// 商户名称
	private String mchtName;
	// 商户简称
	private String mchtSimpleName;
	// 商户机构ID
	private String mchtOrgId;
	// 商户机构名称
	private String mchtOrg;
	private String payMchtId;
	private String auditId;
	private String syncState;
	private String zmk;
	private String zak;
	private String zpk;

	//新增字段
	private String secMerId;
	
	public String getSecMerId() {
		return secMerId;
	}

	public void setSecMerId(String secMerId) {
		this.secMerId = secMerId;
	}

	public String getZmk() {
		return zmk;
	}

	public void setZmk(String zmk) {
		this.zmk = zmk;
	}

	public String getZak() {
		return zak;
	}

	public void setZak(String zak) {
		this.zak = zak;
	}

	public String getZpk() {
		return zpk;
	}

	public void setZpk(String zpk) {
		this.zpk = zpk;
	}

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public String getSyncState() {
		return syncState;
	}

	public void setSyncState(String syncState) {
		this.syncState = syncState;
	}

	public String getPayMchtId() {
		return payMchtId;
	}

	public void setPayMchtId(String payMchtId) {
		this.payMchtId = payMchtId;
	}

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
	 * @return the termId
	 */
	public String getTermId() {
		return termId;
	}

	/**
	 * @param termId
	 *            the termId to set
	 */
	public void setTermId(String termId) {
		this.termId = termId;
	}

	/**
	 * @return the machId
	 */
	public String getMachId() {
		return machId;
	}

	/**
	 * @param machId
	 *            the machId to set
	 */
	public void setMachId(String machId) {
		this.machId = machId;
	}

	/**
	 * @return the termStat
	 */
	public String getTermStat() {
		return termStat;
	}

	/**
	 * @param termStat
	 *            the termStat to set
	 */
	public void setTermStat(String termStat) {
		this.termStat = termStat;
	}

	/**
	 * @return the termType
	 */
	public String getTermType() {
		return termType;
	}

	/**
	 * @param termType
	 *            the termType to set
	 */
	public void setTermType(String termType) {
		this.termType = termType;
	}

	/**
	 * @return the termSignStat
	 */
	public String getTermSignStat() {
		return termSignStat;
	}

	/**
	 * @param termSignStat
	 *            the termSignStat to set
	 */
	public void setTermSignStat(String termSignStat) {
		this.termSignStat = termSignStat;
	}

	/**
	 * @return the parmDownFlag
	 */
	public String getParmDownFlag() {
		return parmDownFlag;
	}

	/**
	 * @param parmDownFlag
	 *            the parmDownFlag to set
	 */
	public void setParmDownFlag(String parmDownFlag) {
		this.parmDownFlag = parmDownFlag;
	}

	/**
	 * @return the termParm
	 */
	public String getTermParm() {
		return termParm;
	}

	/**
	 * @param termParm
	 *            the termParm to set
	 */
	public void setTermParm(String termParm) {
		this.termParm = termParm;
	}

	/**
	 * @return the keyVer
	 */
	public String getKeyVer() {
		return keyVer;
	}

	/**
	 * @param keyVer
	 *            the keyVer to set
	 */
	public void setKeyVer(String keyVer) {
		this.keyVer = keyVer;
	}

	/**
	 * @return the icParmVer
	 */
	public String getIcParmVer() {
		return icParmVer;
	}

	/**
	 * @param icParmVer
	 *            the icParmVer to set
	 */
	public void setIcParmVer(String icParmVer) {
		this.icParmVer = icParmVer;
	}

	/**
	 * @return the icFlag
	 */
	public String getIcFlag() {
		return icFlag;
	}

	/**
	 * @param icFlag
	 *            the icFlag to set
	 */
	public void setIcFlag(String icFlag) {
		this.icFlag = icFlag;
	}

	/**
	 * @return the termBatchNo
	 */
	public String getTermBatchNo() {
		return termBatchNo;
	}

	/**
	 * @param termBatchNo
	 *            the termBatchNo to set
	 */
	public void setTermBatchNo(String termBatchNo) {
		this.termBatchNo = termBatchNo;
	}

	/**
	 * @return the termBatchDate
	 */
	public String getTermBatchDate() {
		return termBatchDate;
	}

	/**
	 * @param termBatchDate
	 *            the termBatchDate to set
	 */
	public void setTermBatchDate(String termBatchDate) {
		this.termBatchDate = termBatchDate;
	}

	/**
	 * @return the bindTelNo
	 */
	public String getBindTelNo() {
		return bindTelNo;
	}

	/**
	 * @param bindTelNo
	 *            the bindTelNo to set
	 */
	public void setBindTelNo(String bindTelNo) {
		this.bindTelNo = bindTelNo;
	}

	/**
	 * @return the bindTelNo1
	 */
	public String getBindTelNo1() {
		return bindTelNo1;
	}

	/**
	 * @param bindTelNo1
	 *            the bindTelNo1 to set
	 */
	public void setBindTelNo1(String bindTelNo1) {
		this.bindTelNo1 = bindTelNo1;
	}

	/**
	 * @return the termInstAdrr
	 */
	public String getTermInstAdrr() {
		return termInstAdrr;
	}

	/**
	 * @param termInstAdrr
	 *            the termInstAdrr to set
	 */
	public void setTermInstAdrr(String termInstAdrr) {
		this.termInstAdrr = termInstAdrr;
	}

	/**
	 * @return the lastAudTlr
	 */
	public String getLastAudTlr() {
		return lastAudTlr;
	}

	/**
	 * @param lastAudTlr
	 *            the lastAudTlr to set
	 */
	public void setLastAudTlr(String lastAudTlr) {
		this.lastAudTlr = lastAudTlr;
	}

	/**
	 * @return the lastAudDateTime
	 */
	public String getLastAudDateTime() {
		return lastAudDateTime;
	}

	/**
	 * @param lastAudDateTime
	 *            the lastAudDateTime to set
	 */
	public void setLastAudDateTime(String lastAudDateTime) {
		this.lastAudDateTime = lastAudDateTime;
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

	/**
	 * @return the mchtName
	 */
	public String getMchtName() {
		return mchtName;
	}

	/**
	 * @param mchtName
	 *            the mchtName to set
	 */
	public void setMchtName(String mchtName) {
		this.mchtName = mchtName;
	}

	/**
	 * @return the mchtSimpleName
	 */
	public String getMchtSimpleName() {
		return mchtSimpleName;
	}

	/**
	 * @param mchtSimpleName
	 *            the mchtSimpleName to set
	 */
	public void setMchtSimpleName(String mchtSimpleName) {
		this.mchtSimpleName = mchtSimpleName;
	}

	/**
	 * @return the mchtOrgId
	 */
	public String getMchtOrgId() {
		return mchtOrgId;
	}

	/**
	 * @param mchtOrgId
	 *            the mchtOrgId to set
	 */
	public void setMchtOrgId(String mchtOrgId) {
		this.mchtOrgId = mchtOrgId;
	}

	/**
	 * @return the mchtOrg
	 */
	public String getMchtOrg() {
		return mchtOrg;
	}

	/**
	 * @param mchtOrg
	 *            the mchtOrg to set
	 */
	public void setMchtOrg(String mchtOrg) {
		this.mchtOrg = mchtOrg;
	}

}
