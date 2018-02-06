package com.ruimin.ifs.po;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("ifs_cur_inf")
public class TblCurInf {
	@Id
	private String curcd;
	private String cnname;
	private String st;
	private String isLock;
	private String isDel;
	private String createDate;
	private String lastUpdDate;
	private String lastUpdTlr;
	private Integer showseq;
	private String isusd;
	private String enname;
	private String cursymbol;
	private String createTlr;
	private String baseUnit;
	private String minUnit;
	private String curEdicd;
	private String curEdiname;
	private String curno;
	private String dratedays;

	public String getDratedays() {
		return dratedays;
	}

	public void setDratedays(String dratedays) {
		this.dratedays = dratedays;
	}

	public String getCurcd() {
		return curcd;
	}

	public void setCurcd(String curcd) {
		this.curcd = curcd;
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastUpdDate() {
		return lastUpdDate;
	}

	public void setLastUpdDate(String lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}

	public String getLastUpdTlr() {
		return lastUpdTlr;
	}

	public void setLastUpdTlr(String lastUpdTlr) {
		this.lastUpdTlr = lastUpdTlr;
	}

	public Integer getShowseq() {
		return showseq;
	}

	public void setShowseq(Integer showseq) {
		this.showseq = showseq;
	}

	public String getIsusd() {
		return isusd;
	}

	public void setIsusd(String isusd) {
		this.isusd = isusd;
	}

	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	public String getCursymbol() {
		return cursymbol;
	}

	public void setCursymbol(String cursymbol) {
		this.cursymbol = cursymbol;
	}

	public String getCreateTlr() {
		return createTlr;
	}

	public void setCreateTlr(String createTlr) {
		this.createTlr = createTlr;
	}

	public String getBaseUnit() {
		return baseUnit;
	}

	public void setBaseUnit(String baseUnit) {
		this.baseUnit = baseUnit;
	}

	public String getMinUnit() {
		return minUnit;
	}

	public void setMinUnit(String minUnit) {
		this.minUnit = minUnit;
	}

	public String getCurEdicd() {
		return curEdicd;
	}

	public void setCurEdicd(String curEdicd) {
		this.curEdicd = curEdicd;
	}

	public String getCurEdiname() {
		return curEdiname;
	}

	public void setCurEdiname(String curEdiname) {
		this.curEdiname = curEdiname;
	}

	public String getCurno() {
		return curno;
	}

	public void setCurno(String curno) {
		this.curno = curno;
	}

}
