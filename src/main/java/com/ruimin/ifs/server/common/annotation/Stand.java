/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: Stand.java
 * Author:   GH
 * Date:     2015年9月10日 上午9:56:58
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.server.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author GH
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Stand {

	public enum AllowEmpty {
		NULL, NOTNULL
	};

	int maxLen() default 0;

	String[] scope() default {};

	AllowEmpty empty() default AllowEmpty.NOTNULL;
}
