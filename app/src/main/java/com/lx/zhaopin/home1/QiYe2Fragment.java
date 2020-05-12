package com.lx.zhaopin.home1;

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
import com.lx.zhaopin.activity.GangWeiDetailActivity;
import com.lx.zhaopin.adapter.ZaiZhaoGangAdapter;
import com.lx.zhaopin.bean.GongSiZaiZhaoBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

public class QiYe2Fragment extends Fragment {

    private static final String TAG = "QiYe1Fragment";

    private int nowPageIndex = 1;
    private static String shopJiaID;
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayout noDataLinView;
    private int totalPage;
    private List<GongSiZaiZhaoBean.DataListBean> allList;
    private ZaiZhaoGangAdapter zaiZhaoGangAdapter;
    private Intent intent;

    public static Fragment newInstance(String id) {
        QiYe2Fragment fragment = new QiYe2Fragment();
        shopJiaID = id;
        Log.i(TAG, "newInstance: 企业ID" + shopJiaID);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.qiye2fragment_layout, null);

        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        noDataLinView = view.findViewById(R.id.noDataLinView);


        allList = new ArrayList<>();
        zaiZhaoGangAdapter = new ZaiZhaoGangAdapter(getActivity(), allList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(zaiZhaoGangAdapter);
        getDataList(String.valueOf(nowPageIndex), AppSP.pageCount, shopJiaID);
        zaiZhaoGangAdapter.setOnItemClickener(getPid());

        return view;

    }

    @NotNull
    private ZaiZhaoGangAdapter.OnItemClickener getPid() {
        return new ZaiZhaoGangAdapter.OnItemClickener() {
            @Override
            public void onItemClick(String pid) {
                intent = new Intent(getActivity(), GangWeiDetailActivity.class);
                intent.putExtra("pid", pid);
                startActivity(intent);

            }
        };
    }

    private void getDataList(String pageNo, String pageSize, String cid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("cid", cid);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.wenTiPage, params, new SpotsCallBack<GongSiZaiZhaoBean>(getActivity()) {
            @Override
            public void onSuccess(Response response, GongSiZaiZhaoBean resultBean) {
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
                        zaiZhaoGangAdapter.notifyDataSetChanged();
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
