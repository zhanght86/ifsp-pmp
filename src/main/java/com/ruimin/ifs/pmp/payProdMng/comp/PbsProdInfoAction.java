package com.ruimin.ifs.pmp.payProdMng.comp;

import java.util.ArrayList;
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
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtMngVO;
import com.ruimin.ifs.pmp.payProdMng.common.constants.PayProdConstants;
import com.ruimin.ifs.pmp.payProdMng.process.bean.PbsProdInfo;
import com.ruimin.ifs.pmp.payProdMng.process.bean.PbsProdInfoVO;
import com.ruimin.ifs.pmp.payProdMng.process.service.PbsProdInfoService;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.sysConf.process.bean.AccessTypeInfo;

@SnowDoc(desc = "产品管理")
@ActionResource
public class PbsProdInfoAction extends SnowAction {
	@Action
	@SnowDoc(desc = "产品信息查询")
	public PageResult queryPbsProdInfo(QueryParamBean queryBean) throws SnowException {
		String qprodId = queryBean.getParameter("qprodId");
		String qprodName = queryBean.getParameter("qprodName");
		String qprodState = queryBean.getParameter("qprodState");
		return PbsProdInfoService.getInstance().queryPbsProdInfo(qprodId, qprodName, qprodState, queryBean.getPage());

	}

