package com.ruimin.ifs.mng.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.core.util.constant.SnowErrorCode;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.utils.JSONUtil;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.mng.process.service.OrgService;
import com.ruimin.ifs.mng.process.service.RoleMngEntryService;
import com.ruimin.ifs.po.TblBctl;
import com.ruimin.ifs.po.TblResInfo;
import com.ruimin.ifs.po.TblRole;
import com.ruimin.ifs.util.BaseUtil;
import com.ruimin.ifs.util.SeqNoGenUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SnowDoc(desc = "角色管理")
@ActionResource
public class RoleMngEntryAction extends SnowAction {

	@Action
	@SnowDoc(desc = "分页查询角色信息")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {

		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 查询标志
		String param = queryBean.getParameter("param", "");
		if ("detail".equals(param)) {
			String tlrno = queryBean.getParameter("tlrno", "");
			return RoleMngEntryService.getInstance().queryRoleByTlrno(tlrno, queryBean.getPage());
		}
		
		Map<String, String> queryMap = new HashMap<String, String>();
		// 角色号
		String roleId = queryBean.getParameter("qRoleId");
		// 角色名
		String roleName = queryBean.getParameter("qRoleName", "");
		// 状态
		String status = queryBean.getParameter("qStatus", "");
		// 机构号
		String brcode = queryBean.getParameter("brcode");
		TblBctl bctl = OrgService.getInstance().queryOrgById(brcode);
		// 页面传入机构级别
		String brclass = queryBean.getParameter("qBrclass", "");
		// 当前操作员机构级别
		String currBrClass = sessionUserInfo.getBrClass();
		//获取选中列表操作员编号，用于反显操作员角色信息
		String tlrno = queryBean.getParameter("tlrno", "");
		queryMap.put("roleId", StringUtils.isBlank(roleId) ? "" : roleId);
		queryMap.put("roleName", StringUtils.isBlank(roleName) ? "" : "%" + roleName + "%");
		queryMap.put("status", status);
		queryMap.put("brclass", brclass);
		queryMap.put("currBrClass", currBrClass);
		queryMap.put("brCodeClass", bctl == null ? "" : bctl.getBrclass());
		queryMap.put("tlrno",tlrno==null ? "" : tlrno);
		//如果是新增或者修改 使用另一个sql 语句 
		if("add".equals(param) || "mod".equals(param)){
			return RoleMngEntryService.getInstance().listRole(queryMap, queryBean.getPage());
		}		
		return RoleMngEntryService.getInstance().listRole1(queryMap, queryBean.getPage());
	}

