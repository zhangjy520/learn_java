package cn.gukeer.common.utils;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    private static SimpleDateFormat standardFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat orderDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static int ONE_MIN = 60;
    public static int ONE_HOUR = 60 * ONE_MIN;
    public static int ONE_DAY = 24 * ONE_HOUR;
    public static int ONE_YEAR = 365 * ONE_DAY;

    public static Date parseOrderDate(String str) throws ParseException {
        return orderDateFormat.parse(str);
    }

    public static Date parseDate(String str) throws ParseException {
        return sdf.parse(str);
    }

    public static String formatDate(Date date) {
        return sdf.format(date);
    }

    public static Date parseStandardDate(String str) {
        try {
            return standardFormat.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public static String formatStandardDate(Date date) {
        if (date == null) {
            return null;
        }
        return standardFormat.format(date);
    }

    public static Date getNextMondy(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int w = calendar.get(Calendar.DAY_OF_WEEK);
        int addDay = 0;
        if (w == 1) {
            addDay = 1;
        } else {
            addDay = 9 - w;
        }
        calendar.add(Calendar.DAY_OF_MONTH, addDay);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 1);

        return calendar.getTime();
    }

    public static String formatOrderDate(Date date) {
        return orderDateFormat.format(date);
    }

    private static SimpleDateFormat onlyDate = new SimpleDateFormat("yyyy-MM-dd");

    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date getTodayStart() {
        Calendar calendar = Calendar.getInstance();
        return doGetDateStart(calendar);
    }

    private static Date doGetDateStart(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    private static Date doGetDateEnd(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTime();
    }


    public static Date getDateStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return doGetDateStart(calendar);
    }

    public static Date getDateEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return doGetDateEnd(calendar);
    }

    public static Date getTodayEnd() {
        Calendar calendar = Calendar.getInstance();
        return doGetDateEnd(calendar);
    }

    public static Date parseOnlyDate(String str) {
        try {
            return onlyDate.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatOnlyDate(Date date) {
        return onlyDate.format(date);
    }

    public static Date parseTime(String time) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("HH:mm");
        Date date = f.parse(time);
        return date;
    }

    public static double getDeltaInMinutes(Date before, Date after) {
        if (before == null || after == null) {
            return 0;
        }
        long beforeMs = before.getTime();
        long afterMs = after.getTime();
        long ms = afterMs - beforeMs;
        return ms / (double) (1000 * ONE_MIN);
    }

    public static int getAgeByBornDate(Date bornDate) {
        Calendar now = Calendar.getInstance();
        Calendar born = Calendar.getInstance();
        born.setTime(bornDate);


        now.add(Calendar.YEAR, -born.get(Calendar.YEAR));
        now.add(Calendar.MONTH, -born.get(Calendar.MONTH));
        now.add(Calendar.DAY_OF_MONTH, -born.get(Calendar.DAY_OF_MONTH));

        return now.get(Calendar.YEAR);
    }

    public static int getAgeByBornDate(String bornDate) {
        return getAgeByBornDate(DateUtils.parseOnlyDate(bornDate));
    }

    public static Date getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static String formatTime(Date date) {
        if (date == null) {
            return "00:00";
        }
        SimpleDateFormat f = new SimpleDateFormat("HH:mm");
        return f.format(date);
    }

    public static Date fromTimestamp(long timestamp) {
        timestamp = 1413967965 * 1000l;
        Date date = new Date();
        date.setTime(timestamp);
        return date;
    }


    public static String intervalNowTimeToView(long timestamp) {

        return "10分钟前";
    }

    public static Long yyyyMMddToMillis(String date) throws ParseException {
        if ("".equals(date) || null == date) {
            return Long.valueOf(0);
        } else {
            Calendar c = Calendar.getInstance();

                c.setTime(new SimpleDateFormat("yyyyMMdd").parse(date));

            return c.getTimeInMillis();
        }

    }

    public static String millsToyyyyMMdd(Long mills) {

        if (null != mills && mills > 0) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(mills);
            return formatter.format(calendar.getTime());
        } else {
            return "";
        }

    }

    public static String millsToMMddHHmm(Long mills) {

        if (null != mills && mills > 0) {
            DateFormat formatter = new SimpleDateFormat("MM月dd日  HH:mm");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(mills);
            return formatter.format(calendar.getTime());
        } else {
            return "";
        }

    }


    public static String millsToyyyyMMddHHmmss(Long mills) {

        if (null != mills && mills > 0) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(mills);
            return formatter.format(calendar.getTime());
        } else {
            return "";
        }

    }

    /*判断当前时间和发布时间之间差别*/
    public static boolean outOfDate(Long mills) {
        if (System.currentTimeMillis() > mills) {
            return false;
        }
        return true;
    }

    public static String getYmdw(Integer type) {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy年MM月dd日");
        String day = dateFm.format(date);
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        if (type == 1) {
            return weekDays[w];
        } else if (type == 2) {
            String months[] = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};
            Integer str = Integer.parseInt(new SimpleDateFormat("MM").format(date));
            return months[str - 1];
        } else if (type == 3) {
            return new SimpleDateFormat("dd").format(date);
        } else {
            return day + " " + weekDays[w];
        }
    }

    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }


    /**
     * @param args
     * @throws UnsupportedEncodingException
     * @throws ParseException
     */
    public static void main(String[] args) throws UnsupportedEncodingException, ParseException {


        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMdd");
        dateFm.format(date);
        System.out.println(getYmdw(1));//1 :星期  2：月份  3 号  其他：全日期
        System.out.println(getYmdw(2));//1 :星期  2：月份  3 号  其他：全日期
        System.out.println(getYmdw(3));//1 :星期  2：月份  3 号  其他：全日期
        System.out.println(getYmdw(4));//1 :星期  2：月份  3 号  其他：全日期
        System.out.println(millsToMMddHHmm(System.currentTimeMillis()));
/*

        int aa = Integer.parseInt(new SimpleDateFormat("MM").format(1456019729000L));
        String month[] = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};
        System.out.println(month[aa - 1]);
        System.out.println( Long.valueOf(2016));
*/
        /*millsToyyyyMMdd(System.currentTimeMillis());*/

    }

}
