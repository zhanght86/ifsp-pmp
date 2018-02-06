/**
 * 
 * Copyright (C), 2016-2016, 上海睿民互联网科技有限公司
 * FileName: AccountTypeService.java
 * Author:   Cheng
 * Date:     2016年7月14日下午4:34:18
 * Description: TODO
 * History: //修改记录
 * <author>      <time>                   <version>    <desc>
 * zhaodk      2016年7月14日下午4:34:18               0.1
 */
package com.ruimin.ifs.mng.process.service;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.Page;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.po.AccountType;
import com.ruimin.ifs.util.StringUtil;

/**
 * 〈审核流程维护〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author MJ
 */

public class AccountTypeService extends SnowService {

	public static AccountTypeService getInstance() throws SnowException {
		return ContextUtil.getSingleService(AccountTypeService.class);
	}

	/**
	 * * 功能描述: 账户类型查询
	 * 
	 * @param queryBean
	 *            qAcctTypeCode
	 * @param queryBean
	 *            qAcctTypeName
	 * @param queryBean
	 *            page
	 * @return 返回类型 PageResult
	 * @throws SnowException
	 */
	public PageResult queryList(String qAcctTypeCode, String qAcctTypeName, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.accountType.queryAll",
				RqlParam.map().set("qAcctTypeCode", qAcctTypeCode == null ? "" : "%" + qAcctTypeCode + "%")
						.set("qAcctTypeName", qAcctTypeName == null ? "" : "%" + qAcctTypeName + "%"),
				page);
	}

	/**
	 * * 功能描述: 生成账户类型的编号
	 * 
	 * @return 返回类型 String
	 * @throws SnowException
	 */
	public String genAcctTypeCode() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String Maxseq = (String) dao.selectOne("com.ruimin.ifs.mng.rql.accountType.getMaxSeq");
		if (StringUtil.isEmpty(Maxseq)) {
			Maxseq = "10";
		} else {
			Maxseq = Integer.parseInt(Maxseq) + 1 + "";
		}
		return Maxseq;
	}

	/**
	 * * 功能描述: 保存账户类型实体类
	 * 
	 * @return 返回类型 void
	 * @throws SnowException
	 */
	public void saveDataDicEntry(AccountType accountType) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(accountType);
	}

	/**
	 * * 功能描述: 修改账户类型实体类
	 * 
	 * @return 返回类型 void
	 * @throws SnowException
	 */

	public void update(AccountType accountType) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(accountType);
	}

	/**
	 * * 功能描述: 删除账户类型实体类
	 * 
	 * @return 返回类型 void
	 * @throws SnowException
	 */

	public void deleteByAcctTypeCode(String acctTypeCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(AccountType.class, acctTypeCode);
	}

}
