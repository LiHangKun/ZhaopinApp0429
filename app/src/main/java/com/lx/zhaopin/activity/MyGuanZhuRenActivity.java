package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class MyGuanZhuRenActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.myguanzhuren_activity);
        init();
    }

    private void init() {
        topTitle.setText("我的关注--人才");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
