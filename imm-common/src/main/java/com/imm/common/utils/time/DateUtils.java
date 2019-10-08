package com.imm.common.utils.time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author kevin
 */
public class DateUtils {

    protected static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static final String YYYY = "yyyy";

    public static final String YYYY_MM = "yyyy-MM";

    public static final String YYYYMMDD = "yyyyMMdd";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String HH_MM_SS = "HH:mm:ss";

    private static SimpleDateFormat dtsdf = new SimpleDateFormat(HH_MM_SS);

    private static SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);

    private static SimpleDateFormat ldf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);

    /**
     * 转换最小时间
     *
     * @param date 日期时间
     * @return 最小时间的日期 00:00:00
     */
    public static Date toMinTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 转换为最大时间
     *
     * @param date 日期时间
     * @return 最大时间的日期 23:59:59
     */
    public static Date toMaxTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static String getNowString() {
        return ldf.format(new Date());
    }

    public static String getAyearAgoString() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        return ldf.format(calendar.getTime());
    }

    /**
     * 获取多久之前的数据
     *
     * @param day
     * @return
     */
    public static String getDateBeforeString(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -day);
        return ldf.format(calendar.getTime());
    }

    /**
     * 获取多久之前的数据
     *
     * @param day
     * @return
     */
    public static String getDateBeforeShortString(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -day);
        return sdf.format(calendar.getTime());
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);

            return Integer.parseInt(String.valueOf(between_days));
        } catch (Exception e) {
            logger.error("计算两个日期之间相差的天数发生异常：{}", e.getMessage(), e);
        }
        return 0;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(String smdate, String bdate) {
        try {
            Date startDate = sdf.parse(smdate);
            Date endDate = sdf.parse(bdate);

            return daysBetween(startDate, endDate);
        } catch (Exception e) {
            logger.error("计算两个日期之间相差的天数发生异常：{}", e.getMessage(), e);
        }
        return 0;
    }

    public static String format(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    public static String formatShort(Date date) {
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    public static String formatLong(Date date) {
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return ldf.format(date);
    }

    /**
     * 计算2个日期之间相差的  相差多少年月日
     * 比如：2011-02-02 到  2017-03-02 相差 6年，1个月，0天
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public static DayCompare daysComparePrecise(Date fromDate, Date toDate) {
        Calendar from = Calendar.getInstance();
        from.setTime(fromDate);
        Calendar to = Calendar.getInstance();
        to.setTime(toDate);
        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);
        int fromDay = from.get(Calendar.DAY_OF_MONTH);
        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);
        int toDay = to.get(Calendar.DAY_OF_MONTH);
        int year = toYear - fromYear;
        int month = toMonth - fromMonth;
        int day = toDay - fromDay;
        return DayCompare.builder().day(day).month(month).year(year).build();
    }

    /**
     * 计算2个日期之间相差的  相差多少年月日
     * 比如：2011-02-02 到  2017-03-02 相差 6年，1个月，0天
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public static DayCompare daysComparePrecise(String fromDate, String toDate, String pattern) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date startDate = sdf.parse(fromDate);
            Date endDate = sdf.parse(toDate);
            return daysComparePrecise(startDate, endDate);
        } catch (ParseException e) {
            logger.error("日期转换发生异常:{}", e.getMessage());
            throw e;
        }
    }

    /**
     * 计算2个日期之间相差的  以年、月、日为单位，各自计算结果是多少
     * 比如：2011-02-02 到  2017-03-02
     * 以年为单位相差为：6年
     * 以月为单位相差为：73个月
     * 以日为单位相差为：2220天
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public static DayCompare daysCompare(Date fromDate, Date toDate) {
        Calendar from = Calendar.getInstance();
        from.setTime(fromDate);
        Calendar to = Calendar.getInstance();
        to.setTime(toDate);
        //只要年月
        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);
        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);
        int year = toYear - fromYear;
        int month = toYear * 12 + toMonth - (fromYear * 12 + fromMonth);
        int day = (int) ((to.getTimeInMillis() - from.getTimeInMillis()) / (24 * 3600 * 1000));
        return DayCompare.builder().day(day).month(month).year(year).build();
    }

    /**
     * 计算2个日期之间相差的  以年、月、日为单位，各自计算结果是多少
     * 比如：2011-02-02 到  2017-03-02
     * 以年为单位相差为：6年
     * 以月为单位相差为：73个月
     * 以日为单位相差为：2220天
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public static DayCompare daysCompare(String fromDate, String toDate, String pattern) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date startDate = sdf.parse(fromDate);
            Date endDate = sdf.parse(toDate);
            return daysCompare(startDate, endDate);
        } catch (ParseException e) {
            logger.error("日期转换发生异常:{}", e.getMessage());
            throw e;
        }
    }

    /**
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date) {

        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    /**
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     *
     * @param date
     * @return
     */
    public static int getSeason(String date) {
        try {
            return getSeason(sdf.parse(date));
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 格式化日期 YYYYMMDD
     *
     * @return
     */
    public static Date fomatDate(String date) {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期 HH:MM:SS
     *
     * @return
     */
    public static Date fomatDateTime(String date) {
        try {
            return dtsdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期 HH:MM:SS
     *
     * @return
     */
    public static Date fomatDate(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static Date formatLongDate(String date) {
        try {
            return ldf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return seconds != null && !seconds.isEmpty() && !"null".equals(seconds) ? sdf.format(new Date(Long.valueOf(seconds))) : "";
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @return
     */
    public static String timeStamp2Date(String seconds) {
        return seconds != null && !seconds.isEmpty() && !"null".equals(seconds) ? ldf.format(new Date(Long.valueOf(seconds))) : "";
    }

    public static void main(String[] args) throws Exception {
        Date d = fomatDate("2018-10-15");
        Date[] dateArray = getTimeInterval(d, 1);
        System.out.println(formatShort(dateArray[0]));
        System.out.println(formatShort(dateArray[1]));
    }

    public static Date[] getTimeInterval(Date date) {
        return getTimeInterval(date, 1);
    }

    public static Date[] getTimeInterval(Date date, int cycle) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        Date startDate = cal.getTime();
        cal.add(Calendar.DATE, 6 * cycle + (cycle - 1));
        Date endDate = cal.getTime();
        return new Date[]{startDate, endDate};
    }

    public static Set<Date> getWeekDayList(Date date, int cycle) {
        Date[] dates = getTimeInterval(date, cycle);
        return findDates(dates[0], dates[1]);
    }

    public static Set<Date> getWeekDayList(Date date) {
        return getWeekDayList(date, 1);
    }

    public static Set<Date> findDates(Date beginDate, Date endDate) {
        Set<Date> set = new TreeSet<>();
        set.add(beginDate);
        Calendar startDate = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        startDate.setTime(beginDate);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(endDate);
        // 测试此日期是否在指定日期之后
        while (endDate.after(startDate.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            startDate.add(Calendar.DAY_OF_MONTH, 1);
            set.add(startDate.getTime());
        }
        return set;
    }

    /**
     * 判断指定日期是否在指定的开始日期from，指定的结束日期to之内
     *
     * @param time 指定日期
     * @param from 指定开始日期
     * @param to   指定结束日期
     * @return
     */

    public static boolean belongCalendar(Date time, Date from, Date to) {
        Calendar date = null;
        Calendar after = null;
        Calendar before = null;
        if (time != null) {
            date = Calendar.getInstance();
            date.setTime(time);
        }

        if (from != null) {
            after = Calendar.getInstance();
            after.setTime(from);
        }
        if (to != null) {
            before = Calendar.getInstance();
            before.setTime(to);
        }
        if (date.after(after) && date.before(before)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断指定日期是否小于指定的日期to
     *
     * @param time 指定日期
     * @param to   指定日期
     * @return
     */

    public static boolean lessThanCalendar(Date time, Date to) {
        Calendar date = null;
        Calendar before = null;
        if (time != null) {
            date = Calendar.getInstance();
            date.setTime(time);
        }
        if (to != null) {
            before = Calendar.getInstance();
            before.setTime(to);
        }
        if (date.before(before)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 把时间转换为：时分秒格式。
     *
     * @param second ：秒，传入单位为秒
     * @return
     */
    /**
     * 把时间转换为：时分秒格式。
     *
     * @param time
     * @return
     */
    public static String getTimeString(int time) {
        int miao = time % 60;
        int fen = time / 60;
        int hour = 0;
        if (fen >= 60) {
            hour = fen / 60;
            fen = fen % 60;
        }
        String timeString;
        String miaoString;
        String fenString;
        String hourString;
        if (miao < 10) {
            miaoString = "0" + miao;
        } else {
            miaoString = miao + "";
        }
        if (fen < 10) {
            fenString = "0" + fen;
        } else {
            fenString = fen + "";
        }
        if (hour < 10) {
            hourString = "0" + hour;
        } else {
            hourString = hour + "";
        }
        if (hour != 0) {
            timeString = hourString + ":" + fenString;
        } else {
            timeString = fenString + ":" + miaoString;
        }
        return timeString;
    }

    public static int getWeekday(Date today) {
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 指定日期N个月后的日期
     *
     * @param inputDate
     * @param number
     * @return YYYY-MM-DD
     */
    public static String getAfterMonthDate(Date inputDate, int number) {
        //获得一个日历的实例
        Calendar c = Calendar.getInstance();
        //设置日历时间
        c.setTime(inputDate);
        //在日历的月份上增加N个月
        c.add(Calendar.MONTH, number);
        return sdf.format(c.getTime());
    }
}
