package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class MyJiaoYuActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.myjiaoyu_activity);
        init();
    }

    private void init() {
        topTitle.setText("教育经历");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
