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

import org.apache.commons.lang.StringUtils;

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
import com.ruimin.ifs.pmp.chnlMng.common.constants.ChannelInfoConstants;
import com.ruimin.ifs.pmp.chnlMng.process.bean.ChannelInfo;
import com.ruimin.ifs.pmp.chnlMng.process.bean.ChannelResultInfo;
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagyChlTxnAcctRel;
import com.ruimin.ifs.pmp.chnlMng.process.service.ChannelInfoService;
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
@SnowDoc(desc = "渠道信息管理操作Action")
@ActionResource
public class ChannelInfoAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询渠道信息")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		// 获取查询参数
		String qchlId = queryBean.getParameter("qchlId");// 渠道编号
		String qchlName = queryBean.getParameter("qchlName");// 渠道名称
		String qchlStat = queryBean.getParameter("qchlStat");// 清算账号
		String qchlAccNo = queryBean.getParameter("qchlAccNo");// 渠道状态
		// 分页查询渠道信息
		return ChannelInfoService.getInstance().queryList(qchlId, qchlName, qchlStat, qchlAccNo, queryBean.getPage());

	}

	@SnowDoc(desc = "新增渠道信息")
	@Action(propagation = Propagation.REQUIRED)
	public void add(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取页面传递的渠道基本信息
		UpdateRequestBean reqBean = updateMap.get("channelInfo");
		ChannelInfo channelInfo = new ChannelInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), channelInfo);
		}
		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 新增时，时间格式的转换
		String chlSetlTm = channelInfo.getChlSetlTm();
		chlSetlTm = chlSetlTm.replace(":", "");
		// 封装渠道补充信息
		String maxSeq = queryMaxSeq();
		channelInfo.setChlSetlTm(chlSetlTm);
		channelInfo.setChlId("C" + maxSeq);// 渠道编号
		channelInfo.setCrtTlr(sessionUserInfo.getTlrno());// 当前操作用户
		channelInfo.setCrtDateTime(BaseUtil.getCurrentDateTime());// 当前操作时间
		channelInfo.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
		channelInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新日期时间
		String channelStat = channelInfo.getChlStat();// '02-未启用，00-启用，99-停用';
		// 如果新增时操作员选择启用状态时，记录开通日期为当前系统日期。
		if (ChannelInfoConstants.CHL_STATE_00.equals(channelStat)) {
			String openDate = BaseUtil.getCurrentDate();// 当前时间，8位
			channelInfo.setChlOpenDate(openDate);// 启用日期添加
		}
		// 向渠道信息表中插入数据
		ChannelInfoService.getInstance().insert(channelInfo);

		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[渠道信息管理]--新增：渠道编号[chlId]=" + "C" + maxSeq + "" });

	}

	@SnowDoc(desc = "修改渠道信息")
	@Action(propagation = Propagation.REQUIRED)
	public void update(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取页面传递的渠道基本信息
		UpdateRequestBean reqBean = updateMap.get("channelInfo");
		ChannelInfo channelInfo = new ChannelInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), channelInfo);
		}

		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 修改时，时间格式的转换
		String chlSetlTm = channelInfo.getChlSetlTm();
		chlSetlTm = chlSetlTm.replace(":", "");
		channelInfo.setChlSetlTm(chlSetlTm);

		// 封装渠道补充信息
		channelInfo.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
		channelInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新日期时间

		// 更新渠道信息表中的数据
		ChannelInfoService.getInstance().update(channelInfo);
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[渠道信息管理]--修改：渠道编号[chlId]=" + channelInfo.getChlId() + "" });

	}

	@SnowDoc(desc = "渠道信息的启用/停用")
	@Action(propagation = Propagation.REQUIRED)
	public void updateChnlStat(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取页面传递的渠道基本信息 '02-未启用，00-启用，99-停用';
		// 三种情况：1:00-99-00-99..... 2:02-00-09-00-09....... 3:99-00-99-00
		UpdateRequestBean reqBean = updateMap.get("channelInfo");
		// 获取渠道状态
		String chlStat = reqBean.getParameter("chlStat");
		// 获取渠道号
		String chlId = reqBean.getParameter("chlId");
		// 获取实体对象
		ChannelInfo channelInfo = new ChannelInfo();
		// 设置ID
		channelInfo.setChlId(chlId);
		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 封装渠道补充信息
		channelInfo.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
		channelInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新日期时间
		// 如果是 00，则把状态改为99停用
		if (ChannelInfoConstants.CHL_STATE_00.equals(chlStat)) {
			channelInfo.setChlStat(ChannelInfoConstants.CHL_STATE_99);// 渠道状态
			// 更新渠道信息表中的数据
			ChannelInfoService.getInstance().updateChnlStat(channelInfo);
		} else if (ChannelInfoConstants.CHL_STATE_99.equals(chlStat)) {
			// 如果是首次 99 变为 00；则添加启用时间，根据渠道编号查询开通时间
			// 如果为空，则是首次
			ChannelInfo checkTime = ChannelInfoService.getInstance().selectOpenTime(chlId);
			String chlOpenDate = checkTime.getChlOpenDate();
			if (chlOpenDate == null || chlOpenDate.length() == 0) {
				channelInfo.setChlStat(ChannelInfoConstants.CHL_STATE_00);// 渠道状态
				// 设置启用时间
				channelInfo.setChlOpenDate(BaseUtil.getCurrentDate());// 当前时间，8位
				// 更新渠道信息表中的数据
				ChannelInfoService.getInstance().updateChnlStatOpenDate(channelInfo);
			} else {
				channelInfo.setChlStat(ChannelInfoConstants.CHL_STATE_00);// 渠道状态
				// 更新渠道信息表中的数据
				ChannelInfoService.getInstance().updateChnlStat(channelInfo);
			}
		} else {// 说明之前为停用，即状态为 02
				// 其实设置为启用状态
			channelInfo.setChlStat(ChannelInfoConstants.CHL_STATE_00);
			;
			// 设置启用日期
			channelInfo.setChlOpenDate(BaseUtil.getCurrentDate());// 当前时间，8位
			// 更新渠道信息表中的数据
			ChannelInfoService.getInstance().updateChnlStatOpenDate(channelInfo);
		}
		String msg = "";
		if (ChannelInfoConstants.CHL_STATE_00.equals(chlStat)) {
			msg = "[渠道信息管理]--停用:渠道编号[chlId]=" + channelInfo.getChlId();
		} else {
			msg = "[渠道信息管理]--启用:渠道编号[chlId]=" + channelInfo.getChlId();
		}
		// 打印日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });

	}

	@SnowDoc(desc = "查询渠道基本信息表的最大序列号")
	public String queryMaxSeq() throws SnowException {
		ChannelInfoService channelInfoService = ChannelInfoService.getInstance();
		String maxSeq = channelInfoService.queryMaxSeq();
		String maxSeq1 = StringUtils.substring(maxSeq, 2, 5);
		String nextSeq;
		if (maxSeq1 != null) {
			int num = Integer.valueOf(maxSeq1);
			nextSeq = StringUtil.leftPad(String.valueOf(num + 1), 4, "0");
		} else {
			nextSeq = "0001";
		}
		return nextSeq;
	}

	@Action
	@SnowDoc(desc = "用于刷新渠道权限使用")
	public PageResult query(QueryParamBean queryBean) throws SnowException {
		String chlId = queryBean.getParameter("qchlId");
		return ChannelInfoService.getInstance().query(chlId, queryBean.getPage());
	}

	/**
	 * 渠道权限方法
	 * 
	 * @param updateMap
	 * @throws Exception
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "渠道权限配置/修改")
	public void addChannelAuthInfo(Map<String, UpdateRequestBean> updateMap) throws Exception {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 获取渠道基本信息数据集
		UpdateRequestBean reqPbsProdBean = updateMap.get("channelInfo");
		// 获取渠道权限信息数据集
		UpdateRequestBean reqConfProdBean = updateMap.get("channelAuthInfo");
		// SnowLog.getLogger(this.getClass()).info("=========== STP1
		// 产品重复性验证============");
		// 获取渠道基本信息
		ChannelInfo channelInfo = new ChannelInfo();
		while (reqPbsProdBean.hasNext()) {
			DataObjectUtils.map2bean(reqPbsProdBean.next(), channelInfo);
		}
		// 获取渠道ID
		String chlId = channelInfo.getChlId();
		// 获取渠道权限数据
		List<Map<String, String>> ConfProdList = reqConfProdBean.getTotalList();
		// 此实体类用于转换页面接收的数据使用
		ChannelResultInfo channelResultInfo = new ChannelResultInfo();
		// 设置一个标志位
		boolean flag = true;
		String acctTypeNoReal;
		// 渠道开通交易和账户关联表
		PagyChlTxnAcctRel pagyChlTxnAcctRel = new PagyChlTxnAcctRel();
		// 遍历询得到所有的配置行信息
		if (ConfProdList == null || ConfProdList.size() == 0) {
			SnowExceptionUtil.throwErrorException("请完善渠道权限,至少有一条渠道权限配置！");
		}
		for (Map<String, String> map : ConfProdList) {
			channelResultInfo = DataObjectUtils.map2bean(map, ChannelResultInfo.class);
			// 如果数据是删除的就不管了
			String recordState = channelResultInfo.getRecordState();
			// 如果这条数据为“delete”，则放弃该条数据,继续下次循环
			if ("delete".equals(recordState)) {
				flag = false;
				continue;
			}
			// 获取路由交易，一个grid只能有一条
			String payTxnTypeId = channelResultInfo.getPayTxnTypeId();
			// 获取账户，一个grid可能有一条，可能有多条
			String acctTypeNo = channelResultInfo.getAcctTypeNo();
			// 账户类型可能有多个,循环遍历出来,分离账户类型
			String[] sum = acctTypeNo.split(",");
			for (int i = 0; i < sum.length; i++) {
				acctTypeNoReal = sum[i];
				// 重复性验证，渠道ID,路由交易类型，账户类型，对数据进行验证,单条多次验证
				pagyChlTxnAcctRel = ChannelInfoService.getInstance().checkAdd(chlId, payTxnTypeId, acctTypeNoReal);
				// 如果找到了该权限配置，则验证是否是本渠道的修改
				if (pagyChlTxnAcctRel != null) {
					String channelIdResult = pagyChlTxnAcctRel.getChannelId();
					// 获取查询结果中的渠道号，与数据集渠道号比较，相同也为本渠道操作
					if ((channelIdResult.trim()).equals(chlId)) {
						// 说明是原来的权限配置，不算重复,继续验证
						flag = false;
					}
					if (!(channelIdResult.trim()).equals(chlId)) {
						// 说明不是原来的权限配置重复的，提示用户，修改产品重复,跳出循环
						flag = true;
						break;
					}
				} else {// 没有找到记录，两种情况，1.原来没有权限，第一次配置
						// 2.原来已经有权限，做了修改，此时要先把原来的权限删除掉
					flag = false;
					continue;
				}
			}
		}
		/** 全部验证完成，如果flag为false则做插入操作，否则提示用户，产品重复 */
		// 只要有一条重复，就算重复
		if (flag == true) {// 说明是重复，提示用户
			SnowExceptionUtil.throwErrorException("渠道权限配置重复，请重新配置！");
		}
		// 如果false,则做插入操作
		if (flag == false) {
			// 1.第一次配置权限直接插入，2.原来已经有权限，做了修改，此时要先把原来的权限删除掉
			ChannelInfoService.getInstance().delete(chlId);
			// 遍历询得到所有的配置行信息
			String real;
			for (Map<String, String> map : ConfProdList) {
				channelResultInfo = DataObjectUtils.map2bean(map, ChannelResultInfo.class);
				// 如果数据是删除的就不管了
				String recordState = channelResultInfo.getRecordState();
				// 如果这条数据为“delete”，则放弃该条数据,继续下次循环
				if ("delete".equals(recordState)) {
					continue;
				}
				String payTxnTypeId = channelResultInfo.getPayTxnTypeId();// 获取交易类型
				String acctTypeNo = channelResultInfo.getAcctTypeNo();// 获取账户类型
				// 账户类型可能有多个,循环遍历出来
				String[] sum = acctTypeNo.split(",");
				for (int i = 0; i < sum.length; i++) {
					real = sum[i];
					// 账户关联表全部插入
					ChannelInfoService.getInstance().insertPagyChlTxnAcctRel(chlId, real, payTxnTypeId);
				}
			}
		}
		/****************** 记录日志 ********************/
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[渠道信息管理]--权限配置:渠道编号=" + channelInfo.getChlId() + "" });
	}

	/**
	 * 获取渠道名称（支持多个）
	 * 
	 * @param bean
	 * @param key
	 *            单个渠道编号或多个以“,”分割的渠道编号
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String getChannelNames(FieldBean bean, String key, ServletRequest request) throws SnowException {
		if (StringUtil.isBlank(key)) {
			return key;
		}
		List<Object> chnlNameList = ChannelInfoService.getInstance().getChnlNames(key);
		if (null == chnlNameList || chnlNameList.size() == 0) {
			return key;
		}
		String chnlNames = chnlNameList.toString().replace(" ", "").replace("[", "").replace("]", "");
		return chnlNames;
	}

	/**
	 * 根据渠道编号去查询渠道名称
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@SnowDoc(desc = "根据渠道编号去查询渠道名称")
	public static String queryByChnlName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		StringBuffer strBuf = new StringBuffer();

		if (StringUtil.isEmpty(key)) {
			return strBuf.toString();
		}
		String prodList = ChannelInfoService.getInstance().queryByChnlName(key);

		if (prodList == null || "".equals(prodList)) {
			return strBuf.toString();
		}
		strBuf.append(prodList);

		return strBuf.toString();

	}

	/**
	 * 查询渠道名称
	 * 
	 * @return
	 * @throws SnowException
	 */
	@SnowDoc(desc = "查询渠道名称")
	public PageResult queryChnlName(QueryParamBean queryBean) throws SnowException {
		return ChannelInfoService.getInstance().queryChnlName(queryBean.getPage());
	}
}
