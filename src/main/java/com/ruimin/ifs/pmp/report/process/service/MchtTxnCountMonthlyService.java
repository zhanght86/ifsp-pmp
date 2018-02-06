package com.ruimin.ifs.pmp.report.process.service;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;

import java.util.List;

@Service
@SnowDoc(desc = "商户交易统计月报信息service")
public class MchtTxnCountMonthlyService extends SnowService {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static MchtTxnCountMonthlyService getInstance() throws SnowException {
		return ContextUtil.getSingleService(MchtTxnCountMonthlyService.class);
	}

	/**
	 * 
	 * 功能描述: 商户交易统计月报信息分页查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param qStleMonthStart
	 * @param qStleMonthEnd
	 * @param qMchtInfo
	 * @param qMchtOrg
	 * @param page
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public PageResult pageQuery(String qStleMonthStart, String qStleMonthEnd, String qMchtInfo, String qMchtOrg,Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.report.rql.mchtTxnCountMonthly.queryList",
				RqlParam.map().set("qStleMonthStart", StringUtils.isBlank(qStleMonthStart) ? "" : qStleMonthStart)
						.set("qStleMonthEnd", StringUtils.isBlank(qStleMonthEnd) ? "" : qStleMonthEnd)
						.set("qMchtInfo", StringUtils.isBlank(qMchtInfo) ? ""
								: "%" + qMchtInfo + "%")
						.set("qMchtOrg", qMchtOrg),page);
	}
	 /**
     * 报表下载查询
     * @param qmchtNo
     * @param qcountStartDate
     * @param qcountEndDate
     * @param qriskRuleType
     * @param currentBrCode
     * @return
     */
    public List<Object> doutAgent(String qStleMonthStart, String qStleMonthEnd, String qMchtInfo, String qMchtOrg)  throws SnowException{
        /** 查询操作 **/
        DBDao dao = DBDaos.newInstance();
        List<Object> detail = dao.selectList("com.ruimin.ifs.pmp.report.rql.mchtTxnCountMonthly.queryList",
                RqlParam.map().set("qStleMonthStart", StringUtils.isBlank(qStleMonthStart) ? "" : qStleMonthStart)
                .set("qStleMonthEnd", StringUtils.isBlank(qStleMonthEnd) ? "" : qStleMonthEnd)
                .set("qMchtInfo", StringUtils.isBlank(qMchtInfo) ? ""
                        : "%" + qMchtInfo + "%")
                .set("qMchtOrg", qMchtOrg));
        if (detail.size() == 0) {
            SnowExceptionUtil.throwErrorException("数据记录不存在，无法生成报表");
        }
        return detail;
    }
}
