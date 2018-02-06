package com.ruimin.ifs.pmp.mchtAppMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;
@Table("Mss_MSG_BASE_INFO")
public class MsgBaseInfo {
	@Id
	private String msgId;
	private String msgType;
	private String msgChn;
	private String sendWay;
	private String msgDate;
	private String msgTime;
	private String msgTitle;
	private String msgIntro;
	private String msgDesc;
	private String msgExpiry;
	private String msgSign;
	private String picUrl;
	
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
    public String getMsgType() {
        return msgType;
    }
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
    public String getMsgChn() {
        return msgChn;
    }
    public void setMsgChn(String msgChn) {
        this.msgChn = msgChn;
    }
    public String getSendWay() {
        return sendWay;
    }
    public void setSendWay(String sendWay) {
        this.sendWay = sendWay;
    }
    public String getMsgDate() {
        return msgDate;
    }
    public void setMsgDate(String msgDate) {
        this.msgDate = msgDate;
    }
    public String getMsgTime() {
        return msgTime;
    }
    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }
    public String getMsgTitle() {
        return msgTitle;
    }
    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }
    public String getMsgIntro() {
        return msgIntro;
    }
    public void setMsgIntro(String msgIntro) {
        this.msgIntro = msgIntro;
    }
    public String getMsgDesc() {
        return msgDesc;
    }
    public void setMsgDesc(String msgDesc) {
        this.msgDesc = msgDesc;
    }
    public String getMsgExpiry() {
        return msgExpiry;
    }
    public void setMsgExpiry(String msgExpiry) {
        this.msgExpiry = msgExpiry;
    }
    public String getMsgSign() {
        return msgSign;
    }
    public void setMsgSign(String msgSign) {
        this.msgSign = msgSign;
    }
    public String getPicUrl() {
        return picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
	
	
	
	
}
