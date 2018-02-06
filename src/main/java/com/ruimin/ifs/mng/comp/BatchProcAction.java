package com.ruimin.ifs.mng.comp;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.framework.dataobject.PageQueryResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.mng.process.bean.batch.BatchStepInfo;
import com.ruimin.ifs.mng.process.service.BatchService;
import com.ruimin.ifs.po.TblBhProcStep;
import com.ruimin.ifs.util.SeqNoGenUtil;

@SnowDoc(desc = "批量操作")
@ActionResource
public class BatchProcAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询所有批量设置信息")
	public PageResult listAllBhProcStep(QueryParamBean queryBean) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryBhProcStep", queryBean.getPage());
	}

	@Action
	@SnowDoc(desc = "批量监控查询")
	public PageQueryResult listBatchMonitor(QueryParamBean queryBean) throws SnowException {
		String bhDate = queryBean.getParameter("bhdate");
		String statusCode = queryBean.getParameter("statuscode");
		List<BatchStepInfo> result = BatchService.getService().getBatchStepInfoList(bhDate, statusCode);
		int everypage = queryBean.getPage().getEveryPage();
		/** 获取nextPage：表示下一页是第几页 */
		int nextpage = queryBean.getPage().getCurrentPage();
		int maxIndex = nextpage * everypage;

		/** 对最后一页的处理 */
		if (maxIndex > result.size()) {
			maxIndex = result.size();
		}
		List<BatchStepInfo> resultList = result.subList((nextpage - 1) * everypage, maxIndex);
		PageQueryResult queryResult = new PageQueryResult();
		queryResult.setQueryResult(resultList);
		queryResult.setTotalCount(result.size());
		return queryResult;
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增或修改批量设置")
	public void saveOrUpdateProcStep(Map<String, UpdateRequestBean> reqMap) throws SnowException {
		UpdateRequestBean requestBean = reqMap.get("procStepDs");
		if (requestBean.hasNext()) {
			DBDao dao = DBDaos.newInstance();
			Map<String, String> valueMap = requestBean.next();
			String id = valueMap.get("id");
			TblBhProcStep proc = new TblBhProcStep();
			DataObjectUtils.map2bean(valueMap, proc);
			// 校验
			this.vaildProcStep(proc);
			if (StringUtils.isNotBlank(id) && Integer.parseInt(id) > 0) {// 编辑
				dao.update(proc);
			} else {// 新增
				proc.setId(SeqNoGenUtil.genBhProcStepId());
				dao.insert(proc);
			}
		}
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "删除批量设置")
	public void delteProcStep(Map<String, UpdateRequestBean> reqMap) throws SnowException {
		UpdateRequestBean requestBean = reqMap.get("procStepDs");
		if (requestBean.hasNext()) {
			String id = requestBean.next().get("id");
			DBDao dao = DBDaos.newInstance();
			dao.delete(TblBhProcStep.class, Integer.parseInt(id));
		}
	}

	private boolean vaildProcStep(TblBhProcStep procStep) throws SnowException {
		String reportFlag = procStep.getReportFlag();
		if (reportFlag != null && reportFlag.length() < 9 && reportFlag.length() > 0) {
			SnowExceptionUtil.throwWarnException("WEB_E0041", "报表标志不满足格式:*-*-*-*-*,其中*代表0,1");
		}
		if (reportFlag != null && reportFlag.length() == 9) {
			int tmpFlag1 = 3;// 3起到判断作用
			String tmpFlag2 = "";
			int tmpFlag3 = 3;
			String tmpFlag4 = "";
			int tmpFlag5 = 3;
			String tmpFlag6 = "";
			int tmpFlag7 = 3;
			String tmpFlag8 = "";
			int tmpFlag9 = 3;
			try {
				tmpFlag1 = Integer.parseInt(reportFlag.substring(0, 1));
				tmpFlag2 = reportFlag.substring(1, 2);
				tmpFlag3 = Integer.parseInt(reportFlag.substring(2, 3));
				tmpFlag4 = reportFlag.substring(3, 4);
				tmpFlag5 = Integer.parseInt(reportFlag.substring(4, 5));
				tmpFlag6 = reportFlag.substring(5, 6);
				tmpFlag7 = Integer.parseInt(reportFlag.substring(6, 7));
				tmpFlag8 = reportFlag.substring(7, 8);
				tmpFlag9 = Integer.parseInt(reportFlag.substring(8, 9));
			} catch (Exception ex) {
				SnowExceptionUtil.throwWarnException("WEB_E0041", "报表标志不满足格式:*-*-*-*-*,其中*代表0,1");
			}
			if ((tmpFlag1 != 0 && tmpFlag1 != 1) || (tmpFlag3 != 0 && tmpFlag3 != 1) || (tmpFlag5 != 0 && tmpFlag5 != 1)
					|| (tmpFlag7 != 0 && tmpFlag7 != 1) || (tmpFlag9 != 0 && tmpFlag9 != 1)
					|| tmpFlag2.equals("-") == false || tmpFlag4.equals("-") == false || tmpFlag6.equals("-") == false
					|| tmpFlag8.equals("-") == false) {
				SnowExceptionUtil.throwWarnException("WEB_E0041", "报表标志不满足格式:*-*-*-*-*,其中*代表0,1");
			}
		}
		int jobNo = procStep.getJobno();
		if (jobNo != 5 && jobNo != 10 && jobNo != 20 && jobNo != 25 && jobNo != 35 && jobNo != 40 && jobNo != 50) {
			SnowExceptionUtil.throwWarnException("WEB_E0041", "作业号只能为5,10,20,25,35,40,50");
		}
		return true;
	}

}
