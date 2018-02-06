package com.ruimin.ifs.pmp.sysConf.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 
 * Copyright (C), 2016-2016, 上海睿民互联网科技有限公司 FileName: AccountType.java类 Author:
 * Cheng Date: 2016年7月14日下午4:45:42 Description: TODO History: //修改记录
 * <author> <time> <version> <desc> zhaodk 2016年7月14日下午4:45:42 0.1
 */

@Table("PAY_ACCT_TYPE_BASE_INFO")
public class AccountType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String acctTypeNo;// 账户类型编号
	private String acctTypeName; // 账户类型名
	private String acctTypeDesc;// 账户类型描述
	private String acctTypeStat; // 账户的有效状态
	private String crtTlr; // 创建操作员
	private String crtDateTime; // 创建时间
	private String lastUpdTlr; // 更新操作员
	private String lastUpdDateTime; // 更新时间

	public String getAcctTypeNo() {
		return acctTypeNo;
	}

	public void setAcctTypeNo(String acctTypeNo) {
		this.acctTypeNo = acctTypeNo;
	}

	public String getAcctTypeStat() {
		return acctTypeStat;
	}

	public void setAcctTypeStat(String acctTypeStat) {
		this.acctTypeStat = acctTypeStat;
	}

	public String getAcctTypeName() {
		return acctTypeName;
	}

	public void setAcctTypeName(String acctTypeName) {
		this.acctTypeName = acctTypeName;
	}

	public String getAcctTypeDesc() {
		return acctTypeDesc;
	}

	public void setAcctTypeDesc(String acctTypeDesc) {
		this.acctTypeDesc = acctTypeDesc;
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

	public AccountType() {
	}

	public AccountType(String acctTypeNo, String acctTypeName, String acctTypeDesc, String acctTypeStat, String crtTlr,
			String crtDateTime, String lastUpdTlr, String lastUpdDateTime) {
		super();
		this.acctTypeNo = acctTypeNo;
		this.acctTypeName = acctTypeName;
		this.acctTypeDesc = acctTypeDesc;
		this.acctTypeStat = acctTypeStat;
		this.crtTlr = crtTlr;
		this.crtDateTime = crtDateTime;
		this.lastUpdTlr = lastUpdTlr;
		this.lastUpdDateTime = lastUpdDateTime;
	}

}
