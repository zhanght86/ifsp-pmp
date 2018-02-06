package com.ruimin.ifs.mng.comp;

import java.util.Map;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowErrorCode;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.mng.process.service.CurrencyManEntryService;
import com.ruimin.ifs.po.TblCurInf;

@SnowDoc(desc = "汇率信息维护")
@ActionResource
public class CurrencyManEntryAction extends SnowAction {

	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		String qcurcd = queryBean.getParameter("qcurcd");
		String qcurname = queryBean.getParameter("qcurname");
		return CurrencyManEntryService.getInstance().queryList(qcurcd, qcurname, queryBean.getPage());
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改")
	public void saveOrUpdate(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("CurrencyManEntry");
		TblCurInf tblCurInf = new TblCurInf();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tblCurInf);
		}
		CurrencyManEntryService.getInstance().updateDataDicEntry(tblCurInf);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"币种信息修改:id=" + tblCurInf.getCurcd() });
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增")
	public void saveOrUpdate1(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("CurrencyManEntry");
		TblCurInf tblCurInf = new TblCurInf();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tblCurInf);
		}
		CurrencyManEntryService cs = CurrencyManEntryService.getInstance();
		if (null != cs.checkId(tblCurInf.getCurcd())) {
			SnowExceptionUtil.throwWarnException(SnowErrorCode.MESSAGE_WEB_0034, new String[] { "币种货币代码" });
		}
		CurrencyManEntryService.getInstance().saveDataDicEntry(tblCurInf);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"币种信息新增:id=" + tblCurInf.getCurcd() });
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "删除")
	public void delete(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("CurrencyManEntry");
		TblCurInf tblCurInf = new TblCurInf();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tblCurInf);
		}
		CurrencyManEntryService.getInstance().deletDataDicEntry(tblCurInf);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"币种信息删除:id=" + tblCurInf.getCurcd() });
	}

}
