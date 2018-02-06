/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: ValidException.java
 * Author:   GH
 * Date:     2015年8月28日 下午2:03:23
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.server.common.exception;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author GH
 */
public class ValidException extends Exception {

	/**
	 */
	private static final long serialVersionUID = 6510805704710057939L;

	private String code;
	private String msg;

	public ValidException() {
	}

	public ValidException(String code) {
		this.code = code;
	}

	public ValidException(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
