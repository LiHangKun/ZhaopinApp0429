package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.LoginBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MainActivity;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.common.NoticeDetailActivity;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.MD5Util;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.StringUtil;
import com.lx.zhaopin.utils.ToastFactory;

import org.greenrobot.eventbus.EventBus;

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
    private Intent intent;
    private RelativeLayout closeLayout;
    private TextView phoneTv;
    private TextView codeTv;
    private ImageView hideImg;
    private int hide=0;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.login2password_activity);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    private void init() {
        topTitle.setText("密码登录");

        /*if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }*/

        topTitle.setVisibility(View.INVISIBLE);
        viewBase.setVisibility(View.INVISIBLE);
        baseTop.setVisibility(View.GONE);

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
        phoneTv=findViewById(R.id.phone_tv);
        codeTv=findViewById(R.id.code_tv);
        hideImg=findViewById(R.id.hide_img);

        hideImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hide==0){
                    hide=1;
                    hideImg.setImageResource(R.drawable.mima_show);
                    edit2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else if(hide==1){
                    hide=0;
                    hideImg.setImageResource(R.drawable.mima_invisible);
                    edit2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        closeLayout=findViewById(R.id.back_layout);
        closeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
//                intent = new Intent(Login2PassWordActivity.this, MainActivity.class);//启动MainActivity
//                //SPTool.addSessionMap(AppSP.USER_TYPE, "0");
//                SPTool.addSessionMap(AppSP.USER_TYPE, SPTool.getSessionValue(AppSP.USER_TYPE));
//                startActivity(intent);
//                finish();//关闭当前活动
            }
        });

        faCode.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv0.setOnClickListener(this);
        tv00.setOnClickListener(this);


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
                SPTool.addSessionMap(AppSP.isLogin, true);
                String RongToken = resultBean.getJRongToken();
                String mid = resultBean.getMid();
                String name = resultBean.getName();
                String avatar = resultBean.getAvatar();
                String userPhone = resultBean.getMobile();
                String duanUid = resultBean.getId();
                EventBus.getDefault().post(new MessageEvent(2, mid, name, avatar, RongToken, duanUid, null));
                //TODO 保存的数据
                SPTool.addSessionMap(AppSP.UID, mid);
                Log.e(TAG, "onSuccess: http 保存数据2" + SPTool.getSessionValue(AppSP.UID) + "-----");
                SPTool.addSessionMap(AppSP.USER_NAME, name);
                SPTool.addSessionMap(AppSP.UID_DUAN, duanUid);
                SPTool.addSessionMap(AppSP.USER_ICON, avatar);
                SPTool.addSessionMap(AppSP.USER_POSITIO_NNAME, resultBean.getPositionName());
                SPTool.addSessionMap(AppSP.USER_PHONE, userPhone);
                SPTool.addSessionMap(AppSP.USER_RongToken, RongToken);
                String HR_RongToken = resultBean.getrRongToken();
                SPTool.addSessionMap(AppSP.USER_HR_RongToken, HR_RongToken);
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
}

