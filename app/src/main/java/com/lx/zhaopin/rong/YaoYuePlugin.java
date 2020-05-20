package com.lx.zhaopin.rong;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

import com.lx.zhaopin.R;
import com.lx.zhaopin.utils.ToastFactory;

import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;

/**
 * Created by kxn on 2019/12/26 0026.
 */
public class YaoYuePlugin implements IPluginModule {
    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.drawable.xiaoxi_yaoyue);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getString(R.string.liao6);
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        //GlobalBeans.getSelf().getEventCenter().sendType(EventCenter.EventType.EVT_SENDGIFT);
        ToastFactory.getToast(fragment.getContext(),"邀约").show();
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}

