package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class MyJianLiActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.myjianli_activity);
        init();
    }

    private void init() {
        topTitle.setText("我的简历");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
