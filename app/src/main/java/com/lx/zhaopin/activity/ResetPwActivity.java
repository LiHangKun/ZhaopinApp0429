package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.MD5Util;
import com.lx.zhaopin.utils.MyCountDownTimer;
import com.lx.zhaopin.utils.StringUtil;
import com.lx.zhaopin.utils.ToastFactory;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import okhttp3.Response;

public class ResetPwActivity extends BaseActivity {

    private static final String TAG = "ResetPwActivity";
    @BindView(R.id.edit1)
    EditText edit1;
    @BindView(R.id.edit2)
    EditText edit2;
    @BindView(R.id.edit3)
    EditText edit3;
    @BindView(R.id.faCode)
    TextView faCode;
    @BindView(R.id.okID)
    TextView okID;

    String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.resetpw_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        topTitle.setText("找回密码");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.faCode, R.id.okID})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.faCode:
                if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "手机号不能为空").show();
                    return;
                } else if (!StringUtil.isMobileNOCui(edit1.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "手机号码格式不正确").show();
                    return;
                } else {
                    //发送验证码,判断手机号是否可以用
                    checkPhoneMethod(edit1.getText().toString().trim());
                }
                break;
            case R.id.okID:
                if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "手机号不能为空").show();
                    return;
                } else if (!StringUtil.isMobileNOCui(edit1.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "手机号码格式不正确").show();
                    return;
                } else if (TextUtils.isEmpty(edit2.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "验证码不能为空").show();
                    return;
                } else if (TextUtils.isEmpty(edit3.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "登录密码不能为空").show();
                    return;
                } else if (!edit3.getText().toString().trim().matches(regex)) {
                    ToastFactory.getToast(mContext, "密码必须8--16位包含数字加字母,请重试").show();
                    //edit2.setText("");
                    return;
                } else {
                    zhaoHuiMethod(edit1.getText().toString().trim(), edit2.getText().toString().trim(), MD5Util.encrypt(edit3.getText().toString().trim()));
                }
                break;
        }
    }

    private void zhaoHuiMethod(String trim, String trim1, String encrypt) {
        Log.i(TAG, "zhaoHuiMethod: " + trim + "--" + trim1 + "--" + encrypt);

    }


    //验证手机号是否已注册 接口中涉及到”是/否”的字段都是：1表示是，0表示否
    private void checkPhoneMethod(final String mobile) {
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        Log.i(TAG, "判断手机号码是否已注册: " + NetClass.BASE_URL + NetCuiMethod.checkPhone + "---" + new Gson().toJson(params));
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.checkPhone, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                if (resultBean.getExist().equals("1")) {
                    ToastFactory.getToast(mContext, "手机号已存在").show();
                    return;
                } else {
                    sendPhoneCode("1", mobile);
                }

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }

    //发送验证码
    private void sendPhoneCode(String type, String mobile) {
        Map<String, String> params = new HashMap<>();
        params.put("type", type);
        params.put("mobile", mobile);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.sendPhoneCode, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {

                ToastFactory.getToast(mContext, resultBean.getAuthCode()).show();
                MyCountDownTimer timer = new MyCountDownTimer(mContext, faCode, 60 * 1000, 1000);
                timer.start();
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


}