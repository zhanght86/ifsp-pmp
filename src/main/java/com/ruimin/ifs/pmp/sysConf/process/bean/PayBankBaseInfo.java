/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.sysConf.process.bean 
 * FileName: PmpBankInfo.java
 * Author:   ZLJ
 * Date:     2016年7月11日 上午11:04:24
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   ZLJ           2016年7月11日上午11:04:24                     1.0                  
 *===============================================================================================
 */

package com.ruimin.ifs.pmp.sysConf.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

import java.io.Serializable;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月11日 <br>
 * 作者：ZLJ <br>
 * 说明：产品银行信息<br>
 */
@Table("PAY_BANK_BASE_INFO")
public class PayBankBaseInfo implements Serializable {

	/**
	 */
	private static final long serialVersionUID = -6608037478030562423L;

	@Id
	private String bankNo;// 银行编号
	private String bankName;// 银行名称
	private String showSer;// 显示顺序
	private String debitCreditFlag;// 借贷标志
	private String selfOtherFlag;// 本行他行标志
	private String picId;// 图片ID
	private String picPath;// 图片路径
	private String dataState;// 数据有效状态
	private String crtTlr;// 创建柜员
	private String crtDateTime;// 创建日期时间
	private String lastUpdTlr;// 最近更新柜员
	private String lastUpdDateTime;// 最近更新时间

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getShowSer() {
		return showSer;
	}

	public void setShowSer(String showSer) {
		this.showSer = showSer;
	}

	public String getDebitCreditFlag() {
		return debitCreditFlag;
	}

	public void setDebitCreditFlag(String debitCreditFlag) {
		this.debitCreditFlag = debitCreditFlag;
	}

	public String getSelfOtherFlag() {
		return selfOtherFlag;
	}

	public void setSelfOtherFlag(String selfOtherFlag) {
		this.selfOtherFlag = selfOtherFlag;
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
	}

	public String getCrtTlr() {
		return crtTlr;
	}

	public void setCrtTlr(String crtTlr) {
		this.crtTlr = crtTlr;
	}

	public String getCrtDateTime() {
		return crtDateTime;
	}

	public void setCrtDateTime(String crtDateTime) {
		this.crtDateTime = crtDateTime;
	}

	public String getLastUpdTlr() {
		return lastUpdTlr;
	}

	public void setLastUpdTlr(String lastUpdTlr) {
		this.lastUpdTlr = lastUpdTlr;
	}

	public String getLastUpdDateTime() {
		return lastUpdDateTime;
	}

	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}

}
