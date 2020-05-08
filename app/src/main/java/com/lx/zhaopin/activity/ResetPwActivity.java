package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class ResetPwActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.resetpw_activity);
        init();
    }

    private void init() {
        topTitle.setText("找回密码");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
