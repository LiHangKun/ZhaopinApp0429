package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.view.View;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.utils.ToastFactory;

public class MyGongZuoActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.mygongzuo_activity);
        init();
    }

    private void init() {
        topTitle.setText("工作经验");
        rightText.setText("删除");
        rightText.setOnClickListener(this);
        rightText.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rightText:
                ToastFactory.getToast(mContext,"删除").show();
                break;
        }
    }
}
