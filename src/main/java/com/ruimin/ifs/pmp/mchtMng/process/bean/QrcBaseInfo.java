package com.ruimin.ifs.pmp.mchtMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 二维码基本信息表
 * 
 * @author shrm_tyzf010
 *
 */
@Table("MSS_QRC_BASE_INFO")
public class QrcBaseInfo {
	@Id
	private String qrcCodeId;
	private String mchtId;
	private String qrcType;
	private String qrcStat;
	private String brno;
	private String brname;
	private String qrCode;
	private String batchId;
	private String brcode;
	private String mchtSimpleName;
	private String mchtUseStat;
	private String respTxnSsn;
	private String respTxnTime;
	private String downCount;
	private String qrcPicId;
	private String expiryDateTime;
	private String tlrno;
	private String tlrName;
	private String crtDate;
	private String lastUpdTlr;
	private String lastUpdDateTime;
	
	
	
	public String getQrcCodeId() {
		return qrcCodeId;
	}

	public void setQrcCodeId(String qrcCodeId) {
		this.qrcCodeId = qrcCodeId;
	}

	public String getMchtId() {
		return mchtId;
	}

	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}

	public String getQrcType() {
		return qrcType;
	}

	public void setQrcType(String qrcType) {
		this.qrcType = qrcType;
	}

	public String getQrcStat() {
		return qrcStat;
	}

	public void setQrcStat(String qrcStat) {
		this.qrcStat = qrcStat;
	}

	public String getBrno() {
		return brno;
	}

	public void setBrno(String brno) {
		this.brno = brno;
	}

	public String getBrname() {
		return brname;
	}

	public void setBrname(String brname) {
		this.brname = brname;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBrcode() {
		return brcode;
	}

	public void setBrcode(String brcode) {
		this.brcode = brcode;
	}

	public String getMchtSimpleName() {
		return mchtSimpleName;
	}

	public void setMchtSimpleName(String mchtSimpleName) {
		this.mchtSimpleName = mchtSimpleName;
	}

	public String getMchtUseStat() {
		return mchtUseStat;
	}

	public void setMchtUseStat(String mchtUseStat) {
		this.mchtUseStat = mchtUseStat;
	}

	public String getRespTxnSsn() {
		return respTxnSsn;
	}

	public void setRespTxnSsn(String respTxnSsn) {
		this.respTxnSsn = respTxnSsn;
	}

	public String getRespTxnTime() {
		return respTxnTime;
	}

	public void setRespTxnTime(String respTxnTime) {
		this.respTxnTime = respTxnTime;
	}

	public String getDownCount() {
		return downCount;
	}

	public void setDownCount(String downCount) {
		this.downCount = downCount;
	}

	public String getQrcPicId() {
		return qrcPicId;
	}

	public void setQrcPicId(String qrcPicId) {
		this.qrcPicId = qrcPicId;
	}

	public String getExpiryDateTime() {
		return expiryDateTime;
	}

	public void setExpiryDateTime(String expiryDateTime) {
		this.expiryDateTime = expiryDateTime;
	}

	public String getTlrno() {
		return tlrno;
	}

	public void setTlrno(String tlrno) {
		this.tlrno = tlrno;
	}

	public String getTlrName() {
		return tlrName;
	}

	public void setTlrName(String tlrName) {
		this.tlrName = tlrName;
	}

	public String getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}

	public String getLastUpdTlr() {
		return lastUpdTlr;
	}

	public void setLastUpdTlr(String lastUpdTlr) {
		this.lastUpdTlr = lastUpdTlr;
	}

	public String getLastUpdDateTime() {
		return lastUpdDateTime;
	}

	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}

}
