package com.lx.zhaopin.utils;

import android.util.Log;

import com.lx.zhaopin.common.MyApplication;

;


/**
 * Created by Administrator on 2018/5/18.
 */

public class LoggerUtil {

    public static void e(String str){
        if (MyApplication.isDebug){
            Log.e("DEBUG",str);
        }
    }

    public static void e(String str1, String str2){
        if (MyApplication.isDebug){
            Log.e(str1+"",str2+"");
        }
    }

}
