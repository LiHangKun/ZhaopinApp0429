package com.lx.zhaopin.other;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lx.zhaopin.R;

public class BaseDialog extends Dialog implements View.OnClickListener {

    public BaseDialog(Context context) {
        super(context, R.style.DialogStyle);
    }

    @Override
    public void onClick(View v) {

    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
    }

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }

}
