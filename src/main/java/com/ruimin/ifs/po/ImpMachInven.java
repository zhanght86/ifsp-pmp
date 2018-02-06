/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: ImpMachInven.java
 * Author:   xw
 * Date:     2015年8月25日 下午7:16:23
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
@Table("IMP_MACH_INVEN")
public class ImpMachInven implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String machInvenId;
	private String machType;
	private String invenStat;
	private String batchNo;
	private String machId;
	private String termFacyId;
	private String termFacyName;
	private String storTlr;
	private String storDateTime;
	private String reciTlr;
	private String reciDateTime;
	private String backTlr;
	private String backDateTime;
	private String invalidTlr;
	private String invalidDate;
	private String signTlr;
	private String signDateTime;
	private String misc;
	private String lastUpdTlr;
	private String lastUpdDateTime;
	private String termModlId;
	private String propType;
	private String propOrgName;

	/**
	 * @return the machInvenId
	 */
	public String getMachInvenId() {
		return machInvenId;
	}

	/**
	 * @param machInvenId
	 *            the machInvenId to set
	 */
	public void setMachInvenId(String machInvenId) {
		this.machInvenId = machInvenId;
	}

	/**
	 * @return the machType
	 */
	public String getMachType() {
		return machType;
	}

	/**
	 * @param machType
	 *            the machType to set
	 */
	public void setMachType(String machType) {
		this.machType = machType;
	}

	/**
	 * @return the invenStat
	 */
	public String getInvenStat() {
		return invenStat;
	}

	/**
	 * @param invenStat
	 *            the invenStat to set
	 */
	public void setInvenStat(String invenStat) {
		this.invenStat = invenStat;
	}

	/**
	 * @return the batchNo
	 */
	public String getBatchNo() {
		return batchNo;
	}

	/**
	 * @param batchNo
	 *            the batchNo to set
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
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
	 * @return the termFacyId
	 */
	public String getTermFacyId() {
		return termFacyId;
	}

	/**
	 * @param termFacyId
	 *            the termFacyId to set
	 */
	public void setTermFacyId(String termFacyId) {
		this.termFacyId = termFacyId;
	}

	/**
	 * @return the termFacyName
	 */
	public String getTermFacyName() {
		return termFacyName;
	}

	/**
	 * @param termFacyName
	 *            the termFacyName to set
	 */
	public void setTermFacyName(String termFacyName) {
		this.termFacyName = termFacyName;
	}

	/**
	 * @return the storTlr
	 */
	public String getStorTlr() {
		return storTlr;
	}

	/**
	 * @param storTlr
	 *            the storTlr to set
	 */
	public void setStorTlr(String storTlr) {
		this.storTlr = storTlr;
	}

	/**
	 * @return the storDateTime
	 */
	public String getStorDateTime() {
		return storDateTime;
	}

	/**
	 * @param storDateTime
	 *            the storDateTime to set
	 */
	public void setStorDateTime(String storDateTime) {
		this.storDateTime = storDateTime;
	}

	/**
	 * @return the reciTlr
	 */
	public String getReciTlr() {
		return reciTlr;
	}

	/**
	 * @param reciTlr
	 *            the reciTlr to set
	 */
	public void setReciTlr(String reciTlr) {
		this.reciTlr = reciTlr;
	}

	/**
	 * @return the reciDateTime
	 */
	public String getReciDateTime() {
		return reciDateTime;
	}

	/**
	 * @param reciDateTime
	 *            the reciDateTime to set
	 */
	public void setReciDateTime(String reciDateTime) {
		this.reciDateTime = reciDateTime;
	}

	/**
	 * @return the backTlr
	 */
	public String getBackTlr() {
		return backTlr;
	}

	/**
	 * @param backTlr
	 *            the backTlr to set
	 */
	public void setBackTlr(String backTlr) {
		this.backTlr = backTlr;
	}

	/**
	 * @return the backDateTime
	 */
	public String getBackDateTime() {
		return backDateTime;
	}

	/**
	 * @param backDateTime
	 *            the backDateTime to set
	 */
	public void setBackDateTime(String backDateTime) {
		this.backDateTime = backDateTime;
	}

	/**
	 * @return the invalidTlr
	 */
	public String getInvalidTlr() {
		return invalidTlr;
	}

	/**
	 * @param invalidTlr
	 *            the invalidTlr to set
	 */
	public void setInvalidTlr(String invalidTlr) {
		this.invalidTlr = invalidTlr;
	}

	/**
	 * 
	 * /**
	 * 
	 * @return the signTlr
	 */
	public String getSignTlr() {
		return signTlr;
	}

	/**
	 * @return the invalidDate
	 */
	public String getInvalidDate() {
		return invalidDate;
	}

	/**
	 * @param invalidDate
	 *            the invalidDate to set
	 */
	public void setInvalidDate(String invalidDate) {
		this.invalidDate = invalidDate;
	}

	/**
	 * @param signTlr
	 *            the signTlr to set
	 */
	public void setSignTlr(String signTlr) {
		this.signTlr = signTlr;
	}

	/**
	 * @return the signDateTime
	 */
	public String getSignDateTime() {
		return signDateTime;
	}

	/**
	 * @param signDateTime
	 *            the signDateTime to set
	 */
	public void setSignDateTime(String signDateTime) {
		this.signDateTime = signDateTime;
	}

	/**
	 * @return the misc
	 */
	public String getMisc() {
		return misc;
	}

	/**
	 * @param misc
	 *            the misc to set
	 */
	public void setMisc(String misc) {
		this.misc = misc;
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

	public String getTermModlId() {
		return termModlId;
	}

	public void setTermModlId(String termModlId) {
		this.termModlId = termModlId;
	}

	public String getPropType() {
		return propType;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}

	public String getPropOrgName() {
		return propOrgName;
	}

	public void setPropOrgName(String propOrgName) {
		this.propOrgName = propOrgName;
	}

	/**
	 * @param machInvenId
	 * @param machType
	 * @param invenStat
	 * @param batchNo
	 * @param machId
	 * @param termFacyId
	 * @param termFacyName
	 * @param storTlr
	 * @param storDateTime
	 * @param reciTlr
	 * @param reciDateTime
	 * @param backTlr
	 * @param backDateTime
	 * @param invalidTlr
	 * @param invalidDateTime
	 * @param signTlr
	 * @param signDateTime
	 * @param misc
	 * @param lastUpdTlr
	 * @param lastUpdDateTime
	 * @param termModlId
	 * @param propType
	 * @param propOrgName
	 */
	public ImpMachInven(String machInvenId, String machType, String invenStat, String batchNo, String machId,
			String termFacyId, String termFacyName, String storTlr, String storDateTime, String reciTlr,
			String reciDateTime, String backTlr, String backDateTime, String invalidTlr, String invalidDate,
			String signTlr, String signDateTime, String misc, String lastUpdTlr, String lastUpdDateTime,
			String termModlId, String propType, String propOrgName) {
		super();
		this.machInvenId = machInvenId;
		this.machType = machType;
		this.invenStat = invenStat;
		this.batchNo = batchNo;
		this.machId = machId;
		this.termFacyId = termFacyId;
		this.termFacyName = termFacyName;
		this.storTlr = storTlr;
		this.storDateTime = storDateTime;
		this.reciTlr = reciTlr;
		this.reciDateTime = reciDateTime;
		this.backTlr = backTlr;
		this.backDateTime = backDateTime;
		this.invalidTlr = invalidTlr;
		this.invalidDate = invalidDate;
		this.signTlr = signTlr;
		this.signDateTime = signDateTime;
		this.misc = misc;
		this.lastUpdTlr = lastUpdTlr;
		this.lastUpdDateTime = lastUpdDateTime;
		this.termModlId = termModlId;
		this.propType = propType;
		this.propOrgName = propOrgName;
	}

	/**
	 * 
	 */
	public ImpMachInven() {
		super();
	}

}
