package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.view.FlowLiner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyGongZuoActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.edit1)
    EditText edit1;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.llView1OnClick)
    LinearLayout llView1OnClick;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.llView2OnClick)
    LinearLayout llView2OnClick;
    @BindView(R.id.llView3OnClick)
    LinearLayout llView3OnClick;
    @BindView(R.id.flowLiner)
    FlowLiner flowLiner;
    @BindView(R.id.edit2)
    EditText edit2;
    @BindView(R.id.okID)
    TextView okID;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.mygongzuo_activity);
        ButterKnife.bind(this);
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


    @OnClick({R.id.llView1OnClick, R.id.llView2OnClick, R.id.llView3OnClick, R.id.okID, R.id.rightText})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rightText:
                ToastFactory.getToast(mContext, "删除").show();
                break;
            case R.id.llView1OnClick:
                ToastFactory.getToast(mContext, "职位名称").show();
                break;
            case R.id.llView2OnClick:
                ToastFactory.getToast(mContext, "在职时长").show();
                break;
            case R.id.llView3OnClick:
                ToastFactory.getToast(mContext, "专业技能").show();
                break;
            case R.id.okID:
                ToastFactory.getToast(mContext, "保存").show();
                break;
        }
    }
}
