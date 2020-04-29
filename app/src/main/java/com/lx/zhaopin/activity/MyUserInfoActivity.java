package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.view.View;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.utils.RxToast;

public class MyUserInfoActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.myuserinfo_activity);
        init();
    }

    private void init() {
        topTitle.setText("个人信息");
        rightText.setText("保存");
        rightText.setVisibility(View.VISIBLE);
        rightText.setOnClickListener(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rightText:
                RxToast.normal("保存");
                break;
        }
    }
}
