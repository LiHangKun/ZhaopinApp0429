package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class QiuZhiFeedActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.qiuzhifeed_activity);
        init();
    }

    private void init() {
        topTitle.setText("求职反馈");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
