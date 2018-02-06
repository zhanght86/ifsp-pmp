/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.mchtMng.process.service 
 * FileName: RateRuleInfoService.java
 * Author:   zrx
 * Date:     2016年7月19日 上午10:42:15
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月19日上午10:42:15                     1.0                  
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
import com.ruimin.ifs.pmp.mchtMng.process.bean.RateRuleInfo;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月19日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
public class RateRuleInfoService extends SnowService {
	public static RateRuleInfoService getInstance() throws SnowException {
		return ContextUtil.getSingleService(RateRuleInfoService.class);
	}

	/**
	 * 分页查询活动方法分段列表
	 * 
	 * @param rateRuleNo
	 *            费率规则序号
	 * @param chargeType
	 *            收费类型
	 * @param page
	 *            分页对象
	 */
	public PageResult queryList(String rateId, String chargeType, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.rateRuleInfo.queryList",
				RqlParam.map().set("rateId", rateId).set("chargeType", chargeType), page);

	}

	/**
	 * 根据费率编号查询费率规则信息
	 */
	public List<Object> queryRateRuleinfo(String rateId) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.rateRuleInfo.query",
				RqlParam.map().set("rateId", StringUtils.isEmpty(rateId) ? "" : rateId));
	}

	/**
	 * 新增费率规则信息
	 * 
	 * @throws SnowException
	 */
	public void addRateRuleInfo(RateRuleInfo rateRuleInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(rateRuleInfo);

	}

	/**
	 * 修改费率规则表中的信息
	 * 
	 * @throws SnowException
	 */
	public void updateRateRuleInfo(RateRuleInfo rateRuleInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(rateRuleInfo);

	}

	/**
	 * 删除费率规则表中的信息
	 * 
	 * @throws SnowException
	 */
	public void daleteRateRuleInfo(RateRuleInfo rateRuleInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(rateRuleInfo);
	}

	/**
	 * 根据费率编号删除费率规则表中的信息
	 */
	public void delete(String rateId) {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.rateRuleInfo.deleteRateRuleInfo",
				RqlParam.map().set("rateId", StringUtils.isEmpty(rateId) ? "" : rateId));

	}

	/**
	 * 批量插入费率规则信息
	 * 
	 * @throws SnowException
	 */
	public void addRateRuleInfo(List<RateRuleInfo> list) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		if (null == list || list.size() == 0) {
			return;
		}
		for (RateRuleInfo param : list) {
			dao.insert(param);
		}
	}

	/**
	 * 批量删除费率规则信息
	 * 
	 * @throws SnowException
	 */
	public void deleteRateRuleInfo(List<RateRuleInfo> list) throws SnowException {
		if (null == list || list.size() == 0) {
			return;
		}
		DBDao dao = DBDaos.newInstance();
		for (RateRuleInfo param : list) {
			dao.delete(param);
		}
	}

	/**
	 * 批量更新费率规则信息
	 * 
	 * @throws SnowException
	 */
	public void updateRateRuleInfo(List<RateRuleInfo> list) throws SnowException {
		if (null == list || list.size() == 0) {
			return;
		}
		DBDao dao = DBDaos.newInstance();
		for (RateRuleInfo param : list) {
			dao.update(param);
		}
	}

}
