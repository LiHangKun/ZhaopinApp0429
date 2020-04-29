package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class SettingActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.setting_activity);
        init();
    }

    private void init() {
        topTitle.setText("设置");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
