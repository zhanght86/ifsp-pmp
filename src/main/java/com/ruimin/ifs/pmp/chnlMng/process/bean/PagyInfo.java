/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.chnlMng.comp 
 * FileName: PagyInfo.java
 * Author:   zqy
 * Date:     2016年7月27日 上午10:39:09
 * Description: 通道信息管理     
 * History: 
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zqy           2016年7月27日上午10:39:09                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 名称：通道信息管理 功能：通道信息管理 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月27日 <br>
 * 作者：zqy <br>
 * 说明：<br>
 */
@Table("PAGY_BASE_INFO")
public class PagyInfo {
	@Id
	// 通道编号
	private String pagyNo;
	// 通道名称
	private String pagyName;
	// 通道状态
	private String pagyStat;
	// 开通日期
	private String pagyOpenDate;
	// 备注
	private String remark;

	// 创建柜员
	private String crtTlr;
	// 创建日期时间
	private String crtDateTime;
	// 最近更新柜员
	private String lastUpdTlr;
	// 最近更新日期时间
	private String lastUpdDateTime;

	public String getPagyNo() {
		return pagyNo;
	}

	public void setPagyNo(String pagyNo) {
		this.pagyNo = pagyNo;
	}

	public String getPagyName() {
		return pagyName;
	}

	public void setPagyName(String pagyName) {
		this.pagyName = pagyName;
	}

	public String getPagyStat() {
		return pagyStat;
	}

	public void setPagyStat(String pagyStat) {
		this.pagyStat = pagyStat;
	}

	public String getPagyOpenDate() {
		return pagyOpenDate;
	}

	public void setPagyOpenDate(String pagyOpenDate) {
		this.pagyOpenDate = pagyOpenDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
