package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class YiLuQuActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.yiluqu_activity);
        init();
    }

    private void init() {
        topTitle.setText("已录取");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}