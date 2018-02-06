package com.ruimin.ifs.po;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("IMP_TERM_INF")
public class ImpTermInf implements java.io.Serializable {

	private static final long serialVersionUID = 8765916446204678915L;
	@Id
	private String termId;
	private String mchtId;
	private String termStat;
	private String termType;
	private String termSignStat;
	private String termModlId;
	private String termSoftVer;
	private String parmDownFlag;
	private String termParm;
	private String termPara1;
	private String keyDownFlag;
	private String icDownFlag;
	private String propType;
	private String propOrgName;
	private String icFlag;
	private String termBrh;
	private String termBatchNo;
	private String termBatchDate;
	private String bindTelNo;
	private String bindTelNo1;
	private String termInstAdrr;
	private String fixOrgId;
	private String fixOrgName;
	private String fixTlrName;
	private String fixTlrNo;
	private String depoFlag;
	private String depoAmt;
	private String othSvrId;
	private String othSvrName;
	private String longitude;
	private String latitude;
	private String excuRange;
	private String stationNo;
	private String misc1;
	private String misc2;
	private String miscFlag1;
	private String miscFlag2;
	private String miscDate;
	private String lastAudTlr;
	private String lastAudDateTime;
	private String crtTlr;
	private String crtDateTime;
	private String lastUpdTlr;
	private String lastUpdDateTime;
	private String upMchtId;// 实体商户号
	private String isVirPos;// 是否是虚拟商户号标识
	private String auditFlag;//

	public String getTermId() {
		return this.termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getMchtId() {
		return this.mchtId;
	}

	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}

	public String getTermStat() {
		return this.termStat;
	}

	public void setTermStat(String termStat) {
		this.termStat = termStat;
	}

