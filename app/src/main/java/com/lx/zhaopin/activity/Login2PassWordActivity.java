package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.LoginBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.MD5Util;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.StringUtil;
import com.lx.zhaopin.utils.ToastFactory;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Response;

public class Login2PassWordActivity extends BaseActivity implements View.OnClickListener {

    private EditText edit1;
    private EditText edit2;
    private ImageView imageDui;
    private boolean duiHaoBoolean = true;
    private static final String TAG = "Login2PassWordActivity";
    private String registrationID;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.login2password_activity);
        init();
    }

    private void init() {
        topTitle.setText("密码登录");

        topTitle.setVisibility(View.INVISIBLE);
        view.setVisibility(View.INVISIBLE);

        baseback.setImageResource(R.drawable.guanbi_hei);

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        registrationID = JPushInterface.getRegistrationID(this);
        SPTool.addSessionMap(AppSP.JupshID, registrationID);
        Log.i(TAG, "onCreate:极光信息 " + registrationID);

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        imageDui = findViewById(R.id.imageDui);
        imageDui.setOnClickListener(this);
        TextView faCode = findViewById(R.id.faCode);
        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        TextView tv4 = findViewById(R.id.tv4);
        TextView tv0 = findViewById(R.id.tv0);
        TextView tv00 = findViewById(R.id.tv00);

        faCode.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv0.setOnClickListener(this);
        tv00.setOnClickListener(this);


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
            case R.id.imageDui:
                duiHaoBoolean = !duiHaoBoolean;
                if (duiHaoBoolean) {
                    imageDui.setImageResource(R.drawable.zhifu_yixuan);
                } else {
                    imageDui.setImageResource(R.drawable.zhifu_weixuan);
                }
                break;
            case R.id.tv00:
                //忘记密码
                startActivity(new Intent(mContext, ResetPwActivity.class));
                break;
            case R.id.tv0:
                //注册
                startActivity(new Intent(mContext, RegActivity.class));
                break;
            case R.id.tv1:
                //登录
                if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "手机号不能为空").show();
                    return;
                } else if (!StringUtil.isMobileNOCui(edit1.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "手机号码格式不正确").show();
                    return;
                } else if (TextUtils.isEmpty(edit2.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "密码不能为空").show();
                    return;
                } else if (!duiHaoBoolean) {
                    ToastFactory.getToast(mContext, "请先同意协议").show();
                    return;
                } else {
                    login2TypeMethod(edit1.getText().toString().trim(), MD5Util.encrypt(edit2.getText().toString().trim()));
                }
                break;
            case R.id.tv2:
                //密码登录
                startActivity(new Intent(mContext, Login1PhoneCodeActivity.class));
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


    private void login2TypeMethod(String mobile, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("password", password);
        params.put("registerId", registrationID);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.loginMethod, params, new SpotsCallBack<LoginBean>(mContext) {
            @Override
            public void onSuccess(Response response, LoginBean resultBean) {


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }
}

