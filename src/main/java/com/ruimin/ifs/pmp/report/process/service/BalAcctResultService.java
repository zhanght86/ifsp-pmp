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
@SnowDoc(desc = "对账结果信息service")
public class BalAcctResultService extends SnowService {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static BalAcctResultService getInstance() throws SnowException {
		return ContextUtil.getSingleService(BalAcctResultService.class);
	}

	/**
	 * 
	 * 功能描述: 对账差错信息分页查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param qStlmDateStart
	 * @param qStlmDateEnd
	 * @param qMchtInfo
	 * @param qMchtOrg
	 * @param qTxnType
	 * @param qChkStat
	 * @param qPagyNo
	 * @param qThirdSsn
	 * @param qChlTxnSsn
	 * @param qChlTxnSsn2 
	 * @param page
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public PageResult pageQuery(String qChlMchtNo,String qStlmDateStart, String qStlmDateEnd, String qMchtInfo, String qMchtOrg,
			String qTxnType, String qChkStat, String qPagyNo, String qThirdSsn, String qChlTxnSsn, Page page)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.report.rql.balAcctResult.queryList",
				RqlParam.map().set("qStlmDateStart", StringUtils.isBlank(qStlmDateStart) ? "" : qStlmDateStart)
						.set("qStlmDateEnd", StringUtils.isBlank(qStlmDateEnd) ? "" : qStlmDateEnd)
						.set("qMchtInfo", StringUtils.isBlank(qMchtInfo) ? ""
								: "%" + qMchtInfo + "%")
						.set("qTxnType", StringUtils.isBlank(qTxnType) ? "" : qTxnType)
						.set("qMchtOrg", qMchtOrg)
						.set("qChkStat", StringUtils.isBlank(qChkStat) ? "" : qChkStat)
						.set("qPagyNo", StringUtils.isBlank(qPagyNo) ? "" : qPagyNo)
						.set("qThirdSsn", StringUtils.isBlank(qThirdSsn) ? ""
								: "%" + qThirdSsn + "%")
						.set("qChlTxnSsn", StringUtils.isBlank(qChlTxnSsn) ? "" : "%" + qChlTxnSsn + "%")
						.set("qChlMchtNo", StringUtils.isBlank(qChlMchtNo) ? "" : "%" + qChlMchtNo + "%"),
				page);
	}
	/**
	 * 
	 * 功能描述: 对账差错信息报表下载查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param qStlmDateStart
	 * @param qStlmDateEnd
	 * @param qMchtInfo
	 * @param qMchtOrg
	 * @param qTxnType
	 * @param qChkStat
	 * @param qPagyNo
	 * @param qThirdSsn
	 * @param qChlTxnSsn
	 * @param qChlMchtNo 
	 * @param page
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public List queryForExport(String qStlmDateStart, String qStlmDateEnd, String qMchtInfo, String qMchtOrg,
			String qTxnType, String qChkStat, String qPagyNo, String qThirdSsn, String qChlTxnSsn, String qChlMchtNo)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.report.rql.balAcctResult.queryList",
				RqlParam.map().set("qStlmDateStart", StringUtils.isBlank(qStlmDateStart) ? "" : qStlmDateStart)
						.set("qStlmDateEnd", StringUtils.isBlank(qStlmDateEnd) ? "" : qStlmDateEnd)
						.set("qMchtInfo", StringUtils.isBlank(qMchtInfo) ? ""
								: "%" + qMchtInfo + "%")
						.set("qTxnType", StringUtils.isBlank(qTxnType) ? "" : qTxnType)
						.set("qMchtOrg", qMchtOrg)
						.set("qChkStat", StringUtils.isBlank(qChkStat) ? "" : qChkStat)
						.set("qPagyNo", StringUtils.isBlank(qPagyNo) ? "" : qPagyNo)
						.set("qThirdSsn", StringUtils.isBlank(qThirdSsn) ? ""
								: "%" + qThirdSsn + "%")
						.set("qChlTxnSsn", StringUtils.isBlank(qChlTxnSsn) ? "" : "%" + qChlTxnSsn + "%")
						.set("qChlMchtNo", StringUtils.isBlank(qChlMchtNo) ? "" : "%" + qChlMchtNo + "%"));
	}
}
