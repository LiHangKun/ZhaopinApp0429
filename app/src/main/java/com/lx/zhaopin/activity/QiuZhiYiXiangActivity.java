package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class QiuZhiYiXiangActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.qiuzhiyixiang_activity);
        init();
    }

    private void init() {
        topTitle.setText("求职意向");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
