package com.ruimin.ifs.login.process.bean;

import com.ruimin.ifs.core.encrypt.EncryptUtil;
import com.ruimin.ifs.core.encrypt.EncryptUtil.EncryptType;
import com.ruimin.ifs.core.exception.SnowException;

public class LoginBean {
	private String userName;
	private String passWord;
	private String brno;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPassword() throws SnowException {
		if (passWord != null) {
			// return
			// EncryptUtil.getEncrypt(EncryptType.SIMPLE).decrypt(passWord);
		}
		return passWord;
	}

	public String getBrno() {
		return brno;
	}

	public void setBrno(String brno) {
		this.brno = brno;
	}

}
