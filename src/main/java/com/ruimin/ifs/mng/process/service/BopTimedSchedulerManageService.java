package com.ruimin.ifs.mng.process.service;

import java.util.ArrayList;
import java.util.List;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.framework.quartz.TaskJobScheduler;
import com.ruimin.ifs.framework.quartz.bean.IfsCronJob;
import com.ruimin.ifs.mng.process.bean.TblCronJobVO;

/**
 * 定时任务配置
 * 
 * @author shaoqin
 *
 */
@Service
public class BopTimedSchedulerManageService extends SnowService {

	public static BopTimedSchedulerManageService getInstance() throws SnowException {
		return ContextUtil.getSingleService(BopTimedSchedulerManageService.class);
	}

	public List<TblCronJobVO> queryList() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		List<Object> list = dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryCronJob");
		List<TblCronJobVO> newList = new ArrayList<TblCronJobVO>();
		TaskJobScheduler tj = new TaskJobScheduler();
		for (int i = 0; i < list.size(); i++) {
			TblCronJobVO cronJob = (TblCronJobVO) list.get(i);
			cronJob.setIsRunning(tj.isRunning(cronJob.getJobno())); // 重组定时任务,加入任务状态监视
			newList.add(cronJob);
		}
		return newList;
	}

	/**
	 * 日志查询
	 * 
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryListLog(String excuteTimeStart, String excuteTimeEnd, String excuteResult, Page page)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryJobLog",
				RqlParam.map().set("excuteTimeStart", excuteTimeStart == null ? "" : excuteTimeStart)
						.set("excuteTimeEnd", excuteTimeEnd == null ? "" : excuteTimeEnd)
						.set("excuteResult", excuteResult == null ? "" : excuteResult),
				page);
	}

	public void saveIfsCronJob(IfsCronJob ifsCronJob) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(ifsCronJob);
	}

	public void updateIfsCronJob(IfsCronJob ifsCronJob) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(ifsCronJob);
	}

	public void deletIfsCronJob(IfsCronJob ifsCronJob) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(ifsCronJob);
	}

	public void deleteIfsCronJobById(String id) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(IfsCronJob.class, id);
	}
}
