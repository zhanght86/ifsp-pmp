/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: UploadExceptionUtil.java
 * Author:   GH
 * Date:     2015年9月1日 下午5:20:05
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.pmp.pubTool.util;

import com.ruimin.ifs.pmp.pubTool.exception.UploadHandleException;
import com.ruimin.ifs.pmp.pubTool.exception.VerifyException;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author GH
 */
public class FileOprExceptionUtil {

	public static void throwVerifyException(String code, String msg) throws VerifyException {
		throw new VerifyException(code, msg);
	}

	public static void throwUploadHandleException(String msg, Throwable t) throws UploadHandleException {
		throw new UploadHandleException(msg, t);
	}
}
