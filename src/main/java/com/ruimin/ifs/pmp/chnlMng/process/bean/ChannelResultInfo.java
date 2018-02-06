/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.chnlMng.process.bean 
 * FileName: ChannelInfo.java
 * Author:   zrx
 * Date:     2016年7月21日 上午10:07:53
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月21日上午10:07:53                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.bean;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月21日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
/**
 * 此实体类，用于渠道权限接收参数使用
 * 
 * @author Administrator
 *
 */
public class ChannelResultInfo {
	private String payTxnTypeId;
	private String payTxnTypeName;
	private String acctTypeNo;
	private String acctTypeName;
	private String recordState;// 数据状态

	public String getRecordState() {
		return recordState;
	}

	public void setRecordState(String recordState) {
		this.recordState = recordState;
	}

	public String getPayTxnTypeId() {
		return payTxnTypeId;
	}

	public void setPayTxnTypeId(String payTxnTypeId) {
		this.payTxnTypeId = payTxnTypeId;
	}

	public String getPayTxnTypeName() {
		return payTxnTypeName;
	}

	public void setPayTxnTypeName(String payTxnTypeName) {
		this.payTxnTypeName = payTxnTypeName;
	}

	public String getAcctTypeNo() {
		return acctTypeNo;
	}

	public void setAcctTypeNo(String acctTypeNo) {
		this.acctTypeNo = acctTypeNo;
	}

	public String getAcctTypeName() {
		return acctTypeName;
	}

	public void setAcctTypeName(String acctTypeName) {
		this.acctTypeName = acctTypeName;
	}

}
