package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class MyJianLiActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.myjianli_activity);
        init();
    }

    private void init() {
        topTitle.setText("我的简历");
        TextView okID = findViewById(R.id.okID);
        okID.setOnClickListener(this);
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
            case R.id.okID:
                //预览简历
                startActivity(new Intent(mContext, YuLanJianLiActivity.class));
                break;
        }
    }
}
