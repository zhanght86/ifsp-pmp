/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.term.process.service 
 * FileName: PaypInfoService.java
 * Author:   wangyl
 * Date:     2016年8月10日 下午2:36:59
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   wangyl           2016年8月10日下午2:36:59                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.term.process.service;

import java.util.List;
import java.util.Map;

import com.ruim.ifsp.utils.verify.IfspDataVerifyUtil;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.term.common.constants.TermConstants;
import com.ruimin.ifs.term.process.bean.PaypMachInf;

/**
 * 名称：设备库存信息管理Service<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月10日 <br>
 * 作者：wangyl <br>
 * 说明：<br>
 */

public class PaypMachInfoService extends SnowService {
	public static PaypMachInfoService getInstance() throws SnowException {
		return ContextUtil.getSingleService(PaypMachInfoService.class);
	}

	/**
	 * * 功能描述: 批量插入设备库存信息
	 * 
	 * @param list
	 *            设备库存JavaBeanLIst
	 * @return 返回类型 List<Object>
	 * @throws SnowException
	 */
	public List<PaypMachInf> batchInsertPaypMachInf(List<PaypMachInf> list) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		List<PaypMachInf> resultList = dao.insert(list);
		return resultList;
	}

	/**
	 * * 功能描述: 查询没有被绑定的设备号
	 * 
	 * @return 返回类型 List<Object>
	 * @throws SnowException
	 */
	public PageResult queryNoBingMachIdList(Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.term.rql.paypTerm.queryNoBingMachIdList", RqlParam.map(), page);
	}

	/**
	 * * 功能描述: 更改设备号的 设备状态 （） 0-已入库,1-已出库,2-作废
	 * 
	 * @return 返回类型 List<Object>
	 * @throws SnowException
	 */
	public void updateMachMachStat(String flag, String machId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		if (TermConstants.MACH_STAT_0.equals(flag) || TermConstants.MACH_STAT_1.equals(flag)
				|| TermConstants.MACH_STAT_2.equals(flag)) {
			StringBuffer sql = new StringBuffer(
					"update PAYP_MACH_INF  set MACH_STAT  = '" + flag + "' where mach_Id = '" + machId + "'");
			dao.executeUpdateSql(sql.toString());
		} else {
			SnowExceptionUtil.throwWarnException(flag, " 0-已入库,1-已出库,2-作废！ 只能是0、1、2标示");
		}

	}

	@Action
	@SnowDoc(desc = "设备库存信息查询")
	public PageResult queryListPaypMachInf(Map<String, Object> param, Page page) throws SnowException {
		// 获取一个DAO对象，它会当前默认的数据源
		DBDao dao = DBDaos.newInstance();
		// 调用查询方法，key为RQL配置文件的包名+<select>节点的id
		RqlParam map = RqlParam.map();
		if (IfspDataVerifyUtil.isNotBlank(param.get("machId"))) {
			map.set("machId", "%" + param.get("machId") + "%");
		}
		if (IfspDataVerifyUtil.isNotBlank(param.get("batchNo"))) {
			map.set("batchNo", "%" + param.get("batchNo") + "%");
		}
		if (IfspDataVerifyUtil.isNotBlank(param.get("propertyType"))) {
			map.set("propertyType", "%" + param.get("propertyType") + "%");
		}
		if (IfspDataVerifyUtil.isNotBlank(param.get("machType"))) {
			map.set("machType", "%" + param.get("machType") + "%");
		}
		if (IfspDataVerifyUtil.isNotBlank(param.get("machInst"))) {
			map.set("machInst", "%" + param.get("machInst") + "%");
		}
		if (IfspDataVerifyUtil.isNotBlank(param.get("machStat"))) {
			map.set("machStat", "%" + param.get("machStat") + "%");
		}
		System.out.println("库存设备查询条件： "+map.toString());
		return dao.selectList("com.ruimin.ifs.term.rql.paypTerm.queryPaypMachInfo", map, page);
	}

	/**
	 * * 功能描述: 保存设备库存信息
	 * 
	 * @param PaypMachInf
	 *            设备库存JavaBean
	 * @return 返回类型void
	 * @throws SnowException
	 */
	public void savePaypMachInf(PaypMachInf paypMachInf) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(paypMachInf);
	}

	/**
	 * * 功能描述: 更新设备库存信息
	 * 
	 * @param PaypMachInf
	 *            设备库存JavaBean
	 * @return 返回类型void
	 * @throws SnowException
	 */
	public void updatePaypMachInf(PaypMachInf paypMachInf) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String sql = "UPDATE  PAYP_MACH_INF  SET MACH_STAT = '" + TermConstants.MACH_STAT_2 + "' where MACH_ID = '"
				+ paypMachInf.getMachId() + "'";
		dao.executeUpdateSql(sql);
	}
	
	/**
	 * 功能描述: 查询终端库存编号
	 * */
	public String selectMachId(String MachId) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.term.rql.paypTerm.selectMachId",MachId);
	}
}
