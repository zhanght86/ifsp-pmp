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
@SnowDoc(desc = "通道清算汇总信息service")
public class PagyClearingRsltService extends SnowService {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static PagyClearingRsltService getInstance() throws SnowException {
		return ContextUtil.getSingleService(PagyClearingRsltService.class);
	}

	/**
	 * 
	 * 功能描述: 通道清算汇总信息分页查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param qStlmDateStart
	 * @param qStlmDateEnd
	 * @param qPagyNo
	 * @param page
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public PageResult pageQuery(String qStlmDateStart, String qStlmDateEnd, String qPagyNo, Page page)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.report.rql.pagyClearingRslt.queryList",
				RqlParam.map().set("qStlmDateStart", StringUtils.isBlank(qStlmDateStart) ? "" : qStlmDateStart)
						.set("qStlmDateEnd", StringUtils.isBlank(qStlmDateEnd) ? "" : qStlmDateEnd)
						.set("qPagyNo", StringUtils.isBlank(qPagyNo) ? "" : qPagyNo),
				page);
	}
	/**
	 * 
	 * 功能描述: 通道清算汇总信息全量查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param qStlmDateStart
	 * @param qStlmDateEnd
	 * @param qPagyNo
	 * @param page
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public List queryForExport(String qStlmDateStart, String qStlmDateEnd, String qPagyNo)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.report.rql.pagyClearingRslt.queryList",
				RqlParam.map().set("qStlmDateStart", StringUtils.isBlank(qStlmDateStart) ? "" : qStlmDateStart)
						.set("qStlmDateEnd", StringUtils.isBlank(qStlmDateEnd) ? "" : qStlmDateEnd)
						.set("qPagyNo", StringUtils.isBlank(qPagyNo) ? "" : qPagyNo));
	}
}
