package com.lx.zhaopin.activity;

import android.os.Bundle;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class ChangJianWenTiActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.changjianwenti_activity);
        init();
    }

    private void init() {
        topTitle.setText("常见问题");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
