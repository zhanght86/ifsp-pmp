/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: BeanUtil.java
 * Author:   GH
 * Date:     2015年8月21日 下午2:31:30
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.pmp.pubTool.util;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.framework.core.SessionUserInfo;

/**
 * 〈bean〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author GH
 */
public class BeanUtil {

	/**
	 * 补充操作员和时间信息
	 * 
	 * @throws SnowException
	 */
	public static Object fullOprAndTime(Object o) throws SnowException {
		if (o == null) {
			return o;
		}

		try {
			o.getClass().getMethod("setCrtTlr", String.class).invoke(o,
					SessionUserInfo.getSessionUserInfo().getTlrno());
			o.getClass().getMethod("setCrtDateTime", String.class).invoke(o, BaseUtil.getCurrentDateTime());
			o.getClass().getMethod("setLastUpdTlr", String.class).invoke(o,
					SessionUserInfo.getSessionUserInfo().getTlrno());
			o.getClass().getMethod("setLastUpdDateTime", String.class).invoke(o, BaseUtil.getCurrentDateTime());
		} catch (Exception e) {
			SnowExceptionUtil.throwErrorException("WEB_E0605");
		}

		return o;
	}

	/**
	 * 补充操作员和时间信息(更新)
	 * 
	 * @throws SnowException
	 */
	public static Object fullOprAndTimeForUpd(Object o) throws SnowException {
		if (o == null) {
			return o;
		}

		try {
			o.getClass().getMethod("setLastUpdTlr", String.class).invoke(o,
					SessionUserInfo.getSessionUserInfo().getTlrno());
			o.getClass().getMethod("setLastUpdDateTime", String.class).invoke(o, BaseUtil.getCurrentDateTime());
		} catch (Exception e) {
			SnowExceptionUtil.throwErrorException("WEB_E0605");
		}

		return o;
	}
}
