/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: BaseUtil.java
 * Author:   GH
 * Date:     2015年8月14日 下午4:35:57
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.util;


import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author GH
 */
public class BaseUtil {

	private static SimpleDateFormat showDateFormat14 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static SimpleDateFormat showDateFormat8 = new SimpleDateFormat("yyyy-MM-dd");

	private static SimpleDateFormat sysDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	private static SimpleDateFormat sysTimeFormat = new SimpleDateFormat("HHmmss");

	private static SimpleDateFormat sysDateFormat8 = new SimpleDateFormat("yyyyMMdd");

	private static SimpleDateFormat showDateFormat14ZHCN = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

	/**
	 * 获得系统当前日期时间 格式:yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		return sysDateFormat.format(new Date());
	}

	/**
	 * 获得系统当前时间 格式: HHmmss
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		return sysTimeFormat.format(new Date());
	}

	/**
	 * 获得系统当前日期 格式: yyyyMMdd
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		return sysDateFormat8.format(new Date());
	}

	/**
	 * 获得向前系统时间用于显示
	 * 
	 * @return
	 */
	public static String getCurrentDateTimeForShow() {
		return showDateFormat14.format(new Date());
	}

	/**
	 * 获得中文系统时间
	 * 
	 * @return
	 */
	public static String getCurrentDateTimeZHCN() {
		return showDateFormat14ZHCN.format(new Date());
	}

	/**
	 * 14位字符串转日期格式字符串
	 */
	public static String convertStringToDateStr(String str) {

		SimpleDateFormat df = new SimpleDateFormat();
		if (StringUtils.isBlank(str) || !(str.trim().length() == 8 || str.trim().length() == 14)) {
			return str;
		}
		if (str.length() == 14) {
			df = sysDateFormat;
		}
		if (str.length() == 8) {
			df = sysDateFormat8;
		}
		Date dt;
		try {
			dt = df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return str;
		}
		return (str.length() == 14) ? showDateFormat14.format(dt) : showDateFormat14.format(dt).substring(0, 10);
	}

	/**
	 * 日期格式字符串转 数字格式字符串 8位格式写完了, 14位没写
	 */
	public static String convertDateStrToNumStr(String str) {

		SimpleDateFormat df = null;
		SimpleDateFormat df1 = null;
		if (StringUtils.isBlank(str) || !(str.length() == 10 || str.length() == 19)) {
			return str;
		}
		if (str.length() == 19) {
			df = showDateFormat14;
			df1 = sysDateFormat;
		}
		if (str.length() == 10) {
			df = showDateFormat8;
			df1 = sysDateFormat8;
		}

		Date dt;
		try {
			dt = df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return str;
		}
		return df1.format(dt);

	}

	/**
	 * 填补字符串
	 * 
	 * @param str
	 * @param fill
	 * @param len
	 * @param isEnd
	 * @return
	 */
	public static String fillString(String str, char fill, int len, boolean isEnd) {
		int fillLen = len - str.getBytes().length;
		if (len <= 0) {
			return str;
		}
		for (int i = 0; i < fillLen; i++) {
			if (isEnd) {
				str += fill;
			} else {
				str = fill + str;
			}
		}
		return str;
	}

	/**
	 * 填补字符串(中文字符扩充)
	 * 
	 * @param str
	 * @param fill
	 * @param len
	 * @param isEnd
	 * @return
	 */
	public static String fillStringForChinese(String str, char fill, int len, boolean isEnd) {
		int num = 0;
		Pattern p = Pattern.compile("^[\u4e00-\u9fa5]");
		for (int i = 0; i < str.length(); i++) {
			Matcher m = p.matcher(str.substring(i, i + 1));
			if (m.find()) {
				num++;
			}
		}
		int fillLen = len - (str.length() + num);
		if (len <= 0) {
			return str;
		}
		for (int i = 0; i < fillLen; i++) {
			if (isEnd) {
				str += fill;
			} else {
				str = fill + str;
			}
		}
		return str;
	}

