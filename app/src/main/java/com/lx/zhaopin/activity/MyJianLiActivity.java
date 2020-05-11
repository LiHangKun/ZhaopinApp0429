package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.MyJianLi1Adapter;
import com.lx.zhaopin.adapter.MyJianLi2Adapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.MyJianLiBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ViewUtil;
import com.lx.zhaopin.view.FlowLiner;
import com.makeramen.roundedimageview.RoundedImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

public class MyJianLiActivity extends BaseActivity implements View.OnClickListener {

    private RoundedImageView roundedImageView;
    private TextView tv1, tv2, tv3, tv4, tv5, tv6;
    private FlowLiner flowLiner;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private Intent intent;
    private static final String TAG = "MyJianLiActivity";

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.myjianli_activity);
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
            case 2:
            case 3:
                //更新我的简历
                myJianliMe(SPTool.getSessionValue(AppSP.UID));
                Log.i(TAG, "getEventmessage: 更新我的简历");
                break;
        }
    }


    private void init() {
        topTitle.setText("我的简历");
        TextView okID = findViewById(R.id.okID);
        okID.setOnClickListener(this);

        roundedImageView = findViewById(R.id.roundedImageView);
        LinearLayout llView0 = findViewById(R.id.llView0);


        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }

        llView0.setOnClickListener(this);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        flowLiner = findViewById(R.id.flowLiner);
        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView2 = findViewById(R.id.recyclerView2);

        LinearLayout addView1 = findViewById(R.id.addView1);
        LinearLayout addView2 = findViewById(R.id.addView2);
        LinearLayout addView3 = findViewById(R.id.addView3);
        addView1.setOnClickListener(this);
        addView2.setOnClickListener(this);
        addView3.setOnClickListener(this);

        myJianliMe(SPTool.getSessionValue(AppSP.UID));


    }

    private void myJianliMe(String mid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", mid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.myJianLi, params, new SpotsCallBack<MyJianLiBean>(mContext) {
            @Override
            public void onSuccess(Response response, MyJianLiBean resultBean) {

                Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                        .error(R.mipmap.imageerror)).load(resultBean.getAvatar()).into(roundedImageView);
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

                tv3.setText(resultBean.getEducation().getName());
                tv4.setText(resultBean.getBirthday());
                tv5.setText(resultBean.getWorkYears() + "年经验");
                tv6.setText(resultBean.getLatestCityName());

                //TODO 教育经历
                List<MyJianLiBean.ExperienceEducationListBean> experienceEducationList = resultBean.getExperienceEducationList();
                MyJianLi1Adapter myJianLi1Adapter = new MyJianLi1Adapter(mContext, experienceEducationList);
                recyclerView1.setLayoutManager(new LinearLayoutManager(mContext));
                recyclerView1.setAdapter(myJianLi1Adapter);
                //TODO 教育经历


                //TODO 工作经验
                List<MyJianLiBean.ExperienceWorkListBean> experienceWorkList = resultBean.getExperienceWorkList();
                MyJianLi2Adapter myJianLi2Adapter = new MyJianLi2Adapter(mContext, experienceWorkList);
                recyclerView2.setLayoutManager(new LinearLayoutManager(mContext));
                recyclerView2.setAdapter(myJianLi2Adapter);
                //TODO 工作经验

                //TODO 专业技能
                List<MyJianLiBean.ResumeSkillListBean> resumeSkillList = resultBean.getResumeSkillList();
                List<String> resumeSkillListString = new ArrayList<>();
                for (int i = 0; i < resumeSkillList.size(); i++) {
                    resumeSkillListString.add(resumeSkillList.get(i).getName());
                }

                for (int i = 0; i < resumeSkillListString.size(); i++) {
                    final TextView radioButton = new TextView(mContext);
                    FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT,
                            FlowLiner.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(0, 0, ViewUtil.dp2px(mContext, 10),
                            ViewUtil.dp2px(mContext, 10));
                    radioButton.setLayoutParams(layoutParams);
                    final String str = resumeSkillListString.get(i);
                    radioButton.setText(str);
                    radioButton.setGravity(Gravity.CENTER);
                    radioButton.setTextSize(13);
                    radioButton.setPadding(ViewUtil.dp2px(mContext, 18),
                            ViewUtil.dp2px(mContext, 6)
                            , ViewUtil.dp2px(mContext, 18),
                            ViewUtil.dp2px(mContext, 6));
                    radioButton.setTextColor(getResources().getColorStateList(R.color.radio_text_selector_primary_4d4d4d));
                    //radioButton.setBackgroundResource(R.drawable.search_selector);
                    radioButton.setBackgroundResource(R.drawable.button_shape03);
                    radioButton.setFocusable(true);
                    radioButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            /*TODO ACTION*/
                            // doSearch(str);
                        }
                    });
                    flowLiner.addView(radioButton);
                }
                //TODO 专业技能


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
            case R.id.okID:
                //预览简历
                startActivity(new Intent(mContext, YuLanJianLiActivity.class));
                break;
            case R.id.llView0:
                //我的信息
                startActivity(new Intent(mContext, MyUserInfoActivity.class));
                break;
            case R.id.addView1:
                //添加学历
                intent = new Intent(mContext, MyJiaoYuActivity.class);
                startActivity(intent);
                break;
            case R.id.addView2:
                //专业技能
                intent = new Intent(mContext, AddZhuanYeJiNengActivity.class);
                startActivity(intent);
                break;
            case R.id.addView3:
                //工作经验
                intent = new Intent(mContext, MyGongZuoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
