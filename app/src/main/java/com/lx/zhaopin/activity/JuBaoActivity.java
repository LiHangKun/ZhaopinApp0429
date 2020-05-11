package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.utils.ToastFactory;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JuBaoActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.roundedImageView)
    RoundedImageView roundedImageView;
    @BindView(R.id.edit1)
    EditText edit1;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.jubao_activity);
        ButterKnife.bind(this);
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

                if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "举报原因不能为空").show();
                    return;
                }
                //TODO   Success
                startActivity(new Intent(mContext, JuBaoSuccessActivity.class));
                finish();
                break;
        }
    }


}
