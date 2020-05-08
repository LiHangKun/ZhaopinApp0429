package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class SelectCityCity2ListActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.selectcitylist_activity);
        init();
    }

    private void init() {
        topTitle.setText("选择地址");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
