package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.QiuZhiZheMyInfoBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MainActivity;
import com.lx.zhaopin.hr.MainHRActivity;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import okhttp3.Response;

public class SelectUserTypeActivity extends BaseActivity {
    @BindView(R.id.llView1OnClick)
    LinearLayout llView1OnClick;
    @BindView(R.id.llView2OnClick)
    LinearLayout llView2OnClick;
    private static final String TAG = "SelectUserTypeActivity";
    private String recruiter = "0";

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.selectusertype_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        topTitle.setText("切换身份");
        getQiuZhiMyInfo(SPTool.getSessionValue(AppSP.UID));
    }


    //求职者个人信息
    private void getQiuZhiMyInfo(String mid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", mid);
        Log.i(TAG, "求职者个人信息: " + NetClass.BASE_URL + NetCuiMethod.qiuZhiMyInfo + "---" + new Gson().toJson(params));
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.checkPhone, params, new BaseCallback<QiuZhiZheMyInfoBean>() {

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, QiuZhiZheMyInfoBean resultBean) {


                //是否具有hr权限 1 是 0 否
                recruiter = resultBean.getRecruiter();


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.llView1OnClick, R.id.llView2OnClick})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llView1OnClick:
                //求职者
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
                break;
            case R.id.llView2OnClick:
                //HR
                //是否具有hr权限 1 是 0 否
                if (recruiter.equals("1")) {
                    getHrRongToken(SPTool.getSessionValue(AppSP.UID));
                } else {
                    ToastFactory.getToast(mContext, "您还不是HR,请联系客服申请").show();
                    return;
                }

                break;
        }
    }

    private void getHrRongToken(String mid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", mid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.getHRRongToken, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {

                startActivity(new Intent(mContext, MainHRActivity.class));
                finish();

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }
}
