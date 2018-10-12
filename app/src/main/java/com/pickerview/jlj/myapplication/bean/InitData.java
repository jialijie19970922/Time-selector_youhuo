package com.pickerview.jlj.myapplication.bean;

import com.pickerview.jlj.myapplication.utils.BasisTimesUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jialijie on 2018/9/29/029.
 */

public class InitData {

    private BasisTimesUtils instance;

    /**
     * 单例模式
     */
    private InitData() {
        instance = BasisTimesUtils.getInstance();

    }

    private static InitData single = null;// 静态工厂方法

    public static InitData getInstance() {
        if (single == null) {
            single = new InitData();
        }
        return single;
    }

    private List<String> h;
    private List<String> data;
    private List<String> seconds;
    /**
     * 得到年，返回年
     *
     * @return
     */
    public List<String> getH() {
        h = new ArrayList<>();
        String year = instance.year();
        int years = Integer.parseInt(year);
        // 这里是一个算法 当前年限-500后*2后/2 等于当前年限 不要问我为什么，因为还有一个条件没加，如有疑问请联系qq：1347291890
        for (int i = 1000; (years-500)*2 > i; i++) {
            h.add(i + "");
        }
        return h;
    }

    /**
     * 没的说，哪年不是12个月？
     * 得到月，返回月，首先第一次得返回当前月份
     *
     * @return
     */
    public List<String> getData() {
        data = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            if (i < 10) {
                data.add("0" + i);
            } else {
                data.add("" + i);
            }
        }
        return data;
    }

    /**
     * 得到日，返回日子
     *
     * @return
     */
    public List<String> getSeconds(int maxday) {// 需要一个最大值
        seconds = new ArrayList<>();
        for (int i = 1; i < maxday; i++) {
            if (i < 10) {
                seconds.add("0" + i);
            } else {
                seconds.add("" + i);
            }
        }
        return seconds;
    }
}
