package com.ruimin.ifs.pmp.payProdMng.process.service;

import java.util.List;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.payProdMng.process.bean.TxnTypeAndAccount;
import com.ruimin.ifs.pmp.sysConf.process.bean.AccountType;

@Service
public class AccountTypeQueryService extends SnowService {
	/**
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static AccountTypeQueryService getInstance() throws SnowException {
		// 根据class获取服务实例
		return ContextUtil.getSingleService(AccountTypeQueryService.class);
	}

	/**
	 * 指定交易方式下的账户类型
	 * 
	 * @param qtxnTypeCode
	 *            交易类型
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryAccountTypeInfo(String qtxnTypeCode, Page page) throws SnowException {
		// 获取一个DAO对象
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.payProdMng.rql.ACCOUNT_TYPE_INFO.queryAccountTypeInfo",
				RqlParam.map().set("qtxnTypeCode", StringUtils.isBlank(qtxnTypeCode) ? "" : qtxnTypeCode), page);
	}

	/**
	 * 查询产品类型下的账户类型
	 * 
	 * @param qprodId
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public List<TxnTypeAndAccount> queryAccountTypeByProdId(String qprodId, Page page) throws SnowException {
		// 获取一个DAO对象
		DBDao dao = DBDaos.newInstance();
		return dao.queryAll(
				"select a.*,b.txn_type_code from PAY_ACCT_TYPE_BASE_INFO a left join PBS_PROD_REL_TXN_ACCT b on a.acct_type_no=b.acct_type_code where b.prod_id='"
						+ qprodId + "' order by a.CRT_DATE_TIME DESC",
				TxnTypeAndAccount.class);
	}

}
