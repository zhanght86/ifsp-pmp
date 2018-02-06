package com.ruimin.ifs.pmp.txnQuery.process.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.txnQuery.process.bean.BatchJobRecord;

@SnowDoc(desc = "批量任务结果查询Service")
@Service
public class BatchJobRecordService extends SnowService {

	public static BatchJobRecordService getInstance() throws SnowException {
		return ContextUtil.getSingleService(BatchJobRecordService.class);
	}

	/**
	 * 根据查询条件 分页查询 批量任务执行结果列表
	 */
	public PageResult queryAll(Map<String, String> queryParamMap, Page page) {
		DBDao dao = DBDaos.newInstance();
		
		PageResult result= dao.selectList("com.ruimin.ifs.pmp.txnQuery.rql.batchJobRecord.queryList", queryParamMap, page);
		
		return result;
		
	}
	
}
