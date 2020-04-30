package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class AboutMeActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.aboutme_activity);
        init();
    }

    private void init() {
        topTitle.setText("关于我们");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
