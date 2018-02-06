package com.ruimin.ifs.mng.process.service;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;

@Service
public class CommonService extends SnowService {

	public static CommonService getInstance() throws SnowException {
		return ContextUtil.getSingleService(CommonService.class);
	}

}