	/**
	 * 产品接入方式查询(有效的接入方式)
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "机构下拉查询")
	public PageResult querySelect(QueryParamBean queryBean) throws SnowException {
		return PbsProdInfoService.getInstance().querySelect(queryBean.getPage());
	}

	/**
	 * 产品信息录入
	 * 
	 * @param updateMap
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "产品信息新增")
	public void savePbsProdInfo(Map<String, UpdateRequestBean> updateMap) throws Exception {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 获取产品信息数据集
		UpdateRequestBean reqPbsProdBean = updateMap.get("pbsProdInfo");
		// 获取商品配置数据集
		UpdateRequestBean reqConfProdBean = updateMap.get("confProdInfo");
		SnowLog.getLogger(this.getClass()).info("=========== STP1 产品重复性验证============");
		// 获取产品信息对象
		PbsProdInfoVO pbsProdInfoVO = new PbsProdInfoVO();
		while (reqPbsProdBean.hasNext()) {
			DataObjectUtils.map2bean(reqPbsProdBean.next(), pbsProdInfoVO);
		}
		/** 获取接入方式，全部的交易类型，全部的账户信息，验证是否已经有该产品 */
		// 获取接入方式
		String accessTypeCode = pbsProdInfoVO.getAccessTypeCode();
		// 获取产品配置信息
		List<Map<String, String>> ConfProdList = reqConfProdBean.getTotalList();
		// 此实体类用于转换页面接收的数据使用
		PbsProdInfo PbsProdInfo = new PbsProdInfo();
		// 设置一个标志位
		boolean flag = true;
		String acctTypeCodeReal;
		// 遍历询得到所有的配置行信息
		outterLoop: for (Map<String, String> map : ConfProdList) {
			PbsProdInfo = DataObjectUtils.map2bean(map, PbsProdInfo.class);
			String txnTypeCode = PbsProdInfo.getTxnTypeCode();// 获取交易类型
			String acctTypeCode = PbsProdInfo.getAcctTypeCode();// 获取账户类型

			// 账户类型可能有多个,循环遍历出来
			String[] sum = acctTypeCode.split(",");
			// 针对A000：全部卡种做特殊验证处理
			if (acctTypeCode.contains(PayProdConstants.ACCT_TYPE_FULL_BANK_CARD)) {
				for (String temp : sum) {
					if (PayProdConstants.ACCT_TYPE_ALL_BANK_CARD.contains(temp)) {
						SnowExceptionUtil
								.throwErrorException("产品交易类型[" + PbsProdInfo.getTxnTypeCodeName() + "]下的账户类型重复，请修改！");
					}
				}
			}
			for (int i = 0; i < sum.length; i++) {
				acctTypeCodeReal = sum[i];
				// 接入方式，交易类型，账户类型，对数据进行重复性验证
				// 针对A000：全部卡种做特殊处理
				acctTypeCodeReal = PayProdConstants.ACCT_TYPE_FULL_BANK_CARD.equals(acctTypeCodeReal)
						? acctTypeCodeReal + "," + PayProdConstants.ACCT_TYPE_ALL_BANK_CARD
						: PayProdConstants.ACCT_TYPE_ALL_BANK_CARD.contains(acctTypeCodeReal)
								? acctTypeCodeReal + "," + PayProdConstants.ACCT_TYPE_FULL_BANK_CARD : acctTypeCodeReal;
				flag = PbsProdInfoService.getInstance().checkAdd(accessTypeCode, txnTypeCode, acctTypeCodeReal);
				// false，说明没有查到该条信息，说明是新产品，跳出循环
				if (flag == true) {
					break outterLoop;
				}
			}
		}
		/** 全部验证完成，如果flag为false则做插入操作，否则提示用户，产品重复 */
		// 只要有一条重复，就算重复
		if (flag == true) {// 说明是重复产品，提示用户
			SnowExceptionUtil.throwErrorException("产品信息重复，请重新配置！重复交易：[" + PbsProdInfo.getTxnTypeCodeName() + "];重复账户：["
					+ PbsProdInfo.getAcctTypeCodeName() + "]");
		}
		// 如果false,则做插入操作
		if (flag == false) {
			// 调用service插入产品信息表
			PbsProdInfoService.getInstance().insertPbsProdInfo(pbsProdInfoVO, sessionUserInfo);
			// 获取产品编号
			String prodId = pbsProdInfoVO.getProdId();
			// 循环插入
			// 遍历询得到所有的配置行信息
			String real;
			for (Map<String, String> map : ConfProdList) {
				PbsProdInfo = DataObjectUtils.map2bean(map, PbsProdInfo.class);
				String txnTypeCode = PbsProdInfo.getTxnTypeCode();// 获取交易类型
				String acctTypeCode = PbsProdInfo.getAcctTypeCode();// 获取账户类型
				// 账户类型可能有多个,循环遍历出来
				String[] sum = acctTypeCode.split(",");
				for (int i = 0; i < sum.length; i++) {
					real = sum[i];
					// 账户关联表全部插入
					PbsProdInfoService.getInstance().insertAccountTypeInfo(pbsProdInfoVO, txnTypeCode, real, prodId,
							sessionUserInfo);
				}
				PbsProdInfoService.getInstance().insertTxnTypeInfo(pbsProdInfoVO, txnTypeCode, prodId, sessionUserInfo);
			}
		}
		/****************** 记录日志 ********************/
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[支付产品管理]--新增,产品编号[prodId]=" + pbsProdInfoVO.getProdId() + "" });
	}

	@SnowDoc(desc = "停用启用支付产品")
	@Action(propagation = Propagation.REQUIRED)
	public void updatePbsProdInfoState(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		UpdateRequestBean reqBean = updateMap.get("pbsProdInfo");// 获取数据集
		// 产品信息
		PbsProdInfoVO pbsProdInfoVO = new PbsProdInfoVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), pbsProdInfoVO);
		}
		PbsProdInfoService.getInstance().updatePbsProdInfoState(pbsProdInfoVO);
		/****************** 记录日志 ********************/
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[支付产品管理]--启用,产品编号[prodId]=" + pbsProdInfoVO.getProdId() + "" });
	}

	@Action
	@SnowDoc(desc = "根据产品号查询签约的商户信息")
	public List<MchtMngVO> querysigned(QueryParamBean queryBean) throws SnowException {
		String qprodId = queryBean.getParameter("qprodId");
		return PbsProdInfoService.getInstance().querysigned(qprodId, queryBean.getPage());

	}

	/**
	 * 对产品信息就行修改
	 * 
	 * @param updateMap
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "产品信息修改")
	public void updatePbsProdInfo(Map<String, UpdateRequestBean> updateMap) throws Exception {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 获取产品信息数据集
		UpdateRequestBean reqPbsProdBean = updateMap.get("pbsProdInfo");
		// 获取商品配置数据集
		UpdateRequestBean reqConfProdBean = updateMap.get("confProdInfo");
		SnowLog.getLogger(this.getClass()).info("=========== STP1 处理产品基本信息 ============");
		// 获取产品信息对象
		PbsProdInfoVO pbsProdInfoVO = new PbsProdInfoVO();
		while (reqPbsProdBean.hasNext()) {
			DataObjectUtils.map2bean(reqPbsProdBean.next(), pbsProdInfoVO);
		}
		/**
		 * 修改操作，要与原来产品比较，如果接入方式、交易、账户都相同，只做update操作;如果不相同，删除原来的交易和账户，从新插入交易、账户，
		 * 产品编号不变
		 */
		// 获取产品编号
		String prodId = pbsProdInfoVO.getProdId();
		// 获取接入方式
		String accessTypeCode = pbsProdInfoVO.getAccessTypeCode();
		// 获取产品配置信息
		List<Map<String, String>> ConfProdList = reqConfProdBean.getTotalList();
		// 此实体类用于转换页面接收的数据使用
		PbsProdInfo PbsProdInfo = new PbsProdInfo();
		// 对于交易类型要做重复判定
		List<String> txnTypeCodeList = new ArrayList();
		// 设置一个标志位
		boolean flag = true;
		String checkaccType;
		// 遍历询得到所有的配置行信息
		outterLoop: for (Map<String, String> map : ConfProdList) {
			PbsProdInfo = DataObjectUtils.map2bean(map, PbsProdInfo.class);
			// 如果数据是删除的就不管了
			String recordState = PbsProdInfo.getRecordState();
			// 如果这条数据为“delete”，则放弃该条数据,继续下次循环
			if ("delete".equals(recordState)) {
				continue;
			}
			String txnTypeCode = PbsProdInfo.getTxnTypeCode();// 获取交易类型
			String acctTypeCode = PbsProdInfo.getAcctTypeCode();// 获取账户类型
			// 账户类型可能有多个,循环遍历出来
			String[] sum = acctTypeCode.split(",");
			// 针对A000：全部卡种做特殊验证处理
			if (acctTypeCode.contains(PayProdConstants.ACCT_TYPE_FULL_BANK_CARD)) {
				for (String temp : sum) {
					if (PayProdConstants.ACCT_TYPE_ALL_BANK_CARD.contains(temp)) {
						SnowExceptionUtil.throwErrorException("产品交易类型[" + PbsProdInfo.getTxnTypeCodeName() + "]下的账户类型重复，请修改！");
					}
				}
			}
			for (int i = 0; i < sum.length; i++) {
				// 获取产品信息对象,用于接收核对返回的数据
				checkaccType = sum[i];
				// 过滤掉本产品的重复性验证
				PbsProdInfoVO pbsProdInfo = new PbsProdInfoVO();
				// 针对A000：全部卡种做特殊处理
				checkaccType = PayProdConstants.ACCT_TYPE_FULL_BANK_CARD.equals(checkaccType)
						? checkaccType + "," + PayProdConstants.ACCT_TYPE_ALL_BANK_CARD
						: PayProdConstants.ACCT_TYPE_ALL_BANK_CARD.contains(checkaccType)
								? checkaccType + "," + PayProdConstants.ACCT_TYPE_FULL_BANK_CARD : checkaccType;
				pbsProdInfo = PbsProdInfoService.getInstance().checkUptHos(accessTypeCode, txnTypeCode, checkaccType);
				if (pbsProdInfo != null) {
					String checkProdId = pbsProdInfo.getProdId();
					if ((checkProdId.trim()).equals(prodId)) {
						// 说明是原来的产品，不算重复,继续验证
						flag = false;
					}
					if (!(checkProdId.trim()).equals(prodId)) {
						// 说明不是原来的产品重复的，提示用户，修改产品重复,跳出循环
						flag = true;
						break outterLoop;
					}
				} else {// 没有找到记录，说明修改了原来信息，则删除原来产品，把修改内容插入到产品中
					flag = false;
					continue;
				}
			}
		}
		if (flag == true) {// 说明是相同产品，只是基本信息更改，做更新操作
			SnowExceptionUtil.throwErrorException("产品信息重复，请重新修改！重复交易：[" + PbsProdInfo.getTxnTypeCodeName() + "];重复账户：["
					+ PbsProdInfo.getAcctTypeCodeName() + "]");
		}
		if (flag == false) {// 如果false,则删除该产品下的交易和账户
			// 调用service删除该产品下的交易和账户
			PbsProdInfoService.getInstance().deleteTxnInfo(prodId);
			PbsProdInfoService.getInstance().deleteAcctInfo(prodId);
			// 更新基本信息表
			PbsProdInfoService.getInstance().updateTxnInfo(pbsProdInfoVO);
			String insertAcct;
			// 遍历询得到所有的配置行信息
			for (Map<String, String> map : ConfProdList) {
				PbsProdInfo = DataObjectUtils.map2bean(map, PbsProdInfo.class);
				// 如果数据是删除的就不管了
				String recordState = PbsProdInfo.getRecordState();
				// 如果这条数据为“delete”，则放弃该条数据,继续下次循环
				if ("delete".equals(recordState)) {
					continue;
				}
				String txnTypeCode = PbsProdInfo.getTxnTypeCode();// 获取交易类型
				String acctTypeCode = PbsProdInfo.getAcctTypeCode();// 获取账户类型
				String[] sum = acctTypeCode.split(",");
				for (int i = 0; i < sum.length; i++) {
					insertAcct = sum[i];
					// 账户关联表全部插入
					PbsProdInfoService.getInstance().insertAccountTypeInfo(pbsProdInfoVO, txnTypeCode, insertAcct,
							prodId, sessionUserInfo);
				}
				PbsProdInfoService.getInstance().insertTxnTypeInfo(pbsProdInfoVO, txnTypeCode, prodId, sessionUserInfo);
			}
		}
		// ****************** 记录日志 ********************//*
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[支付产品管理]--修改,产品编号[prodId]=" + pbsProdInfoVO.getProdId() + "" });
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
	 /**
	  * 根据产品编号查询产品名称
	  * @param bean
	  * @param key
	  * @param request
	  * @return
	  * @throws SnowException
	  */
	 public static String getProdName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		 StringBuffer strBuf= new StringBuffer();
		 if(StringUtil.isEmpty(key)){
	    	   return strBuf.toString();
	       }
		 List<Object> prodList = PbsProdInfoService.getInstance().getProdName(key);
		 if(prodList == null || prodList.size() ==0){
	    	   return strBuf.toString();
	       } 
		 for(Object prodObj : prodList){
			 PbsProdInfoVO prod =(PbsProdInfoVO) prodObj;
	    	   strBuf.append(prod.getProdName()).append(",");
	       }
	       return strBuf.toString().substring(0,strBuf.toString().length()-1);
	 }

	@Action
	@SnowDoc(desc = "产品信息查询")
	public PageResult queryPbsProd(QueryParamBean queryBean) throws SnowException {
		String qprodId = queryBean.getParameter("qprodId");
		String qprodName = queryBean.getParameter("qprodName");
		String mchtId = queryBean.getParameter("mchtId");
		//查询上级商户签订的产品
		PageResult pr = PbsProdInfoService.getInstance().queryPbsProd1(mchtId,queryBean.getPage());
		int i = pr.getTotalCount();  //如果没有查询到结果 返回0
		if(i != 0){
			return pr;  //查询到了结果返回上级商户的签订的产品
		}
		//否则返回 所有的产品信息
		return PbsProdInfoService.getInstance().queryPbsProd(qprodId, qprodName, queryBean.getPage());

	}
}
