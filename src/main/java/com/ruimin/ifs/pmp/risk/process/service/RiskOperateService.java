package com.ruimin.ifs.pmp.risk.process.service;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.framework.process.query.Page;

@SnowDoc(desc = "风控操作Service")
@Service
public class RiskOperateService extends SnowService {
	public static RiskOperateService getInstance()throws SnowException {
		return ContextUtil.getSingleService(RiskOperateService.class);
	}

	public PageResult queryOperateVo(String actionBitmap, Page page) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		if("01".equals(actionBitmap)){
			return dao.selectList("com.ruimin.ifs.pmp.risk.rql.riskOperate.queryOperateVo1",page);
		}
		if("10,01".equals(actionBitmap) || "01,10".equals(actionBitmap) || "11".equals(actionBitmap)){
			return dao.selectList("com.ruimin.ifs.pmp.risk.rql.riskOperate.queryOperateVo2",page);
		}
		if("10".equals(actionBitmap)){
			return dao.selectList("com.ruimin.ifs.pmp.risk.rql.riskOperate.queryOperateVo3",page);
		}
		return null;
		
	}
}
