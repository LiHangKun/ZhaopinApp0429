package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.ChangJianWenTiAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.WenTiBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

public class ChangJianWenTiActivity extends BaseActivity {

    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayout noDataLinView;

    private int nowPageIndex = 1;
    private int totalPage = 1;
    private static final String TAG = "ChangJianWenTiActivity";
    private List<WenTiBean.DataListBean> allList;
    private ChangJianWenTiAdapter changJianWenTiAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.changjianwenti_activity);
        init();
    }

    private void init() {
        topTitle.setText("常见问题");

        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
        noDataLinView = findViewById(R.id.noDataLinView);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        allList = new ArrayList<>();
        changJianWenTiAdapter = new ChangJianWenTiAdapter(mContext, allList);
        recyclerView.setAdapter(changJianWenTiAdapter);


        //下拉
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                allList.clear();
                nowPageIndex = 1;
                getDataList(String.valueOf(nowPageIndex), AppSP.pageCount);
                Log.i(TAG, "onRefresh: 执行下拉刷新方法");
            }
        });


        //上拉
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                if (nowPageIndex < totalPage) {
                    nowPageIndex++;
                    getDataList(String.valueOf(nowPageIndex), AppSP.pageCount);
                    Log.i(TAG, "onLoadMore: 执行上拉加载");
                    smartRefreshLayout.finishLoadMore();
                } else {
                    Log.i(TAG, "onLoadMore: 相等不可刷新");
                    //smartRefreshLayout.setEnableLoadMore(false);
                    smartRefreshLayout.finishRefresh(2000, true);//传入false表示刷新失败
                    smartRefreshLayout.finishLoadMore();
                }
            }
        });


        getDataList(String.valueOf(nowPageIndex), AppSP.pageCount);

    }

    private void getDataList(String pageNo, String pageSize) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.wenTiPage, params, new SpotsCallBack<WenTiBean>(mContext) {
            @Override
            public void onSuccess(Response response, WenTiBean resultBean) {
                smartRefreshLayout.finishRefresh();
                if (resultBean.getDataList() != null) {
                    totalPage = resultBean.getTotalPage();
                    if (resultBean.getDataList().size() == 0) {
                        recyclerView.setVisibility(View.GONE);
                        noDataLinView.setVisibility(View.VISIBLE);
                    } else {
                        if (nowPageIndex == 1) {
                            allList.clear();
                        }
                        recyclerView.setVisibility(View.VISIBLE);
                        noDataLinView.setVisibility(View.GONE);
                        allList.addAll(resultBean.getDataList());
                        changJianWenTiAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
                smartRefreshLayout.finishRefresh();
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
