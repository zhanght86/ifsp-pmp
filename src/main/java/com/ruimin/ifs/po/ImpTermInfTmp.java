/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: ImpTermInfTmp.java
 * Author:   xw
 * Date:     2015年8月24日 上午10:52:29
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.ruimin.ifs.po;

import java.io.Serializable;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author xw
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Table("IMP_TERM_INF_TMP")
public class ImpTermInfTmp implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 1L;
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
	private String auditFlag;

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
	 * @return the termModlId
	 */
	public String getTermModlId() {
		return termModlId;
	}

	/**
	 * @param termModlId
	 *            the termModlId to set
	 */
	public void setTermModlId(String termModlId) {
		this.termModlId = termModlId;
	}

	/**
	 * @return the termSoftVer
	 */
	public String getTermSoftVer() {
		return termSoftVer;
	}

	/**
	 * @param termSoftVer
	 *            the termSoftVer to set
	 */
	public void setTermSoftVer(String termSoftVer) {
		this.termSoftVer = termSoftVer;
	}

	/**
	 * @return the parmDownFalg
	 */
	public String getParmDownFlag() {
		return parmDownFlag;
	}

	/**
	 * @param parmDownFalg
	 *            the parmDownFalg to set
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
	 * @return the termPara1
	 */
	public String getTermPara1() {
		return termPara1;
	}

	/**
	 * @param termPara1
	 *            the termPara1 to set
	 */
	public void setTermPara1(String termPara1) {
		this.termPara1 = termPara1;
	}

	/**
	 * @return the keyDownFlag
	 */
	public String getKeyDownFlag() {
		return keyDownFlag;
	}

	/**
	 * @param keyDownFlag
	 *            the keyDownFlag to set
	 */
	public void setKeyDownFlag(String keyDownFlag) {
		this.keyDownFlag = keyDownFlag;
	}

	/**
	 * @return the propType
	 */
	public String getPropType() {
		return propType;
	}

	/**
	 * @param propType
	 *            the propType to set
	 */
	public void setPropType(String propType) {
		this.propType = propType;
	}

	/**
	 * @return the propOrgName
	 */
	public String getPropOrgName() {
		return propOrgName;
	}

	/**
	 * @param propOrgName
	 *            the propOrgName to set
	 */
	public void setPropOrgName(String propOrgName) {
		this.propOrgName = propOrgName;
	}

	/**
	 * @return the termBrh
	 */
	public String getTermBrh() {
		return termBrh;
	}

	/**
	 * @param termBrh
	 *            the termBrh to set
	 */
	public void setTermBrh(String termBrh) {
		this.termBrh = termBrh;
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
	 * @return the fixOrgId
	 */
	public String getFixOrgId() {
		return fixOrgId;
	}

	/**
	 * @param fixOrgId
	 *            the fixOrgId to set
	 */
	public void setFixOrgId(String fixOrgId) {
		this.fixOrgId = fixOrgId;
	}

	/**
	 * @return the fixOrgName
	 */
	public String getFixOrgName() {
		return fixOrgName;
	}

	/**
	 * @param fixOrgName
	 *            the fixOrgName to set
	 */
	public void setFixOrgName(String fixOrgName) {
		this.fixOrgName = fixOrgName;
	}

	/**
	 * @return the fixTlrName
	 */
	public String getFixTlrName() {
		return fixTlrName;
	}

	/**
	 * @param fixTlrName
	 *            the fixTlrName to set
	 */
	public void setFixTlrName(String fixTlrName) {
		this.fixTlrName = fixTlrName;
	}

	/**
	 * @return the fixTlrNo
	 */
	public String getFixTlrNo() {
		return fixTlrNo;
	}

	/**
	 * @param fixTlrNo
	 *            the fixTlrNo to set
	 */
	public void setFixTlrNo(String fixTlrNo) {
		this.fixTlrNo = fixTlrNo;
	}

	/**
	 * @return the depoFlag
	 */
	public String getDepoFlag() {
		return depoFlag;
	}

	/**
	 * @param depoFlag
	 *            the depoFlag to set
	 */
	public void setDepoFlag(String depoFlag) {
		this.depoFlag = depoFlag;
	}

	/**
	 * @return the depoAmt
	 */
	public String getDepoAmt() {
		return depoAmt;
	}

	/**
	 * @param depoAmt
	 *            the depoAmt to set
	 */
	public void setDepoAmt(String depoAmt) {
		this.depoAmt = depoAmt;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the excuRange
	 */
	public String getExcuRange() {
		return excuRange;
	}

	/**
	 * @param excuRange
	 *            the excuRange to set
	 */
	public void setExcuRange(String excuRange) {
		this.excuRange = excuRange;
	}

	/**
	 * @return the stationNo
	 */
	public String getStationNo() {
		return stationNo;
	}

	/**
	 * @param stationNo
	 *            the stationNo to set
	 */
	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	/**
	 * @return the misc1
	 */
	public String getMisc1() {
		return misc1;
	}

	/**
	 * @param misc1
	 *            the misc1 to set
	 */
	public void setMisc1(String misc1) {
		this.misc1 = misc1;
	}

	/**
	 * @return the misc2
	 */
	public String getMisc2() {
		return misc2;
	}

	/**
	 * @param misc2
	 *            the misc2 to set
	 */
	public void setMisc2(String misc2) {
		this.misc2 = misc2;
	}

	/**
	 * @return the miscFlag1
	 */
	public String getMiscFlag1() {
		return miscFlag1;
	}

	/**
	 * @param miscFlag1
	 *            the miscFlag1 to set
	 */
	public void setMiscFlag1(String miscFlag1) {
		this.miscFlag1 = miscFlag1;
	}

	/**
	 * @return the miscFlag2
	 */
	public String getMiscFlag2() {
		return miscFlag2;
	}

	/**
	 * @param miscFlag2
	 *            the miscFlag2 to set
	 */
	public void setMiscFlag2(String miscFlag2) {
		this.miscFlag2 = miscFlag2;
	}

	/**
	 * @return the miscDate
	 */
	public String getMiscDate() {
		return miscDate;
	}

	/**
	 * @param miscDate
	 *            the miscDate to set
	 */
	public void setMiscDate(String miscDate) {
		this.miscDate = miscDate;
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
	 * 
	 */
	public ImpTermInfTmp() {
		super();
	}

	/**
	 * @return the icDownFlag
	 */
	public String getIcDownFlag() {
		return icDownFlag;
	}

	/**
	 * @param icDownFlag
	 *            the icDownFlag to set
	 */
	public void setIcDownFlag(String icDownFlag) {
		this.icDownFlag = icDownFlag;
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
	 * @return the virMchtId
	 */
	public String getUpMchtId() {
		return upMchtId;
	}

	/**
	 * @param virMchtId
	 *            the virMchtId to set
	 */
	public void setUpMchtId(String upMchtId) {
		this.upMchtId = upMchtId;
	}

	/**
	 * @return the isVirPos
	 */
	public String getIsVirPos() {
		return isVirPos;
	}

	/**
	 * @param isVirPos
	 *            the isVirPos to set
	 */
	public void setIsVirPos(String isVirPos) {
		this.isVirPos = isVirPos;
	}

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public String getOthSvrId() {
		return othSvrId;
	}

	public void setOthSvrId(String othSvrId) {
		this.othSvrId = othSvrId;
	}

	public String getOthSvrName() {
		return othSvrName;
	}

	public void setOthSvrName(String othSvrName) {
		this.othSvrName = othSvrName;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param termId
	 * @param mchtId
	 * @param termStat
	 * @param termType
	 * @param termSignStat
	 * @param termModlId
	 * @param termSoftVer
	 * @param parmDownFlag
	 * @param termParm
	 * @param termPara1
	 * @param keyDownFlag
	 * @param icDownFlag
	 * @param propType
	 * @param propOrgName
	 * @param icFlag
	 * @param termBrh
	 * @param termBatchNo
	 * @param termBatchDate
	 * @param bindTelNo
	 * @param bindTelNo1
	 * @param termInstAdrr
	 * @param fixOrgId
	 * @param fixOrgName
	 * @param fixTlrName
	 * @param fixTlrNo
	 * @param depoFlag
	 * @param depoAmt
	 * @param othSerId
	 * @param othSerName
	 * @param longitude
	 * @param excuRange
	 * @param stationNo
	 * @param misc1
	 * @param misc2
	 * @param miscFlag1
	 * @param miscFlag2
	 * @param miscDate
	 * @param lastAudTlr
	 * @param lastAudDateTime
	 * @param crtTlr
	 * @param crtDateTime
	 * @param lastUpdTlr
	 * @param lastUpdDateTime
	 */
	public ImpTermInfTmp(String termId, String mchtId, String termStat, String termType, String termSignStat,
			String termModlId, String termSoftVer, String parmDownFlag, String termParm, String termPara1,
			String keyDownFlag, String icDownFlag, String propType, String propOrgName, String icFlag, String termBrh,
			String termBatchNo, String termBatchDate, String bindTelNo, String bindTelNo1, String termInstAdrr,
			String fixOrgId, String fixOrgName, String fixTlrName, String fixTlrNo, String depoFlag, String depoAmt,
			String othSerId, String othSerName, String longitude, String excuRange, String stationNo, String misc1,
			String misc2, String miscFlag1, String miscFlag2, String miscDate, String lastAudTlr,
			String lastAudDateTime, String crtTlr, String crtDateTime, String lastUpdTlr, String lastUpdDateTime) {
		super();
		this.termId = termId;
		this.mchtId = mchtId;
		this.termStat = termStat;
		this.termType = termType;
		this.termSignStat = termSignStat;
		this.termModlId = termModlId;
		this.termSoftVer = termSoftVer;
		this.parmDownFlag = parmDownFlag;
		this.termParm = termParm;
		this.termPara1 = termPara1;
		this.keyDownFlag = keyDownFlag;
		this.icDownFlag = icDownFlag;
		this.propType = propType;
		this.propOrgName = propOrgName;
		this.icFlag = icFlag;
		this.termBrh = termBrh;
		this.termBatchNo = termBatchNo;
		this.termBatchDate = termBatchDate;
		this.bindTelNo = bindTelNo;
		this.bindTelNo1 = bindTelNo1;
		this.termInstAdrr = termInstAdrr;
		this.fixOrgId = fixOrgId;
		this.fixOrgName = fixOrgName;
		this.fixTlrName = fixTlrName;
		this.fixTlrNo = fixTlrNo;
		this.depoFlag = depoFlag;
		this.depoAmt = depoAmt;
		this.longitude = longitude;
		this.excuRange = excuRange;
		this.stationNo = stationNo;
		this.misc1 = misc1;
		this.misc2 = misc2;
		this.miscFlag1 = miscFlag1;
		this.miscFlag2 = miscFlag2;
		this.miscDate = miscDate;
		this.lastAudTlr = lastAudTlr;
		this.lastAudDateTime = lastAudDateTime;
		this.crtTlr = crtTlr;
		this.crtDateTime = crtDateTime;
		this.lastUpdTlr = lastUpdTlr;
		this.lastUpdDateTime = lastUpdDateTime;
	}

}
