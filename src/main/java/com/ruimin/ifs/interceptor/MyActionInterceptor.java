package com.ruimin.ifs.interceptor;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.intercept.SnowActionInterceptor;

public class MyActionInterceptor extends SnowActionInterceptor {

	@Override
	public void preHandle(String method, Object[] args, Object retObj) {
		System.out.println(method + "==========执行前调用==========");

	}

	@Override
	public void postHandle(String method, Object[] args, Object retObj) {
		System.out.println(method + "==========执行后调用==========返回值：" + retObj.toString());

	}

	@Override
	public void postException(String method, Object[] args, Object retObj, Exception e) throws SnowException {
		System.out.println(method + "==========异常后调用==========" + e.getMessage());
	}

}
