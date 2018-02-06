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
import com.ruimin.ifs.gov.util.StringUtils;

@Service
@SnowDoc(desc = "商户入账结果信息service")
public class MchtInAcctRsltService extends SnowService {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static MchtInAcctRsltService getInstance() throws SnowException {
		return ContextUtil.getSingleService(MchtInAcctRsltService.class);
	}

	/**
	 * 
	 * 功能描述: 对账结果信息分页查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param qStlmDateStart
	 * @param qStlmDateEnd
	 * @param qMchtInfo
	 * @param qMchtOrg
	 * @param qInAcctNo
	 * @param qInAcctStat
	 * @param page
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public PageResult pageQuery(String qStlmDateStart, String qStlmDateEnd, String qMchtInfo, String qMchtOrg,
			String qInAcctNo, String qInAcctStat, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.report.rql.mchtInAcctRslt.queryList",
				RqlParam.map().set("qStlmDateStart", StringUtils.isBlank(qStlmDateStart) ? "" : qStlmDateStart)
						.set("qStlmDateEnd", StringUtils.isBlank(qStlmDateEnd) ? "" : qStlmDateEnd)
						.set("qMchtInfo", StringUtils.isBlank(qMchtInfo) ? ""
								: "%" + qMchtInfo + "%")
						.set("qInAcctNo", StringUtils.isBlank(qInAcctNo) ? ""
								: "%" + qInAcctNo + "%")
						.set("qMchtOrg", qMchtOrg)
						.set("qInAcctStat", StringUtils.isBlank(qInAcctStat) ? "" : qInAcctStat),
				page);
	}
	/**
	 * 
	 * 功能描述: 对账结果信息报表导出查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param qStlmDateStart
	 * @param qStlmDateEnd
	 * @param qMchtInfo
	 * @param qMchtOrg
	 * @param qInAcctNo
	 * @param qInAcctStat
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public List queryForExport(String qStlmDateStart, String qStlmDateEnd, String qMchtInfo, String qMchtOrg,
			String qInAcctNo, String qInAcctStat) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.report.rql.mchtInAcctRslt.queryList",
				RqlParam.map().set("qStlmDateStart", StringUtils.isBlank(qStlmDateStart) ? "" : qStlmDateStart)
						.set("qStlmDateEnd", StringUtils.isBlank(qStlmDateEnd) ? "" : qStlmDateEnd)
						.set("qMchtInfo", StringUtils.isBlank(qMchtInfo) ? ""
								: "%" + qMchtInfo + "%")
						.set("qInAcctNo", StringUtils.isBlank(qInAcctNo) ? ""
								: "%" + qInAcctNo + "%")
						.set("qMchtOrg", qMchtOrg)
						.set("qInAcctStat", StringUtils.isBlank(qInAcctStat) ? "" : qInAcctStat));
	}
}
