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
import com.ruimin.ifs.pmp.sysConf.process.bean.PassBankVO;
import com.ruimin.ifs.pmp.sysConf.process.service.PassBankService;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

@SnowDoc(desc = "通道银行信息配置")
@ActionResource
public class PassBankAction extends SnowAction {

	/**
	 * 查询出所有通道状态正常的通道编号和通道名称
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryAllPassInfo(QueryParamBean queryBean) throws SnowException {

		return PassBankService.getInstance().queryAllPassInfo(queryBean.getPage());
	}

	/**
	 * 根据通道名称，银行编号，银行名称
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryPassBankByCode(QueryParamBean queryBean) throws SnowException {
		String qPassName = queryBean.getParameter("qPassInfo"); // 通道名称
		String qBankCode = queryBean.getParameter("qBankCode"); // 银行编号
		String qBankName = queryBean.getParameter("qBankName"); // 银行名称

		return PassBankService.getInstance().queryPassBankByCodeInfo(qPassName, qBankCode, qBankName,
				queryBean.getPage());
	}

	/**
	 * 增加一条通道银行配置信息
	 * 
	 * @param queryBean
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	public void addPassBankInfo(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("passbank");
		PassBankVO passBankVO = new PassBankVO();

		// 获取新增字段并写入实体类中
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), passBankVO);
		}
		// 判断字段是否为空
		if (StringUtils.isBlank(passBankVO.getPassNo())) {
			SnowExceptionUtil.throwWarnException("WEB_E0045", "通道编号不能为空!");
		}
		if (StringUtils.isBlank(passBankVO.getBankNo())) {
			SnowExceptionUtil.throwWarnException("WEB_E0045", "银行编号不能为空!");
		}
		if (StringUtils.isBlank(passBankVO.getBankName())) {
			SnowExceptionUtil.throwWarnException("WEB_E0045", "银行名称不能为空!");
		}

		// 通过通道编号和银行编号检查该条记录是否存在
		int count = PassBankService.getInstance().queryPassBankExits(passBankVO.getPassNo(), passBankVO.getBankNo());
		if (count > 0) {
			SnowExceptionUtil.throwWarnException("WEB_E0045", "该条记录已存在!");
		}

		PassBankService.getInstance().addPassBankInfo(passBankVO);
	}

	/**
	 * 修改通道银行配置信息
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	public void updPassBankInfo(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("passbank");

		PassBankVO passBankVO = new PassBankVO();

		// 获取新增字段并写入实体类中
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), passBankVO);
		}

		if (StringUtils.isBlank(passBankVO.getBankNo())) {
			SnowExceptionUtil.throwWarnException("银行编号不能为空!");
		}
		if (StringUtils.isBlank(passBankVO.getBankName())) {
			SnowExceptionUtil.throwWarnException("银行名称不能为空!");
		}

		PassBankService.getInstance().updPassBankInfo(passBankVO);
	}

	/**
	 * 设置记录启用，停用
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	public void setPassBankStatus(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		PassBankVO passBankVO = new PassBankVO();
		// 取数据
		UpdateRequestBean reqBean = updateMap.get("passbank");
		String dataState = reqBean.getParameter("dataState");

		// 获取新增字段并写入实体类中
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), passBankVO);
		}
		String passNo = passBankVO.getPassNo();
		String bankNo = passBankVO.getBankNo();

		// 跟新实体类
		PassBankService.getInstance().updatePassBankState(passNo, bankNo, dataState);

		String msg = "";
		if ("99".equals(dataState)) {
			msg = "通道银行配置启用,passNo=" + passBankVO.getPassNo() + "bankNo=" + passBankVO.getBankNo();
		} else {

			msg = "通道银行配置停用,passNo=" + passBankVO.getPassNo() + "bankNo=" + passBankVO.getBankNo();
		}
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), msg });

	}

}
