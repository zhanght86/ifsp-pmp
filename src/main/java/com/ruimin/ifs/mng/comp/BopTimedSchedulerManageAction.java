package com.ruimin.ifs.mng.comp;

import java.util.List;
import java.util.Map;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.quartz.bean.IfsCronJob;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.mng.process.bean.TblCronJobVO;
import com.ruimin.ifs.mng.process.service.BopTimedSchedulerManageService;

@SnowDoc(desc = "定时任务配置")
@ActionResource
public class BopTimedSchedulerManageAction extends SnowAction {

	@Action
	@SnowDoc(desc = "查询")
	public List<TblCronJobVO> queryAll(QueryParamBean queryBean) throws SnowException {
		return BopTimedSchedulerManageService.getInstance().queryList();
	}

	@Action
	@SnowDoc(desc = "日志查询")
	public PageResult queryLogAll(QueryParamBean queryBean) throws SnowException {
		String excuteTimeStart = queryBean.getParameter("excuteTimeStart");
		String excuteTimeEnd = queryBean.getParameter("excuteTimeEnd");
		String excuteResult = queryBean.getParameter("excuteResult");
		return BopTimedSchedulerManageService.getInstance().queryListLog(excuteTimeStart, excuteTimeEnd, excuteResult,
				queryBean.getPage());
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增/修改")
	public void saveOrUpdate1(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("BopTimedSchedulerManage");
		IfsCronJob ifsCronJob = new IfsCronJob();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), ifsCronJob);
		}
		ifsCronJob.setStartTime(ifsCronJob.getStartTime().replace(":", ""));
		ifsCronJob.setEndTime(ifsCronJob.getEndTime().replace(":", ""));
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";
		if (StringUtils.isBlank(ifsCronJob.getId())) {
			// 新增
			ifsCronJob.setId(ContextUtil.getUUID());
			BopTimedSchedulerManageService.getInstance().saveIfsCronJob(ifsCronJob);
			msg = "定时任务新增:id=";
		} else {
			// 执行方式不是每月某日,需将每月某日字段重置
			if (!ifsCronJob.getRuntime().equals("97")) {
				ifsCronJob.setDaysOfMonth(0);
			}
			// 修改
			BopTimedSchedulerManageService.getInstance().updateIfsCronJob(ifsCronJob);
			msg = "定时任务修改:id=";
		}
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg + ifsCronJob.getId() });
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "删除")
	public void delete(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("BopTimedSchedulerManage");
		String id = reqBean.getParameter("id");
		IfsCronJob ifsCronJob = new IfsCronJob();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), ifsCronJob);
		}
		BopTimedSchedulerManageService.getInstance().deleteIfsCronJobById(id);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "定时任务删除:id=" + id });
	}

}
