package com.lx.zhaopin.rong;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

import com.lx.zhaopin.R;

import io.rong.imkit.RongExtension;
import io.rong.sight.SightPlugin;


//语音聊天
public class MyYinPinPlugin extends SightPlugin {
    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.drawable.xiaoxi_yuyintonghua);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getString(R.string.liao4);
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        super.onClick(fragment, rongExtension);
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}
