/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.comp 
 * FileName: GalRoutMngAction.java
 * Author:   chenqilei
 * Date:     2016年8月2日 上午11:37:34
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年8月2日上午11:37:34                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.comp;

import com.ruimin.ifs.core.annotation.Action;
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
import com.ruimin.ifs.pmp.chnlMng.common.constants.PagyRoutMngConstants;
import com.ruimin.ifs.pmp.chnlMng.process.bean.GalBasicInfo;
import com.ruimin.ifs.pmp.chnlMng.process.bean.GalRoutMng;
import com.ruimin.ifs.pmp.chnlMng.process.bean.SaveDataUser;
import com.ruimin.ifs.pmp.chnlMng.process.service.GalRoutMngService;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月2日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
public class GalRoutMngAction {
	/**
	 * 通道路由的查询根据传入的条件查询
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@SnowDoc(desc = "通道路由的查询功能")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		String routeId = queryBean.getParameter("qrouteId");
		String payTxnTypeId = queryBean.getParameter("qpayTxnTypeId");
		String acctTypeNo = queryBean.getParameter("qacctTypeNo");
		String routeStat = queryBean.getParameter("qrouteStat");
		String chnlId = queryBean.getParameter("qchnlId");
		return GalRoutMngService.getInstance().queryAll(routeId, payTxnTypeId, acctTypeNo, routeStat, chnlId,
				queryBean.getPage());
	}

	/**
	 * 通道路由的新增
	 * 
	 * @param updateBean
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "通道路由的新增")
	public void addRoutMng(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("galRoutMng");
		// 创建实体类
		GalRoutMng rout = new GalRoutMng();
		// 查询最大序列号
		String maxSeq = GalRoutMngService.getInstance().queryMaxSeq();
		String nextSeq;
		if (maxSeq != null) {
			int num = Integer.valueOf(maxSeq);
			nextSeq = StringUtil.leftPad(String.valueOf(num + 1), 2, "0");
		} else {
			nextSeq = "1001";
		}
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), rout);
			// 交易类型编号
			String payTxnTypeId = reqBean.getParameter("payTxnTypeId");
			// 账户类型编号
			String acctTypeNo = reqBean.getParameter("acctTypeNo");
			// 渠道编号
			String chnlId = reqBean.getParameter("chnlId");
			// 根据账户类型+交易类型+渠道编号查询重复性
			GalRoutMng br = GalRoutMngService.getInstance().queryExist(payTxnTypeId, acctTypeNo, chnlId);
			if (br != null) {
				SnowExceptionUtil.throwErrorException("交易类型名称+账户类型名称+接入渠道已存在");
			}
			// 策略名称编号
			String ttsCode = reqBean.getParameter("ttsCode");
			// 通道路由状态
			String routeStat = reqBean.getParameter("routeStat");
			if (PagyRoutMngConstants.ROUTE_STAT_ON.equals(routeStat)) {
				rout.setOpenDate(BaseUtil.getCurrentDate());// 当前时间，8位
			}
			rout.setPayTxnTypeId(payTxnTypeId);
			rout.setAcctTypeNo(acctTypeNo);
			rout.setTtsCode(ttsCode);
			rout.setChnlId(chnlId);
			rout.setRouteStat(routeStat);
			;
		}
		rout.setRouteId(nextSeq);
		// 更新操作员
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		rout.setCrtTlr(sessionUserInfo.getTlrno());
		rout.setCrtDateTime(BaseUtil.getCurrentDateTime());
		rout.setLastUpdTlr(sessionUserInfo.getTlrno());
		rout.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		GalRoutMngService.getInstance().addRoutMng(rout);
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[通道路由管理]--新增 : 路由编号[routeId]=" + rout.getRouteId() });
	}

	/**
	 * 通道路由的修改功能
	 * 
	 * @param updateBean
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "通道路由的修改功能")
	public void updateRoutMng(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("galRoutMng");
		// 创建实体类
		GalRoutMng rout = new GalRoutMng();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), rout);
		}
		// 更新操作员
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		// 交易类型编号
		String payTxnTypeId = reqBean.getParameter("payTxnTypeId");
		// 账户类型编号
		String acctTypeNo = reqBean.getParameter("acctTypeNo");
		// 账户类型编号
		String chnlId = reqBean.getParameter("chnlId");
		// 路由编号
		String routeId = reqBean.getParameter("routeId");
		// 策略名称编号
		String ttsCode = reqBean.getParameter("ttsCode");
		// 根据账户类型+交易类型+渠道编号查询重复性
		GalRoutMng br = GalRoutMngService.getInstance().queryExist(payTxnTypeId, acctTypeNo, chnlId);
		if (br != null) {
			// 如果查询的这条是数据的路由Id跟原来的路由Id相同的话那么就是原本的这条数据
			// 这样就对他进行Update操作
//			if (br.getRouteId().equals(routeId)) {
//				rout.setPayTxnTypeId(payTxnTypeId);
//				rout.setAcctTypeNo(acctTypeNo);
//				rout.setTtsCode(ttsCode);
//				rout.setChnlId(chnlId);
//				rout.setCrtTlr(sessionUserInfo.getTlrno());
//				rout.setCrtDateTime(BaseUtil.getCurrentDateTime());
//				rout.setLastUpdTlr(sessionUserInfo.getTlrno());
//				rout.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
//				GalRoutMngService.getInstance().updateRoutMng(rout);
//				return;
//			    }
//		} else {
		    SnowExceptionUtil.throwErrorException("交易类型名称+账户类型名称+接入渠道已存在");
		}
		rout.setPayTxnTypeId(payTxnTypeId);
		rout.setAcctTypeNo(acctTypeNo);
		rout.setTtsCode(ttsCode);
		rout.setChnlId(chnlId);
		rout.setCrtTlr(sessionUserInfo.getTlrno());
		rout.setCrtDateTime(BaseUtil.getCurrentDateTime());
		rout.setLastUpdTlr(sessionUserInfo.getTlrno());
		rout.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		GalRoutMngService.getInstance().updateRoutMng(rout);
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[通道路由管理]--修改 : 路由编号[routeId]=" + rout.getRouteId() });
	}

	/**
	 * 通道路由状态的修改
	 * 
	 * @param updateBean
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "通道路由状态的修改")
	public void updateRoutMngStatus(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("galRoutMng");
		// 获取状态

		GalRoutMng rout = new GalRoutMng();
		String routeStat = reqBean.getParameter("routeStat");
		String routeId = reqBean.getParameter("routeId");
		// String openDate = reqBean.getParameter("openDate");
		rout.setRouteId(routeId);
		if (PagyRoutMngConstants.ROUTE_STAT_ON.equals(routeStat)) {
			rout.setRouteStat("99");
			// 条用修改状态的方法
			GalRoutMngService.getInstance().updateStatus(rout);
		} else if (PagyRoutMngConstants.ROUTE_STAT_LAY_UP.equals(routeStat)) {
			// 如果是首次 99 变为 00；则添加启用时间，根据通道编号查询开通时间
			// 如果为空，则是首次
			GalRoutMng checkTime = GalRoutMngService.getInstance().selectOpenTime(routeId);
			String openDate = checkTime.getOpenDate();
			if (openDate == null || openDate.length() == 0) {
				rout.setRouteStat("00");
				rout.setOpenDate(BaseUtil.getCurrentDate());// 当前时间，8位
				// 通道路由状态的修改 修改通道路由状态为'00' 并设置首次开通时间
				GalRoutMngService.getInstance().updateRoutMngStatus(rout);
			} else {
				rout.setRouteStat("00");
				// 通道路由状态的修改 修改通道路由状态为'00'
				GalRoutMngService.getInstance().updateStatus(rout);
			}
		} else {// 说明之前为停用，即状态为 02
				// 其实设置为启用状态
			rout.setRouteStat("00");
			rout.setOpenDate(BaseUtil.getCurrentDate());// 当前时间，8位
			// 通道路由状态的修改 修改通道路由状态为'00' 并设置首次开通时间
			GalRoutMngService.getInstance().updateRoutMngStatus(rout);
		}
		// 更新相关操作信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";
		if (PagyRoutMngConstants.ROUTE_STAT_ON.equals(routeStat)) {
			msg = "启用通道路由,id=" + rout.getTtsCode();
		}
		if (PagyRoutMngConstants.ROUTE_STAT_NO_START.equals(routeStat)) {
			msg = "未启用通道路由,id=" + rout.getTtsCode();
		}
		if (PagyRoutMngConstants.ROUTE_STAT_LAY_UP.equals(routeStat)) {
			msg = "暂停启用通道路由,id=" + rout.getTtsCode();
		}
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[通道路由管理]--修改状态 : 路由编号[routeId]=" + rout.getRouteId() });
	}

	/**
	 * 跟据交易类型+账户类型+策略类型 查找有效的通道名称 显示在新增,修改,详情的表格页面
	 */
	public PageResult queryThreeTerm(QueryParamBean queryBean) throws SnowException {
		String payTxnTypeId = queryBean.getParameter("qpayTxnTypeId");
		String acctTypeNo = queryBean.getParameter("qacctTypeNo");
		String ttsCode = queryBean.getParameter("qttsCode");
		GalBasicInfo basic = new GalBasicInfo();
		PageResult result = null;
		if ((!"".equals(payTxnTypeId) && payTxnTypeId != null) && (!"".equals(acctTypeNo) && acctTypeNo != null)
				&& (!"".equals(ttsCode) && ttsCode != null)) {
			if (PagyRoutMngConstants.ROUTE_CHOOSE_ALL.equals(acctTypeNo)
					|| PagyRoutMngConstants.ROUTE_CHOOSE_SELF_DEBIT_CARD.equals(acctTypeNo)
					|| PagyRoutMngConstants.ROUTE_CHOOSE_SELF_CREDIT_CARD.equals(acctTypeNo)
					|| PagyRoutMngConstants.ROUTE_CHOOSE_OTHERS_DEBIT_CARD.equals(acctTypeNo)
					|| PagyRoutMngConstants.ROUTE_CHOOSE_OTHERS_CREDIT_CARD.equals(acctTypeNo)) {
				// 在路由是 A000,A001,A002,A003,A004的下
				// 找账户是A000,A001,A002,A003,A004的+交易类型+策略类型 下的通道
				result = GalRoutMngService.getInstance().queryThreeTerm(payTxnTypeId, acctTypeNo, ttsCode,
						queryBean.getPage());
				int count = result.getTotalCount();
				basic.setTtsCodeCount(String.valueOf(count));
				return result;
			} else if (PagyRoutMngConstants.ROUTE_CHOOSE_ALI_PAY.equals(acctTypeNo)) {
				// 在路由是 A005的账户下 找账户是A005的+交易类型+策略类型 下的通道
				result = GalRoutMngService.getInstance().queryAliply(payTxnTypeId, acctTypeNo, ttsCode,
						queryBean.getPage());
				int count = result.getTotalCount();
				basic.setTtsCodeCount(String.valueOf(count));
				return result;
			} else if (PagyRoutMngConstants.ROUTE_CHOOSE_WECHAT.equals(acctTypeNo)) {
				// 在路由是 A006的账户下 找账户是A006的+交易类型+策略类型 下的通道
				result = GalRoutMngService.getInstance().queryWeChat(payTxnTypeId, acctTypeNo, ttsCode,
						queryBean.getPage());
				int count = result.getTotalCount();
				basic.setTtsCodeCount(String.valueOf(count));
				return result;
			} else {
				// 如果不是这几中类型的路由那么就返回空的通道
				return GalRoutMngService.getInstance().queryThree(payTxnTypeId, acctTypeNo, ttsCode,
						queryBean.getPage());
			}
		}
		return GalRoutMngService.getInstance().queryThree(payTxnTypeId, acctTypeNo, ttsCode, queryBean.getPage());
	}
}
