package com.ruimin.ifs.po;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("IFS_BAT_PROC_STEP")
public class TblBhProcStep {
	@Id
	private int id;
	private int jobno;
	private int step;
	private int subStep;
	private String processFunction;
	private String processParam;
	private String processTlrno;
	private String runtime;
	private String subFlag;
	private int maxproc;
	private String tempFlag;
	private String suspend;
	private String auto;
	private String timestamps;
	private String desc0;
	private String desc1;
	private String desc2;
	private String reportFlag;
	private String reportDataFlag;
	private int repeatCnt;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
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

	public void setProcessParam(String processParam) {
		this.processParam = processParam;
	}

	public String getProcessParam() {
		return processParam;
	}

	public void setProcessTlrno(String processTlrno) {
		this.processTlrno = processTlrno;
	}

	public String getProcessTlrno() {
		return processTlrno;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setSubFlag(String subFlag) {
		this.subFlag = subFlag;
	}

	public String getSubFlag() {
		return subFlag;
	}

	public void setMaxproc(int maxproc) {
		this.maxproc = maxproc;
	}

	public int getMaxproc() {
		return maxproc;
	}

	public void setTempFlag(String tempFlag) {
		this.tempFlag = tempFlag;
	}

	public String getTempFlag() {
		return tempFlag;
	}

	public void setSuspend(String suspend) {
		this.suspend = suspend;
	}

	public String getSuspend() {
		return suspend;
	}

	public void setAuto(String auto) {
		this.auto = auto;
	}

	public String getAuto() {
		return auto;
	}

	public void setTimestamps(String timestamps) {
		this.timestamps = timestamps;
	}

	public String getTimestamps() {
		return timestamps;
	}

	public void setDesc0(String desc0) {
		this.desc0 = desc0;
	}

	public String getDesc0() {
		return desc0;
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

	public void setReportFlag(String reportFlag) {
		this.reportFlag = reportFlag;
	}

	public String getReportFlag() {
		return reportFlag;
	}

	public void setReportDataFlag(String reportDataFlag) {
		this.reportDataFlag = reportDataFlag;
	}

	public String getReportDataFlag() {
		return reportDataFlag;
	}

	public void setRepeatCnt(int repeatCnt) {
		this.repeatCnt = repeatCnt;
	}

	public int getRepeatCnt() {
		return repeatCnt;
	}
}
