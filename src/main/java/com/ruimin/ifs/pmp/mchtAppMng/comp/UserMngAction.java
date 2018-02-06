package com.ruimin.ifs.pmp.mchtAppMng.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.mng.process.service.RoleMngEntryService;
import com.ruimin.ifs.pmp.mchtAppMng.process.service.UserMngService;

@ActionResource
@SnowDoc(desc = "用户管理")
public class UserMngAction {
	/**
	 * 查询
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryMain(QueryParamBean queryBean) throws SnowException{
		PageResult result = null;
		/**查询条件*/
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String tlrno = sessionUserInfo.getTlrno();
		String brcode = sessionUserInfo.getBrCode();
		String roleId = RoleMngEntryService.getInstance().queryRoleByTlrno(tlrno);
		String quserName = queryBean.getParameter("quserName");
		String qmchtArtifId = queryBean.getParameter("qmchtArtifId");
		String qmchtSimpleName = queryBean.getParameter("qmchtSimpleName");
		String qmchtId = queryBean.getParameter("qmchtId");
		/**返回查询结果*/
		result = UserMngService.getInstance().queryMain(tlrno,brcode,roleId,quserName,qmchtArtifId,qmchtSimpleName,qmchtId, queryBean.getPage());
		SnowLog.getServerLog().info("用户信息查询: [ 记录总数："+result.getTotalCount()+" ]");
		return result;
	}
		
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryInfo(QueryParamBean queryBean) throws SnowException{
		PageResult result = null;
		/**查询条件*/
		String userId = queryBean.getParameter("userId");
		/**返回查询结果*/
		result = UserMngService.getInstance().queryInfo(userId, queryBean.getPage());
		SnowLog.getServerLog().info("店员信息查询: [ 记录总数："+result.getTotalCount()+" ]");
		return result;
	}
}
