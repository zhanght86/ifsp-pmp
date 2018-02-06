package com.ruimin.ifs.pmp.mchtAppMng.comp;

import java.util.Map;

import com.ruim.ifsp.utils.id.IfspId;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.mchtAppMng.common.constant.AdvertConstants;
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.AdBaseInfo;
import com.ruimin.ifs.pmp.mchtAppMng.process.service.AdMngService;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;
import com.ruimin.ifs.util.CommonConstants;



@SnowDoc(desc = "广告位维护")
@ActionResource
public class AdMngAction extends SnowAction {
	String adCount = SysParamUtil.getParam(AdvertConstants.ADVERT_COUNT);
	@Action
	@SnowDoc(desc = "广告位全部查询")
	public PageResult queryMain(QueryParamBean queryBean) throws SnowException {
		String qadPst = queryBean.getParameter("qadPst");
		String qadInfo = queryBean.getParameter("qadInfo");
		String qadStat = queryBean.getParameter("qadStat");
		return AdMngService.getInstance().queryMain(qadPst,qadInfo,qadStat,queryBean.getPage());
	}
	
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "停用启用广告位")
	public void disableEnable(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		UpdateRequestBean reqBean = updateMap.get("adMng");//获取数据集
		AdBaseInfo adBaseInfo = new AdBaseInfo();
		while (reqBean.hasNext()) {
				DataObjectUtils.map2bean(reqBean.next(), adBaseInfo);
		}
		String adStat=adBaseInfo.getAdStat();
		String msg="";
		if("0".equals(adStat)){
			msg="停用";
			adBaseInfo.setAdStat("1");
		}
		if("1".equals(adStat)){
			msg="启用";
			adBaseInfo.setAdStat("0");
		}
		AdMngService.getInstance().update(adBaseInfo);

		/******************  记录日志 ********************/
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[广告位]--"+msg+":广告位编号[noticeNo]=" + adBaseInfo.getAdId() + "" });
	}
	
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "广告位新增")
	public void add(Map<String, UpdateRequestBean> updateMap) throws Exception {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
    	//广告位信息
		AdBaseInfo adBaseInfo = new AdBaseInfo();
    	//获取广告位数据集
    	UpdateRequestBean reqBean = updateMap.get("adMng");
    	while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), adBaseInfo);
    	}
    	
    	boolean result = AdMngService.ifExit(adBaseInfo);
    	if(result){
    		//获取图片信息
        	SnowLog.getLogger(AdMngService.class).info("=============== 设置新增广告位信息 =============");
        	
//        	//查询当前记录的最大系统广告位编号
//        	String maxSeq = AdMngService.getInstance().queryMaxId();
//        	String nextSeq;
//        	if(!StringUtil.isBlank(maxSeq)){
//        		int num = Integer.valueOf(maxSeq);
//        		nextSeq = String.valueOf(num+1);
//        	}else{
//       			nextSeq = "10000001";
//       		}

        	String nextSeq = IfspId.getObjectId();
        	adBaseInfo.setAdId(nextSeq);//设置广告位编号
        	String Url=CommonConstants.getProperty("SHOW_FILE");
        	adBaseInfo.setPicUrl(Url);
        	//实体类对象插入到数据库中
        	AdMngService.getInstance().add(adBaseInfo);
  
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
                    "[系统广告位]--新增:广告位编号[noticeNo]=" + adBaseInfo.getAdId() });
    	}else{
    		SnowExceptionUtil.throwErrorException("广告位置相同，图片位置不能重复");
    	}
    	
	}
	
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改广告位")
	public void update(Map<String, UpdateRequestBean> updateMap) throws Exception{
		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		UpdateRequestBean reqBean = updateMap.get("adMng");//获取数据集
		AdBaseInfo adBaseInfo = new AdBaseInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), adBaseInfo);
		}
		boolean result = AdMngService.ifExit(adBaseInfo);
		if(result){
			//修改信息
			AdMngService.getInstance().update(adBaseInfo);
			/******************  记录日志 ********************/
			sessionUserInfo.addBizLog("update.log",
					new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[系统广告位]--修改:广告位编号[noticeNo]=" + adBaseInfo.getAdId()+ "]" });
		}else{
			SnowExceptionUtil.throwErrorException("广告位置相同，图片位置不能重复");
		}
	}
	
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "删除广告位")
	public void delete(Map<String, UpdateRequestBean> updateMap) throws Exception{
		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		UpdateRequestBean reqBean = updateMap.get("adMng");//获取数据集
		AdBaseInfo adBaseInfo = new AdBaseInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), adBaseInfo);
		}
		AdMngService.getInstance().delete(adBaseInfo);
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[系统广告位]--删除:广告位编号[noticeNo]=" + adBaseInfo.getAdId()+ "]" });
	}
}
