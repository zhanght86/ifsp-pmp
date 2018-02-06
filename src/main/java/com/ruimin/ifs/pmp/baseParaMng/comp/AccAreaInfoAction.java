/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.baseParaMng.comp 
 * FileName: OpenAcctOrgan.java
 * Author:   chenqilei
 * Date:     2016年7月19日 上午10:12:35
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月19日上午10:12:35                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.baseParaMng.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.pmp.baseParaMng.process.service.AccAreaCodeService;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月19日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
@ActionResource
public class AccAreaInfoAction {
	/**
	 * 查询区划码信息
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action()
	@SnowDoc(desc = "查询区划码信息")
	public PageResult pageQuery(QueryParamBean queryBean) throws SnowException {
		// 省级名称
		String qProviceNm = queryBean.getParameter("qProviceNm");
		// 市级名称
		String qCityNm = queryBean.getParameter("qCityNm");
		return AccAreaCodeService.getInstance().queryList(qProviceNm, qCityNm, queryBean.getPage());
	}
}
