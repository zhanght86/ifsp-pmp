/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.accessTypeMng.comp 
 * FileName: AccessTypeMngAction.java
 * Author:   zrx
 * Date:     2016年7月8日 下午2:55:55
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx          2016年7月8日下午2:55:55                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.sysConf.comp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.payProdMng.process.service.PbsProdInfoService;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.sysConf.process.bean.AccessTypeInfo;
import com.ruimin.ifs.pmp.sysConf.process.bean.RelAccessTypeTxnType;
import com.ruimin.ifs.pmp.sysConf.process.bean.TxnTypeInfo;
import com.ruimin.ifs.pmp.sysConf.process.service.AccessTypeMngService;
import com.ruimin.ifs.pmp.sysConf.process.service.AccessTypeRelTxnTypeService;
import com.ruimin.ifs.pmp.sysConf.process.service.RelAccessTypeTxnTypeService;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月8日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */

@SnowDoc(desc = "接入方式关联交易类型Action")
@ActionResource
public class AccessTypeRelTxnTypeAction extends SnowAction {
	@Action
	@SnowDoc(desc = "根据接入方式查询关联交易类型")
	public PageResult queryAccessTypeInfo(QueryParamBean queryBean) throws SnowException {
		// 获取查询参数
		// 接入方式编号
		String qAccessTypeCode = queryBean.getParameter("qAccessTypeCode");
		// 数据状态
		String qDataState = queryBean.getParameter("qDataState");
		// 分页查询接入方式信息
		return AccessTypeRelTxnTypeService.getInstance().queryList(qAccessTypeCode, qDataState, queryBean.getPage());

	}

}
