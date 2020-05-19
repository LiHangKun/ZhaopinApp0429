package com.lx.zhaopin.hr;

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
import com.lx.zhaopin.bean.HRMessage2Bean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.hradapter.HRMessage2Adapter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

public class HRMessage2Fragment extends Fragment {
    private int nowPageIndex = 1;
    private int totalPage = 1;
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayout noDataLinView;
    private View view;
    private static final String TAG = "HRMessage2Fragment";
    private List<HRMessage2Bean.DataListBean> allList;
    private HRMessage2Adapter hrMessage2Adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //HRMessageList1

        view = View.inflate(container.getContext(), R.layout.message2fragment_layout, null);
        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        noDataLinView = view.findViewById(R.id.noDataLinView);


        allList = new ArrayList<>();
        hrMessage2Adapter = new HRMessage2Adapter(getActivity(), allList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(hrMessage2Adapter);

        hrMessage2Adapter.setOnItemClickListener(new HRMessage2Adapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(String id) {
                ToastFactory.getToast(getActivity(), "面试管看到的消息详情" + id).show();
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


    private void getDataList(String pageNo, String pageSize) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.HRMessageList1, params, new SpotsCallBack<HRMessage2Bean>(getActivity()) {
            @Override
            public void onSuccess(Response response, HRMessage2Bean resultBean) {
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
                        hrMessage2Adapter.notifyDataSetChanged();
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
