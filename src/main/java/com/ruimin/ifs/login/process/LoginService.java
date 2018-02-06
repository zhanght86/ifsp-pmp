package com.ruimin.ifs.login.process;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.encrypt.EncryptUtil;
import com.ruimin.ifs.core.encrypt.EncryptUtil.EncryptType;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.core.util.constant.SnowConstants;
import com.ruimin.ifs.core.util.constant.SnowErrorCode;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.core.bean.Function;
import com.ruimin.ifs.framework.core.bean.PasswordInfo;
import com.ruimin.ifs.login.common.constants.ErrorCodeConstants;
import com.ruimin.ifs.login.common.constants.TlrConstants;
import com.ruimin.ifs.po.TblBctl;
import com.ruimin.ifs.po.TblFunction;
import com.ruimin.ifs.po.TblStaff;
import com.ruimin.ifs.po.TblSysInf;
import com.ruimin.ifs.po.TblSysParam;
import com.ruimin.ifs.util.SnowConstant;

@Service
public class LoginService extends SnowService {

	public static LoginService getInstance() throws SnowException {
		return ContextUtil.getSingleService(LoginService.class);
	}

	private TblSysInf getTblSysInf() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.select(TblSysInf.class, SnowConstant.TABLE_SYS_INFO_ID);
	}

	public SessionUserInfo loginUserSessionInfo(String username, char[] password, String brhno) throws SnowException {
		SessionUserInfo userinfo = SessionUserInfo.newInstance();
		TblSysInf sysInfo = getTblSysInf();
		if (!sysInfo.getStatus().equals(SnowConstant.SYS_INFO_STATE_ONLINE)) {
			SnowExceptionUtil.throwWarnException(SnowErrorCode.MESSAGE_WEB_0017);
		}
		TblStaff staff = checkUser(username, password, brhno);
		userinfo.setTlrno(staff.getTlrno());
		TblBctl bctl = null;
		if (StringUtils.isBlank(brhno)) {
			DBDao dao = DBDaos.newInstance();
			bctl = dao.select(TblBctl.class, staff.getBrcode());
			userinfo.setBrno(bctl.getBrno());
		} else {
			userinfo.setBrno(brhno);
			List<Object> orgList = DBDaos.newInstance().selectList("com.ruimin.ifs.login.rql.login.queryOrgByNo",
					RqlParam.map().set("brno", brhno));
			if (orgList.size() > 0) {
				bctl = (TblBctl) orgList.get(0);
			}
		}
		if (bctl != null) {
			userinfo.setBrName(bctl.getBrname());
			userinfo.setBrClass(bctl.getBrclass());
			userinfo.setUpBrcode(bctl.getBlnUpBrcode());
		}
		userinfo.setBrCode(staff.getBrcode());
		userinfo.setTlrName(staff.getTlrName());
		String cuDateParam = LoginService.getInstance().getSysParamDef("SYS", "CURRENT_DATE", "0");
		if (cuDateParam.equals("1")) {
			userinfo.setTxdate(new Date());
		} else {
			userinfo.setBhDate(DateUtil.getStringToDate8(sysInfo.getBhdate()));
			userinfo.setTxdate(DateUtil.getStringToDate8(sysInfo.getTbsdy()));
		}
		userinfo.setAllUrlFuncMap(getAllUrlFuncMap());
		userinfo.setUserFuncMap(getUserFuncMap(staff));
		return userinfo;
	}

	public String getSysParamDef(String paramId, String magicId, String defaultVal) {
		DBDao dao = DBDaos.newInstance();
		TblSysParam param = (TblSysParam) dao.selectOne("com.ruimin.ifs.login.rql.login.getSystemParamByKey",
				RqlParam.map().set("key1", paramId).set("key2", magicId));
		if (param == null) {
			param = new TblSysParam();
		}
		if (StringUtils.isBlank(param.getParamValueTx())) {
			return defaultVal;
		} else {
			return param.getParamValueTx();
		}
	}

	public boolean comparePassword(String sInputPassword, String sRightPassword, String sEncMethod)
			throws SnowException {
		String sTransPassword = "";
		if (sEncMethod == null || sEncMethod.equalsIgnoreCase("") || sEncMethod.equalsIgnoreCase("none")) {
			sTransPassword = sInputPassword;
		} else if (sEncMethod.equalsIgnoreCase(EncryptType.MD5.name())) {
			sTransPassword = EncryptUtil.getEncrypt(EncryptType.MD5).encrypt(sInputPassword);
		} else {
			sTransPassword = EncryptUtil.getEncrypt(EncryptType.valueOf(sEncMethod.toUpperCase()))
					.encrypt(sInputPassword);
		}
		return sTransPassword.equals(sRightPassword);
	}

	public Map<String, Function> getUserFuncMap(TblStaff staff) throws SnowException {
		Map<String, Function> map = new HashMap<String, Function>();
		DBDao dao = DBDaos.newInstance();
		// 根据角色获取权限
		List<Object> funcs = dao.selectList("com.ruimin.ifs.login.rql.login.queryUserFuncsByRoles", staff.getTlrno());
		if (funcs == null || funcs.size() == 0) {
			SnowExceptionUtil.throwWarnException(SnowErrorCode.MESSAGE_WEB_0016);
		}
		for (Object o : funcs) {
			TblFunction func = (TblFunction) o;
			map.put(func.getFuncid(), func);
		}
		// TODO 根据机构获取权限
		return map;
	}
	public  TblBctl getUserRole(String username) throws SnowException {
	    DBDao dao = DBDaos.newInstance();
	    return   (TblBctl) dao.selectOne("com.ruimin.ifs.login.rql.login.getUserRole", username);
        
	}
	private Map<String, String> getAllUrlFuncMap() throws SnowException {
		Map<String, String> map = new HashMap<String, String>();
		DBDao dao = DBDaos.newInstance();
		List<TblFunction> funcs = dao.selectAll(TblFunction.class);
		for (TblFunction func : funcs) {
			if (StringUtils.isNotBlank(func.getPagepath()))
				map.put(func.getPagepath(), func.getFuncid());
		}

		return map;
	}

	public TblStaff getTblStaff(String trlNo) {
		DBDao dao = DBDaos.newInstance();
		TblStaff tlrInfo = (TblStaff) dao.selectOne("com.ruimin.ifs.login.rql.login.queryUserByUsernameAndPassword",
				RqlParam.map().set("username", trlNo));		
		return tlrInfo;
	}

	/**
	 * 校验用户信息
	 * 
	 * @param username
	 * @param password
	 * @param brhno
	 * @return 校验结果信息，如果用户不存在或用户存在但密码不对，抛出异常；校验通过，返回true
	 * @throws SnowException
	 */
	public TblStaff checkUser(String username,char[] password, String brhno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		TblStaff tlrInfo = (TblStaff) dao.selectOne("com.ruimin.ifs.login.rql.login.queryUserByUsernameAndPassword",
				RqlParam.map().set("username", username));

		if (tlrInfo == null) {
			SnowExceptionUtil.throwErrorException("WEB_E0011");// WEB_E0015
		} else {
			if (StringUtils.isNotBlank(brhno)) {
				List<Object> orgList = dao.selectList("com.ruimin.ifs.login.rql.login.queryOrgByNo",
						RqlParam.map().set("brno", brhno));
				if (orgList.size() <= 0) {
					SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_WEB_0024);
				}
			}

			// 判断所选择登录机构是否是该用户授权的机构 modify by zhangshishu 2012-09-12
			// if (StringUtils.isNotBlank(brhno)) {
			// TblBctl btcl = (TblBctl)
			// dao.selectOne("queryUserByUsernameAndPassword", param);
			// HQLDAO hqldao = BaseDAOUtils.getHQLDAO();
			// List<Bctl> bctls = hqldao.queryByQL2List("from Bctl where brno='"
			// + userBrno + "'");
			// if (bctls.size() <= 0) {
			// ExceptionUtil.throwCommonException("根据选择登录机构号brno[" + userBrno +
			// "]未查到机构信息");
			// }
			// Bctl bctl = bctls.get(0);
			// tlrInfo.setBrno(userBrno);
			// tlrInfo.setBrcode(bctl.getBrcode());
			// }

			// int totPswdErrCnt = tlrInfo.getTotpswderrcnt().intValue();
			// if (SystemConstant.ERR_PWD_TIMES_CONTINUE <= totPswdErrCnt) {
			// ExceptionUtil.throwCommonException(ErrorCode.ERROR_CODE_TLRNO_PSWD_ERR_THREE_TIMES);
			// }
			String status = tlrInfo.getStatus();
			// 如果用户已经离职，不允许再登陆
			if (SnowConstants.TLR_NO_STATE_QUIT.equals(status)) {
				SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_WEB_0018);
			}
			// 操作员被删除后，不能登陆
			if (SnowConstants.FLAG_DEL.equals(tlrInfo.getFlag())) {
				SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_WEB_0019);
			}
			// 操作员被停用后，不允许登录
			if (TlrConstants.TLR_FLAG_DISABLED.equals(tlrInfo.getFlag())) {
				SnowExceptionUtil.throwErrorException(ErrorCodeConstants.TLR_DISABLED);
			}
			String loginRule = LoginService.getInstance().getSysParamDef("PSWD", "LOGIN_RULE", "0");
			if (loginRule.equals("1") && SnowConstants.TLR_NO_STATE_LOGIN.equals(status)) {// 不允许重复登录
				SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_WEB_0020);
			}

			//checkPassword(tlrInfo, password);

			return tlrInfo;
		}
		return null;
	}

	private void checkPassword(TblStaff user, String password) throws SnowException {
		String enc = user.getPasswdenc();

		int lockingTime = Integer.valueOf(LoginService.getInstance().getSysParamDef("PSWD", "LOCKING_TIME", "-1"));
		if (SnowConstants.LOCKED.equals(user.getIsLock())) {
			if (lockingTime < 0) {
				SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_WEB_0021);
			} else {
				long x = System.currentTimeMillis()
						- DateUtil.stringToDateTime(user.getLastfailedtm(), "yyyyMMddHHmmss").getTime();
				long between = x / 1000 / 60;
				if (between < lockingTime || lockingTime < 0) {
					SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_WEB_0022,
							new String[] { lockingTime + "" });
				} else {
					user.setTotpswderrcnt(0);
					user.setIsLock(SnowConstants.UNLOCKED);
				}
			}
		}

		if (!comparePassword(password, user.getPassword(), enc)) {
			if (user.getTotpswderrcnt() != null) {
				int totpswderrcnt = user.getTotpswderrcnt().intValue();
				user.setTotpswderrcnt(totpswderrcnt + 1);
			} else {
				user.setTotpswderrcnt(1);
			}
			user.setLastfailedtm(DateUtil.timeToNumber(new Timestamp(System.currentTimeMillis())));

			int maxErrCnt = Integer.valueOf(LoginService.getInstance().getSysParamDef("PSWD", "MAX_ERR_CNT", "0"));
			if (user.getTotpswderrcnt().intValue() > maxErrCnt && maxErrCnt >= 0) {
				user.setTotpswderrcnt(0);
				user.setIsLock(SnowConstants.LOCKED);
				user.setLockReason("用户密码连续输入错误次数超过允许的最大次数" + maxErrCnt);
			}
			this.updatePasswordInfo(user);
			if (SnowConstants.LOCKED.equals(user.getIsLock())) {
				if (lockingTime < 0) {
					SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_WEB_0021);
				} else {
					SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_WEB_0022,
							new String[] { lockingTime + "" });
				}
			} else {
				SnowExceptionUtil.throwErrorException("WEB_E0011");
				// SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_WEB_0023,
				// new String[] { (maxErrCnt - user.getTotpswderrcnt() + 1) + ""
				// });
			}
		} else {
			user.setTotpswderrcnt(0);
			user.setLastfailedtm(null);
			user.setLockReason(null);
			this.updatePasswordInfo(user);
		}
		PasswordInfo pass = SessionUserInfo.getSessionUserInfo().getPassInfo();
		// 密码有效时间(天)
		int effectiveDay = Integer.valueOf(LoginService.getInstance().getSysParamDef("PSWD", "EFFECTIVE_DAY", "0"));
		pass.setEffectiveDay(effectiveDay);
		if (StringUtils.isBlank(user.getLastpwdchgtm())) {
			pass.setPswdForcedToChange(true);// 未修改过密码
		} else {
			long between = 0L;
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				pass.setLastpwdchgtm(df.parse(user.getLastpwdchgtm()));
				between = DateUtil.getDaysBetween(new Date(), df.parse(user.getLastpwdchgtm()));
			} catch (ParseException e) {
				SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_SYS_0033, e);
			}
			if (between > effectiveDay && effectiveDay >= 0) {
				pass.setPswdForcedToChange(true);// 超过N久没修改密码,要强制修改
			}
		}

		// 密码强度
		pass.setPswdStrength(LoginService.getInstance().getSysParamDef("PSWD", "PSWD_STRENGTH", "2"));
	}

	public void updatePasswordInfo(TblStaff staff) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(staff);
	}

	/**
	 * 设置最近退出登陆时间
	 * 
	 * @param userLoginId
	 *            用户名
	 * @return
	 */
	public void setLoginOutInfo(String userLoginId) throws SnowException {
		TblStaff tblStaff = null;
		DBDao dao = DBDaos.newInstance();
		tblStaff = getTblStaff(userLoginId);
		try {
			if (tblStaff != null) {
				if (tblStaff.getStatus().equals(SnowConstants.TLR_NO_STATE_LOGIN)) {
					tblStaff.setStatus(SnowConstants.TLR_NO_STATE_LOGOUT);
					// 最近登出时间
					tblStaff.setLastlogouttm(DateUtil.getLocalDateTime14());
					dao.update(tblStaff);
				}
			}
		} catch (Exception e) {
			SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_SYS_0005, e.getMessage(), e);
		}
	}

	public void updateUserLoginSucess(SessionUserInfo userInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		TblStaff tblStaff = getTblStaff(userInfo.getTlrno());
		tblStaff.setStatus(SnowConstants.TLR_NO_STATE_LOGIN);
		tblStaff.setSessionId(userInfo.getSessionId());
		tblStaff.setLastaccesstm(DateUtil.getLocalDateTime14());
		tblStaff.setLoginIp(userInfo.getIp());
		dao.update(tblStaff);
	}

	/**
	 * 校验新旧密码字段数据有效性
	 *
	 * @param oldpwd
	 *            旧密码
	 * @param newpwd
	 *            新密码
	 * @param againnewpwd
	 *            新密码确认
	 * @return
	 *
	 */
	public void checkPwdFields(String oldPwd, String newPwd, String againNewPwd) throws SnowException {
		// 所有密码字段不能为空或空格
		if ((null == oldPwd) || (null == newPwd) || (null == againNewPwd))
			SnowExceptionUtil.throwWarnException(SnowErrorCode.MESSAGE_WEB_0027);

		if ((0 == oldPwd.trim().length()) || (0 == newPwd.trim().length()) || (0 == againNewPwd.trim().length()))
			SnowExceptionUtil.throwWarnException(SnowErrorCode.MESSAGE_WEB_0027);

		// 新旧密码不能相同
		if (true == newPwd.trim().equals(oldPwd.trim()))
			SnowExceptionUtil.throwWarnException(SnowErrorCode.MESSAGE_WEB_0028);

		// 新密码和确认密码必须相同
		if (false == newPwd.trim().equals(againNewPwd.trim()))
			SnowExceptionUtil.throwWarnException(SnowErrorCode.MESSAGE_WEB_0029);

		return;
	}

	/**
	 * 校验用户密码信息
	 *
	 * @param userLoginId
	 *            用户名
	 * @param password
	 *            输入的密码
	 * @return 校验结果信息，如果用户不存在或用户存在但密码不对，抛出异常；校验通过，返回true
	 *
	 */
	public TblStaff checkUserPwd(String userLoginId, String password, String newPasswd) throws SnowException {
		// 比较密码不能为六个相同字母
		boolean bSameChars = true;
		char c = newPasswd.charAt(0);
		for (int i = 0; i < newPasswd.length(); i++) {
			if (c != newPasswd.charAt(i)) {
				bSameChars = false;
				break;
			}
		}
		if (true == bSameChars)
			SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_WEB_0030);

		// 新旧密码不能相同
		if (true == newPasswd.trim().equals(password.trim()))
			SnowExceptionUtil.throwWarnException(SnowErrorCode.MESSAGE_WEB_0031);
		DBDao dao = DBDaos.newInstance();
		TblStaff tblStaff = dao.select(TblStaff.class, userLoginId);
		// 判断返回条件
		if (tblStaff == null)
			SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_WEB_0015);

		String sEncMethod = tblStaff.getPasswdenc();
		String oldPassWord = password;
		String newPassWord = newPasswd;
		if (sEncMethod == null || sEncMethod.equalsIgnoreCase("") || sEncMethod.equalsIgnoreCase("none")) {
			oldPassWord = password;
			newPassWord = newPasswd;
		} else if (sEncMethod.equalsIgnoreCase(EncryptType.MD5.name())) {
			oldPassWord = EncryptUtil.getEncrypt(EncryptType.MD5).encrypt(oldPassWord);
			newPassWord = EncryptUtil.getEncrypt(EncryptType.MD5).encrypt(newPasswd);
		} else {
			oldPassWord = EncryptUtil.getEncrypt(EncryptType.valueOf(sEncMethod.toUpperCase())).encrypt(oldPassWord);
			newPassWord = EncryptUtil.getEncrypt(EncryptType.valueOf(sEncMethod.toUpperCase())).encrypt(newPasswd);
		}

		if (!tblStaff.getPassword().equals(oldPassWord)) {
			SnowExceptionUtil.throwWarnException(SnowErrorCode.MESSAGE_WEB_0032);
		}
		tblStaff.setPassword(newPassWord);
		return tblStaff;
	}

	public void updateUserLogOut() {
		DBDaos.newInstance().executeUpdate("com.ruimin.ifs.login.rql.login.updateStaffStatusLogOut",
				RqlParam.map().set("lastLogOutTm", DateUtil.getDateAndTm14()));
	}
}
