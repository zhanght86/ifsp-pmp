/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.baseParaMng.process.service 
 * FileName: OpenAcctOrganService.java
 * Author:   chenqilei
 * Date:     2016年7月19日 上午10:19:09
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月19日上午10:19:09                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.baseParaMng.process.service;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;


import org.apache.commons.lang.StringUtils;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月19日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
public class AccAreaCodeService extends SnowService {
	public static AccAreaCodeService getInstance() throws SnowException {
		return ContextUtil.getSingleService(AccAreaCodeService.class);
	}

	/**
	 * 查询区划码信
	 * 
	 * @param qProvinceNm
	 *            省 [模糊查询]
	 * @param qCityNm
	 *           市 [模糊查询]
	 * @param page
	 *            查询的条数
	 * @return 满足条件的区划信息
	 * @throws SnowException
	 */
	public PageResult queryList(String qProviceNm, String qCityNm, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao
				.selectList("com.ruimin.ifs.pmp.baseParaMng.rql.accAreaCode.queryList",
						RqlParam.map().set("qProviceNm", StringUtils.isEmpty(qProviceNm) ? "" : "%" + qProviceNm + "%")
								.set("qCityNm", StringUtils.isEmpty(qCityNm) ? "" : "%" + qCityNm + "%"),
						page);
	}

}
