package com.lx.zhaopin.hractivity;

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
import com.lx.zhaopin.activity.RenCaiDetailActivity;
import com.lx.zhaopin.adapter.SouRenCaiAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.RenCaiListBean;
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

public class HRSearchSuccessActivity extends BaseActivity implements View.OnClickListener {

    private TextView searchTv;
    private ImageView back;
    private ClearEditText clearEditText;
    private String keyWord;
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayout noDataLinView;
    private static final String TAG = "HRSearchSuccessActivity";
    private int nowPageIndex = 1;
    private int totalPage = 1;
    private List<RenCaiListBean.DataListBean> allList;
    private Intent intent;
    private String cityId = "";
    private SouRenCaiAdapter souRenCaiAdapter;

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


        getDataList("2", keyWord, String.valueOf(nowPageIndex), AppSP.pageCount);

        allList = new ArrayList<>();
        souRenCaiAdapter = new SouRenCaiAdapter(mContext, allList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(souRenCaiAdapter);
        souRenCaiAdapter.setOnItemClickListener(getRid());

        //下拉
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                allList.clear();
                nowPageIndex = 1;
                getDataList("2", keyWord, String.valueOf(nowPageIndex), AppSP.pageCount);
                Log.i(TAG, "onRefresh: 执行下拉刷新方法");
            }
        });


        //上拉
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                if (nowPageIndex < totalPage) {
                    nowPageIndex++;
                    getDataList("2", keyWord, String.valueOf(nowPageIndex), AppSP.pageCount);
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
    private SouRenCaiAdapter.OnItemClickListener getRid() {
        return new SouRenCaiAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(String rid) {
                intent = new Intent(mContext, RenCaiDetailActivity.class);
                intent.putExtra("rid", rid);
                startActivity(intent);
            }
        };
    }


    //职位分页列表求职者
    private void getDataList(String dataType, String name, String pageNo, String pageSize) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("dataType", dataType);
        params.put("name", name);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.HRSouRenCai, params, new SpotsCallBack<RenCaiListBean>(mContext) {
            @Override
            public void onSuccess(Response response, RenCaiListBean resultBean) {
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
                        souRenCaiAdapter.notifyDataSetChanged();
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
                    getDataList("2", keyWord, String.valueOf(nowPageIndex), AppSP.pageCount);

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
                getDataList("2", keyWord, String.valueOf(nowPageIndex), AppSP.pageCount);
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
