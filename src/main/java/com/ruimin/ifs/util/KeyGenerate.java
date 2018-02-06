/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: KegG.java
 * Author:   GH
 * Date:     2015年6月1日 下午5:47:56
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.util;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

/**
 * 〈序号产生类〉<br>
 * 
 * @author GH
 */
public class KeyGenerate {

	/**
	 * 
	 * 功能描述: 生成集团商户号<br>
	 * 〈功能详细描述〉
	 * 
	 */
	public synchronized static String generateGroupMchtId() {

		final String min = "1";
		final String max = "9999999";
		String front = "1" + BaseUtil.getCurrentDate();
		DBDao dao = DBDaos.newInstance();
		Integer o = (Integer) dao.selectOne("com.ruimin.ifs.pmp.common.sequences.rql.sequences.queryGroupMchtSer",
				RqlParam.map().set("min", min).set("max", max).set("date", front));

		return front + StringUtils.leftPad(o == null ? "1" : o.toString(), 7, '0');
	}

	/**
	 * 
	 * 功能描述: 生成内部商户号<br>
	 * 〈功能详细描述〉
	 * 
	 */
	public synchronized static String generateMchtId() {

		final String min = "1";
		final String max = "9999999";
		// String fieldName = "substr(mcht_id,10,7)";
		String front = "8" + BaseUtil.getCurrentDate();

		// 16位
		// 8 + 8位日期 + 7位序号
		DBDao dao = DBDaos.newInstance();

		Integer o = (Integer) dao.selectOne("com.ruimin.ifs.pmp.common.sequences.rql.sequences.queryMchtSer",
				RqlParam.map().set("min", min).set("max", max).set("date", front)
		// .set("fieldName", fieldName)
		);

		return front + StringUtils.leftPad(o == null ? "1" : o.toString(), 7, '0');
	}

	public synchronized static String generateMchtXJId() {

		final String min = "1";
		final String max = "9999999";
		// String fieldName = "substr(mcht_id,10,7)";
		String front = "8" + BaseUtil.getCurrentDate();

		// String front = "8"+BaseUtil.getCurrentDateTimeZHCN();
		// 16位
		// 8 + 8位日期 + 7位序号
		DBDao dao = DBDaos.newInstance();

		Integer o = (Integer) dao.selectOne("com.ruimin.ifs.pmp.common.sequences.rql.sequences.queryMchtCheckId",
				RqlParam.map().set("min", min).set("max", max).set("date", front)
		// .set("fieldName", fieldName)
		);
		String s = front + StringUtils.leftPad(o == null ? "1" : o.toString(), 7, '0');
		return s;
	}

	// 特征条件参数管理序号
	public static synchronized String generateParmSeqID() {
		final String max = "99999";
		DBDao dao = DBDaos.newInstance();
		Integer o = (Integer) dao.selectOne("com.ruimin.ifs.pmp.common.sequences.rql.sequences.generateParmSeqID",
				RqlParam.map().set("max", max));
		if (o == null) {
			o = 0;
		}
		return fillString(String.valueOf(o), '0', 2, false);
	}

	public synchronized static String generateSeqId() {

		final String min = "1";
		final String max = "9999999";
		String front = BaseUtil.getCurrentDate();
		// 8位日期 + 7位序号
		DBDao dao = DBDaos.newInstance();

		Integer o = (Integer) dao.selectOne("com.ruimin.ifs.pmp.common.sequences.rql.sequences.querySeqId",
				RqlParam.map().set("min", min).set("max", max).set("date", front));
		String s = front + StringUtils.leftPad(o == null ? "1" : o.toString(), 7, '0');
		return s;
	}

	/**
	 * 
	 * 功能描述: 生成营销活动号<br>
	 * 〈功能详细描述〉
	 * 
	 */
	public synchronized static String generateActNo() {

		final String min = "1";
		final String max = "9999";
		String front = "88";

		// 16位
		// 8 + 8位日期 + 7位序号
		DBDao dao = DBDaos.newInstance();

		Integer o = (Integer) dao.selectOne("com.ruimin.ifs.pmp.common.sequences.rql.sequences.queryActSer",
				RqlParam.map().set("min", min).set("max", max).set("font", front));

		return front + StringUtils.leftPad(o == null ? "1" : o.toString(), 4, '0');
	}

	/**
	 * 生成终端编号
	 * 
	 * @return
	 * @throws CommonException
	 */
	public synchronized static String generateTermId() throws SnowException {
		final String min = "00000001";
		final String max = "99999999";
		DBDao dao = DBDaos.newInstance();
		int count = dao.selectCount("com.ruimin.ifs.term.rql.termInfo.queryCount");
		if (count == 0) {
			return min;
		} else {
			String termId = (String) dao.selectOne("com.ruimin.ifs.term.rql.termInfo.queryTermId");
			String newTermId = String.valueOf(new BigDecimal(termId).add(new BigDecimal("1")));
			newTermId = StringUtils.leftPad(newTermId, 8, "0");
			if (newTermId.equals(max)) {
				newTermId = max;
			}
			return newTermId;
		}
	}

	/**
	 * 字符串填充函数
	 * 
	 * @param string
	 * @param filler
	 * @param totalLength
	 * @param atEnd
	 * @return
	 */
	public static String fillString(String in, char filler, int totalLength, boolean atEnd) {
		String value = in;
		byte[] tempbyte = value.getBytes();
		int currentLength = tempbyte.length;
		int delta = totalLength - currentLength;
		if (delta < 0) {
			value = value.substring(0, totalLength);
		}
		for (int i = 0; i < delta; i++) {
			if (atEnd) {
				value += filler;
			} else {
				value = filler + value;
			}
		}
		return value;
	}

