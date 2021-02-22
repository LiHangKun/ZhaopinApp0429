package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.hractivity.HRMianShiListActivity;
import com.lx.zhaopin.hradapter.HRDaiMianShiListAdapter;
import com.lx.zhaopin.hradapter.HRDaiMianShiListBean;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

public class HRDaiMianShiListActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private LinearLayout noDataLinView;
    private List<HRDaiMianShiListBean.DataListBean> allList;
    private HRDaiMianShiListAdapter daiMianShiListAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.daimianshilist_activity);
        init();
    }

    //HR 待面试
    private void init() {
        topTitle.setText("待面试");
        rightText.setText("面试记录");
        rightText.setVisibility(View.VISIBLE);
        rightText.setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerView);
        noDataLinView = findViewById(R.id.noDataLinView);

        getDataList("3");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        allList = new ArrayList<>();
        daiMianShiListAdapter = new HRDaiMianShiListAdapter(mContext, allList);
        recyclerView.setAdapter(daiMianShiListAdapter);


    }


    private void getDataList(String interviewStatus) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("interviewStatus", interviewStatus);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.HR_mianshiList, params, new SpotsCallBack<HRDaiMianShiListBean>(mContext) {
            @Override
            public void onSuccess(Response response, HRDaiMianShiListBean resultBean) {
                if (resultBean.getDataList() != null) {
                    if (resultBean.getDataList().size() == 0) {
                        recyclerView.setVisibility(View.GONE);
                        noDataLinView.setVisibility(View.VISIBLE);
                    } else {

                        recyclerView.setVisibility(View.VISIBLE);
                        noDataLinView.setVisibility(View.GONE);
                        allList.addAll(resultBean.getDataList());
                        daiMianShiListAdapter.notifyDataSetChanged();
                    }
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rightText:
                //面试记录
                startActivity(new Intent(mContext, HRMianShiListActivity.class));
                break;
        }
    }
}
