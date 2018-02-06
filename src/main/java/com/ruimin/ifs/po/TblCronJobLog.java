package com.ruimin.ifs.po;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("ifs_cron_job_log")
public class TblCronJobLog {
	@Id
	private String id;
	private String jobno;
	private String subProceFunction;
	private String excuteTime;
	private String excuteResult;
	private String excuteOwn;
	private String failFlag;
	private String sucFlag;
	private String exceptionMsg;
	private String endExcuteFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobno() {
		return jobno;
	}

	public void setJobno(String jobno) {
		this.jobno = jobno;
	}

	public String getSubProceFunction() {
		return subProceFunction;
	}

	public void setSubProceFunction(String subProceFunction) {
		this.subProceFunction = subProceFunction;
	}

	public String getExcuteTime() {
		return excuteTime;
	}

	public void setExcuteTime(String excuteTime) {
		this.excuteTime = excuteTime;
	}

	public String getExcuteResult() {
		return excuteResult;
	}

	public void setExcuteResult(String excuteResult) {
		this.excuteResult = excuteResult;
	}

	public String getExcuteOwn() {
		return excuteOwn;
	}

	public void setExcuteOwn(String excuteOwn) {
		this.excuteOwn = excuteOwn;
	}

	public String getFailFlag() {
		return failFlag;
	}

	public void setFailFlag(String failFlag) {
		this.failFlag = failFlag;
	}

	public String getSucFlag() {
		return sucFlag;
	}

	public void setSucFlag(String sucFlag) {
		this.sucFlag = sucFlag;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public String getEndExcuteFlag() {
		return endExcuteFlag;
	}

	public void setEndExcuteFlag(String endExcuteFlag) {
		this.endExcuteFlag = endExcuteFlag;
	}

}