	// /**
	// * 获得本行及下属机构编号信息
	// * @param brhMap
	// * @return
	// */
	// public static String getBelowBrhInfo(Map<String, String> brhMap) {
	// String belowBrhInfo = "(";
	// Iterator<String> iter = brhMap.keySet().iterator();
	// while(iter.hasNext()) {
	// String brhId = iter.next();
	// belowBrhInfo += "'" + brhId + "'";
	// if(iter.hasNext()) {
	// belowBrhInfo += ",";
	// }
	// }
	// belowBrhInfo += ")";
	// return belowBrhInfo;
	// }

	/**
	 * 获取bigDecimal的值
	 */
	public static BigDecimal strToBD(String num) {
		if (StringUtils.isBlank(num)) {
			num = "0";
		}
		return new BigDecimal(num);
	}

	/**
	 * str减法 num1 - num2
	 */
	public static String subtract(String num1, String num2) {
		BigDecimal n1 = strToBD(num1);
		BigDecimal n2 = strToBD(num2);

		return String.valueOf(n1.subtract(n2));
	}

	/**
	 * 判断两个数字型str的大小
	 * 
	 * @return -1时, num1 < num2; 0时, num1=num2 ; 1时, num1>num2
	 */
	public static int compareValueForStr(String num1, String num2) {
		BigDecimal n1 = strToBD(num1);
		BigDecimal n2 = strToBD(num2);
		return n1.compareTo(n2);
	}

