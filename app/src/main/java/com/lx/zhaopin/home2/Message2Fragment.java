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
import com.lx.zhaopin.activity.XiaoXiDetailActivity;
import com.lx.zhaopin.adapter.QiuZhiMessage2Adapter;
import com.lx.zhaopin.bean.QiuZhiFeedBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.event.EvenDyname;
import com.lx.zhaopin.event.VideoActionBean;
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
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

//这个是求职反馈---点击进入求职反馈的详情进度列表页面
public class Message2Fragment extends Fragment {

    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayout noDataLinView;
    private static final String TAG = "Message2Fragment";
    private int nowPageIndex = 1;
    private int totalPage = 1;
    private List<QiuZhiFeedBean.DataListBean> allList;
    private QiuZhiMessage2Adapter qiuZhiMessage2Adapter;
    private int ii;


    @Subscribe(threadMode = ThreadMode.POSTING, sticky = false)
    public void getEventmessage(EvenDyname event) {
        VideoActionBean dynameitenbean = event.getVideoActionBean();
        allList.get(ii).setUnreadCount(dynameitenbean.getUnreadCount());
        qiuZhiMessage2Adapter.notifyDataSetChanged();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }

        View view = View.inflate(container.getContext(), R.layout.message2fragment_layout, null);
        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        noDataLinView = view.findViewById(R.id.noDataLinView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        allList = new ArrayList<>();
        qiuZhiMessage2Adapter = new QiuZhiMessage2Adapter(getActivity(), allList);
        recyclerView.setAdapter(qiuZhiMessage2Adapter);

        qiuZhiMessage2Adapter.setOnItemClickListener(new QiuZhiMessage2Adapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position, String id) {
                ii = position;
                VideoActionBean videoActionBean = new VideoActionBean();
                videoActionBean.setUnreadCount("0");
                EventBus.getDefault().post(new EvenDyname(videoActionBean));

                Intent intent = new Intent(getActivity(), XiaoXiDetailActivity.class);
                intent.putExtra("messageId", id);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(final int i, final String id) {
                //删除
               /* StyledDialog.init(getActivity());
                StyledDialog.buildIosAlert("", "\r是否删除反馈信息?", new MyDialogListener() {
                    @Override
                    public void onFirst() {

                    }

                    @Override
                    public void onSecond() {
                        //删除收藏 --
                        allList.remove(i);
                        qiuZhiMessage2Adapter.notifyDataSetChanged();


                    }
                }).setBtnColor(R.color.mainColor2, R.color.mainColor1, 0).setBtnText("取消", "确定").show();*/
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


        return view;

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                getDataList(String.valueOf(nowPageIndex), AppSP.pageCount);
            }
        }
    }

    private void getDataList(String pageNo, String pageSize) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.qiuZhiFeedList, params, new SpotsCallBack<QiuZhiFeedBean>(getActivity()) {
            @Override
            public void onSuccess(Response response, QiuZhiFeedBean resultBean) {
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
                        qiuZhiMessage2Adapter.notifyDataSetChanged();
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
