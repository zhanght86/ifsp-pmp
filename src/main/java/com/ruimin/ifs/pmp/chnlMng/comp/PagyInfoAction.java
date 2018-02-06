/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.comp 
 * FileName: PagyInfoAction.java
 * Author:   zqy
 * Date:     2016年7月27日 上午10:39:09
 * Description: 通道信息管理     
 * History: 
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zqy           2016年7月27日上午10:39:09                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.comp;

import java.util.List;

import javax.servlet.ServletRequest;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.chnlMng.process.service.ChannelInfoService;
import com.ruimin.ifs.pmp.chnlMng.process.service.PagyInfoService;
import com.ruimin.ifs.pmp.mchtMng.process.bean.RateBaseInfo;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

/**
 * 名称：通道信息管理 功能：通道信息管理 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月27日 <br>
 * 作者：zqy <br>
 * 说明：<br>
 */
@SnowDoc(desc = "通道信息管理操作Action")
@ActionResource
public class PagyInfoAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询通道信息")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		// 获取查询参数
		String pagyNo = queryBean.getParameter("qpagyNo");// 通道编号
		String pagyName = queryBean.getParameter("qpagyName");// 通道名称
		    if(pagyName!=null&&pagyName.length()!=0){
		        //由于通道编号与数据字典不匹配所以做如下调整(数据字典303;通道编号504)
		        if("303".equals(pagyName)) pagyName="504";
		        if (pagyNo==null||pagyNo.length()==0) {   
	                pagyNo=pagyName;
	                pagyName=null;
		        }else {
                    if ((pagyName).contains(pagyNo)) {
                        pagyName=null;
                    }else {
                        pagyNo = "000";
                        pagyName = "000";
                    }
                }
		    }

		// 获取查询类型判断标识
		String queryType = queryBean.getParameter("queryType");

		// 分页查询通道信息
		return PagyInfoService.getInstance().queryList(pagyNo, pagyName, queryType, queryBean.getPage());

	}

	/**
	 * 获取通道名称（支持多个）
	 * 
	 * @param bean
	 * @param key
	 *            单个通道编号或多个以“,”分割的通道编号
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String getPagyNames(FieldBean bean, String key, ServletRequest request) throws SnowException {
		if (StringUtil.isBlank(key)) {
			return key;
		}
		List<Object> pagyNameList = PagyInfoService.getInstance().queryPagyNames(key);

		if (pagyNameList == null || pagyNameList.size() == 0) {
			return key;
		}
		String pagyNames = pagyNameList.toString().replace(" ", "").replace("[", "").replace("]", "");
		return pagyNames;
	}
}
