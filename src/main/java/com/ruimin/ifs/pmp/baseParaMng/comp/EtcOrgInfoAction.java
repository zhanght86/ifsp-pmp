/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.baseParaMng.comp 
 * FileName: OpenAcctOrgan.java
 * Author:   chenqilei
 * Date:     2016年7月19日 上午10:12:35
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月19日上午10:12:35                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.baseParaMng.comp;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.baseParaMng.process.bean.OpenAcctOrgan;
import com.ruimin.ifs.pmp.baseParaMng.process.service.EtcOrgInfoService;
import com.ruimin.ifs.pmp.baseParaMng.process.service.OpenAcctOrganService;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtContractConstants;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月19日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
@ActionResource
public class EtcOrgInfoAction {
	/**
	 * 查询证通机构信息
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action()
	@SnowDoc(desc = "查询证通机构信息")
	public PageResult pageQuery(QueryParamBean queryBean) throws SnowException {
		// 获取机构编号
		String qPtyCd = queryBean.getParameter("qPtyCd");
		// 获取机构名称
		String qPtyNm = queryBean.getParameter("qPtyNm");
		return EtcOrgInfoService.getInstance().queryList(qPtyCd, qPtyNm, queryBean.getPage());
	}

	/**
	 * 根据机构编号查找机构名
	 * 
	 * @param bean
	 * @param key
	 *            开户机构编号
	 * @param request
	 * @return 开户机构名称
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "根据机构编号查找机构名")
	public static String getSetlAcctInstituteName(FieldBean bean, String key, ServletRequest request)
			throws SnowException {
		if (StringUtil.isBlank(key)) {
			return key;
		}
		String name = EtcOrgInfoService.getInstance().getSetlAcctInstituteName(key);
		if (StringUtil.isBlank(name)) {
			return key;
		}
		return name;
	}

}
