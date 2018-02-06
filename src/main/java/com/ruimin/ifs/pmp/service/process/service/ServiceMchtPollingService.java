package com.ruimin.ifs.pmp.service.process.service;

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
import com.ruimin.ifs.pmp.service.process.bean.ServiceMchtPollingVo;
@Service
public class ServiceMchtPollingService extends SnowService {
	public static ServiceMchtPollingService getInstance() throws SnowException{
		return ContextUtil.getSingleService(ServiceMchtPollingService.class);
	}
	/**
	 * 商户巡检信息查询
	 * @param qserviceName
	 * @param qmchtName
	 * @param qpollingDate 
	 * @param qtermId 
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryMain(String qserviceName, String qmchtName, String qtermId, String qpollingDate, Page page) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return dao.selectListIn("com.ruimin.ifs.pmp.service.rql.serviceMchtPolling.queryMain", RqlParam.map()
				.set("qserviceName",StringUtils.isBlank(qserviceName) ? "" : "%"+qserviceName+"%")
				.set("qmchtName",StringUtils.isBlank(qmchtName) ? "" : "%"+qmchtName+"%")
				.set("qtermId",StringUtils.isBlank(qtermId) ? "" : "%"+qtermId+"%")
				.set("qpollingDate",StringUtils.isBlank(qpollingDate) ? "" : qpollingDate), page);
	}
	/**
	 * 查询商户巡检表中所有的商户巡检信息
	 * @return
	 * @throws SnowException
	 */
	public List<Object> selectAllServiceMchtPolling() throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.service.rql.serviceMchtPolling.selectAllServiceMchtPolling",RqlParam.map().set("",""));
	}
	/**
	 * 获取商户巡检最大序号
	 * @return
	 * @throws SnowException
	 */
	public String getMaxServiceId() throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return (String)dao.selectOne("com.ruimin.ifs.pmp.service.rql.serviceMchtPolling.getMaxServiceId");
	}
	/**
	 * 新增一条商户巡检基本信息
	 * @param vo
	 * @throws SnowException
	 */
	public void addServiceMchtPolling(ServiceMchtPollingVo vo) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.service.rql.serviceMchtPolling.addServiceMchtPolling",vo);
	}
}
