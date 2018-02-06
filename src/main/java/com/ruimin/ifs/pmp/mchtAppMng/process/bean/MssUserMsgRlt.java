package com.ruimin.ifs.pmp.mchtAppMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;
@Table("MSS_USER_MSG_RLT")
public class MssUserMsgRlt {
	@Id
	private String msgId;
	@Id
	private String userId;
	
	private String userLev;
	
	private String msgStat;
	
	public String getMsgId() {
		return msgId;
	}
	
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserLev() {
		return userLev;
	}

	public void setUserLev(String userLev) {
		this.userLev = userLev;
	}

	public String getMsgStat() {
		return msgStat;
	}

	public void setMsgStat(String msgStat) {
		this.msgStat = msgStat;
	}
	
}
