package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class DaiMianShiListActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.daimianshilist_activity);
        init();
    }

    private void init() {
        topTitle.setText("待面试");
        rightText.setText("面试记录");
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
                //面试记录
                startActivity(new Intent(mContext, MianShiListActivity.class));
                break;
        }
    }
}
