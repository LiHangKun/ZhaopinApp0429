package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class YanSFActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.yansf_activity);
        init();
    }

    private void init() {
        topTitle.setText("验证身份");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
