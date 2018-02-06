package com.ruimin.ifs.example.comp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.example.process.bean.DataBean;
import com.ruimin.ifs.framework.dataobject.PageQueryResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;

@SnowDoc(desc = "CQDemoAction")
@ActionResource
public class CQDemoAction extends SnowAction {

	@Action
	@SnowDoc(desc = "search")
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
			bean.setF2("你好 world" + i);
			list.add(bean);
		}
		result.setTotalCount(N);
		result.setQueryResult(list);
		return result;
	}

	/**
	 *
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 */
	public static String getCQName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		return "中文翻译";
	}

}
