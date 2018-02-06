/**
 * 
 * Copyright (C), 2016-2016, 上海睿民互联网科技有限公司
 * FileName: AccountTypeService.java
 * Author:   Cheng
 * Date:     2016年7月14日下午4:34:18
 * Description: TODO
 * History: //修改记录
 * <author>      <time>                   <version>    <desc>
 * zhaodk      2016年7月14日下午4:34:18          0.1
 */
package com.ruimin.ifs.mng.process.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.constant.SnowErrorCode;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.po.TblFunction;

/**
 * 〈菜单管理Service〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author MJ
 */
@Service
public class FunctionService extends SnowService {

	/**
	 * 功能:获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static FunctionService getInstance() throws SnowException {
		return ContextUtil.getSingleService(FunctionService.class);
	}

	/**
	 * 功能:查询所有功能
	 * 
	 * @return result
	 * @throws SnowException
	 * 
	 */
	public List<TblFunction> listAll() throws SnowException {
		List<Object> list = DBDaos.newInstance().selectList("com.ruimin.ifs.mng.rql.sysmng.findAllFunctions");
		List<TblFunction> result = new ArrayList<TblFunction>();
		for (Object obj : list) {
			result.add((TblFunction) obj);
		}
		return result;
	}

	/**
	 * 功能:新增功能
	 * 
	 * @param tblFunction
	 * @throws SnowException
	 * 
	 */
	public void insert(TblFunction tblFunction) throws SnowException {
		String parentid = tblFunction.getLastdirectory();
		if ("".equals(parentid)) {
			parentid = null;
		}
		String maxid = (String) DBDaos.newInstance().selectOne("com.ruimin.ifs.mng.rql.sysmng.findFunctionMaxId",
				RqlParam.map().set("param", parentid));
		if (StringUtils.isBlank(maxid)) {
			maxid = parentid == null ? "1000" : parentid + StringUtils.leftPad("1", 3, "0");
		} else {
			if (StringUtils.isBlank(parentid)) {
				int lmaxid = 0;
				try {
					lmaxid = Integer.valueOf(maxid) + 1;
				} catch (Exception e) {
					getLogger().warn("funcid must be digits characters. ", e);
					lmaxid = 1000;
				}
				maxid = "" + lmaxid;
			} else {
				maxid = maxid.substring(parentid.length());
				int lmaxid = 0;
				try {
					lmaxid = Integer.valueOf(maxid) + 1;
				} catch (Exception e) {
					getLogger().warn("funcid must be digits characters. ", e);
					lmaxid = 1;
				}
				maxid = parentid + StringUtils.leftPad("" + lmaxid, 3, "0");
			}
		}
		tblFunction.setFuncid(maxid);
		DBDaos.newInstance().insert(tblFunction);

	}

	/**
	 * 功能:更新功能
	 * 
	 * @param tblFunction
	 * @throws SnowException
	 * 
	 */
	public void update(TblFunction tblFunction) throws SnowException {
		DBDaos.newInstance().update(tblFunction);
	}

	/**
	 * 功能:删除
	 * 
	 * @param funcid
	 * @throws SnowException
	 * 
	 */
	public void delete(String funcid) throws SnowException {
		Long haschild = (Long) DBDaos.newInstance().selectOne("com.ruimin.ifs.mng.rql.sysmng.checkFunctionHaschild",
				funcid);
		if (haschild > 0) {
			SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_WEB_0035);
		}
		Long hasusing = (Long) DBDaos.newInstance().selectOne("com.ruimin.ifs.mng.rql.sysmng.checkFunctionHasusing",
				funcid);
		if (hasusing > 0) {
			SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_WEB_0036);
		}
		DBDaos.newInstance().delete(TblFunction.class, funcid);
	}

}
