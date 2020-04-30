package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class HangYeLeiXingActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.hangyeleixing_activity);
        init();
    }

    private void init() {
        topTitle.setText("选择行业");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
