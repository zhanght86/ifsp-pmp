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
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.baseParaMng.process.bean.PbsMchtGrpInfo;
import com.ruimin.ifs.pmp.baseParaMng.process.bean.PbsMchtMccInfo;
import com.ruimin.ifs.pmp.baseParaMng.process.service.MchtMccMngService;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;

@ActionResource
@SnowDoc(desc = "商户Mcc管理")
public class MchtMccMngAction {
	/**
	 * 商户Mcc页面查询
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "商户Mcc页面查询")
	public PageResult queryMain(QueryParamBean queryBean) throws SnowException {
		/** 查询条件 */
		String qmccId = queryBean.getParameter("qmccId");// MCC编号
		String qmccDesc = queryBean.getParameter("qmccDesc");// MCC描述
		String qmchtGrpId = queryBean.getParameter("qmchtGrpId");// 组别编号

		/** 查询主页面字段 */
		return MchtMccMngService.getInstance().queryMain(qmccId, qmccDesc, qmchtGrpId, queryBean.getPage());
	}

	/**
	 * 商户组别新增
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "商户Mcc新增")
	public void addMchtMcc(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		/** 获取数据集 */
		UpdateRequestBean reqBean = updateMap.get("mchtMcc");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";
		/** 导入实体类 */
		PbsMchtMccInfo mchtMccMngVO = new PbsMchtMccInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtMccMngVO);
		}

		/* 判断字段是否为空 */
		if (StringUtils.isBlank(mchtMccMngVO.getMccId())) {
			SnowExceptionUtil.throwWarnException("商户Mcc编号不能为空!");
		}
		if (StringUtils.isBlank(mchtMccMngVO.getMchtGrpId())) {
			SnowExceptionUtil.throwWarnException("商户组别编号不能为空!");
		}
		if (StringUtils.isBlank(mchtMccMngVO.getMccDesc())) {
			SnowExceptionUtil.throwWarnException("商户Mcc描述不能为空!");
		}

		/* 检查商户Mcc编号是否重复 */
		int count = MchtMccMngService.getInstance().checkMchtMccExist(mchtMccMngVO.getMccId());
		if (count > 0) {
			SnowExceptionUtil.throwWarnException("该商户Mcc记录已存在!");
		}

		// 新增
		MchtMccMngService.getInstance().addMchtMcc(mchtMccMngVO);

		// 日志
		msg = "[商户MCC管理 ]--新增商户MCC信息 :MCC编号[MccId] =" + mchtMccMngVO.getMccId();
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
	public void updMchtMcc(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		/** 获取数据集 */
		UpdateRequestBean reqBean = updateMap.get("mchtMcc");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";

		/** 导入实体类 */
		PbsMchtMccInfo mchtMccMngVO = new PbsMchtMccInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtMccMngVO);
		}

		/* 判断字段是否为空 */
		if (StringUtils.isBlank(mchtMccMngVO.getMchtGrpId())) {
			SnowExceptionUtil.throwWarnException("商户组别编号不能为空!");
		}
		if (StringUtils.isBlank(mchtMccMngVO.getMccDesc())) {
			SnowExceptionUtil.throwWarnException("商户Mcc描述不能为空!");
		}

		// 修改
		MchtMccMngService.getInstance().updMchtMcc(mchtMccMngVO);

		// 日志
		msg = "[商户MCC管理 ]--修改商户MCC信息 :MCC编号[MccId] =" + mchtMccMngVO.getMccId();
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
	public void delMchtMcc(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("mchtMcc");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String msg = "";

		PbsMchtMccInfo mchtMccMngVO = new PbsMchtMccInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtMccMngVO);
		}
		String mchtGrpId = mchtMccMngVO.getMchtGrpId();
		String mccId = mchtMccMngVO.getMccId();

		/* 判断字段是否为空 */
		if (StringUtils.isBlank(mchtGrpId)) {
			SnowExceptionUtil.throwWarnException("商户组别编号不能为空!");
		}
		if (StringUtils.isBlank(mccId)) {
			SnowExceptionUtil.throwWarnException("商户MCC编号不能为空!");
		}

		/**
		 * 判断该商户MCC是否被使用 去支付通道子商户信息表中判断，当通道编号为301时，是否存在商户行业编号=组别编号,商户行业子编号=MCC编号
		 */
		int count = MchtMccMngService.getInstance().queryGrpByMchtMccId(mccId);
		if (count > 0) {
			SnowExceptionUtil.throwWarnException("已有商户使用该MCC被使用，无法删除");
		} else {
			MchtMccMngService.getInstance().delMchtMcc(mccId);
		}

		// 日志
		msg = "[商户MCC管理 ]--删除商户MCC信息 :MCC编号[MccId] =" + mchtMccMngVO.getMccId();
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });
	}

	/**
	 * 根据组别编号获取组别名称
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 */
	public static String getGrpName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		if (StringUtils.isNotBlank(key)) {
			PbsMchtGrpInfo mchtGrpMngVO = MchtMccMngService.getInstance().queryGrpName(key);
			if (mchtGrpMngVO != null) {
				return mchtGrpMngVO.getGrpDesc();
			}
		}
		return key;
	}
}
