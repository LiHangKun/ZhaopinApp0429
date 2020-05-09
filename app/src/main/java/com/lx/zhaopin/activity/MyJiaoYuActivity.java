package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyJiaoYuActivity extends BaseActivity {
    @BindView(R.id.edit1)
    EditText edit1;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.llView1OnClick)
    LinearLayout llView1OnClick;
    @BindView(R.id.edit2)
    EditText edit2;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.llView2OnClick)
    LinearLayout llView2OnClick;
    @BindView(R.id.edit3)
    EditText edit3;
    @BindView(R.id.okID)
    TextView okID;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.myjiaoyu_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        topTitle.setText("教育经历");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.llView1OnClick, R.id.llView2OnClick, R.id.okID})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llView1OnClick:
                //获取学历
                break;
            case R.id.llView2OnClick:
                //获取教育时间段
                break;
            case R.id.okID:
                //保存

                break;
        }
    }
}
