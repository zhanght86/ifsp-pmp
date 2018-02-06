package com.ruimin.ifs.job;

import java.util.HashMap;

import org.slf4j.Logger;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.framework.quartz.BaseJobDetail;
import com.ruimin.ifs.framework.quartz.bean.JobConstant;

public class TestJob extends BaseJobDetail {
	private Logger logger = SnowLog.getLogger(this.getClass());

	@Override
	public String executeJob(HashMap jobParameters) throws SnowException {
		logger.info("execute job....");
		return JobConstant.JOB_EXECUTE_RESULT_SUCCESS;
	}

	@Override
	public void afterExeSuccess(HashMap jobParameters) throws SnowException {
		logger.info("execute job success process....");
	}

	@Override
	public void afterExeFail(HashMap jobParameters) throws SnowException {
		logger.info("execute job fail process....");
	}

}
