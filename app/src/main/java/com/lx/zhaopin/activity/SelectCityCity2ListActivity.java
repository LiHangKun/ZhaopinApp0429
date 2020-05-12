package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.SelectCityAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.CityBean;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

public class SelectCityCity2ListActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private static final String TAG = "SelectCityCity2ListActi";

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.selectcitylist_activity);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void init() {
        topTitle.setText("选择地址");
        String provinceId = getIntent().getStringExtra("provinceId");
        recyclerView = findViewById(R.id.recyclerView);
        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }
        getCityDataList(provinceId);


    }

    private void getCityDataList(String provinceId) {

        Map<String, String> params = new HashMap<>();
        params.put("provinceId", provinceId);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.memberCity, params, new BaseCallback<CityBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, CityBean resultBean) {

                SelectCityAdapter cityListAdapter = new SelectCityAdapter(mContext, resultBean.getDataList());
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                recyclerView.setAdapter(cityListAdapter);

                cityListAdapter.setOnItemClickener(new SelectCityAdapter.onItemClickener() {


                    @Override
                    public void itemClick(String name, String cityId, String sJing, String sWei) {
                        EventBus.getDefault().post(new MessageEvent(4, name, cityId, null, null, null, null));


                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 500);


                        String cityName = name;
                        String cityID = cityId;
                        Log.i(TAG, "itemClick: 用户选择的名字" + cityName + "---" + cityID + "----" + sJing + "------" + sWei);


                    }
                });
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
