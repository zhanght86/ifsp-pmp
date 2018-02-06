package com.ruimin.ifs.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 日期工具类
 * 
 * @author zhaodk
 * 
 */
public class DateUtils {
    
    /**
     * 日期格式：yyyy-MM-dd
     */
    private static String STR_YYYY_MM_DD = "yyyy-MM-dd";
    
    /**
     * 日期格式：yyyyMMdd
     */
    private static String STR_YYYY_MM_DD_2 = "yyyyMMdd";
    
    /**
     * 例：2014-05-22 15:00:40
     */
    private static String DATE_TIME_18_FMT_HOUR = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 日期格式：yyMMdd
     */
    private static String STR_YY_MM_DD = "yyMMdd";
    
    /**
     * 例：20140516110105
     */
    private static String DATE_TIME_14_FMT = "yyyyMMddHHmmss";
    
    /**
     * 日期格式：yyyy年MM月dd日 HH时mm分ss秒
     */
    private static String STR_YYYY_MM_DD_HH_MM_SS_CN = "yyyy年MM月dd日 HH时mm分ss秒";
    
    /** 日期转换Y-M-d */
//  public static DateFormat dfday = new SimpleDateFormat("yyyy-MM-dd");// 日期格式

//  public static DateFormat dfday2 = new SimpleDateFormat("yyyy/MM/dd");// 日期格式

    /** 日期转换Y-M-d */
//  public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /** 获取当前系统时间 Y-M-d H:M:S */
//  public static String day_Time = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date());

    /** 获取当前系统时间 Y-M-d */
//    public static String day = FastDateFormat.getInstance("yyyy-MM-dd").format(new Date());

    /** 日期转换 Y-M-d H:M:S */
//  public static SimpleDateFormat formatime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static int day_Weeks_count;

//  public static DateFormat dfdayyyyyMMdd = new SimpleDateFormat("yyyyMMdd");// 日期格式

    public static DateFormat dfdayTime = new SimpleDateFormat("HH:mm:ss");// 日期格式

//  private static SimpleDateFormat orderDate = new SimpleDateFormat("yyMMdd", Locale.CANADA);
    
//    private static SimpleDateFormat showDateFormatZHCN = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
    
    public static DateFormat dfdayTimeHHmmss = new SimpleDateFormat("HHmmss");// 日期格式
    
    /** 日期转换Y-M-d */
    public static DateFormat dfday = new SimpleDateFormat("yyyy-MM-dd");// 日期格式

    public static DateFormat dfday2 = new SimpleDateFormat("yyyy/MM/dd");// 日期格式

    /** 日期转换Y-M-d */
    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /** 获取当前系统时间 Y-M-d H:M:S */
//    public static String day_Time = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date());


    /** 日期转换 Y-M-d H:M:S */
    public static SimpleDateFormat formatime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static DateFormat dfdayyyyyMMdd = new SimpleDateFormat("yyyyMMdd");// 日期格式

    private static SimpleDateFormat orderDate = new SimpleDateFormat("yyMMdd", Locale.CANADA);
    
    private static SimpleDateFormat showDateFormatZHCN = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
    
    
    /**
     * 获得中文系统时间
     * @return
     */
    public static String getCurrentDateTimeZHCN() {
        return new SimpleDateFormat(STR_YYYY_MM_DD_HH_MM_SS_CN).format(new Date());
    }

