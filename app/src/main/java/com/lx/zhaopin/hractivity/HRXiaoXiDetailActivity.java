package com.lx.zhaopin.hractivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.HRXiaoXiDetailAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.MessageDetailBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

public class HRXiaoXiDetailActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.xiaoxidetail_activity);
        init();
    }

    private void init() {
        topTitle.setText("消息");
        String messageId = getIntent().getStringExtra("messageId");
        recyclerView = findViewById(R.id.recyclerView);

        getMessageDetail(messageId);

    }

    //messageDetail
    private void getMessageDetail(String msgId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("msgId", msgId);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.HRMessageDetail, params, new SpotsCallBack<MessageDetailBean>(mContext) {
            @Override
            public void onSuccess(Response response, MessageDetailBean resultBean) {

                HRXiaoXiDetailAdapter hrXiaoXiDetailAdapter = new HRXiaoXiDetailAdapter(mContext, resultBean.getDataList());
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                recyclerView.setAdapter(hrXiaoXiDetailAdapter);

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
}
