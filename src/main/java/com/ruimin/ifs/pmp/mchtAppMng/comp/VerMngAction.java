package com.ruimin.ifs.pmp.mchtAppMng.comp;

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
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.mchtAppMng.common.constant.AdvertConstants;
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.VerBaseInfo;
import com.ruimin.ifs.pmp.mchtAppMng.process.service.VerMngService;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;



@SnowDoc(desc = "app版本维护")
@ActionResource
public class VerMngAction extends SnowAction {
	String adCount = SysParamUtil.getParam(AdvertConstants.ADVERT_COUNT);
	@Action
	@SnowDoc(desc = "版本信息查询")
	public PageResult queryMain(QueryParamBean queryBean) throws SnowException {
		String qdeviceType = queryBean.getParameter("qdeviceType");
		String qappVer = queryBean.getParameter("qappVer");
		String qupdFlag = queryBean.getParameter("qupdFlag");
		return VerMngService.getInstance().queryMain(qdeviceType,qappVer,qupdFlag,queryBean.getPage());
	}
	
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "版本新增")
	public void add(Map<String, UpdateRequestBean> updateMap) throws Exception {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
    	//
		VerBaseInfo verBaseInfo = new VerBaseInfo();
    	//
    	UpdateRequestBean reqBean = updateMap.get("verMng");
    	while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), verBaseInfo);
    	}
    	boolean isExit = VerMngService.ifExit(verBaseInfo);
    	if(!isExit){
    		verBaseInfo.setLastUpdDateTime(IfspDateTime.getYYYYMMDDHHMMSS());
        	VerMngService.getInstance().add(verBaseInfo);
  
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
                    "[版本管理]--新增:版本号[appVer]=" + verBaseInfo.getAppVer() });
    	}else{
    		SnowExceptionUtil.throwErrorException("已有该设备类型的版本，不可重复增加");
    	}
    	
	}
}
