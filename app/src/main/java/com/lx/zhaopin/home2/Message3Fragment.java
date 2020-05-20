package com.lx.zhaopin.home2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.QiuZhiFeedActivity;
import com.lx.zhaopin.activity.XiaoXiDetailActivity;
import com.lx.zhaopin.adapter.Message3FragmentAdapter;
import com.lx.zhaopin.bean.QiuZhiZheMyInfoBean;
import com.lx.zhaopin.bean.SystemMessageListBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.GouTongCaoZuoBean;
import com.lx.zhaopin.common.NoticeDetailActivity;
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

public class Message3Fragment extends Fragment {

    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayout noDataLinView;
    private int nowPageIndex = 1;
    private int totalPage = 1;

    private static final String TAG = "Message3Fragment";
    private List<SystemMessageListBean.DataListBean> allList;
    private Message3FragmentAdapter message3FragmentAdapter;
    private int positionSelect;
    private Intent intent;

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = false)
    public void getEventmessage(ShuaXinBean event) {
        GouTongCaoZuoBean gouTongCaoZuoBean = event.getGouTongCaoZuoBean();
        allList.get(positionSelect).setUnread(gouTongCaoZuoBean.getUnread());
        message3FragmentAdapter.notifyDataSetChanged();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(container.getContext(), R.layout.message3fragment_layout, null);
        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        noDataLinView = view.findViewById(R.id.noDataLinView);

        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        allList = new ArrayList<>();
        message3FragmentAdapter = new Message3FragmentAdapter(getActivity(), allList);
        recyclerView.setAdapter(message3FragmentAdapter);
        message3FragmentAdapter.setOnItemClickListener(new Message3FragmentAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position, String Correlation, String messageType, String url, String title, String messID) {
                switch (view.getId()) {
                    case R.id.llView:
                        // 1 系统消息 2 收到Offer 3 求职反馈 4 面试邀请 5 面试取消 6 面试超时  7 举报结果

                        switch (messageType) {
                            case "1":
                                positionSelect = position;
                                getReadMess(messID);
                                ToastFactory.getToast(getActivity(), "系统消息").show();
                                intent = new Intent(getActivity(), NoticeDetailActivity.class);
                                intent.putExtra("title", title);
                                intent.putExtra("titleUrl", url);
                                startActivity(intent);
                                break;
                            case "2":
                                positionSelect = position;
                                getReadMess(messID);
                                ToastFactory.getToast(getActivity(), "收到Offer").show();
                                intent = new Intent(getActivity(), QiuZhiFeedActivity.class);
                                intent.putExtra("offerID", Correlation);
                                intent.putExtra("userType", "0");
                                startActivity(intent);
                                break;
                            case "3":
                                positionSelect = position;
                                getReadMess(messID);
                                ToastFactory.getToast(getActivity(), "求职反馈").show();
                                intent = new Intent(getActivity(), XiaoXiDetailActivity.class);
                                intent.putExtra("messageId", Correlation);
                                startActivity(intent);
                                break;
                            case "4":
                                positionSelect = position;
                                getReadMess(messID);
                                ToastFactory.getToast(getActivity(), "面试邀请").show();
                                //interviewId
                                intent = new Intent(getActivity(), XiaoXiDetailActivity.class);
                                intent.putExtra("interviewId", Correlation);
                                startActivity(intent);
                                break;
                            case "5":
                                positionSelect = position;
                                getReadMess(messID);
                                ToastFactory.getToast(getActivity(), "面试取消").show();
                                intent = new Intent(getActivity(), XiaoXiDetailActivity.class);
                                intent.putExtra("interviewId", Correlation);
                                startActivity(intent);
                                break;
                            case "6":
                                positionSelect = position;
                                getReadMess(messID);
                                ToastFactory.getToast(getActivity(), "面试超时").show();
                                intent = new Intent(getActivity(), XiaoXiDetailActivity.class);
                                intent.putExtra("interviewId", Correlation);
                                startActivity(intent);
                                break;
                            case "7":
                                positionSelect = position;
                                getReadMess(messID);
                                ToastFactory.getToast(getActivity(), "举报结果").show();
                                break;
                        }
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

       /* if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))){
            getDataList(String.valueOf(nowPageIndex), AppSP.pageCount);
        }*/



        return view;

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))){
                getDataList(String.valueOf(nowPageIndex), AppSP.pageCount);
            }
        }
    }


    //查看系统消息 lookSysMessage
    private void getReadMess(String messageId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("messageId", messageId);
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.lookSysMessage, params, new BaseCallback<QiuZhiZheMyInfoBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, QiuZhiZheMyInfoBean resultBean) {
                GouTongCaoZuoBean gouTongCaoZuoBean = new GouTongCaoZuoBean();
                gouTongCaoZuoBean.setChatApplyStatus("1");
                EventBus.getDefault().post(new ShuaXinBean(gouTongCaoZuoBean));

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
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.sysMessageList, params, new SpotsCallBack<SystemMessageListBean>(getActivity()) {
            @Override
            public void onSuccess(Response response, SystemMessageListBean resultBean) {
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
                        message3FragmentAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
                smartRefreshLayout.finishRefresh();
            }
        });
    }
}
