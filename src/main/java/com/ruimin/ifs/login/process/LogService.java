package com.ruimin.ifs.login.process;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.po.TblLoginLog;

@Service
public class LogService extends SnowService {

	public static LogService getInstance() throws SnowException {
		return ContextUtil.getSingleService(LogService.class);
	}

	/**
	 * 记录日志
	 * 
	 * @param opsType
	 *            记录类型
	 * @param successFlag
	 *            是否成功
	 * @param remark
	 *            备注
	 * @throws SnowException
	 */
	public void saveOrUpdateLog(TblLoginLog tblLoginLog, String opsType, boolean successFlag, String remark)
			throws SnowException {
		if (opsType.equals("login")) { // 登录
			DBDao dao = DBDaos.newInstance();
			SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
			if (successFlag) {
				tblLoginLog.setLoginSucTm(DateUtil.get14Date());
			} else {
				tblLoginLog.setLoginFailTm(DateUtil.get14Date());
			}
			tblLoginLog.setCrtTm(DateUtil.get14Date());
			tblLoginLog.setLoginRemark(remark);
			if (StringUtils.isBlank(tblLoginLog.getBrNo())) {
				tblLoginLog.setBrNo(sessionUserInfo.getBrno());
			}
			dao.insert(tblLoginLog);
			sessionUserInfo.setLoginLogId(tblLoginLog.getLogId());
		} else if (opsType.equals("loginout")) { // 登出
			DBDao dao = DBDaos.newInstance();
			SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfoNoException();
			if (null != sessionUserInfo) {
				tblLoginLog = dao.select(TblLoginLog.class, sessionUserInfo.getLoginLogId());
				if (null != tblLoginLog) {
					tblLoginLog.setLoginOutTm(DateUtil.get14Date());
					tblLoginLog.setLoginRemark(remark);
					dao.update(tblLoginLog);
				}
			}
		}
	}

}
