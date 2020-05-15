package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.SplashActivity;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.ActivityManager;
import com.lx.zhaopin.utils.MyCountDownTimer;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import okhttp3.Response;

public class ZhuXiaoActivity extends BaseActivity implements View.OnClickListener {

    private String phone;
    private TextView tv1;
    private TextView faCode;
    private TextView okID;
    private EditText edit1;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.zhuxiao_activity);
        init();
    }

    private void init() {
        topTitle.setText("注销用户");

        phone = SPTool.getSessionValue(AppSP.USER_PHONE);
        tv1 = findViewById(R.id.tv1);
        faCode = findViewById(R.id.faCode);
        okID = findViewById(R.id.okID);
        tv1.setText(phone);

        edit1 = findViewById(R.id.edit1);
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
                if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "验证码不能为空").show();
                    return;
                } else {
                    StyledDialog.init(ZhuXiaoActivity.this);
                    StyledDialog.buildIosAlert("提示", "注销账户后您的所有信息将会被清空\r\n请谨慎操作", new MyDialogListener() {
                        @Override
                        public void onFirst() {

                        }

                        @Override
                        public void onSecond() {
                            //TODO 注销账户
                            zhuXiaoUser(edit1.getText().toString().trim());

                        }
                    }).setBtnColor(R.color.mainColor2, R.color.mainColor1, 0).setBtnText("我再想想", "注销").show();
                }
                break;

        }
    }

    private void zhuXiaoUser(String authCode) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("authCode", authCode);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.sendPhoneCode, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                Intent intent = new Intent(ZhuXiaoActivity.this, SplashActivity.class);
                startActivity(intent);
                SPTool.addSessionMap(AppSP.UID, "");
                SPTool.addSessionMap(AppSP.USER_NAME, "");
                SPTool.addSessionMap(AppSP.USER_ICON, "");
                SPTool.addSessionMap(AppSP.USER_PHONE, "");
                SPTool.addSessionMap(AppSP.USER_RongToken, "");
                SPTool.addSessionMap(AppSP.USER_TYPE, "0");
                SPTool.addSessionMap(AppSP.isLogin, false);
                RongIM.getInstance().logout();
                ActivityManager.finishActivity();
                finish();

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
        params.put("type", "6");
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
