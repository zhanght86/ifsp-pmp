/**
 * 
 * Copyright (C), 2012-2013, 上海睿民互联网科技有限公司
 * FileName: TODO
 * Author:   ChengGX
 * Date:     2016年7月14日下午4:27:27
 * Description: TODO
 * History: //修改记录
 * <author>      <time>                   <version>    <desc>
 * ChengGX      2016年7月14日下午4:27:27               0.1
 */
package com.ruimin.ifs.pmp.sysConf.comp;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.sysConf.process.service.AccountTypeService;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.sysConf.common.constants.AccountTypeConstants;
import com.ruimin.ifs.pmp.sysConf.process.bean.AccountType;

@SnowDoc(desc = "账户类型的管理")
@ActionResource
public class AccountTypeAction extends SnowAction {

	/**
	 * * 功能描述: 账户类型查询
	 * 
	 * @param queryBean
	 *            查询数据集
	 * @return 返回类型 PageResult
	 * @throws SnowException
	 */
	@SnowDoc(desc = "账户类型查询")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		String qAcctTypeNo = queryBean.getParameter("qAcctTypeNo");
		String qAcctTypeName = queryBean.getParameter("qAcctTypeName");
		return AccountTypeService.getInstance().queryList(qAcctTypeNo, qAcctTypeName, queryBean.getPage());
	}

	/**
	 * * 功能描述: 账户类型新增
	 * 
	 * @param updateMap
	 *            查询数据集
	 * @return 返回类型 void
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "账户类型 新增")
	public void saveOrUpdate(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 这里获取的是数据集的东西，一般数据集都是需要把所有的字段全给写到field里面去，最主要的是id,no之类的字段
		UpdateRequestBean reqBean = updateMap.get("accountType");
		AccountType accountType = new AccountType();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), accountType);
		}
		// 先设置好new出来对象的id，最后再在Action中调用service方法
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		accountType.setCrtTlr(sessionUserInfo.getTlrno());
		String acctTypeCode = AccountTypeService.getInstance().genAcctTypeCode();
		accountType.setAcctTypeNo(acctTypeCode);
		accountType.setCrtDateTime(DateUtil.get14Date());
		;
		accountType.setAcctTypeStat(AccountTypeConstants.ACC_TYPE_00); // "00"代表启用，"99"代表启用
		AccountTypeService.getInstance().saveDataDicEntry(accountType);
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrCode(),
				"[账户类型管理]--账户新增:账户号[acctTypeCode]=" + accountType.getAcctTypeNo() });
	}

	/**
	 * * 功能描述: 账户类型修改
	 * 
	 * @param updateMap
	 *            查询数据集
	 * @return 返回类型 void
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "账户类型修改")
	public void update(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("accountType");
		AccountType accountType = new AccountType();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), accountType);
		}
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		accountType.setLastUpdTlr(sessionUserInfo.getTlrno());
		// accountType.setDataState("00");
		accountType.setLastUpdDateTime(DateUtil.get14Date());
		AccountTypeService.getInstance().update(accountType);
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrCode(),
				"[账户类型管理]--账户修改:账户号[acctTypeCode]=" + accountType.getAcctTypeNo() });
	}

	/**
	 * * 功能描述: 账户类型停用/启用
	 * 
	 * @param updateMap
	 *            查询数据集
	 * @return 返回类型 void
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "账户类型停用/启用 ")
	public void delete(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("accountType");
		AccountType accountType = new AccountType();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), accountType);
		}
		String state = accountType.getAcctTypeStat();
		if (AccountTypeConstants.ACC_TYPE_00.equals(state)) { // 如果该记录的状态"00":启用，则设置成"99"：停用
			accountType.setAcctTypeStat(AccountTypeConstants.ACC_TYPE_99);
		} else {
			accountType.setAcctTypeStat(AccountTypeConstants.ACC_TYPE_00);
		}
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		accountType.setLastUpdTlr(sessionUserInfo.getTlrno());
		;
		accountType.setLastUpdDateTime(DateUtil.get14Date());
		AccountTypeService.getInstance().update(accountType);
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrCode(),
				"[账户类型管理]--账户启用/停用:账户号[acctTypeCode]=" + accountType.getAcctTypeNo() });
	}

	/**
	 * 查询支付账户类型基础信息表,获取账户名字
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String getAcctTypeNoName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		if (StringUtil.isBlank(key)) {
			return key;
		}
		List<Object> chnlNameList = AccountTypeService.getInstance().getAcctTypeNoName(key);
		if (null == chnlNameList || chnlNameList.size() == 0) {
			return key;
		}
		String chnlNames = chnlNameList.toString().replace(" ", "").replace("[", "").replace("]", "");
		return chnlNames;
	}

	@Action
	@SnowDoc(desc = "查询支付账户类型，下拉选，多选，渠道权限使用")
	public PageResult queryAcctTypeBaseInfo(QueryParamBean queryBean) throws SnowException {
		return AccountTypeService.getInstance().queryAcctTypeBaseInfo(queryBean.getPage());
	}

	/**
	 * 查询支付账户类型基础信息表,获取账户名字
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String queryByAcctTypeNo(FieldBean bean, String key, ServletRequest request) throws SnowException {
		StringBuffer strBuf = new StringBuffer();
		if (StringUtil.isEmpty(key)) {
			return strBuf.toString();
		}
		String prodList = AccountTypeService.getInstance().queryByAcctTypeNo(key);

		if (prodList == null || "".equals(prodList)) {
			return strBuf.toString();
		}
		strBuf.append(prodList);
		return strBuf.toString();
	}

	/**
	 * 根据账户类型编号查询账户类型名称
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@SnowDoc(desc = "根据账户类型编号查询账户类型名称")
	public PageResult queryAcctTypeName(QueryParamBean queryBean) throws SnowException {
		return AccountTypeService.getInstance().queryAcctTypeName(queryBean.getPage());
	}
}
