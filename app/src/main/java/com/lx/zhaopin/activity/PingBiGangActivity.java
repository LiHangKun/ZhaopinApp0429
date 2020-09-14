package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.PingBiGangAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.PingBiGangWeiBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import okhttp3.Response;

public class PingBiGangActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayout noDataLinView;
    private int nowPageIndex = 1;
    private int totalPage = 1;
    private static final String TAG = "PingBiGangActivity";
    private List<PingBiGangWeiBean.DataListBean> allList;
    private PingBiGangAdapter pingBiGangAdapter;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.pingbigang_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        titleTv.setText("屏蔽岗位");
        baseTop.setVisibility(View.GONE);
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
        noDataLinView = findViewById(R.id.noDataLinView);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        allList = new ArrayList<>();
        pingBiGangAdapter = new PingBiGangAdapter(mContext, allList);
        recyclerView.setAdapter(pingBiGangAdapter);

        pingBiGangAdapter.setOnItemClickListener(getOnItemClickListener());


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

    @NotNull
    private PingBiGangAdapter.OnItemClickListener getOnItemClickListener() {
        return new PingBiGangAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, final String id, String open) {
                switch (view.getId()) {
                    case R.id.tv7:
                        //取消屏蔽岗位
                        StyledDialog.init(mContext);
                        StyledDialog.buildIosAlert("", "\r是否取消屏蔽岗位?", new MyDialogListener() {
                            @Override
                            public void onFirst() {

                            }

                            @Override
                            public void onSecond() {
                                quXiaoPingBiGang(id);


                            }
                        }).setBtnColor(R.color.mainColor2, R.color.mainColor1, 0).setBtnText("取消", "确定").show();
                        break;
                    case R.id.llView:
                        //0.已停招，1.未停招
                        if (open.equals("0")) {
                            ToastFactory.getToast(mContext, "该职位已停职").show();
                            return;
                        } else {
                            Intent intent = new Intent(mContext, GangWeiDetailActivity.class);
                            intent.putExtra("pid", id);
                            startActivity(intent);
                        }

                        break;
                }


            }
        };
    }

    private void quXiaoPingBiGang(String pid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pid", pid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.zhiWeiIsHeShi, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                getDataList(String.valueOf(nowPageIndex), AppSP.pageCount);
                EventBus.getDefault().post(new MessageEvent(11, null, null, null, null, null, null));

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


    private void getDataList(String pageNo, String pageSize) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.pingBiGangList, params, new SpotsCallBack<PingBiGangWeiBean>(mContext) {
            @Override
            public void onSuccess(Response response, PingBiGangWeiBean resultBean) {
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
                        pingBiGangAdapter.notifyDataSetChanged();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.left_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_layout:
                onBackPressed();
                break;
        }
    }
}
