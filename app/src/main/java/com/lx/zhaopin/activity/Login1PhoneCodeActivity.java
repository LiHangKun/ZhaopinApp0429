package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.LoginBean;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MainActivity;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.common.NoticeDetailActivity;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.other.NormalTextWatcher;
import com.lx.zhaopin.utils.MyCountDownTimer;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.StringUtil;
import com.lx.zhaopin.utils.ToastFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Request;
import okhttp3.Response;

public class Login1PhoneCodeActivity extends BaseActivity implements View.OnClickListener {

    private EditText edit1;
    private EditText edit2;
    private static final String TAG = "Login1PhoneCodeActivity";
    private TextView faCode;
    private Intent intent;
    private ImageView imageDui;
    private boolean duiHaoBoolean = true;
    private String registrationID;
    private TextView phoneTv;
    private TextView codeTv;
    private ImageView closeImg;
    private RelativeLayout closeLayout;
    private boolean phoneReady, codeReady;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.login1phonecode_activity);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void init() {
        topTitle.setText("短信验证码登录");
        topTitle.setVisibility(View.INVISIBLE);
        viewBase.setVisibility(View.INVISIBLE);
        baseTop.setVisibility(View.GONE);

        /*if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }*/

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        registrationID = JPushInterface.getRegistrationID(this);
        SPTool.addSessionMap(AppSP.JupshID, registrationID);
        Log.i(TAG, "onCreate:极光信息 " + registrationID);
//        baseback.setImageResource(R.drawable.login_close_img);
        baseback.setVisibility(View.GONE);
        backImg.setVisibility(View.VISIBLE);
        closeImg=findViewById(R.id.close_login_img);
        closeLayout=findViewById(R.id.back_layout);
        closeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Login1PhoneCodeActivity.this, MainActivity.class);//启动MainActivity
                //SPTool.addSessionMap(AppSP.USER_TYPE, "0");
                SPTool.addSessionMap(AppSP.USER_TYPE, SPTool.getSessionValue(AppSP.USER_TYPE));
                startActivity(intent);
                finish();//关闭当前活动
            }
        });
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        imageDui = findViewById(R.id.imageDui);
        imageDui.setOnClickListener(this);
        faCode = findViewById(R.id.faCode);
        phoneTv=findViewById(R.id.phone_tv);
        codeTv=findViewById(R.id.code_tv);

        final TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        TextView tv4 = findViewById(R.id.tv4);
        TextView tv0 =  findViewById(R.id.tv0);

        faCode.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv0.setOnClickListener(this);

        edit1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                edit1.setHint("");
                phoneTv.setVisibility(View.VISIBLE);
                return false;
            }
        });

        edit2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                edit2.setHint("");
                codeTv.setVisibility(View.VISIBLE);
                return false;
            }
        });

        edit1.addTextChangedListener(new NormalTextWatcher(edit1, 11) {
            @Override
            protected void showBtnEnable(boolean enable) {
                phoneReady = enable;
                if (phoneReady && codeReady){
                    tv1.setEnabled(true);
                }
            }
        });

        edit2.addTextChangedListener(new NormalTextWatcher(edit2, 6) {
            @Override
            protected void showBtnEnable(boolean enable) {
                codeReady = enable;
                if (phoneReady && codeReady){
                    tv1.setEnabled(true);
                }
            }
        });

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
            case R.id.imageDui:
                duiHaoBoolean = !duiHaoBoolean;
                if (duiHaoBoolean) {
                    imageDui.setImageResource(R.drawable.zhifu_yixuan);
                } else {
                    imageDui.setImageResource(R.drawable.zhifu_weixuan);
                }
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
                    ToastFactory.getToast(mContext, "验证码不能为空").show();
                    return;
                } else if (!duiHaoBoolean) {
                    ToastFactory.getToast(mContext, "请先同意协议").show();
                    return;
                } else {
                    login1TypeMethod(edit1.getText().toString().trim(), edit2.getText().toString().trim());
                }
                break;
            case R.id.tv2:
                //密码登录
                startActivity(new Intent(mContext, Login2PassWordActivity.class));
                break;
            case R.id.tv3:
                //用户协议
                intent = new Intent(mContext, NoticeDetailActivity.class);
                intent.putExtra("title", "用户协议");
                intent.putExtra("titleUrl", NetClass.Web_XieYi1);
                startActivity(intent);
                break;
            case R.id.tv4:
                //隐私政策
                intent = new Intent(mContext, NoticeDetailActivity.class);
                intent.putExtra("title", "隐私政策");
                intent.putExtra("titleUrl", NetClass.Web_XieYi2);
                startActivity(intent);
                break;
            case R.id.tv0:
                //注册
                startActivity(new Intent(mContext, RegActivity.class));
                break;
        }
    }


    private void login1TypeMethod(String mobile, String authCode) {
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("authCode", authCode);
        params.put("registerId", registrationID);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.loginMethod, params, new SpotsCallBack<LoginBean>(mContext) {
            @Override
            public void onSuccess(Response response, LoginBean resultBean) {

                SPTool.addSessionMap(AppSP.isLogin, true);
                String RongToken = resultBean.getJRongToken();
                String mid = resultBean.getMid();
                String name = resultBean.getName();
                String avatar = resultBean.getAvatar();
                String userPhone = resultBean.getMobile();
                String duanUid = resultBean.getId();
                String HR_RongToken = resultBean.getrRongToken();
                EventBus.getDefault().post(new MessageEvent(2, mid, name, avatar, RongToken, duanUid, null));
                //TODO 保存的数据
                SPTool.addSessionMap(AppSP.USER_HR_RongToken, HR_RongToken);
                SPTool.addSessionMap(AppSP.UID, mid);
                SPTool.getSessionValue(AppSP.UID);
                Log.e(TAG, "onSuccess: http 保存数据1" + SPTool.getSessionValue(AppSP.UID) + "-----");
                SPTool.addSessionMap(AppSP.USER_NAME, name);
                SPTool.addSessionMap(AppSP.USER_POSITIO_NNAME, resultBean.getPositionName());
                SPTool.addSessionMap(AppSP.UID_DUAN, duanUid);
                SPTool.addSessionMap(AppSP.USER_ICON, avatar);
                SPTool.addSessionMap(AppSP.USER_PHONE, userPhone);
                SPTool.addSessionMap(AppSP.USER_RongToken, RongToken);
                SPTool.addSessionMap(AppSP.USER_TYPE, "0");
                SPTool.addSessionMap(AppSP.USER_HR_PERMISSION, TextUtils.equals("1", resultBean.getHr()));


                //1 进入界面 SelectHangYeActivity
                //0 进入主页
                String neverLogin = resultBean.getNeverLogin();
                if (neverLogin.equals("1")) {
                    intent = new Intent(mContext, SelectHangYeActivity.class);
                    intent.putExtra("mid", mid);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    AppSP.isToShopCar = true;
                    startActivity(intent);
                    finish();
                }


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
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
                if (resultBean.getExist().equals("0")) {
                    ToastFactory.getToast(mContext, "手机号不存在").show();
                    return;
                } else {
                    sendPhoneCode("2", mobile);
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
