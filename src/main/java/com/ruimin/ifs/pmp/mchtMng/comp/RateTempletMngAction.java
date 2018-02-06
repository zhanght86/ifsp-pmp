/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.mchtMng.comp 
 * FileName: RateTempletMngAction.java
 * Author:   zrx
 * Date:     2016年7月18日 下午3:43:53
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月18日下午3:43:53                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mchtMng.comp;

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
import com.ruimin.ifs.pmp.mchtMng.common.constants.RateMngConstants;
import com.ruimin.ifs.pmp.mchtMng.process.bean.RateBaseInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.RateRuleInfo;
import com.ruimin.ifs.pmp.mchtMng.process.service.RateRuleInfoService;
import com.ruimin.ifs.pmp.mchtMng.process.service.RateTempletMngService;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月18日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
@SnowDoc(desc = "费率模板管理Action")
@ActionResource
public class RateTempletMngAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询费率模板信息")
	public PageResult queryRateBaseInfo(QueryParamBean queryBean) throws SnowException {
		// 获取查询参数
		// 费率编号
		String qrateId = queryBean.getParameter("qrateId");
		// 费率名称
		String qrateName = queryBean.getParameter("qrateName");
		// 分段标志
		String qsectionType = queryBean.getParameter("qsectionType");
		// 分页查询接入方式信息
		return RateTempletMngService.getInstance().queryList(qrateId, qrateName, qsectionType, queryBean.getPage());
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增费率模板信息")
	public void add(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateMap.get("rateBaseInfo");
		RateBaseInfo rateBaseInfo = new RateBaseInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), rateBaseInfo);
		}
		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 封装费率补充信息
		rateBaseInfo.setCrtTlr(sessionUserInfo.getTlrno());// 当前操作用户
		rateBaseInfo.setCrtDateTime(BaseUtil.getCurrentDateTime());// 当前操作时间
		rateBaseInfo.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
		rateBaseInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新日期时间
		rateBaseInfo.setRateState(RateMngConstants.RATE_STATE_NORMAL);// 费率状态
		String rateId = queryMaxSeq();
		rateBaseInfo.setRateId("A" + rateId);// 赋值最大费率编号
		RateTempletMngService.getInstance().insertRateTemplet(rateBaseInfo);

		// 封装费率模板分段信息
		String sectionType = rateBaseInfo.getSectionType();
		if (!StringUtil.isEmpty(sectionType) && sectionType.equals(RateMngConstants.RATE_SECTION_TYPE)) {
			// 获取页面传递的分段信息
			UpdateRequestBean reqBean1 = updateMap.get("rateRuleInfo");
			RateRuleInfo rateRuleInfo = new RateRuleInfo();
			while (reqBean1.hasNext()) {
				DataObjectUtils.map2bean(reqBean1.next(), rateRuleInfo);
			}
			String rateRuleNo = queryMaxSeq1();
			rateRuleInfo.setRateRuleNo(rateRuleNo);// 赋值最大费率规则序号
			rateRuleInfo.setRateId("A" + rateId);// 费率编号
			rateRuleInfo.setCrtTlr(sessionUserInfo.getTlrno());// 当前操作用户
			rateRuleInfo.setCrtDateTime(BaseUtil.getCurrentDateTime());// 当前操作时间
			rateRuleInfo.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
			rateRuleInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新日期时间
			rateRuleInfo.setDateState(RateMngConstants.RATE_STATE_NORMAL);// 数据有效状态
			String chargeType = rateRuleInfo.getChargeType();
			String chargeValue = rateRuleInfo.getChargeValue();
			if (chargeType.equals(RateMngConstants.RATE_CHARGE_TYPE)) {
				rateRuleInfo
						.setChargeValue(StringUtil.isEmpty(chargeValue) ? "" : BaseUtil.transYuanToFen(chargeValue));
			} else {
				String feeMin = rateRuleInfo.getFeeMin();// 最小手续费
				String feeMax = rateRuleInfo.getFeeMax();// 最大手续费
				rateRuleInfo.setChargeValue(StringUtil.isEmpty(chargeValue) ? "" : chargeValue);
				rateRuleInfo.setFeeMin(StringUtil.isEmpty(feeMin) ? "" : BaseUtil.transYuanToFen(feeMin));
				rateRuleInfo.setFeeMax(StringUtil.isEmpty(feeMax) ? "" : BaseUtil.transYuanToFen(feeMax));
			}
			// 向费率规则表中插入数据
			RateRuleInfoService.getInstance().addRateRuleInfo(rateRuleInfo);

		} else if (!StringUtil.isEmpty(sectionType) && sectionType.equals(RateMngConstants.RATE_SECTION_TYPE1)) {
			// 获取页面传递的分段信息
			UpdateRequestBean reqBean1 = updateMap.get("rateRuleInfo");
			RateRuleInfo rateRuleInfo = new RateRuleInfo();
			int rateRuleNo = Integer.parseInt(queryMaxSeq1());
			while (reqBean1.hasNext()) {
				DataObjectUtils.map2bean(reqBean1.next(), rateRuleInfo);
				// 封装分段补充信息
				rateRuleInfo.setRateRuleNo(StringUtil.leftPad(String.valueOf(rateRuleNo), 3, "0"));// 赋值最大费率规则序号
				rateRuleInfo.setRateId("A" + rateId);// 费率编号
				rateRuleInfo.setCrtTlr(sessionUserInfo.getTlrno());// 当前操作用户
				rateRuleInfo.setCrtDateTime(BaseUtil.getCurrentDateTime());// 当前操作时间
				rateRuleInfo.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
				rateRuleInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新日期时间
				rateRuleInfo.setDateState(RateMngConstants.RATE_STATE_NORMAL);// 数据有效状态				
				rateRuleInfo.setSectionMin(StringUtil.isEmpty(rateRuleInfo.getSectionMin()) ? "0"
						: BaseUtil.transYuanToFen(rateRuleInfo.getSectionMin()));
				rateRuleInfo.setSectionMax(StringUtil.isEmpty(rateRuleInfo.getSectionMax()) ? ""
						: BaseUtil.transYuanToFen(rateRuleInfo.getSectionMax()));
				String chargeType = rateRuleInfo.getChargeType();// 收费类型
				String chargeValue = rateRuleInfo.getChargeValue();// 收费值
				if (chargeType.equals(RateMngConstants.RATE_CHARGE_TYPE)) {
					rateRuleInfo.setChargeValue(
							StringUtil.isEmpty(chargeValue) ? "" : BaseUtil.transYuanToFen(chargeValue));
				} else {
					String feeMin = rateRuleInfo.getFeeMin();// 最小手续费
					String feeMax = rateRuleInfo.getFeeMax();// 最大手续费
					rateRuleInfo.setChargeValue(StringUtil.isEmpty(chargeValue) ? "" : chargeValue);
					rateRuleInfo.setFeeMin(StringUtil.isEmpty(feeMin) ? "" : BaseUtil.transYuanToFen(feeMin));
					rateRuleInfo.setFeeMax(StringUtil.isEmpty(feeMax) ? "" : BaseUtil.transYuanToFen(feeMax));
				}
				// 批量插入费率分段列表
				RateRuleInfoService.getInstance().addRateRuleInfo(rateRuleInfo);
				rateRuleNo++;
			}
		}

		// 记录日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "新增费率模板,编号=[" + rateId + "]" });

	}

	@SnowDoc(desc = "修改费率模板")
	@Action(propagation = Propagation.REQUIRED)
	public void update(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateMap.get("rateBaseInfo");
		RateBaseInfo rateBaseInfo = new RateBaseInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), rateBaseInfo);
		}
		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 封装费率补充信息
		rateBaseInfo.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
		rateBaseInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新日期时间

		// 更新费率模板信息
		RateTempletMngService.getInstance().updateRateTemplet(rateBaseInfo);

		// 获取页面传递的分段信息
		UpdateRequestBean reqBean1 = updateMap.get("rateRuleInfo");
		String rateId = reqBean1.getParameter("rateId");
		// 根据费率编号查询费率规则表中是否已存在对应的记录，如不存在，则执行插入操作，否则，执行更新操作
		List<Object> list = RateRuleInfoService.getInstance().queryRateRuleinfo(rateId);
		if (list == null || list.size() == 0) {
			// 封装费率模板分段信息
			String sectionType = rateBaseInfo.getSectionType();
			if (!StringUtil.isEmpty(sectionType) && sectionType.equals(RateMngConstants.RATE_SECTION_TYPE)) {
				// 获取实体类
				RateRuleInfo rateRuleInfo = new RateRuleInfo();
				while (reqBean1.hasNext()) {
					DataObjectUtils.map2bean(reqBean1.next(), rateRuleInfo);
				}
				String rateRuleNo = queryMaxSeq1();
				rateRuleInfo.setRateRuleNo(rateRuleNo);// 赋值最大费率规则序号
				rateRuleInfo.setRateId(rateId);// 费率编号
				rateRuleInfo.setCrtTlr(sessionUserInfo.getTlrno());// 当前操作用户
				rateRuleInfo.setCrtDateTime(BaseUtil.getCurrentDateTime());// 当前操作时间
				rateRuleInfo.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
				rateRuleInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新日期时间
				rateRuleInfo.setDateState(RateMngConstants.RATE_STATE_NORMAL);// 数据有效状态
				String chargeType = rateRuleInfo.getChargeType();
				String chargeValue = rateRuleInfo.getChargeValue();
				if (chargeType.equals(RateMngConstants.RATE_CHARGE_TYPE)) {
					rateRuleInfo.setChargeValue(
							StringUtil.isEmpty(chargeValue) ? "" : BaseUtil.transYuanToFen(chargeValue));
				} else {
					String feeMin = rateRuleInfo.getFeeMin();// 最小手续费
					String feeMax = rateRuleInfo.getFeeMax();// 最大手续费
					rateRuleInfo.setChargeValue(StringUtil.isEmpty(chargeValue) ? "" : chargeValue);
					rateRuleInfo.setFeeMin(StringUtil.isEmpty(feeMin) ? "" : BaseUtil.transYuanToFen(feeMin));
					rateRuleInfo.setFeeMax(StringUtil.isEmpty(feeMax) ? "" : BaseUtil.transYuanToFen(feeMax));
				}
				// 向费率规则表中插入数据
				RateRuleInfoService.getInstance().addRateRuleInfo(rateRuleInfo);

			} else if (!StringUtil.isEmpty(sectionType) && sectionType.equals(RateMngConstants.RATE_SECTION_TYPE1)) {
				// 获取实体类
				RateRuleInfo rateRuleInfo = new RateRuleInfo();
				int rateRuleNo = Integer.parseInt(queryMaxSeq1());
				while (reqBean1.hasNext()) {
					DataObjectUtils.map2bean(reqBean1.next(), rateRuleInfo);
					// 封装分段补充信息
					rateRuleInfo.setRateRuleNo(StringUtil.leftPad(String.valueOf(rateRuleNo), 3, "0"));// 赋值最大费率规则序号
					rateRuleInfo.setRateId(rateId);// 费率编号
					rateRuleInfo.setCrtTlr(sessionUserInfo.getTlrno());// 当前操作用户
					rateRuleInfo.setCrtDateTime(BaseUtil.getCurrentDateTime());// 当前操作时间
					rateRuleInfo.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
					rateRuleInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新日期时间
					rateRuleInfo.setDateState(RateMngConstants.RATE_STATE_NORMAL);// 数据有效状态
					rateRuleInfo.setSectionMin(StringUtil.isEmpty(rateRuleInfo.getSectionMin()) ? "0"
							: BaseUtil.transYuanToFen(rateRuleInfo.getSectionMin()));
					rateRuleInfo.setSectionMax(StringUtil.isEmpty(rateRuleInfo.getSectionMax()) ? ""
							: BaseUtil.transYuanToFen(rateRuleInfo.getSectionMax()));
					String chargeType = rateRuleInfo.getChargeType();// 收费类型
					String chargeValue = rateRuleInfo.getChargeValue();// 收费值
					if (chargeType.equals(RateMngConstants.RATE_CHARGE_TYPE)) {
						rateRuleInfo.setChargeValue(
								StringUtil.isEmpty(chargeValue) ? "" : BaseUtil.transYuanToFen(chargeValue));
					} else {
						String feeMin = rateRuleInfo.getFeeMin();// 最小手续费
						String feeMax = rateRuleInfo.getFeeMax();// 最大手续费
						rateRuleInfo.setChargeValue(StringUtil.isEmpty(chargeValue) ? "" : chargeValue);
						rateRuleInfo.setFeeMin(StringUtil.isEmpty(feeMin) ? "" : BaseUtil.transYuanToFen(feeMin));
						rateRuleInfo.setFeeMax(StringUtil.isEmpty(feeMax) ? "" : BaseUtil.transYuanToFen(feeMax));
					}
					// 批量插入费率分段列表
					RateRuleInfoService.getInstance().addRateRuleInfo(rateRuleInfo);
					rateRuleNo++;
				}
			}
		} else {
			// 封装费率模板分段信息
			String sectionType = rateBaseInfo.getSectionType();
			if (!StringUtil.isEmpty(sectionType) && sectionType.equals(RateMngConstants.RATE_SECTION_TYPE)) {
				// 获取实体类
				RateRuleInfo rateRuleInfo = new RateRuleInfo();
				int rateRuleNo = Integer.parseInt(queryMaxSeq1());
				while (reqBean1.hasNext()) {
					DataObjectUtils.map2bean(reqBean1.next(), rateRuleInfo);
					// 当本条分段记录状态为删除时
					if (reqBean1.getRecodeState() == reqBean1.DELETE) {
						RateRuleInfoService.getInstance().daleteRateRuleInfo(rateRuleInfo);
					} else if (reqBean1.getRecodeState() == reqBean1.INSERT) {
						// 如果本条分段记录状态为新增时
						rateRuleInfo.setRateRuleNo(StringUtil.leftPad(String.valueOf(rateRuleNo), 3, "0"));// 赋值最大费率规则序号
						rateRuleInfo.setRateId(rateId);// 费率编号
						rateRuleInfo.setCrtTlr(sessionUserInfo.getTlrno());// 当前操作用户
						rateRuleInfo.setCrtDateTime(BaseUtil.getCurrentDateTime());// 当前操作时间
						rateRuleInfo.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
						rateRuleInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新日期时间
						rateRuleInfo.setDateState(RateMngConstants.RATE_STATE_NORMAL);// 数据有效状态
						String chargeType = rateRuleInfo.getChargeType();// 收费类型
						String chargeValue = rateRuleInfo.getChargeValue();// 收费值
						if (chargeType.equals(RateMngConstants.RATE_CHARGE_TYPE)) {
							rateRuleInfo.setChargeValue(
									StringUtil.isEmpty(chargeValue) ? "" : BaseUtil.transYuanToFen(chargeValue));
						} else {
							String feeMin = rateRuleInfo.getFeeMin();// 最小手续费
							String feeMax = rateRuleInfo.getFeeMax();// 最大手续费
							rateRuleInfo.setChargeValue(StringUtil.isEmpty(chargeValue) ? "" : chargeValue);
							rateRuleInfo.setFeeMin(StringUtil.isEmpty(feeMin) ? "" : BaseUtil.transYuanToFen(feeMin));
							rateRuleInfo.setFeeMax(StringUtil.isEmpty(feeMax) ? "" : BaseUtil.transYuanToFen(feeMax));
						}
						RateRuleInfoService.getInstance().addRateRuleInfo(rateRuleInfo);
						rateRuleNo++;
					} else if (reqBean1.getRecodeState() == reqBean1.MODIFY) {
						rateRuleInfo.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
						rateRuleInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新日期时间
						rateRuleInfo.setDateState(RateMngConstants.RATE_STATE_NORMAL);// 数据有效状态
						String chargeType = rateRuleInfo.getChargeType();// 收费类型
						String chargeValue = rateRuleInfo.getChargeValue();// 收费值
						if (chargeType.equals(RateMngConstants.RATE_CHARGE_TYPE)) {
							rateRuleInfo.setChargeValue(
									StringUtil.isEmpty(chargeValue) ? "" : BaseUtil.transYuanToFen(chargeValue));
						} else {
							String feeMin = rateRuleInfo.getFeeMin();// 最小手续费
							String feeMax = rateRuleInfo.getFeeMax();// 最大手续费
							rateRuleInfo.setChargeValue(StringUtil.isEmpty(chargeValue) ? "" : chargeValue);
							rateRuleInfo.setFeeMin(StringUtil.isEmpty(feeMin) ? "" : BaseUtil.transYuanToFen(feeMin));
							rateRuleInfo.setFeeMax(StringUtil.isEmpty(feeMax) ? "" : BaseUtil.transYuanToFen(feeMax));
						}
						// 向费率规则表中插入数据
						RateRuleInfoService.getInstance().updateRateRuleInfo(rateRuleInfo);
					}
				}
			} else if (!StringUtil.isEmpty(sectionType) && sectionType.equals(RateMngConstants.RATE_SECTION_TYPE1)) {
				// 获取实体类
				RateRuleInfo rateRuleInfo = new RateRuleInfo();
				int rateRuleNo = Integer.parseInt(queryMaxSeq1());
				while (reqBean1.hasNext()) {
					DataObjectUtils.map2bean(reqBean1.next(), rateRuleInfo);
					rateRuleInfo.setDateState(RateMngConstants.RATE_STATE_NORMAL);// 数据有效状态
					// 当本条分段记录未改动时，跳过本条
					if (reqBean1.getRecodeState() == reqBean1.NONE) {
						continue;
					}
					// 当本条分段记录状态为删除时
					if (reqBean1.getRecodeState() == reqBean1.DELETE) {
						RateRuleInfoService.getInstance().daleteRateRuleInfo(rateRuleInfo);
					} else if (reqBean1.getRecodeState() == reqBean1.INSERT) {
						// 如果本条分段记录状态为新增时
						rateRuleInfo.setRateRuleNo(StringUtil.leftPad(String.valueOf(rateRuleNo), 3, "0"));// 赋值最大费率规则序号
						rateRuleInfo.setRateId(rateId);// 费率编号
						rateRuleInfo.setCrtTlr(sessionUserInfo.getTlrno());// 当前操作用户
						rateRuleInfo.setCrtDateTime(BaseUtil.getCurrentDateTime());// 当前操作时间
						rateRuleInfo.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
						rateRuleInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新日期时间
						rateRuleInfo.setSectionMin(StringUtil.isEmpty(rateRuleInfo.getSectionMin()) ? "0"
								: BaseUtil.transYuanToFen(rateRuleInfo.getSectionMin()));
						rateRuleInfo.setSectionMax(StringUtil.isEmpty(rateRuleInfo.getSectionMax()) ? ""
								: BaseUtil.transYuanToFen(rateRuleInfo.getSectionMax()));
						String chargeType = rateRuleInfo.getChargeType();// 收费类型
						String chargeValue = rateRuleInfo.getChargeValue();// 收费值
						if (chargeType.equals(RateMngConstants.RATE_CHARGE_TYPE)) {
							rateRuleInfo.setChargeValue(
									StringUtil.isEmpty(chargeValue) ? "" : BaseUtil.transYuanToFen(chargeValue));
						} else {
							String feeMin = rateRuleInfo.getFeeMin();// 最小手续费
							String feeMax = rateRuleInfo.getFeeMax();// 最大手续费
							rateRuleInfo.setChargeValue(StringUtil.isEmpty(chargeValue) ? "" : chargeValue);
							rateRuleInfo.setFeeMin(StringUtil.isEmpty(feeMin) ? "" : BaseUtil.transYuanToFen(feeMin));
							rateRuleInfo.setFeeMax(StringUtil.isEmpty(feeMax) ? "" : BaseUtil.transYuanToFen(feeMax));
						}
						RateRuleInfoService.getInstance().addRateRuleInfo(rateRuleInfo);
						rateRuleNo++;
					} else if (reqBean1.getRecodeState() == reqBean1.MODIFY) {
						rateRuleInfo.setLastUpdTlr(sessionUserInfo.getTlrno());// 最近更新柜员
						rateRuleInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最近更新日期时间
						rateRuleInfo.setSectionMin(StringUtil.isEmpty(rateRuleInfo.getSectionMin()) ? "0"
								: BaseUtil.transYuanToFen(rateRuleInfo.getSectionMin()));
						rateRuleInfo.setSectionMax(StringUtil.isEmpty(rateRuleInfo.getSectionMax()) ? ""
								: BaseUtil.transYuanToFen(rateRuleInfo.getSectionMax()));
						String chargeType = rateRuleInfo.getChargeType();// 收费类型
						String chargeValue = rateRuleInfo.getChargeValue();// 收费值
						if (chargeType.equals(RateMngConstants.RATE_CHARGE_TYPE)) {
							rateRuleInfo.setChargeValue(
									StringUtil.isEmpty(chargeValue) ? "" : BaseUtil.transYuanToFen(chargeValue));
						} else {
							String feeMin = rateRuleInfo.getFeeMin();// 最小手续费
							String feeMax = rateRuleInfo.getFeeMax();// 最大手续费
							rateRuleInfo.setChargeValue(StringUtil.isEmpty(chargeValue) ? "" : chargeValue);
							rateRuleInfo.setFeeMin(StringUtil.isEmpty(feeMin) ? "" : BaseUtil.transYuanToFen(feeMin));
							rateRuleInfo.setFeeMax(StringUtil.isEmpty(feeMax) ? "" : BaseUtil.transYuanToFen(feeMax));
						}
						RateRuleInfoService.getInstance().updateRateRuleInfo(rateRuleInfo);
					}
				}
			}
		}
		// 记录日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "修改费率模板,编号=[" + rateId + "]" });

	}

	@SnowDoc(desc = "删除费率模板")
	@Action(propagation = Propagation.REQUIRED)
	public void delete(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取页面传递的方法基本信息
		UpdateRequestBean reqBean = updateMap.get("rateBaseInfo");
		String rateId = reqBean.getParameter("rateId");

		// 查询费率模板是否被使用
		int a = RateTempletMngService.getInstance().select(rateId);
		if (a < 1) {
			// 删除费率规则表中的信息
			RateRuleInfoService.getInstance().delete(rateId);

			// 删除费率模板信息
			RateTempletMngService.getInstance().delete(rateId);
		} else {
			SnowExceptionUtil.throwErrorException("该费率模板正在使用中,无法删除!");
		}

		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 记录日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "删除费率模板,编号=[" + rateId + "]" });

	}

	@SnowDoc(desc = "修改费率模板")
	@Action(propagation = Propagation.REQUIRED)
	public void allowModify(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取页面传递的方法基本信息
		UpdateRequestBean reqBean = updateMap.get("rateBaseInfo");
		String rateId = reqBean.getParameter("rateId");
		int a = RateTempletMngService.getInstance().select(rateId);
		if (a > 0) {
			SnowExceptionUtil.throwErrorException("该费率模板正在使用中,不允许修改!");
		}
		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 记录日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "费率模板,编号=[" + rateId + "]" });

	}

	@SnowDoc(desc = "查询费率基本信息表的最大序列号")
	public String queryMaxSeq() throws SnowException {
		RateTempletMngService rateTempletMngService = RateTempletMngService.getInstance();
		String maxSeq = rateTempletMngService.queryMaxSeq();
		String maxSeq1 = StringUtils.substring(maxSeq, 1, 4);
		String nextSeq;
		if (maxSeq1 != null) {
			int num = Integer.valueOf(maxSeq1);
			nextSeq = StringUtil.leftPad(String.valueOf(num + 1), 3, "0");
		} else {
			nextSeq = "001";
		}
		return nextSeq;
	}

	@SnowDoc(desc = "查询费率规则表的最大序列号")
	public String queryMaxSeq1() throws SnowException {
		RateTempletMngService rateTempletMngService = RateTempletMngService.getInstance();
		String maxSeq = rateTempletMngService.queryMaxSeq1();
		String nextSeq;
		if (maxSeq != null) {
			int num = Integer.valueOf(maxSeq);
			nextSeq = StringUtil.leftPad(String.valueOf(num + 1), 3, "0");
		} else {
			nextSeq = "001";
		}
		return nextSeq;
	}

	/**
	 * 渠道管理，详情显示使用，获取费率模板名字
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String getchlRateName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		if (StringUtil.isBlank(key)) {
			return key;
		}
		List<Object> chnlNameList = RateTempletMngService.getInstance().getchlRateName(key);
		if (null == chnlNameList || chnlNameList.size() == 0) {
			return key;
		}
		String chnlNames = chnlNameList.toString().replace(" ", "").replace("[", "").replace("]", "");
		return chnlNames;
	}

	@Action
	@SnowDoc(desc = "费率基本信息查询")
	public PageResult queryRateInfo(QueryParamBean queryBean) throws SnowException {
		return RateTempletMngService.getInstance().querySelect(queryBean.getPage());
	}

}
