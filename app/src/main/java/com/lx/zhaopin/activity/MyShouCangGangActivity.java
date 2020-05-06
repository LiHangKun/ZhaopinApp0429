package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class MyShouCangGangActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.myshoucanggang_activity);
        init();
    }

    private void init() {
        topTitle.setText("我的收藏岗位");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
