/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: UploadServiceException.java
 * Author:   GH
 * Date:     2015年9月1日 下午5:06:48
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.pmp.pubTool.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 〈处理异常类〉<br>
 * 
 * @author GH
 */
public class UploadHandleException extends Exception {

	/**
	 */
	private static final long serialVersionUID = -5123382962531371258L;

	private final Throwable cause;

	public UploadHandleException() {
		this(null, null);
	}

	public UploadHandleException(final String msg) {
		this(msg, null);
	}

	public UploadHandleException(String msg, Throwable cause) {
		super(msg);
		this.cause = cause;
	}

	@Override
	public void printStackTrace(PrintStream stream) {
		super.printStackTrace(stream);
		if (cause != null) {
			stream.println("Caused by:");
			cause.printStackTrace(stream);
		}
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		super.printStackTrace(s);
		if (cause != null) {
			s.println("Caused by:");
			cause.printStackTrace(s);
		}
	}

	public Throwable getCause() {
		return cause;
	}
}
