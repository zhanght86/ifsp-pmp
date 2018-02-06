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
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.MchtUserVO;
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.UserGroupDtlBaseInfo;
import com.ruimin.ifs.pmp.mchtAppMng.process.service.MchtUserGroupMngService;
import com.ruimin.ifs.pmp.mchtAppMng.process.service.UserGroupDtlMngService;
import com.ruimin.ifs.pmp.mchtAppMng.process.service.UserMngService;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;



@SnowDoc(desc = "用户组信息明细维护")
@ActionResource
public class UserGroupDtlMngAction extends SnowAction {
	String adCount = SysParamUtil.getParam(AdvertConstants.ADVERT_COUNT);
	@Action
	@SnowDoc(desc = "用户组信息明细查询")
	public PageResult queryMain(QueryParamBean queryBean) throws SnowException {
		String qgroupId = queryBean.getParameter("qgroupId");
		String qmchtId = queryBean.getParameter("qmchtId");
		String qdeviceType = queryBean.getParameter("qdeviceType");
		String quserId = queryBean.getParameter("quserId");
		String quserName = queryBean.getParameter("quserName");
		return UserGroupDtlMngService.getInstance().queryMain(qgroupId,qmchtId,qdeviceType,quserId,quserName,queryBean.getPage());
	}
	
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "用户组信息明细新增")
	public void add(Map<String, UpdateRequestBean> updateMap) throws Exception {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		UserGroupDtlBaseInfo userGroupDtlBaseInfo = new UserGroupDtlBaseInfo();
    	UpdateRequestBean reqBean = updateMap.get("userGroupDtlMng");
    	while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), userGroupDtlBaseInfo);
    	}
    	MchtUserGroupBaseInfo mchtUserGroupBaseInfo = MchtUserGroupMngService.getInstance().queryByGroupId(userGroupDtlBaseInfo.getGroupId());
    	if(IfspDataVerifyUtil.isBlank(mchtUserGroupBaseInfo)){
    		SnowExceptionUtil.throwErrorException("组号不存在");
    	}
    	MchtUserVO mchtUserVO = UserMngService.getInstance().queryInfoById(userGroupDtlBaseInfo.getUserId());
    	if(IfspDataVerifyUtil.isBlank(mchtUserVO)){
    		SnowExceptionUtil.throwErrorException("用户号不存在");
    	}else{
    		String userId = mchtUserVO.getUserId();
    		if("1".equals(mchtUserVO.getUserType())){//用户类型：0-店长，1-店员
    			userId = UserGroupDtlMngService.getInstance().queryUserIdMngByAss(userId);
    		}
    		String mchtId = UserGroupDtlMngService.getInstance().queryMchtIdByUserId(userId);
    		if(!mchtUserGroupBaseInfo.getMchtId().equals(mchtId)){
    			SnowExceptionUtil.throwErrorException("该用户不属于该组的商户，用户号与商户号不匹配");
    		}
    	}
    	
    	UserGroupDtlBaseInfo userGroupDtlBaseInfoQuery = UserGroupDtlMngService.getInstance().queryIfExit(userGroupDtlBaseInfo);
    	if(IfspDataVerifyUtil.isNotBlank(userGroupDtlBaseInfoQuery)){
    		SnowExceptionUtil.throwErrorException("改组信息已存在，不可重复添加");
    	}
    	UserGroupDtlMngService.getInstance().add(userGroupDtlBaseInfo);
    	sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
    			"[用户组信息明细管理]--新增:组用户[groupId]=" + userGroupDtlBaseInfo.getGroupId()+"，[userId]=" + userGroupDtlBaseInfo.getUserId() });
	}
}
