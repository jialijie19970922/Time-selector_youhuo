package com.pickerview.jlj.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.pickerview.jlj.myapplication.bean.InitData;
import com.pickerview.jlj.myapplication.myinterfase.onSelectListener;
import com.pickerview.jlj.myapplication.mypickerview.PickerView;
import com.pickerview.jlj.myapplication.utils.BasisTimesUtils;

/**
 * 日期选择器
 * Created by jialijie on 2018/9/29/029.
 */
public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    String year = "";// 年
    String minute = "";// 月
    String day = "";// 日
    String date = "";// 拼接字符串用
    PickerView hour_pv;
    PickerView minute_pv;
    PickerView second_pv;
    private EditText my_edit_text;
    private List<String> h;// 年集合
    private List<String> data;// 月集合
    private List<String> seconds;// 日集合
    private BasisTimesUtils timesutils;
    private InitData instance;
    private EditText my_edit_keyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        // 获取时间值
        instance = InitData.getInstance();
        h = instance.getH();
        data = instance.getData();
        seconds = instance.getSeconds(32);// 默认最大值为32天

        // 设置时间
        setDate(h, data, seconds);
        year = hour_pv.getMiddleText();
        minute = minute_pv.getMiddleText();
        day = second_pv.getMiddleText();

        // 设置当前时间
        my_edit_text.setText(year+minute+day);

        timesutils = BasisTimesUtils.getInstance();

        // 监听用户滑动
        myOnClick();
    }

    private void myOnClick() {
        hour_pv.setOnSelectListener(new onSelectListener() {

            @Override
            public void onSelect(String text) {
                year = text;
                int lastDayOfMonth = timesutils.getDay(Integer.parseInt(year), Integer.parseInt(minute));
                setSecond(seconds = instance.getSeconds(lastDayOfMonth+1));
                setEditText();
            }
        });

        minute_pv.setOnSelectListener(new onSelectListener() {

            @Override
            public void onSelect(String text) {
                minute = text;
                int lastDayOfMonth = timesutils.getDay(Integer.parseInt(year), Integer.parseInt(minute));
                setSecond(seconds = instance.getSeconds(lastDayOfMonth+1));
                setEditText();
            }
        });
        second_pv.setOnSelectListener(new onSelectListener() {

            @Override
            public void onSelect(String text) {
                day = text;
                setEditText();
            }
        });
    }

    private void initView() {
        hour_pv = findViewById(R.id.hour_pv);
        minute_pv = findViewById(R.id.minute_pv);
        second_pv = findViewById(R.id.second_pv);
        my_edit_text = findViewById(R.id.my_edit_text);
        my_edit_keyboard = findViewById(R.id.my_edit_keyboard);
        my_edit_text.setOnFocusChangeListener(this);
    }

    // 第一次显示、设置年月日
    private void setDate(List<String> h, List<String> data, List<String> seconds) {
        hour_pv.setData(h);
        minute_pv.setData(data);
        second_pv.setData(seconds);
    }

    // 设置天总数
    public void setSecond(List<String> seconds){
        second_pv.setData(seconds);
    }

    // 设置edittext显示的值
    private void setEditText() {
        date = year + minute + day;
        my_edit_text.setText(date);
    }


    /**
     * 控制软键盘
     *
     * @param isShow
     */
    protected void showKeyboard(boolean isShow) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (null == imm)
            return;

        if (isShow) {
            if (getCurrentFocus() != null) {
                //有焦点打开
                imm.showSoftInput(getCurrentFocus(), 0);
            } else {
                //无焦点打开
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
        } else {
            if (getCurrentFocus() != null) {
                //有焦点关闭
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } else {
                //无焦点关闭
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        }
    }

    /**
     * 获得焦点监听
     * @param v
     * @param hasFocus
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.my_edit_text:// 出生日期
                InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(my_edit_text.getWindowToken(), 0);
                break;
            case R.id.my_edit_keyboard:// 尿酸值
                showKeyboard(hasFocus);
                break;
        }
    }
}
