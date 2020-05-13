package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.QiuZhiZheMyInfoBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
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

public class MyYinSiActivity extends BaseActivity {
    @BindView(R.id.imageState1)
    ImageView imageState1;
    @BindView(R.id.relView1)
    RelativeLayout relView1;
    @BindView(R.id.imageState2)
    ImageView imageState2;
    @BindView(R.id.relView2)
    RelativeLayout relView2;
    private static final String TAG = "MyYinSiActivity";
    private String openResume;
    private String openChat;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.myyinsi_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        topTitle.setText("隐私设置");
        getQiuZhiMyInfo(SPTool.getSessionValue(AppSP.UID));
    }

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

                //"openResume":"1",//是否开放简历
                openResume = resultBean.getOpenResume();
                // 1 是 0 否
                switch (openResume) {
                    case "0":
                        imageState1.setImageResource(R.drawable.guan);
                        break;
                    case "1":
                        imageState1.setImageResource(R.drawable.kai);
                        break;
                }

                //"openChat":"1",//是否开放沟通
                openChat = resultBean.getOpenChat();
                switch (openChat) {
                    case "0":
                        imageState2.setImageResource(R.drawable.guan);
                        break;
                    case "1":
                        imageState2.setImageResource(R.drawable.kai);
                        break;
                }

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


    @OnClick({R.id.relView1, R.id.relView2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relView1:
                //是否开放简历
                xiuGaiOpenJianLi(openResume);
                break;
            case R.id.relView2:
                //是否允许企业主动和我沟通
                xiuGaiOpenChat(openChat);
                break;
        }
    }

    //修改简历开关
    private void xiuGaiOpenJianLi(String openResume) {
        Map<String, String> params = new HashMap<>();

        switch (openResume) {
            case "0":
                params.put("openResume", "1");
                break;
            case "1":
                params.put("openResume", "0");
                break;
        }
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.xiugaiQiuZhiMyInfo, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                ToastFactory.getToast(mContext, resultBean.getResultNote()).show();

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });


    }


    //修改是否可以和我主动沟通
    private void xiuGaiOpenChat(String openChat) {
        Map<String, String> params = new HashMap<>();

        switch (openChat) {
            case "0":
                params.put("openChat", "1");
                break;
            case "1":
                params.put("openChat", "0");
                break;
        }
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.xiugaiQiuZhiMyInfo, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                ToastFactory.getToast(mContext, resultBean.getResultNote()).show();

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });


    }

}
