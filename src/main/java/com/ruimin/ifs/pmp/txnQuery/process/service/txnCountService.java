package com.ruimin.ifs.pmp.txnQuery.process.service;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.framework.process.query.Page;

import java.util.Map;

@SnowDoc(desc = "支付业务交易统计Service")
@Service
public class txnCountService extends SnowService {
	public static txnCountService getInstance() throws SnowException {
		return ContextUtil.getSingleService(txnCountService.class);
	}

	/**
	 * 根据查询条件 分页查询 支付业务交易统计数据
	 */
	public PageResult queryAll(Map<String, String> queryParamMap, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.txnQuery.rql.txnCount.queryList", queryParamMap, page);
	}

}
