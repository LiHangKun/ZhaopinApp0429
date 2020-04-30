package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.view.View;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.utils.ToastFactory;

public class YuLanJianLiActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.yulanjianli_activity);
        init();
    }

    private void init() {
        topTitle.setText("预览简历");
        rightIcon.setVisibility(View.VISIBLE);
        rightIcon.setOnClickListener(this);
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
            case R.id.rightIcon:
                //分享
                ToastFactory.getToast(mContext,"分享").show();
                break;
        }
    }
}
