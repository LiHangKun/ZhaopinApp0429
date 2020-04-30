package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class ZhiWuLeiXingActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.zhiwuleixing_activity);
        init();
    }

    private void init() {
        topTitle.setText("选择职位类型");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