    /**
     * Date日期转换成 (yyyy-MM-dd)
     * 
     * @param d
     * @return
     */
    public static String Day_D(Date date) {
        try {
            return new SimpleDateFormat(STR_YYYY_MM_DD).format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取系统当天日期yyyy-MM-dd
     * 
     * @return
     * @throws ParseException
     */
    public static String DT_DAY() {
        String day = null;
        try {
            day = new SimpleDateFormat(STR_YYYY_MM_DD).format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * 将如果字符串符合yyyy-MM-dd格式的日期字符串，转换成yyyyMMdd<br>
     * 
     * @param str
     *            日期格式字符串
     * @return String
     */
    public static String convertDataFormat(String str) {
        if (str == null) {
            return "";
        }
        String result = str;
        String regStr1 = "\\d{4}[-]\\d{2}[-]\\d{2}";
        String regStr2 = "-";
        if (Pattern.matches(regStr1, str)) {
            Pattern pattern = Pattern.compile(regStr2);
            Matcher matcher = pattern.matcher(str);
            result = matcher.replaceAll("");
        }
        return result;
    }

    /**
     * 获取年(4位)
     * 
     * @return
     * @throws Exception
     */
    public static String day_YEAR() {
        return convertDataFormat(DT_DAY()).substring(0, 4);
    }

    /**
     * 获取日期所在的年(4位)
     * 
     * @param day
     * @return
     */
    public static String day_YEAR(String day) {
        return convertDataFormat(day).substring(0, 4);
    }

    /**
     * 获取月(2位)
     * 
     * @return
     */
    public static String day_MONTH() {
        return convertDataFormat(DT_DAY()).substring(4, 6);
    }

    /**
     * 获得当前时间的Timestamp
     * 
     * @return Timestamp
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取日期所在的月(2位)
     * 
     * @return
     */
    public static String day_MONTH(String day) {
        return convertDataFormat(day).substring(4, 6);
    }

    /**
     * 获取两个日期之间所在的月
     * 
     * @param start
     * @param end
     * @return
     */
    public static List<Integer> getMONTN(String start, String end) {
        List<Integer> list = new ArrayList<Integer>();
        int bing = Integer.valueOf(day_MONTH(start));
        int ends = Integer.valueOf(day_MONTH(end));
        for (int i = bing; i <= ends; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * 通过日期字符串获取日期是本年的几周
     * 
     * @param today
     * @return
     */
    public static int day_Week(String today) {
        try {
            Date date = new SimpleDateFormat(STR_YYYY_MM_DD).parse(today);
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.setTime(date);
            return calendar.get(Calendar.WEEK_OF_YEAR);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 得到起始日期和结束日期之间的所有日期
     * 
     * @param start:其实日期
     * @param end:结束日期
     * @return
     */
    public static List<Date> getAllDates(Date start, Date end) {
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(start);
        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTime(end);
        return dayAll(calendar1, calendar2, start);
    }

    /**
     * 得到起始日期和结束日期之间的所有日期
     * 
     * @param start:其实日期
     * @param end:结束日期
     * @return
     */
    public static List<Date> getAllDatesStr(String start, String end) {
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(parseDate(start));
        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTime(parseDate(end));
        return dayAll(calendar1, calendar2, parseDate(start));
    }

    public static List<Date> dayAll(Calendar calendar1, Calendar calendar2, Date start) {
        List<Date> list = new ArrayList<Date>();
        list.add(start);
        while (calendar1.compareTo(calendar2) < 0) {
            calendar1.add(Calendar.DATE, 1);
            list.add(calendar1.getTime());
        }
        return list;
    }

    /**
     * 获取日期的前i天
     * 
     * @param day
     *            日期
     * @param i
     *            日期的前几天
     * @return
     */
    public static String date_Forward(String day, int i) {
        Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
        calendar.setTime(DateUtils.parseDate(day,STR_YYYY_MM_DD_2));
        calendar.add(Calendar.DATE, -i); // 得到前一天
        String yestedayDate = new SimpleDateFormat(STR_YYYY_MM_DD_2).format(calendar.getTime());
        return yestedayDate;
    }
    /**
     * 获取日期的前i天
     * 
     * @param day
     *            日期
     * @param i
     *            日期的前几天
     * @return
     */
    public static String date_Forward(Date day, int i) {
        Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
        calendar.setTime(day);
        calendar.add(Calendar.DATE, -i); // 得到前一天
        String yestedayDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return yestedayDate;
    }
    public static String date_Forward(Date day, int i,String format) {
        Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
        calendar.setTime(day);
        calendar.add(Calendar.DATE, -i); // 得到前一天
        String yestedayDate = new SimpleDateFormat(format).format(calendar.getTime());
        return yestedayDate;
    }
    /**
     * 获取日期的前i天
     * 
     * @param day
     *            日期
     * @param i
     *            日期的前几天
     * @return
     */
    public static String date_Forward(int i) {
        Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -i); // 得到前一天
        String yestedayDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return yestedayDate;
    }
    /**
     * 获取日期的前i月
     * 
     * @param day
     *            日期
     * @param i
     *            日期的后几月
     * @return
     */
    public static String date_MONTH_Forward(String day, int i) {
        Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
        calendar.setTime(DateUtils.parseDate(day));
        calendar.add(Calendar.MONTH, i); // 得到前一天
        String yestedayDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return yestedayDate;
    }
    /**
     * 获取日期的后i月
     * 
     * @param day
     *            日期
     * @param i
     *            日期的前几月
     * @return
     */
    public static String date_MONTH_back(String day, int i) {
        Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
        calendar.setTime(DateUtils.parseDate(day));
        calendar.add(Calendar.MONTH, -i); // 得到前一天
        String yestedayDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return yestedayDate;
    }
    /**
     * 获取日期的后i天
     * 
     * @param day
     *            日期
     * @param i
     *            日期的后几天
     * @return
     */
    public static String date_back(String day, int i) {
        Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
        calendar.setTime(DateUtils.parseDate(day));
        calendar.add(Calendar.DATE, i); // 得到前一天
        String yestedayDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return yestedayDate;
    }

    
    /**
     * 获取当前日期的前i天
     * 
     * @param i
     *            日期的前几天
     * @return
     */
    public static String day_Forward(int i) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);   
        calendar.add(Calendar.DATE, -i);
        return  new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }
    /**
     * 获取当前日期的后i天
     * 
     * @param i
     *            日期的后几天
     * @return
     */
    public static String day_back(int i) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);   
        calendar.add(Calendar.DATE, i);
        return  new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }
    /**
     * 获取当前日期的前i月
     * 
     * @param i
     *            日期的前几月
     * @return
     */
    public static String day_MONTH_Forward(int i) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -i);
        return  new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }
    /**
     * 获取当前日期的后i月
     * 
     * @param i
     *            日期的后几月
     * @return
     */
    public static String day_MONTH_back(int i) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);   
        calendar.add(Calendar.MONTH, i);
        return  new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }
    
    /**
     * 得到本月的第一天
     * 
     * @return
     */
    public static String getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    /**
     * 通过日期获取当前时期的第一天
     * 
     * @param date
     *            日期（yyyy-MM-dd）
     * @return
     */
    public static String getMonthFirstDay(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.parseDate(date));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    /**
     * 通过日期获取当前时期的前几月第一天
     * 
     * @param date
     *            日期
     * @param i
     *            前几月
     * @return
     */
    @SuppressWarnings("static-access")
    public static String getMonthFirstDay(String date, int i) {
        Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
        calendar.setTime(DateUtils.parseDate(date));
        calendar.add(calendar.MONTH, -i); // 得到前一月
        String yestedayDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return getMonthFirstDay(yestedayDate);
    }

    /**
     * 得到本月的最后一天
     * 
     * @return
     */
    public static String getMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    /**
     * 通过日期获取当前时期的最后一天
     * 
     * @param date
     *            日期（yyyy-MM-dd）
     * @return
     */
    public static String getMonthLastDay(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.parseDate(date));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    /**
     * 通过日期获取当前时期的最后一天
     * 
     * @param date
     *            日期（yyyy-MM-dd）
     * @param i
     *            月
     * @return
     */
    @SuppressWarnings("static-access")
    public static String getMonthLastDay(String date, int i) {
        Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
        calendar.setTime(DateUtils.parseDate(date));
        calendar.add(calendar.MONTH, -i); // 得到前一月
        String yestedayDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return getMonthLastDay(yestedayDate);

    }

    /**
     * 通过Hashset的add方法判断是否已经添加过相同的数据，如果已存在相同的数据则不添加
     * 
     * @param arlList
     */
    public static List<Integer> removeDuplicateWithOrder(String start, String end) {
        List<Date> list = getAllDates(start, end);
        List<Integer> newList = new ArrayList<Integer>();
        if (list != null && list.size() > 0) {
            for (Date date : list) {
                int week = day_Week(new SimpleDateFormat(STR_YYYY_MM_DD).format(date));
                if (!newList.contains(week)) {
                    newList.add(week);
                }
            }
        }
        return newList;
    }

    /**
     * 通过两个日期获取所在日期内的周
     * 
     * @param start
     * @param end
     * @return
     */
    public static String dayofWeek(String start, String end) {
        StringBuffer buf = new StringBuffer();
        List<Integer> list = removeDuplicateWithOrder(start, end);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                int week = list.get(i);
                buf.append(week);
                if (i != (list.size() - 1)) {
                    buf.append(",");
                }
            }
            day_Weeks_count = list.size();
        }
        return buf.toString();
    }

    /**
     * 当前时间加秒 返回时为 HH:mm:ss
     * 
     * @param size
     *            秒数
     * @return
     */
    public static String addTime(int size) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.SECOND, size);
        // System.out.println(new SimpleDateFormat("yyyy-MM-dd
        // HH:mm:ss").format(cal.getTime()));
        return new SimpleDateFormat("HH:mm:ss").format(cal.getTime());
    }

    /**
     * 时间加秒
     * 
     * @param day
     * @param size
     * @return
     */
    public static String addTime(String day, int size) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.SECOND, size);
        return new SimpleDateFormat("HH:mm:ss").format(cal.getTime());
    }

    /**
     * 当前时间加秒 返回时为 yyyy-MM-dd HH:mm:ss
     * 
     * @param size
     *            秒数
     * @return
     */
