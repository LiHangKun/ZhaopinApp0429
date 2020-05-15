package com.lx.zhaopin.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.lx.zhaopin.R;
import com.lxj.xpopup.core.DrawerPopupView;


/**
 * Description: 自定义抽屉弹窗
 * Create by dance, at 2018/12/20
 */
public class CustomDrawerPopupView extends DrawerPopupView {
    private static final String TAG = "CustomDrawerPopupView";



    public CustomDrawerPopupView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_drawer_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();


        Log.e("tag", "CustomDrawerPopupView onCreate");



    }

    @Override
    protected void onShow() {
        super.onShow();
        Log.e(TAG, "CustomDrawerPopupView onShow");
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        Log.e(TAG, "CustomDrawerPopupView onDismiss");
    }
}