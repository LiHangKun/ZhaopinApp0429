package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class PingBiGangActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.pingbigang_activity);
        init();
    }

    private void init() {
        topTitle.setText("屏蔽岗位");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
