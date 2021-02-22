package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class JinDuDetailActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.jindudetail_activity);
        init();
    }

    private void init() {
        topTitle.setText("求职反馈的进度详情");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
