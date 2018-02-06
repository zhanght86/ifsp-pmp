package com.ruimin.ifs.login.comp;

import java.util.Date;
import java.util.Map;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.core.util.constant.SnowErrorCode;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.login.process.LoginService;
import com.ruimin.ifs.login.process.bean.ChangePwdForm;
import com.ruimin.ifs.po.TblStaff;

@SnowDoc(desc = "ChangePwdAction")
@ActionResource
public class ChangePwdAction extends SnowAction {

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "操作员密码修改")
	public void saveOrUpdate(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("changePwd");
		ChangePwdForm cpf = new ChangePwdForm();
		LoginService ls = ContextUtil.getSingleService(LoginService.class);
		SessionUserInfo userInfo = SessionUserInfo.getSessionUserInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), cpf);
		}
		if (!(cpf.getNewPassWord().equals(cpf.getAgainNewPassWord()))) {
			SnowExceptionUtil.throwWarnException(SnowErrorCode.MESSAGE_WEB_0025);
		}
		// 校验用户信息,密码的合法性
		ls.checkPwdFields(cpf.getOldPassWord(), cpf.getNewPassWord(), cpf.getAgainNewPassWord());
		TblStaff user = ls.checkUserPwd(userInfo.getTlrno(), cpf.getOldPassWord(), cpf.getNewPassWord());
		if (null == user) {
			SnowExceptionUtil.throwWarnException(SnowErrorCode.MESSAGE_WEB_0026);
		}
		/** 赋值新数据 **/
		user.setSessionId(userInfo.getSessionId());
		user.setLastpwdchgtm(DateUtil.get14Date());
		// 更新
		ls.updatePasswordInfo(user);
		userInfo.getPassInfo().setPswdForcedToChange(false);
		userInfo.getPassInfo().setLastpwdchgtm(new Date());
		userInfo.addBizLog("update.log", new String[] { user.getTlrno(), user.getBrcode(), "密码修改" });
	}
}
