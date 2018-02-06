/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.comp 
 * FileName: PagyCategoryNameService.java
 * Author:   zqy
 * Date:     2016年8月02日 上午10:39:09
 * Description: 类目名词    
 * History: 
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zqy           2016年8月02日上午10:39:09                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.service;

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
 * 名称：类目名词 功能：类目名词 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月02日 <br>
 * 作者：zqy <br>
 * 说明：<br>
 */
@SnowDoc(desc = "类目名词 操作Service")
@Service
public class PagyCategoryNameService extends SnowService {
	public static PagyCategoryNameService getInstance() throws SnowException {
		return ContextUtil.getSingleService(PagyCategoryNameService.class);
	}

	/**
	 * 分页查询通道商户登记信息
	 */
	public PageResult queryList(String pCategoryCode, String userCode, String categoryLevel, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.pagyCategoryName.queryList",
				RqlParam.map().set("pCategoryCode", StringUtils.isEmpty(pCategoryCode) ? "" : "%" + pCategoryCode + "%")
						.set("userCode", StringUtils.isEmpty(userCode) ? "" : "%" + userCode + "%").set("categoryLevel",
								StringUtils.isEmpty(categoryLevel) ? "" : "%" + categoryLevel + "%"),
				page);
	}

}
