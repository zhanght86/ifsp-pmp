/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.chnlMng.comp 
 * FileName: PagyCategoryName.java
 * Author:   zqy
 * Date:     2016年8月02日 上午10:39:09
 * Description: 通道商户登记    
 * History: 
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zqy           2016年8月01日上午10:39:09                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 名称：支付通道子商户信息信息表 功能：支付通道子商户信息信息表 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月01日 <br>
 * 作者：zqy <br>
 * 说明：<br>
 */
@Table("PAGY_CATEGORY_NAME")
public class PagyCategoryName {
	@Id
	// 行业编码
	private String categoryCode;
	@Id
	// 类目使用方
	private String userCode;
	@Id
	// 级别
	private String categoryLevel;

	// 父行业编码
	private String pCategoryCode;
	// 名词描述
	private String categoryDesc;

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(String categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public String getpCategoryCode() {
		return pCategoryCode;
	}

	public void setpCategoryCode(String pCategoryCode) {
		this.pCategoryCode = pCategoryCode;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
}