/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.term.process.service 
 * FileName: PaypTermEntryService.java
 * Author:   wangyl
 * Date:     2016年8月11日 下午3:41:20
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   wangyl           2016年8月11日下午3:41:20                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.term.process.service;

import java.util.List;

import com.ruimin.ifs.core.annotation.Action;
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
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.term.process.bean.PaypTermInf_temp;
import com.ruimin.ifs.term.process.bean.PaypTermKey;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：终端信息查询<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月11日 <br>
 * 作者：wangyl <br>
 * 说明：<br>
 */
// Service注解表示该类为服务，必须有此注解才能使用ContextUtil获取
@Service
public class PaypTermInfoService extends SnowService {
	/**
	 * 查询所有商户
	 * 
	 * @return
	 * @throws SnowException
	 */
	public PageResult listAllMC(String mchtId, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectListIn("com.ruimin.ifs.term.rql.mchtEntry.queryAllMchtChnls",
				RqlParam.map().set("mchtId", StringUtils.isBlank(mchtId) ? "" : "%" + mchtId + "%"), page);
	}

	/**
	 * 根据商户ID 查询对应的产品信息
	 * 
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryMchtProdInfoByMchtId(String mchtId, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		PageResult p = dao.selectList("com.ruimin.ifs.term.rql.paypTerm.queryMchtProdInfo",
				RqlParam.map().set("mchtId", mchtId), page);
		return p;

	}

	/**
	 * 根据商户ID 查询对应的产品信息总数
	 * 
	 * @return
	 * @throws SnowException
	 */
	public int queryMchtProdInfoByMchtIdCount(String mchtId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		int count = dao.selectCount("com.ruimin.ifs.term.rql.paypTerm.queryMchtProdInfoCount",
				RqlParam.map().set("mchtId", mchtId));
		return count;

	}

	/**
	 *
	 * @return服务实例，单例
	 * @throwsSnowException
	 */
	public static PaypTermInfoService getInstance() throws SnowException {
		// 根据class获取服务实例
		return ContextUtil.getSingleService(PaypTermInfoService.class);
	}

	@Action
	@SnowDoc(desc = "获取数据")
	public PageResult queryList(String termId, String mchtId, String termStat, String termType, String mchtName,
			String machInst, String auditId, Page page) throws SnowException {
		// 获取一个DAO对象，它会当前默认的数据源
		DBDao dao = DBDaos.newInstance();
		// 调用查询方法，key为RQL配置文件的包名+<select>节点的id
		PageResult p = dao.selectList("com.ruimin.ifs.term.rql.paypTerm.queryPaypTermInfo",
				RqlParam.map().set("termId", "%" + termId + "%").set("mchtId", "%" + mchtId + "%")
						.set("termStat", "%" + termStat + "%").set("termType", "%" + termType + "%")
						.set("mchtName", "%" + mchtName + "%").set("machInst", "%" + machInst + "%")
						.set("auditId", "%" + auditId + "%"),
				page);
		return p;
	}

	@Action
	@SnowDoc(desc = "保存终端信息")
	public void savePaypTermInf(PaypTermInf_temp paypTermInf) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(paypTermInf);
	}

	@Action
	@SnowDoc(desc = "修改终端信息")
	public void updatePaypTermInf(PaypTermInf_temp paypTermInf) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(paypTermInf);
	}

	@Action
	@SnowDoc(desc = "终端绑定设备库存编号 ")
	public void updatePaypTermInfBingMachId(String machId, String mchtId, String tlrNo, String currentDateTime)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String sql = "UPDATE  Payp_Term_Inf_Tmp SET MACH_ID = '" + machId + "',LAST_UPD_TLR='" + tlrNo
				+ "',LAST_UPD_DATE_TIME='" + currentDateTime + "' WHERE Term_ID = '" + mchtId + "'";
		dao.executeUpdateSql(sql);
	}

	@Action
	@SnowDoc(desc = "启用或停用终端 ")
	/*
	 * 00 新增未审核 01 启用 02 新增被拒绝 03 修改待审核 04 修改被拒绝 05 启动待审核 06 启动被拒绝 07 停用待审核 08
	 * 停用被拒绝 09 绑定待审核 10 绑定被拒绝 11 解绑待审核 12 解绑被拒绝 13 绑定 14 解绑 99 停用
	 */
	public void updatePaypTermInfTermStat(PaypTermInf_temp paypTermInf, String termId, String tlrNo,
			String currentDateTime) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String termStat = paypTermInf.getTermStat();
		String auditId = paypTermInf.getAuditId();
		String sql = "UPDATE  Payp_Term_Inf_Tmp SET TERM_STAT = '" + termStat + "',AUDIT_ID ='" + auditId
				+ "',LAST_UPD_TLR='" + tlrNo + "',LAST_UPD_DATE_TIME='" + currentDateTime + "' WHERE TERM_ID = '"
				+ termId + "'";
		dao.executeUpdateSql(sql);
	}

	@Action
	@SnowDoc(desc = "解除终端绑定的设备库存编号 ")
	public void updateUnPaypTermInfBingMachId(String mchtId, String tlrNo, String currentDateTime)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String sql = "UPDATE  Payp_Term_Inf_Tmp SET MACH_ID = '',LAST_UPD_TLR='" + tlrNo + "',LAST_UPD_DATE_TIME='"
				+ currentDateTime + "' WHERE term_ID = '" + mchtId + "'";
		dao.executeUpdateSql(sql);
	}

	@Action
	@SnowDoc(desc = "获取数据")
	public PaypTermKey queryKey(String termId, String mchtId) throws SnowException {
		// 获取一个DAO对象，它会当前默认的数据源
		DBDao dao = DBDaos.newInstance();
		PaypTermKey paypTermKey = (PaypTermKey) dao.selectOne("com.ruimin.ifs.term.rql.paypTerm.queryKey",
				RqlParam.map().set("termId", StringUtil.trimHeadAndEnd(termId)).set("mchtId",
						StringUtil.trimHeadAndEnd(mchtId)));
		return paypTermKey;
	}

	public List<Object> querybatDownLoadFile(String termId, String mchtId, String termStat, String termType,
			String mchtName, String machInst) {
		// 获取一个DAO对象，它会当前默认的数据源
		DBDao dao = DBDaos.newInstance();
		// 调用查询方法，key为RQL配置文件的包名+<select>节点的id

		List<Object> selectList = dao.selectList("com.ruimin.ifs.term.rql.paypTerm.queryPaypTermInfo",
				RqlParam.map().set("termId", "%" + termId + "%").set("mchtId", "%" + mchtId + "%")
						.set("termStat", "%" + termStat + "%").set("termType", "%" + termType + "%")
						.set("mchtName", "%" + mchtName + "%").set("machInst", "%" + machInst + "%")
						.set("auditId", "%" + "" + "%"));

		return selectList;

	}

}
