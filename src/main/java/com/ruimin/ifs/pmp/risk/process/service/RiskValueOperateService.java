package com.ruimin.ifs.pmp.risk.process.service;


import java.util.List;
import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.pmp.risk.process.bean.RiskValueOperateMapping;
@SnowDoc(desc = "阈值操作Service")
@Service
public class RiskValueOperateService extends SnowService  {
	public static RiskValueOperateService getInstance()throws SnowException {
		return ContextUtil.getSingleService(RiskValueOperateService.class);
	}

	/**
	 * 点击详情时查询风控配置
	 * @param riskId
	 * @param riskModelId 
	 * @return
	 * @throws SnowException
	 */
	public List<Object> queryAll(String riskId, String riskModelId) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.risk.rql.riskValueOperate.queryAll",RqlParam.map().set("riskId",riskId)
				.set("riskModelId", riskModelId));
	}
	/**
	 * 查询阀值
	 * @param valueId
	 * @param riskModelId
	 * @return
	 * @throws SnowException
	 */
	public RiskValueOperateMapping queryValue(String valueId, String riskModelId) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return (RiskValueOperateMapping)dao.selectOne("com.ruimin.ifs.pmp.risk.rql.riskValueOperate.queryValue", RqlParam.map().set("valueId", valueId).set("riskModelId", riskModelId));
	}
	
}


