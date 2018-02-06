package com.ruimin.ifs.pmp.paPay.comp;

import java.util.Map;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.paPay.process.bean.PagyMixpayPayMethodInfo;
import com.ruimin.ifs.pmp.paPay.process.service.PAPayMethodInfoService;
import com.ruimin.ifs.pmp.sysConf.common.constants.AccountTypeConstants;

@SnowDoc(desc = "平安支付方式查询")
@ActionResource
public class PAPayMethodInfoAction extends SnowAction{

	@Action
	@SnowDoc(desc = "平安支付方式查询")
	public PageResult queryMain(QueryParamBean queryBean) throws SnowException {
		String pmtId = queryBean.getParameter("qPmtId");
		String pmtName=queryBean.getParameter("qPmtName");
		String pmtTag=queryBean.getParameter("qPmtTag");
		return PAPayMethodInfoService.getInstance().queryMain(pmtId,pmtName,pmtTag,queryBean.getPage());
	}
	

	/**
	 * 支付方式停用/启用
	 * @param updateMap
	 * @throws SnowException
	 */
	@SnowDoc(desc = "支付方式停用/启用 ")
	public void updState(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("PagyMixpayPayMethodInfo");
		PagyMixpayPayMethodInfo methodInfo=new PagyMixpayPayMethodInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), methodInfo);
		}
		String state = methodInfo.getUsedTag();
		if (AccountTypeConstants.PA_PAY_1.equals(state)) {
			methodInfo.setUsedTag(AccountTypeConstants.PA_PAY_2);
		}else{
			methodInfo.setUsedTag(AccountTypeConstants.PA_PAY_1);
		}
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		PAPayMethodInfoService.getInstance().update(methodInfo);
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrCode(),
				"[平安支付管理]--支付方式停用/启用:支付方式编号[pmtId]=" +methodInfo.getPmtId()+"支付标签[pmtTag]"+methodInfo.getPmtTag()  });
	}
	
	
	/**
	 * 支付方式查询
	 * @param queryBean
	 * @return
	 * @throws Exception 
	 */
	@Action
	@SnowDoc(desc = "支付方式查询")
	public void quyPayMethod(Map<String, UpdateRequestBean> updateMap) throws Exception {
		PAPayMethodInfoService.getInstance().quyPayMethod();
		 //得到操作员编号
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        String tlrno = sessionUserInfo.getTlrno();
        String msg = "[平安支付]--支付方式查询 :操作员编号[Tlrno]=" +tlrno;
        sessionUserInfo.addBizLog("quyPayMethod.log",
                new String[] { tlrno, sessionUserInfo.getBrno(), msg });
	}
	
}
