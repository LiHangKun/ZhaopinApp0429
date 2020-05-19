package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.MianShiListAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.MianShiListBean;
import com.lx.zhaopin.common.AppSP;
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

public class MianShiListActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private LinearLayout noDataLinView;
    private List<MianShiListBean.DataListBean> allList;
    private MianShiListAdapter mianShiListAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.mianshilist_activity);
        init();
    }

    private void init() {
        topTitle.setText("求职者面试记录");
        recyclerView = findViewById(R.id.recyclerView);
        noDataLinView = findViewById(R.id.noDataLinView);


        getDataList("");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        allList = new ArrayList<>();
        mianShiListAdapter = new MianShiListAdapter(mContext, allList);
        recyclerView.setAdapter(mianShiListAdapter);


    }

    private void getDataList(String interviewStatus) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("interviewStatus", interviewStatus);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.mianshiList, params, new SpotsCallBack<MianShiListBean>(mContext) {
            @Override
            public void onSuccess(Response response, MianShiListBean resultBean) {
                if (resultBean.getDataList() != null) {
                    if (resultBean.getDataList().size() == 0) {
                        recyclerView.setVisibility(View.GONE);
                        noDataLinView.setVisibility(View.VISIBLE);
                    } else {

                        recyclerView.setVisibility(View.VISIBLE);
                        noDataLinView.setVisibility(View.GONE);
                        allList.addAll(resultBean.getDataList());
                        mianShiListAdapter.notifyDataSetChanged();
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
}
