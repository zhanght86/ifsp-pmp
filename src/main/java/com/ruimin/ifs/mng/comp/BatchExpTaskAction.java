package com.ruimin.ifs.mng.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.common.bean.DownLoadFileBean;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.dataobject.FormRequestBean;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.po.TblExpTaskInfo;

@SnowDoc(desc = "批量导出")
@ActionResource
public class BatchExpTaskAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询所有批量导出任务")
	public PageResult listAllBatchExpTask(QueryParamBean queryBean) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String tskNm = queryBean.getParameter("tskNm", "");
		String tskStartTms = queryBean.getParameter("tskStartTms", "");
		String tskStat = queryBean.getParameter("tskStat", "");
		return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryBatchExpTask",
				RqlParam.map().set("filename", "%" + tskNm + "%").set("begin", tskStartTms).set("state", tskStat),
				queryBean.getPage());
	}

	@Action
	@SnowDoc(desc = "批量导出生成文件的下载")
	public DownLoadFileBean download(FormRequestBean queryBean) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		TblExpTaskInfo task = dao.select(TblExpTaskInfo.class, queryBean.getParameter("oid"));
		String fileName = task.getTskDesc();
		String filePath = task.getExpFileNm();
		DownLoadFileBean bean = new DownLoadFileBean();
		bean.setFileName(fileName);
		bean.setFilePath(filePath);
		return bean;
	}

}