	/**
	 * 角色人员权限查询
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "角色人员权限查询")
	public PageResult queryStaffRoleRef(QueryParamBean queryBean) throws SnowException {
		String roleId = queryBean.getParameter("roleId");
		return RoleMngEntryService.getInstance().queryStaffRoleRef(roleId, queryBean.getPage());
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增/修改 角色")
	public void addRoleFunc(Map<String, UpdateRequestBean> updateMap) throws SnowException {

		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		UpdateRequestBean reqBean = updateMap.get("RoleMngEntry");
		String funcStr = reqBean.getParameter("funcStr");
		String roleId = reqBean.getParameter("roleId");
		String[] ss = {};
		if (StringUtils.isNotBlank(funcStr)) {
			ss = reqBean.getParameter("funcStr").split(","); // 勾选的菜单权限
		}
		TblRole tblRole = new TblRole();
		RoleMngEntryService roleMngEntryService = RoleMngEntryService.getInstance();
		String msg = "";
		if (StringUtils.isBlank(roleId)) {
			SnowLog.getLogger(this.getClass()).info("============ 新增角色 ===========");
			// 新增
			DataObjectUtils.map2bean(reqBean.next(), tblRole);
			tblRole.setRoleId(SeqNoGenUtil.genRoleId());
			tblRole.setStatus("00");
			tblRole.setIsLock("0");
			tblRole.setCrtDt(BaseUtil.getCurrentDate());
			tblRole.setLastUpdOper(sessionUserInfo.getTlrno());
			tblRole.setLastUpdTms(BaseUtil.getCurrentDateTime());
			roleMngEntryService.saveRoleMngEntry(tblRole);
			msg = "[角色管理 ]--新增角色信息 :角色编号[RoleId]=" + tblRole.getRoleId();
		} else {
			SnowLog.getLogger(this.getClass()).info("============ 修改角色 ===========");
			// 修改
			// 管理岗必须包含系统维护,系统配置的所有功能
			if (null != roleId && roleId.equals("111")) {
				// 1005:平台管理
				// 1005001:机构管理,1005010:系统公告,1005002:地区管理,1005005:操作员日志,1005003:角色管理,1005004:操作员管理,1005006:菜单管理
				String[] reg = "1005,1005001,1005010,1005002,1005005,1005003,1005004,1005006".split(",");
				String roleList = funcStr;
				if (roleList.isEmpty()) {
					SnowExceptionUtil.throwWarnException(SnowErrorCode.MESSAGE_WEB_0042);
				}
				try {
					for (int i = 0; i < reg.length; i++) {
						if (roleList.contains(reg[i])) {
							continue;
						} else {
							SnowExceptionUtil.throwWarnException(SnowErrorCode.MESSAGE_WEB_0042);
						}
					}

				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
			}
			SnowLog.getLogger(this.getClass()).info("============ 删除原角色权限 ===========");
			tblRole = roleMngEntryService.checkId(roleId);
			roleMngEntryService.deleteRoleResInf(roleId); // 删除老权限
			msg = "[角色管理 ]--修改角色信息 :角色编号[RoleId]=" + tblRole.getRoleId();
			SnowLog.getLogger(this.getClass()).info("============ 修改角色信息 ===========");
			tblRole.setLastUpdOper(sessionUserInfo.getTlrno());
			tblRole.setLastUpdTms(BaseUtil.getCurrentDateTime());
			roleMngEntryService.updateRoleMngEntry(tblRole);
		}
		SnowLog.getLogger(this.getClass()).info("============ 设置角色权限 ===========");
		List<TblResInfo> list = new ArrayList<TblResInfo>();
		TblResInfo tblResInfo = null;
		int size = ss.length;
		for (int i = 0; i < size; i++) {
			tblResInfo = new TblResInfo();
			tblResInfo.setId(ContextUtil.getUUID());
			tblResInfo.setFuncid(ss[i]);
			tblResInfo.setRoleId(String.valueOf(tblRole.getRoleId()));
			list.add(tblResInfo);
		}
		if (list.size() > 0) {
			roleMngEntryService.saveResInfo(list);
			msg += ",角色权限新增,角色编号[RoleId]=" + roleId == null ? tblRole.getRoleId() : roleId;
		}

		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}

	/**
	 * 修改角色状态为有效无效
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "有效/无效")
	public void setStatus(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		UpdateRequestBean reqBean = updateMap.get("RoleMngEntry");
		String foo = reqBean.getParameter("foo");
		String roleId = reqBean.getParameter("roleId");
		TblRole tblRole = new TblRole();
		RoleMngEntryService roleMngEntryService = RoleMngEntryService.getInstance();
		tblRole = roleMngEntryService.checkId(roleId);
		tblRole.setStatus(foo);
		int count = roleMngEntryService.getInstance().queryOprById(roleId);

		if (count > 0 && "99".equals(foo)) {
			SnowExceptionUtil.throwWarnException("WEB_E0045", "请先删除该角色下的所有操作员！");
		}
		tblRole.setLastUpdOper(sessionUserInfo.getTlrno());
		tblRole.setLastUpdTms(BaseUtil.getCurrentDateTime());
		roleMngEntryService.updateRoleMngEntry(tblRole);

		String msg = "";
		if ("99".equals(foo)) {
			msg = "[角色管理 ]--更改角色角色为有效 :角色编号[RoleId]=" + tblRole.getRoleId();
		} else {
			msg = "[角色管理 ]--更改角色角色为无效 :角色编号[RoleId]=" + tblRole.getRoleId();
		}
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });

	}

	/**
	 * 设置角色权限
	 * 
	 * @param updateMap
	 * @return
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "设置角色权限 查询角色拥有的权限")
	public Map<String, Object> setRoleMng(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("RoleMngEntry");
		String roleId = reqBean.getParameter("roleId");
		RoleMngEntryService roleMngEntryService = RoleMngEntryService.getInstance();
		List<Object> list = roleMngEntryService.queryResInf(roleId);
		List<String> returnList = new ArrayList<String>();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			TblResInfo tblResInfo = (TblResInfo) list.get(i);
			returnList.add(tblResInfo.getFuncid());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("funcs", JSONUtil.toJSON(returnList).replace("\"", "").replace("[", "").replace("]", ""));
		return map;
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "role修改")
	public void updateRole(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		UpdateRequestBean reqBean = updateMap.get("RoleMngEntry");
		TblRole tblRole = new TblRole();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tblRole);
		}
		tblRole.setLastUpdOper(sessionUserInfo.getTlrno());
		tblRole.setLastUpdTms(BaseUtil.getCurrentDateTime());
		RoleMngEntryService.getInstance().updateRoleMngEntry(tblRole);

		String msg = "[角色管理 ]--修改角色信息 :角色编号[RoleId]=" + tblRole.getRoleId();
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}
}
