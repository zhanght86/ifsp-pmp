package com.ruimin.ifs.mng.process.bean;

import com.ruimin.ifs.gov.util.StringUtils;

public class TblCronJobVO {
	private String id; // 任务id
	private Integer jobno; // 任务编号
	private String processFunction; // 执行任务类
	private String processParam; // 任务参数
	private Integer maxproc; //
	private String runtime; // 执行方式 90-每日 97-每月某日 9N-不运行
	private Integer daysOfMonth; // 每月某日
	private Double repeatTime; // 执行间隔
	private Integer repeatCnt; // 日执行次数
	private String startTime; // 开始时间
	private String endTime; // 结束时间 格式HHmmss
	private String lastRunTime; // 最后的执行时间
	private String dueTime; //
	private String sucFlag; //
	private String failFlag; //
	private String auto; //
	private String lockOwn; //
	private String desc0; // 任务描述
	private String desc1; //
	private String desc2; //
	private String timestamps; // 时间戳
	private String dualcontrolLockstatus; // 是否锁定 0-解锁, 1-锁定
	private Boolean isRunning; // true-运行中, false-已停止

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getJobno() {
		return jobno;
	}

	public void setJobno(Integer jobno) {
		this.jobno = jobno;
	}

	public String getProcessFunction() {
		return processFunction;
	}

	public void setProcessFunction(String processFunction) {
		this.processFunction = processFunction;
	}

	public String getProcessParam() {
		return processParam;
	}

	public void setProcessParam(String processParam) {
		this.processParam = processParam;
	}

	public Integer getMaxproc() {
		return maxproc;
	}

	public void setMaxproc(Integer maxproc) {
		this.maxproc = maxproc;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public Integer getDaysOfMonth() {
		return daysOfMonth;
	}

	public void setDaysOfMonth(Integer daysOfMonth) {
		this.daysOfMonth = daysOfMonth;
	}

	public Double getRepeatTime() {
		return repeatTime;
	}

	public void setRepeatTime(Double repeatTime) {
		this.repeatTime = repeatTime;
	}

	public Integer getRepeatCnt() {
		return repeatCnt;
	}

	public void setRepeatCnt(Integer repeatCnt) {
		this.repeatCnt = repeatCnt;
	}

	public String getStartTime() {
		if (StringUtils.isBlank(startTime)) {
			return "";
		}
		return startTime.substring(0, 2) + ":" + startTime.substring(2, 4) + ":" + startTime.substring(4);
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		if (StringUtils.isBlank(endTime)) {
			return "";
		}
		return endTime.substring(0, 2) + ":" + endTime.substring(2, 4) + ":" + endTime.substring(4);
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLastRunTime() {
		return lastRunTime;
	}

	public void setLastRunTime(String lastRunTime) {
		this.lastRunTime = lastRunTime;
	}

	public String getDueTime() {
		return dueTime;
	}

	public void setDueTime(String dueTime) {
		this.dueTime = dueTime;
	}

	public String getSucFlag() {
		return sucFlag;
	}

	public void setSucFlag(String sucFlag) {
		this.sucFlag = sucFlag;
	}

	public String getFailFlag() {
		return failFlag;
	}

	public void setFailFlag(String failFlag) {
		this.failFlag = failFlag;
	}

	public String getAuto() {
		return auto;
	}

	public void setAuto(String auto) {
		this.auto = auto;
	}

	public String getLockOwn() {
		return lockOwn;
	}

	public void setLockOwn(String lockOwn) {
		this.lockOwn = lockOwn;
	}

	public String getDesc0() {
		return desc0;
	}

	public void setDesc0(String desc0) {
		this.desc0 = desc0;
	}

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public String getDesc2() {
		return desc2;
	}

	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}

	public String getTimestamps() {
		return timestamps;
	}

	public void setTimestamps(String timestamps) {
		this.timestamps = timestamps;
	}

	public String getDualcontrolLockstatus() {
		return dualcontrolLockstatus;
	}

	public void setDualcontrolLockstatus(String dualcontrolLockstatus) {
		this.dualcontrolLockstatus = dualcontrolLockstatus;
	}

	public Boolean getIsRunning() {
		return isRunning;
	}

	public void setIsRunning(Boolean isRunning) {
		this.isRunning = isRunning;
	}

	public TblCronJobVO(String id, Integer jobno, String processFunction, String processParam, Integer maxproc,
			String runtime, Integer daysOfMonth, Double repeatTime, Integer repeatCnt, String startTime, String endTime,
			String lastRunTime, String dueTime, String sucFlag, String failFlag, String auto, String lockOwn,
			String desc0, String desc1, String desc2, String timestamps, String dualcontrolLockstatus,
			Boolean isRunning) {
		super();
		this.id = id;
		this.jobno = jobno;
		this.processFunction = processFunction;
		this.processParam = processParam;
		this.maxproc = maxproc;
		this.runtime = runtime;
		this.daysOfMonth = daysOfMonth;
		this.repeatTime = repeatTime;
		this.repeatCnt = repeatCnt;
		this.startTime = startTime;
		this.endTime = endTime;
		this.lastRunTime = lastRunTime;
		this.dueTime = dueTime;
		this.sucFlag = sucFlag;
		this.failFlag = failFlag;
		this.auto = auto;
		this.lockOwn = lockOwn;
		this.desc0 = desc0;
		this.desc1 = desc1;
		this.desc2 = desc2;
		this.timestamps = timestamps;
		this.dualcontrolLockstatus = dualcontrolLockstatus;
		this.isRunning = isRunning;
	}

	public TblCronJobVO() {
		super();
	}

}
