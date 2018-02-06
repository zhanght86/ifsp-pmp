/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.platMng.comp 
 * FileName: CheckNewInfoAction.java
 * Author:   chenqilei
 * Date:     2016年8月16日 下午4:11:09
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年8月16日下午4:11:09                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.platMng.comp;

import java.util.List;

import javax.servlet.ServletRequest;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.platMng.process.bean.RoleNameInfo;
import com.ruimin.ifs.pmp.platMng.process.service.AuditNewInfoService;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月16日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
public class AuditNewInfoAction {
	/**
	 * 审核信息全查询
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action()
	@SnowDoc(desc = "审核信息全查询")
	public PageResult queryByAll(QueryParamBean queryBean) throws SnowException {
		// 获取审核日期时间
		String applyDateTime = queryBean.getParameter("qapplyDateTime");
		// 获取申请人
		String applyTlrNo = queryBean.getParameter("qapplyTlrNo");
		// 获取审核业务类型
		String auditType = queryBean.getParameter("qauditType");
		// 获取审核状态
		String auditState = queryBean.getParameter("qauditState");
		// 获取当前操作员信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String tlrno = sessionUserInfo.getTlrno();
		return AuditNewInfoService.getInstance().queryByAll(applyDateTime, applyTlrNo, auditType, auditState, tlrno,
				sessionUserInfo.getBrCode(), queryBean.getPage());
	}

	/**
	 * 根据审核信息编号到审核记录信息表中查出该条审核记录编号下对应的所有审核步骤
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@SnowDoc(desc = "查找审核步骤")
	public PageResult queryByAuditId(QueryParamBean queryBean) throws SnowException {
		// 获取审核信息编号

		String auditId = queryBean.getParameter("auditId");
		return AuditNewInfoService.getInstance().queryByAuditId(auditId, queryBean.getPage());
	}

	/**
	 * 主页只显示当前用户登录的该当前用户审核的 未审核的数据
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryByOnlyCheck(QueryParamBean queryBean) throws SnowException {
		// 获取当前操作员信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String tlrno = sessionUserInfo.getTlrno();
		return AuditNewInfoService.getInstance().queryByOnlyCheck(tlrno, sessionUserInfo.getBrCode(),queryBean.getPage());
	}

	/**
	 * 根据该条信息的审核信息编号查询审核信息记录表中状态为未审核的数据对应的角色ID
	 * 然后根据这个角色ID到审核流程步骤表中查看该操作员编号下是否有该角色ID
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	/*
	 * public SaveUserData queryByCheck(QueryParamBean queryBean) throws
	 * SnowException{
	 * 
	 * //获取操作员编号 SessionUserInfo sessionUserInfo =
	 * SessionUserInfo.getSessionUserInfo(); String tlrno =
	 * sessionUserInfo.getTlrno(); //获取审核信息编号 String auditId =
	 * queryBean.getParameter("auditId"); SaveUserData save =
	 * CheckNewInfoService.getInstance().queryByCheck(tlrno, auditId); return
	 * save; }
	 */
	/*
	 * public String queryByUserName(QueryParamBean queryBean) throws
	 * SnowException { // 创建用户 SessionUserInfo sessionUserInfo =
	 * SessionUserInfo.getSessionUserInfo(); // 获取审核信息的用户名 String userName =
	 * sessionUserInfo.getTlrno(); // 根据操作员编号查出角色iD String roleId =
	 * CheckNewInfoService.getInstance().queryByUserName(userName); // 获取审核流程编号
	 * String auditProcId = queryBean.getParameter("auditProcId"); // 获取审核信息编号
	 * String auditId = queryBean.getParameter("auditId"); if
	 * (StringUtils.isEmpty(roleId)) { // 根据审核流程编号+角色ID查询户审核步骤 int stepId =
	 * CheckNewInfoService.getInstance().queryByRoleId(auditProcId, roleId); //
	 * 根据审核信息编号+审核步骤-->到审核信息记录表中查询对应的状态 String auditState =
	 * CheckNewInfoService.getInstance().queryByStepId(stepId, auditId); if
	 * ("00".equals(auditState)) { // 该条数据对应的状态是未审核 那么查看他上一步的审核状态 stepId =
	 * stepId - 1; if (stepId != 0) { String auditState1 =
	 * CheckNewInfoService.getInstance().queryByStepId(stepId, auditId); if
	 * ("01".equals(auditState1)) { // 如果上一条数据的状态是审核通过那么允许该用户审核 把他设置为1
	 * 写到DATASET中 String agreeCheck="1"; return agreeCheck; } else { //
	 * 如果上一条数据的状态是未审核那么就不让该用户审核 把他设置为2 写到DATASET中 String agreeCheck="2"; return
	 * agreeCheck; } } }else{ //那么该条数据的审核状态是审核通过 不允许该用户审核 把他设置为2写到DATASET中
	 * String agreeCheck="2"; return agreeCheck; } } return null; }
	 */
	/**
	 * 根据审核角色ID查询审核角色名称
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String getName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		StringBuffer strBuf = new StringBuffer();
		if (StringUtil.isEmpty(key)) {
			return strBuf.toString();
		}
		List<Object> prodList = AuditNewInfoService.getInstance().queryRoleName(key);

		if (prodList == null || prodList.size() == 0) {
			return strBuf.toString();
		}
		for (Object prodObj : prodList) {
			RoleNameInfo prod = (RoleNameInfo) prodObj;
			strBuf.append(prod.getRoleName()).append(",");
		}
		return strBuf.toString().substring(0, strBuf.toString().length() - 1);
	}
}