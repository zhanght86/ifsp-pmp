/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.term.process.bean 
 * FileName: MchtBaseInfo.java
 * Author:   wangyl
 * Date:     2016年8月19日 下午3:27:33
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   wangyl           2016年8月19日下午3:27:33                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.term.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月19日 <br>
 * 作者：wangyl <br>
 * 说明：<br>
 */
@Table("PBS_MCHT_BASE_INFO")
public class MchtBaseInfo {
	@Id
	private String mchtId;
	private String mchtType;
	private String mchtName;
	private String mchtSimpleName;
	private String mchtStat;

	/**
	 * @return the mchtId
	 */
	public String getMchtId() {
		return mchtId;
	}

	/**
	 * @param mchtId
	 *            the mchtId to set
	 */
	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}

	/**
	 * @return the mchtType
	 */
	public String getMchtType() {
		return mchtType;
	}

	/**
	 * @param mchtType
	 *            the mchtType to set
	 */
	public void setMchtType(String mchtType) {
		this.mchtType = mchtType;
	}

	/**
	 * @return the mchtName
	 */
	public String getMchtName() {
		return mchtName;
	}

	/**
	 * @param mchtName
	 *            the mchtName to set
	 */
	public void setMchtName(String mchtName) {
		this.mchtName = mchtName;
	}

	/**
	 * @return the mchtSimpleName
	 */
	public String getMchtSimpleName() {
		return mchtSimpleName;
	}

	/**
	 * @param mchtSimpleName
	 *            the mchtSimpleName to set
	 */
	public void setMchtSimpleName(String mchtSimpleName) {
		this.mchtSimpleName = mchtSimpleName;
	}

	/**
	 * @return the mchtStat
	 */
	public String getMchtStat() {
		return mchtStat;
	}

	/**
	 * @param mchtStat
	 *            the mchtStat to set
	 */
	public void setMchtStat(String mchtStat) {
		this.mchtStat = mchtStat;
	}

}
