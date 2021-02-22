//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lx.zhaopin.view;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint({"SimpleDateFormat"})
public class TimeUtils {
    public static final String FORMAT_YEAR = "yyyy";
    public static final String FORMAT_MONTH_DAY = "MM月dd日";
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_TIME = "HH:mm";
    public static final String FORMAT_MONTH_DAY_TIME = "MM月dd日  hh:mm";
    public static final String FORMAT_MONTH_DAY_TIME_EN = "MM-dd hh:mm";
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_DATE1_TIME = "yyyy/MM/dd HH:mm";
    public static final String FORMAT_DATE_TIME_SECOND = "yyyy_MM_dd_HH_mm_ss";
    public static final String FORMAT_DATE_SECOND = "MM/dd/yyyy HH:mm:ss";
    private static SimpleDateFormat sdf = new SimpleDateFormat();
    private static final int YEAR = 31536000;
    private static final int MONTH = 2592000;
    private static final int DAY = 86400;
    private static final int HOUR = 3600;
    private static final int MINUTE = 60;

    public TimeUtils() {
    }

    public static String getDescriptionTimeFromTimestamp(long timestamp) {
        long currentTime = System.currentTimeMillis();
        long timeGap = (currentTime - timestamp) / 1000L;
        System.out.println("timeGap: " + timeGap);
        String timeStr = null;
        if(timeGap > 31536000L) {
            timeStr = timeGap / 31536000L + "年前";
        } else if(timeGap > 2592000L) {
            timeStr = timeGap / 2592000L + "个月前";
        } else if(timeGap > 86400L) {
            timeStr = timeGap / 86400L + "天前";
        } else if(timeGap > 3600L) {
            timeStr = timeGap / 3600L + "小时前";
        } else if(timeGap > 60L) {
            timeStr = timeGap / 60L + "分钟前";
        } else {
            timeStr = "刚刚";
        }

        return timeStr;
    }

    public static String getTodayOrNot(long timestamp){
        long currentTime = System.currentTimeMillis();
        String timeStr = null;
        if(getDayTime_(timestamp).equals(getDayTime_(currentTime))){
            timeStr="今天"+getHourAndMin(timestamp);
        }else {
            timeStr=getTimeLine(timestamp);
        }
        return timeStr;
    }


    public static Date longToDate(long currentTime) {
        Date dateOld = new Date(currentTime);
        return dateOld;
    }

    public static String longToString(long currentTime, String formatType) {
        String strTime = "";
        Date date = longToDate(currentTime);
        strTime = dateToString(date, formatType);
        return strTime;
    }

    public static long stringToLong(String strTime, String formatType) {
        Date date = stringToDate(strTime, formatType);
        if(date == null) {
            return 0L;
        } else {
            long currentTime = dateToLong(date);
            return currentTime;
        }
    }

    public static Date stringToDate(String strTime, String formatType) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;

        try {
            date = formatter.parse(strTime);
        } catch (ParseException var5) {
            Log.e("date error", var5.toString());
        }

        return date;
    }

    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public static String dateToString(Date data, String formatType) {
        return (new SimpleDateFormat(formatType)).format(data);
    }

    public static String getTime_(long time) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        return format.format(new Date(time));
    }

    public static String getMonthTime_(long time) {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        return format.format(new Date(time));
    }

    public static String getYearTime_(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(new Date(time));
    }

    public static String getDayTime_(long time) {
        SimpleDateFormat format = new SimpleDateFormat("dd");
        return format.format(new Date(time));
    }

    public static String getTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date(time));
    }

    public static String getHourTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date(time));
    }

    public static String getMiniteTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("mm");
        return format.format(new Date(time));
    }

    public static String getTimeDetail(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(new Date(time));
    }

    public static String getTimeLine (long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(new Date(time));
    }

    public static String getTimeChina(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(new Date(time));
    }

    public static String getHourAndMin(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(new Date(time));
    }

    public static String getHour(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH");
        return format.format(new Date(time));
    }

    public static String getZoneTime(long timesamp) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date today = new Date(System.currentTimeMillis());
        Date otherDay = new Date(timesamp);
        int temp = Integer.parseInt(sdf.format(today)) - Integer.parseInt(sdf.format(otherDay));
        switch(temp) {
            case 0:
                result = "今天 ";
                break;
            case 1:
                result = "昨天 ";
                break;
            case 2:
                result = "前天 ";
                break;
            default:
                SimpleDateFormat format = new SimpleDateFormat("MM-dd");
                result = format.format(new Date(timesamp));
        }

        return result;
    }

    public static String getCurrentTime(String format) {
        if(format != null && !format.trim().equals("")) {
            sdf.applyPattern(format);
        } else {
            sdf.applyPattern("yyyy-MM-dd HH:mm");
        }

        return sdf.format(new Date());
    }

    public static boolean isAfter(Date olddate, Date newDate, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(olddate);
        cal.set(11, cal.get(11) + hours);
        Date da = cal.getTime();
        return newDate.after(da);
    }

    public static int getWeek(Date d) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        byte weekZh = 0;
        int week = now.get(7);
        if(week == 1) {
            weekZh = 7;
        } else {
            --week;
        }

        return weekZh;
    }

    public static String getWeekZh(Date d) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        String weekZh = "";
        int week = now.get(7);
        switch(week) {
            case 1:
                weekZh = "星期日";
                break;
            case 2:
                weekZh = "星期一";
                break;
            case 3:
                weekZh = "星期二";
                break;
            case 4:
                weekZh = "星期三";
                break;
            case 5:
                weekZh = "星期四";
                break;
            case 6:
                weekZh = "星期五";
                break;
            case 7:
                weekZh = "星期六";
        }

        return weekZh;
    }

    public static String[] getDateAfter(Date d, int day, String formatType) {
        if(day < 0) {
            return null;
        } else {
            String[] str = new String[day];

            for(int i = 0; i < day; ++i) {
                str[i] = dateToString(getDateAfter(d, i), formatType);
            }

            return str;
        }
    }

    public static Date getDateAfter(Date d, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(5, cal.get(5) + day);
        return cal.getTime();
    }

    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            smdate = sdf.parse(sdf.format(smdate));
        } catch (ParseException var11) {
            var11.printStackTrace();
        }

        try {
            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException var10) {
            var10.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / 86400000L;
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static int daysBetween(long smdate, long bdate) {
        return daysBetween(longToDate(smdate), longToDate(bdate));
    }
}
