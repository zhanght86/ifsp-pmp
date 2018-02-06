package com.ruimin.ifs.mng.process.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.mng.process.bean.batch.BatchConstant;
import com.ruimin.ifs.mng.process.bean.batch.BatchInfo;
import com.ruimin.ifs.mng.process.bean.batch.BatchStepInfo;
import com.ruimin.ifs.mng.process.bean.batch.Holiday;
import com.ruimin.ifs.po.TblBhProcStatus;
import com.ruimin.ifs.po.TblBhProcStep;

@Service
@SnowDoc(desc = "批量处理")
public class BatchService extends SnowService {
	public static BatchService getService() throws SnowException {
		return ContextUtil.getSingleService(BatchService.class);
	}

	public List<BatchStepInfo> getBatchStepInfoList(String bhDate, String statusCode) throws SnowException {
		if (StringUtils.isBlank(bhDate)) {
			/** 批量日期为空 查询当前批量日期 */
			bhDate = DateUtil.dateToNumber(SessionUserInfo.getSessionUserInfo().getBhDate());
		}

		List<BatchStepInfo> result = new ArrayList<BatchStepInfo>();
		Holiday holiday = new Holiday(bhDate);
		List<Object> stepInfoList = this.getTblBhProcStepList(holiday);

		BatchInfo batchInfo = new BatchInfo();// 总批量信息
		batchInfo.setBhDate(bhDate);
		batchInfo.setStepList(result);
		for (int i = 0; i < stepInfoList.size(); i++) {
			TblBhProcStep step = (TblBhProcStep) stepInfoList.get(i);
			TblBhProcStatus stepStatus = this.findStatusByStep(step, bhDate);
			BatchStepInfo stepBean = new BatchStepInfo();
			stepBean.setJobNo(step.getJobno());
			stepBean.setStepNo(step.getStep());
			stepBean.setSubStepNo(step.getSubStep());
			stepBean.setSuspendCode(step.getSuspend());
			stepBean.setSubStepName(step.getDesc0());
			if (stepStatus != null) {
				stepBean.setStatusCode(stepStatus.getStatus());
				stepBean.setStartTime(stepStatus.getStartTime());
				stepBean.setEndTime(stepStatus.getEndTime());
			}
			stepBean.setBatchInfo(batchInfo);

			if (statusCode == null || statusCode.equals(stepBean.getStatusCode())) {
				result.add(stepBean);
			}
		}
		return result;
	}

	public List<Object> getTblBhProcStepList(Holiday holiday) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		List<String> runtimes = new ArrayList<String>();

		runtimes.add(BatchConstant.SUB_STEP_RUN_TIME_DAILY);// 添加日运行
		runtimes.add(holiday.getDay_of_month() + "");
		runtimes.add(holiday.getDay_of_week() + "");
		if (holiday.isaPeriodOfTenDays()) {// 每旬
			runtimes.add(BatchConstant.SUB_STEP_RUN_TIME_EVERY_TEN_DAYS);
		} else if (holiday.isHalfYearEnd()) {// 半年
			runtimes.add(BatchConstant.SUB_STEP_RUN_TIME_EVERY_HALF_YEAR);
		} else if (holiday.isMonthEnd()) {// 月末
			runtimes.add(BatchConstant.SUB_STEP_RUN_TIME_MONTHLY);
		} else if (holiday.isSeasonEnd()) {// 季末
			runtimes.add(BatchConstant.SUB_STEP_RUN_TIME_EVERY_SEASON);
		} else if (holiday.isYearEnd()) {// 年末
			runtimes.add(BatchConstant.SUB_STEP_RUN_TIME_YEARLY);
		}

		StringBuffer queryStringBuilder = new StringBuffer("select * from IFS_BAT_PROC_STEP step  where 1=1 ");
		// runtime参数
		if (runtimes != null && runtimes.size() > 0) {
			queryStringBuilder.append(" and step.RUNTIME in ('99'");
			for (String runtime : runtimes) {
				queryStringBuilder.append(",'" + runtime + "'");
			}
			queryStringBuilder.append(")");
		}
		queryStringBuilder.append(" order by step.JOBNO,step.STEP,step.SUB_STEP ");
		return dao.executeQuerySql(queryStringBuilder.toString(), TblBhProcStep.class);
	}

	public TblBhProcStatus findStatusByStep(TblBhProcStep step, String bhDate) throws SnowException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("jobno", step.getJobno());
		paramMap.put("step", step.getStep());
		paramMap.put("substep", step.getSubStep());
		paramMap.put("bhdate", bhDate);

		Object obj = DBDaos.newInstance().selectOne("com.ruimin.ifs.mng.rql.sysmng.queryBhProcStepStatus", paramMap);
		if (obj != null) {
			return (TblBhProcStatus) obj;
		}
		return null;
	}
}
