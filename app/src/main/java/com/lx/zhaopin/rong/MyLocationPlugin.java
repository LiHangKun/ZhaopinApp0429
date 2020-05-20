package com.lx.zhaopin.rong;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

import com.lx.zhaopin.R;

import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.DefaultLocationPlugin;

//发送位置 废弃
public class MyLocationPlugin extends DefaultLocationPlugin {
    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.drawable.gangwei_dingwei);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getString(R.string.liao6);
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        super.onClick(fragment, rongExtension);
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}
