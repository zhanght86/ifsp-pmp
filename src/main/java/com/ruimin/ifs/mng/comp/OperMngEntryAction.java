package com.ruimin.ifs.mng.comp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.encrypt.EncryptUtil;
import com.ruimin.ifs.core.encrypt.EncryptUtil.EncryptType;
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
import com.ruimin.ifs.framework.core.WebConstants;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.utils.SysParamUtil;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.mng.process.service.OperMngEntryService;
import com.ruimin.ifs.mng.process.service.SysParamsSecService;
import com.ruimin.ifs.po.TblStaff;
import com.ruimin.ifs.po.TblStaffRoleRel;

@SnowDoc(desc = "操作员管理查询")
@ActionResource
public class OperMngEntryAction extends SnowAction {
	private static final String DEF_PASSWORD = "DEFAULT_PWD";
	
	@Action
	@SnowDoc(desc = "操作员查询")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {

		// 操作员登录session
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		Map brhs = sessionUserInfo.getExtenMap();
		List<String> list = (List<String>) brhs.get("brhs");

		Map<String, String> queryMap = new HashMap<String, String>();
		String brcode = queryBean.getParameter("qbrcode");
		String tlrno = queryBean.getParameter("qtlrno");
		String tlrName = "%" + queryBean.getParameter("qtlrname") + "%";
		String flag = queryBean.getParameter("qflag");
		String isLock = queryBean.getParameter("qisLock");
		// if(("0000").equals(brcode)){
		// brcode = "";
		// }
		queryMap.put("brCode", sessionUserInfo.getBrCode());
		// 根据操作员机构编码查找其所有下级的机构
		StringBuffer sb = new StringBuffer();
		if (list != null && list.size() == 1) {
			sb.append(list.get(0)).append(",");
		} else {
			for (String brCode : list) {
				sb.append("'" + brCode + "',");
			}
		}
		queryMap.put("brCode", sb.toString().substring(0, sb.length() - 1));
		queryMap.put("brcode", brcode);
		queryMap.put("tlrno", tlrno);
		queryMap.put("tlrName", tlrName);
		queryMap.put("flag", flag);
		queryMap.put("isLock", isLock);
		// 按条件搜索用户表
		return OperMngEntryService.getInstance().queryListTmp(queryMap, queryBean.getPage());
	}

