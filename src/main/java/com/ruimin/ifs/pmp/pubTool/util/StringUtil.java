package com.ruimin.ifs.pmp.pubTool.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类型
 * 
 * @author Administrator
 *
 */
public class StringUtil {
	public static final int FRONT = 0;

	public static final int BACK = 1;

	public static String LINE = new String(new byte[] { 0x0D, 0x0A });

	
	//public static final String FILT_REG_EX="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"; 
	//public static final String FILT_REG_EX="[`$'\"<>]";
	public static final String FILT_REG_EX="[\n|\r|\r\n|\t|<|>()$&;'\"]";
	/**
	 * 用指定的字符填充指定的字符串达到指定的长度，并返回填充之后的字符串 <br>
	 * 
	 * @param p_scr
	 *            待填充的字符串
	 * @param p_fill
	 *            填充的字符
	 * @param p_length
	 *            填充之后的字符串总长度
	 * @return String 填充之后的字符串
	 */
	public static String fill(String p_scr, char p_fill, int p_length) {
		return fill(p_scr, p_fill, p_length, FRONT);
	}

	/**
	 * 用指定的字符填充指定的字符串达到指定的长度，并返回填充之后的字符串<br>
	 * 
	 * @param p_scr
	 *            待填充的字符串
	 * @param p_fill
	 *            填充的字符
	 * @param p_length
	 *            填充之后的字符串总长度
	 * @param direction
	 *            填充方向，SerialPart.FRONT 前面，SerialPart.BACK后面
	 * @return String 填充之后的字符串
	 */
	public static String fill(String p_scr, char p_fill, int p_length, int direction) {
		/* 如果待填充字符串的长度等于填充之后字符串的长度，则无需填充直接返回 */
		if (p_scr.length() == p_length) {
			return p_scr;
		}
		/* 初始化字符数组 */
		char[] fill = new char[p_length - p_scr.length()];
		/* 填充字符数组 */
		Arrays.fill(fill, p_fill);
		/* 根据填充方向，将填充字符串与源字符串进行拼接 */
		switch (direction) {
		case FRONT:
			return String.valueOf(fill).concat(p_scr);
		case BACK:
			return p_scr.concat(String.valueOf(fill));
		default:
			return p_scr;
		}
	}

	/**
	 * 关于汉字填充
	 * 
	 * @param p_scr
	 * @param p_fill
	 * @param p_length
	 * @param direction
	 * @return
	 */
	public static String fillString(String p_scr, char p_fill, int p_length, int direction) {
		/* 如果待填充字符串的长度等于填充之后字符串的长度，则无需填充直接返回 */
		if (p_scr.getBytes().length == p_length) {
			return p_scr;
		}
		/* 初始化字符数组 */
		char[] fill = new char[p_length - p_scr.getBytes().length];
		/* 填充字符数组 */
		Arrays.fill(fill, p_fill);
		/* 根据填充方向，将填充字符串与源字符串进行拼接 */
		switch (direction) {
		case FRONT:
			return String.valueOf(fill).concat(p_scr);
		case BACK:
			return p_scr.concat(String.valueOf(fill));
		default:
			return p_scr;
		}
	}

	/**
	 * 将字符串平面化
	 * 
	 * @param param
	 * @param delim
	 * @return
	 */
	public static String array2String(Object[] param, String delim) {
		if (param == null) {
			return null;
		} else {
			delim = delim != null ? delim : ",";
			StringBuffer result = new StringBuffer();
			for (int i = 0; i < param.length; i++) {
				result.append(param[i]);
				if (i < param.length - 1) {
					result.append(delim);
				}
			}
			return result.toString();
		}
	}

	public static String array2String(Object[] param, String prefix, String suffix, String delim) {
		if (param == null) {
			return null;
		} else {
			delim = delim != null ? delim : ",";
			prefix = prefix != null ? prefix : "";
			suffix = suffix != null ? suffix : "";
			StringBuffer result = new StringBuffer();
			for (int i = 0; i < param.length; i++) {
				result.append(prefix).append(param[i]).append(suffix);
				if (i < param.length - 1) {
					result.append(delim);
				}
			}
			return result.toString();
		}
	}

	/**
	 * 字符串转换
	 * 
	 * @param string
	 * @param delim
	 * @return
	 */
	public static String[] string2Array(String string, String delim) {
		if (string == null) {
			return null;
		} else {
			delim = delim != null ? delim : "\\|";
			return string.split(delim);
		}
	}

