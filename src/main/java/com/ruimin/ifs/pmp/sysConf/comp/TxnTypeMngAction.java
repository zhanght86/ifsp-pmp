/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.accessTypeMng.comp 
 * FileName: TxnTypeMngAction.java
 * Author:   zrx
 * Date:     2016年7月14日 上午10:00:50
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月14日上午10:00:50                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.sysConf.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.sysConf.process.bean.TxnTypeInfo;
import com.ruimin.ifs.pmp.sysConf.process.service.TxnTypeMngService;

import javax.servlet.ServletRequest;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月14日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
@SnowDoc(desc = "交易类型管理操作Action")
@ActionResource
public class TxnTypeMngAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询交易类型信息")
	public PageResult queryTxnTypeInfo(QueryParamBean queryBean) throws SnowException {
		// 获取查询参数
		// 交易类型编号
		String qtxnTypeCode = queryBean.getParameter("qtxnTypeCode");
		// 交易类型名称
		String qtxnTypeName = queryBean.getParameter("qtxnTypeName");
		// 查询标志
		String param = queryBean.getParameter("param");
		if ("detail".equals(param)) {
			String accessTypeCode = queryBean.getParameter("accessTypeCode");
			return TxnTypeMngService.getInstance().queryTxnByAccessTypeCode(accessTypeCode, queryBean.getPage());
		} else {
			// 分页查询交易类型信息
			return TxnTypeMngService.getInstance().queryList(qtxnTypeCode, qtxnTypeName, queryBean.getPage());
		}
	}

	public static String getTxnTypeName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		TxnTypeInfo txnTypeInfo = TxnTypeMngService.getInstance().getTxnTypeName(key);
		return txnTypeInfo.getTxnTypeName();
	}

}
