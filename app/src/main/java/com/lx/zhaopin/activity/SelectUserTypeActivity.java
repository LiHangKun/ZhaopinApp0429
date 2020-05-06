package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class SelectUserTypeActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.selectusertype_activity);
        init();
    }

    private void init() {
        topTitle.setText("切换身份");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