//    public static String addTimeS(int size) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(parseDate(day, "yyyy-MM-dd HH:mm:ss"));
//        cal.add(Calendar.SECOND, size);
//        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
//    }

    /**
     * 当前时间加秒 返回时为 yyyy-MM-dd HH:mm:ss
     * 
     * @param day
     *            yyyy-MM-dd HH:mm:ss
     * @param size
     * @return
     */
    public static String addTimeS(String day, int size) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseDate(day, "yyyy-MM-dd HH:mm:ss"));
        cal.add(Calendar.SECOND, size);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
    }

    /**
     * 判断s1是否大于s2
     * 
     * @param s1
     *            yyyy-MM-dd HH:mm:ss
     * @param s2
     *            yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static boolean bigTime(String s1, String s2) {
        boolean sign = false;
        int result = result(s1, s2);
        if (result > 0) {
            sign = true;
        } else {
            sign = false;
        }
        return sign;
    }

    /**
     * 判断s1是否等于s2
     * 
     * @param s1
     *            yyyy-MM-dd HH:mm:ss
     * @param s2
     *            yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static boolean equalTime(String s1, String s2) {
        boolean sign = false;
        int result = result(s1, s2);
        if (result == 0) {
            sign = true;
        } else {
            sign = false;
        }
        return sign;
    }

    /**
     * 判断s1是否小于s2
     * 
     * @param s1
     *            yyyy-MM-dd HH:mm:ss
     * @param s2
     *            yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static boolean smallTime(String s1, String s2) {
        boolean sign = false;
        int result = result(s1, s2);
        if (result < 0) {
            sign = true;
        } else {
            sign = false;
        }
        return sign;
    }

    /**
     * 时间判断
     * 
     * @param s1
     *            yyyy-MM-dd HH:mm:ss
     * @param s2
     *            yyyy-MM-dd HH:mm:ss layer 0:等于，1：小于，2：等于
     * @return
     */
    public static int compareToTime(String s1, String s2) {
        int layer;
        int result = result(s1, s2);
        if (result == 0) {
            layer = 0;
        } else if (result < 0) {
            layer = 1;
        } else {
            layer = 2;
        }
        return layer;
    }

    /**
     * 时间判断
     * 
     * @param s1
     * @param s2
     * @return
     */
    public static int result(String s1, String s2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(s1));
            c2.setTime(df.parse(s2));
        } catch (ParseException e) {
            System.err.println("格式不正确");
        }
        int result = c1.compareTo(c2);

        return result;
    }

    public static String time(Time time) {
        DateFormat fmt = new SimpleDateFormat("HH:mm:ss");
        return fmt.format(time);
    }

    /**
     * 根据指定日期格式将给出的日期字符串dateStr转换成一个日期对象
     * 
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date parseDate(String dateStr, String pattern) {
        if (dateStr == null || dateStr.length() == 0 || pattern == null || pattern.length() == 0)
            return null;
        DateFormat fmt = new SimpleDateFormat(pattern);
        Date result = null;
        try {
            result = fmt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 系统时间转换
     * 
     * @param timestamps
     * @return
     */
    public static String getCurTimestampStr(Timestamp timestamps) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.format(timestamps);
    }

    /**
     * 日期：yyyy-MM-dd 转换yyyy/MM/dd
     * 
     * @param timestamps
     * @return
     */
    public static String getCurDate(String timestamps) {
        DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
        return fmt.format(parseDate(timestamps));
    }

    /**
     * 日期：yyyy-MM-dd 转换yyyyMMdd
     * 
     * @param timestamps
     * @return
     */
    public static String getToday(String timestamps) {
        return new SimpleDateFormat(STR_YYYY_MM_DD_2).format(parseDate(timestamps));
    }

    /**
     * 将特定格式（yyyy-MM-dd）的字符串转换成日期对象//
     * 
     * @param dateStr
     * @return
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, "yyyy-MM-dd");
    }

    public static String getDate(String pattern) {
        DateFormat fmt = new SimpleDateFormat(pattern);
        return fmt.format(new Date());
    }

    public static String toString(String date, String pattern) {
        Date dat = parseDate(date, pattern);
        if (dat != null) {
            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            return fmt.format(dat);
        } else {
            return "";
        }
    }

    /**
     * 根据符合yyyy-MM-dd日期格式的字符串，得到上周一<br>
     * 
     * @param str
     *            日期格式字符串
     * @return String
     */
    public static String getLastMonday(String str) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.setTime(parseDate(str));
        ca.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        ca.add(Calendar.WEEK_OF_YEAR, -1);
        ca.get(Calendar.DAY_OF_WEEK);
        return convertDataFormat(sf.format(ca.getTime()));
    }

    /**
     * 根据符合yyyy-MM-dd日期格式的字符串，得到上周日<br>
     * 
     * @param str
     *            日期格式字符串
     * @return String
     */
    public static String getLastSunday(String str) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.setTime(parseDate(str));
        ca.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        ca.add(Calendar.WEEK_OF_YEAR, 0);
        ca.get(Calendar.DAY_OF_WEEK);
        return convertDataFormat(sf.format(ca.getTime()));
    }

    /**
     * 获取日期格式字符串
     * 
     * @param p_Date
     *            Date
     * @param p_Format
     *            String
     * @return String
     */
    public static String getFormatDate(Date p_Date, String p_Format) {
        SimpleDateFormat sdf = new SimpleDateFormat(p_Format);
        if (p_Date == null) {
            p_Date = new Date();
        }
        return sdf.format(p_Date);
    }

    /**
     * 得到指定日期的上一年
     * 
     * @param date
     * @return
     */
    public static Date getLastYear(Date now) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.add(Calendar.YEAR, -1);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 年月日
     * 
     * @param value
     * @return
     * @throws Exception
     */
    public static Date getYYMMDD(String value) throws Exception {
        StringBuffer bean = new StringBuffer();
        Date returValue = null;
        String a = value;
        if (value != null) {
            String b = a.substring(0, 4);
            bean.append(b);
            if (a.indexOf("-") != -1) {
                returValue = parseDate(getDate(value.toString()));
            } else {
                bean.append("-" + a.substring(4, 6) + "-" + a.substring(6, 8));
                returValue = parseDate(bean.toString());
            }

        }
        return returValue;
    }

    /**
     * yyyyMMdd转换yyyy-MM-dd
     * 
     * @param value
     * @return
     * @throws Exception
     */
    public static String getYYMMDDString(String value) throws Exception {
        StringBuffer bean = new StringBuffer();
        String returValue = null;
        String a = value;
        if (value != null) {
            String b = a.substring(0, 4);
            bean.append(b);
            if (a.indexOf("-") != -1) {
                returValue = value.toString();
            } else {
                bean.append("-" + a.substring(4, 6) + "-" + a.substring(6, 8));
                returValue = bean.toString();
            }

        }
        return returValue;
    }

    /**
     * 年月日时分秒
     * 
     * @param value
     * @return
     * @throws Exception
     */
    public static Date getYYMMDDHHmmss(String value) throws Exception {
        StringBuffer bean = new StringBuffer();
        Date returValue = null;
        String a = value;
        if (value != null) {
            String b = a.substring(0, 4);
            bean.append(b);
            if (a.indexOf("-") != -1 && a.indexOf(":") != -1) {
                returValue = getFormatDate(getDate(value.toString()));
            } else {
                bean.append("-" + a.substring(4, 6) + "-" + a.substring(6, 8) + " " + a.substring(8, 10) + ":" + a.substring(10, 12) + ":" + a.substring(12, 14));
                returValue = getFormatDate(bean.toString());
            }
        }
        return returValue;
    }

    public static String getYYMMDDHHmmssString(String value) throws Exception {
        StringBuffer bean = new StringBuffer();
        String returValue = null;
        String a = value;
        if (value != null) {
            String b = a.substring(0, 4);
            bean.append(b);
            if (a.indexOf("-") != -1 && a.indexOf(":") != -1) {
                returValue = value.toString();
            } else {
                bean.append("-" + a.substring(4, 6) + "-" + a.substring(6, 8) + " " + a.substring(8, 10) + ":" + a.substring(10, 12) + ":" + a.substring(12, 14));
                returValue = bean.toString();
            }
        }
        return returValue;
    }

    /**
     * 时分秒
     * 
     * @param value
     * @return
     * @throws Exception
     */
    public static Date getHHmmss(String value) throws Exception {
        StringBuffer bean = new StringBuffer();
        Date returValue = null;
        String a = value;
        if (value != null) {
            String b = a.substring(0, 2);
            bean.append(b);
            if (a.indexOf(":") != -1) {
                returValue = getDateByString(value.toString(), "HH:mm:ss");
            } else {
                bean.append(":" + a.substring(2, 4) + ":" + a.substring(4, 6));
                returValue = getDateByString(bean.toString(), "HH:mm:ss");
            }
        }
        return returValue;
    }

    public static String getHHmmssString(String value) throws Exception {
        StringBuffer bean = new StringBuffer();
        String returValue = null;
        String a = value;
        if (value != null) {
            String b = a.substring(0, 2);
            bean.append(b);
            if (a.indexOf(":") != -1) {
                return returValue = value.toString();
            } else {
                bean.append(":" + a.substring(2, 4) + ":" + a.substring(4, 6));
                return returValue = bean.toString();
            }
        }
        return returValue;
    }

    /**
     * 日期转换
     * 
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date getFormatDate(String str) throws ParseException {
        if (str == null || "".equals(str))
            return null;
        if (str.length() <= 10) {
            return getDateByString(str, "yyyy-MM-dd");

        } else {
            return getDateByString(str, "yyyy-MM-dd HH:mm:ss");
        }
    }

    public static Date getDateByString(String str, String pattern) throws ParseException {
        SimpleDateFormat df3 = new SimpleDateFormat();
        df3.applyPattern(pattern);
        return df3.parse(str);
    }

    /**
     * 判断两个日期对象是否是同一天
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isSameDay(Date d1, Date d2) {
        boolean result = false;
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {
            result = true;
        }
        return result;
    }

    /**
     * 获取当前的日期String yyyyMMddHHmmss
     * 
     * @return
     */
    public static String getCurTimestampStryyyyMMddHHmmss() {
        DateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
        return fmt.format(System.currentTimeMillis());
    }

    /**
     * 获取当前系统时间yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String getCurTimestampStr() {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.format(System.currentTimeMillis());
    }

    /**
     * 格式化当前的日期String yyyyMMddHHmmss
     * 
     * @param currentTimeMillis
     * @return
     */
    public static String getCurTimestampStrS(Timestamp currentTimeMillis) {
        DateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
        return fmt.format(currentTimeMillis);
    }

    /**
     * yyyyMMdd
     * 
     * @return
     */
    public static String getCurDate(Date obj) {
        DateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(obj);
    }
    
    /**
     * yyyyMMdd
     * 
     * @return
     */
