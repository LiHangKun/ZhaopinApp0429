package com.lx.zhaopin.home2;

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

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.QiYeInfoActivity;
import com.lx.zhaopin.adapter.ShenQingListAdapter;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.ShenQingListBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.GouTongCaoZuoBean;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.common.ShuaXinBean;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.rongmessage.RongUtil;
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

public class NewShenqingFragment extends Fragment implements View.OnClickListener {

    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayout noDataLinView;
    private int nowPageIndex = 1;
    private int totalPage = 1;
    private static final String TAG = "ShenQingListActivity";
    private List<ShenQingListBean.DataListBean> allList;
    private ShenQingListAdapter shenQingListAdapter;
    private Intent intent;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.shenqinglist_activity, null);
        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }
        init(view);
        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void init(View rootView) {
        //shenQingJiLu


        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }
        smartRefreshLayout = rootView.findViewById(R.id.smartRefreshLayout);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        noDataLinView = rootView.findViewById(R.id.noDataLinView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        allList = new ArrayList<>();
        shenQingListAdapter = new ShenQingListAdapter(getActivity(), allList);
        recyclerView.setAdapter(shenQingListAdapter);
        shenQingListAdapter.SetOnItemClickListener(new ShenQingListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int i, String open, final String id, String qiYeID, final String hrid, final String name, final String icon) {
                switch (view.getId()) {
                    case R.id.AllllView:
                        if (open.equals("0")) {
                            ToastFactory.getToast(getActivity(), "职位已关闭").show();
                            return;
                        } else {
                            // qiYeID QiYeInfoActivity
                            //跳转到企业
                            intent = new Intent(getActivity(), QiYeInfoActivity.class);
                            intent.putExtra("qiYeID", qiYeID);
                            startActivity(intent);
                        }
                        break;
                    case R.id.caoZuoViewTv1:
                        //chuliGouTongShenQing
                        //拒绝3
                        position = i;
                        StyledDialog.init(getActivity());
                        StyledDialog.buildIosAlert("", "\r是否拒绝申请?", new MyDialogListener() {
                            @Override
                            public void onFirst() {
                            }

                            @Override
                            public void onSecond() {
                                chuliGouTongShenQingMe(id, "3", hrid, name, icon);

                            }
                        }).setBtnColor(R.color.mainColor2, R.color.mainColor1, 0).setBtnText("取消", "确定").show();

                        break;
                    case R.id.caoZuoViewTv2:
                        //同意
                        position = i;
                        //chuliGouTongShenQing
                        //同意 2
                        StyledDialog.init(getActivity());
                        StyledDialog.buildIosAlert("", "\r是否同意申请?", new MyDialogListener() {
                            @Override
                            public void onFirst() {
                            }

                            @Override
                            public void onSecond() {
                                chuliGouTongShenQingMe(id, "2", "hr" + hrid, name, icon);

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
        allList.get(position).setApplyDate(gouTongCaoZuoBean.getChatApplyStatus());
        shenQingListAdapter.notifyDataSetChanged();
    }

    //同意 2
    private void chuliGouTongShenQingMe(String applyId, final String status, final String hrid, final String name, final String icon) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("applyId", applyId);
        params.put("status", status);
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.chuliGouTongShenQing, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                GouTongCaoZuoBean gouTongCaoZuoBean = new GouTongCaoZuoBean();
                gouTongCaoZuoBean.setChatApplyStatus(status);
                EventBus.getDefault().post(new ShuaXinBean(gouTongCaoZuoBean));
                EventBus.getDefault().post(new MessageEvent(13, null, null, null, null, null, null));
                getDataList(String.valueOf(nowPageIndex), AppSP.pageCount);

                if (status.equals("2")) {
                    //求职者同意沟通,向对方发送消息
                    RongUtil.addBenDiMessage(hrid, name, icon);
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
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.shenQingJiLu, params, new SpotsCallBack<ShenQingListBean>(getActivity()) {
            @Override
            public void onSuccess(Response response, ShenQingListBean resultBean) {
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
                        shenQingListAdapter.notifyDataSetChanged();
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
    public void onClick(View view) {

    }
}
