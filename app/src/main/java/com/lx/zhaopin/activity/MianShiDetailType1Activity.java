package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class MianShiDetailType1Activity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.miaoshidetailtype1_activity);
        init();
    }

    private void init() {
        topTitle.setText("招聘者面试详情");

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
