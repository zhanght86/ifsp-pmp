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
package com.ruimin.ifs.mng.comp;

import java.util.List;
import java.util.Map;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.mng.process.service.DataDicEntryService;
import com.ruimin.ifs.po.TblDataDic;

/**
 * 〈数据词典配置Action〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author MJ
 */
@SnowDoc(desc = "数据字典配置")
@ActionResource
public class DataDicEntryAction extends SnowAction {
	/**
	 * 功能：查询
	 * 
	 * @param queryBean
	 * @return DataDicEntryService中queryList方法的查询返回结果
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		String qDataTypeNo = queryBean.getParameter("qDataTypeNo");
		String qDataTypeName = queryBean.getParameter("qDataTypeName");
		String qDataNo = queryBean.getParameter("qDataNo");
		String qDataName = queryBean.getParameter("qDataName");
		String qUpDataNo = queryBean.getParameter("qUpDataNo");
		return DataDicEntryService.getInstance().queryList(qDataTypeNo, qDataNo, qDataName, qDataTypeName, qUpDataNo,
				queryBean.getPage());
	}

	/**
	 * 功能：更新数据字典
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "数据字典修改")
	public void saveOrUpdate(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("DataDicEntry");
		TblDataDic tblDataDic = new TblDataDic();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tblDataDic);
		}
		DataDicEntryService.getInstance().updateDataDicEntry(tblDataDic);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"数据字典修改:id=" + tblDataDic.getId() });
	}

	/**
	 * 功能：插入数据字典
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "数据字典新增")
	public void saveOrUpdate1(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("DataDicEntry");
		TblDataDic tblDataDic = new TblDataDic();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tblDataDic);
		}
		tblDataDic.setId(ContextUtil.getUUID());
		DataDicEntryService.getInstance().saveDataDicEntry(tblDataDic);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"数据字典新增:id=" + tblDataDic.getId() });
	}

	/**
	 * 功能：删除数据字典
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "数据字典删除")
	public void delete(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("DataDicEntry");
		TblDataDic tblDataDic = new TblDataDic();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), tblDataDic);
		}
		DataDicEntryService.getInstance().deletDataDicEntry(tblDataDic);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"数据字典删除:id=" + tblDataDic.getId() });
	}


	/**
	 * 查询某个字典类型的数据信息---------Lym(add)
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	public List<Object> getDataDic() throws SnowException{
		return DataDicEntryService.getInstance().queryByTypeNo();
	}
}
