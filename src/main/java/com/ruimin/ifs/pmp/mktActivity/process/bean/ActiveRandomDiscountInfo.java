/**
 * Project Name:ifsp-payactive
 * File Name:ActiveRandomDiscountInfo.java
 * Package Name:com.ruim.ifsp.active.bean.dto
 * Date:2017年12月5日下午2:41:18
 * Copyright (c) 2017, ECT Software All Rights Reserved.
 *
*/

package com.ruimin.ifs.pmp.mktActivity.process.bean;

/**
 * ClassName:ActiveRandomDiscountInfo <br/>
 * Date: 2017年12月5日 下午2:41:18 <br/>
 * 
 * @author lining
 * @version
 * @since JDK 1.7
 * @see
 */
public class ActiveRandomDiscountInfo {
	private String id;
	private String amt;
	private String num;
	public ActiveRandomDiscountInfo() {
	}
	public ActiveRandomDiscountInfo(String id, String amt, String num) {
		this.id = id;
		this.amt = amt;
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

}
