package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class XinPhoneActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.xinphone_activity);
        init();
    }

    private void init() {
        topTitle.setText("绑定新手机号");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
