package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.DaiMianShiListAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.DaimianshiBean;
import com.lx.zhaopin.bean.WeimianshiDetailBean;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Response;

public class DaiMianShiListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.mianshi_list)
    TextView mianshiList;
    private RecyclerView recyclerView;
    private LinearLayout noDataLinView;
    private List<WeimianshiDetailBean.DataListBean> allList;
    private DaiMianShiListAdapter daiMianShiListAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.daimianshilist_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        baseTop.setVisibility(View.GONE);
        topTitle.setText("待面试");
        titleTv.setText("待面试");
        mianshiList.setVisibility(View.VISIBLE);
        rightText.setText("面试记录");
        rightText.setVisibility(View.VISIBLE);
        rightText.setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerView);
        noDataLinView = findViewById(R.id.noDataLinView);

        getDataList("3");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        allList = new ArrayList<>();
        daiMianShiListAdapter = new DaiMianShiListAdapter(mContext, allList);
        recyclerView.setAdapter(daiMianShiListAdapter);


    }


    private void getDataList(String interviewStatus) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("interviewStatus", interviewStatus);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.mianshiList, params, new SpotsCallBack<WeimianshiDetailBean>(mContext) {
            @Override
            public void onSuccess(Response response, WeimianshiDetailBean resultBean) {
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
                startActivity(new Intent(mContext, MianShiListActivity.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.left_layout, R.id.mianshi_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_layout:
                onBackPressed();
                break;
            case R.id.mianshi_list:
                //面试记录
                startActivity(new Intent(mContext, MianShiListActivity.class));
                break;
        }
    }
}
