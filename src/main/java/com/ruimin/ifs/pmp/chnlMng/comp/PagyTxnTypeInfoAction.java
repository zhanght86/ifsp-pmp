/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.chnlMng.comp 
 * FileName: ChannelInfoAction.java
 * Author:   zrx
 * Date:     2016年7月21日 上午10:39:09
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月21日上午10:39:09                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.comp;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagyTxnTypeInfoVO;
import com.ruimin.ifs.pmp.chnlMng.process.service.PagyTxnTypeInfoService;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月21日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
@SnowDoc(desc = "接入支付通道交易类型Action")
@ActionResource
public class PagyTxnTypeInfoAction extends SnowAction {
	/**
	 * 分页查询接入支付通道交易类型列表信息
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询接入支付通道交易类型列表")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		// 获取查询参数
		String qpayTxnTypeId = queryBean.getParameter("qpayTxnTypeId");
		String qpayTxnTypeName = queryBean.getParameter("qpayTxnTypeName");
		String qpayTxnTypeDesc = queryBean.getParameter("qpayTxnTypeDesc");
		// 分页查询接入支付通道交易类型列表信息
		return PagyTxnTypeInfoService.getInstance().queryAll(qpayTxnTypeId, qpayTxnTypeName, qpayTxnTypeDesc,
				queryBean.getPage());

	}

	/**
	 * 接入支付通道交易新增
	 * 
	 * @param updateBean
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增交易")
	public void addType(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("pagyTxnTypeInfo");
		// 创建实体类对象
		PagyTxnTypeInfoVO pagy = new PagyTxnTypeInfoVO();
		String payTxnTypeId = reqBean.getParameter("payTxnTypeId");
		// 遍历页面的数据，放到实体类中
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), pagy);
		}
		// 查询最大序列号
		/*
		 * String reqMax = PagyTxnTypeInfoService.getInstance().selectReqMax();
		 * String reqNext; if(reqMax!=null){ int num = Integer.valueOf(reqMax);
		 * reqNext = StringUtil.leftPad(String.valueOf(num+1), 2,"0"); }else{
		 * reqNext="001"; }
		 */
		// 查询交易类型编号是否存在
		int count = PagyTxnTypeInfoService.getInstance().queryPayTxnTypeId(payTxnTypeId);
		if (count > 0) {
			SnowExceptionUtil.throwErrorException("交易类型编号已存在,不允许重复！！");
		}
		// 创建操作员
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		pagy.setCrtTlr(sessionUserInfo.getTlrno());
		pagy.setCrtDateTime(BaseUtil.getCurrentDateTime());
		// 调用新增的方法
		PagyTxnTypeInfoService.getInstance().addType(pagy);
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[接入支付通道交易]--新增 : 交易类型编号[payTxnTypeId]=" + payTxnTypeId });
	}

	/**
	 * 接入支付通道交易修改
	 * 
	 * @param updateBean
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改交易")
	public void updateType(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("pagyTxnTypeInfo");
		// 创建操作员
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 创建实体类对象
		PagyTxnTypeInfoVO pagy = new PagyTxnTypeInfoVO();
		// 遍历页面的数据，放到实体类中
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), pagy);
		}
		String payTxnTypeId = reqBean.getParameter("payTxnTypeId");
		String payTxnTypeName = reqBean.getParameter("payTxnTypeName");
		String payTxnTypeDesc = reqBean.getParameter("payTxnTypeDesc");
		String crtTlr = sessionUserInfo.getTlrno();
		String crtDateTime = BaseUtil.getCurrentDateTime();
		String lastUpdTlr = sessionUserInfo.getTlrno();
		String lastCrtDateTime = BaseUtil.getCurrentDateTime();
		// 调用修改的方法
		PagyTxnTypeInfoService.getInstance().updateType(payTxnTypeId, payTxnTypeName, payTxnTypeDesc, crtTlr,
				crtDateTime, lastUpdTlr, lastCrtDateTime);
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[接入支付通道交易]--修改: 交易类型编号[payTxnTypeId]=" + pagy.getPayTxnTypeId() });
	}

	/**
	 * 获取接入支付通道交易类型名称（支持多个）
	 * 
	 * @param bean
	 * @param key
	 *            单个类型编号或多个以“,”分割的类型编号
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String getPagyTxnTypeNames(FieldBean bean, String key, ServletRequest request) throws SnowException {
		if (StringUtil.isBlank(key)) {
			return key;
		}
		List<Object> pagyTxnTypeNameList = PagyTxnTypeInfoService.getInstance().getPagyTxnTypeNames(key);
		if (null == pagyTxnTypeNameList || pagyTxnTypeNameList.size() == 0) {
			return key;
		}
		String pagyTxnTypeNames = pagyTxnTypeNameList.toString().replace(" ", "").replace("[", "").replace("]", "");
		return pagyTxnTypeNames;
	}

	@Action
	@SnowDoc(desc = "查询支付通道交易类型，下拉选，渠道权限使用")
	public PageResult queryTxnTpyeInfo(QueryParamBean queryBean) throws SnowException {
		return PagyTxnTypeInfoService.getInstance().queryTxnTpyeInfo(queryBean.getPage());
	}

	/**
	 * 查询接入支付通道交易类型表,获取交易的名字
	 * 
	 * @param bean
	 * @param key
	 *            父级交易编号
	 * @param request
	 * @return 交易类型名称
	 * @throws SnowException
	 */
	public static String getPayTxnTypeIdName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		if (StringUtil.isBlank(key)) {
			return key;
		}
		List<Object> chnlNameList = PagyTxnTypeInfoService.getInstance().getPayTxnTypeIdName(key);
		if (null == chnlNameList || chnlNameList.size() == 0) {
			return key;
		}
		String chnlNames = chnlNameList.toString().replace(" ", "").replace("[", "").replace("]", "");
		return chnlNames;
	}
}
