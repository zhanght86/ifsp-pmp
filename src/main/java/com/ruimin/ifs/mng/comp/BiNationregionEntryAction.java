package com.ruimin.ifs.mng.comp;

import java.util.Map;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.mng.process.bean.BiNationregionEntryVO;
import com.ruimin.ifs.mng.process.service.BiNationregionEntryService;

@SnowDoc(desc = "国家地区代码维护")
@ActionResource
public class BiNationregionEntryAction extends SnowAction {

	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		String qid = queryBean.getParameter("qid");
		String qnationregionNumber = queryBean.getParameter("qnationregionNumber");
		String cnEnFullName = queryBean.getParameter("cnEnFullName");
		String cnEnShortName = queryBean.getParameter("cnEnShortName");
		return BiNationregionEntryService.getInstance().queryList(qid, qnationregionNumber, cnEnFullName, cnEnShortName,
				queryBean.getPage());
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改")
	public void saveOrUpdate(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("BiNationregionEntry");
		BiNationregionEntryVO biNationregionEntryVO = new BiNationregionEntryVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), biNationregionEntryVO);
		}
		biNationregionEntryVO.setCrtDt(DateUtil.get8Date());
		biNationregionEntryVO.setLastUpdTms(DateUtil.get14Date());
		biNationregionEntryVO.setLastUpdOper(SessionUserInfo.getSessionUserInfo().getTlrno());
		BiNationregionEntryService.getInstance().updateBiNationregionEntry(biNationregionEntryVO);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"国家/代码修改:id=" + biNationregionEntryVO.getNationregionCode() });
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增")
	public void saveOrUpdate1(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("BiNationregionEntry");
		BiNationregionEntryVO biNationregionEntryVO = new BiNationregionEntryVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), biNationregionEntryVO);
		}
		biNationregionEntryVO.setCrtDt(DateUtil.get8Date());
		biNationregionEntryVO.setLastUpdTms(DateUtil.get14Date());
		biNationregionEntryVO.setLastUpdOper(SessionUserInfo.getSessionUserInfo().getTlrno());
		BiNationregionEntryService.getInstance().saveBiNationregionEntry(biNationregionEntryVO);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"国家/代码新增:id=" + biNationregionEntryVO.getNationregionCode() });
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "删除")
	public void delete(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("BiNationregionEntry");
		BiNationregionEntryVO biNationregionEntryVO = new BiNationregionEntryVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), biNationregionEntryVO);
		}
		BiNationregionEntryService.getInstance().deletBiNationregionEntry(biNationregionEntryVO);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"国家/代码删除:id=" + biNationregionEntryVO.getNationregionCode() });
	}

}
