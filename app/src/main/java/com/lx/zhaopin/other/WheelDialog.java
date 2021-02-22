package com.lx.zhaopin.other;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.utils.DisplayUtil;

import cn.addapp.pickers.common.LineConfig;

public class WheelDialog extends BaseDialog {

    private TextView cancelTv;
    private TextView titleTv;
    private TextView confirmTv;
    private WheelListView wheelListView;
    private Context mContext;
    private String selAnswer;
    private int selNum = 0;
    private DialogListener listener;

    public WheelDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_wheel);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        cancelTv = getViewById(R.id.dialog_cancel_tv);
        titleTv = getViewById(R.id.dialog_title_tv);
        confirmTv = getViewById(R.id.dialog_confirm_tv);
        wheelListView = getViewById(R.id.dialog_wheel);
        mContext = context;
        init();
    }

    public void setDialogListener(DialogListener dialogListener) {
        listener = dialogListener;
    }

    public void setTitle(String str) {
        titleTv.setText(str);
    }

    public void setItems(String[] strs) {
        wheelListView.setItems(strs);
    }

    private void init() {
        cancelTv.setOnClickListener(this);
        confirmTv.setOnClickListener(this);
        wheelListView.setSelectedTextColor(Color.rgb(21, 20, 19));
        LineConfig config = new LineConfig();
        config.setColor(Color.rgb(241, 241, 241));//线颜色
        config.setAlpha(100);//线透明度
        config.setThick(DisplayUtil.dip2px(mContext, 1));//线粗
        config.setShadowVisible(false);
        wheelListView.setTextSize(16);
        wheelListView.setLineConfig(config);
        wheelListView.setOffset(2);
        wheelListView.setUnSelectedTextColor(Color.parseColor("#939393"));
        wheelListView.setOnWheelChangeListener(new WheelListView.OnWheelChangeListener() {
            @Override
            public void onItemSelected(int i, String s) {
                selAnswer = s;
                selNum = i;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_cancel_tv:
                dismiss();
                break;
            case R.id.dialog_confirm_tv:
                dismiss();
                if (listener != null) {
                    listener.confirm(selAnswer, selNum);
                }
                break;
        }
    }

    public interface DialogListener {
        void confirm(String s, int index);
    }

}
