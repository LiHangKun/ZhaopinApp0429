package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.SelectQiWang3Adapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.SelectQiWang3Bean;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

//选择职位类型
public class SelectQiWangType1_3Activity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<SelectQiWang3Bean.DataListBean> allList;
    private SelectQiWang3Adapter selectQiWang3Adapter;
    private static final String TAG = "SelectQiWangType1_2Acti";

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.hangyeleixing_activity);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void init() {
        topTitle.setText("选择职位类型");
        recyclerView = findViewById(R.id.recyclerView);

        /*if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }*/
        final String parentid2 = getIntent().getStringExtra("parentid2");
        getDataList(parentid2);
        Log.i(TAG, "OnItemClickListener: 输出的信息接受到" + "name" + "----------" + parentid2);

        allList = new ArrayList<>();
        selectQiWang3Adapter = new SelectQiWang3Adapter(mContext, allList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(selectQiWang3Adapter);

        selectQiWang3Adapter.SetOnItemClickListener(new SelectQiWang3Adapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int i, String id, String name) {
                EventBus.getDefault().post(new MessageEvent(6, id, name, null, null, null, null));
                finish();

            }
        });


    }

    private void getDataList(String parentid) {
        Map<String, String> params = new HashMap<>();
        params.put("parentid", parentid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.seleQiWangType1, params, new BaseCallback<SelectQiWang3Bean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, SelectQiWang3Bean resultBean) {
                if (resultBean.getDataList() != null) {
                    if (resultBean.getDataList().size() != 0) {
                        allList.addAll(resultBean.getDataList());
                    }
                    selectQiWang3Adapter.notifyDataSetChanged();
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
}
