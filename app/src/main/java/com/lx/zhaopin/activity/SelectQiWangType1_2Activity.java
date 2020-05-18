package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.SelectQiWang2Adapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.SelectQiWang2Bean;
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
public class SelectQiWangType1_2Activity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<SelectQiWang2Bean.DataListBean> allList;
    private SelectQiWang2Adapter selectQiWang2Adapter;
    private static final String TAG = "SelectQiWangType1_1Acti";


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
        final String parentid = getIntent().getStringExtra("parentid");
        getDataList(parentid);

        allList = new ArrayList<>();
        selectQiWang2Adapter = new SelectQiWang2Adapter(mContext, allList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(selectQiWang2Adapter);

        selectQiWang2Adapter.SetOnItemClickListener(new SelectQiWang2Adapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int i, String parentid, String name) {

                Intent intent = new Intent(mContext, SelectQiWangType1_3Activity.class);
                intent.putExtra("parentid2", parentid);
                Log.i(TAG, "OnItemClickListener: 输出的信息" + name + "----------" + parentid);
                startActivity(intent);

                finish();

            }
        });


    }

    private void getDataList(String parentid) {
        Map<String, String> params = new HashMap<>();
        params.put("parentid", parentid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.seleQiWangType1, params, new BaseCallback<SelectQiWang2Bean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, SelectQiWang2Bean resultBean) {
                if (resultBean.getDataList() != null) {
                    if (resultBean.getDataList().size() != 0) {
                        allList.addAll(resultBean.getDataList());
                    }
                    selectQiWang2Adapter.notifyDataSetChanged();
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
