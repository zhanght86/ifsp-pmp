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
public class OpenAcctOrganAction {
	/**
	 * 查询开户机构所有信息
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action()
	@SnowDoc(desc = "查询开户机构所有信息")
	public PageResult queryByAll(QueryParamBean queryBean) throws SnowException {
		// 获取开户机构编号
		String acctInstNo = queryBean.getParameter("qacctInstNo");
		// 获取开户机构名称
		String acctInstName = queryBean.getParameter("qacctInstName");
		return OpenAcctOrganService.getInstance().queryListTmp(acctInstNo, acctInstName, queryBean.getPage());
	}

	/**
	 * 新增操作
	 * 
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增")
	public void addAcct(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("openAcctOrgan");
		// 创建实体类对象
		OpenAcctOrgan oao = new OpenAcctOrgan();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), oao);

		}

		// 查询开户机构编号是否重复
		OpenAcctOrgan open = OpenAcctOrganService.getInstance().queryByNo(oao.getAcctInstNo());

		if (open != null) {
			SnowExceptionUtil.throwErrorException("开户机构编号不允许重复");
		}

		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 设置数据状态00启用 99停用
		oao.setDataState("00");
		// 设置创建柜员
		oao.setCrtTlr(sessionUserInfo.getTlrno());
		// 设置创建时间
		oao.setCrtDateTime(BaseUtil.getCurrentDateTime());
		oao.setLastUpdTlr(sessionUserInfo.getTlrno());
		oao.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		OpenAcctOrganService.getInstance().addAcct(oao);
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[开户机构]--新增 : 开户机构编号[acctInstNo]=" + oao.getAcctInstNo() });

	}

	/**
	 * 开户机构修改操作
	 * 
	 * @param updateBean
	 * @throws SnowException
	 */
	@Action()
	@SnowDoc(desc = "修改")
	public void updateAcct(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("openAcctOrgan");
		OpenAcctOrgan oao = new OpenAcctOrgan();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), oao);
		}
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		oao.setDataState("00");
		oao.setCrtTlr(sessionUserInfo.getTlrno());
		oao.setCrtDateTime(BaseUtil.getCurrentDateTime());
		oao.setLastUpdTlr(sessionUserInfo.getTlrno());
		oao.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		OpenAcctOrganService.getInstance().updateAcct(oao);
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[开户机构]--修改 : 开户机构编号[acctInstNo]=" + oao.getAcctInstNo() });
	}

	/**
	 * 删除时根据开户机构编号到商户信息表中查询该条记录的状态,状态为00的不允许删除
	 * 
	 * @param updateBean
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "删除")
	public void deleteAcct(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("openAcctOrgan");
		OpenAcctOrgan oao = new OpenAcctOrgan();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), oao);
			String acctInstNo = oao.getAcctInstNo();
			// 先根据开户机构编号到商户合同信息表中查看该条开户机构是否被使用
			String dataState = OpenAcctOrganService.getInstance().queryByAcctInstNo(acctInstNo);
			// 商户合同的状态 03-->已过期 07-->修改被拒绝 09-->中止被拒绝 11-->恢复被拒绝
			// 这几种状态表示开户机构在商户合同里没有被用到
			if (MchtContractConstants.MCHT_CONTR_STAT_NORMAL.equals(dataState)
					|| MchtContractConstants.MCHT_CONTR_STAT_ON.equals(dataState)
					|| MchtContractConstants.MCHT_CONTR_STAT_DEADING.equals(dataState)
					|| MchtContractConstants.MCHT_STAT_04.equals(dataState)
					|| MchtContractConstants.MCHT_STAT_05.equals(dataState)
					|| MchtContractConstants.MCHT_STAT_06.equals(dataState)
					|| MchtContractConstants.MCHT_STAT_08.equals(dataState)
					|| MchtContractConstants.MCHT_STAT_10.equals(dataState)
					|| MchtContractConstants.MCHT_CONTR_STAT_OFF.equals(dataState)) {
				SnowExceptionUtil.throwErrorException("该开户机构信息已被使用，不允许删除");
			}
		}
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		OpenAcctOrganService.getInstance().deleteAcct(oao);
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[开户机构]--删除 : 开户机构编号[acctInstNo]=" + oao.getAcctInstNo() });
	}

	/**
	 * 根据开户机构编号查找开户机构名
	 * 
	 * @param bean
	 * @param key
	 *            开户机构编号
	 * @param request
	 * @return 开户机构名称
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "根据开户机构编号查找开户机构名")
	public static String getSetlAcctInstituteName(FieldBean bean, String key, ServletRequest request)
			throws SnowException {
		if (StringUtil.isBlank(key)) {
			return key;
		}
		List<Object> SetlAcctInstituteNameList = OpenAcctOrganService.getInstance().getSetlAcctInstituteName(key);
		if (null == SetlAcctInstituteNameList || SetlAcctInstituteNameList.size() == 0) {
			return key;
		}
		String SetlAcctInstituteName = SetlAcctInstituteNameList.toString().replace(" ", "").replace("[", "")
				.replace("]", "");
		return SetlAcctInstituteName;
	}

}
