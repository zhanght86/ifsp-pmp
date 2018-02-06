/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.comp 
 * FileName: PagyInfoService.java
 * Author:   zqy
 * Date:     2016年7月27日 上午10:39:09
 * Description: 通道信息管理     
 * History: 
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zqy           2016年7月27日上午10:39:09                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.service;

import java.util.List;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;

/**
 * 名称：通道信息管理 功能：通道信息管理 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月27日 <br>
 * 作者：zqy <br>
 * 说明：<br>
 */
@SnowDoc(desc = "通道信息管理操作Service")
@Service
public class PagyInfoService extends SnowService {
	public static PagyInfoService getInstance() throws SnowException {
		return ContextUtil.getSingleService(PagyInfoService.class);
	}

	/**
	 * 分页查询通道信息
	 */
	public PageResult queryList(String pagyNo, String pagyName, String queryType, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.pagyInfo.queryList",
				RqlParam.map().set("pagyNo", StringUtils.isEmpty(pagyNo) ? "" : "%" + pagyNo + "%")
						.set("pagyName", StringUtils.isEmpty(pagyName) ? "" : "%" + pagyName + "%")
						.set("queryType", StringUtils.isEmpty(queryType) ? "" : "%" + queryType + "%"),
				page);

	}

	/**
	 * 根据通道编号（单个或多个以“,”分割的通道编号）
	 */
	public List<Object> queryPagyNames(String pagyNos) {
		DBDao dao = DBDaos.newInstance();
		String[] pagyNoArray = pagyNos.split(",");
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.pagyInfo.queryPagyNames",
				RqlParam.map().set("pagyNoArray", pagyNoArray));
	}
}
