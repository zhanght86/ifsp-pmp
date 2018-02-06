/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.platMng.process.service 
 * FileName: AuditNewInfoService.java
 * Author:   chenqilei
 * Date:     2016年8月16日 下午4:17:35
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年8月16日下午4:17:35                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.platMng.process.service;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.Page;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月16日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
public class AuditNewInfoService {
	/**
	 * 审核实例 单例
	 * 
	 * @return
	 * @throws SnowException
	 */
	public static AuditNewInfoService getInstance() throws SnowException {
		return ContextUtil.getSingleService(AuditNewInfoService.class);
	}

	/**
	 * 审核消息的全查询
	 * 
	 * @param applyDateTime
	 *            审核日期时间 [模糊查询]
	 * @param applyTlrNo
	 *            申请人 [模糊查询]
	 * @param auditType
	 *            审核业务类型 [精确查询]
	 * @param auditState
	 *            审核状态 [精确查询]
	 * @param currentBrCode
	 * @param page
	 *            分页查询
	 * @return 满足条件的审核信息
	 * @throws SnowException
	 */
	public PageResult queryByAll(String applyDateTime, String applyTlrNo, String auditType, String auditState,
			String tlrno, String currentBrCode, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao
				.selectList("com.ruimin.ifs.pmp.platMng.rql.auditNewInfo.queryByAll",
						RqlParam.map()
								.set("applyDateTime",
										StringUtils.isEmpty(applyDateTime) ? "" : "%" + applyDateTime + "%")
								.set("applyTlrNo", StringUtils.isEmpty(applyTlrNo) ? "" : "%" + applyTlrNo + "%")
								.set("auditType", StringUtils.isEmpty(auditType) ? "" : auditType)
								.set("auditState", StringUtils.isEmpty(auditState) ? "" : auditState)
								.set("tlrno", StringUtils.isEmpty(tlrno) ? "" : tlrno)
								.set("currentBrCode", currentBrCode),
						page);
	}

	/**
	 * 根据审核信息编号查询审核信息记录表中的审核步骤
	 * 
	 * @param auditId
	 *            审核信息编号
	 * @param page
	 *            分页查询
	 * @return 审核信息记录表中的审核步骤
	 * @throws SnowException
	 */
	public PageResult queryByAuditId(String auditId, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();

		return dao.selectList("com.ruimin.ifs.pmp.platMng.rql.auditNewInfo.queryByAuditId",
				RqlParam.map().set("auditId", StringUtils.isEmpty(auditId) ? "" : auditId), page);
	}

	/**
	 * 主页只显示当前用户登录的该当前用户审核的 未审核的数据
	 * 
	 * @param tlrno
	 *            操作员编号
	 * @param string 
	 * @param page
	 *            分页查询
	 * @return 显示当前用户登录的该当前用户审核的数据
	 * @throws SnowException
	 */
	public PageResult queryByOnlyCheck(String tlrno,  String currentBrCode, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.platMng.rql.auditNewInfo.queryByOnlyCheck",
				RqlParam.map().set("tlrno", tlrno).set("currentBrCode", currentBrCode), page);
	}
	/*
	 * public SaveUserData queryByCheck(String tlrno, String auditId) throws
	 * SnowException{ DBDao dao = DBDaos.newInstance(); SaveUserData
	 * saveUserData = new SaveUserData();
	 * 
	 * saveUserData=(SaveUserData)
	 * dao.selectOne("com.ruimin.ifs.pmp.platMng.rql.auditNewInfo.queryByCheck",
	 * RqlParam.map() .set("tlrno", tlrno) .set("auditId", auditId.trim()));
	 * return saveUserData; }
	 */
	/**
	 * 根据操作员ID得到角色名称
	 * 
	 * @param userName
	 * @return
	 * @throws SnowException
	 */
	/*
	 * public String queryByUserName(String userName) throws SnowException{
	 * DBDao dao = DBDaos.newInstance(); return (String) dao.selectOne(
	 * "com.ruimin.ifs.pmp.platMng.rql.auditNewInfo.queryByUserName",RqlParam.
	 * map() .set("userName", userName)); }
	 */
	/**
	 * 根据角色ID+审核流程编号得到审核步骤
	 * 
	 * @param userName
	 * @param auditId
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	/*
	 * public int queryByRoleId(String auditProcId, String roleId) throws
	 * SnowException{ DBDao dao = DBDaos.newInstance(); return (Integer)
	 * dao.selectOne("", RqlParam.map() .set("auditProcId",
	 * StringUtils.isEmpty(auditProcId) ? "" : auditProcId) .set("roleId",
	 * StringUtils.isEmpty(roleId) ? "" : roleId)); }
	 */
	/**
	 * 根据审核信息编号+审核步骤-->到审核信息记录表中查询对应的状态
	 * 
	 * @param stepId
	 * @param auditId
	 * @return
	 * @throws SnowException
	 */

	/*
	 * public String queryByStepId(int stepId, String auditId) throws
	 * SnowException{ DBDao dao = DBDaos.newInstance(); return (String)
	 * dao.selectOne("", RqlParam.map() .set("auditId",
	 * StringUtils.isEmpty(auditId)? "" : auditId) .set("stepId", stepId)); }
	 */
	/**
	 * 根据审核角色ID查询审核角色名称
	 * 
	 * @param prodIds
	 *            审核角色ID
	 * @return 审核角色名称
	 * @throws SnowException
	 */
	public List<Object> queryRoleName(String prodIds) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String[] prodIdArray = prodIds.split(",");

		return dao.selectList("com.ruimin.ifs.pmp.platMng.rql.auditNewInfo.queryRoleName",
				RqlParam.map().set("prodIdArray", prodIdArray));
	}
}
