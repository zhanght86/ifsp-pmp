package com.ruimin.ifs.pmp.pubTool.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.util.ContextUtil;

/*
 * 模拟接口
 */
public class DealMchtChnlReqAction {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static DealMchtChnlReqAction getInstance() throws SnowException {
		return ContextUtil.getSingleService(DealMchtChnlReqAction.class);
	}

	public String retTest(String str) throws SnowException {
		Map<String, String> serRetMap = new HashMap<String, String>();
		if (StringUtils.isNotBlank(str)) {
			serRetMap.put("respCode", "xxx0000");
		} else {
			serRetMap.put("respCode", "xxx9999");
			serRetMap.put("respMsg", "交易失败！");
		}
		Gson gson = new Gson();
		return gson.toJson(serRetMap);
	}
}
