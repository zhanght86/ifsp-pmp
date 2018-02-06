package com.ruimin.ifs.pmp.mchtMng.comp;

import java.util.Map;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.PageQueryResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtAssistInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtAssistInfoTmp;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtAssistMngService;

@ActionResource
@SnowDoc(desc = "商户辅表管理")
public class MchtAssistMngAction {

    /**
     * 页面查询
     * 
     * @param queryBean
     * @return
     * @throws SnowException
     */
    @Action
    @SnowDoc(desc = "商户辅表的页面信息查询")
    public PageResult queryMainAdd(QueryParamBean queryBean) throws SnowException {
        PageQueryResult retResult = new PageQueryResult();
        return retResult;
    }
    
	/**
	 * 页面查询
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "商户辅表的页面信息查询")
	public PageResult queryMain(QueryParamBean queryBean) throws SnowException {
		/** 查询条件 */
		String qmchtId = queryBean.getParameter("qmchtId");// 商户编号 
		/** 查询主页面字段 */
		return MchtAssistMngService.getInstance().queryMain(qmchtId,queryBean.getPage());
	}
	
	/**
	 * 新增
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增")
	public void addMchtAssist(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		/** 获取数据集 */
		UpdateRequestBean reqBean = updateMap.get("PbsMchtAssistInfoTmp");
		PbsMchtAssistInfo assistInfo=new PbsMchtAssistInfo();
		PbsMchtAssistInfoTmp assistInfoTmp=new PbsMchtAssistInfoTmp();
		
		 if(reqBean.hasNext()) {
			Map<String, String> map = reqBean.next();
			String mchtId = map.get("mchtId1");
			DataObjectUtils.map2bean(map, assistInfo);
			DataObjectUtils.map2bean(map, assistInfoTmp);
			assistInfo.setMchtId(mchtId);
			assistInfoTmp.setMchtId(mchtId);
		}
		MchtAssistMngService.getInstance().addAssistInfoTmp(assistInfoTmp);
//		MchtAssistMngService.getInstance().addAssistInfo(assistInfo);
		
		/** 加载日志 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		/** 打印日志 */
		sessionUserInfo.addBizLog("add.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[商户辅表管理]--信息新增:商户编号[mchtId]=" + assistInfoTmp.getMchtId() });
	}
	
	/**
	 * 修改
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改")
	public void updMchtAssist(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("PbsMchtAssistInfoTmp");
		PbsMchtAssistInfo assistInfo=new PbsMchtAssistInfo();
		PbsMchtAssistInfoTmp assistInfoTmp=new PbsMchtAssistInfoTmp();
		
		while (reqBean.hasNext()) {
			Map<String, String> map = reqBean.next();
			DataObjectUtils.map2bean(map, assistInfo);
			DataObjectUtils.map2bean(map, assistInfoTmp);
		}
		MchtAssistMngService.getInstance().updAssistInfoTmp(assistInfoTmp);
//		MchtAssistMngService.getInstance().updAssistInfo(assistInfo);
		
		/** 加载日志 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		/** 打印日志 */
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[商户辅表管理]--信息修改:商户编号[mchtId]=" + assistInfoTmp.getMchtId() });
	}
}
