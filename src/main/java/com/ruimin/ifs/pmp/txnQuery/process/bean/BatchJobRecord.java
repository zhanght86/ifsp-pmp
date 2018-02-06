package com.ruimin.ifs.pmp.txnQuery.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 批量系统批量任务监控表实体对象
 */
@Table("BATCH_JOB_RECROD")
public class BatchJobRecord {
	@Id
	private String JobId;
	@Id
	private String SettleDate;
	@Id
	private String BatchNo;
	private String JobDesc;
	private String BatchModel;
	private String JobStat;
	private String JobResultCode;
	private String JobResult;
	private String JobSpend;
	private String ServerIp;
	private String ServerName;
	private String LogTraceId;
	private String CrtTlr;
	private String CrtDateTime;
	private String LastUpdTlr;
	private String LastUpdDateTime;

	public String getJobId() {
		return JobId;
	}

	public void setJobId(String jobId) {
		JobId = jobId;
	}

	public String getSettleDate() {
		return SettleDate;
	}

	public void setSettleDate(String settleDate) {
		SettleDate = settleDate;
	}

	public String getBatchNo() {
		return BatchNo;
	}

	public void setBatchNo(String batchNo) {
		BatchNo = batchNo;
	}

	public String getJobDesc() {
		return JobDesc;
	}

	public void setJobDesc(String jobDesc) {
		JobDesc = jobDesc;
	}

	public String getLogTraceId() {
		return LogTraceId;
	}

	public void setLogTraceId(String logTraceId) {
		LogTraceId = logTraceId;
	}

	public String getCrtTlr() {
		return CrtTlr;
	}

	public void setCrtTlr(String crtTlr) {
		CrtTlr = crtTlr;
	}

	public String getCrtDateTime() {
		return CrtDateTime;
	}

	public void setCrtDateTime(String crtDateTime) {
		CrtDateTime = crtDateTime;
	}

	public String getLastUpdTlr() {
		return LastUpdTlr;
	}

	public void setLastUpdTlr(String lastUpdTlr) {
		LastUpdTlr = lastUpdTlr;
	}

	public String getLastUpdDateTime() {
		return LastUpdDateTime;
	}

	public void setLastUpdDateTime(String lastUpdDateTime) {
		LastUpdDateTime = lastUpdDateTime;
	}

	public String getBatchModel() {
		return BatchModel;
	}

	public void setBatchModel(String batchModel) {
		BatchModel = batchModel;
	}

	public String getJobStat() {
		return JobStat;
	}

	public void setJobStat(String jobStat) {
		JobStat = jobStat;
	}

	public String getJobResultCode() {
		return JobResultCode;
	}

	public void setJobResultCode(String jobResultCode) {
		JobResultCode = jobResultCode;
	}

	public String getJobResult() {
		return JobResult;
	}

	public void setJobResult(String jobResult) {
		JobResult = jobResult;
	}

	public String getJobSpend() {
		return JobSpend;
	}

	public void setJobSpend(String jobSpend) {
		JobSpend = jobSpend;
	}

	public String getServerIp() {
		return ServerIp;
	}

	public void setServerIp(String serverIp) {
		ServerIp = serverIp;
	}

	public String getServerName() {
		return ServerName;
	}

	public void setServerName(String serverName) {
		ServerName = serverName;
	}

}
