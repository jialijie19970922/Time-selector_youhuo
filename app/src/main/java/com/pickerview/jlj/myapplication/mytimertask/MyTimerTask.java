package com.pickerview.jlj.myapplication.mytimertask;

import android.os.Handler;

import java.util.TimerTask;

/**
 * handler 回传值，不多说了
 * Created by jialijie on 2018/9/29/029.
 */

public class MyTimerTask extends TimerTask {
    Handler handler;
    public MyTimerTask(Handler handler){
        this.handler = handler;
    }
    @Override
    public void run() {
        handler.sendMessage(handler.obtainMessage());
    }
}
