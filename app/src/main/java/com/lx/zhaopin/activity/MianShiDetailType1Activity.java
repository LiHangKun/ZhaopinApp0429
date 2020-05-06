package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.view.MyDialog;

public class MianShiDetailType1Activity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.miaoshidetailtype1_activity);
        init();
    }

    private void init() {
        topTitle.setText("招聘者面试详情");

        TextView quxiaoTV = findViewById(R.id.quxiaoTV);
        quxiaoTV.setOnClickListener(this);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quxiaoTV:
                //取消面试的弹框
                View view = getLayoutInflater().inflate(R.layout.dialog_quxiao_mianshi, null);
                final MyDialog mMyDialog = new MyDialog(mContext, 0, 0, view, R.style.DialogTheme2);
                final EditText edit1 = view.findViewById(R.id.edit1);
                TextView okID = view.findViewById(R.id.okID);
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


                break;
        }
    }
}
