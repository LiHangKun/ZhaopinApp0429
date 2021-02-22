package com.lx.zhaopin.rong;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

import com.lx.zhaopin.R;

import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.ImagePlugin;

/**
 * Created by kxn on 2019/12/26 0026.
 */
//图片
public class MyImagePlugin extends ImagePlugin {
    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.drawable.xiaoxi_tupian);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getString(R.string.liao2);
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        super.onClick(fragment, rongExtension);
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}
