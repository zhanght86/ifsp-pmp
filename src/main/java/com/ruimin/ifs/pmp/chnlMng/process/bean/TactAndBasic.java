/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.chnlMng.process.bean 
 * FileName: TackAndBasic.java
 * Author:   chenqilei
 * Date:     2016年7月28日 上午10:42:17
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月28日上午10:42:17                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月28日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
@Table("PAGY_TACTICS_REL")
public class TactAndBasic {
	@Id
	private String pagyNo; // 通道编号
	@Id
	private String ttsCode; // 策略编号
	private String priorityVul; // 优先级

	public String getPagyNo() {
		return pagyNo;
	}

	public void setPagyNo(String pagyNo) {
		this.pagyNo = pagyNo;
	}

	public String getTtsCode() {
		return ttsCode;
	}

	public void setTtsCode(String ttsCode) {
		this.ttsCode = ttsCode;
	}

	public String getPriorityVul() {
		return priorityVul;
	}

	public void setPriorityVul(String priorityVul) {
		this.priorityVul = priorityVul;
	}

}