	/**
	 * 获得指定日期的偏移日期
	 * 
	 * @param refDate
	 *            参照日期
	 * @param offSize
	 *            偏移日期
	 * @return
	 */
	public static String getOffSizeDate(String refDate, String offSize) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(Integer.parseInt(refDate.substring(0, 4)), Integer.parseInt(refDate.substring(4, 6)) - 1,
				Integer.parseInt(refDate.substring(6, 8)));
		calendar.add(Calendar.DATE, Integer.parseInt(offSize));
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String retDate = String.valueOf(calendar.get(Calendar.DATE));
		if (Integer.parseInt(month) < 10) {
			month = "0" + month;
		}
		if (Integer.parseInt(retDate) < 10) {
			retDate = "0" + retDate;
		}
		return year + month + retDate;
	}

	/**
	 * 将金额元转分
	 * 
	 * @param str
	 * @return
	 */
	public static String transYuanToFen(String str) {
		if (str == null || "".equals(str.trim()))
			return "0";
		BigDecimal bigDecimal = new BigDecimal(str.trim());
		return bigDecimal.movePointRight(2).toString();
	}

	/**
	 * 将金额分转元
	 * 
	 * @param str
	 * @return
	 */
	public static String transFenToYuan(String str) {
		if (str == null || "".equals(str.trim()))
			return "0";
		BigDecimal bigDecimal = new BigDecimal(str.trim());
		return bigDecimal.movePointLeft(2).toString();
	}

	/**
	 * 将double转string,并保留小数点后指定位数
	 * 
	 * @param num
	 *            需要转换的double数
	 * @param scale
	 *            保留的位数
	 * @return
	 */
	public static String doubleToString(Double num, int scale) {
		return (new BigDecimal(num).divide(new BigDecimal(1.0), scale, BigDecimal.ROUND_HALF_DOWN)).toString();
	}

	/**
	 * 获得指定个数的随机数组合
	 * 
	 * @param len
	 * @return 2010-8-19上午10:51:15
	 */
	public static String getRandomNum(int len) {
		String ran = "";
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			ran += String.valueOf(random.nextInt(10));
		}
		return ran;
	}

	/**
	 * 判断字符串是否全部由数字组成
	 * 
	 * @param str
	 * @return 2010-8-26下午02:20:28
	 */
	public static boolean isMoney(String str) {

		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i)))
				return false;
		}
		return true;
	}

	/**
	 * 判断字符串是否全部由数字组成
	 * 
	 * @param str
	 * @return 2010-8-26下午02:20:28
	 */
	public static boolean isAllDigit(String str) {
		str = str.replace(".", "");
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i)))
				return false;
		}
		return true;
	}

	public static Date getCurrentTs() {
		Date now = new Date();
		return new Timestamp(now.getTime());
	}

	/**
	 * 获得指定个数转换为Double
	 * 
	 * @param len
	 * @return 2010-8-19上午10:51:15
	 */

	public static Double getDValue(String value, Double _default) {
		if (StringUtils.isNotEmpty(value)) {
			return Double.valueOf(value);
		}
		return _default;
	}

	/**
	 * 获得指定个数转换为BigDecimal
	 * 
	 * @param len
	 * @return 2010-8-19上午10:51:15
	 */
	public static BigDecimal getBValue(String value, BigDecimal _default) {
		if (StringUtils.isNotEmpty(value)) {
			try {
				return new BigDecimal(value.trim());
			} catch (Exception ex) {
				return _default;
			}
		} else
			return _default;
	}

	/**
	 * 获得指定个数转换为int
	 * 
	 * @param len
	 * @return 2010-8-19上午10:51:15
	 */
	public static Integer getInt(String value, int _default) {
		if (StringUtils.isNotEmpty(value)) {
			try {
				return Integer.parseInt(value.trim());
			} catch (Exception ex) {
				return _default;
			}
		} else
			return _default;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String formate8Date(String str) {
		if (str.length() == 8) {
			return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8);
		}
		return str;
	}

	public static String getCurrDate(String format) {
		SimpleDateFormat formater = new SimpleDateFormat(format);
		return formater.format(new Date());
	}

	/**
	 * 16进制
	 * 
	 * @param c
	 * @return 2011-7-27上午11:50:28
	 */
	private static byte toByte(char c) {
		byte b = (byte) "0123456789abcdef".indexOf(c);
		return b;
	}

	/**
	 * 16进制转BCD
	 * 
	 * @param hex
	 * @return 2011-7-27上午11:49:20
	 */
	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	/**
	 * BCD转成16进制
	 * 
	 * @param bArray
	 * @return 2011-7-27上午11:47:56
	 */
	public static String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	public static String urlToRoleId(String url) {
		try {
			String[] array = url.split("/");
			String[] result = array[array.length - 1].split("\\.");
			String res = result[0];
			return res;
		} catch (Exception e) {
			return url;
		}
	}

	public static String transMoney(double n) {
		try {
			String[] fraction = { "角", "分" };
			String[] digit = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
			String[][] unit = { { "元", "万", "亿" }, { "", "拾", "佰", "仟" } };

			String head = n < 0 ? "负" : "";
			n = Math.abs(n);

			String s = "";

			for (int i = 0; i < fraction.length; i++) {
				s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
			}
			if (s.length() < 1) {
				s = "整";
			}
			int integerPart = (int) Math.floor(n);

			for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
				String p = "";
				for (int j = 0; j < unit[1].length && n > 0; j++) {
					p = digit[integerPart % 10] + unit[1][j] + p;
					integerPart = integerPart / 10;
				}
				s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
			}
			return head + s.replaceAll("(零.)*零元", "元").replaceAll("(零.)+", "").replaceAll("(零.)+", "零")
					.replaceAll("^整$", "零元整");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String insertString(String src, String fill) {
		String tmp = "";
		for (int i = 0; i < src.length(); i++) {
			tmp += fill;
			tmp += src.substring(i, i + 1);
		}
		return tmp;
	}

	/**
	 * 
	 * 功能描述: 正则表达式校验<br>
	 * 〈功能详细描述〉
	 *
	 * @param regex
	 * @param str
	 * @return
	 */
	public static boolean regexVil(String regex, String str) {
		Pattern pat = Pattern.compile(regex);
		Matcher m = pat.matcher(str);
		return m.matches();
	}

	public static Object fillObject(Object object, Class cls) {
		try {
			Method[] methods = cls.getMethods();
			for (Method m : methods) {
				String mName = m.getName();
				if (mName.startsWith("get")) {
					Object value = (Object) m.invoke(object, new Class[] {});
					String mkey = mName.substring(3, mName.length());
					if (value == null) {
						for (Method me : methods) {
							String meName = me.getName();
							String mekey = meName.substring(3, meName.length());
							if (meName.startsWith("set") && mkey.equals(mekey)) {
								me.invoke(object, "");
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * 生成银联商户号
	 * 
	 * added by mj 2015-11-20
	 */
	public static String genCupId(String url) {
		try {
			String[] array = url.split("/");
			String[] result = array[array.length - 1].split("\\.");
			String res = result[0];
			return res;
		} catch (Exception e) {
			return url;
		}
	}
}
