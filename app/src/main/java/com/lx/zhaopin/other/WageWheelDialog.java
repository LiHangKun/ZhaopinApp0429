package com.lx.zhaopin.other;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.utils.DisplayUtil;
import com.lx.zhaopin.utils.ToastFactory;

import cn.addapp.pickers.common.LineConfig;

/**
 * 工资选择器
 */
public class WageWheelDialog extends BaseDialog {

    private TextView cancelTv;
    private TextView titleTv;
    private TextView confirmTv;
    private WheelListView wageWheelLeft;
    private WheelListView wageWheelRight;
    private Context mContext;
    private String selAnswerLeft, selAnswerRight;
    private int selAnswerLeftIndex, selAnswerRightIndex;
    private int selNum = 0;
    private DialogListener listener;

    public WageWheelDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_wage_wheel);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        cancelTv = getViewById(R.id.dialog_cancel_tv);
        titleTv = getViewById(R.id.dialog_title_tv);
        confirmTv = getViewById(R.id.dialog_confirm_tv);
        wageWheelLeft = getViewById(R.id.wage_wheel_left);
        wageWheelRight = getViewById(R.id.wage_wheel_right);
        mContext = context;
        init();
    }

    public void setDialogListener(DialogListener dialogListener) {
        listener = dialogListener;
    }

    public void setTitle(String str) {
        titleTv.setText(str);
    }

    public void setItemsLeft(String[] strs) {
        wageWheelLeft.setItems(strs);
    }

    public void setItemsRight(String[] strs) {
        wageWheelRight.setItems(strs);
    }

    private void init() {
        cancelTv.setOnClickListener(this);
        confirmTv.setOnClickListener(this);
        wageWheelLeft.setSelectedTextColor(Color.rgb(21, 20, 19));
        LineConfig config = new LineConfig();
        config.setColor(Color.rgb(241, 241, 241));//线颜色
        config.setAlpha(100);//线透明度
        config.setThick(DisplayUtil.dip2px(mContext, 1));//线粗
        config.setShadowVisible(false);
        wageWheelLeft.setTextSize(16);
        wageWheelLeft.setLineConfig(config);
        wageWheelLeft.setOffset(2);
        wageWheelLeft.setUnSelectedTextColor(Color.parseColor("#939393"));
        wageWheelLeft.setOnWheelChangeListener(new WheelListView.OnWheelChangeListener() {
            @Override
            public void onItemSelected(int i, String s) {
                selAnswerLeftIndex = i;
                selAnswerLeft = s;
            }
        });
        wageWheelLeft.setSelectedIndex(3);
        wageWheelRight.setSelectedTextColor(Color.rgb(21, 20, 19));
        wageWheelRight.setTextSize(16);
        wageWheelRight.setLineConfig(config);
        wageWheelRight.setOffset(2);
        wageWheelRight.setUnSelectedTextColor(Color.parseColor("#939393"));
        wageWheelRight.setOnWheelChangeListener(new WheelListView.OnWheelChangeListener() {
            @Override
            public void onItemSelected(int i, String s) {
                selAnswerRightIndex = i;
                selAnswerRight = s;
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
                if (selAnswerRightIndex - selAnswerLeftIndex < -1) {
                    ToastFactory.getToast(mContext, "请重新选择薪资范围").show();
                } else {
                    dismiss();
                    if (listener != null) {
                        if (selAnswerLeft.equals("面议")) {
                            listener.confirm("面议");
                        } else {
                            listener.confirm(selAnswerLeft + "-" + selAnswerRight);
                        }
                    }
                }

                break;
        }
    }

    public interface DialogListener {
        void confirm(String result);
    }

}
