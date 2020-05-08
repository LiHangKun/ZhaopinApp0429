package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.utils.ToastFactory;

public class Login1PhoneCodeActivity extends BaseActivity implements View.OnClickListener {

    private EditText edit1;
    private EditText edit2;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.login1phonecode_activity);
        init();
    }

    private void init() {
        topTitle.setText("短信验证码登录");

        topTitle.setVisibility(View.INVISIBLE);
        view.setVisibility(View.INVISIBLE);

        baseback.setImageResource(R.drawable.guanbi_hei);

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);

        TextView faCode = findViewById(R.id.faCode);
        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        TextView tv4 = findViewById(R.id.tv4);

        faCode.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);


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
            case R.id.faCode:
                //发送验证码
                ToastFactory.getToast(mContext, "发送验证码").show();
                break;
            case R.id.tv1:
                //登录
                ToastFactory.getToast(mContext, "登录").show();
                break;
            case R.id.tv2:
                //密码登录
                startActivity(new Intent(mContext, Login2PassWordActivity.class));
                break;
            case R.id.tv3:
                //用户协议
                ToastFactory.getToast(mContext, "用户协议").show();
                break;
            case R.id.tv4:
                //隐私政策
                ToastFactory.getToast(mContext, "隐私政策").show();
                break;
        }
    }
}
