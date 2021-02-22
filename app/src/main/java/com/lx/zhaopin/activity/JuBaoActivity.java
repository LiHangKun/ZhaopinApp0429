package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.ZhiWeiDetailBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Response;

public class JuBaoActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.roundedImageView)
    RoundedImageView roundedImageView;
    @BindView(R.id.edit1)
    EditText edit1;
    private String pid;
    private static final String TAG = "JuBaoActivity";
    private RoundedImageView roundedImageView1;
    private TextView tv21;
    private TextView tv11;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.jubao_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        topTitle.setText("举报");
        pid = getIntent().getStringExtra("pid");

        tv11 = findViewById(R.id.tv1);
        tv21 = findViewById(R.id.tv2);
        roundedImageView1 = findViewById(R.id.roundedImageView);


        TextView okID = findViewById(R.id.okID);
        okID.setOnClickListener(this);


        getZhiWeiDetail(pid);

    }

    //职位详情
    private void getZhiWeiDetail(String pid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pid", pid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.zhiWeiDetail, params, new SpotsCallBack<ZhiWeiDetailBean>(mContext) {
            @Override
            public void onSuccess(Response response, ZhiWeiDetailBean resultBean) {

                tv1.setText(resultBean.getName());
                tv2.setText(resultBean.getCompany().getName());
                Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror).error(R.mipmap.imageerror))
                        .load(resultBean.getCompany().getLogo()).into(roundedImageView);

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.okID:
                if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "举报原因不能为空").show();
                    return;
                } else {
                    juBao(edit1.getText().toString().trim());
                }

                break;
        }
    }

    //职位举报 zhiWeiJuBao
    private void juBao(String reason) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pid", pid);
        params.put("reason", reason);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.zhiWeiJuBao, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {

                //TODO   Success
                startActivity(new Intent(mContext, JuBaoSuccessActivity.class));
                finish();


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


}
