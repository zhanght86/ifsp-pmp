package com.ruimin.ifs.pmp.mchtAppMng.comp;

import java.util.Map;

import com.ruim.ifsp.utils.verify.IfspDataVerifyUtil;
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
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.MchtUserGroupBaseInfo;
import com.ruimin.ifs.pmp.mchtAppMng.process.service.MchtUserGroupMngService;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaseInfo;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtMngService;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;



@SnowDoc(desc = "商户用户组信息维护")
@ActionResource
public class MchtUserGroupMngAction extends SnowAction {
	String adCount = SysParamUtil.getParam(AdvertConstants.ADVERT_COUNT);
	@Action
	@SnowDoc(desc = "商户用户组信息查询")
	public PageResult queryMain(QueryParamBean queryBean) throws SnowException {
		String qgroupId = queryBean.getParameter("qgroupId");
		String qmchtId = queryBean.getParameter("qmchtId");
		String qdeviceType = queryBean.getParameter("qdeviceType");
		return MchtUserGroupMngService.getInstance().queryMain(qgroupId,qmchtId,qdeviceType,queryBean.getPage());
	}
	
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "商户用户组信息新增")
	public void add(Map<String, UpdateRequestBean> updateMap) throws Exception {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		MchtUserGroupBaseInfo mchtUserGroupBaseInfo = new MchtUserGroupBaseInfo();
    	UpdateRequestBean reqBean = updateMap.get("mchtUserGroupMng");
    	while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtUserGroupBaseInfo);
    	}
    	PbsMchtBaseInfo pbsMchtBaseInfo = MchtMngService.getInstance().queryByMchtId(mchtUserGroupBaseInfo.getMchtId());
    	if(IfspDataVerifyUtil.isBlank(pbsMchtBaseInfo)){
    		SnowExceptionUtil.throwErrorException("商户号不存在");
    	}
    	String deviceType = mchtUserGroupBaseInfo.getDeviceType();
		String groupId = mchtUserGroupBaseInfo.getMchtId();
		if("1".equals(deviceType)){
			groupId += "IOS";
		}
		if("2".equals(deviceType)){
			groupId += "Android";
		}
		MchtUserGroupBaseInfo mchtUserGroupBaseInfoQuery = MchtUserGroupMngService.getInstance().queryByGroupId(groupId);
    	if(IfspDataVerifyUtil.isBlank(mchtUserGroupBaseInfoQuery)){
    		mchtUserGroupBaseInfo.setGroupId(groupId);
    		mchtUserGroupBaseInfo.setUserCount(0);
        	MchtUserGroupMngService.getInstance().add(mchtUserGroupBaseInfo);
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
                    "[商户用户组信息管理]--新增:商户用户组号[groupId]=" + mchtUserGroupBaseInfo.getGroupId() });
    	}else{
    		SnowExceptionUtil.throwErrorException("改组信息已存在，不可重复添加");
    	}
	}
}
