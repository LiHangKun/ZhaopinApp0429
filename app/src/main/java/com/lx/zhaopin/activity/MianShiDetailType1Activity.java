package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.view.MyDialog;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MianShiDetailType1Activity extends BaseActivity  {
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.imageState)
    ImageView imageState;
    @BindView(R.id.roundedImageView)
    RoundedImageView roundedImageView;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.llView1OnClick)
    LinearLayout llView1OnClick;
    @BindView(R.id.llView2OnClick)
    LinearLayout llView2OnClick;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6Click)
    TextView tv6Click;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.quxiaoTV)
    TextView quxiaoTV;
    @BindView(R.id.okID)
    TextView okID;
    @BindView(R.id.qiuZhiView)
    LinearLayout qiuZhiView;
    @BindView(R.id.quxiaoTv)
    TextView quxiaoTv;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.miaoshidetailtype1_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        topTitle.setText("求职者面试详情");


    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.llView1OnClick, R.id.llView2OnClick, R.id.tv6Click, R.id.quxiaoTV, R.id.okID, R.id.qiuZhiView, R.id.quxiaoTv})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llView1OnClick:
                ToastFactory.getToast(mContext, "聊天").show();
                break;
            case R.id.llView2OnClick:
                ToastFactory.getToast(mContext, "打电话").show();
                break;
            case R.id.tv6Click:
                ToastFactory.getToast(mContext, "导航").show();
                break;
            case R.id.okID:
                ToastFactory.getToast(mContext, "我已到达").show();
                break;
            case R.id.qiuZhiView:
                //这个不要了
                break;
            case R.id.quxiaoTV:
                //取消面试的弹框
                View view = getLayoutInflater().inflate(R.layout.dialog_quxiao_mianshi, null);
                final MyDialog mMyDialog = new MyDialog(mContext, 0, 0, view, R.style.DialogTheme2);
                final EditText edit1 = view.findViewById(R.id.edit1);
                mMyDialog.setCancelable(true);
                mMyDialog.show();

                view.findViewById(R.id.quxiaoTV).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMyDialog.dismiss();
                    }
                });

                view.findViewById(R.id.okID).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                            ToastFactory.getToast(mContext, "取消原因不能为空").show();
                            return;
                        } else {
                            ToastFactory.getToast(mContext, edit1.getText().toString().trim()).show();
                            mMyDialog.dismiss();
                        }
                    }
                });
        }
    }
}