	@SuppressWarnings("rawtypes")
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增 / 修改")
	public void saveOrUpdate(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("OperMngEntry");
		String paramStat = reqBean.getParameter("paramStat");
		String tlrno = reqBean.getParameter("tlrno");
		String s = reqBean.getParameter("s");
        String passwd = reqBean.getParameter("password");
		
		SnowLog.getLogger(OperMngEntryAction.class).info("密码："+passwd);
		
		TblStaff tblStaff = new TblStaff();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tblStaff);
		}
		OperMngEntryService operMngEntryService = OperMngEntryService.getInstance();
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";
		if ("add".equals(paramStat)) {
			// 新增
			if (null != operMngEntryService.checkId(tblStaff.getTlrno())) {
				SnowExceptionUtil.throwWarnException(SnowErrorCode.MESSAGE_WEB_0034, new String[] { "操作员号" });
			}
			String encMode = SysParamUtil.getInstance().getString("PSWD.ENC_MODE", "MD5"); // 获取系统参数配置的加密方式
			if (StringUtils.isBlank(encMode)) {
				encMode = "MD5";
			}
			//String passwd =  SysParamsSecService.getInstance().queryValueByMagicId(DEF_PASSWORD);
			if("MD5".equals(encMode)){
				tblStaff.setPassword(EncryptUtil.getEncrypt(EncryptType.MD5).encrypt(passwd));
			} else {
				tblStaff.setPassword(EncryptUtil.getEncrypt(EncryptType.AES).encrypt(passwd));
			}

			tblStaff.setStatus("0");
			tblStaff.setFlag("00");
			tblStaff.setSessionId(sessionUserInfo.getSessionId());
			tblStaff.setPswderrcnt(0);
			tblStaff.setTotpswderrcnt(0);
			tblStaff.setPasswdenc(encMode);
			tblStaff.setIsLock("0");
			tblStaff.setIsLockModify("1");
			operMngEntryService.saveOperMngEntry(tblStaff);
			msg = "[操作员管理 ]--新增操作员信息 :操作员编号[Tlrno]=" + tblStaff.getTlrno();
		} else {
			String tlrname = tblStaff.getTlrName();
			String brcode = tblStaff.getBrcode();

			String workNo = tblStaff.getWorkNo();
			String phone = tblStaff.getPhone();
			String mobile = tblStaff.getMobile();
			String email = tblStaff.getEmail();

			tblStaff = operMngEntryService.checkId(tblStaff.getTlrno());
			tblStaff.setTlrName(tlrname);
			tblStaff.setBrcode(brcode);

			if (workNo != "") {
				tblStaff.setWorkNo(workNo);
			}
			if (phone != "") {
				tblStaff.setPhone(phone);
			}
			if (mobile != "") {
				tblStaff.setMobile(mobile);
			}
			if (email != "") {
				tblStaff.setEmail(email);
			}

			operMngEntryService.updateOperMngEntry(tblStaff);
			msg = "[操作员管理 ]--修改操作员信息 :操作员编号[Tlrno]=" + tblStaff.getTlrno();
		}
		operMngEntryService.deleteStaffRoleRel(tlrno); // 删除老权限
		// 增加用户岗位权限
		String[] ss = s.split(",");
		List lists = Arrays.asList(ss);
		// admin管理员必须包含管理岗
		if ("admin".equals(tlrno) && !lists.contains("111")) {
			SnowExceptionUtil.throwWarnException(SnowErrorCode.MESSAGE_WEB_0043);
		}
		int size = lists.size();
		List<TblStaffRoleRel> list = new ArrayList<TblStaffRoleRel>();
		for (int i = 0; i < size; i++) {
			TblStaffRoleRel tblStaffRoleRel = new TblStaffRoleRel();
			tblStaffRoleRel.setId(ContextUtil.getUUID());
			tblStaffRoleRel.setTlrno(tlrno);
			tblStaffRoleRel.setRoleId(ss[i]);
			tblStaffRoleRel.setStatus("1");
			list.add(tblStaffRoleRel);
		}
		operMngEntryService.insertStaffRoleRel(list);

		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "启用/停用")
	public void modStat(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("OperMngEntry");
		String foo = reqBean.getParameter("foo");
		TblStaff tblStaff = new TblStaff();
		OperMngEntryService operMngEntryService = OperMngEntryService.getInstance();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tblStaff);
		}
		tblStaff = operMngEntryService.checkId(tblStaff.getTlrno());
		if ("admin".equals(tblStaff.getTlrno())) {
			SnowExceptionUtil.throwWarnException(WebConstants.MESSAGE_WEB_0044, new String[] { "设置" });
		}
		// tblStaff.setFlag(Integer.parseInt(foo));
		tblStaff.setFlag(foo);

		operMngEntryService.updateOperMngEntry(tblStaff);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";
		if ("00".equals(foo)) {
			msg = "[操作员管理 ]--更改操作员状态为启用 :操作员编号[Tlrno]=" + tblStaff.getTlrno();
		} else {
			msg = "[操作员管理 ]--更改操作员状态为停用 :操作员编号[Tlrno]=" + tblStaff.getTlrno();
		}
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "解锁")
	public void unLock(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("OperMngEntry");
		String foo = reqBean.getParameter("foo");
		TblStaff tblStaff = new TblStaff();
		OperMngEntryService operMngEntryService = OperMngEntryService.getInstance();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tblStaff);
		}
		tblStaff = operMngEntryService.checkId(tblStaff.getTlrno());
		tblStaff.setIsLock(foo);
		tblStaff.setLockReason("");
		tblStaff.setIsLockModify("1");
		operMngEntryService.updateOperMngEntry(tblStaff);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "[操作员管理 ]--解锁用户状态 :操作员编号[Tlrno]=" + tblStaff.getTlrno();
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "删除")
	public void delete(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("OperMngEntry");
		String foo = reqBean.getParameter("foo");
		if ("00000199972".equals(foo)) {
			SnowExceptionUtil.throwWarnException("超级管理员不允许删除");
		}
		OperMngEntryService.getInstance().deleteOperMngEntryById(foo);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "[操作员管理 ]--删除用户 :操作员编号[Tlrno]=" + foo;
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "重置密码")
	public void restPassword(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("OperMngEntry");
		String foo = reqBean.getParameter("foo");
		TblStaff tblStaff = new TblStaff();
		OperMngEntryService operMngEntryService = OperMngEntryService.getInstance();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tblStaff);
		}
		tblStaff = operMngEntryService.checkId(tblStaff.getTlrno());
		String passwd = SysParamsSecService.getInstance().queryValueByMagicId(DEF_PASSWORD);
		tblStaff.setPassword(passwd);

		operMngEntryService.updateOperMngEntry(tblStaff);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "[操作员管理 ]--重置用户密码 :操作员编号[Tlrno]=" + foo;
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "强制签退")
	public void powerLogout(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("OperMngEntry");
		String foo = reqBean.getParameter("foo");
		TblStaff tblStaff = new TblStaff();
		OperMngEntryService operMngEntryService = OperMngEntryService.getInstance();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tblStaff);
		}
		tblStaff = operMngEntryService.checkId(tblStaff.getTlrno());
		tblStaff.setStatus(foo);
		operMngEntryService.updateOperMngEntry(tblStaff);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "[操作员管理 ]--签退用户:操作员编号[Tlrno]=" + foo;
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}

}
