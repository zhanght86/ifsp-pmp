/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.accessTypeMng.process.service 
 * FileName: AccessTypeMngService.java
 * Author:   zrx
 * Date:     2016年7月8日 下午3:18:23
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月8日下午3:18:23                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.sysConf.process.service;

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
import com.ruimin.ifs.pmp.sysConf.process.bean.AccessTypeInfo;
import com.ruimin.ifs.pmp.sysConf.process.bean.RelAccessTypeTxnType;
import com.ruimin.ifs.pmp.sysConf.process.bean.TxnTypeInfo;

import java.util.List;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月8日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */

@SnowDoc(desc = "接入方式关联交易类型Service")
@Service
public class AccessTypeRelTxnTypeService extends SnowService {
	public static AccessTypeRelTxnTypeService getInstance() throws SnowException {
		return ContextUtil.getSingleService(AccessTypeRelTxnTypeService.class);
	}

	/**
	 * 分页查询接入方式信息
	 * 
	 * @param qAccessTypeCode
	 *            接入方式编号
	 * @param qDataState
	 *            数据有效状态
	 * @param page
	 *            分页对象
	 */
	public PageResult queryList(String qAccessTypeCode, String qDataState, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.sysConf.rql.accessTypeRelTxnType.queryList",
				RqlParam.map().set("qAccessTypeCode", StringUtils.isEmpty(qAccessTypeCode) ? "" : qAccessTypeCode)
						.set("qDataState", StringUtils.isEmpty(qDataState) ? "" : qDataState),
				page);
	}
}
