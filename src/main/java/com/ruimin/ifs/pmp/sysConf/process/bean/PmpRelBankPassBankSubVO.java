/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.sysConf.process.bean 
 * FileName: PmpRelBankPassBankSubVO.java
 * Author:   ZLJ
 * Date:     2016年7月15日 下午8:29:16
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   ZLJ           2016年7月15日下午8:29:16                     1.0                  
 *===============================================================================================
 */

package com.ruimin.ifs.pmp.sysConf.process.bean;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月15日 <br>
 * 作者：ZLJ <br>
 * 说明：<br>
 */
public class PmpRelBankPassBankSubVO extends PmpRelBankPassBankVO {
	public String bankName;// 通道银行名称

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}
