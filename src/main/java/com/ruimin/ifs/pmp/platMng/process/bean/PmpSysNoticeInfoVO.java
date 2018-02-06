package com.ruimin.ifs.pmp.platMng.process.bean;

import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("PMP_SYS_NOTICE_INFO")
public class PmpSysNoticeInfoVO {
	private String crtDateTime;
	private String noticeTitle;
	@Id
	private String noticeNo;
	private String noticeContent;
	private String noticePicId;
	private String noticePicPath;
	private String noticeState;
	private String crtTlr;
	private String lastUpdTlr;
	private String lastUpdDateTime;

	public String getCrtDateTime() {
		return crtDateTime;
	}

	public void setCrtDateTime(String crtDateTime) {
		this.crtDateTime = crtDateTime;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticePicId() {
		return noticePicId;
	}

	public void setNoticePicId(String noticePicId) {
		this.noticePicId = noticePicId;
	}

	public String getNoticePicPath() {
		return noticePicPath;
	}

	public void setNoticePicPath(String noticePicPath) {
		this.noticePicPath = noticePicPath;
	}

	public String getNoticeState() {
		return noticeState;
	}

	public void setNoticeState(String noticeState) {
		this.noticeState = noticeState;
	}

	public String getCrtTlr() {
		return crtTlr;
	}

	public void setCrtTlr(String crtTlr) {
		this.crtTlr = crtTlr;
	}

	public String getLastUpdTlr() {
		return lastUpdTlr;
	}

	public void setLastUpdTlr(String lastUpdTlr) {
		this.lastUpdTlr = lastUpdTlr;
	}

	public String getLastUpdDateTime() {
		return lastUpdDateTime;
	}

	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}

}
