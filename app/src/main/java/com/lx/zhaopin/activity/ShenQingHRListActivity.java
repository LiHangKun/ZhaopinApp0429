package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.ShenQingHRListAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.HRShenQingBean;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.GouTongCaoZuoBean;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.common.ShuaXinBean;
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
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

//HR的新申请列表
public class ShenQingHRListActivity extends BaseActivity {

    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayout noDataLinView;
    private int nowPageIndex = 1;
    private int totalPage = 1;
    private static final String TAG = "ShenQingListActivity";
    private List<HRShenQingBean.DataListBean> allList;
    private ShenQingHRListAdapter shenQingHRListAdapter;
    private Intent intent;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.shenqinglist_activity);
        init();
    }

    private void init() {
        //shenQingJiLu


        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }


        topTitle.setText("申请记录");
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
        noDataLinView = findViewById(R.id.noDataLinView);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        allList = new ArrayList<>();
        shenQingHRListAdapter = new ShenQingHRListAdapter(mContext, allList);
        recyclerView.setAdapter(shenQingHRListAdapter);
        shenQingHRListAdapter.SetOnItemClickListener(new ShenQingHRListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int i, final String requestId, String requestStatus, String open, final String rid, final String name, final String icon) {
                switch (view.getId()) {
                    case R.id.llView:
                        if (!open.equals("1")) {
                            ToastFactory.getToast(mContext, "该简历已关闭").show();
                            return;
                        } else {
                            // 人才详情
                            intent = new Intent(mContext, RenCaiDetailActivity.class);
                            intent.putExtra("rid", rid);
                            startActivity(intent);
                        }
                        break;
                    case R.id.tv7:
                        //拒绝 3
                        position = i;
                        StyledDialog.init(mContext);
                        StyledDialog.buildIosAlert("", "\r是否拒绝申请?", new MyDialogListener() {
                            @Override
                            public void onFirst() {
                            }

                            @Override
                            public void onSecond() {
                                chuliGouTongShenQingMe(requestId, "3", rid, name, icon);

                            }
                        }).setBtnColor(R.color.mainColor2, R.color.mainColor1, 0).setBtnText("取消", "确定").show();

                        break;
                    case R.id.tv8:
                        position = i;
                        //同意 2
                        StyledDialog.init(mContext);
                        StyledDialog.buildIosAlert("", "\r是否同意申请?", new MyDialogListener() {
                            @Override
                            public void onFirst() {
                            }

                            @Override
                            public void onSecond() {
                                chuliGouTongShenQingMe(requestId, "2", rid, name, icon);

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

    private int position;

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = false)
    public void getEventmessage(ShuaXinBean event) {
        GouTongCaoZuoBean gouTongCaoZuoBean = event.getGouTongCaoZuoBean();
        allList.get(position).setRequestStatus(gouTongCaoZuoBean.getRequestStatus());
        shenQingHRListAdapter.notifyDataSetChanged();
    }

    //HR 操作同意给求职者发送自定义消息
    //同意 2
    private void chuliGouTongShenQingMe(String applyId, final String status, final String rid, final String name, final String icon) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("applyId", applyId);
        params.put("status", status);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.HR_ShenQingCaoView, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                GouTongCaoZuoBean gouTongCaoZuoBean = new GouTongCaoZuoBean();
                gouTongCaoZuoBean.setRequestStatus(status);
                EventBus.getDefault().post(new ShuaXinBean(gouTongCaoZuoBean));
                EventBus.getDefault().post(new MessageEvent(13, null, null, null, null, null, null));
                getDataList(String.valueOf(nowPageIndex), AppSP.pageCount);

                if (status.equals("2")) {
                    //HR 同意了
                    //RongUtil.addBenDiMessage(rid, name, icon);
                }


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
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.HR_ShenQingChatList, params, new SpotsCallBack<HRShenQingBean>(mContext) {
            @Override
            public void onSuccess(Response response, HRShenQingBean resultBean) {
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
                        shenQingHRListAdapter.notifyDataSetChanged();
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
