package com.ruimin.ifs.example.comp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.common.bean.DownLoadFileBean;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.example.process.bean.DataBean;
import com.ruimin.ifs.framework.dataobject.FormRequestBean;
import com.ruimin.ifs.framework.dataobject.PageQueryResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;

@SnowDoc(desc = "DemoAction")
@ActionResource
public class DemoAction extends SnowAction {

	@Action
	@SnowDoc(desc = "下载文件测试")
	public DownLoadFileBean downLoadTest(FormRequestBean formRqeust) throws SnowException {
		DownLoadFileBean df = new DownLoadFileBean();
		System.out.println(formRqeust.getRequestParams().toString());
		df.setFileName("中文测试");
		df.setFilePath("c:/home/1.txt");
		return df;
	}

	@Action
	@SnowDoc(desc = "search")
	public PageQueryResult listAll(QueryParamBean queryBean) {
		int N = 24;
		PageQueryResult result = new PageQueryResult();
		int pageIndex = queryBean.getPage().getCurrentPage();
		int pageSize = queryBean.getPage().getEveryPage();

		String order = queryBean.getCqRequest().getParameter("order");
		List<DataBean> list = new ArrayList<DataBean>();

		int start = queryBean.getPage().getEveryPage() * (queryBean.getPage().getCurrentPage() - 1);
		for (int i = start; i < start + pageSize; i++) {
			if (i >= N)
				break;
			DataBean bean = new DataBean();
			bean.setF1("id" + pageIndex + i);
			bean.setF2("你好 world" + i);
			bean.setF3(order);
			list.add(bean);
		}
		result.setTotalCount(N);
		result.setQueryResult(list);
		return result;
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "saveOrUpdate")
	public Map<String, String> saveOrUpdate(Map<String, UpdateRequestBean> updateMap) {
		Map<String, String> result = new HashMap<String, String>();

		return result;
	}

}
