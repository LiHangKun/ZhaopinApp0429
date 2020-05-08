package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.utils.ToastFactory;

import org.feezu.liuli.timeselector.Utils.TextUtil;

public class FeedActivity extends BaseActivity implements View.OnClickListener {

    private TextView okID;
    private EditText edit2;
    private EditText edit1;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.feed_activity);
        init();
    }

    private void init() {
        topTitle.setText("意见反馈");

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        okID = findViewById(R.id.okID);


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
                //反馈
                if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "多少写点内容吧...").show();
                    return;
                } else if (TextUtils.isEmpty(edit2.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "联系方式不能为空").show();
                    return;
                } else {
                    feedMethod(edit1.getText().toString().trim(), edit2.getText().toString().trim());
                }
                break;
        }
    }

    //反馈
    private void feedMethod(String trim, String trim1) {

    }
}
