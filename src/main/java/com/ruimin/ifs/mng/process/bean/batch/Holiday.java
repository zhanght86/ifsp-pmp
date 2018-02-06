package com.ruimin.ifs.mng.process.bean.batch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;

public class Holiday {
	private String date = null;
	private int day_of_week = 0;
	private int day_of_month = 0;
	private int end_day_of_month = 0;
	private int month_of_year = 0;
	private SimpleDateFormat formate = null;

	public Holiday(String _strDate) throws SnowException {
		java.util.Calendar ca = java.util.Calendar.getInstance();
		Date _date = null;
		formate = new SimpleDateFormat(BatchConstant.DATE_PARTTEN);
		try {
			_date = formate.parse(_strDate);
		} catch (ParseException ex) {
			throw SnowExceptionUtil.wrapException(ex);
		}
		ca.setTime(_date);
		this.date = _strDate;

		this.day_of_month = ca.get(java.util.Calendar.DAY_OF_MONTH);
		this.end_day_of_month = ca.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		this.month_of_year = ca.get(java.util.Calendar.MONTH) - java.util.Calendar.JANUARY + 1;
		/*
		 * 星期几
		 */
		switch (ca.get(java.util.Calendar.DAY_OF_WEEK)) {
		case java.util.Calendar.MONDAY:
			this.day_of_week = 1;
			break;
		case java.util.Calendar.TUESDAY:
			this.day_of_week = 2;
			break;
		case java.util.Calendar.WEDNESDAY:
			this.day_of_week = 3;
			break;
		case java.util.Calendar.THURSDAY:
			this.day_of_week = 4;
			break;
		case java.util.Calendar.FRIDAY:
			this.day_of_week = 5;
			break;
		case java.util.Calendar.SATURDAY:
			this.day_of_week = 6;
			break;
		case java.util.Calendar.SUNDAY:
			this.day_of_week = 7;
			break;
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Date[").append(this.date).append("]Week Day[").append(day_of_week).append("]Day Of Month[")
				.append(day_of_month).append("] End Day Of Month[").append(end_day_of_month).append("]");
		if (this.isWeekEnd())
			sb.append(" [Week End]");
		if (this.isaPeriodOfTenDays())
			sb.append(" [A Period Of Ten Days]");
		if (this.isMonthEnd())
			sb.append(" [Month End]");
		if (this.isSeasonEnd())
			sb.append(" [Season End]");
		if (this.isHalfYearEnd())
			sb.append(" [Half Year End]");
		if (this.isYearEnd())
			sb.append(" [Year End]");
		return sb.toString();
	}

	public boolean isYearEnd() {
		/*
		 * 年末
		 */
		if (day_of_month == end_day_of_month && month_of_year == 12)
			return true;
		else
			return false;
	}

	public boolean isHalfYearEnd() {
		/*
		 * 半年末
		 */
		if (day_of_month == end_day_of_month && month_of_year == 6)
			return true;
		else
			return false;
	}

	public boolean isSeasonEnd() {
		/*
		 * 季末
		 */
		if (day_of_month == end_day_of_month
				&& (month_of_year == 3 || month_of_year == 6 || month_of_year == 9 || month_of_year == 12))
			return true;
		else
			return false;
	}

	public boolean isMonthEnd() {
		/*
		 * 月末
		 */
		if (day_of_month == end_day_of_month)
			return true;
		else
			return false;
	}

	public boolean isMonthBegin() {
		/*
		 * 月初
		 */
		if (day_of_month == 1) {
			return true;
		}
		return false;
	}

	public boolean isaPeriodOfTenDays() {
		/*
		 * 旬末
		 */
		if (day_of_month == 10 || day_of_month == 20 || day_of_month == end_day_of_month)
			return true;
		else
			return false;
	}

	public boolean isWeekEnd() {
		/*
		 * 周末
		 */
		if (this.day_of_week == 7)
			return true;
		else
			return false;
	}

	/**
	 * 通过子线程的运行时间和holiday表的比较确定子步骤这次是否需要运行
	 * 
	 * @param runtime
	 *            表bh_proc_step中子步骤的运行时刻定义
	 * @param holidayArray
	 *            表holiday中当前批量日期的周末季末等定义
	 * @param bhdate
	 *            当前批量日期
	 * @return boolean 返回是否需要运行该子步骤
	 */
	public boolean checkRun(String runtime) {
		if (runtime.equalsIgnoreCase(BatchConstant.SUB_STEP_RUN_TIME_NONE)) {
			// 不跑
			return false;
		} else if (runtime.equals(BatchConstant.SUB_STEP_RUN_TIME_DAILY)) {
			// 每日运行
			return true;
		} else if (runtime.equals(BatchConstant.SUB_STEP_RUN_TIME_EVERY_TEN_DAYS) && this.isaPeriodOfTenDays()) { // 每旬运行
			return true;
		} else if (runtime.equals(BatchConstant.SUB_STEP_RUN_TIME_MONTHLY) && this.isMonthEnd()) { // 每月运行
			return true;
		} else if (runtime.equals(BatchConstant.SUB_STEP_RUN_TIME_EVERY_SEASON) && this.isSeasonEnd()) { // 每季运行
			return true;
		} else if (runtime.equals(BatchConstant.SUB_STEP_RUN_TIME_EVERY_HALF_YEAR) && this.isHalfYearEnd()) { // 每半年运行
			return true;
		} else if (runtime.equals(BatchConstant.SUB_STEP_RUN_TIME_YEARLY) && this.isYearEnd()) { // 每年运行
			return true;
		}
		/*
		 * 指定运行时间为每周中的某日
		 */
		else if (runtime.equals(BatchConstant.WEEKDAY_MONDAY) && this.day_of_week == 1) { // 周一运行
			return true;
		} else if (runtime.equals(BatchConstant.WEEKDAY_TUESDAY) && this.day_of_week == 2) { // 周二运行
			return true;
		} else if (runtime.equals(BatchConstant.WEEKDAY_WEDNESDAY) && this.day_of_week == 3) { // 周三运行
			return true;
		} else if (runtime.equals(BatchConstant.WEEKDAY_THURSDAY) && this.day_of_week == 4) { // 周四运行
			return true;
		} else if (runtime.equals(BatchConstant.WEEKDAY_FRIDAY) && this.day_of_week == 5) { // 周五运行
			return true;
		} else if (runtime.equals(BatchConstant.WEEKDAY_SATURDAY) && this.day_of_week == 6) { // 周六运行
			return true;
		} else if (runtime.equals(BatchConstant.WEEKDAY_SUNDAY) && this.day_of_week == 7) { // 周日运行
			return true;
		}
		/*
		 * 指定为每月中的某日运行
		 */
		else {
			if (Integer.parseInt(runtime) == this.day_of_month)
				return true;
		}
		return false;
	}

//	public static void main(String[] args) {
//		try {
//			String _date = args[0];
//			System.out.println(_date);
//			Holiday holiday = new Holiday(_date);
//			System.out.println(holiday.toString());
//			if (holiday.isMonthEnd()) {
//				System.out.println("Month End");
//			}
//			if (holiday.isYearEnd()) {
//				System.out.println("Year End");
//			}
//			if (holiday.isMonthBegin()) {
//				System.out.println("Month Begin");
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//	}

	public int getDay_of_week() {
		return day_of_week;
	}

	public int getDay_of_month() {
		return day_of_month;
	}
}
