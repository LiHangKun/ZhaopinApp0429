package com.lx.zhaopin.other;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.lx.zhaopin.R;

public class WheelDialog extends Dialog {
    public WheelDialog(Context context) {
        super(context, R.style.MyDatePickerDialogTheme);
    }
    public WheelDialog(Context context, int theme) {
        super(context, theme);
    }



}
