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
import com.ruimin.ifs.pmp.payProdMng.process.bean.AccessAndTxnType;

@Service
public class TxnTypeQueryService extends SnowService {
	/**
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static TxnTypeQueryService getInstance() throws SnowException {
		// 根据class获取服务实例
		return ContextUtil.getSingleService(TxnTypeQueryService.class);
	}

	/**
	 * 查询指定接入方式下的交易类型
	 * 
	 * @param qaccessTypeCode
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryTxnTypeInfo(String qaccessTypeCode, Page page) throws SnowException {
		// 获取一个DAO对象
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.payProdMng.rql.TXN_TYPE_INFO.queryTxnTypeInfo",
				RqlParam.map().set("qaccessTypeCode", StringUtils.isBlank(qaccessTypeCode) ? "" : qaccessTypeCode),
				page);
	}

	/**
	 * 查询产品编号下的交易类型
	 * 
	 * @param qprodId
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public List<AccessAndTxnType> queryTxnTypeInfoByProdId(String qprodId, Page page) throws SnowException {
		// 获取一个DAO对象
		DBDao dao = DBDaos.newInstance();
		return dao.queryAll(
				"select m.*,f.access_type_code,g.access_type_name  from PBS_TXN_TYPE_INFO m left join PBS_PROD_REL_TXN_TYPE n on m.txn_type_code=n.txn_type_code left join PBS_PROD_INFO f on f.prod_id=n.prod_id left join PBS_ACCESS_TYPE_INFO g on g.access_type_code=f.access_type_code where n.PROD_ID= '"
						+ qprodId + "' order by m.CRT_DATE_TIME DESC",
				AccessAndTxnType.class);
	}
	
	
	//2017-04-10修改
	public List<Object> query(String qprodId) throws SnowException {
		// 获取一个DAO对象
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.payProdMng.rql.TXN_TYPE_INFO.query",
				RqlParam.map().set("qprodId", StringUtils.isBlank(qprodId) ? "" : qprodId.trim()));
	}
//	public PageResult query(String qprodId, Page page) throws SnowException {
//		// 获取一个DAO对象
//		DBDao dao = DBDaos.newInstance();
//		return dao.selectList("com.ruimin.ifs.pmp.payProdMng.rql.TXN_TYPE_INFO.query",
//				RqlParam.map().set("qprodId", StringUtils.isBlank(qprodId) ? "" : qprodId.trim()), page);
//	}

	/**
	 * 根据产品编号查询产品列表
	 */
	public List<Object> getAcctTypeName(String prodIds) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String[] prodIdArray = prodIds.split(",");

		return dao.selectList("com.ruimin.ifs.pmp.payProdMng.rql.TXN_TYPE_INFO.getAcctTypeName",
				RqlParam.map().set("prodIdArray", prodIdArray));
	}

	public List<Object> getTxnTypeName(String prodIds) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String[] prodIdArray = prodIds.split(",");

		return dao.selectList("com.ruimin.ifs.pmp.payProdMng.rql.TXN_TYPE_INFO.getTxnTypeName",
				RqlParam.map().set("prodIdArray", prodIdArray));
	}
}
