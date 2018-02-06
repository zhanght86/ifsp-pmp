/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.chnlMng.process.service 
 * FileName: ChannelInfoService.java
 * Author:   zrx
 * Date:     2016年7月21日 上午10:51:06
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月21日上午10:51:06                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.service;

import java.util.List;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.chnlMng.process.bean.ChannelInfo;
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagyChlTxnAcctRel;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月21日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
@SnowDoc(desc = "渠道信息管理操作Service")
@Service
public class ChannelInfoService extends SnowService {
	public static ChannelInfoService getInstance() throws SnowException {
		return ContextUtil.getSingleService(ChannelInfoService.class);
	}

	/**
	 * 
	 * 功能描述: 分页查询渠道信息
	 * 
	 * @param qchlId
	 * @param qchlName
	 * @param qchlStat
	 * @param qchlAccNo
	 * @return PageResult
	 * 
	 */
	public PageResult queryList(String qchlId, String qchlName, String qchlStat, String qchlAccNo, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.channelInfo.queryList",
				RqlParam.map().set("qchlId", StringUtils.isEmpty(qchlId) ? "" : "%" + qchlId + "%")
						.set("qchlName", StringUtils.isEmpty(qchlName) ? "" : "%" + qchlName + "%")
						.set("qchlAccNo", StringUtils.isEmpty(qchlAccNo) ? ""
								: "%" + qchlAccNo + "%")
						.set("qchlStat", StringUtils.isEmpty(qchlStat) ? "" : qchlStat),
				page);

	}

	/**
	 * 向渠道信息表插入数据
	 * 
	 * @throws SnowException
	 */
	public void insert(ChannelInfo channelInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(channelInfo);

	}

	/**
	 * 更新渠道信息表中的数据
	 * 
	 * @throws SnowException
	 */
	public void update(ChannelInfo channelInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(channelInfo);

	}

	/**
	 * 更新渠道信息表中的渠道状态
	 * 
	 * @throws SnowException
	 */
	public void updateChnlStat(ChannelInfo channelInfo) throws SnowException {
		// 获取渠道号
		String chlId = channelInfo.getChlId();
		// 获取渠道状态
		String chlStat = channelInfo.getChlStat();
		// 获取更改人及其时间
		String lastUpdTlr = channelInfo.getLastUpdTlr();
		String lastUpdDateTime = channelInfo.getLastUpdDateTime();
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.chnlMng.rql.channelInfo.updateChnlStat",
				RqlParam.map().set("chlId", chlId).set("chlStat", chlStat).set("lastUpdTlr", lastUpdTlr)
						.set("lastUpdDateTime", lastUpdDateTime));

	}

	/**
	 * 更新渠道信息表中的渠道状态（未使用--到使用）
	 * 
	 * @throws SnowException
	 */
	public void updateChnlStatOpenDate(ChannelInfo channelInfo) throws SnowException {
		// 获取渠道号
		String chlId = channelInfo.getChlId();
		// 获取渠道状态
		String chlStat = channelInfo.getChlStat();
		// 获取启用时间
		String chlOpenDate = channelInfo.getChlOpenDate();
		// 获取更改人及其时间
		String lastUpdTlr = channelInfo.getLastUpdTlr();
		String lastUpdDateTime = channelInfo.getLastUpdDateTime();
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.chnlMng.rql.channelInfo.updateChnlStatOpen",
				RqlParam.map().set("chlId", chlId).set("chlStat", chlStat).set("lastUpdTlr", lastUpdTlr)
						.set("chlOpenDate", chlOpenDate).set("lastUpdDateTime", lastUpdDateTime));

	}

	/**
	 * 查询渠道信息表最大序列号
	 */
	public String queryMaxSeq() {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.channelInfo.queryMaxSeq");

	}

	/**
	 * 用于刷新渠道权限使用
	 * 
	 * @param page
	 * @return
	 */
	public PageResult query(String chlId, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.channelInfo.query",
				RqlParam.map().set("chlId", StringUtils.isBlank(chlId) ? "" : chlId.trim()), page);
	}

	/**
	 * 对渠道权限重复性验证
	 * 
	 * @param chlId
	 * @param payTxnTypeId
	 * @param acctTypeNoReal
	 * @return
	 * @throws SnowException
	 */
	public PagyChlTxnAcctRel checkAdd(String chlId, String payTxnTypeId, String acctTypeNoReal) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		PagyChlTxnAcctRel pagyChlTxnAcctRel = (PagyChlTxnAcctRel) dao.selectOne(
				"com.ruimin.ifs.pmp.chnlMng.rql.channelInfo.check",
				RqlParam.map().set("chlId", StringUtils.isBlank(chlId) ? "" : chlId.trim())
						.set("payTxnTypeId", StringUtils.isBlank(payTxnTypeId) ? "" : payTxnTypeId.trim())
						.set("acctTypeNoReal", StringUtils.isBlank(acctTypeNoReal) ? "" : acctTypeNoReal));

		return pagyChlTxnAcctRel;
	}

	/**
	 * 渠道权限：渠道开通交易和账户关联表
	 * 
	 * @param chlId
	 *            渠道ID
	 * @param real
	 *            账户类型
	 * @param payTxnTypeId
	 *            路由交易
	 * @throws SnowException
	 */
	public void insertPagyChlTxnAcctRel(String chlId, String real, String payTxnTypeId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 交易和账户关联对象
		PagyChlTxnAcctRel pagyChlTxnAcctRel = new PagyChlTxnAcctRel();
		pagyChlTxnAcctRel.setChannelId(chlId);// 设置渠道ID
		pagyChlTxnAcctRel.setPayTxnTypeId(payTxnTypeId);// 设置渠道交易
		pagyChlTxnAcctRel.setAcctTypeNo(real);// 设置账户类型
		dao.insert(pagyChlTxnAcctRel);
	}

	/**
	 * 如果原来渠道权限有重复的，则删除
	 */
	public void delete(String chlId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String sql = "delete from PAGY_CHL_TXN_ACCT_REL where CHANNEL_ID='" + chlId + "'";
		dao.executeUpdateSql(sql);
	}

	/**
	 * 根据ID查询渠道启用时间
	 * 
	 * @param chlId
	 * @return
	 * @throws SnowException
	 */
	public ChannelInfo selectOpenTime(String chlId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		ChannelInfo channelInfo = (ChannelInfo) dao.selectOne(
				"com.ruimin.ifs.pmp.chnlMng.rql.channelInfo.selectOpenTime",
				RqlParam.map().set("chlId", StringUtils.isBlank(chlId) ? "" : chlId.trim()));
		return channelInfo;
	}

	/**
	 * 获取渠道信息名称
	 * 
	 * @param chnlIds
	 *            单个渠道编号或多个以","分割的渠道编号
	 * @return
	 * @throws SnowException
	 */
	public List<Object> getChnlNames(String chnlIds) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String[] chnlIdArray = chnlIds.split(",");

		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.channelInfo.getChnlNames",
				RqlParam.map().set("chnlIdArray", chnlIdArray));
	}

	/**
	 * 根据渠道编号去查询渠道名称
	 * 
	 * @param payTxnTypeId
	 * @return
	 * @throws SnowException
	 */
	public String queryByChnlName(String prodIds) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String[] prodIdArray = prodIds.split(",");
		return (String) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.channelInfo.queryByChnlName",
				RqlParam.map().set("prodIdArray", prodIdArray));
	}

	/**
	 * 查询渠道名称
	 * 
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryChnlName(Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.channelInfo.queryChnlName", page);
	}
}
