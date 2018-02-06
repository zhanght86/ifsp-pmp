package com.ruimin.ifs.pmp.paPay.comp;

import java.util.Map;

import com.ruim.ifsp.utils.verify.IfspDataVerifyUtil;
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
import com.ruimin.ifs.pmp.paPay.process.bean.PagyMixpayPayMethodFeeCfg;
import com.ruimin.ifs.pmp.paPay.process.service.PAPayMethodFeeCfgService;
import com.ruimin.ifs.pmp.sysConf.common.constants.AccountTypeConstants;

@SnowDoc(desc="平安支付费率查询")
@ActionResource
public class PAPayMethodFeeCfgAction extends SnowAction{

	@Action
	@SnowDoc(desc = "平安支付费率查询")
	public PageResult queryMain(QueryParamBean queryBean) throws SnowException {
		String pmtId = queryBean.getParameter("qPmtId");
		String pmtTag=queryBean.getParameter("qPmtTag");
		String pmfId=queryBean.getParameter("qPmfId");
		PageResult queryMain = PAPayMethodFeeCfgService.getInstance().queryMain(pmtId, pmfId,pmtTag, queryBean.getPage());
		return queryMain;
	}
	
	/**
	 * 支付费率停用/启用
	 * @param updateMap
	 * @throws SnowException
	 */
	@SnowDoc(desc = "支付费率停用/启用 ")
	public void updState(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("PagyMixpayPayMethodFeeCfg");
		PagyMixpayPayMethodFeeCfg methodInfo=new PagyMixpayPayMethodFeeCfg();
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
		if (IfspDataVerifyUtil.isNotBlank(methodInfo)) {
			PAPayMethodFeeCfgService.getInstance().update(methodInfo);
		}
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrCode(),
				"[平安支付管理]--支付费率停用/启用:支付方式编号[pmtId]=" +methodInfo.getPmtId()+"支付标签[pmtTag]"+methodInfo.getPmtTag()+"行业会类编号[pmfId]"+methodInfo.getPmfId()  });
	}
	
	
	/**
	 * 查询支付费率
	 * @param queryBean
	 * @return
	 * @throws Exception 
	 */
	@Action
	@SnowDoc(desc = "查询支付费率")
	public void queryPayCfg(Map<String, UpdateRequestBean> updateMap) throws Exception {
		PAPayMethodFeeCfgService.getInstance().quyPayCfg();
		 //得到操作员编号
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        String tlrno = sessionUserInfo.getTlrno();
        String msg = "[平安支付]--查询支付费率 :操作员编号[Tlrno]=" +tlrno;
        sessionUserInfo.addBizLog("queryPayCfg.log",
                new String[] { tlrno, sessionUserInfo.getBrno(), msg });
	}
}
