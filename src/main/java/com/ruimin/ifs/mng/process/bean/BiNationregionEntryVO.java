package com.ruimin.ifs.mng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("ifs_nation_inf")
public class BiNationregionEntryVO {
	@Id
	private String nationregionCode; // 国家/地区代码"
	private String chinaName; // 中文全称"
	private String nationregionNumber; // 国家/地区数字代码"
	private String crtDt = ""; // 创建日期"
	private String lastUpdTms = ""; // 最后更新时间"
	private String lastUpdOper = ""; // 最后操作人"
	private String chinaShortName; // 中文简称"
	private String engName; // 英文全称"
	private String engShortName; // 英文简称"

	public String getNationregionCode() {
		return nationregionCode;
	}

	public void setNationregionCode(String nationregionCode) {
		this.nationregionCode = nationregionCode;
	}

	public String getChinaName() {
		return chinaName;
	}

	public void setChinaName(String chinaName) {
		this.chinaName = chinaName;
	}

	public String getNationregionNumber() {
		return nationregionNumber;
	}

	public void setNationregionNumber(String nationregionNumber) {
		this.nationregionNumber = nationregionNumber;
	}

	public String getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}

	public String getLastUpdTms() {
		return lastUpdTms;
	}

	public void setLastUpdTms(String lastUpdTms) {
		this.lastUpdTms = lastUpdTms;
	}

	public String getLastUpdOper() {
		return lastUpdOper;
	}

	public void setLastUpdOper(String lastUpdOper) {
		this.lastUpdOper = lastUpdOper;
	}

	public String getChinaShortName() {
		return chinaShortName;
	}

	public void setChinaShortName(String chinaShortName) {
		this.chinaShortName = chinaShortName;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getEngShortName() {
		return engShortName;
	}

	public void setEngShortName(String engShortName) {
		this.engShortName = engShortName;
	}

	public BiNationregionEntryVO() {
	}

}
