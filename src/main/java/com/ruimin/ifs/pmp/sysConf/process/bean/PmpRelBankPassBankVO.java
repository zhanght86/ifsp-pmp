/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.sysConf.process.bean 
 * FileName: PmpRelBankPassBankVo.java
 * Author:   ZLJ
 * Date:     2016年7月14日 下午5:36:02
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   ZLJ           2016年7月14日下午5:36:02                     1.0                  
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
 * 日期：2016年7月14日 <br>
 * 作者：ZLJ <br>
 * 说明：产品银行与通道银行 bean<br>
 */
@Table("PMP_REL_BANK_PASS_BANK")
public class PmpRelBankPassBankVO implements Serializable {

	/**
	 */
	private static final long serialVersionUID = -808329141345387003L;
	@Id
	private String relNo;// 记录编号
	private String prodBankNo;// 产品银行编号
	private String passNo;// 通道编号
	private String passBankNo;// 通道银行编号
	private String dataState;// 有效状态
	private String crtTlr;// 创建柜员
	private String crtDateTime;// 创建时间
	private String lastUpdTlr;// 最近更新柜员
	private String lastUpdDateTime;// 最近更新时间

	public String getRelNo() {
		return relNo;
	}

	public void setRelNo(String relNo) {
		this.relNo = relNo;
	}

	public String getProdBankNo() {
		return prodBankNo;
	}

	public void setProdBankNo(String prodBankNo) {
		this.prodBankNo = prodBankNo;
	}

	public String getPassNo() {
		return passNo;
	}

	public void setPassNo(String passNo) {
		this.passNo = passNo;
	}

	public String getPassBankNo() {
		return passBankNo;
	}

	public void setPassBankNo(String passBankNo) {
		this.passBankNo = passBankNo;
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
