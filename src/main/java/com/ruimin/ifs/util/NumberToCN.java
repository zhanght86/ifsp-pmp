package com.ruimin.ifs.util;

import com.ruim.ifsp.utils.message.IfspStringUtil;

import java.math.BigDecimal;

/**
 * 名称：〈数字转换为汉语中人民币的大写〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年12月13日 <br>
 * 作者：renliang <br>
 * 说明：<br>
 */
public class NumberToCN {
	/**
	 * 汉语中数字大写
	 */
	private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	/**
	 * 汉语中货币单位大写，这样的设计类似于占位符
	 */
	private static final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾",
			"佰", "仟", "兆", "拾", "佰", "仟" };
	/**
	 * 特殊字符：整
	 */
	private static final String CN_FULL = "整";
	/**
	 * 特殊字符：负
	 */
	private static final String CN_NEGATIVE = "负";
	/**
	 * 金额的精度，默认值为2
	 */
	private static final int MONEY_PRECISION = 2;
	/**
	 * 特殊字符：零元整
	 */
	private static final String CN_ZEOR_FULL = "零元" + CN_FULL;

	/**
	 * 把输入的金额转换为汉语中人民币的大写
	 * 
	 * @param numberOfMoney
	 *            输入的金额
	 * @return 对应的汉语大写
	 */
	public static String number2CNMontrayUnit(BigDecimal numberOfMoney) {
		StringBuffer sb = new StringBuffer();
		// -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
		// positive.
		int signum = numberOfMoney.signum();
		// 零元整的情况
		if (signum == 0) {
			return CN_ZEOR_FULL;
		}
		// 这里会进行金额的四舍五入
		long number = numberOfMoney.movePointRight(MONEY_PRECISION).setScale(0, 4).abs().longValue();
		// 得到小数点后两位值
		long scale = number % 100;
		int numUnit = 0;
		int numIndex = 0;
		boolean getZero = false;
		// 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
		if (!(scale > 0)) {
			numIndex = 2;
			number = number / 100;
			getZero = true;
		}
		if ((scale > 0) && (!(scale % 10 > 0))) {
			numIndex = 1;
			number = number / 10;
			getZero = true;
		}
		int zeroSize = 0;
		while (true) {
			if (number <= 0) {
				break;
			}
			// 每次获取到最后一个数
			numUnit = (int) (number % 10);
			if (numUnit > 0) {
				if ((numIndex == 9) && (zeroSize >= 3)) {
					sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
				}
				if ((numIndex == 13) && (zeroSize >= 3)) {
					sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
				}
				sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
				sb.insert(0, CN_UPPER_NUMBER[numUnit]);
				getZero = false;
				zeroSize = 0;
			} else {
				++zeroSize;
				if (!(getZero)) {
					sb.insert(0, CN_UPPER_NUMBER[numUnit]);
				}
				if (numIndex == 2) {
					if (number > 0) {
						sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
					}
				} else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
					sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
				}
				getZero = true;
			}
			// 让number每次都去掉最后一个数
			number = number / 10;
			++numIndex;
		}
		// 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
		if (signum == -1) {
			sb.insert(0, CN_NEGATIVE);
		}
		// 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
		if (!(scale > 0)) {
			sb.append(CN_FULL);
		}
		return sb.toString();
	}

	public static String dateToCnDate(String dateTime) {
		if (IfspStringUtil.isBlank(dateTime))
			return "";
		if (dateTime.length() != 8)
			return "";
		String cnDateTime = "";
		cnDateTime += dateTime.substring(0, 4) + "年";
		cnDateTime += dateTime.substring(4, 6) + "月";
		cnDateTime += dateTime.substring(6, 8) + "日";
		return cnDateTime;
	}

	public static String numberToChars(String number) {
		if (IfspStringUtil.isBlank(number))
			return "";
		char[] nums = number.trim().toCharArray();
		String resStr = "";
		if (number.length() > 0) {
			for (int i = 0; i < nums.length; i++) {
				if (i == 0) {
					resStr += nums[i];
				} else {
					resStr += "," + nums[i];
				}
			}
		}
		return resStr;
	}

	public static void main(String[] args) {
		double money = 2020004.12;
		BigDecimal numberOfMoney = new BigDecimal(money);
		String s = NumberToCN.number2CNMontrayUnit(numberOfMoney);
		System.out.println("你输入的金额为：【" + money + "】   #--# [" + s.toString() + "]");

		char[] nums = String.valueOf(numberOfMoney.intValue()).toCharArray();
		if (nums.length > 0) {
			// 千 百 十 万 千 百 十 元 角 分
			// System.out.println("千 百 十 万 千 百 十 元 角 分");
			// if(nums.length >= 10)System.out.print(nums[nums.length-10]);
			// if(nums.length >= 9)System.out.print(nums[nums.length-9]);
			// if(nums.length >= 8)System.out.print(nums[nums.length-8]);
			// if(nums.length >= 7)System.out.print(nums[nums.length-7]);
			// if(nums.length >= 6)System.out.print(nums[nums.length-6]);
			// if(nums.length >= 5)System.out.print(nums[nums.length-5]);
			// if(nums.length >= 4)System.out.print(nums[nums.length-4]);
			// if(nums.length >= 3)System.out.print(nums[nums.length-3]);
			// if(nums.length >= 2)System.out.print(nums[nums.length-2]);
			// if(nums.length >= 1)System.out.print(nums[nums.length-1]);

			if (nums.length >= 10)
				System.out.print(nums[nums.length - 10] + "千");
			if (nums.length >= 9)
				System.out.print(nums[nums.length - 9] + "百");
			if (nums.length >= 8)
				System.out.print(nums[nums.length - 8] + "十");
			if (nums.length >= 7)
				System.out.print(nums[nums.length - 7] + "万");
			if (nums.length >= 6)
				System.out.print(nums[nums.length - 6] + "千");
			if (nums.length >= 5)
				System.out.print(nums[nums.length - 5] + "百");
			if (nums.length >= 4)
				System.out.print(nums[nums.length - 4] + "十");
			if (nums.length >= 3)
				System.out.print(nums[nums.length - 3] + "元");
			if (nums.length >= 2)
				System.out.print(nums[nums.length - 2] + "角");
			if (nums.length >= 1)
				System.out.print(nums[nums.length - 1] + "分");

			System.out.println(numberToChars("123456789"));
			System.out.println(dateToCnDate("20161208"));
		}

	}
}