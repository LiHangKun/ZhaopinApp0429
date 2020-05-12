package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.QiuZhiYiXiangAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.QiuZhiyiXiangBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Response;

public class QiuZhiYiXiangActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.llView1)
    LinearLayout llView1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.llView2)
    LinearLayout llView2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.llView3)
    LinearLayout llView3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.llView4)
    LinearLayout llView4;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.qiuzhiyixiang_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        topTitle.setText("求职意向");
        getDataList();


    }

    private void getDataList() {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.qiuZhiYiXiang, params, new SpotsCallBack<QiuZhiyiXiangBean>(mContext) {
            @Override
            public void onSuccess(Response response, QiuZhiyiXiangBean resultBean) {

                List<QiuZhiyiXiangBean.ResumeExpectationListBean> allList = new ArrayList<>();
                QiuZhiYiXiangAdapter qiuZhiYiXiangAdapter = new QiuZhiYiXiangAdapter(mContext, allList);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                recyclerView.setAdapter(qiuZhiYiXiangAdapter);
                qiuZhiYiXiangAdapter.setOnItemClickener(new QiuZhiYiXiangAdapter.OnItemClickener() {
                    @Override
                    public void onItemClick(String id) {
                        Intent intent = new Intent(mContext, QiuZhiQiWangActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                });


                String jobNature = resultBean.getJobNature();
                //工作性质 1.全职，2.兼职
                switch (jobNature) {
                    case "1":
                        tv1.setText("全职");
                        break;
                    case "2":
                        tv1.setText("兼职");
                        break;
                }

                String jobStatus = resultBean.getJobStatus();
                //工作状态 1.离职-随时到岗，2.在职-月内到岗 3.在职-考虑机会 4.在职-暂不考虑
                switch (jobStatus) {
                    case "1":
                        tv3.setText("离职-随时到岗");
                        break;
                    case "2":
                        tv3.setText("在职-月内到岗");
                        break;
                    case "3":
                        tv3.setText("在职-考虑机会");
                        break;
                    case "4":
                        tv3.setText("在职-暂不考虑");
                        break;
                }


                String arrivalTime = resultBean.getArrivalTime();
                //到岗时间 1.一周 2.半个月 3.一个月
                switch (arrivalTime) {
                    case "1":
                        tv4.setText("一周");
                        break;
                    case "2":
                        tv4.setText("半个月");
                        break;
                    case "3":
                        tv4.setText("一个月");
                        break;
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


    @OnClick({R.id.llView1, R.id.llView2, R.id.llView3, R.id.llView4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llView1:
                //工作性质
                break;
            case R.id.llView2:
                //我的状态
                break;
            case R.id.llView4:
                //到岗时间
                break;
        }
    }
}
