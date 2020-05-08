package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class RegActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.reg_activity);
        init();
    }

    private void init() {
        topTitle.setText("注册账号");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
