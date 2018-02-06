/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.accessTypeMng.comp 
 * FileName: AccessTypeMngAction.java
 * Author:   zrx
 * Date:     2016年7月8日 下午2:55:55
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx          2016年7月8日下午2:55:55                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.sysConf.comp;

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
import com.ruimin.ifs.pmp.payProdMng.process.service.PbsProdInfoService;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.sysConf.common.constants.AccessTypeConstants;
import com.ruimin.ifs.pmp.sysConf.process.bean.AccessTypeInfo;
import com.ruimin.ifs.pmp.sysConf.process.bean.RelAccessTypeTxnType;
import com.ruimin.ifs.pmp.sysConf.process.bean.TxnTypeInfo;
import com.ruimin.ifs.pmp.sysConf.process.service.AccessTypeMngService;
import com.ruimin.ifs.pmp.sysConf.process.service.RelAccessTypeTxnTypeService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月8日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */

@SnowDoc(desc = "接入方式管理操作Action")
@ActionResource
public class AccessTypeMngAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询接入方式信息")
	public PageResult queryAccessTypeInfo(QueryParamBean queryBean) throws SnowException {
		// 获取查询参数
		// 接入方式编号
		String qaccessTypeCode = queryBean.getParameter("qaccessTypeCode");
		// 接入方式名称
		String qaccessTypeName = queryBean.getParameter("qaccessTypeName");
		// 数据状态
		String qdataState = queryBean.getParameter("qdataState");
		// 分页查询接入方式信息
		return AccessTypeMngService.getInstance().queryList(qaccessTypeCode, qaccessTypeName, qdataState,
				queryBean.getPage());

	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增接入方式")
	public void addAccessTypeInfo(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateMap.get("accessTypeInfo");
		// 获取实体类
		AccessTypeInfo accessTypeInfo = new AccessTypeInfo();
		AccessTypeMngService accessTypeMngService = AccessTypeMngService.getInstance();
		// 查询最大序列号
		String maxSeq = accessTypeMngService.queryMaxSeq();
		String nextSeq;
		if (maxSeq != null) {
			int num = Integer.valueOf(maxSeq);
			nextSeq = StringUtil.leftPad(String.valueOf(num + 1), 2, "0");
		} else {
			nextSeq = AccessTypeConstants.ACCESS_TYPE_MIN_CODE;
		}

		// 新增接入方式相关操作信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 获取关联表最大序列号
		String maxSeq1 = queryMaxSeq();
		int relNo = Integer.parseInt(maxSeq1);
		String s = reqBean.getParameter("s");
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), accessTypeInfo);
			// 增加接入方式所对应的交易类型
			String[] ss = s.split(",");
			List<String> lists = Arrays.asList(ss);
			int size = lists.size();
			for (int i = 0; i < size; i++) {
				RelAccessTypeTxnType relAccessTypeTxnType = new RelAccessTypeTxnType();
				relAccessTypeTxnType.setRelNo(Integer.toString(relNo));
				relAccessTypeTxnType.setAccessTypeCode(nextSeq);
				relAccessTypeTxnType.setTxnTypeCode(ss[i]);
				relAccessTypeTxnType.setDataState(AccessTypeConstants.DATA_STATE_00);
				relAccessTypeTxnType.setCrtTlr(sessionUserInfo.getTlrno());
				relAccessTypeTxnType.setCrtDateTime(BaseUtil.getCurrentDateTime());
				relAccessTypeTxnType.setLastUpdTlr(sessionUserInfo.getTlrno());
				relAccessTypeTxnType.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
				// 向关联表中插入数据
				accessTypeMngService.insertRelAccessTypeTxnType1(relAccessTypeTxnType);
				relNo++;
			}
		}
		accessTypeInfo.setAccessTypeCode(nextSeq);

		// 设置记录状态为生效("00"-启用,"99"-停用)
		accessTypeInfo.setDataState(AccessTypeConstants.DATA_STATE_00);
		accessTypeInfo.setCrtTlr(sessionUserInfo.getTlrno());
		accessTypeInfo.setCrtDateTime(BaseUtil.getCurrentDateTime());

		// 调用添加方法
		accessTypeMngService.addAccessTypeInfo(accessTypeInfo);

		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"接入方式编号：id=" + accessTypeInfo.getAccessTypeCode() });
	}

	@SuppressWarnings("static-access")
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改接入方式信息")
	public void updateAccessTypeInfo(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateMap.get("accessTypeInfo");
		AccessTypeInfo accessTypeInfo = new AccessTypeInfo();
		AccessTypeMngService accessTypeMngService = AccessTypeMngService.getInstance();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), accessTypeInfo);
		}
		// 更新相关操作信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		accessTypeInfo.setLastUpdTlr(sessionUserInfo.getTlrno());
		accessTypeInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		// 调用修改方法，修改接入方式信息
		accessTypeMngService.updateAccessTypeInfo(accessTypeInfo);

		// 获取关联表最大序列号
		String maxSeq = queryMaxSeq();
		int relNo = Integer.parseInt(maxSeq);

		// 获取页面传递的分段信息
		UpdateRequestBean reqBean1 = updateMap.get("relAccessTypeTxnType");
		String accessTypeCode = reqBean1.getParameter("accessTypeCode");
		RelAccessTypeTxnType relAccessTypeTxnType = new RelAccessTypeTxnType();
		while (reqBean1.hasNext()) {
			DataObjectUtils.map2bean(reqBean1.next(), relAccessTypeTxnType);
			// 当本条记录未改动时，跳过本条
			if (reqBean1.getRecodeState() == reqBean1.NONE) {
				continue;
			}
			// 当本条记录状态为删除时
			if (reqBean1.getRecodeState() == reqBean1.DELETE) {
				RelAccessTypeTxnTypeService.getInstance().deleteRelAccessTxn(relAccessTypeTxnType);
			} else if (reqBean1.getRecodeState() == reqBean1.INSERT) {
				// 如果本条记录状态为新增时
				String txnTypeCode = relAccessTypeTxnType.getTxnTypeCode();
				List<Object> list = RelAccessTypeTxnTypeService.getInstance().queryByTxnTypeCode(accessTypeCode,
						txnTypeCode);
				if (list.size() != 0) {
					SnowExceptionUtil.throwErrorException("该交易类型已经添加过，请重新选择!");
				}
				relAccessTypeTxnType.setRelNo(Integer.toString(relNo));
				relAccessTypeTxnType.setAccessTypeCode(accessTypeCode);
				relAccessTypeTxnType.setDataState(AccessTypeConstants.DATA_STATE_00);
				relAccessTypeTxnType.setCrtTlr(sessionUserInfo.getTlrno());
				relAccessTypeTxnType.setCrtDateTime(BaseUtil.getCurrentDateTime());
				relAccessTypeTxnType.setLastUpdTlr(sessionUserInfo.getTlrno());
				relAccessTypeTxnType.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
				RelAccessTypeTxnTypeService.getInstance().addRelAccessTxn(relAccessTypeTxnType);
				relNo++;
			} else if (reqBean1.getRecodeState() == reqBean1.MODIFY) {
				String txnTypeCode = relAccessTypeTxnType.getTxnTypeCode();
				List<Object> list = RelAccessTypeTxnTypeService.getInstance().queryByTxnTypeCode(accessTypeCode,
						txnTypeCode);
				if (list.size() != 0) {
					SnowExceptionUtil.throwErrorException("该交易类型已经添加过，请重新选择!");
				}
				relAccessTypeTxnType.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
				relAccessTypeTxnType.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新日期时间
				// 更新接入方式与交易类型关联表数据
				RelAccessTypeTxnTypeService.getInstance().updateRelAccessTxn(relAccessTypeTxnType);
			}
		}

		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"接入方式编号：id=" + accessTypeInfo.getAccessTypeCode() });

	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "接入方式的启用/停用")
	public void deleteAccessTypeInfo(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateMap.get("accessTypeInfo");
		String dataState = reqBean.getParameter("dataState");
		AccessTypeMngService accessTypeMngService = AccessTypeMngService.getInstance();
		AccessTypeInfo accessTypeInfo = new AccessTypeInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), accessTypeInfo);
			RelAccessTypeTxnType rat = new RelAccessTypeTxnType();
			String accessTypeCode = accessTypeInfo.getAccessTypeCode();
			rat.setAccessTypeCode(accessTypeCode);
			rat.setDataState(dataState);
			accessTypeMngService.deleteRelAccessTypeTxnType(rat);
		}

		// 更新相关操作信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		accessTypeInfo.setLastUpdTlr(sessionUserInfo.getTlrno());
		accessTypeInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		accessTypeInfo.setDataState(dataState);

		// 调用删除方法
		accessTypeMngService.deleteAccessTypeInfo(accessTypeInfo);
		String msg = "";
		if (AccessTypeConstants.DATA_STATE_00.equals(dataState)) {
			msg = "启用接入方式，id=" + accessTypeInfo.getAccessTypeCode();
		} else {
			msg = "停用接入方式，id=" + accessTypeInfo.getAccessTypeCode();
		}
		// 打印日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });

	}

	@SnowDoc(desc = "查询关联表最大序列号")
	public String queryMaxSeq() throws SnowException {
		AccessTypeMngService accessTypeMngService = AccessTypeMngService.getInstance();
		String maxSeq = accessTypeMngService.queryMaxSeq1();
		String nextSeq;
		if (maxSeq != null) {
			int num = Integer.valueOf(maxSeq);
			nextSeq = String.valueOf(num + 1);
		} else {
			nextSeq = AccessTypeConstants.MIN_SEQ;
		}
		return nextSeq;
	}

	/**
	 * 交易类型查询
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "交易类型下拉查询")
	public List<TxnTypeInfo> querySelect(QueryParamBean queryBean) throws SnowException {
		return AccessTypeMngService.getInstance().querySelect();
	}

	/**
	 * 根据接入方式ID查询接入方式名称
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String getAccessTypeCodeName(FieldBean bean, String key, ServletRequest request)
			throws SnowException {
		StringBuffer strBuf = new StringBuffer();
		if (StringUtil.isEmpty(key)) {
			return strBuf.toString();
		}
		List<Object> prodList = PbsProdInfoService.getInstance().getAccessTypeCodeName(key);

		if (prodList == null || prodList.size() == 0) {
			return strBuf.toString();
		}
		for (Object prodObj : prodList) {
			AccessTypeInfo prod = (AccessTypeInfo) prodObj;
			strBuf.append(prod.getAccessTypeName()).append(",");
		}
		return strBuf.toString().substring(0, strBuf.toString().length() - 1);
	}

}