	public static List<String> string2List(String p_Param, String p_Delim) {
		if (p_Param == null) {
			return null;
		}
		StringTokenizer st;
		if (p_Delim != null) {
			st = new StringTokenizer(p_Param, p_Delim);
		} else {
			st = new StringTokenizer(p_Param);
		}
		List<String> result = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			result.add(st.nextToken());
		}
		return result;
	}

	public static String encoding(String str) throws Exception {
		return new String(str.getBytes("GB23"), "GB23");
	}

	/**
	 * 去除前后空格
	 * 
	 * @param str
	 * @return
	 */
	public static String trimHeadAndEnd(String str) {
		if (str == null) {
			return null;
		}
		String temp = str.trim();
		if (temp.length() > 0) {
			while (temp.indexOf(" ") == 0) {
				temp = temp.substring(1, temp.length());
			}
			return temp;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 功能描述:特殊符号替换 <br>
	 * 〈功能详细描述〉
	 *
	 * @param rep
	 * @param redex
	 * @param replaceement
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static String replaceAll(String rep, String[] redex, String replaceement[]) {
		if (redex.length == replaceement.length) {
			for (int i = 0; i < redex.length; i++) {
				rep = rep.replaceAll("\\" + redex[i], replaceement[i]);
			}
		}
		return rep;
	}

	/**
	 * 字符串中间部分替换
	 * 
	 * @param index
	 *            开始位数
	 * @param res
	 *            原字符串
	 * @param str
	 *            替换后字符串
	 * @param end
	 *            结束留位数
	 * @return
	 */
	public static String stirngReplaceIndex(int index, String res, String str, int end) {
		String value = res;
		if (res.length() > 10) {
			for (int i = index; i < res.length() - end; i++) {
				value = replaceIndex(i, value, str);
			}
		}
		return value;
	}

	public static String replaceIndex(int index, String res, String str) {
		return res.substring(0, index) + str + res.substring(index + 1);
	}

	private static final int PAD_LIMIT = 8192;

	public static boolean isEmpty(String str) {
		return (str == null) || (str.length() == 0);
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isBlank(String str) {
		int strLen;
		if ((str == null) || ((strLen = str.length()) == 0))
			return true;

		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	private static String padding(int repeat, char padChar) throws IndexOutOfBoundsException {
		if (repeat < 0) {
			throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
		}
		char[] buf = new char[repeat];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = padChar;
		}
		return new String(buf);
	}

	public static String rightPad(String str, int size) {
		return rightPad(str, size, ' ');
	}

	public static String rightPad(String str, int size, char padChar) {
		if (str == null) {
			return null;
		}
		int pads = size - str.length();
		if (pads <= 0) {
			return str;
		}
		if (pads > 8192) {
			return rightPad(str, size, String.valueOf(padChar));
		}
		return str.concat(padding(pads, padChar));
	}

	public static String rightPad(String str, int size, String padStr) {
		if (str == null) {
			return null;
		}
		if (isEmpty(padStr)) {
			padStr = " ";
		}
		int padLen = padStr.length();
		int strLen = str.length();
		int pads = size - strLen;
		if (pads <= 0) {
			return str;
		}
		if ((padLen == 1) && (pads <= 8192)) {
			return rightPad(str, size, padStr.charAt(0));
		}

		if (pads == padLen)
			return str.concat(padStr);
		if (pads < padLen) {
			return str.concat(padStr.substring(0, pads));
		}
		char[] padding = new char[pads];
		char[] padChars = padStr.toCharArray();
		for (int i = 0; i < pads; i++) {
			padding[i] = padChars[(i % padLen)];
		}
		return str.concat(new String(padding));
	}

	public static String leftPad(String str, int size) {
		return leftPad(str, size, ' ');
	}

	public static String leftPad(String str, int size, char padChar) {
		if (str == null) {
			return null;
		}
		int pads = size - str.length();
		if (pads <= 0) {
			return str;
		}
		if (pads > 8192) {
			return leftPad(str, size, String.valueOf(padChar));
		}
		return padding(pads, padChar).concat(str);
	}

	/**
	 * 
	 * @param str
	 *            当前长度
	 * @param size
	 *            补位数
	 * @param padStr
	 *            补得值
	 * @return
	 */
	public static String leftPad(String str, int size, String padStr) {
		if (str == null) {
			return null;
		}
		if (isEmpty(padStr)) {
			padStr = " ";
		}
		int padLen = padStr.length();
		int strLen = str.length();
		int pads = size - strLen;
		if (pads <= 0) {
			return str;
		}
		if ((padLen == 1) && (pads <= 8192)) {
			return leftPad(str, size, padStr.charAt(0));
		}

		if (pads == padLen)
			return padStr.concat(str);
		if (pads < padLen) {
			return padStr.substring(0, pads).concat(str);
		}
		char[] padding = new char[pads];
		char[] padChars = padStr.toCharArray();
		for (int i = 0; i < pads; i++) {
			padding[i] = padChars[(i % padLen)];
		}
		return new String(padding).concat(str);
	}

	/**
	 * 
	 * 功能描述: 判断是否为空<br>
	 * 〈功能详细描述〉
	 *
	 * @param object
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static boolean isNull(Object object) {
		if (object instanceof String) {
			return StringUtil.isEmpty(object.toString());
		}
		return object == null;
	}

//	public static void main(String[] args) {
//		String[] s = new String[] { "6226902006329942", "6228558888006399724" };
//		for (int i = 0; i < s.length; i++) {
//			System.out.println(stirngReplaceIndex(6, s[i], "*", 4));
//		}
//
//		System.out.println(formatMonney("0.1"));
//		System.out.println(new BigDecimal("9.00"));
//	}

	/**
	 * 功能描述: 格式化金额 〈功能详细描述〉 2016-1-12
	 * 
	 * @param object
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static String formatMonney(Object object) {
		if (isNull(object)) {
			return "0.00";
		}
		DecimalFormat myformat = new DecimalFormat();
		myformat.applyPattern("0.00");
		return myformat.format(new BigDecimal(object.toString().trim()));
	}
	/**
	 * 功能描述：过滤传入参数字符串中的特殊字符
	 * @param String
	 * @return 过滤后的字符串
	 * */
	public static String filtrateSpecialCharater(String param){
		if(StringUtil.isEmpty(param)){
			return param;
		}
		Pattern p = Pattern.compile(FILT_REG_EX); 
		Matcher m = p.matcher(param);
		return m.replaceAll("").trim();
	}
	
	public static void main(String[] args) {
		String a= "alert(恭喜你中nrt$&奖啦：\"前'往<a>http://www.baidu.com</a>领取奖励;)";
		String after = StringUtil.filtrateSpecialCharater(a);
		System.out.println("过滤后的字符串为："+after);
	}
}
