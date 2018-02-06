/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.accessTypeMng.process.bean 
 * FileName: TxnTypeInfoVo.java
 * Author:   zrx
 * Date:     2016年7月15日 下午3:03:22
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月15日下午3:03:22                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.sysConf.process.bean;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月15日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
public class TxnTypeInfoVo {

	private boolean select = false;
	private String txnTypeCode;
	private String txnTypeName;

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public String getTxnTypeCode() {
		return txnTypeCode;
	}

	public void setTxnTypeCode(String txnTypeCode) {
		this.txnTypeCode = txnTypeCode;
	}

	public String getTxnTypeName() {
		return txnTypeName;
	}

	public void setTxnTypeName(String txnTypeName) {
		this.txnTypeName = txnTypeName;
	}

}
