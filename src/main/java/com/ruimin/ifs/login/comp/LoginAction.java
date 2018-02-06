package com.ruimin.ifs.login.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.global.GlobalUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.FormRequestBean;
import com.ruimin.ifs.framework.dataobject.FormReturnBean;
import com.ruimin.ifs.framework.session.SessionManager;
import com.ruimin.ifs.framework.session.SessionUtil;
import com.ruimin.ifs.login.process.LogService;
import com.ruimin.ifs.login.process.LoginService;
import com.ruimin.ifs.login.process.bean.LoginBean;
import com.ruimin.ifs.mng.process.service.OrgService;
import com.ruimin.ifs.po.TblBctl;
import com.ruimin.ifs.po.TblLoginLog;
import com.ruimin.ifs.po.TblStaff;
import com.ytec.yuap.uacs.authentication.AttributePrincipal;
import com.ytec.yuap.uacs.util.AssertionHolder;
import com.ytec.yuap.uacs.validation.Assertion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;

@SnowDoc(desc = "登录模块")
@ActionResource
public class LoginAction extends SnowAction {

    @Action(propagation = Propagation.REQUIRE_NEW)
    @SnowDoc(desc = "更新用户安全信息")
    public void updatePasswordInfo(TblStaff staff) throws SnowException {
        LoginService.getInstance().updatePasswordInfo(staff);
    }

    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "用户登录")
    public FormReturnBean snowLogin(FormRequestBean requestBean) throws SnowException {
        Assertion  assertion  =  AssertionHolder.getAssertion();
        AttributePrincipal  principal  =  assertion.getPrincipal();
        //获取principal之后，说明已完成身份认证，获取操作员编号
        String  name  =  principal.getName();
        String userName  =DESEncrypt.decrypt(name);
        FormReturnBean retBean = new FormReturnBean();
        String opsType = "login";
        boolean successFlag = false;
        String remark = null;
        LoginBean loginBaen = DataObjectUtils.map2bean(requestBean.getRequestParams(), LoginBean.class);

        TblStaff tb=LoginService.getInstance().getTblStaff(userName);
        String password ="";
        if(tb==null){    
            retBean.setSendUrl("/pages/login/error.jsp");
            return retBean;
        }
        TblBctl t= LoginService.getInstance().getUserRole(userName);
        if(t==null){
             retBean.setSendUrl("/pages/login/error.jsp");
             return retBean;
        }
        String logId = ContextUtil.getUUID();       
        String brno = loginBaen.getBrno();
        String loginAddr = requestBean.getRequest().getRemoteAddr();
        String sessionId = requestBean.getRequest().getSession().getId();
        try {
            UsernamePasswordToken userToken = new UsernamePasswordToken(userName, password.toCharArray());
            userToken.setHost(brno);
            try {
                SecurityUtils.getSubject().login(userToken);
            } catch (AuthenticationException e) {
                throw e;
            }
            // 语言
            String language = GlobalUtil.getDefLanguage();
            String[] lang = language.split("_");
            Locale locale = new Locale(lang[0], lang[1]);
            // session信息
            SessionUserInfo userinfo = SessionUserInfo.getSessionUserInfo();
            userinfo.setIp(requestBean.getRequest().getRemoteAddr());
            userinfo.setSessionId(requestBean.getRequest().getSession().getId());
            SessionUtil.setSessionUserInfo(requestBean.getRequest(), userinfo);
            userinfo.setLocale(locale);
          if (userinfo.getPassInfo().isPswdForcedToChange()) {// 需要修改密码
              retBean.setSendUrl("/pages/login/changePwd.jsp");
          } else {// 登录成功
                retBean.setSendUrl("/pages/login/index.jsp");
                // 更新用户登录信息
                LoginService.getInstance().updateUserLoginSucess(userinfo);
                successFlag = true;
                remark = "登录成功";

                List<String> brhList = new ArrayList<String>();
                queryAllBrhById(userinfo.getBrCode(), brhList);
                brhList.add(userinfo.getBrCode());
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("brhs", brhList);
                userinfo.setExtenMap(map);
          }
        } catch (Exception e) {// 登录失败
            retBean.setSendUrl("/");
            retBean.getReturnAttrMap().put("REQ_MSG", e.getMessage());
            successFlag = false;
            remark = "登录失败:" + e.getMessage();
        } finally {
            // 记录登录日志
            TblLoginLog tblLoginLog = new TblLoginLog();
            tblLoginLog.setLogId(logId);
            tblLoginLog.setTlrNo(userName);
            tblLoginLog.setBrNo(brno);
            tblLoginLog.setLoginAddr(loginAddr);
            tblLoginLog.setSessionId(sessionId);
            LogService.getInstance().saveOrUpdateLog(tblLoginLog, opsType, successFlag, remark);
        }
        return retBean;
    }

    @Action(propagation = Propagation.TRX_NONE)
    @SnowDoc(desc = "用户退出")
    public String snowLogOut(FormRequestBean requestBean) throws SnowException {
        SessionUserInfo userInfo = SessionUtil.getSessionUserInfo(requestBean.getRequest());
        try {
            this.userLogOutUpdate(userInfo);
        } finally {
            SessionManager.getInstance().destroySessionData(requestBean.getRequest());
            SessionUtil.removeSessionUserInfo(requestBean.getRequest());
            SessionUserInfo.removeSessionUserInfo();
            SessionManager.getInstance().destroySession(requestBean.getRequest());
        }
        return "/";
    }

    @Action(propagation = Propagation.REQUIRED)
    public void userLogOutUpdate(SessionUserInfo userInfo) throws SnowException {
        String opsType = "loginout";
        boolean successFlag = false;
        String remark = null;
        try {
            if (userInfo == null) {
                userInfo = SessionUserInfo.getSessionUserInfoNoException();
            }
            if (userInfo != null) {
                LoginService.getInstance().setLoginOutInfo(userInfo.getTlrno());
                successFlag = true;
                remark = "用户登出";
                // 记录登录日志
                TblLoginLog tblLoginLog = new TblLoginLog();
                LogService.getInstance().saveOrUpdateLog(tblLoginLog, opsType, successFlag, remark);
            }
        } catch (SnowException e) {
            throw e;
        }
    }

    private void queryAllBrhById(String brhId, List<String> list) throws SnowException {
        TblBctl objs = OrgService.getInstance().queryOrgById(brhId);
        if ("1".equals(objs.getBrclass())) {
            List<Object> orgs = OrgService.getInstance().listAll();
            for (Object org : orgs) {
                TblBctl bctl = (TblBctl) org;
                list.add(bctl.getBrcode());
            }
        }
        if ("2".equals(objs.getBrclass())) {
            List<Object> orgs = OrgService.getInstance().listOrg(brhId);
            for (Object org : orgs) {
                TblBctl bctl = (TblBctl) org;
                list.add(bctl.getBrcode());
            }
            list.add(brhId);
        }
        if ("3".equals(objs.getBrclass())) {
            list.add(brhId);
        }
    }

    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "重新启动应用时，设置用户为登录状态")
    public void setAllUserLogOut() throws SnowException {
        LoginService.getInstance().updateUserLogOut();
    }
}
