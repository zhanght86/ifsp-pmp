/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.pubTool.comp 
 * FileName: Base64Coder.java
 * Author:   Administrator
 * Date:     2016年8月3日 下午4:53:35
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   Administrator           2016年8月3日下午4:53:35                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.pubTool.util;

import com.ruimin.ifs.core.encrypt.Base64;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.framework.dataset.field.FieldBean;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletRequest;

/**
 * 名称：Base64加密解密〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月3日 <br>
 * 作者：Administrator <br>
 * 说明：<br>
 */
public class Base64Coder {
	public final static String ENCODING = "UTF-8";

	/**
	 * 加密
	 * 
	 * @param data
	 * @return
	 * @throws SnowException
	 * @throws UnsupportedEncodingException
	 */
	public static String encoded(String data) throws SnowException, UnsupportedEncodingException {

		byte[] binaryData = data.getBytes(ENCODING);

		byte[] bt = Base64.encodeBase64(binaryData);
		return new String(bt, ENCODING);
	}

	/**
	 * 解密
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 * @throws UnsupportedEncodingException
	 */
	public static String decode(FieldBean bean, String key, ServletRequest request)
			throws SnowException, UnsupportedEncodingException {

		byte[] binaryData = key.getBytes(ENCODING);

		byte[] bt = Base64.decodeBase64(binaryData);
		return new String(bt, ENCODING);
	}
}
