package com.ruimin.ifs.po;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("IFS_BAT_PROC_STATUS")
public class TblBhProcStatus {
	@Id
	private int id;
	private String bhdate;
	private int jobno;
	private int step;
	private int subStep;
	private String processFunction;
	private int processid;
	private String startTime;
	private String endTime;
	private String subFlag;
	private String status;
	private String branchlist;
	private String desc1;
	private String desc2;
	private String timestamps;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setBhdate(String bhdate) {
		this.bhdate = bhdate;
	}

	public String getBhdate() {
		return bhdate;
	}

	public void setJobno(int jobno) {
		this.jobno = jobno;
	}

	public int getJobno() {
		return jobno;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getStep() {
		return step;
	}

	public void setSubStep(int subStep) {
		this.subStep = subStep;
	}

	public int getSubStep() {
		return subStep;
	}

	public void setProcessFunction(String processFunction) {
		this.processFunction = processFunction;
	}

	public String getProcessFunction() {
		return processFunction;
	}

	public void setProcessid(int processid) {
		this.processid = processid;
	}

	public int getProcessid() {
		return processid;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setSubFlag(String subFlag) {
		this.subFlag = subFlag;
	}

	public String getSubFlag() {
		return subFlag;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setBranchlist(String branchlist) {
		this.branchlist = branchlist;
	}

	public String getBranchlist() {
		return branchlist;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public String getDesc1() {
		return desc1;
	}

	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}

	public String getDesc2() {
		return desc2;
	}

	public void setTimestamps(String timestamps) {
		this.timestamps = timestamps;
	}

	public String getTimestamps() {
		return timestamps;
	}
}
