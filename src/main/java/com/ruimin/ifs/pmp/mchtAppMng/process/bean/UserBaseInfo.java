package com.ruimin.ifs.pmp.mchtAppMng.process.bean;

public class UserBaseInfo {
    private static final long serialVersionUID = -4359740496801089495L;
    
    private String userId; // 用户号

    private String pwdType; // 密码类型：0-普通密码

    private String userName; // 用户名

    private String userStat; // 用户状态：00-正常 01-停用 02-冻结

    private String userLevel; // 用户等级：0-普通 1-VIP

    private String userType; // 用户类型：0-店长 1-店员

    private String phoneNo; // 手机号

    private String pwdSetFalg; // 密码来源：0-自主设置 1-系统生成

    private String userPwd; // 密码

    private String pwdErrCnt; // 密码错误次数

    private String pwdStat; // 密码状态

    private String crtDateTime; // 创建时间

    private String updDateTime; // 更新时间
    
    private String channelId;
    private String deviceType;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPwdType() {
        return pwdType;
    }

    public void setPwdType(String pwdType) {
        this.pwdType = pwdType == null ? null : pwdType.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserStat() {
        return userStat;
    }

    public void setUserStat(String userStat) {
        this.userStat = userStat == null ? null : userStat.trim();
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel == null ? null : userLevel.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public String getPwdSetFalg() {
        return pwdSetFalg;
    }

    public void setPwdSetFalg(String pwdSetFalg) {
        this.pwdSetFalg = pwdSetFalg == null ? null : pwdSetFalg.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getPwdErrCnt() {
        return pwdErrCnt;
    }

    public void setPwdErrCnt(String pwdErrCnt) {
        this.pwdErrCnt = pwdErrCnt;
    }

    public String getPwdStat() {
        return pwdStat;
    }

    public void setPwdStat(String pwdStat) {
        this.pwdStat = pwdStat == null ? null : pwdStat.trim();
    }

    public String getCrtDateTime() {
        return crtDateTime;
    }

    public void setCrtDateTime(String crtDateTime) {
        this.crtDateTime = crtDateTime == null ? null : crtDateTime.trim();
    }

    public String getUpdDateTime() {
        return updDateTime;
    }

    public void setUpdDateTime(String updDateTime) {
        this.updDateTime = updDateTime == null ? null : updDateTime.trim();
    }

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
    
}