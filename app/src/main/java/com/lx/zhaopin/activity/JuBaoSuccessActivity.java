package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MainActivity;

public class JuBaoSuccessActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.jubaosuccess_activity);
        init();
    }

    private void init() {
        topTitle.setText("结果");

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
                //再去逛逛
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("pagerName", "0");
                AppSP.isToShopCar = true;
                startActivity(intent);
                finish();
                break;
        }
    }
}
