package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class FeedActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.feed_activity);
        init();
    }

    private void init() {
        topTitle.setText("意见反馈");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
