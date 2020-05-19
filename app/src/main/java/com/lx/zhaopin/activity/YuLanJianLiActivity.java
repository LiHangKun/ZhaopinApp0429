package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.YuLan1Adapter;
import com.lx.zhaopin.adapter.YuLan2Adapter;
import com.lx.zhaopin.adapter.YuLan3Adapter;
import com.lx.zhaopin.adapter.YuLan4Adapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.YuLanBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.view.FlowLiner;
import com.makeramen.roundedimageview.RoundedImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Response;

public class YuLanJianLiActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.roundedImageView)
    RoundedImageView roundedImageView;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6)
    TextView tv6;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.tv8)
    TextView tv8;
    @BindView(R.id.tv9)
    TextView tv9;
    @BindView(R.id.tv10)
    TextView tv10;
    @BindView(R.id.tv11)
    TextView tv11;
    @BindView(R.id.recyclerView1)
    RecyclerView recyclerView1;
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;
    @BindView(R.id.recyclerView3)
    RecyclerView recyclerView3;

    @BindView(R.id.recyclerView4)
    RecyclerView recyclerView4;

    @BindView(R.id.flowLiner)
    FlowLiner flowLiner;

    private static final String TAG = "YuLanJianLiActivity";

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = false)
    public void getEventmessage(MessageEvent event) {
        int messageType = event.getMessageType();
        switch (messageType) {
            case 3:
                Log.i(TAG, "getEventmessage: 修改专业技能");
                getYuLanJianLi();
                break;
        }
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.yulanjianli_activity);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void init() {
        topTitle.setText("预览简历");
        rightIcon.setVisibility(View.VISIBLE);
        rightIcon.setImageResource(R.drawable.gangwei_fenxiang);
        rightIcon.setOnClickListener(this);
        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }
        getYuLanJianLi();

    }

    private void getYuLanJianLi() {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.jianLiPreview, params, new SpotsCallBack<YuLanBean>(mContext) {
            @Override
            public void onSuccess(Response response, YuLanBean resultBean) {

                Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror).error(R.mipmap.imageerror))
                        .load(resultBean.getAvatar()).into(roundedImageView);

                tv1.setText(resultBean.getName());
                String sex = resultBean.getSex();
                //性别，1.男，2.女
                switch (sex) {
                    case "1":
                        tv2.setText("男");
                        break;
                    case "2":
                        tv2.setText("女");
                        break;
                }

                if (TextUtils.isEmpty(resultBean.getMinSalary())) {
                    tv3.setVisibility(View.GONE);
                } else {
                    tv3.setText(resultBean.getMinSalary() + "-" + resultBean.getMaxSalary() + "K");
                }
                tv4.setText(resultBean.getEducation().getName());
                tv5.setText(resultBean.getAge() + "岁");
                tv6.setText(resultBean.getWorkYears() + "年");
                tv7.setText(resultBean.getLatestCityName());

                String jobNature = resultBean.getJobNature();
                //工作性质 1.全职，2.兼职
                switch (jobNature) {
                    case "1":
                        tv8.setText("全职");
                        break;
                    case "2":
                        tv8.setText("兼职");
                        break;
                }

                tv9.setText(resultBean.getLatestCityName());

                String jobStatus = resultBean.getJobStatus();
                //工作状态 1.离职-随时到岗，2.在职-月内到岗 3.在职-考虑机会 4.在职-暂不考虑
                switch (jobStatus) {
                    case "1":
                        tv10.setText("离职-随时到岗");
                        break;
                    case "2":
                        tv10.setText("在职-月内到岗");
                        break;
                    case "3":
                        tv10.setText("在职-考虑机会");
                        break;
                    case "4":
                        tv10.setText("在职-暂不考虑");
                        break;
                }

                String arrivalTime = resultBean.getArrivalTime();
                //到岗时间 1.一周 2.半个月 3.一个月
                switch (arrivalTime) {
                    case "1":
                        tv11.setText("一周");
                        break;
                    case "2":
                        tv11.setText("半个月");
                        break;
                    case "3":
                        tv11.setText("一个月");
                        break;
                }

                //TODO 求职意向 recyclerView1
                List<YuLanBean.ResumeExpectationListBean> resumeExpectationList = resultBean.getResumeExpectationList();
                recyclerView1.setLayoutManager(new LinearLayoutManager(mContext));
                YuLan1Adapter yuLan1Adapter = new YuLan1Adapter(mContext, resumeExpectationList);
                recyclerView1.setAdapter(yuLan1Adapter);
                //TODO 求职意向 recyclerView1


                //TODO 工作经验 recyclerView2
                List<YuLanBean.ExperienceWorkListBean> experienceWorkList = resultBean.getExperienceWorkList();
                recyclerView2.setLayoutManager(new LinearLayoutManager(mContext));
                YuLan2Adapter yuLan2Adapter = new YuLan2Adapter(mContext, experienceWorkList);
                recyclerView2.setAdapter(yuLan2Adapter);
                //TODO 工作经验 recyclerView2

                //TODO 学历信息 recyclerView3
                List<YuLanBean.ExperienceEducationListBean> experienceEducationList = resultBean.getExperienceEducationList();
                recyclerView3.setLayoutManager(new LinearLayoutManager(mContext));
                YuLan3Adapter yuLan3Adapter = new YuLan3Adapter(mContext, experienceEducationList);
                recyclerView3.setAdapter(yuLan3Adapter);
                //TODO 学历信息 recyclerView3

                //TODO 专业技能 recyclerView4
                List<YuLanBean.ResumeSkillListBean> resumeSkillList = resultBean.getResumeSkillList();
                recyclerView4.setLayoutManager(new LinearLayoutManager(mContext));
                YuLan4Adapter yuLan4Adapter = new YuLan4Adapter(mContext, resumeSkillList);
                recyclerView4.setAdapter(yuLan4Adapter);
                yuLan4Adapter.setOnItemClickener(new YuLan4Adapter.OnItemClickener() {
                    @Override
                    public void OnItemClickener(String id, String name) {
                        Intent intent = new Intent(mContext, AddZhuanYeJiNengActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("name", name);
                        startActivity(intent);

                    }
                });
                //TODO 专业技能 recyclerView4


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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rightIcon:
                //分享
                ToastFactory.getToast(mContext, "分享").show();
                break;
        }
    }


}
