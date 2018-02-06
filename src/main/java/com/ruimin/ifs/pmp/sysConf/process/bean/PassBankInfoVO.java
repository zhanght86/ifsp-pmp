/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.sysConf.process.bean 
 * FileName: PassBankInfoVO.java
 * Author:   Administrator
 * Date:     2016年7月25日 下午5:11:30
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   Administrator           2016年7月25日下午5:11:30                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.sysConf.process.bean;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月25日 <br>
 * 作者：Administrator <br>
 * 说明：<br>
 */
public class PassBankInfoVO extends PassBankVO {
	private String passName; // 通道名称

	/**
	 * @return the passName
	 */
	public String getPassName() {
		return passName;
	}

	/**
	 * @param passName
	 *            the passName to set
	 */
	public void setPassName(String passName) {
		this.passName = passName;
	}

}
