package com.ruimin.ifs.pmp.chnlMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 
 * 支付通道主商户信息表-实体类
 * 
 * @author zhangjc
 *
 */
@Table("PAGY_MAIN_MCHT_INFO")
public class PagyMainMchtInfo {
	@Id
	/** 变量 mainMchtNo . */
	private String mainMchtNo;
	/** 变量 pagyNo . */
	private String pagyNo;
	/** 变量 mainMchtPublicNo . */
	private String mainMchtPublicNo;
	/** 变量 mainMchtName . */
	private String mainMchtName;
	/** 变量 mainMchtAcsType . */
	private String mainMchtAcsType;
	/** 变量 mainMchtSetlModel . */
	private String mainMchtSetlModel;
	/** 变量 mainMchtSetlCycleType . */
	private String mainMchtSetlCycleType;
	/** 变量 mainMchtSetlCycle . */
	private String mainMchtSetlCycle;
	/** 变量 mainMchtSetlTm . */
	private String mainMchtSetlTm;
	/** 变量 mainMchtSetlAccno . */
	private String mainMchtSetlAccno;
	/** 变量 mainMchtMccCode . */
	private String mainMchtMccCode;
	/** 变量 mainMchtMccName . */
	private String mainMchtMccName;
	/** 变量 mainMchtMccSubCode . */
	private String mainMchtMccSubCode;
	/** 变量 mainMchtMccSubName . */
	private String mainMchtMccSubName;
	/** 变量 mainMchtRateCode . */
	private String mainMchtRateCode;
	/** 变量 mainMchtStat . */
	private String mainMchtStat;
	/** 变量 crtTlr . */
	private String crtTlr;
	/** 变量 crtDateTime . */
	private String crtDateTime;
	/** 变量 lastUpdTlr . */
	private String lastUpdTlr;
	/** 变量 lastUpdDateTime . */
	private String lastUpdDateTime;

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

	public String getMainMchtPublicNo() {
		return mainMchtPublicNo;
	}

	public void setMainMchtPublicNo(String mainMchtPublicNo) {
		this.mainMchtPublicNo = mainMchtPublicNo;
	}

	public String getMainMchtName() {
		return mainMchtName;
	}

	public void setMainMchtName(String mainMchtName) {
		this.mainMchtName = mainMchtName;
	}

	public String getMainMchtAcsType() {
		return mainMchtAcsType;
	}

	public void setMainMchtAcsType(String mainMchtAcsType) {
		this.mainMchtAcsType = mainMchtAcsType;
	}

	public String getMainMchtSetlModel() {
		return mainMchtSetlModel;
	}

	public void setMainMchtSetlModel(String mainMchtSetlModel) {
		this.mainMchtSetlModel = mainMchtSetlModel;
	}

	public String getMainMchtSetlCycleType() {
		return mainMchtSetlCycleType;
	}

	public void setMainMchtSetlCycleType(String mainMchtSetlCycleType) {
		this.mainMchtSetlCycleType = mainMchtSetlCycleType;
	}

	public String getMainMchtSetlCycle() {
		return mainMchtSetlCycle;
	}

	public void setMainMchtSetlCycle(String mainMchtSetlCycle) {
		this.mainMchtSetlCycle = mainMchtSetlCycle;
	}

	public String getMainMchtSetlTm() {
		return mainMchtSetlTm;
	}

	public void setMainMchtSetlTm(String mainMchtSetlTm) {
		this.mainMchtSetlTm = mainMchtSetlTm;
	}

	public String getMainMchtSetlAccno() {
		return mainMchtSetlAccno;
	}

	public void setMainMchtSetlAccno(String mainMchtSetlAccno) {
		this.mainMchtSetlAccno = mainMchtSetlAccno;
	}

	public String getMainMchtMccCode() {
		return mainMchtMccCode;
	}

	public void setMainMchtMccCode(String mainMchtMccCode) {
		this.mainMchtMccCode = mainMchtMccCode;
	}

	public String getMainMchtMccName() {
		return mainMchtMccName;
	}

	public void setMainMchtMccName(String mainMchtMccName) {
		this.mainMchtMccName = mainMchtMccName;
	}

	public String getMainMchtMccSubCode() {
		return mainMchtMccSubCode;
	}

	public void setMainMchtMccSubCode(String mainMchtMccSubCode) {
		this.mainMchtMccSubCode = mainMchtMccSubCode;
	}

	public String getMainMchtMccSubName() {
		return mainMchtMccSubName;
	}

	public void setMainMchtMccSubName(String mainMchtMccSubName) {
		this.mainMchtMccSubName = mainMchtMccSubName;
	}

	public String getMainMchtRateCode() {
		return mainMchtRateCode;
	}

	public void setMainMchtRateCode(String mainMchtRateCode) {
		this.mainMchtRateCode = mainMchtRateCode;
	}

	public String getMainMchtStat() {
		return mainMchtStat;
	}

	public void setMainMchtStat(String mainMchtStat) {
		this.mainMchtStat = mainMchtStat;
	}

	public String getCrtTlr() {
		return crtTlr;
	}

	public void setCrtTlr(String crtTlr) {
		this.crtTlr = crtTlr;
	}

	public String getCrtDateTime() {
		return crtDateTime;
	}

	public void setCrtDateTime(String crtDateTime) {
		this.crtDateTime = crtDateTime;
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