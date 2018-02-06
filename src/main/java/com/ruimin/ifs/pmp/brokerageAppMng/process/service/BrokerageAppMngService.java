package com.ruimin.ifs.pmp.brokerageAppMng.process.service;

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
import com.ruimin.ifs.pmp.brokerageAppMng.process.bean.BbsAppInfoVO;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

@Service
@SnowDoc(desc = "券商APP管理")
public class BrokerageAppMngService extends SnowService {
	public static BrokerageAppMngService getInstance() throws SnowException {
		return ContextUtil.getSingleService(BrokerageAppMngService.class);
	}

	/**
	 * 分页查询app列表
	 * 
	 * @param qAppId
	 *            appid 模糊匹配
	 * @param qAppName
	 *            app名称 模糊匹配
	 * @param qTraderName
	 *            券商名称 模糊匹配
	 * @param qOrgId
	 *            机构号 模糊匹配
	 * @param qStat
	 *            状态
	 */
	public PageResult pageQuery(String qAppId, String qAppName, String qTraderName, String qOrgId, String qStat,
			Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.brokerageAppMng.rql.brokerageAppMng.queryList",
				RqlParam.map().set("qAppId", StringUtil.isEmpty(qAppId) ? "" : "%" + qAppId + "%")
						.set("qAppName", StringUtil.isEmpty(qAppName) ? "" : "%" + qAppName + "%")
						.set("qTraderName", StringUtil.isEmpty(qTraderName) ? "" : "%" + qTraderName + "%")
						.set("qOrgId", StringUtil.isEmpty(qOrgId) ? "" : "%" + qOrgId + "%")
						.set("qStat", qStat == null ? "" : qStat),
				page);
	}

	public void add(BbsAppInfoVO appInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(appInfo);
	}

	public void update(BbsAppInfoVO appInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(appInfo);
	}

	public void changeStatus(String appid, String tlrno, String newStatus) {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.brokerageAppMng.rql.brokerageAppMng.changeStatus",
				RqlParam.map().set("appid", appid).set("tlrno", tlrno).set("newStatus", newStatus)
						.set("currentDateTime", BaseUtil.getCurrentDateTime()));
	}
	public String queryAppId(String qAppId){
	    DBDao dao = DBDaos.newInstance();
	    return (String) dao.selectOne("com.ruimin.ifs.pmp.brokerageAppMng.rql.brokerageAppMng.queryAppId", qAppId);
	}
}
