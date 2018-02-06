package com.ruimin.ifs.pmp.mchtAppMng.process.bean;

public class MchtUserVO extends UserBaseInfo{
	private String mchtId;
	private String mchtSimpleName;
	private String mchtArtifId;
	private String nickName;
	private String empNo;
	private String userIdAss;
	
	public String getMchtId() {
		return mchtId;
	}
	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}
	public String getMchtSimpleName() {
		return mchtSimpleName;
	}
	public void setMchtSimpleName(String mchtSimpleName) {
		this.mchtSimpleName = mchtSimpleName;
	}
	public String getMchtArtifId() {
		return mchtArtifId;
	}
	public void setMchtArtifId(String mchtArtifId) {
		this.mchtArtifId = mchtArtifId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
    public String getEmpNo() {
        return empNo;
    }
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }
	public String getUserIdAss() {
		return userIdAss;
	}
	public void setUserIdAss(String userIdAss) {
		this.userIdAss = userIdAss;
	}
	
	
	
	
}
