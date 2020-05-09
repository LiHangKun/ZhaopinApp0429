package com.lx.zhaopin.activity;

import android.os.Bundle;
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
import com.lx.zhaopin.utils.SPTool;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;
import okhttp3.Response;

public class SelectCityPro1ListActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private static final String TAG = "SelectCityPro1ListActiv";

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.selectcitylist_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        topTitle.setText("选择地址");

        getDataList();
    }

    //获取城市列表
    private void getDataList() {
        Map<String, String> params = new HashMap<>();
        params.put("mid", "ca38784005db46c80a5aa2704c9571e54279a16ef03bf24044e6da0fc235fb91");
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
                    public void itemClick(String name, String id, String sJing, String sWei) {
                        String cityName = name;
                        String cityID = id;
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
