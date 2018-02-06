/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.sysConf.process.bean 
 * FileName: OpenAcctOrgan.java
 * Author:   chenqilei
 * Date:     2016年7月18日 下午1:31:29
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月18日下午1:31:29                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.baseParaMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月18日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
@Table("PBS_ACCT_INST_INFO")
public class OpenAcctOrgan {
	@Id
	private String acctInstNo;
	private String acctInstName;
	private String dataState;
	private String crtTlr;
	private String crtDateTime;
	private String lastUpdTlr;
	private String lastUpdDateTime;

	public String getAcctInstNo() {
		return acctInstNo;
	}

	public void setAcctInstNo(String acctInstNo) {
		this.acctInstNo = acctInstNo;
	}

	public String getAcctInstName() {
		return acctInstName;
	}

	public void setAcctInstName(String acctInstName) {
		this.acctInstName = acctInstName;
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

	public void setCrtTlr(String crlTlr) {
		this.crtTlr = crlTlr;
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
