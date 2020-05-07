package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class MyShouCangRenActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.myshoucangren_activity);
        init();
    }

    private void init() {
        topTitle.setText("我的收藏人才");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
