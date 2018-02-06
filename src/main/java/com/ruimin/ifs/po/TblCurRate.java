package com.ruimin.ifs.po;

import java.math.BigDecimal;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("ifs_cur_rate")
public class TblCurRate {
	@Id
	private String id;
	private String curcd;
	private String currrateDate;
	private String lastUpdDate;
	private String lastUpdTlr;
	private String toCurcd;
	private BigDecimal buyRate;
	private BigDecimal sellRate;
	private BigDecimal exchgRate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCurcd() {
		return curcd;
	}

	public void setCurcd(String curcd) {
		this.curcd = curcd;
	}

	public String getCurrrateDate() {
		return currrateDate;
	}

	public void setCurrrateDate(String currrateDate) {
		this.currrateDate = currrateDate;
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

	public String getToCurcd() {
		return toCurcd;
	}

	public void setToCurcd(String toCurcd) {
		this.toCurcd = toCurcd;
	}

	public BigDecimal getBuyRate() {
		return buyRate;
	}

	public void setBuyRate(BigDecimal buyRate) {
		this.buyRate = buyRate;
	}

	public BigDecimal getSellRate() {
		return sellRate;
	}

	public void setSellRate(BigDecimal sellRate) {
		this.sellRate = sellRate;
	}

	public BigDecimal getExchgRate() {
		return exchgRate;
	}

	public void setExchgRate(BigDecimal exchgRate) {
		this.exchgRate = exchgRate;
	}

}
