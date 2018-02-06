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

import java.util.List;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.po.TblDataDic;

/**
 * 〈数据词典配置Service〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author MJ
 */
@Service
public class DataDicEntryService extends SnowService {
	/**
	 * 功能:获取DataDicEntryService实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static DataDicEntryService getInstance() throws SnowException {
		return ContextUtil.getSingleService(DataDicEntryService.class);
	}

	/**
	 * 功能:查询所有数据字典数据
	 * 
	 * @param queryDataTypeNo
	 * @param queryDataNo
	 * @param queryDataName
	 * @param queryDataTypeName
	 * @param qUpDataNo
	 * @param page
	 * @return 数据库返回结果
	 * @throws SnowException
	 * 
	 */
	public PageResult queryList(String queryDataTypeNo, String queryDataNo, String queryDataName,
			String queryDataTypeName, String qUpDataNo, Page page) throws SnowException {
		return DBDaos.newInstance().selectList("com.ruimin.ifs.mng.rql.sysmng.queryDataDic",
				RqlParam.map().set("queryDataTypeNo", queryDataTypeNo == null ? "" : "%" + queryDataTypeNo + "%")
						.set("queryDataNo", queryDataNo == null ? "" : "%" + queryDataNo + "%")
						.set("queryDataName", queryDataName == null ? "" : "%" + queryDataName + "%")
						.set("queryDataTypeName", queryDataTypeName == null ? "" : "%" + queryDataTypeName + "%")
						.set("qUpDataNo", qUpDataNo == null ? "" : "%" + qUpDataNo + "%"),
				page);
	}

	/**
	 * 功能:新增数据字典数据
	 * 
	 * @param tblDataDic
	 * @throws SnowException
	 * 
	 */
	public void saveDataDicEntry(TblDataDic tblDataDic) throws SnowException {
		DBDaos.newInstance().insert(tblDataDic);
	}

	/**
	 * 功能:更新数据字典数据
	 * 
	 * @param tblDataDic
	 * @throws SnowException
	 * 
	 */
	public void updateDataDicEntry(TblDataDic tblDataDic) throws SnowException {
		DBDaos.newInstance().update(tblDataDic);
	}

	/**
	 * 功能:删除数据字典数据
	 * 
	 * @param tblDataDic
	 * @throws SnowException
	 * 
	 */
	public void deletDataDicEntry(TblDataDic tblDataDic) throws SnowException {
		DBDaos.newInstance().delete(tblDataDic);
	}

	/**
	 * 功能:通过Id删除数据字典数据
	 * 
	 * @param id
	 * @throws SnowException
	 * 
	 */
	public void deleteDataDicEntryById(String id) throws SnowException {
		DBDaos.newInstance().delete(TblDataDic.class, id);
	}

	/**
	 * 功能:查询数据字典部分数据
	 * 
	 * @param dataTypeNo
	 * @param upDataNo
	 * @param dateNo
	 * @return result TblDataDic部分属性值
	 * @throws SnowException
	 * 
	 */
	public TblDataDic query(String dataTypeNo, String upDataNo, String dataNo) throws SnowException {
		return (TblDataDic) DBDaos.newInstance().selectOne("com.ruimin.ifs.mng.rql.sysmng.queryDataDicByDataNo",
				RqlParam.map().set("dataTypeNo", dataTypeNo == null ? "" : dataTypeNo)
						.set("upDataNo", upDataNo == null ? "" : "%" + upDataNo + "%")
						.set("dataNo", dataNo == null ? "" : dataNo));
	}
	
	/**
	 * 根据字典类型查询Lym(add)
	 * @return
	 */
	public List<Object> queryByTypeNo(){
		
		return DBDaos.newInstance().selectList("com.ruimin.ifs.mng.rql.datadic.getDataDicByTypeNo",
				RqlParam.map().set("typeNo", "1933"));
	}
}
