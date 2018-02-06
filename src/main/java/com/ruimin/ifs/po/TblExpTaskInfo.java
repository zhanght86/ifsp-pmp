package com.ruimin.ifs.po;

import com.ruimin.ifs.framework.process.exp.bean.ExportBean;
import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("ifs_exp_task_inf")
public class TblExpTaskInfo implements ExportBean {

	@Id
	private String tskId;
	private String tskNm;
	private String tskStartTms;
	private String tskStartOp;
	private String tskDesc;
	private String tskParam1;
	private String tskParam2;
	private String tskOwner;
	private String tskEndTms;
	private String tskRunClass;
	private String expFileNm;
	private Long expRcdNum;
	private Long expRcdSumNum;
	private Long expFileSize;
	private String tskStat;

	public String getTskId() {
		return tskId;
	}

	public void setTskId(String tskId) {
		this.tskId = tskId;
	}

	public String getTskNm() {
		return tskNm;
	}

	public void setTskNm(String tskNm) {
		this.tskNm = tskNm;
	}

	public String getTskStartTms() {
		return tskStartTms;
	}

	public void setTskStartTms(String tskStartTms) {
		this.tskStartTms = tskStartTms;
	}

	public String getTskStartOp() {
		return tskStartOp;
	}

	public void setTskStartOp(String tskStartOp) {
		this.tskStartOp = tskStartOp;
	}

	public String getTskDesc() {
		return tskDesc;
	}

	public void setTskDesc(String tskDesc) {
		this.tskDesc = tskDesc;
	}

	public String getTskParam1() {
		return tskParam1;
	}

	public void setTskParam1(String tskParam1) {
		this.tskParam1 = tskParam1;
	}

	public String getTskParam2() {
		return tskParam2;
	}

	public void setTskParam2(String tskParam2) {
		this.tskParam2 = tskParam2;
	}

	public String getTskOwner() {
		return tskOwner;
	}

	public void setTskOwner(String tskOwner) {
		this.tskOwner = tskOwner;
	}

	public String getTskEndTms() {
		return tskEndTms;
	}

	public void setTskEndTms(String tskEndTms) {
		this.tskEndTms = tskEndTms;
	}

	public String getTskRunClass() {
		return tskRunClass;
	}

	public void setTskRunClass(String tskRunClass) {
		this.tskRunClass = tskRunClass;
	}

	public String getExpFileNm() {
		return expFileNm;
	}

	public void setExpFileNm(String expFileNm) {
		this.expFileNm = expFileNm;
	}

	public Long getExpRcdNum() {
		return expRcdNum;
	}

	public void setExpRcdNum(Long expRcdNum) {
		this.expRcdNum = expRcdNum;
	}

	public Long getExpRcdSumNum() {
		return expRcdSumNum;
	}

	public void setExpRcdSumNum(Long expRcdSumNum) {
		this.expRcdSumNum = expRcdSumNum;
	}

	public Long getExpFileSize() {
		return expFileSize;
	}

	public void setExpFileSize(Long expFileSize) {
		this.expFileSize = expFileSize;
	}

	public String getTskStat() {
		return tskStat;
	}

	public void setTskStat(String tskStat) {
		this.tskStat = tskStat;
	}

}
