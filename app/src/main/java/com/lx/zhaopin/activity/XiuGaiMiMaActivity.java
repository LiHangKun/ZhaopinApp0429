package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.MD5Util;
import com.lx.zhaopin.utils.MyCountDownTimer;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

public class XiuGaiMiMaActivity extends BaseActivity implements View.OnClickListener {

    private TextView okID;
    private TextView faCode;
    private EditText edit2;
    private EditText edit1;
    private String phone;
    private TextView tv1;
    String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.xiugaimima_activity);
        init();
    }

    private void init() {
        topTitle.setText("修改密码");
        baseback.setImageResource(R.drawable.login_close_img);
        phone = SPTool.getSessionValue(AppSP.USER_PHONE);
        tv1 = findViewById(R.id.tv1);
        faCode = findViewById(R.id.faCode);
        okID = findViewById(R.id.okID);
        tv1.setText(phone);

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);

        okID.setOnClickListener(this);
        faCode.setOnClickListener(this);


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
            case R.id.faCode:
                //发送验证码
                faPhoneCode(phone);
                break;
            case R.id.okID:
                //修改密码
                if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "验证码不能为空").show();
                    return;
                } else if (TextUtils.isEmpty(edit2.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "密码不能为空").show();
                    return;
                } else if (!edit2.getText().toString().trim().matches(regex)) {
                    ToastFactory.getToast(XiuGaiMiMaActivity.this, "密码必须8--16位包含数字加字母,请重试").show();
                    //edit2.setText("");
                    return;
                } else {
                    xiuGaiMiMaMethod(edit1.getText().toString().trim(), MD5Util.encrypt(edit2.getText().toString().trim()));
                }

                break;
        }
    }

    //修改密码
    private void xiuGaiMiMaMethod(String authCode, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("mobile", phone);
        params.put("authCode", authCode);
        params.put("password", password);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.retrievePassword, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                ToastFactory.getToast(mContext, resultBean.getResultNote()).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 500);

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


    //发送验证码
    private void faPhoneCode(String mobile) {
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("type", "5");
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.sendPhoneCode, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {

                ToastFactory.getToast(mContext, resultBean.getResultNote()).show();
                MyCountDownTimer timer = new MyCountDownTimer(mContext, faCode, 60 * 1000, 1000);
                timer.start();
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }
}
