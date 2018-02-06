/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: SysParamUtil.java
 * Author:   MJ
 * Date:     2015年11月26日 下午3:06:26
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.pmp.pubTool.util;

import java.util.ResourceBundle;

/**
 * 〈获取系统参数〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author MJ
 */
public class SysParamUtil {

	private static String SYSPARAM_FILE = "SysParam";

	private static ResourceBundle BUNDLE = ResourceBundle.getBundle(SYSPARAM_FILE);

	/**
	 * 获得系统参数
	 * 
	 * @param key
	 * @return
	 */
	public static String getParam(String key) {
		return BUNDLE.getString(key);
	}
}
