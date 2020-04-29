package com.lx.zhaopin.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.lx.zhaopin.R;


/**
 * 网络连接工具类
 */
public class NetUtil {

    /**
     * 是否连接网络
     * @param context
     * @return
     */
    public static boolean isNetWorking(Context context){

        //return true;

        ConnectivityManager cManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            return true;
        }else {
            MUIToast.toast(context, R.string.net_error);
            return false;
        }
    }



    /**
     * 检查网络是否连接
     */
    public static void checkNetState(final Context context) {
        if (!isNetWork(context)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("当前网络不可用，是否打开网络设置?");
            builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (android.os.Build.VERSION.SDK_INT > 10) {
                        context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
                    } else {
                        context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                    }
                }
            });
            builder.create().show();
        }
    }

    //判断是否有网络
    public static boolean isNetWork(Context context){
        //得到网络的管理者
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(null != info){
            return true;
        }else{
            return false;
        }
    }


}
