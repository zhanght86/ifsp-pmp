/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.process.service 
 * FileName: GalRoutTactMngService.java
 * Author:   chenqilei
 * Date:     2016年7月27日 下午2:53:56
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月27日下午2:53:56                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.service;

import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.chnlMng.process.bean.GalBasicInfo;
import com.ruimin.ifs.pmp.chnlMng.process.bean.GalRoutTactMng;
import com.ruimin.ifs.pmp.chnlMng.process.bean.TactAndBasic;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月27日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
@SnowDoc(desc = "通道路由策略管理Service")
public class GalRoutTactMngService {
	public static GalRoutTactMngService getInstance() throws SnowException {
		return ContextUtil.getSingleService(GalRoutTactMngService.class);
	}

	/**
	 * 分页查询通道路由策略管理信息
	 * 
	 * @param ttsCode
	 *            路由策略编号 [模糊查询]
	 * @param ttsResp
	 *            路由策略名称 [模糊查询]
	 * @param ttsType
	 *            路由策略类型 [精确查询]
	 * @param ttsStat
	 *            路由策略状态 [精确查询]
	 * @param page
	 *            查询的条数
	 * @return 符合查询条件的所有信息
	 * @throws SnowException
	 */
	public PageResult queryAll(String ttsCode, String ttsResp, String ttsType, String ttsStat, Page page)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.galRoutTactMng.queryAll",
				RqlParam.map().set("ttsCode", StringUtils.isEmpty(ttsCode) ? "" : "%" + ttsCode + "%")
						.set("ttsResp", StringUtils.isEmpty(ttsResp) ? "" : "%" + ttsResp + "%")
						.set("ttsType", StringUtils.isEmpty(ttsType) ? "" : ttsType)
						.set("ttsStat", StringUtils.isEmpty(ttsStat) ? "" : ttsStat),
				page);
	}

	/**
	 * 分页查询通道基本信息表
	 * 
	 * @param ttsCode
	 *            路由策略编号
	 * @param page
	 *            分页查询
	 * @return 符合条件的通道信息
	 * @throws SnowException
	 */
	public PageResult queryName(String ttsCode, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.galRoutTactMng.queryName",
				RqlParam.map().set("ttsCode", StringUtils.isEmpty(ttsCode) ? "" : ttsCode.trim()), page);
	}

	/**
	 * 新增时查询通道的名称 并让优先级按照从1 到 2 .....排列
	 * 
	 * @param ttsCode
	 *            路由策略编号
	 * @param page
	 *            分页查询
	 * @return 符合条件的通道信息
	 * @throws SnowException
	 */
	public PageResult queryGalBasicInfo(String ttsCode, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.galRoutTactMng.queryGalBasicInfo",
				RqlParam.map().set("ttsCode", StringUtils.isEmpty(ttsCode) ? "" : ttsCode.trim()), page);
	}

	/**
	 * 查询最大序列号
	 * 
	 * @return
	 * @throws SnowException
	 */
	public String queryMaxSeq() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.galRoutTactMng.queryMaxSeq");
	}

	/**
	 * 新增交易表中的数据
	 * 
	 * @param tact
	 *            路由策略关系表实体类的对象
	 * @throws SnowException
	 */
	public void addData(TactAndBasic tact) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.chnlMng.rql.galRoutTactMng.addData", tact);
	}

	/**
	 * 新增路由策略表中的数据
	 * 
	 * @param rout
	 *            路由策略表实体类的对象
	 * @throws SnowException
	 */
	public void addRoutMng(GalRoutTactMng rout) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(rout);
	}

	/**
	 * 修改路由策略的状态
	 * 
	 * @param rout
	 *            路由策略表实体类的对象
	 * @throws SnowException
	 */
	public void updateTtsStat(GalRoutTactMng rout) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String ttsStat = rout.getTtsStat();
		String lastUpdTlr = rout.getLastUpdTlr();
		String lastUpdDate = rout.getLastUpdDateTime();
		String ttsCode = rout.getTtsCode();
		dao.executeUpdate("com.ruimin.ifs.pmp.chnlMng.rql.galRoutTactMng.updateTtsStat",
				RqlParam.map().set("ttsStat", ttsStat).set("lastUpdTlr", lastUpdTlr).set("lastUpdDate", lastUpdDate)
						.set("ttsCode", ttsCode));
	}

	/**
	 * 修改交易表中的信息
	 * 
	 * @param basic
	 *            路由策略关系表实体类的对象
	 * @throws SnowException
	 */
	public void updateByTtsCodeInfo(TactAndBasic basic) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String priorityVul = basic.getPriorityVul();
		String ttsCode = basic.getTtsCode();
		String pagyNo = basic.getPagyNo();
		dao.executeUpdate("com.ruimin.ifs.pmp.chnlMng.rql.galRoutTactMng.updateByTtsCodeInfo",
				RqlParam.map().set("priorityVul", priorityVul).set("ttsCode", ttsCode).set("pagyNo", pagyNo));
	}

	/**
	 * 修改路由策略表中的数据
	 * 
	 * @param rout
	 *            路由策略表实体类的对象
	 * @throws SnowException
	 */
	public void updateRoutMng(GalRoutTactMng rout) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(rout);
	}

	/**
	 * 根据通道编号查询通道名称
	 * 
	 * @param prodIds
	 *            通道编号
	 * @return 通道的基本信息
	 * @throws SnowException
	 */
	public List<Object> getPagyNoName(String prodIds) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String[] prodIdArray = prodIds.split(",");
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.galRoutTactMng.getPagyNoName",
				RqlParam.map().set("prodIdArray", prodIdArray));
	}

	/**
	 * 到支付通道策略关系表查询同一个策略下通道的优先级有没有重复的
	 * 
	 * @param priorityVul
	 *            优先级
	 * @param ttsCode
	 *            路由策略编号
	 * @return 满足条件的条数
	 * @throws SnowException
	 */
	public int selectPriorityVul(String priorityVul, String ttsCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.galRoutTactMng.selectPriorityVul",
				RqlParam.map().set("priorityVul", StringUtils.isEmpty(priorityVul) ? "" : priorityVul).set("ttsCode",
						StringUtils.isEmpty(ttsCode) ? "" : ttsCode));
	}

	/**
	 * 查询每个策略下有没有重复的通道
	 * 
	 * @param pagyNo
	 *            通道编号
	 * @param ttsCode
	 *            路由策略编号
	 * @return 满足条件的条数
	 * @throws SnowException
	 */
	public int selectPagyNo(String pagyNo, String ttsCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.galRoutTactMng.selectPagyNo",
				RqlParam.map().set("pagyNo", StringUtils.isEmpty(pagyNo) ? "" : pagyNo).set("ttsCode",
						StringUtils.isEmpty(ttsCode) ? "" : ttsCode));
	}

	/**
	 * 修改时删除表格中的数据
	 * 
	 * @param basic
	 *            路由策略关系表实体类的对象
	 * @throws SnowException
	 */
	public void deleteData(TactAndBasic basic) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(basic);
	}

	/**
	 * 根据策略编号去查询策略名称
	 * 
	 * @param prodIds
	 *            路由策略编号
	 * @return 路由策略名称
	 * @throws SnowException
	 */
	public String queryByTtsCode(String prodIds) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String[] prodIdArray = prodIds.split(",");
		return (String) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.galRoutTactMng.queryByTtsCode",
				RqlParam.map().set("prodIdArray", prodIdArray));
	}

	/**
	 * 查询支付通道策略表名称
	 * 
	 * @param page
	 *            分页查询条数
	 * @return 符合条件的信息
	 * @throws SnowException
	 */
	public PageResult queryTtsResp(Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.galRoutTactMng.queryTtsResp", page);
	}
}
