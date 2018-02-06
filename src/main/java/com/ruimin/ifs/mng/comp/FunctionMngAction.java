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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.core.bean.TreeNode;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.mng.process.service.FunctionService;
import com.ruimin.ifs.po.TblFunction;

/**
 * 〈菜单管理Action〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author MJ
 */
@SnowDoc(desc = "权限管理")
@ActionResource
public class FunctionMngAction extends SnowAction {
	/**
	 * 功能：查询
	 * 
	 * @param queryBean
	 * @return list
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询")
	public List<TreeNode> queryAll(QueryParamBean queryBean) throws SnowException {
		List<TreeNode> list = new ArrayList<TreeNode>();
		FunctionService service = FunctionService.getInstance();
		List<TblFunction> functions = service.listAll();
		for (TblFunction function : functions) {
			TreeNode node = new TreeNode();
			node.setAttributes(function);
			node.setIconCls(function.getIconCls());
			node.setText(function.getFuncname());
			node.setId(function.getFuncid());
			node.setPid(function.getLastdirectory());
			if (StringUtils.isBlank(function.getLastdirectory())) {
			}
			list.add(node);
		}
		return list;

	}

	/**
	 * 功能：保存
	 * 
	 * @param updateMap
	 * @return
	 * @throws SnowException
	 */
	@SnowDoc(desc = "保存")
	public Map<String, String> saveOrUpdate(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("FunctionMng");
		TblFunction tblFunction = new TblFunction();
		if (reqBean.hasNext()) {
			Map<String, String> mapdata = reqBean.next();
			DataObjectUtils.map2bean(mapdata, tblFunction);
			tblFunction.setIsdirectory("2".equals(tblFunction.getFuncType()) ? 1 : 0);
			tblFunction.setIconCls(mapdata.get("_icon"));
			tblFunction.setFuncname(mapdata.get("_text"));

			FunctionService service = FunctionService.getInstance();
			SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
			if (StringUtils.isBlank(tblFunction.getFuncid())) {
				service.insert(tblFunction);
				sessionUserInfo.addBizLog("update.log",
						new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
								"菜单权限添加:id=" + tblFunction.getFuncid() + ", name=" + tblFunction.getFuncname() });
			} else {
				service.update(tblFunction);
				sessionUserInfo.addBizLog("update.log",
						new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
								"菜单权限修改:id=" + tblFunction.getFuncid() + ", name=" + tblFunction.getFuncname() });
			}
		}

		Map<String, String> result = new HashMap<String, String>();
		result.put("funcid", tblFunction.getFuncid());
		return result;
	}

	/**
	 * 功能：删除
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "删除")
	public void delFunc(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("FunctionMng");
		if (reqBean.hasNext()) {
			Map<String, String> mapdata = reqBean.next();
			String funcid = mapdata.get("funcid");
			String funcname = mapdata.get("funcname");

			FunctionService service = FunctionService.getInstance();
			service.delete(funcid);
			SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
			sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
					sessionUserInfo.getBrno(), "菜单权限删除:id=" + funcid + ", name=" + funcname });
		}

	}

}
