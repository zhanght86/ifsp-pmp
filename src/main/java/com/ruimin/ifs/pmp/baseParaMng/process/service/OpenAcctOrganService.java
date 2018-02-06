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
import com.ruimin.ifs.pmp.baseParaMng.process.bean.OpenAcctOrgan;

import java.util.List;

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
public class OpenAcctOrganService extends SnowService {
	public static OpenAcctOrganService getInstance() throws SnowException {
		return ContextUtil.getSingleService(OpenAcctOrganService.class);
	}

	/**
	 * 查询开户机构所有信息的方法
	 * 
	 * @param acctInstNo
	 *            开户机构编号 [模糊查询]
	 * @param acctInstName
	 *            开户机构名称 [模糊查询]
	 * @param page
	 *            查询的条数
	 * @return 满足条件的开户机构的所有信息
	 * @throws SnowException
	 */
	public PageResult queryListTmp(String acctInstNo, String acctInstName, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao
				.selectList("com.ruimin.ifs.pmp.baseParaMng.rql.openAcctOrgan.queryListTmp",
						RqlParam.map().set("acctInstNo", StringUtils.isEmpty(acctInstNo) ? "" : "%" + acctInstNo + "%")
								.set("acctInstName", StringUtils.isEmpty(acctInstName) ? "" : "%" + acctInstName + "%"),
						page);
	}

	/**
	 * 查询开户机构编号是否重复
	 * 
	 * @param acctInstNo
	 *            开户机构编号
	 * @return 开户机构信息
	 * @throws SnowException
	 */
	public OpenAcctOrgan queryByNo(String acctInstNo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (OpenAcctOrgan) dao.selectOne("com.ruimin.ifs.pmp.baseParaMng.rql.openAcctOrgan.queryByNo", acctInstNo);
	}

	/**
	 * 开户机构新增
	 * 
	 * @param oao
	 *            开户机构实体类对象
	 * @throws SnowException
	 */
	public void addAcct(OpenAcctOrgan oao) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(oao);
	}

	/**
	 * 开户机构修改
	 * 
	 * @param oao
	 *            开户机构实体类对象
	 * @throws SnowException
	 */
	public void updateAcct(OpenAcctOrgan oao) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(oao);
	}

	/**
	 * 根据开户机构表ID像商户信息表中查询有关这条数据的状态
	 * 
	 * @param acctInstNo
	 *            开户机构编号
	 * @return 商户信息的状态
	 * @throws SnowException
	 */
	public String queryByAcctInstNo(String acctInstNo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.baseParaMng.rql.openAcctOrgan.queryByAcctInstNo", acctInstNo);
	}

	/**
	 * 删除开户机构表中数据
	 * 
	 * @param oao
	 *            开户机构实体类对象
	 * @throws SnowException
	 */
	public void deleteAcct(OpenAcctOrgan oao) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(oao);
	}

	/**
	 * 根据开户机构编号查找开户机构名
	 * 
	 * @param prodIds
	 *            开户机构编号
	 * @return 开户机构信息
	 * @throws SnowException
	 */
	public List<Object> getSetlAcctInstituteName(String prodIds) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String[] prodIdArray = prodIds.split(",");
		return dao.selectList("com.ruimin.ifs.pmp.baseParaMng.rql.openAcctOrgan.getSetlAcctInstituteName",
				RqlParam.map().set("prodIdArray", prodIdArray));
	}

}
