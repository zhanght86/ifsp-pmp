/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: VerifyException.java
 * Author:   GH
 * Date:     2015年9月1日 下午5:16:38
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.pmp.pubTool.exception;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author GH
 */
public class VerifyException extends Exception {

	/**
	 */
	private static final long serialVersionUID = 4693357832587424984L;

	private String code;
	private String msg;

	public VerifyException(String code, String msg) {
		this.msg = msg;
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

}
