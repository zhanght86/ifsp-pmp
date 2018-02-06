package com.ruimin.ifs.login.process.bean;

import com.ruimin.ifs.rql.annotation.Id;

public final class ChangePwdForm {
	@Id
	private String tlrno = null;
	private String oldPassWord = null;
	private String newPassWord = null;
	private String againNewPassWord = null;

	public String getTlrno() {
		return tlrno;
	}

	public void setTlrno(String tlrno) {
		this.tlrno = tlrno;
	}

	public String getOldPassWord() {
		return oldPassWord;
	}

	public void setOldPassWord(String oldPassWord) {
		this.oldPassWord = oldPassWord;
	}

	public String getNewPassWord() {
		return newPassWord;
	}

	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}

	public String getAgainNewPassWord() {
		return againNewPassWord;
	}

	public void setAgainNewPassWord(String againNewPassWord) {
		this.againNewPassWord = againNewPassWord;
	}

}
