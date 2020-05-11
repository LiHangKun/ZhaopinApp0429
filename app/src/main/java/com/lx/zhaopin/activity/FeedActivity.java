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
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

public class FeedActivity extends BaseActivity implements View.OnClickListener {

    private TextView okID;
    private EditText edit2;
    private EditText edit1;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.feed_activity);
        init();
    }

    private void init() {
        topTitle.setText("意见反馈");

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        okID = findViewById(R.id.okID);


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
                //反馈
                if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "多少写点内容吧...").show();
                    return;
                } else if (TextUtils.isEmpty(edit2.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "联系方式不能为空").show();
                    return;
                } else {
                    feedMethod(edit1.getText().toString().trim(), edit2.getText().toString().trim());
                }
                break;
        }
    }

    //反馈
    private void feedMethod(String content, String phone) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("content", content);
        params.put("phone", phone);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.feedback, params, new SpotsCallBack<PhoneStateBean>(mContext) {
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
}
