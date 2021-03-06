package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.SelectCityAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.CityBean;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;

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
        topTitle.setText("选择地址省");

        /*if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }*/

        getDataList();
    }

    //获取省份
    private void getDataList() {
        Map<String, String> params = new HashMap<>();
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.memberProvinceCity, params, new BaseCallback<CityBean>() {
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
                    public void itemClick(String name, String id) {
                        String cityName = name;
                        String cityID = id;
                        Log.i(TAG, "itemClick: 用户选择的名字" + cityName + "---" + cityID );
                        Intent intent = new Intent(mContext, SelectCityCity2ListActivity.class);
                        intent.putExtra("provinceId", id);
                        startActivity(intent);
                        finish();

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
