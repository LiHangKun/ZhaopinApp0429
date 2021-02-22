package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.MyShouCangGangAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.SavePersonBean;
import com.lx.zhaopin.bean.ShouCangZhiWeiBean;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import okhttp3.Response;

public class MyShouCangGangActivity extends BaseActivity {

    @BindView(R.id.zhiwei_tv)
    TextView zhiweiTv;
    @BindView(R.id.zhiwei_line)
    View zhiweiLine;
    @BindView(R.id.zhiwei_layout)
    LinearLayout zhiweiLayout;
    @BindView(R.id.niuren_tv)
    TextView niurenTv;
    @BindView(R.id.niuren_line)
    View niurenLine;
    @BindView(R.id.niuren_layout)
    LinearLayout niurenLayout;
    @BindView(R.id.niuren_recyclerView)
    RecyclerView niurenRecyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayout noDataLinView;
    private int nowPageIndex = 1;
    private int totalPage = 1;
    private int personPage=1;
    private int personTotal=1;
    private int personOrZhiwei=0;
    private static final String TAG = "MyShouCangGangActivity";
    private List<ShouCangZhiWeiBean.DataListBean> allList;
    private List<ShouCangZhiWeiBean.DataListBean> personAllList;
    private MyShouCangGangAdapter myShouCangGangAdapter;
    private RelativeLayout leftLayout;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.myshoucanggang_activity);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void init() {
        baseTop.setVisibility(View.GONE);
        topTitle.setText("我的收藏");
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
        noDataLinView = findViewById(R.id.noDataLinView);
        leftLayout = findViewById(R.id.left_layout);
        leftLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        allList = new ArrayList<>();
        myShouCangGangAdapter = new MyShouCangGangAdapter(mContext, allList);
        recyclerView.setAdapter(myShouCangGangAdapter);

        myShouCangGangAdapter.setOnItemClickListener(new MyShouCangGangAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, final String pid, String open) {

                switch (view.getId()) {
                    case R.id.llView:
                        //0.已停招，1.未停招
                        if (open.equals("0")) {
                            ToastFactory.getToast(mContext, "该职位已停职").show();
                            return;
                        } else {
                            //职位详情
                            Intent intent = new Intent(mContext, GangWeiDetailActivity.class);
                            intent.putExtra("pid", pid);
                            startActivity(intent);
                        }
                        break;
                    case R.id.tv7:
                        //取消收藏岗位
                        StyledDialog.init(mContext);
                        StyledDialog.buildIosAlert("", "\r是否取消收藏岗位?", new MyDialogListener() {
                            @Override
                            public void onFirst() {

                            }

                            @Override
                            public void onSecond() {
                                quXiaoShouCangGang(pid);


                            }
                        }).setBtnColor(R.color.mainColor2, R.color.mainColor1, 0).setBtnText("取消", "确定").show();
                        break;
                }


            }
        });


        //下拉
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if(personOrZhiwei==0){
                    allList.clear();
                    nowPageIndex = 1;
                    getDataList(String.valueOf(nowPageIndex), AppSP.pageCount);
                    Log.i(TAG, "onRefresh: 执行下拉刷新方法");
                }else if(personOrZhiwei==1){
//                    personAllList.clear();
                    personPage = 1;
                    getNiurenDataList(String.valueOf(personPage), AppSP.pageCount);
                    Log.i(TAG, "onRefresh: 执行下拉刷新方法");
                }

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

    private void quXiaoShouCangGang(String pid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pid", pid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.zhiWeiShouCang, params, new BaseCallback<PhoneStateBean>() {
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
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.shouCangZhiWeiList, params, new SpotsCallBack<ShouCangZhiWeiBean>(mContext) {
            @Override
            public void onSuccess(Response response, ShouCangZhiWeiBean resultBean) {
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
                        myShouCangGangAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
                smartRefreshLayout.finishRefresh();
            }
        });
    }



    private void getNiurenDataList(String pageNo, String pageSize) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.shouCangNiurenList, params, new SpotsCallBack<SavePersonBean>(mContext) {
            @Override
            public void onSuccess(Response response, SavePersonBean resultBean) {
                smartRefreshLayout.finishRefresh();
//                if (resultBean.getDataList() != null) {
//                    totalPage = resultBean.getTotalPage();
//                    if (resultBean.getDataList().size() == 0) {
//                        recyclerView.setVisibility(View.GONE);
//                        noDataLinView.setVisibility(View.VISIBLE);
//                    } else {
//                        if (nowPageIndex == 1) {
//                            allList.clear();
//                        }
//                        recyclerView.setVisibility(View.VISIBLE);
//                        noDataLinView.setVisibility(View.GONE);
//                        allList.addAll(resultBean.getDataList());
//                        myShouCangGangAdapter.notifyDataSetChanged();
//                    }
//                }
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

    @OnClick({R.id.zhiwei_layout, R.id.niuren_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhiwei_layout:
                zhiweiTv.setTextColor(getResources().getColor(R.color.text_color));
                zhiweiLine.setVisibility(View.VISIBLE);
                niurenTv.setTextColor(getResources().getColor(R.color.zhiwei_location_text));
                niurenLine.setVisibility(View.INVISIBLE);
                personOrZhiwei=0;
                niurenRecyclerView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                break;
            case R.id.niuren_layout:
                zhiweiTv.setTextColor(getResources().getColor(R.color.zhiwei_location_text));
                zhiweiLine.setVisibility(View.INVISIBLE);
                niurenTv.setTextColor(getResources().getColor(R.color.text_color));
                niurenLine.setVisibility(View.VISIBLE);
                personOrZhiwei=1;
                recyclerView.setVisibility(View.GONE);
                niurenRecyclerView.setVisibility(View.VISIBLE);

                break;
        }
    }
}
