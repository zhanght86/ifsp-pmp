/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.mchtMng.process.service 
 * FileName: RateTempletMngService.java
 * Author:   zrx
 * Date:     2016年7月18日 下午4:09:33
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月18日下午4:09:33                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mchtMng.process.service;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.mchtMng.process.bean.RateBaseInfo;

import java.util.List;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月18日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
public class RateTempletMngService extends SnowService {
	public static RateTempletMngService getInstance() throws SnowException {
		return ContextUtil.getSingleService(RateTempletMngService.class);
	}

	/**
	 * 分页查询接入方式信息
	 * 
	 * @param rateId
	 *            费率编号
	 * @param rateName
	 *            费率名称
	 * @parameter sectionType 分段标志
	 * @param page
	 *            分页对象
	 */
	public PageResult queryList(String rateId, String rateName, String sectionType, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.rateTempletMng.queryList",
				RqlParam.map().set("rateId", StringUtils.isEmpty(rateId) ? "" : rateId)
						.set("sectionType", StringUtils.isEmpty(sectionType) ? "" : sectionType)
						.set("rateName", StringUtils.isEmpty(rateName) ? ""
								: "%" + rateName + "%"),
				page);
	}

	/**
	 * 新增费率模板信息
	 * 
	 * @throws SnowException
	 */
	public void insertRateTemplet(RateBaseInfo rateBaseInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(rateBaseInfo);
	}

	/**
	 * 修改费率模板信息
	 * 
	 * @throws SnowException
	 */
	public void updateRateTemplet(RateBaseInfo rateBaseInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(rateBaseInfo);
	}

	/**
	 * 删除费率模板信息
	 * 
	 * @throws SnowException
	 */
	public void delete(String rateId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(RateBaseInfo.class, rateId);
	}

	/**
	 * 从商户合同账户费率表中查询费率模板是否被使用
	 * 
	 * @return
	 */
	public int select(String rateId) {
		DBDao dao = DBDaos.newInstance();
		List<Object> list = dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.rateTempletMng.query",
				RqlParam.map().set("rateId", StringUtils.isEmpty(rateId) ? "" : rateId));
		return list.size();

	}

	/**
	 * 
	 * @return 查询费率基本信息表的最大序列号
	 * @throws SnowException
	 */
	public String queryMaxSeq() {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.rateTempletMng.queryMaxSeq");
	}

	/**
	 * 
	 * @return 查询费率规则表的最大序列号
	 * @throws SnowException
	 */
	public String queryMaxSeq1() {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.rateTempletMng.queryMaxSeq1");
	}

	/**
	 * 获取费率模板名字
	 * 
	 * @param prodIds
	 * @return
	 * @throws SnowException
	 */
	public List<Object> getchlRateName(String chlRates) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String[] chlRateArray = chlRates.split(",");

		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.rateTempletMng.getchlRateName",
				RqlParam.map().set("chlRateArray", chlRateArray));
	}

	/**
	 * 查询费率模板
	 * 
	 * @return
	 * @throws SnowException
	 */
	public PageResult querySelect(Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return  dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.rateTempletMng.querySelect", page);
	}

}
