/*
pmp * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.mchtMng.process.bean 
 * FileName: MchtContractAcctRate.java
 * Author:   zrx
 * Date:     2016年7月28日 上午10:11:48
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月28日上午10:11:48                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mchtMng.process.bean;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月28日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */

public class MchtContractIdProductAll {

	// 商户合同编号

	private String prodId;
	// 产品编号

	private String prodName;

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public MchtContractIdProductAll(String prodId, String prodName) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
	}

	public MchtContractIdProductAll() {

	}

}
