package com.ruimin.ifs.pmp.baseParaMng.comp;

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
import com.ruimin.ifs.pmp.baseParaMng.process.bean.PbsMchtGrpInfo;
import com.ruimin.ifs.pmp.baseParaMng.process.service.MchtGrpMngService;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

@ActionResource
@SnowDoc(desc = "商户组别管理")
public class MchtGrpMngAction {
	/**
	 * 商户组别页面查询
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "商户组别页面查询")
	public PageResult queryMain(QueryParamBean queryBean) throws SnowException {
		/** 查询条件 */
		String qmchtGrpNo = queryBean.getParameter("qmchtGrpNo");// 商户组别编号
		String qgrpDesc = queryBean.getParameter("qgrpDesc");// 商户组别描述

		/** 查询主页面字段 */
		return MchtGrpMngService.getInstance().queryMain(qmchtGrpNo, qgrpDesc, queryBean.getPage());
	}

	/**
	 * 商户组别新增
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "商户组别新增")
	public void addMchtGrp(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		/** 获取数据集 */
		UpdateRequestBean reqBean = updateMap.get("mchtGrp");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";

		/** 导入实体类 */
		PbsMchtGrpInfo mchtGrpMngVO = new PbsMchtGrpInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtGrpMngVO);
		}

		/* 判断组别编号字段是否为空 */
		if (StringUtils.isBlank(mchtGrpMngVO.getMchtGrpNo())) {
			SnowExceptionUtil.throwWarnException("商户组别编号不能为空!");
		}

		/* 检查商户组别编号是否重复 */
		int count = MchtGrpMngService.getInstance().checkMchtGrpExist(mchtGrpMngVO.getMchtGrpNo());
		if (count > 0) {
			SnowExceptionUtil.throwWarnException("该商户组别记录已存在!");
		}

		// 新增
		MchtGrpMngService.getInstance().addMchtGrp(mchtGrpMngVO);

		// 日志
		msg = "[商户组别管理 ]--新增商户组别信息 :商户组别编号[MchtGrpNo] =" + mchtGrpMngVO.getMchtGrpNo();
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}

	/**
	 * 修改
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改")
	public void updMchtGrp(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		/** 获取数据集 */
		UpdateRequestBean reqBean = updateMap.get("mchtGrp");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";

		/** 导入实体类 */
		PbsMchtGrpInfo mchtGrpMngVO = new PbsMchtGrpInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtGrpMngVO);
		}

		/** 修改前判断组别描述是否为空 */
		if (StringUtils.isBlank(mchtGrpMngVO.getGrpDesc())) {
			SnowExceptionUtil.throwWarnException("商户组别描述不能为空!");
		}

		// 修改
		MchtGrpMngService.getInstance().updMchtGrp(mchtGrpMngVO);

		// 日志
		msg = "[商户组别管理 ]--修改商户组别信息 :商户组别编号[MchtGrpNo] =" + mchtGrpMngVO.getMchtGrpNo();
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}

	/**
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "删除")
	public void delMchtGrp(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("mchtGrp");
		String mchtGrpNo = reqBean.getParameter("mchtGrpNo");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";

		/**
		 * 判断该商户组别是否被使用
		 * 
		 * 当商户组别下有mcc则不能删除，并提示用户"该组别下存在MCC信息，不允许删除"
		 */
		int count = MchtGrpMngService.getInstance().queryMccByMchtGrpNo(mchtGrpNo);
		if (count > 0) {
			SnowExceptionUtil.throwWarnException("该组别下存在MCC信息，不允许删除");
		} else {
			MchtGrpMngService.getInstance().delMchtGrp(mchtGrpNo);
		}

		// 日志
		msg = "[商户组别管理  ]--删除商户组别管信息 :商户组别编号[MchtGrpNo] =" + mchtGrpNo;
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}
}
