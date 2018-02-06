package com.ruimin.ifs.example.comp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.example.process.bean.DataBean;
import com.ruimin.ifs.framework.dataobject.PageQueryResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;

@SnowDoc(desc = "我的第一个action	")
@ActionResource
public class MyFirstAction extends SnowAction {

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "查询测试")
	public PageQueryResult listAll(QueryParamBean queryBean) {
		PageQueryResult result = new PageQueryResult();
		int pageIndex = queryBean.getPage().getCurrentPage();
		List<DataBean> list = new ArrayList<DataBean>();
		int N = 24;
		int start = queryBean.getPage().getEveryPage() * (queryBean.getPage().getCurrentPage() - 1);
		for (int i = start; i < start + 10; i++) {
			if (i >= N)
				break;
			DataBean bean = new DataBean();
			bean.setF1("id" + pageIndex + i);
			bean.setF2("你好 world");
			list.add(bean);
		}
		result.setTotalCount(N);
		result.setQueryResult(list);
		return result;
	}

	@Action
	@SnowDoc(desc = "更新测试")
	public Map<String, String> saveOrUpdate(Map<String, UpdateRequestBean> updateMap) {
		Map<String, String> result = new HashMap<String, String>();

		return result;
	}
}
