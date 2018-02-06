/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: EmptyAn.java
 * Author:   GH
 * Date:     2015年9月6日 上午11:08:02
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
 * 〈没注解的字段 的校验方式:〉<br>
 * NO- 不校验<br>
 * YES- 不空<br>
 * 
 * @author GH
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyInterface {

	public enum Type {
		NO, YES
	};

	Type type() default Type.NO;

}
