package com.ruimin.ifs.po;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("IFS_CAL_INF")
public class TblCalInf {
	@Id
	private String id;
	private int year;
	private String holidayDef;
	private String lastUpdOperId;
	private String lastUpdTime;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	public void setHolidayDef(String holidayDef) {
		this.holidayDef = holidayDef;
	}

	public String getHolidayDef() {
		return holidayDef;
	}

	public void setLastUpdOperId(String lastUpdOperId) {
		this.lastUpdOperId = lastUpdOperId;
	}

	public String getLastUpdOperId() {
		return lastUpdOperId;
	}

	public void setLastUpdTime(String lastUpdTime) {
		this.lastUpdTime = lastUpdTime;
	}

	public String getLastUpdTime() {
		return lastUpdTime;
	}
}
