package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class MianShiListActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.mianshilist_activity);
        init();
    }

    private void init() {
        topTitle.setText("面试记录");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
