package com.pickerview.jlj.myapplication.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * time 工具类
 * Created by jialijie on 2018/9/29/029.
 */

public class BasisTimesUtils {
    /**
     * 单例模式
     */
    private BasisTimesUtils() {
    }

    private static BasisTimesUtils single = null;    //静态工厂方法

    public static BasisTimesUtils getInstance() {
        if (single == null) {
            single = new BasisTimesUtils();
        }
        return single;
    }

    /**
     * 当前的时间(年)
     */
    public String year() {
        String date = "";
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            date = df.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     *根据年份和月份判断某月有多少天
     * @param year
     * @param month
     * @return
     */
    public int getDay(int year, int month) {
        int day = 30;
        boolean flag = false;
        switch (year % 4) { //余0等于闰年
            case 0:
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 2:
                day = flag ? 29 : 28; //闰年2月是29天
                break;
            default:
                day = 30;
                break;
        }
        return day;
    }
}