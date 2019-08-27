package com.kidd.projectbase.utils;

import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    public static final String DATE_FORMAT_1 = "yyyy-MM-dd";
    public static final String DATE_FORMAT_2 = "HH:mm:ss";
    public static final String DATE_FORMAT_3 = "hh:mm a";
    public static final String DATE_FORMAT_4 = "dd/MM/yyyy";
    public static final String DATE_FORMAT_5 = "hh:mmaa dd/MM/yyyy";
    public static final String DATE_FORMAT_6 = "MM/dd/yyyy";
    public static final String DATE_FORMAT_7 = "HH:mm";
    public static final String DATE_FORMAT_8 = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS";
    public static final String DATE_FORMAT_9 = DATE_FORMAT_1 + " " + DATE_FORMAT_2;
    public static final String DATE_FORMAT_10 = "hh:mmaa";
    public static final String DATE_FORMAT_11 = "hh:mm dd/MM/yyyy";
    public static final String DATE_FORMAT_12 = "ddHHmmss";
    public static final String DATE_FORMAT_13 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_14 = "EEEE";
    public static final String DATE_FORMAT_15 = "HH:mm";
    public static final String DATE_FORMAT_16 = "dd";
    public static final String DATE_FORMAT_17 = "MM";
    public static final String DATE_FORMAT_18 = "yyyy";
    public static final String DATE_FORMAT_19 = "HH:mm dd/MM/yyyy";

    public static final String TIME_0H = "00:00:00";
    public static final String TIME_7H = "07:00:00";
    public static final int TIME_START = 0;
    public static final int TIME_END_MINUTE = 59;
    public static final int TIME_END_HOUR = 23;

    public static final long MINUTE_MILLIS = TimeUnit.MINUTES.toMillis(1);
    public static final long HOUR_MILLIS = TimeUnit.HOURS.toMillis(1);
    public static final long DAY_MILLIS = TimeUnit.DAYS.toMillis(1);

    public static String getCurrentDayName() {
        Calendar cal = Calendar.getInstance();
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                return "Thứ 2";
            case Calendar.TUESDAY:
                return "Thứ 3";
            case Calendar.WEDNESDAY:
                return "Thứ 4";
            case Calendar.THURSDAY:
                return "Thứ 5";
            case Calendar.FRIDAY:
                return "Thứ 6";
            case Calendar.SATURDAY:
                return "Thứ 7";
            default:
                return "Chủ Nhật";
        }
    }

    public static String getDayName(String day) {
        switch (day) {
            case "Monday":
                return "Thứ Hai";
            case "Tuesday":
                return "Thứ Ba";
            case "Wednesday":
                return "Thứ Tư";
            case "Thursday":
                return "Thứ Năm";
            case "Friday":
                return "Thứ Sáu";
            case "Saturday":
                return "Thứ Bảy";
            case "Sunday":
                return "Chủ Nhật";
            default:
                return day;
        }
    }

    public static String getCurrentDateName() {
        // current time
        StringBuilder time = new StringBuilder("Ngày ");
        Calendar cal = Calendar.getInstance();
        if (cal.get(Calendar.DAY_OF_MONTH) < 10)
            time.append("0");
        time.append(cal.get(Calendar.DAY_OF_MONTH));
        time.append(" tháng ");
        if (cal.get(Calendar.MONTH) + 1 < 10)
            time.append("0");
        time.append(cal.get(Calendar.MONTH) + 1);
        time.append(" năm ");
        time.append(cal.get(Calendar.YEAR));
        return time.toString();
    }

    public static String fortmatDateFromTimeString(String time) throws ParseException {
        String result = "";
        if (time == null || time.isEmpty()) {
            return result;
        }
        Date publishDate = new SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.getDefault()).parse(time);
        Date currentDate = new Date();
        int hour = 0;
        int min = 0;
        int day = 0;
        long totalSec = (currentDate.getTime() - publishDate.getTime());
        if (totalSec > 0) {
            day = (int) (totalSec / (60 * 60 * 24 * 1000));
            if (day >= 7) {
                return formatDate(time, DATE_FORMAT_11, DATE_FORMAT_4);
            } else if (day >= 2) {
                result = day + " ngày trước";
            } else if (day > 0) {
                result = "Hôm qua";
            } else {
                hour = (int) (totalSec / (60 * 60 * 1000));
                if (hour > 0) {
                    result = hour + " giờ trước";
                } else {
                    min = (int) (totalSec) / (60 * 1000);
                    if (min == 0) {
                        result = "Vừa xong";
                    } else {
                        result = min + " phút trước";
                    }
                }
            }
        }
        return result;
    }

    public static String formatDate(String date, String initDateFormat, String endDateFormat) throws ParseException {
        Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
        SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
        String parsedDate = formatter.format(initDate);

        return parsedDate;
    }

    public static Calendar convertStringToCalendar(String time, String format) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        try {
            cal.setTime(sdf.parse(time));
        } catch (ParseException e) {
            Log.d(DateUtil.class.getSimpleName(), "convertStringToCalendar: " + e.getMessage());
        }
        return cal;
    }

    public static String getMonthLunarName(int month) {
        switch (month) {
            case 1:
                return "Giêng";
            case 2:
                return "Hai";
            case 3:
                return "Ba";
            case 4:
                return "Tư";
            case 5:
                return "Năm";
            case 6:
                return "Sáu";
            case 7:
                return "Bảy";
            case 8:
                return "Tám";
            case 9:
                return "Chín";
            case 10:
                return "Mười";
            case 11:
                return "Một";
            case 12:
                return "Chạp";
            default:
                return null;
        }
    }

    public static String getMonthSolarName(int month) {
        switch (month) {
            case 1:
                return "Tháng Một";
            case 2:
                return "Tháng Hai";
            case 3:
                return "Tháng Ba";
            case 4:
                return "Tháng Tư";
            case 5:
                return "Tháng Năm";
            case 6:
                return "Tháng Sáu";
            case 7:
                return "Tháng Bảy";
            case 8:
                return "Tháng Tám";
            case 9:
                return "Tháng Chín";
            case 10:
                return "Tháng Mười";
            case 11:
                return "Tháng Mười Một";
            case 12:
                return "Tháng Mười Hai";
            default:
                return null;
        }
    }

    public static String getMonthSolarShortName(int month) {
        switch (month) {
            case 1:
                return "Một";
            case 2:
                return "Hai";
            case 3:
                return "Ba";
            case 4:
                return "Tư";
            case 5:
                return "Năm";
            case 6:
                return "Sáu";
            case 7:
                return "Bảy";
            case 8:
                return "Tám";
            case 9:
                return "Chín";
            case 10:
                return "Mười";
            case 11:
                return "11";
            case 12:
                return "12";
            default:
                return null;
        }
    }

    public static String getStampCurrentDay(@NonNull String pattern) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        String date = dateFormat.format(calendar.getTime());
        return date;
    }

    public static String getStampByDate(@NonNull Date date, @NonNull String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static String getStampByCalendar(@NonNull Calendar calendar, @NonNull String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        String strDate = dateFormat.format(calendar.getTime());
        return strDate;
    }

    public static String getStampByCalendar(@NonNull Calendar calendar, @NonNull String pattern, Locale locale) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);
        String strDate = dateFormat.format(calendar.getTime());
        return strDate;
    }

    public static Date getDateByStamp(@NonNull String stamp, @NonNull String pattern) {
        try {
            return new SimpleDateFormat(pattern, Locale.getDefault()).parse(stamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static Calendar getCalendarByStamp(@NonNull String stamp, @NonNull String pattern) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(new SimpleDateFormat(pattern, Locale.getDefault()).parse(stamp));
            return c;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Calendar.getInstance();
    }

    public static Calendar getCalendarByStamp(@NonNull String stamp, @NonNull String pattern, Locale locale) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(new SimpleDateFormat(pattern, locale).parse(stamp));
            return c;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Calendar.getInstance();
    }

    /**
     * Compare two Calendar by Years, Months and Days without time in day
     *
     * @param c1
     * @param c2
     * @return -1 if c1 before c2, 0 if c1 and c2 are same day, 1 if c1 after c2
     */
    public static int compareTwoCalendarOnlyDay(@NonNull Calendar c1, @NonNull Calendar c2) {
        if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
            return Integer.compare(c1.get(Calendar.DAY_OF_YEAR), c2.get(Calendar.DAY_OF_YEAR));
        } else if (c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR)) {
            return 1;
        } else {
            return -1;
        }
    }

    public static String getTimeDisplay(Calendar cal) {
        // current time
        StringBuilder time = new StringBuilder("");
        if (cal.get(Calendar.DAY_OF_MONTH) < 10)
            time.append("0");
        time.append(cal.get(Calendar.DAY_OF_MONTH));
        time.append("/");
        if (cal.get(Calendar.MONTH) + 1 < 10)
            time.append("0");
        time.append(cal.get(Calendar.MONTH) + 1);
        time.append("/");
        time.append(cal.get(Calendar.YEAR));
        return time.toString();
    }

    public static String getTimeAgo(Calendar calendar) {
        if (calendar == null)
            return null;
        long now = Calendar.getInstance().getTimeInMillis();
        long time = calendar.getTimeInMillis();
        final long diff = now - time;
        if (diff < TimeUnit.MINUTES.toMillis(1)) {
            return "Vừa xong";
        } else if (diff < 60 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " phút trước";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " giờ trước";
        } else if (diff < 2 * 24 * HOUR_MILLIS) {
            return "Hôm qua";
        } else if (diff < 7 * 24 * HOUR_MILLIS) {
            return diff / DAY_MILLIS + " ngày trước";
        } else {
            return getTimeDisplay(calendar);
        }
    }

    public static String getTimeStill(Calendar dateTimeStart, String timeStart) {
        if (dateTimeStart == null)
            return null;
        if (TextUtils.isEmpty(timeStart)) {
            setTimeDefault(dateTimeStart, true);
        }
//        if (TextUtils.isEmpty(timeEnd)) {
//            setTimeDefault(dateTimeEnd, false);
//        }
        Calendar current = Calendar.getInstance();
        // on going
//        if (current.getTimeInMillis() >= dateTimeStart.getTimeInMillis() && current.getTimeInMillis() <= dateTimeEnd.getTimeInMillis()) {
//            return "Đang diễn ra";
//        } else
        if (current.getTimeInMillis() < dateTimeStart.getTimeInMillis()) {
            if (TextUtils.isEmpty(timeStart) && current.get(Calendar.YEAR) == dateTimeStart.get(Calendar.YEAR)) {
                return "Còn " + (dateTimeStart.get(Calendar.DAY_OF_YEAR) - current.get(Calendar.DAY_OF_YEAR)) + " ngày nữa";
            }
            long now = current.getTimeInMillis();
            long time = dateTimeStart.getTimeInMillis();
            final long diff = time - now;
            if (diff < 60 * MINUTE_MILLIS) {
                return "Còn " + diff / MINUTE_MILLIS + " phút nữa";
            } else if (diff < 24 * HOUR_MILLIS) {
                return "Còn " + diff / HOUR_MILLIS + " giờ nữa";
            } else if (diff < 365 * 24 * HOUR_MILLIS) {
                return "Còn " + (diff / DAY_MILLIS) + " ngày nữa";
            } else {
                return getTimeDisplay(dateTimeStart);
            }
        } else {
            return "Đã diễn ra";
        }
    }

    public static String getDayMonthDisplay(Calendar calendar) {
        if (calendar == null)
            return null;
        StringBuilder sb = new StringBuilder();
        sb.append(calendar.get(Calendar.DAY_OF_MONTH)).append(" ").append(getMonthSolarName(calendar.get(Calendar.MONTH) + 1));
        return sb.toString();
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static String getDateTimeDisplayByFormat(String format, Calendar calendar) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(calendar.getTimeInMillis());
        } catch (Exception e) {

        }
        return null;
    }

    public static long getDayDuring(Calendar cal1, Calendar cal2) {
        return ((cal1.getTimeInMillis() - cal2.getTimeInMillis()) / DAY_MILLIS) + 1;
    }

    public static boolean checkBetweenCalendar(String timeStart, String timeEnd, String timeCurrent) {
        try {

            Date time1 = new SimpleDateFormat(DATE_FORMAT_2).parse(timeStart);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);

            Date time2 = new SimpleDateFormat(DATE_FORMAT_2).parse(timeEnd);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            calendar2.add(Calendar.DATE, 1);

            Date d = new SimpleDateFormat(DATE_FORMAT_2).parse(timeCurrent);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(d);
            calendar3.add(Calendar.DATE, 1);

            Date x = calendar3.getTime();
            if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getAge(Calendar dob, Calendar now) {
        int year1 = now.get(Calendar.YEAR);
        int year2 = dob.get(Calendar.YEAR);
        int age = year1 - year2;
        int month1 = now.get(Calendar.MONTH);
        int month2 = dob.get(Calendar.MONTH);
        if (month2 > month1) {
            age--;
        } else if (month1 == month2) {
            int day1 = now.get(Calendar.DAY_OF_MONTH);
            int day2 = dob.get(Calendar.DAY_OF_MONTH);
            if (day2 > day1) {
                age--;
            }
        }
        return age;
    }

    public static boolean isOver30Day(String date) {
        long day30 = 30l * 24 * 60 * 60 * 1000;
        long giveTime = getDateByStamp(date, DATE_FORMAT_11).getTime();
        return new Date().getTime() - giveTime > day30;
    }

    public static Calendar setTimeDefault(Calendar calendar, boolean isStart) {
        if (isStart) {
            calendar.set(Calendar.HOUR_OF_DAY, TIME_START);
            calendar.set(Calendar.MINUTE, TIME_START);
            calendar.set(Calendar.SECOND, TIME_START);
        } else {
            calendar.set(Calendar.HOUR_OF_DAY, TIME_END_HOUR);
            calendar.set(Calendar.MINUTE, TIME_END_MINUTE);
        }
        return calendar;
    }
}
