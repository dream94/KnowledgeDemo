package com.example.cc.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cc on 2018/10/18.
 */

public class TimeUtils {

    public static void testDemo() {
        String curDate = getCurDate();
        Log.e("dream", "getCurDate:" + curDate);
        Log.e("dream", "date:" + getStringToDate(curDate, "yyyy-MM-dd"));
        Log.e("dream", "date:" + getStringToDate("2018-10-11", "yyyy-MM-dd"));
    }

    // 获取当前日期
    public static String getCurDate() {
        String format = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(new Date());
    }

    // 日期str转时间戳
    public static long getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }
}
