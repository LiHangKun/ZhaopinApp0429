package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

public class JuBaoActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.jubao_activity);
        init();
    }

    private void init() {
        topTitle.setText("举报");
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
                startActivity(new Intent(mContext, JuBaoSuccessActivity.class));
                finish();
                break;
        }
    }
}
