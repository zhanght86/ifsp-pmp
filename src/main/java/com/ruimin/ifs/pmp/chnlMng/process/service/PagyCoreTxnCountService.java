/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.chnlMng.process.service 
 * FileName: ChannelInfoService.java
 * Author:   zrx
 * Date:     2016年7月21日 上午10:51:06
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月21日上午10:51:06                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.service;

import java.util.Map;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.framework.process.query.Page;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月21日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
@SnowDoc(desc = "通道交易统计Service")
@Service
public class PagyCoreTxnCountService extends SnowService {
	public static PagyCoreTxnCountService getInstance() throws SnowException {
		return ContextUtil.getSingleService(PagyCoreTxnCountService.class);
	}

	/**
	 * 通道交易统计
	 */
	public PageResult queryList(Map<String, String> paramMap, Page page) {
		DBDao dao = DBDaos.newInstance();
		PageResult pageR=dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.pagyCoreTxnCount.queryList", paramMap, page);
		 return pageR;
	}
}
