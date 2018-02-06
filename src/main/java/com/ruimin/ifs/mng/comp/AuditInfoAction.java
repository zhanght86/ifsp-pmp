/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: AuditProAction.java
 * Author:   MJ
 * Date:     2015年11月30日 上午9:46:53
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.mng.comp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
import org.apache.commons.lang.StringUtils;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.mng.process.bean.ImpAuditInfo;
import com.ruimin.ifs.mng.process.bean.ImpAuditPro;
import com.ruimin.ifs.mng.process.service.AuditInfoService;
import com.ruimin.ifs.mng.process.service.RoleMngEntryService;
import com.ruimin.ifs.po.TblRole;

/**
 * 〈审核任务维护〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author MJ
 */
public class AuditInfoAction extends SnowAction {

	/**
	 * 功能描述: 审核任务查询 <br>
	 * 〈功能详细描述〉
	 *
	 * @param updateMap
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@SnowDoc(desc = "审核任务查询")
	public PageResult query(QueryParamBean queryBean) throws SnowException {
		return AuditInfoService.getInstance().query(queryBean.getPage());
	}

	/**
	 * 功能描述: 审核流程查询 <br>
	 * 〈功能详细描述〉
	 *
	 * @param updateMap
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@SnowDoc(desc = "审核流程查询")
	public PageResult queryPro(QueryParamBean queryBean) throws SnowException {
		String auditId = queryBean.getParameter("auditId", "");
		return AuditInfoService.getInstance().queryPro(auditId, queryBean.getPage());
	}

	/**
	 * 功能描述: 审核任务修改<br>
	 * 〈功能详细描述〉
	 *
	 * @param updateMap
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "审核流程新增")
	public void update(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取数据集
		UpdateRequestBean bean = updateMap.get("AuditInfo");
		Map<String, String> map = bean.next();
		UpdateRequestBean reqBean = updateMap.get("AuditPro");
		// 获取会话信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 初始化对象集合
		List<ImpAuditPro> auditPros = new ArrayList<ImpAuditPro>();
		while (reqBean.hasNext()) {
			// 初始化对象
			ImpAuditPro pro = new ImpAuditPro();
			// 为对象属性赋值
			DataObjectUtils.map2bean(reqBean.next(), pro);
			// 放入集合
			auditPros.add(pro);
		}
		// 修改操作
		AuditInfoService.getInstance().update(auditPros);
		// 日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"审核任务流程修改:编号=" + map.get("AuditId") });
	}

	/**
	 * 功能描述: 审核任务状态修改 <br>
	 * 〈功能详细描述〉
	 *
	 * @param updateMap
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "审核任务状态修改")
	public void useStatChg(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("AuditInfo");
		String foo = reqBean.getParameter("foo");
		ImpAuditInfo auditInfo = new ImpAuditInfo();
		AuditInfoService service = AuditInfoService.getInstance();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), auditInfo);
		}
		auditInfo.setAuditStat(foo);
		service.update(auditInfo);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";
		if ("1".equals(foo)) {
			msg = "更改审核任务为启用,id=" + auditInfo.getAuditId();
		} else {
			msg = "更改审核任务为停用,id=" + auditInfo.getAuditId();
		}
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}

	/**
	 * 根据roleId获取roleName
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 */
	public static String getAuditRoleName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		if (StringUtils.isNotBlank(key)) {
			String auditRoleName = " ";
			String[] roleIds = key.split(",");
			for (String roleId : roleIds) {
				TblRole role = RoleMngEntryService.getInstance().checkId(roleId);
				auditRoleName += role.getRoleName() + ",";
			}
			return auditRoleName.substring(0, auditRoleName.length() - 1);
		}
		return key;
	}

}
