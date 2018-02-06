/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: TxnBaseUtil.java
 * Author:   GH
 * Date:     2015年8月28日 上午11:03:12
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.server.common.util;

import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.server.common.annotation.Empty;
import com.ruimin.ifs.server.common.annotation.VerifyInterface;
import com.ruimin.ifs.server.common.exception.ValidException;
import java.lang.reflect.Field;

/**
 * 〈交易工具类〉<br>
 * 
 * @author GH
 */
public class TxnBaseUtil {

	/**
	 * 校验字段必填:
	 * 
	 * 
	 * @param flag
	 *            : true- strings 为非空字段;
	 * @throws ValidException
	 */
	public static void vaiFieldRequ(Object obj) throws ValidException {
		if (obj == null) {
			throw new ValidException("60001", "请求报文内容为空.");
		}
		boolean flag = false;
		//
		// if(!obj.getClass().isAnnotationPresent(VerifyEmptyFiled.class)){
		// return;
		// }else{
		// VerifyEmptyFiled verifyTpe =
		// obj.getClass().getAnnotation(VerifyEmptyFiled.class);
		// if("NO".equals(verifyTpe.type().toString())){
		// flag = false; //没注解的不校验
		// }else if("YES".equals(verifyTpe.type().toString())){
		// flag = true; //校验有注解的, 且注解为 NOTNULL的
		// }
		// }

		Field[] fields = obj.getClass().getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);
			if ((!field.isAnnotationPresent(Empty.class) && !flag) || (field.isAnnotationPresent(Empty.class)
					&& "NULL".equals(field.getAnnotation(Empty.class).empty().toString()))) {
				continue;
			} else {

				// 字段名
				String fieldName = field.getName();
				// 字段值
				Object fieldValue;
				try {
					fieldValue = field.get(obj);
					SnowLog.getServerLog().debug("字段:" + fieldName + "|| 字段值: " + fieldValue);
				} catch (Exception e) {
					SnowLog.getServerLog().error("解析字段:[" + fieldName + "]失败:", e);
					throw new ValidException("60001", "报文中[" + fieldName + "]字段解析失败.");
				}
				if (fieldValue == null || "".equals(fieldValue)) {
					throw new ValidException("60001", "报文中[" + fieldName + "]字段不能为空.");
				}
			}

		}
	}

}
