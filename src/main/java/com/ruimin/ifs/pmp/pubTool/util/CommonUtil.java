package com.ruimin.ifs.pmp.pubTool.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.baseParaMng.process.bean.PbsMchtGrpInfo;
import com.ruimin.ifs.pmp.baseParaMng.process.service.MchtMccMngService;

/**
 * 
 * 无锡内管开发-工具类
 * 
 * @author zhangjc
 *
 */
public class CommonUtil {
	/**
	 * 【金额】分转元，整数部分每三位逗号分隔
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 */
	public static String transFenToYuan(FieldBean bean, String key, ServletRequest request) {
		if (key == null || "".equals(key.trim()))
			return "0.00";
		try {
			BigDecimal bigDecimal = new BigDecimal(key.trim());
			key = bigDecimal.movePointLeft(2).toString();
		} catch (Exception e) {
			return key;
		}
		// 取整数位
		String[] split = key.split("\\.");
		String str1 = split[0];
		str1 = new StringBuilder(str1).reverse().toString(); // 先将字符串颠倒顺序
		String str2 = "";
		for (int i = 0; i < str1.length(); i++) {
			if (i * 3 + 3 > str1.length()) {
				str2 += str1.substring(i * 3, str1.length());
				break;
			}
			str2 += str1.substring(i * 3, i * 3 + 3) + ",";
		}
		if (str2.endsWith(",")) {
			str2 = str2.substring(0, str2.length() - 1);
		}
		// 最后再将顺序反转过来,拼接小数位
		return new StringBuilder(str2).reverse().append(".").append(split[1]).toString();
	}

	 /**
     * 【金额】分转元，整数部分每三位逗号分隔,用于Action
     * @param bean
     * @param key
     * @param request
     * @return
     */
    public static String transFenToYuan(String key) {
        if (key == null || "".equals(key.trim()))
            return key;
        try {
            BigDecimal bigDecimal = new BigDecimal(key.trim());
            key = bigDecimal.movePointLeft(2).toString();
        } catch (Exception e) {
            return key;
        }
        // 取整数位
        String[] split = key.split("\\.");
        String str1 = split[0];
        str1 = new StringBuilder(str1).reverse().toString(); // 先将字符串颠倒顺序
        String str2 = "";
        for (int i = 0; i < str1.length(); i++) {
            if (i * 3 + 3 > str1.length()) {
                str2 += str1.substring(i * 3, str1.length());
                break;
            }
            str2 += str1.substring(i * 3, i * 3 + 3) + ",";
        }
        if (str2.endsWith(",")) {
            str2 = str2.substring(0, str2.length() - 1);
        }
        // 最后再将顺序反转过来,拼接小数位
        return new StringBuilder(str2).reverse().append(".").append(split[1]).toString();
    }
	/**
	 * 【金额】元转分
	 * 
	 * @param yuan
	 *            单位元的金额
	 * @return
	 */
	public static String transYuanToFen(String yuan) {
		// 去除字符串逗号
		String del = ",";
		yuan = filtStr(yuan, del);
		// 金额为0
		if (yuan == null || "".equals(yuan.trim()))
			return "0";
		// 存在金额，转为分
		BigDecimal bigDecimal = new BigDecimal(yuan.trim());
		return bigDecimal.movePointRight(2).toString();
	}

	/**
	 * 过滤字符串（注意：该方法中的del仅支持单个字符）
	 * 
	 * @param str
	 *            字符串
	 * @param del
	 *            删掉的部分
	 * @return
	 */
	public static String filtStr(String str, String del) {
		StringBuffer sb = new StringBuffer();// 数据容器
		int strLength = str.length();// 获取字符串长度,作为循环计数器
		String indivStr;// 放置截取字符串
		while (strLength != 0) {
			indivStr = str.substring(str.length() - strLength, str.length() - strLength + 1);
			if (!indivStr.equals(del)) {
				sb.append(indivStr);
			}
			strLength--;
		}
		return sb.toString();
	}

	/**
	 * 根据组别编号获取组别名称
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 */
	public static String getGrpName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		if (StringUtils.isNotBlank(key)) {
			PbsMchtGrpInfo mchtGrpMngVO = MchtMccMngService.getInstance().queryGrpName(key);
			if (mchtGrpMngVO != null) {
				return mchtGrpMngVO.getGrpDesc();
			}
		}
		return key;
	}

	/**
	 * 【时间】时间格式转换
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 * @throws ParseException
	 */
	public static String timeFormat(FieldBean bean, String key, ServletRequest request)
			throws SnowException, ParseException {
		String chlSetlTm = key;
		if (StringUtils.isNotBlank(chlSetlTm)) {
			SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
			Date date = (Date) sdf.parse(key);
			chlSetlTm = sdf2.format(date);
		}
		return chlSetlTm;
	}

	/**
	 * 截取字符串【用于通过路径获得文件名】
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String cutStr(FieldBean bean, String key, ServletRequest request) throws SnowException {
		String strArray[] = key.split("/");
		return strArray[strArray.length - 1];
	}
}
