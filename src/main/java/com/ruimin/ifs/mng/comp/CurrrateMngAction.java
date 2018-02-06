package com.ruimin.ifs.mng.comp;

import java.util.Map;

import javax.servlet.ServletRequest;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.mng.process.service.CurrrateMngService;
import com.ruimin.ifs.po.TblCurInf;
import com.ruimin.ifs.po.TblCurRate;

@SnowDoc(desc = "汇率信息维护")
@ActionResource
public class CurrrateMngAction extends SnowAction {

	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		String curcd = queryBean.getParameter("curcd");
		String tocurcd = queryBean.getParameter("toCurcd");
		String currrateDate = queryBean.getParameter("currrateDate");
		return CurrrateMngService.getInstance().queryList(curcd, tocurcd, currrateDate, queryBean.getPage());
	}

	/**
	 * 根据curcd获取curcdName
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 */
	public static String queryCurcdName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		if (StringUtils.isNotBlank(key)) {
			TblCurInf cur = CurrrateMngService.getInstance().queryCurcdName(key);
			if (cur != null) {
				return cur.getCnname();
			}
		}
		return key;
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增 / 修改")
	public void saveOrUpdate(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("CurrrateMng");
		TblCurRate tblCurRate = new TblCurRate();
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tblCurRate);
		}
		String msg = "";
		tblCurRate.setLastUpdTlr(sessionUserInfo.getTlrno());
		tblCurRate.setLastUpdDate(DateUtil.get8Date());
		if (StringUtils.isBlank(tblCurRate.getId())) {
			// 新增
			tblCurRate.setId(ContextUtil.getUUID());
			CurrrateMngService.getInstance().saveTblCurRate(tblCurRate);
			msg = "汇率信息新增:id=";
		} else {
			// 修改
			CurrrateMngService.getInstance().updateTblCurRate(tblCurRate);
			msg = "汇率信息修改:id=";
		}
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg + tblCurRate.getId() });
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "删除")
	public void delete(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("CurrrateMng");
		TblCurRate tblCurRate = new TblCurRate();
		String id = reqBean.getParameter("id");
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tblCurRate);
		}
		CurrrateMngService.getInstance().deleteTblCurRateById(id);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "汇率信息删除:id=" + id });
	}

}
