package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.view.View;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.utils.ToastFactory;

//此页面是 添加和删除专业技能,当传递过来的ID 不等于空的时候 显示删除按钮
public class AddZhuanYeJiNengActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.addzhuanyejineng_activity);
        init();
    }

    private void init() {
        topTitle.setText("专业技能");

        rightText.setVisibility(View.VISIBLE);
        rightText.setText("删除");
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
                ToastFactory.getToast(mContext, "此页面是 添加和删除专业技能,当传递过来的ID 不等于空的时候 显示删除按钮").show();
                break;
        }
    }
}
