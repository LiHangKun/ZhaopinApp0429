package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class PingBiRenActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.pingbiren_activity);
        init();
    }

    private void init() {
        topTitle.setText("屏蔽人才");
    }

    @Override
    protected void initEvent() {
    }

    @Override
    protected void initData() {

    }
}
