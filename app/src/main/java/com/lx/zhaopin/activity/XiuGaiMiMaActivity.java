package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class XiuGaiMiMaActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.xiugaimima_activity);
        init();
    }

    private void init() {
        topTitle.setText("修改密码");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