	public String getTermType() {
		return this.termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getTermSignStat() {
		return this.termSignStat;
	}

	public void setTermSignStat(String termSignStat) {
		this.termSignStat = termSignStat;
	}

	public String getTermModlId() {
		return this.termModlId;
	}

	public void setTermModlId(String termModlId) {
		this.termModlId = termModlId;
	}

	public String getTermSoftVer() {
		return this.termSoftVer;
	}

	public void setTermSoftVer(String termSoftVer) {
		this.termSoftVer = termSoftVer;
	}

	public String getParmDownFlag() {
		return this.parmDownFlag;
	}

	public void setParmDownFlag(String parmDownFlag) {
		this.parmDownFlag = parmDownFlag;
	}

	public String getTermParm() {
		return this.termParm;
	}

	public void setTermParm(String termParm) {
		this.termParm = termParm;
	}

	public String getTermPara1() {
		return this.termPara1;
	}

	public void setTermPara1(String termPara1) {
		this.termPara1 = termPara1;
	}

	public String getKeyDownFlag() {
		return this.keyDownFlag;
	}

	public void setKeyDownFlag(String keyDownFlag) {
		this.keyDownFlag = keyDownFlag;
	}

	public String getIcDownFlag() {
		return this.icDownFlag;
	}

	public void setIcDownFlag(String icDownFlag) {
		this.icDownFlag = icDownFlag;
	}

	public String getPropType() {
		return this.propType;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}

	public String getPropOrgName() {
		return this.propOrgName;
	}

	public void setPropOrgName(String propOrgName) {
		this.propOrgName = propOrgName;
	}

	public String getIcFlag() {
		return this.icFlag;
	}

	public void setIcFlag(String icFlag) {
		this.icFlag = icFlag;
	}

	public String getTermBrh() {
		return this.termBrh;
	}

	public void setTermBrh(String termBrh) {
		this.termBrh = termBrh;
	}

	public String getTermBatchNo() {
		return this.termBatchNo;
	}

	public void setTermBatchNo(String termBatchNo) {
		this.termBatchNo = termBatchNo;
	}

	public String getTermBatchDate() {
		return this.termBatchDate;
	}

	public void setTermBatchDate(String termBatchDate) {
		this.termBatchDate = termBatchDate;
	}

	public String getBindTelNo() {
		return this.bindTelNo;
	}

	public void setBindTelNo(String bindTelNo) {
		this.bindTelNo = bindTelNo;
	}

	public String getBindTelNo1() {
		return this.bindTelNo1;
	}

	public void setBindTelNo1(String bindTelNo1) {
		this.bindTelNo1 = bindTelNo1;
	}

	public String getTermInstAdrr() {
		return this.termInstAdrr;
	}

	public void setTermInstAdrr(String termInstAdrr) {
		this.termInstAdrr = termInstAdrr;
	}

	public String getFixOrgId() {
		return this.fixOrgId;
	}

	public void setFixOrgId(String fixOrgId) {
		this.fixOrgId = fixOrgId;
	}

	public String getFixOrgName() {
		return this.fixOrgName;
	}

	public void setFixOrgName(String fixOrgName) {
		this.fixOrgName = fixOrgName;
	}

	public String getFixTlrName() {
		return this.fixTlrName;
	}

	public void setFixTlrName(String fixTlrName) {
		this.fixTlrName = fixTlrName;
	}

	public String getFixTlrNo() {
		return this.fixTlrNo;
	}

	public void setFixTlrNo(String fixTlrNo) {
		this.fixTlrNo = fixTlrNo;
	}

	public String getDepoFlag() {
		return this.depoFlag;
	}

	public void setDepoFlag(String depoFlag) {
		this.depoFlag = depoFlag;
	}

	public String getDepoAmt() {
		return this.depoAmt;
	}

	public void setDepoAmt(String depoAmt) {
		this.depoAmt = depoAmt;
	}

	public String getOthSvrId() {
		return this.othSvrId;
	}

	public void setOthSvrId(String othSvrId) {
		this.othSvrId = othSvrId;
	}

	public String getOthSvrName() {
		return this.othSvrName;
	}

	public void setOthSvrName(String othSvrName) {
		this.othSvrName = othSvrName;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getExcuRange() {
		return this.excuRange;
	}

	public void setExcuRange(String excuRange) {
		this.excuRange = excuRange;
	}

	public String getStationNo() {
		return this.stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getMisc1() {
		return this.misc1;
	}

	public void setMisc1(String misc1) {
		this.misc1 = misc1;
	}

	public String getMisc2() {
		return this.misc2;
	}

	public void setMisc2(String misc2) {
		this.misc2 = misc2;
	}

	public String getMiscFlag1() {
		return this.miscFlag1;
	}

	public void setMiscFlag1(String miscFlag1) {
		this.miscFlag1 = miscFlag1;
	}

	public String getMiscFlag2() {
		return this.miscFlag2;
	}

	public void setMiscFlag2(String miscFlag2) {
		this.miscFlag2 = miscFlag2;
	}

	public String getMiscDate() {
		return this.miscDate;
	}

	public void setMiscDate(String miscDate) {
		this.miscDate = miscDate;
	}

	public String getLastAudTlr() {
		return this.lastAudTlr;
	}

	public void setLastAudTlr(String lastAudTlr) {
		this.lastAudTlr = lastAudTlr;
	}

	public String getLastAudDateTime() {
		return this.lastAudDateTime;
	}

	public void setLastAudDateTime(String lastAudDateTime) {
		this.lastAudDateTime = lastAudDateTime;
	}

	public String getCrtTlr() {
		return this.crtTlr;
	}

	public void setCrtTlr(String crtTlr) {
		this.crtTlr = crtTlr;
	}

	public String getCrtDateTime() {
		return this.crtDateTime;
	}

	public void setCrtDateTime(String crtDateTime) {
		this.crtDateTime = crtDateTime;
	}

	public String getLastUpdTlr() {
		return this.lastUpdTlr;
	}

	public void setLastUpdTlr(String lastUpdTlr) {
		this.lastUpdTlr = lastUpdTlr;
	}

	public String getLastUpdDateTime() {
		return this.lastUpdDateTime;
	}

	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}

	public String getUpMchtId() {
		return upMchtId;
	}

	public void setUpMchtId(String upMchtId) {
		this.upMchtId = upMchtId;
	}

	public String getIsVirPos() {
		return isVirPos;
	}

	public void setIsVirPos(String isVirPos) {
		this.isVirPos = isVirPos;
	}

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

}