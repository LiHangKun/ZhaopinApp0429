package com.lx.zhaopin.hr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.MianShiDetailType1Activity;
import com.lx.zhaopin.activity.QiuZhiFeedActivity;
import com.lx.zhaopin.activity.XiaoXiDetailActivity;
import com.lx.zhaopin.bean.QiuZhiZheMyInfoBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.GouTongCaoZuoBean;
import com.lx.zhaopin.common.NoticeDetailActivity;
import com.lx.zhaopin.common.ShuaXinBean;
import com.lx.zhaopin.hradapter.HRMessage3Adapter;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

public class HRMessage3Fragment extends Fragment {

    private int nowPageIndex = 1;
    private int totalPage = 1;
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayout noDataLinView;

    private static final String TAG = "HRMessage3Fragment";
    private List<HRSystemBean.DataListBean> allList;
    private HRMessage3Adapter hrMessage3Adapter;
    private int positionSelect;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = View.inflate(container.getContext(), R.layout.hrmessage3fragment_layout, null);

        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        noDataLinView = view.findViewById(R.id.noDataLinView);

        allList = new ArrayList<>();
        hrMessage3Adapter = new HRMessage3Adapter(getActivity(), allList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(hrMessage3Adapter);

        hrMessage3Adapter.setOnItemClickListener(new HRMessage3Adapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position, String Correlation, String messageType, String url, String title, String messID) {
                //聊天状态 MessageType  1 系统消息 2 收到Offer 3 求职反馈 4 面试邀请
                //5 HR取消面试 6 面试超时 7 举报结果 8 求职者点击已到达 9 求职者同意沟通 10 求职者取消面试
                switch (view.getId()) {
                    case R.id.llView:
                        switch (messageType) {
                            case "1":
                                //1 系统消息
                                positionSelect = position;
                                getReadMess(messID);
                                intent = new Intent(getActivity(), NoticeDetailActivity.class);
                                intent.putExtra("title", title);
                                intent.putExtra("titleUrl", url);
                                startActivity(intent);
                                break;
                            case "2":
                                //2 收到Offer
                                positionSelect = position;
                                getReadMess(messID);
                                intent = new Intent(getActivity(), QiuZhiFeedActivity.class);
                                intent.putExtra("offerID", Correlation);
                                intent.putExtra("userType", "0");
                                startActivity(intent);
                                break;
                            case "3":
                                //3 求职反馈
                                positionSelect = position;
                                getReadMess(messID);
                                intent = new Intent(getActivity(), XiaoXiDetailActivity.class);
                                intent.putExtra("messageId", Correlation);
                                startActivity(intent);
                                break;
                            case "4":
                                //4 面试邀请
                                positionSelect = position;
                                getReadMess(messID);
                                intent = new Intent(getActivity(), MianShiDetailType1Activity.class);
                                intent.putExtra("interviewId", Correlation);
                                startActivity(intent);
                                break;
                            case "5":
                                //5 HR取消面试
                                positionSelect = position;
                                getReadMess(messID);
                                intent = new Intent(getActivity(), MianShiDetailType1Activity.class);
                                intent.putExtra("interviewId", Correlation);
                                startActivity(intent);
                                break;
                            case "6":
                                //6 面试超时
                                positionSelect = position;
                                getReadMess(messID);
                                intent = new Intent(getActivity(), MianShiDetailType1Activity.class);
                                intent.putExtra("interviewId", Correlation);
                                startActivity(intent);
                                break;
                            case "7":
                                //7 举报结果
                                positionSelect = position;
                                getReadMess(messID);
                                break;
                            case "8":
                                //8 求职者点击已到达
                                positionSelect = position;
                                getReadMess(messID);
                                intent = new Intent(getActivity(), MianShiDetailType1Activity.class);
                                intent.putExtra("interviewId", Correlation);
                                startActivity(intent);
                                break;
                            case "9":
                                //9 求职者同意沟通
                                positionSelect = position;
                                getReadMess(messID);
                                break;
                            case "10":
                                //10 求职者取消面试
                                positionSelect = position;
                                getReadMess(messID);
                                intent = new Intent(getActivity(), MianShiDetailType1Activity.class);
                                intent.putExtra("interviewId", Correlation);
                                startActivity(intent);
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


        getDataList(String.valueOf(nowPageIndex), AppSP.pageCount);
        return view;

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
                gouTongCaoZuoBean.setUnread("0");
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
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.HRMessageList2, params, new SpotsCallBack<HRSystemBean>(getActivity()) {
            @Override
            public void onSuccess(Response response, HRSystemBean resultBean) {
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
                        hrMessage3Adapter.notifyDataSetChanged();
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
