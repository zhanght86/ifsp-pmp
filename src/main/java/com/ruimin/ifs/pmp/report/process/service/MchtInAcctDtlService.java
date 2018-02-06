package com.ruimin.ifs.pmp.report.process.service;

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

@Service
@SnowDoc(desc = "商户入账明细信息service")
public class MchtInAcctDtlService extends SnowService {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static MchtInAcctDtlService getInstance() throws SnowException {
		return ContextUtil.getSingleService(MchtInAcctDtlService.class);
	}

	/**
	 * 
	 * 功能描述: 商户入账明细信息分页查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param qInAcctDate
	 * @param qMchtId
	 * @param page
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public PageResult pageQuery(String qInAcctDate, String qMchtId,String qAmtFlg, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.report.rql.mchtInAcctDtl.queryList",
				RqlParam.map().set("qInAcctDate", qInAcctDate).set("qMchtId", qMchtId).set("qAmtFlg", qAmtFlg), page);
	}
	/**
	 * 
	 * 功能描述: 商户入账明细信息报表查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param qInAcctDate
	 * @param qMchtId
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public List queryForExport(String qInAcctDate, String qMchtId,String qAmtFlg) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.report.rql.mchtInAcctDtl.queryList",
				RqlParam.map().set("qInAcctDate", qInAcctDate).set("qMchtId", qMchtId).set("qAmtFlg", qAmtFlg));
	}
}
