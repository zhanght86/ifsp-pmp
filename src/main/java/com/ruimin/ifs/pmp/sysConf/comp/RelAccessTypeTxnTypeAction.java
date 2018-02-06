/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.sysConf.comp 
 * FileName: RelAccessTypeTxnTypeAction.java
 * Author:   zrx
 * Date:     2016年7月28日 下午3:15:59
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月28日下午3:15:59                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.sysConf.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.pmp.sysConf.process.service.RelAccessTypeTxnTypeService;

import java.util.List;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月28日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
@SnowDoc(desc = "接入方式与交易类型关联操作Action")
@ActionResource
public class RelAccessTypeTxnTypeAction {
	@Action
	@SnowDoc(desc = "查询交易类型信息")
	public List<Object> queryAll(QueryParamBean queryBean) throws SnowException {
		// 获取查询参数
		// 获取接入方式编号
		String accessTypeCode = queryBean.getParameter("accessTypeCode");
		// 分页查询交易类型信息
		return RelAccessTypeTxnTypeService.getInstance().queryList(accessTypeCode);
	}

}
