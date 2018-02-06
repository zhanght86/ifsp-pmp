package com.ruimin.ifs.pmp.mchtMng.comp;

import java.util.Map;

import com.ruim.ifsp.utils.datetime.IfspDateTime;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.mng.process.service.DataDicEntryService;
import com.ruimin.ifs.pmp.accessPara.process.service.AccessParaService;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtCertInfoVO;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsChlMchtInfo;
import com.ruimin.ifs.pmp.mchtMng.process.service.ChlMchtInfoMngService;
import com.ruimin.ifs.po.TblDataDic;

@ActionResource
@SnowDoc(desc = "渠道商户管理")
public class ChlMchtInfoMngAction extends SnowAction{

	 /**
     * 查询
     * 
     * @param queryBean
     * @return
     * @throws SnowException
     */
    @Action
    @SnowDoc(desc = "查询")
    public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
    	 String qChlMchtNo=queryBean.getParameter("qChlMchtNo");
    	 String qChlMchtName=queryBean.getParameter("qChlMchtName");
    	 PageResult result = ChlMchtInfoMngService.getInstance().queryAll(qChlMchtNo,qChlMchtName,queryBean.getPage());
         return result;
    }
    
	/**
	 * 功能：更新渠道商户信息
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@SnowDoc(desc = "渠道商户信息修改")
	public void btnUpdSave(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		UpdateRequestBean reqBean = updateMap.get("PbsChlMchtInfo");
		PbsChlMchtInfo chlMchtInfo=new PbsChlMchtInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), chlMchtInfo);
		}
		chlMchtInfo.setLastUpdTlr(sessionUserInfo.getTlrno());
		chlMchtInfo.setLastUpdDateTime(IfspDateTime.getYYYYMMDDHHMMSS());
		ChlMchtInfoMngService.getInstance().updateChlMchtInfo(chlMchtInfo);
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"渠道商户信息修改:id=" + chlMchtInfo.getChlMchtNo() });
	}
	
	
	/**
	 * 功能：新增渠道商户信息
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@SnowDoc(desc = "渠道商户信息新增")
	public void btnAddSave(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		UpdateRequestBean reqBean = updateMap.get("PbsChlMchtInfo");
		PbsChlMchtInfo chlMchtInfo=new PbsChlMchtInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), chlMchtInfo);
		}
		chlMchtInfo.setChlMchtStat("00");
		chlMchtInfo.setCrtTlr(sessionUserInfo.getTlrno());
		chlMchtInfo.setCrtDateTime(IfspDateTime.getYYYYMMDDHHMMSS());
		ChlMchtInfoMngService.getInstance().addChlMchtInfo(chlMchtInfo);
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"渠道商户信息新增:id=" + chlMchtInfo.getChlMchtNo() });
	}
	
	/**
	 * 功能：删除渠道商户信息
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@SnowDoc(desc = "渠道商户信息删除")
	public void delete(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		UpdateRequestBean reqBean = updateMap.get("PbsChlMchtInfo");
		PbsChlMchtInfo chlMchtInfo=new PbsChlMchtInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), chlMchtInfo);
		}
		ChlMchtInfoMngService.getInstance().delChlMchtInfo(chlMchtInfo);
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"渠道商户信息删除:id=" + chlMchtInfo.getChlMchtNo() });
	}
	
	
	/**
	 * 修改渠道商户状态
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@SnowDoc(desc = "状态启用停用")
	public void setAccStatus(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg="";
		UpdateRequestBean reqBean = updateMap.get("PbsChlMchtInfo");
		PbsChlMchtInfo chlMchtInfo=new PbsChlMchtInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), chlMchtInfo);
		}
		String certifiStatus = "";
		if("00".equals(chlMchtInfo.getChlMchtStat())){
			certifiStatus="99";
		}else{
			certifiStatus="00";
		}
		chlMchtInfo.setChlMchtStat(certifiStatus);
		chlMchtInfo.setLastUpdTlr(sessionUserInfo.getTlrno());
		chlMchtInfo.setLastUpdDateTime(IfspDateTime.getYYYYMMDDHHMMSS());
		ChlMchtInfoMngService.getInstance().updateChlMchtInfo(chlMchtInfo);
		if ("99".equals(certifiStatus)) {
			msg = "[渠道商户 ]--更改状态为停用 :渠道商户编号[ChlMchtNo]=" + chlMchtInfo.getChlMchtNo();
		} else {
			msg = "[渠道商户]--更改状态为启用 :渠道商户编号[ChlMchtNo]=" + chlMchtInfo.getChlMchtNo();
		}
		// 日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });

	}

}
