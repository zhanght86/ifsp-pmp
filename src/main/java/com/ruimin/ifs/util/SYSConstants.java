/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: SqlConstants.java
 * Author:   zhaodk
 * Date:     2015年11月11日 下午5:26:12
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>          <time>                 <version>      <desc>
 * 修改人姓名：zhaodk    修改时间：2015年11月11日 下午5:26:12    版本号：1.0         描述：
 */
package com.ruimin.ifs.util;

/**
 * 系统基础常量<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author zhaodk
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SYSConstants {
	public class ImpBadCardInf_STATUS_Constants {

	}

	/**
	 * 
	 * 系统SQL模板<br>
	 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
	 * 
	 * @author zhaodk
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public interface SQL_Constants {
		/**
		 * 二维码类型查询
		 */
		public final String DataQrcTypeInfo_queryList = "com.ruimin.ifs.qrc.qrcTypeInfo.rql.DataQrcTypeInfo.queryList";
		/**
		 * 获取当前维码类型最大编号ID
		 */
		public final String DataQrcTypeInfo_queryMaxQrcTypeNo = "com.ruimin.ifs.qrc.qrcTypeInfo.rql.DataQrcTypeInfo.queryMaxQrcTypeNo";
		/**
		 * 二维码类型查询
		 */
		public final String DataQrcChannelInfo_queryList = "com.ruimin.ifs.qrc.qrcTypeInfo.rql.DataQrcChannelInfo.queryList";
		/**
		 * 获取当前维码类型最大编号ID
		 */
		public final String DataQrcChannelInfo_queryMaxQrcTypeNo = "com.ruimin.ifs.qrc.qrcTypeInfo.rql.DataQrcChannelInfo.queryMaxQrcTypeNo";

		/**
		 * 二维码渠道权限管理查询
		 */
		public final String DataQrcChannelAuthorityInfo_queryList = "com.ruimin.ifs.qrc.qrcTypeInfo.rql.DataQrcChannelAuthorityInfo.queryList";

		/**
		 * 获取当前二维码权限最大编号ID
		 */
		public final String DataQrcChannelAuthorityInfo_queryMaxQrcTypeNo = "com.ruimin.ifs.qrc.qrcTypeInfo.rql.DataQrcChannelAuthorityInfo.queryMaxQrcTypeNo";
		/**
		 * 安保问题维护查询
		 */
		public final String TblSecQa_queryList = "com.ruimin.ifs.bbs.confMng.rql.TblSecQa.queryList";
		/**
		 * 获取当前安保问题维护最大编号ID
		 */
		public final String TblSecQa_queryMaxQrcTypeNo = "com.ruimin.ifs.bbs.confMng.rql.TblSecQa.queryMaxQrcTypeNo";

		/**
		 * 风控规则查询
		 */
		public final String TblRiskRule_queryList = "com.ruimin.ifs.bbs.confMng.rql.tblRiskRule.queryList";

		/**
		 * 风控规则查询最大ID
		 */
		public final String TblRiskRule_queryMaxQrcTypeNo = "com.ruimin.ifs.bbs.confMng.rql.tblRiskRule.queryMaxQrcTypeNo";

		/**
		 * 风控规则查询
		 */
		public final String TblRiskRule_queryList1 = "com.ruimin.ifs.bbs.confMng.rql.tblRiskRule.queryList1";

		/**
		 * 获取当前风控规则最大编号ID
		 */
		// public final String TblRiskRule_queryMaxQrcTypeNo =
		// "com.ruimin.ifs.bbs.confMng.rql.tblRiskRule.queryRiskCtrCd";
		public final String ImpBadCardInf_queryList = null;
		public final String TblTxnCfg_queryList = null;
		public final String TblPartyIsInf_queryMaxPartyCd = "com.ruimin.ifs.pmp.mchtMark.rql.tblPartyIsInf.queryMaxPartyCd";
		public final String TblPartyInf_queryMaxPartyCd = "com.ruimin.ifs.pmp.mchtMark.rql.tblPartyInf.queryMaxPartyCd";

	}

	/**
	 * 活动编号查询最大ID
	 */
	public final String TblPartyIsInf_queryList = "com.ruimin.ifs.bbs.confMng.rql.tblPartyIsInf.queryList";

	public final String ImpBadCardInf_queryList = null;
	public final String TblTxnCfg_queryList = null;

	/**
	 * 
	 * 系统基础状态<br>
	 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
	 * 
	 * @author MS
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public interface STATUS_Constants {
		/**
		 * 有效
		 */
		public final String STATUS_00 = "00";
		/**
		 * 无效
		 */
		public final String STATUS_01 = "01";
		/**
		 * 启用
		 */
		public final String QRC_TYPE_SAT_00 = "00";
		/**
		 * 停用
		 */
		public final String QRC_TYPE_SAT_99 = "99";

	}

	/**
	 * 
	 * 系统安保问题维护状态<br>
	 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
	 * 
	 * @author zhaodk
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public interface TblSecQa_STATUS_Constants {
		/**
		 * 有效
		 */
		public final String STATUS_0 = "0";
		/**
		 * 无效
		 */
		public final String STATUS_1 = "1";
	}
}
