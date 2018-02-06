/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.baseParaMng.comp 
 * FileName: BankBinAction.java
 * Author:   chenqilei
 * Date:     2016年7月20日 上午10:54:43
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月20日上午10:54:43                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.baseParaMng.comp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.common.bean.UploadFilebean;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.FormReturnBean;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.baseParaMng.common.constants.BankBinConstants;
import com.ruimin.ifs.pmp.baseParaMng.common.constants.DataStateConstants;
import com.ruimin.ifs.pmp.baseParaMng.process.bean.BankBin;
import com.ruimin.ifs.pmp.baseParaMng.process.service.BankBinService;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月20日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
@ActionResource
public class BankBinAction2 {
	private String selfOrgCode = SysParamUtil.getParam("SELF_ORG_CODE");// 展示地址

	/**
	 * 查询银联卡表中的记录
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action()
	@SnowDoc(desc = "查询记录")
	public PageResult queryByAll(QueryParamBean queryBean) throws SnowException {
		// 获取发卡机构编号
		String issueOrgId = queryBean.getParameter("qissueOrgId");
		// 获取卡BIN号
		String cardBinNo = queryBean.getParameter("qcardBinNo");
		// 获取卡类型
		String cardType = queryBean.getParameter("qcardType");
		return BankBinService.getInstance().queryListTmp(issueOrgId, cardBinNo, cardType, queryBean.getPage());
	}

	/**
	 * 银联卡表修改功能
	 * 
	 * @param updateBean
	 * @throws SnowException
	 */
	@Action()
	@SnowDoc(desc = "修改")
	public void updateBank(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("bankBin");
		// 创建实体类对象
		BankBin bank = new BankBin();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), bank);
		}
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		bank.setLastUpdTlr(sessionUserInfo.getTlrno());
		bank.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		BankBinService.getInstance().updateBank(bank);
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[银联卡表管理]--修改 : 序号[sepNo]=" + bank.getSeqNo() });
	}

	/**
	 * 删除数据
	 * 
	 * @param bank
	 * @throws SnowException
	 */
	@Action()
	@SnowDoc(desc = "删除")
	public void deleteBank(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("bankBin");
		// 创建实体类对象
		BankBin bank = new BankBin();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), bank);
		}
		BankBinService.getInstance().deleteBank(bank);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[银联卡表管理]--删除 : 序号[sepNo]=" + bank.getSeqNo() });
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "导入卡bin")
	public FormReturnBean importBankList(Map<String, UploadFilebean> upFileMap) throws Exception {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 接收导入的文件
		String filePath = upFileMap.get("upFile").getContentMsg();
		List<BankBin> binList = new ArrayList<BankBin>();
		int successCount = 0;
		int ignoreCount = 0;
		String currentDateTime = DateUtil.get14Date();
		BufferedReader reader = null;
		BankBin bank;
		try {
			// 先判断本行机构编号有没有配置，若没有配置则报错返回
			if (StringUtil.isBlank(selfOrgCode)) {
				SnowLog.getLogger(this.getClass()).error("[银联卡表维护]无法执行导入，本行机构编号未配置,请联系系统管理员！");
				SnowExceptionUtil.throwErrorException("[银联卡表维护]无法执行导入，本行机构编号未配置,请联系系统管理员！");
			}
			// 创建BufferedReader流读取文件
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));
			while (reader.ready()) {
				String tmp = reader.readLine();
				// 读到空行则继续下次循环，由while判断决定跳出
				if (StringUtil.isBlank(tmp)) {
					continue;
				}
				if (tmp.startsWith("TOTAL")) {
					break;
				}
				// 把从文件中读取到的数据转换为byte数组的格式 并设置为GBK编码格式
				byte[] tmpByte = tmp.getBytes("GBK");

				// 文件内容格式：机构编号 机构名称 卡号长度 卡bin 是否银联品牌卡 卡种 本期增减状态

				// 本期增减标志-如果为D：本期删除，则不导入,继续读取下一条
				String dataState = new String(tmpByte, 63, 2, "GBK").trim();
				if (StringUtil.isNotBlank(dataState) && "D".equals(dataState)) {
					ignoreCount++;
					continue;
				}
				bank = new BankBin();
				// 读取机构编号
				String issueOrgId = new String(tmpByte, 0, 11, "GBK").trim();
				bank.setIssueOrgId(issueOrgId);

				// 读取机构名称
				String issueName = new String(tmpByte, 12, 30, "GBK").trim();
				bank.setIssueName(issueName);

				// 读取卡号长度
				String cardNoLen = new String(tmpByte, 43, 2, "GBK").trim();
				bank.setCardNoLen(cardNoLen);

				// 读取卡bin号
				String cardBinNo = new String(tmpByte, 46, 12, "GBK").trim();
				bank.setCardBinNo(cardBinNo);

				// 读取是否银联品牌卡标志
				String cupFlag = new String(tmpByte, 59, 1, "GBK").trim();
				if (BankBinConstants.NO_UNIONPAY_BRAND.equals(cupFlag)) {
					bank.setCupFlag("00");
				} else if (BankBinConstants.UNIONPAY_BRAND.equals(cupFlag)) {
					bank.setCupFlag("01");
				}

				// 读取卡种
				String cardType = new String(tmpByte, 61, 1, "GBK").trim();
				// 转换卡种，并根据卡种+配置的本行机构编号判断系统内部账户类型
				if (BankBinConstants.DEBIT_CARD.equals(cardType)) {
					bank.setCardType(BankBinConstants.DEBIT);
					if (bank.getIssueOrgId().equals(selfOrgCode)) {
						bank.setInternalAcctType(BankBinConstants.SELF_DEBIT);
					} else {
						bank.setInternalAcctType(BankBinConstants.OTHERS_DEBIT);
					}
				} else if (BankBinConstants.CREDIT_CARD.equals(cardType)) {
					bank.setCardType(BankBinConstants.CREDIT);
					if (bank.getIssueOrgId().equals(selfOrgCode)) {
						bank.setInternalAcctType(BankBinConstants.SELF_CREDIT);
					} else {
						bank.setInternalAcctType(BankBinConstants.OTHERS_CREDIT);
					}
				} else if (BankBinConstants.QUASI_DEBIT_CARD.equals(cardType)) {
					bank.setCardType(BankBinConstants.QUASI_DEBIT);
					if (bank.getIssueOrgId().equals(selfOrgCode)) {
						bank.setInternalAcctType(BankBinConstants.SELF_CREDIT);
					} else {
						bank.setInternalAcctType(BankBinConstants.OTHERS_CREDIT);
					}
				} else if (BankBinConstants.PREPAY_CARD.equals(cardType)) {
					bank.setCardType(BankBinConstants.PREPAY);
					// 4-预付费卡不标识系统内部账户类型
				}

				// 卡类型长度固定2位
				bank.setCardTypeLen("2");

				// 数据来源-YWB：业务表
				bank.setDataSource("YWB");

				// 数据有效状态00:启用
				bank.setDataState(DataStateConstants.ENABLE);

				// 创建信息
				bank.setCrtTlr(sessionUserInfo.getTlrno());
				bank.setCrtDateTime(currentDateTime);

				successCount++;
				// 生成序号
				bank.setSeqNo(String.valueOf(successCount));

				binList.add(bank);

			}

			// 清空原有表中的所有数据
			BankBinService.getInstance().deleteAll();

			// 批量插入卡表数据
			BankBinService.getInstance().batchAdd(binList);

		} catch (Exception e) {
			SnowLog.getLogger(this.getClass()).error("银联卡表文件导入时异常：", e);
			throw e;
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		// 生成返回页面数据
		List<String> msgList = new ArrayList<String>();

		msgList.add("文件导入完成，文件内容共" + (successCount + ignoreCount) + "条记录");
		msgList.add("成功导入:" + successCount + "条记录");
		msgList.add("忽略:" + ignoreCount + "条记录");
		FormReturnBean bean = new FormReturnBean();
		bean.setSendUrl("/pages/payPmp/pubTool/fileUpload.jsp");
		bean.getReturnAttrMap().put("impFile", upFileMap.get("upFile").getFilename());
		bean.getReturnAttrMap().put("result", true);
		bean.getReturnAttrMap().put("resultMsg", msgList);
		// 打印日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[银联卡表管理]--全量导入银联卡表" });
		return bean;
	}
}
