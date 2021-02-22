package com.lx.zhaopin.hractivity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

//页面不要了
public class HRBuHeShiActivity extends BaseActivity {

    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayout noDataLinView;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.hrbuheshi_activity);
        initMe();
    }

    private void initMe() {
        topTitle.setText("HR不合适");
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
        noDataLinView = findViewById(R.id.noDataLinView);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