	/**
	 * 随机生成12位批次号
	 */
	public static String batchNo() {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int i = 0;
		while (i < 4) {
			int t = ran.nextInt(9);
			if (sb.indexOf(String.valueOf(t)) == -1) {
				sb.append(t);
				i++;
			}
		}
		return BaseUtil.getCurrentDate() + sb.toString();
	}

	/**
	 * 终端库存编号16位生成 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @return
	 */
	public static String machInvenId() {
		try {
			Long l = UUID.randomUUID().getMostSignificantBits();
			String pk = "" + l;
			String[] pks = pk.split("-");
			if (pks.length > 1) {
				pk = pks[1];
			}
			String day = new SimpleDateFormat("yyyyMM").format(new Date());
			pk = "K" + day + pk;
			if (pk.length() < 16) {
				pk = StringUtil.leftPad(pk, 16, "6");
			} else if (pk.length() > 16) {
				pk = pk.substring(0, 16);
			}
			return pk;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * 功能描述: 生成终端版本号<br>
	 * 〈功能详细描述〉
	 * 
	 */
	public synchronized static String generateTermVer() {

		final String min = "1";
		final String max = "999999";

		// 16位
		// 8 + 8位日期 + 7位序号
		DBDao dao = DBDaos.newInstance();

		Integer o = (Integer) dao.selectOne("com.ruimin.ifs.pmp.common.sequences.rql.sequences.queryTermVer",
				RqlParam.map().set("min", min).set("max", max));

		return StringUtils.leftPad(o == null ? "1" : o.toString(), 6, '0');
	}

	/**
	 * 
	 * 功能描述: 生成终端菜单号<br>
	 * 〈功能详细描述〉
	 * 
	 */
	public synchronized static String generateMenuId(String verId) {

		final String min = "1";
		final String max = "99";

		// 16位
		// 8 + 8位日期 + 7位序号
		DBDao dao = DBDaos.newInstance();

		Integer o = (Integer) dao.selectOne("com.ruimin.ifs.pmp.common.sequences.rql.sequences.queryMenuSer",
				RqlParam.map().set("min", min).set("max", max).set("verId", verId));

		return StringUtils.leftPad(o == null ? "1" : o.toString(), 2, '0');
	}

	/**
	 * 生成银联商户号
	 * 
	 * @param gen
	 * @param areaNo
	 * @param mcc
	 * @return
	 */
	public static synchronized String getMchtNoSeq(String gen, String areaNo, String mcc) {
		DBDao dao = DBDaos.newInstance();
		Integer o = (Integer) dao.selectOne("com.ruimin.ifs.pmp.common.sequences.rql.sequences.queryCupMchtSer",
				RqlParam.map().set("gen", gen).set("areaNo", areaNo).set("mcc", mcc));

		if (o == null) {
			o = 0;
		}
		return fillString(String.valueOf(o), '0', 4, false);
	}

	/**
	 * 生成计费算法编号
	 * 
	 * @param gen
	 * @param areaNo
	 * @param mcc
	 * @return
	 */
	public static synchronized String genDiscId() {
		DBDao dao = DBDaos.newInstance();
		Integer o = (Integer) dao.selectOne("com.ruimin.ifs.pmp.common.sequences.rql.sequences.queryDiscId");

		if (o == null) {
			o = 0;
		}
		return "SF" + fillString(String.valueOf(o), '0', 4, false);
	}

	/**
	 * 生成终端类型编号
	 * 
	 * @return
	 */
	public static synchronized String generateTermType() {
		final String max = "99";
		DBDao dao = DBDaos.newInstance();
		Integer o = (Integer) dao.selectOne("com.ruimin.ifs.pmp.common.sequences.rql.sequences.queryTermTp",
				RqlParam.map().set("max", max));
		if (o == null) {
			o = 0;
		}
		return fillString(String.valueOf(o), '0', 2, false);
	}

	/**
	 * 
	 * 功能描述: 生成内部商户号<br>
	 * 〈功能详细描述〉
	 * 
	 */
	public synchronized static String generateOrgId() {
		final String min = "0000";
		final String max = "9999";
		DBDao dao = DBDaos.newInstance();
		Integer o = (Integer) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.sequences.queryOrgSeq",
				RqlParam.map().set("min", min).set("max", max));
		return fillString(o == null ? "1" : String.valueOf(o), '0', 4, false);
	}

//	public static void main(String[] args) {
//		System.out.println(machInvenId());
//	}

	/**
	 * 生成终端编号
	 * 
	 * @return
	 * @throws CommonException
	 */
	public synchronized static String generateTermParmFormId() throws SnowException {
		final String min = "0001";
		final String max = "9999";
		DBDao dao = DBDaos.newInstance();
		int count = dao.selectCount("com.ruimin.ifs.pmp.term.rql.termParmForm.queryCount");
		if (count == 0) {
			return min;
		} else {
			String parmId = (String) dao.selectOne("com.ruimin.ifs.pmp.term.rql.termParmForm.queryParmId");
			String newParmId = String.valueOf(new BigDecimal(parmId).add(new BigDecimal("1")));
			newParmId = StringUtils.leftPad(newParmId, 4, "0");
			if (newParmId.equals(max)) {
				newParmId = max;
			}
			return newParmId;
		}
	}

}
