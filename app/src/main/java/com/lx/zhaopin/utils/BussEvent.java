package com.lx.zhaopin.utils;

/**
 * Created by cxh on 2016/7/25.
 * 异步通知事件
 */
public class BussEvent {

    private int state;
    private Object message;

    public Object getMessage() {
        return message;
    }
    public void setMessage(Object message) {
        this.message = message;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public BussEvent(int state) {
        this.state = state;
    }

    public static int REFRESH_MIANSHIDETAIL=1;//刷新面试详情页面

}
