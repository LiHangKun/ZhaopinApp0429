package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class MyGuanZhuGangActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.myguanzhu_activity);
        init();
    }

    private void init() {
        topTitle.setText("我的关注--公司");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