//    public static String getDBTimeStamp() {
//    	SnowDao dao=SnowDaos.newInstance();
//    	String obj=(String) dao.selectOne("BisMPOSPay.bizDQL.ONL_DB_DATE_TIME.sltTimeStamp");
//        return obj;
//    }
    

    
    /**
     * yyyyMMdd
     * 
     * @return
     */
    public static String getCurDateyyyyMMdd() {
        DateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(new Date());
    }

    /**
     * 日期转换
     * 
     * @param date
     * @return
     */
    public static String getCommDateStr(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);
    }

    /**
     * 日期转换成Date (yyyy-MM-dd)
     * 
     * @param d
     * @return
     */
    public static Date Day_D(String date) {
        try {
            return new SimpleDateFormat(STR_YYYY_MM_DD).parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取系统日期yyyyMMdd
     * 
     * @return
     */
    public static String today() {
        return new SimpleDateFormat("yyyyMMdd").format(Day_D(DT_DAY()));
    }

    public static String DT_DATE(String date) {
        String day = null;
        try {
            day = new SimpleDateFormat(STR_YYYY_MM_DD).format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * 转化日期yyyyMMdd
     * 
     * @param date
     * @return
     */
    public static String DT_DATE2(String date) {
        String day = null;
        try {
            day = new SimpleDateFormat("yyyyMMdd").format(DateUtils.parseDate(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * 获取系统当前时间
     * 
     * @return
     */
    public static String time() {
        String day = null;
        try {
            DateFormat fmt = new SimpleDateFormat("HHmmss");
            day = fmt.format(System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * 获取系统当前时间
     * 
     * @return
     */
    public static String times() {
        String day = null;
        try {
            DateFormat fmt = new SimpleDateFormat("HH:mm:ss");
            day = fmt.format(System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * 传入Date和指定的格式，将Date转为指定格式的String
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String parseDate(Date date, String pattern) {
        DateFormat fmt = new SimpleDateFormat(pattern);
        String result = fmt.format(date);
        return result;
    }

    public static String getCommDateStr(Timestamp ts) {
        if (ts == null)
            return "";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(ts);
    }

    public static String getTimestamp(Timestamp ts) {
        if (ts == null)
            return "";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(ts);
    }

//    public static String getTimestampString(String date, String hour, String minute) {
//        if (date == null || date.trim().length() != 10)
//            date = getDate("yyyy-MM-dd");
//        if (hour == null)
//            hour = StringUtil.fill("", '0', 2, StringUtil.FRONT);
//        else if (hour.trim().length() < 2)
//            hour = StringUtil.fill(hour, '0', 2, StringUtil.FRONT);
//        else
//            hour = Double.parseDouble(hour) < 24 ? hour.trim().substring(0, 2) : "00";
//        if (minute == null)
//            minute = StringUtil.fill("", '0', 2, StringUtil.FRONT);
//        else if (minute.trim().length() < 2)
//            minute = StringUtil.fill(minute, '0', 2, StringUtil.FRONT);
//        else
//            minute = Double.parseDouble(minute) < 60 ? minute.trim().substring(0, 2) : "00";
//        return date + " " + hour + ":" + minute + ":00.000";
//    }
//
//    public static String getTimestampString(String date, String hour, String minute, String second) {
//        if (date == null || date.trim().length() != 10)
//            date = getDate("yyyy-MM-dd");
//        if (hour == null)
//            hour = StringUtil.fill("", '0', 2, StringUtil.FRONT);
//        else if (hour.trim().length() < 2)
//            hour = StringUtil.fill(hour, '0', 2, StringUtil.FRONT);
//        else
//            hour = Double.parseDouble(hour) < 24 ? hour.trim().substring(0, 2) : "00";
//        if (minute == null)
//            minute = StringUtil.fill("", '0', 2, StringUtil.FRONT);
//        else if (minute.trim().length() < 2)
//            minute = StringUtil.fill(minute, '0', 2, StringUtil.FRONT);
//        else
//            minute = Double.parseDouble(minute) < 60 ? minute.trim().substring(0, 2) : "00";
//        if (second == null)
//            second = StringUtil.fill("", '0', 2, StringUtil.FRONT);
//        else if (second.trim().length() < 2)
//            second = StringUtil.fill(second, '0', 2, StringUtil.FRONT);
//        else
//            second = Double.parseDouble(second) < 60 ? second.trim().substring(0, 2) : "00";
//        return date + " " + hour + ":" + minute + ":" + second + ".000";
//    }
//
//    /**
//     * 传入日期，小时，分转为yy-mm-dd 00:00:00.000格式的字符串
//     * 
//     * @param date
//     * @param hour
//     * @param minute
//     * @return
//     */
//    public static String getTimestampStringWithoutSelfDefine(String date, String hour, String minute) {
//        if (StringUtils.isEmpty(date)) {
//            return null;
//        }
//        if (date == null || date.trim().length() != 10)
//            date = getDate("yyyy-MM-dd");
//        if (hour == null)
//            hour = StringUtil.fill("", '0', 2, StringUtil.FRONT);
//        else if (hour.trim().length() < 2)
//            hour = StringUtil.fill(hour, '0', 2, StringUtil.FRONT);
//        else
//            hour = Double.parseDouble(hour) < 24 ? hour.trim().substring(0, 2) : "00";
//        if (minute == null)
//            minute = StringUtil.fill("", '0', 2, StringUtil.FRONT);
//        else if (minute.trim().length() < 2)
//            minute = StringUtil.fill(minute, '0', 2, StringUtil.FRONT);
//        else
//            minute = Double.parseDouble(minute) < 60 ? minute.trim().substring(0, 2) : "00";
//        return date + " " + hour + ":" + minute + ":00.000";
//    }

    /**
     * 获取年份
     * 
     * @param dataStr
     * 
     * @return
     */
    public static String getYear(String dataStr) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(parseDate(dataStr));
        return String.valueOf(ca.get(Calendar.YEAR));
    }

    /**
     * 获取月份
     * 
     * @param dataStr
     * 
     * @return 返回格式为: 01,02,03,04,05,06,07,08,09,10,11,12
     */
    public static String getMonth(String dataStr) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(parseDate(dataStr));
        String tmpMonthStr = "0" + String.valueOf((ca.get(Calendar.MONTH) + 1));
        return tmpMonthStr.substring(tmpMonthStr.length() - 2);
    }

    /**
     * 获取春夏秋冬季
     * 
     * @param dataStr
     * 
     * @return 返回1，2，3，4
     */
    public static String getQuarter(String dataStr) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(parseDate(dataStr));
        // 1,2,3月 返回1 4,5,6月返回2 ...
        return String.valueOf(ca.get(Calendar.MONTH) / 3 + 1);
//      return String.valueOf(Math.round(ca.get(Calendar.MONTH) / 3 + 1));
    }

    /**
     * 返回格式为yyyy-mm-dd-hh.mm.ss.ffffff的Timestamp
     * 
     * @param timestampStr
     * @return
     */
    public static Timestamp getTimestamp(String timestampStr) {
        if (timestampStr != null && !timestampStr.equals("")) {
            return Timestamp.valueOf(timestampStr.substring(0, 10) + " " + timestampStr.substring(11, 13) + ":" + timestampStr.substring(14, 16) + ":" + timestampStr.substring(17));
        } else {
            return null;
        }
    }

//    public static Timestamp Timestamp_String(String Timestamps) {
//        if (Timestamps != null && StringUtils.isNotEmpty(Timestamps)) {
//            return Timestamp.valueOf(Timestamps);
//        }
//        return null;
//    }

    public static String formatOrderDate(Object date) {
        String dates = new SimpleDateFormat(STR_YY_MM_DD,Locale.CANADA).format(date);
        return dates;
    }

    public static Timestamp getBeginTimeStamp(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static Timestamp getEndTimeStamp(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 返回yyMMdd的日期字符串
     * 
     * @return
     */
    public static String getDateString() {
        // SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        return formatOrderDate(new Date());
    }

    /**
     * oracle TimeStamp类型字符串拼接 TimeStamp：yyyy-mm-dd hh24:mi:ss.ff
     * 
     * @param timestamp
     * @return
     */
    public static String timestampOracle(Timestamp timestamp) {
        return "to_timestamp('" + timestamp + "','yyyy-mm-dd hh24:mi:ss.ff')";
    }

    /**
     * 时间转换yyyy-MM-dd HH:mm:ss
     * 
     * @param timestamp
     * @return
     */
    public static String timestamp(Timestamp timestamp) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.format(timestamp);
    }

    /**
     * oracle Date类型转换Timestamp类型(开始)
     * 
     * @param date
     *            yyyy-mm-dd
     * @return Timestamp yyyy-mm-dd hh24:mi:ss.ff
     */
    public static Timestamp OREACLE_BEAGIN_TIMESTAMP(String date) {
        return getBeginTimeStamp(parseDate(date));
    }

    /**
     * oracle Date类型转换Timestamp类型字符串拼接(开始)
     * 
     * @param date
     * @return String yyyy-mm-dd hh24:mi:ss.ff
     */
    public static String OREACLE_BEAGIN_TIMESTAMP_STRING(String date) {
        return timestamp(OREACLE_BEAGIN_TIMESTAMP(date));
    }

    /**
     * oracle Date类型转换Timestamp类型(结束)
     * 
     * @param date
     *            yyyy-mm-dd
     * @return Timestamp yyyy-mm-dd hh24:mi:ss.ff
     */
    public static Timestamp OREACLE_END_TIMESTAMP(String date) {
        return getEndTimeStamp(parseDate(date));
    }

    /**
     * oracle Date类型转换Timestamp类型字符串拼接(结束)
     * 
     * @param date
     * @return String yyyy-mm-dd hh24:mi:ss.ff
     */
    public static String OREACLE_END_TIMESTAMP_STRING(String date) {
        return timestamp(OREACLE_END_TIMESTAMP(date));
    }

    /**
     * oracle date类型字符串拼接 date：yyyy-mm-dd hh24:mi:ss
     * 
     * @param timestamp
     * @return
     */
    public static String to_dateS(java.sql.Date timestamp) {
        return "to_date('" + timestamp + "','yyyy-mm-dd hh24:mi:ss')";
    }

    /**
     * oracle date类型字符串拼接 date：yyyy-mm-dd
     * 
     * @param timestamp
     * @return
     */
    public static String to_date(java.sql.Date date) {
        return "to_date('" + date + "','yyyy-mm-dd')";
    }

    /**
     * 返回yyMMdd的日期字符串
     * 
     * @return
     */
    // public static String getDateString()
    // {
    // SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
    // return sdf.format(new Date());
    // }
    /**
     * 返回yyyyMMdd的日期字符串
     * 
     * @param date
     * @return
     */
    public static String getDateString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    /**
     * 返回yyyyMM的日期字符串
     * 
     * @return
     */
    public static String getMonthString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.format(new Date());
    }

    /**
     * 传入YYYYMMDD字符串，返回Date
     */

    public static Date getSqlDate(String date) {
        return parseDate(date, "yyyyMMdd");
    }

    /**
     * 传入YYYYMMDD字符串，返回Date
     */

    public static Date getUtilDate(String date) {
        return parseDate(date, "yyyy-MM-dd");
    }

    /**
     * 传入Date，返回之后的一天
     */
    public static Date getNextDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + 1);
        return calendar.getTime();
    }

    /**
     * 获取今天是本年的第几周
     * 
     * @return
     */
    public static int day_Week() {
        Calendar calendar = Calendar.getInstance();
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        return weekOfYear;
    }

    /**
     * 得到起始日期和结束日期之间的所有日期
     * 
     * @param start
     *            :其实日期
     * @param end
     *            :结束日期
     * @return
     */
    public static List<Date> getAllDates(String start, String end) {
        return getAllDates(parseDate(start), parseDate(end));
    }

    /**
     * 通过年获取本年的所有天数
     * 
     * @param YEAR
     * @return
     */
    public static List<Date> getAllDates(String YEAR) {
        return getAllDates(parseDate(getCurrentYearFirst(YEAR)), parseDate(getCurrentYearEnd(YEAR)));
    }

    /**
     * 得到起始日期和结束日期之间的所有日期,根据指定格式
     * 
     * @param start
     *            :其实日期
     * @param end
     *            :结束日期
     * @param pattern
     *            :指定日期格式
     * @return
     */
    public static List<Date> getAllDates(String start, String end, String pattern) {
        return getAllDates(parseDate(start, pattern), parseDate(end, pattern));
    }

    // 获得本年第一天的日期
    public static String getCurrentYearFirst() {
        int yearPlus = getYearPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, yearPlus);
        Date yearDay = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preYearDay = df.format(yearDay);
        return preYearDay;
    }

    public static int getYearPlus() {
        Calendar cd = Calendar.getInstance();
        int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 获得当天是一年中的第几天
        cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
        cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
        int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
        if (yearOfNumber == 1) {
            return -MaxYear;
        } else {
            return 1 - yearOfNumber;
        }
    }

    /**
     * 获取本月的最后一天
     * 
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getCurrentYearEnd() {
        Calendar cDay1 = Calendar.getInstance();
        cDay1.setTime(new Date());
        final int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date lastDate = cDay1.getTime();
        lastDate.setDate(lastDay);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.format(lastDate);
        return dateFormat.format(lastDate).toString();
    }

    /**
     * 获得本年最后一天的日期
     * 
     * @param YEAR
     * @return
     */
    public static String getCurrentYearEnd(String YEAR) {
        return YEAR + "-12-31";
    }

    /**
     * 获得本年第一天的日期
     * 
     * @param YEAR
     * @return
     */
    public static String getCurrentYearFirst(String YEAR) {
        return YEAR + "-01-01";
    }

    /**
     * 获取明天日期
     * 
     * @return
     */
    public static String nextDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        return new SimpleDateFormat(STR_YYYY_MM_DD).format(calendar.getTime());
    }
    /**
     * 得到起始日期和结束日期之间的所有日期
     * 
     * @param start:其实日期
     * @param end:结束日期
     * @return
     */
    public static List<String> getAllDatesString(String start, String end) {
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(parseDate(start));
        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTime(parseDate(end));
        return dayAllStr(calendar1, calendar2, start);
    }
    public static List<String> dayAllStr(Calendar calendar1, Calendar calendar2, String start) {
        List<String> list = new ArrayList<String>();
        list.add(new SimpleDateFormat(STR_YYYY_MM_DD_2).format(parseDate(start)));
        while (calendar1.compareTo(calendar2) < 0) {
            calendar1.add(Calendar.DATE, 1);
            list.add(new SimpleDateFormat(STR_YYYY_MM_DD_2).format(calendar1.getTime()));
        }
        return list;
    }
    /**
     * 获取系统当天日期yyyyyMMdd
     * 
     * @return
     * @throws ParseException
     */
    public static String DT_DAYyyyyyMMdd() {
        String day = null;
        try {
            day = new SimpleDateFormat(STR_YYYY_MM_DD_2).format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }
    
    
    public static String DT_DfdayTimeHHmmss() {
        String day = null;
        try {
            day = dfdayTimeHHmmss.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }   
    
    
    
    /**
     * yyyyMMdd转换MM-dd
     * 
     * @param value
     * @return
     * @throws Exception
     */
    public static String getMMDDString(String value){
        StringBuffer bean = new StringBuffer();
        String returValue = null;
        String a = value;
        if (value != null && value.length()>=8) {
            if (a.indexOf("-") != -1) {
                returValue = value.toString();
            } else {
                bean.append(a.substring(4, 6) + "-" + a.substring(6, 8));
                returValue = bean.toString();
            }

        }
        return returValue;
    }
    
    /**
     * 根据格式获取实例
     * 
     * @param pattern
     * @return
     */
    public static SimpleDateFormat getSimpleDateFormat(String pattern){
        return new SimpleDateFormat(pattern);
    }

    /**
     * 格式化
     * 格式yyyyMMdd
     * 
     * @return
     */
    public static String formatYearMonthDay(Date date){
        return new SimpleDateFormat(STR_YYYY_MM_DD_2).format(date);
    }
    
    
    
    /**
     * 获得系统当前日期时间
     * 
     * @return
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat sdf = getSimpleDateFormat(DATE_TIME_18_FMT_HOUR);
        return sdf.format(Calendar.getInstance().getTime());
    }
    
    
    /**
     * 
     * 功能描述: 将毫秒数转化成 x天x小时x分钟<br>
     *
     * @param args
     * @throws Exception
     */
    public static String formatDuring(long times){
        long days = times / (1000 * 60 * 60 * 24);  
        long hours = (times % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);  
        long minutes = (times % (1000 * 60 * 60)) / (1000 * 60);  
        long seconds = (times % (1000 * 60)) / 1000;  
        return days + " 天 " + hours + " 小时 " + minutes + " 分钟 "  
                + seconds + " 秒 ";  
    }
    
    /**
     * 返回yyMMddHHmmss的日期字符串
     * 
     * @return
     */
     public static String getyyyyMMddHHmmssDateString() {
    	 SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_14_FMT);
    	 return sdf.format(new Date());
     }
     
     /**
 	 * 获取指定日期的前一天
 	 * @param date yyyyMMdd
 	 * @return yyyyMMdd
 	 */
 	public static String getBeforeDay(String date) {
 		SimpleDateFormat sdf = new SimpleDateFormat(STR_YYYY_MM_DD_2);
 		Calendar calendar = Calendar.getInstance();
 		try {
			calendar.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
 		calendar.add(Calendar.DAY_OF_MONTH, -1);
 		
 		return sdf.format(calendar.getTime());
 	}
    
    public static void main(String[] args) throws Exception {
        // System.out.println(DateUtils.convertDataFormat(day_Forward(DateUtils.DT_DAY(), 1)).substring(0, 8));
        // DateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // System.out.println(fmt.format(new Timestamp(System.currentTimeMillis())));
//      System.out.println("d=" + getCurDate("2008-08-26"));
//      System.out.println(Time(new Time(System.currentTimeMillis())));
//      System.out.println(getAllDates("2011-11-01", "2012-03-26").size());
//      System.out.println(day_MONTH_back(8));
//      
//      System.out.println(getMONTN("2013-01-22", "2013-04-01"));
        
//      System.out.println(DateUtils.formatYearMonthDay(DateUtils
//                      .parseDate("2014-05-06 22:22:22")));
//      System.out.println(DT_DAYyyyyyMMdd()+"="+DT_DfdayTimeHHmmss());
//      System.out.println(convertDataFormat(date_back(DT_DAY(),5)));
//        System.out.println(getBeforeDay("20160101151530"));
//        SimpleDateFormat sdf = new SimpleDateFormat(STR_YYYY_MM_DD_2);
//        System.out.println(sdf.format(new Date()));
    	
    	String s="2015-12-28 16:46:02.408266";
    	System.out.println(s.substring(0, s.lastIndexOf(".")));
    }

}