package com.lx.zhaopin.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends BaseActivity implements View.OnClickListener {


    String Cui = "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111113333333333333333333333333333331111111111111111111111111111111111111111111111111111111111111111111111111111111111111111133333333333333333333333333333311111111111111111111111111111111111111111111111111111111111111111111111111111111111111111333333333333333333333333333333111111111111111111111111111111111111111111111111111111111111111111111111111111111111111113333333333333333333333333333331111111111111111111111111111111111111111111111111111111111111111111111111111111111111111133333333333333333333333333333311111111111111111111111111111111111111111111111111111111111111111111111111111111111111111333333333333333333333333333333111111111111111111111111111111111111111111111111111111111111111111111111111111111111111113333333333333333333333333333331111111111111111111111111111111111111111111111111111111111333333333333333333333333333333";
    @BindView(R.id.tv1)
    TextView tv1;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.test_activity);
        ButterKnife.bind(this);
        getLastIndexForLimit(tv1, maxLine, Cui);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    private int maxLine = 5;
    private SpannableString elipseString;//收起的文字
    private SpannableString notElipseString;//展开的文字

    private void getLastIndexForLimit(TextView tv, int maxLine, String content) {
        //获取TextView的画笔对象
        TextPaint paint = tv.getPaint();
        //每行文本的布局宽度
        int width = getResources().getDisplayMetrics().widthPixels - dip2px(this, 40);
        //实例化StaticLayout 传入相应参数
        StaticLayout staticLayout = new StaticLayout(content, paint, width, Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
        //判断content是行数是否超过最大限制行数3行
        if (staticLayout.getLineCount() > maxLine) {
            //定义展开后的文本内容
            String string1 = content + "    收起";
            notElipseString = new SpannableString(string1);
            //给收起两个字设成蓝色
            notElipseString.setSpan(new ForegroundColorSpan(Color.parseColor("#0079e2")), string1.length() - 2, string1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            //获取到第三行最后一个文字的下标
            int index = staticLayout.getLineStart(maxLine) - 1;
            //定义收起后的文本内容
            String substring = content.substring(0, index - 4) + "..." + "查看全部";
            elipseString = new SpannableString(substring);
            //给查看全部设成蓝色
            elipseString.setSpan(new ForegroundColorSpan(Color.parseColor("#0079e2")), substring.length() - 4, substring.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //设置收起后的文本内容
            tv.setText(elipseString);
            tv.setOnClickListener(this);
            //将textview设成选中状态 true用来表示文本未展示完全的状态,false表示完全展示状态，用于点击时的判断
            tv.setSelected(true);
        } else {
            //没有超过 直接设置文本
            tv.setText(content);
            tv.setOnClickListener(null);
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context mContext, float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    @OnClick({R.id.tv1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                if (view.isSelected()) {
                    //如果是收起的状态
                    tv1.setText(Cui);
                    tv1.setSelected(false);
                } else {
                    //如果是展开的状态
                    tv1.setText(Cui);
                    tv1.setSelected(true);
                }
                break;

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
