package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.ShouYe1FragmentAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.ShouYeQiuZhiZheBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.view.ClearEditText;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

public class SearchSuccessActivity extends BaseActivity implements View.OnClickListener {

    private TextView searchTv;
    private ImageView back;
    private ClearEditText clearEditText;
    private String keyWord;
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayout noDataLinView;
    private static final String TAG = "SearchSuccessActivity";
    private int nowPageIndex = 1;
    private int totalPage = 1;
    private List<ShouYeQiuZhiZheBean.DataListBean> allList;
    private ShouYe1FragmentAdapter shouYe1FragmentAdapter;
    private Intent intent;
    private String cityId = "";

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.searchsuccess_activity);
        init();
    }

    private void init() {
        keyWord = getIntent().getStringExtra("KeyWord");

        back = findViewById(R.id.back);
        searchTv = findViewById(R.id.searchTv);
        clearEditText = findViewById(R.id.clearEditText);
        clearEditText.setText(keyWord);
        back.setOnClickListener(this);
        searchTv.setOnClickListener(this);

        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
        noDataLinView = findViewById(R.id.noDataLinView);


        getDataList("2", keyWord, SPTool.getSessionValue(AppSP.sStringJ), SPTool.getSessionValue(AppSP.sStringW), cityId, String.valueOf(nowPageIndex), AppSP.pageCount);

        allList = new ArrayList<>();
        shouYe1FragmentAdapter = new ShouYe1FragmentAdapter(mContext,allList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(shouYe1FragmentAdapter);
        shouYe1FragmentAdapter.SetOnItemClickListener(getPid());

        //下拉
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                allList.clear();
                nowPageIndex = 1;
                getDataList("2", keyWord, SPTool.getSessionValue(AppSP.sStringJ), SPTool.getSessionValue(AppSP.sStringW), cityId, String.valueOf(nowPageIndex), AppSP.pageCount);
                Log.i(TAG, "onRefresh: 执行下拉刷新方法");
            }
        });


        //上拉
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                if (nowPageIndex < totalPage) {
                    nowPageIndex++;
                    getDataList("2", keyWord, SPTool.getSessionValue(AppSP.sStringJ), SPTool.getSessionValue(AppSP.sStringW), cityId, String.valueOf(nowPageIndex), AppSP.pageCount);
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


    }


    @NotNull
    private ShouYe1FragmentAdapter.OnItemClickListener getPid() {
        return new ShouYe1FragmentAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(String pid) {
                intent = new Intent(mContext, GangWeiDetailActivity.class);
                intent.putExtra("pid", pid);
                startActivity(intent);
            }
        };
    }

    //职位分页列表求职者
    private void getDataList(String dataType, String name, String lng, String lat, String cityId, String pageNo, String pageSize) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("dataType", dataType);
        params.put("name", name);
        params.put("lng", lng);
        params.put("lat", lat);
        params.put("cityId", cityId);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.zhiWeiPageList, params, new SpotsCallBack<ShouYeQiuZhiZheBean>(mContext) {
            @Override
            public void onSuccess(Response response, ShouYeQiuZhiZheBean resultBean) {
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
                        shouYe1FragmentAdapter.notifyDataSetChanged();
                    }
                    shouYe1FragmentAdapter.getDataType(resultBean.getDataType());
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.searchTv:
                if (TextUtils.isEmpty(clearEditText.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "请输入关键词").show();
                    return;
                } else {
                    //开始搜索
                    String keyWord = clearEditText.getText().toString().trim();
                    getDataList("2", keyWord, SPTool.getSessionValue(AppSP.sStringJ), SPTool.getSessionValue(AppSP.sStringW), cityId, String.valueOf(nowPageIndex), AppSP.pageCount);

                }
                break;
        }
    }


    //这个是处理输入法键盘上的搜索按钮
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction()) {
            String keyWord = clearEditText.getText().toString().trim();
            if (TextUtils.isEmpty(keyWord)) {
                ToastFactory.getToast(mContext, "请输入关键词").show();
            } else {
                //开始搜索
                getDataList("2", keyWord, SPTool.getSessionValue(AppSP.sStringJ), SPTool.getSessionValue(AppSP.sStringW), cityId, String.valueOf(nowPageIndex), AppSP.pageCount);
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
