package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.MianShiListAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.MianShiListBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Response;

public class YiLuQuActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;
    private RecyclerView recyclerView;
    private LinearLayout noDataLinView;
    private static final String TAG = "YiLuQuActivity";
    private MianShiListAdapter mianShiListAdapter;
    private List<MianShiListBean.DataListBean> allList;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.yiluqu_activity);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.POSTING, sticky = false)
    public void getEventmessage(MessageEvent event) {
        int messageType = event.getMessageType();
        switch (messageType) {
            case 5:
                //更新个人中心
                getDataList("7");
                Log.i(TAG, "getEventmessage: 更新Offer的状态了");
                break;
        }
    }

    private void init() {
        baseTop.setVisibility(View.GONE);
        topTitle.setText("已录取");
        titleTv.setText("已录取");

        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }

        recyclerView = findViewById(R.id.recyclerView);
        noDataLinView = findViewById(R.id.noDataLinView);

        getDataList("7");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        allList = new ArrayList<>();
        mianShiListAdapter = new MianShiListAdapter(mContext, allList);
        recyclerView.setAdapter(mianShiListAdapter);


    }

    private void getDataList(String interviewStatus) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("interviewStatus", interviewStatus);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.luquList, params, new SpotsCallBack<MianShiListBean>(mContext) {
            @Override
            public void onSuccess(Response response, MianShiListBean resultBean) {
                if (resultBean.getDataList() != null) {
                    if (resultBean.getDataList().size() == 0) {
                        recyclerView.setVisibility(View.GONE);
                        noDataLinView.setVisibility(View.VISIBLE);
                    } else {

                        recyclerView.setVisibility(View.VISIBLE);
                        noDataLinView.setVisibility(View.GONE);
                        allList.addAll(resultBean.getDataList());
                        mianShiListAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {

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

    @OnClick({R.id.left_layout, R.id.title_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_layout:
                onBackPressed();
                break;
            case R.id.title_tv:
                break;
        }
    }
}
