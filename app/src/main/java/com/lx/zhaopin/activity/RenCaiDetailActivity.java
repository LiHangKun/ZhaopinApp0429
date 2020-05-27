package com.lx.zhaopin.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.RenCaiDetail1Adapter;
import com.lx.zhaopin.adapter.RenCaiDetail2Adapter;
import com.lx.zhaopin.adapter.RenCaiDetail3Adapter;
import com.lx.zhaopin.adapter.ZhiWeiYaoYueInAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.GongSiZhiWeiBean;
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
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
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
    private String renIcon;
    private String renName;
    private String renID;
    private String renOpenChat;
    private String yaoYueGangWeiId;

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


                renIcon = resultBean.getAvatar();
                renName = resultBean.getName();
                renID = resultBean.getId();
                renOpenChat = resultBean.getOpenChat();

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

                if (!TextUtils.isEmpty(resultBean.getMinSalary())) {
                    tv3.setText(resultBean.getMinSalary() + "K - " + resultBean.getMaxSalary() + "K");
                } else {
                    tv3.setVisibility(View.INVISIBLE);
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
                    radioButton.setPadding(ViewUtil.dp2px(mContext, 10), ViewUtil.dp2px(mContext, 6), ViewUtil.dp2px(mContext, 10), ViewUtil.dp2px(mContext, 6));
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

    private static final String TAG = "RenCaiDetailActivity";

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
                //ToastFactory.getToast(mContext, "立即沟通" + rid).show();

                //renOpenChat
                switch (renOpenChat) {
                    case "0":
                        //不可以聊天
                        AllGangwei();
                        lightoff();
                        break;
                    case "1":
                        //可以聊天
                        SPTool.addSessionMap("rid", rid);

                        String userId = SPTool.getSessionValue(AppSP.UID);
                        String nickName = SPTool.getSessionValue(AppSP.USER_NAME);
                        String userHead = SPTool.getSessionValue(AppSP.USER_ICON);
                        SPTool.addSessionMap(AppSP.chatWhere, "1");
                        Log.i(TAG, "onClick: " + userId + "<>" + nickName + "<>" + userHead);
                        if (null != userId && null != nickName && null != userHead)
                            RongIM.getInstance().setCurrentUserInfo(new UserInfo(userId, nickName, Uri.parse(userHead)));
                        RongIM.getInstance().setMessageAttachedUserInfo(true);
                        //对方的ID 姓名
                        RongIM.getInstance().startConversation(mContext, Conversation.ConversationType.PRIVATE, renID, renName);
                        break;
                }

                break;
            case R.id.tvButton2:
                //职位邀约
                //ToastFactory.getToast(mContext,"职位邀约").show();
                SPTool.addSessionMap("rid", rid);
                Intent intent = new Intent(mContext, ZhiWeiYaoYueActivity.class);
                //intent.putExtra("rid", rid);
                RenCaiDetailBean renCaiDetailBean = new RenCaiDetailBean();
                intent.putExtra("renCaiDetailBean", renCaiDetailBean);
                startActivity(intent);
                break;
        }
    }

    private PopupWindow popupWindow1;
    private View popupView1;
    private TranslateAnimation animation1;
    /*在子日记的pop上 点击发布 在次弹出发布页面*/

    /**
     * 设置手机屏幕亮度变暗
     */
    private void lightoff() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().setAttributes(lp);
    }

    /**
     * 设置手机屏幕亮度显示正常
     */
    private void lighton() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1f;
        getWindow().setAttributes(lp);
    }

    private void AllGangwei() {
        if (popupWindow1 == null) {
            popupView1 = View.inflate(this, R.layout.pop_layout_allgangwei_list, null);
            RecyclerView recyclerView = popupView1.findViewById(R.id.recyclerView);
            popupWindow1 = new PopupWindow(popupView1, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    lighton();
                }
            });


            getGongSiAllZhiWei(recyclerView);


            // 设置背景图片， 必须设置，不然动画没作用
            popupWindow1.setBackgroundDrawable(new BitmapDrawable());
            popupWindow1.setFocusable(true);
            // 设置点击popupwindow外屏幕其它地方消失
            popupWindow1.setOutsideTouchable(true);
            // 平移动画相对于手机屏幕的底部开始，X轴不变，Y轴从1变0
            animation1 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
            animation1.setInterpolator(new AccelerateInterpolator());
            animation1.setDuration(200);
            popupView1.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow1.dismiss();
                    lighton();
                }
            });
        }

        // 在点击之后设置popupwindow的销毁
        if (popupWindow1.isShowing()) {
            popupWindow1.dismiss();
            lighton();
        }

        // 设置popupWindow的显示位置，此处是在手机屏幕底部且水平居中的位置
        popupWindow1.showAtLocation(findViewById(R.id.setting), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        //popupWindow1.showAtLocation(findViewById(R.id.setting), Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupView1.startAnimation(animation1);

    }

    private void getGongSiAllZhiWei(final RecyclerView recyclerView) {

        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.gongSiAllZhiWei, params, new BaseCallback<GongSiZhiWeiBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, GongSiZhiWeiBean resultBean) {


                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                ZhiWeiYaoYueInAdapter zhiWeiYaoYueInAdapter = new ZhiWeiYaoYueInAdapter(mContext, resultBean.getDataList());
                recyclerView.setAdapter(zhiWeiYaoYueInAdapter);
                zhiWeiYaoYueInAdapter.setOnItemClickListener(new ZhiWeiYaoYueInAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClickListener(String id, String name) {
                        //tv1.setText(name);
                        yaoYueGangWeiId = id;

                        HRShenQingChat(rid, yaoYueGangWeiId);


                        popupWindow1.dismiss();
                    }
                });

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });


    }

    private void HRShenQingChat(String rid, String pid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("rid", rid);
        params.put("pid", pid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.HR_ShenQingChat, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {

                ToastFactory.getToast(mContext, resultBean.getResultNote()).show();

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

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
