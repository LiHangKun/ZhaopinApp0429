package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.MianShiDetailBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.view.MyDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Response;

//求职者--面试详情
public class MianShiDetailType2Activity extends BaseActivity {


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.miaoshidetailtype1_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        topTitle.setText("求职者看到的--面试详情");

        String interviewId = getIntent().getStringExtra("interviewId");

        getMianShiDetail(interviewId);


    }


    private void getMianShiDetail(String interviewId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("interviewId", interviewId);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.mianshiDetail, params, new SpotsCallBack<MianShiDetailBean>(mContext) {
            @Override
            public void onSuccess(Response response, MianShiDetailBean resultBean) {


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


    @OnClick({R.id.llView1OnClick, R.id.llView2OnClick, R.id.tv6Click, R.id.quxiaoTV, R.id.okID, R.id.qiuZhiView, R.id.quxiaoTv})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llView1OnClick:
                ToastFactory.getToast(mContext, "聊天").show();
                break;
            case R.id.llView2OnClick:
                ToastFactory.getToast(mContext, "打电话").show();
                break;
            case R.id.tv6Click:
                ToastFactory.getToast(mContext, "导航").show();
                break;
            case R.id.okID:
                ToastFactory.getToast(mContext, "我已到达").show();
                break;
            case R.id.qiuZhiView:
                //这个不要了
                break;
            case R.id.quxiaoTV:
                //取消面试的弹框
                View view = getLayoutInflater().inflate(R.layout.dialog_quxiao_mianshi, null);
                final MyDialog mMyDialog = new MyDialog(mContext, 0, 0, view, R.style.DialogTheme2);
                final EditText edit1 = view.findViewById(R.id.edit1);
                mMyDialog.setCancelable(true);
                mMyDialog.show();

                view.findViewById(R.id.quxiaoTV).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMyDialog.dismiss();
                    }
                });

                view.findViewById(R.id.okID).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                            ToastFactory.getToast(mContext, "取消原因不能为空").show();
                            return;
                        } else {
                            ToastFactory.getToast(mContext, edit1.getText().toString().trim()).show();
                            mMyDialog.dismiss();
                        }
                    }
                });
        }
    }
}
