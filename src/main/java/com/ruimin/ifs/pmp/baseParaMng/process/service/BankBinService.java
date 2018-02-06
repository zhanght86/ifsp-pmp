/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.baseParaMng.process.service 
 * FileName: BankBinService.java
 * Author:   chenqilei
 * Date:     2016年7月20日 上午11:07:06
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月20日上午11:07:06                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.baseParaMng.process.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.baseParaMng.process.bean.BankBin;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月20日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
public class BankBinService {
	public static BankBinService getInstance() throws SnowException {
		return ContextUtil.getSingleService(BankBinService.class);
	}

	/**
	 * 银联卡表全查询
	 * 
	 * @param issueOrgId
	 *            发卡机构编号 [模糊查询]
	 * @param cardBinNo
	 *            卡BIN号 [模糊查询]
	 * @param cardType
	 *            卡类型 [精确查询]
	 * @param page
	 *            查询的条数
	 * @return 满足条件的银联卡表信息
	 * @throws SnowException
	 */
	public PageResult queryListTmp(String issueOrgId, String cardBinNo, String cardType, Page page)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.baseParaMng.rql.bankBin.queryListTmp",
				RqlParam.map().set("issueOrgId", StringUtils.isEmpty(issueOrgId) ? "" : "%" + issueOrgId + "%")
						.set("cardBinNo", StringUtils.isEmpty(cardBinNo) ? "" : "%" + cardBinNo + "%")
						.set("cardType", StringUtils.isEmpty(cardType) ? "" : cardType),
				page);
	}

	/**
	 * 银联卡表修改
	 * 
	 * @param bank
	 *            银联卡表实体类的对象
	 * @throws SnowException
	 */
	public void updateBank(BankBin bank) throws SnowException {
		DBDao dao = DBDaos.newInstance();

		dao.executeUpdate("com.ruimin.ifs.pmp.baseParaMng.rql.bankBin.updateBank", bank);
	}

	/**
	 * 银联卡表删除
	 * 
	 * @param bank
	 *            银联卡表实体类的对象
	 * @throws SnowException
	 */
	public void deleteBank(BankBin bank) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(bank);
	}

	/**
	 * 银联卡表新增
	 * 
	 * @param bank
	 *            银联卡表实体类的对象
	 * @throws SnowException
	 */
	public void addBank(BankBin bank) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(bank);
	}

	/**
	 * 批量新增数据
	 * 
	 * @param listBankBin
	 *            银联卡表的集合
	 * @throws SnowException
	 */
	public void batchAdd(List<BankBin> listBankBin) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(listBankBin);
	}

	public int queryBankBinByBin(Map<String, String> rqlMap) {
		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.pmp.baseParaMng.rql.bankBin.queryBankBinByBin", rqlMap);
	}

	public String getBankBinId() {
		// TODO Auto-generated method stub
		DBDao dao = DBDaos.newInstance();
		String seqNo = (String) dao.selectOne("com.ruimin.ifs.pmp.baseParaMng.rql.bankBin.queryBankBinSeqNo");
		if (seqNo != null) {
			return seqNo;
		} else {
			return "1";
		}
	}

	/**
	 * 删除原有表中的所有数据
	 * 
	 * @throws SnowException
	 */
	public void deleteAll() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.baseParaMng.rql.bankBin.deleteAll", "");
	}
}
