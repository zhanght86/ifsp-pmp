package com.ruimin.ifs.mng.process.service;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;

@Service
public class UserService extends SnowService {
	public static UserService getInstance() throws SnowException {
		return ContextUtil.getSingleService(UserService.class);
	}

}
