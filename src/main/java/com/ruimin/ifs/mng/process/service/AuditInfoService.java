/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: AuditService.java
 * Author:   MJ
 * Date:     2015年11月19日 下午4:07:31
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.mng.process.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.Page;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.mng.process.bean.ImpAuditInfo;
import com.ruimin.ifs.mng.process.bean.ImpAuditPro;
import com.ruimin.ifs.util.StringUtil;

/**
 * 〈审核流程维护〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author MJ
 */
public class AuditInfoService extends SnowService {

	public static AuditInfoService getInstance() throws SnowException {
		return ContextUtil.getSingleService(AuditInfoService.class);
	}

	/**
	 * 功能描述: 审核任务查询<br>
	 */
	public PageResult query(Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.auditInfo.query", page);
	}

	/**
	 * 功能描述: 审核流程查询<br>
	 */
	public PageResult queryPro(String auditId, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.auditInfo.queryPro",
				RqlParam.map().set("auditId", StringUtils.isBlank(auditId) ? "" : auditId), page);
	}

	/**
	 * 功能描述: 审核任务修改<br>
	 */
	public void update(ImpAuditInfo auditInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(auditInfo);
	}

	/**
	 * 功能描述: 审核任务流程修改<br>
	 */
	public void update(List<ImpAuditPro> auditPros) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		for (ImpAuditPro pro : auditPros) {
			dao.update(pro);
		}
	}

	/**
	 * 功能描述: 查询有权操作商户信息的角色 <br>
	 */
	public ImpAuditPro queryMchtOprRole(String roleId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (ImpAuditPro) dao.selectOne("com.ruimin.ifs.mng.rql.auditInfo.queryMchtOprRole",
				RqlParam.map().set("roleId", StringUtils.isBlank(roleId) ? "" : "%" + roleId + "%"));
	}

	/**
	 * 功能描述: 查询 <br>
	 */
	public ImpAuditInfo query(String id) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.select(ImpAuditInfo.class, id);
	}

	/**
	 * 功能描述: 根据流程编号查询当前角色下级审核角色<br>
	 */
	public ImpAuditPro queryNextAuditRole(String auditId, String auditLevel) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (ImpAuditPro) dao.selectOne("com.ruimin.ifs.mng.rql.auditInfo.queryNextAuditRole",
				RqlParam.map().set("auditId", StringUtil.isBlank(auditId) ? "" : auditId).set("auditLevel",
						StringUtil.isBlank(auditLevel) ? "" : auditLevel));
	}

}
