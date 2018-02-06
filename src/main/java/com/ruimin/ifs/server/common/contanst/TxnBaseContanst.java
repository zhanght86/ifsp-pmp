/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: TxnBaseContanst.java
 * Author:   GH
 * Date:     2015年8月28日 上午11:07:23
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.server.common.contanst;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author GH
 */
public class TxnBaseContanst {

	/** 渠道号: 62 */
	public final static String CHNl_NO = "000004";
	/** 渠道62的操作员: CHNl_62 */
	public final static String CHNl_62 = "CHNl_62";

	/** 审批结果: 0- 审核通过 */
	public final static String VER_STAT_0 = "0";
	/** 审批结果: 1- 审核拒绝 */
	public final static String VER_STAT_1 = "1";
	/** 审批结果: 2- 已受理(未审核) */
	public final static String VER_STAT_2 = "2";
	/** 审批结果: 3 -受理失败 */
	public final static String VER_STAT_3 = "3";

	/** 审批类型: 0- 签约审核 */
	public final static String VER_TYPE_0 = "0";
	/** 审批类型: 1- 银行账户变更 */
	public final static String VER_TYPE_1 = "1";
	/** 审批类型: 2 -营销活动 */
	public final static String VER_TYPE_2 = "2";

	/** 返回码: 00000-成功 */
	public final static String CODE_00000 = "00000";
	/** 返回码: 00006-失败 */
	public final static String CODE_00006 = "00006";

	/** 返回码: 62001 */
	public final static String CODE_62001 = "62001";
	/** 返回码: 62002 */
	public final static String CODE_62002 = "62002";
	/** 返回码: 62003 */
	public final static String CODE_62003 = "62003";
	/** 返回码: 62004 */
	public final static String CODE_62004 = "62004";
	/** 返回码: 62005 */
	public final static String CODE_62005 = "62005";
	/** 返回码: 62006 */
	public final static String CODE_62006 = "62006";
	/** 返回码: 62007 */
	public final static String CODE_62007 = "62007";
	/** 返回码: 62008 */
	public final static String CODE_62008 = "62008";
	/** 返回码: 62009 */
	public final static String CODE_62009 = "62009";
	/** 返回码: 62010 */
	public final static String CODE_62010 = "62010";
	/** 返回码: 62011 */
	public final static String CODE_62011 = "62011";
	/** 返回码: 62012 */
	public final static String CODE_62012 = "62012";
	/** 返回码: 62013 */
	public final static String CODE_62013 = "62013";

	/** 返回信息 */
	public final static Map<String, String> codeMap = new HashMap<String, String>();

	/** 交易状态: 00-成功 */
	public final static String STAT_00 = "00";
	/** 交易状态: 01-被拒绝 */
	public final static String STAT_01 = "01";
	/** 交易状态: 02-受理成功 */
	public final static String STAT_02 = "02";
	/** 交易状态: 03-处理中 */
	public final static String STAT_03 = "03";
	/** 交易状态: 99-失败 */
	public final static String STAT_99 = "99";

	/** 交易码: 621001 - 商户签约 */
	public final static String TXN_TYPE_621001 = "621001";
	/** 交易码: 621002 - 修改银行账户 */
	public final static String TXN_TYPE_621002 = "621002";
	/** 交易码: 621003 - 签约查询 */
	public final static String TXN_TYPE_621003 = "621003";

	/** 交易码: 613002 - 审核结果通知 */
	public final static String TXN_TYPE_613002 = "613002";

	static {
		codeMap.put(CODE_00000, "交易成功.");
		codeMap.put(CODE_00006, "交易出现异常，详情请咨询.");
		// codeMap.put(CODE_62001, "字段为空或数据格式不符.");
		codeMap.put(CODE_62002, "未找到对应商户记录.");
		codeMap.put(CODE_62003, "该商户已冻结, 不能进行该操作.");
		codeMap.put(CODE_62004, "该商户已注销, 不能进行该操作.");
		codeMap.put(CODE_62005, "该商户处于冻结待审核状态, 不能进行该操作.");
		codeMap.put(CODE_62006, "该商户处于注销待审核状态, 不能进行该操作.");
		codeMap.put(CODE_62007, "该商户处于恢复待审核状态, 不能进行该操作.");
		codeMap.put(CODE_62008, "该商户处于修改待审核状态, 不能进行该操作.");
		codeMap.put(CODE_62009, "原交易不存在.");
		codeMap.put(CODE_62010, "该商户处于添加待审核状态, 不能进行该操作.");
		codeMap.put(CODE_62011, "该商户处于新增被拒绝状态, 不能进行该操作.");
		codeMap.put(CODE_62011, "该商户处于新增被拒绝状态, 不能进行该操作.");
		codeMap.put(CODE_62012, "该商户处于自助申请待完善状态, 不能进行该操作.");
		codeMap.put(CODE_62013, "该商户处于自助申请被拒绝状态, 不能进行该操作.");
	}

}
