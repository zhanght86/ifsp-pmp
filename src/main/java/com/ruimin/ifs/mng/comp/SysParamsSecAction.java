package com.ruimin.ifs.mng.comp;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.mng.process.service.SysParamsSecService;
import com.ruimin.ifs.po.TblSysParam;

@SnowDoc(desc = "系统参数设置")
@ActionResource
public class SysParamsSecAction extends SnowAction {

	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		String queryParamId = queryBean.getParameter("queryParamId");
		String queryOprcode1 = queryBean.getParameter("queryOprcode1");
		return SysParamsSecService.getInstance().queryList(queryParamId, queryOprcode1, queryBean.getPage());
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "系统参数修改")
	public void saveOrUpdate(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("SysParamsSec");
		TblSysParam tlp = new TblSysParam();
		SysParamsSecService sps = ContextUtil.getSingleService(SysParamsSecService.class);
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tlp);
		}
		sps.updateSysParamsSec(tlp);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"系统参数修改:将参数" + tlp.getParamId() + " | " + tlp.getMagicId() + "修改为:{" + tlp.getParamValueTx() + "}" });
	}

	/* add by ttt 20151104 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "删除")
	public void delSysParams(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("SysParamsSec");
		String paramId = reqBean.getParameter("paramId");
		String magicId = reqBean.getParameter("magicId");
		SysParamsSecService.getInstance().delSysParams(paramId, magicId);
		// SessionUserInfo sessionUserInfo =
		// SessionUserInfo.getSessionUserInfo();
		// sessionUserInfo.addBizLog("update.log",new
		// String[]{sessionUserInfo.getTlrno(),
		// sessionUserInfo.getBrno(),"删除地区,id="+magicId});
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "参数新增")
	public void addSysParams(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("SysParamsSec");

		TblSysParam tlp = new TblSysParam();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tlp);
		}
		if (StringUtils.isBlank(tlp.getParamId())) {
			SnowExceptionUtil.throwWarnException("WEB_E0045", "参数主键不能为空!");
		}
		if (StringUtils.isBlank(tlp.getMagicId())) {
			SnowExceptionUtil.throwWarnException("WEB_E0045", "参数标识不能为空!");
		}
		/*
		 * //检查地区编码是否重复 int count =
		 * SysParamsSecService.getInstance().queryCityByCtCode(tlp.getCtCode());
		 * if (count>0) {
		 * SnowExceptionUtil.throwWarnException("WEB_E0046",tlp.getCtCode()); }
		 */
		SysParamsSecService.getInstance().addSysParams(tlp);
	}

	/* end add */

}
