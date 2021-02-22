package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

//我的关注  收藏的人才  已经写过了  MyShouCangRenActivity

public class MyGuanZhuRenActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.myguanzhuren_activity);
        init();
    }

    private void init() {
        topTitle.setText("我的关注");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
