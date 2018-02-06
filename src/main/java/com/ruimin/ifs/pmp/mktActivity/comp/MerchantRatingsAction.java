package com.ruimin.ifs.pmp.mktActivity.comp;

import java.util.HashMap;
import java.util.Map;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.mktActivity.process.service.MerchantRatingsService;

@SnowDoc(desc = "客户评级")
@ActionResource
public class MerchantRatingsAction extends SnowAction {
	@Action
	@SnowDoc(desc = "商户评级临时表查询")
	public PageResult getSnapList(QueryParamBean queryBean) throws SnowException {
		//获取查询参数,分页信息已经封装在QueryParamBean对象中,页面三个查询条件
		String qmchtId = queryBean.getParameter("qmchtId");
		String qmchtNm = queryBean.getParameter("qmchtNm");
		String qmchtLvC = queryBean.getParameter("qmchtLvC");
		return MerchantRatingsService.getInstance().querySnapForMap(qmchtId, qmchtNm, qmchtLvC, queryBean.getPage());
	}

	@Action
	@SnowDoc(desc = "商户评级历史表查询")
	public PageResult getHistoryList(QueryParamBean queryBean) throws SnowException {
		String qmhctId = queryBean.getParameter("qmchtId");
		Map<String, String> map = new HashMap<String, String>();
		map.put("qmchtId", qmhctId == null ? "" : qmhctId);
		return MerchantRatingsService.getInstance().queryHistoryForMap(map, queryBean.getPage());
	}
	@Action
	@SnowDoc(desc = "验证审核人更新临时表")
	public void update(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		//获取数据集
		UpdateRequestBean requestBean = updateMap.get("accountInfo");
		//得到数据集中更改的商户等级值
		String changeLv = requestBean.getParameter("changeLv");
		//得到商户号
		String mchtId = requestBean.getParameter("mchtId");
		String username = requestBean.getParameter("username");
		String password = requestBean.getParameter("password");
		//验证审核人身份
		MerchantRatingsService.getInstance().update(username, password, changeLv, mchtId);
		//打印日志
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
							      sessionUserInfo.getBrno(), "商户等级修改:商户号=" + mchtId });
	}
}
