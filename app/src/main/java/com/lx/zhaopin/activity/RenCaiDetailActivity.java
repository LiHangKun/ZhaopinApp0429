package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.RenCaiDetail1Adapter;
import com.lx.zhaopin.adapter.RenCaiDetail2Adapter;
import com.lx.zhaopin.adapter.RenCaiDetail3Adapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.RenCaiDetailBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.utils.ViewUtil;
import com.lx.zhaopin.view.FlowLiner;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import okhttp3.Response;

//人才详情 renCaiDetail
public class RenCaiDetailActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.image3)
    ImageView image3;
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
    @BindView(R.id.tvButton1)
    TextView tvButton1;
    @BindView(R.id.tvButton2)
    TextView tvButton2;
    @BindView(R.id.qiuZhiView)
    LinearLayout qiuZhiView;

    @BindView(R.id.flowLiner)
    FlowLiner flowLiner;

    private List<String> flowData = new ArrayList<>();

    private String rid;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.rencaidetail_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        rid = getIntent().getStringExtra("rid");
        getRenDetail(rid);

    }

    //人才详情
    private void getRenDetail(String rid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("rid", rid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.renCaiDetail, params, new SpotsCallBack<RenCaiDetailBean>(mContext) {
            @Override
            public void onSuccess(Response response, RenCaiDetailBean resultBean) {
                Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                        .error(R.mipmap.imageerror)).load(resultBean.getAvatar()).into(roundedImageView);


                String collected = resultBean.getCollected();
                switch (collected) {
                    case "1":
                        image1.setImageResource(R.drawable.gangwei_shoucang2);
                        break;
                    case "0":
                        image1.setImageResource(R.drawable.gangwei_shoucang1);
                        break;
                }


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
                tv3.setText(resultBean.getMinSalary() + "K - " + resultBean.getMaxSalary() + "K");
                tv4.setText(resultBean.getEducation().getName());
                tv5.setText(resultBean.getBirthday() + "岁");
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
                tv9.setText(resultBean.getLatestCityName());//这个地址
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
                        tv10.setText("一周");
                        break;
                    case "2":
                        tv10.setText("半个月");
                        break;
                    case "3":
                        tv10.setText("一个月");
                        break;
                }

                //求职意向
                List<RenCaiDetailBean.ResumeExpectationListBean> resumeExpectationList = resultBean.getResumeExpectationList();
                RenCaiDetail1Adapter renCaiDetail1Adapter = new RenCaiDetail1Adapter(mContext, resumeExpectationList);
                recyclerView1.setLayoutManager(new LinearLayoutManager(mContext));
                recyclerView1.setAdapter(renCaiDetail1Adapter);
                //求职意向

                //工作经验
                List<RenCaiDetailBean.ExperienceWorkListBean> experienceWorkList = resultBean.getExperienceWorkList();
                RenCaiDetail2Adapter renCaiDetail2Adapter = new RenCaiDetail2Adapter(mContext, experienceWorkList);
                recyclerView2.setLayoutManager(new LinearLayoutManager(mContext));
                recyclerView2.setAdapter(renCaiDetail2Adapter);
                //工作经验

                //学历信息
                List<RenCaiDetailBean.ExperienceEducationListBean> experienceEducationList = resultBean.getExperienceEducationList();
                RenCaiDetail3Adapter renCaiDetail3Adapter = new RenCaiDetail3Adapter(mContext, experienceEducationList);
                recyclerView3.setLayoutManager(new LinearLayoutManager(mContext));
                recyclerView3.setAdapter(renCaiDetail3Adapter);
                //学历信息

                //专业技能
                List<RenCaiDetailBean.ResumeSkillListBean> resumeSkillList = resultBean.getResumeSkillList();
                for (int i = 0; i < resumeSkillList.size(); i++) {
                    flowData.add(resumeSkillList.get(i).getName());
                }
                for (int i = 0; i < flowData.size(); i++) {
                    final TextView radioButton = new TextView(mContext);
                    FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT, FlowLiner.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(0, 0, ViewUtil.dp2px(mContext, 10), ViewUtil.dp2px(mContext, 10));
                    radioButton.setLayoutParams(layoutParams);
                    final String str = flowData.get(i);
                    radioButton.setText(str);
                    radioButton.setGravity(Gravity.CENTER);
                    radioButton.setTextSize(13);
                    radioButton.setPadding(ViewUtil.dp2px(mContext, 18), ViewUtil.dp2px(mContext, 6), ViewUtil.dp2px(mContext, 18), ViewUtil.dp2px(mContext, 6));
                    radioButton.setTextColor(mContext.getResources().getColorStateList(R.color.radio_text_selector_primary_4d4d4d));
                    //radioButton.setBackgroundResource(R.drawable.search_selector);
                    radioButton.setBackgroundResource(R.drawable.button_shape03);
                    radioButton.setFocusable(true);
                    radioButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    flowLiner.addView(radioButton);
                }
                //专业技能


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


    @OnClick({R.id.back, R.id.image1, R.id.image2, R.id.image3, R.id.tvButton1, R.id.tvButton2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.image1:
                //收藏
                quXiaoShouCangRen(rid);
                break;
            case R.id.image2:
                //分享
                ToastFactory.getToast(mContext, "分享简历" + rid).show();
                break;
            case R.id.image3:
                //举报,屏蔽
                StyledDialog.init(mContext);
                StyledDialog.buildIosAlert("", "\r是否屏蔽该简历?", new MyDialogListener() {
                    @Override
                    public void onFirst() {

                    }

                    @Override
                    public void onSecond() {
                        //HR_PingBi_renCaiDetail
                        HR_PingBi_renCaiDetailMet(rid);


                    }
                }).setBtnColor(R.color.mainColor2, R.color.mainColor1, 0).setBtnText("取消", "确定").show();

                break;
            case R.id.tvButton1:
                //立即沟通
                ToastFactory.getToast(mContext, "立即沟通" + rid).show();
                break;
            case R.id.tvButton2:
                //职位邀约
                Intent intent = new Intent(mContext, ZhiWeiYaoYueActivity.class);
                intent.putExtra("rid", rid);
                RenCaiDetailBean renCaiDetailBean = new RenCaiDetailBean();
                intent.putExtra("renCaiDetailBean", renCaiDetailBean);
                startActivity(intent);
                break;
        }
    }

    private void HR_PingBi_renCaiDetailMet(final String rid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("rid", rid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.HR_PingBi_renCaiDetail, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                getRenDetail(rid);

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


    private void quXiaoShouCangRen(final String rid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("rid", rid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.ShouCangRenCai, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                getRenDetail(rid);

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

}
