/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.sysConf.process 
 * FileName: AcctAndTradeService.java
 * Author:   chenqilei
 * Date:     2016年7月13日 下午2:28:27
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月13日下午2:28:27                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.sysConf.process.service;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;

import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.sysConf.process.bean.AcctAndTrade;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月13日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
public class AcctAndTradeService {
	public static AcctAndTradeService getInstance() throws SnowException {
		return ContextUtil.getSingleService(AcctAndTradeService.class);
	}

	/**
	 * 查询账户表信息
	 * 
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult query(Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.sysConf.rql.acctAndTrade.query", page);
	}

	public String queryMaxSeq() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.sysConf.rql.acctAndTrade.queryMaxSeq");
	}

	/**
	 * 关联表新增
	 * 
	 * @param aat
	 * @throws SnowException
	 */
	public void addMessage(AcctAndTrade aat) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(aat);
	}

}
