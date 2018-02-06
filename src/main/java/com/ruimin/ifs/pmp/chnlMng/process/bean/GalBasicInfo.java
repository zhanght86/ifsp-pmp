/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.chnlMng.process.bean 
 * FileName: GalBasicInfo.java
 * Author:   chenqilei
 * Date:     2016年7月28日 上午9:55:23
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月28日上午9:55:23                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.bean;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月28日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
// 仅仅用于存储数据使用
public class GalBasicInfo {
	private String pagyNo; // 通道编号
	private String pagyName; // 通道名称
	private String pagyOpenDate; // 开通日期
	private String pagyStat; // 通道状态
	private String remark; // 备注
	private String crtTlr; // 创建柜员
	private String crtDateTime; // 创建日期时间
	private String lastUpdTlr; // 最近更新柜员
	private String lastUpdDateTime; // 最近更新日期时间
	private String priorityVul;
	private String ttsCodeCount;

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

	public String getPagyOpenDate() {
		return pagyOpenDate;
	}

	public void setPagyOpenDate(String pagyOpenDate) {
		this.pagyOpenDate = pagyOpenDate;
	}

	public String getPagyStat() {
		return pagyStat;
	}

	public void setPagyStat(String pagyStat) {
		this.pagyStat = pagyStat;
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

	public String getPriorityVul() {
		return priorityVul;
	}

	public void setPriorityVul(String priorityVul) {
		this.priorityVul = priorityVul;
	}

	public String getTtsCodeCount() {
		return ttsCodeCount;
	}

	public void setTtsCodeCount(String ttsCodeCount) {
		this.ttsCodeCount = ttsCodeCount;
	}

}
