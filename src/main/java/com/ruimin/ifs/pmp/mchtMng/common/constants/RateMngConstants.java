/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.mchtMng.common.constants 
 * FileName: RateMngConstants.java
 * Author:   zrx
 * Date:     2016年7月19日 下午10:10:37
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月19日下午10:10:37                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mchtMng.common.constants;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月19日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
public class RateMngConstants {
	/************ 数据有效状态 ************/

	/**
	 * 费率状态：00-启用
	 */
	public static final String RATE_STATE_NORMAL = "00";

	/**
	 * 费率状态：99-停用
	 */
	public static final String RATE_STATE_ABNORMAL = "99";

	/**
	 * 分段标志：00-默认
	 */
	public static final String RATE_SECTION_TYPE = "00";

	/**
	 * 分段标志：01-按金额
	 */
	public static final String RATE_SECTION_TYPE1 = "01";

	/**
	 * 收费类型：00-固定金额
	 */
	public static final String RATE_CHARGE_TYPE = "00";

	/**
	 * 收费类型：01-固定费率
	 */
	public static final String RATE_CHARGE_TYPE1 = "01";

}
