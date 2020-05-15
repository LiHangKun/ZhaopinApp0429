package com.lx.zhaopin.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.YuYueTimeListAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.YuYueMianListBean;
import com.lx.zhaopin.bean.ZhiWeiDetailBean;
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

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import okhttp3.Response;

public class GangWeiDetailActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.image3)
    ImageView image3;
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
    @BindView(R.id.flowLiner1)
    FlowLiner flowLiner1;
    @BindView(R.id.flowLiner2)
    FlowLiner flowLiner2;
    @BindView(R.id.roundedImageView)
    RoundedImageView roundedImageView;
    @BindView(R.id.tv8)
    TextView tv8;
    @BindView(R.id.tv9)
    TextView tv9;
    @BindView(R.id.tv10)
    TextView tv10;
    @BindView(R.id.tv11)
    TextView tv11;
    @BindView(R.id.tv12)
    TextView tv12;
    @BindView(R.id.daoHang)
    LinearLayout daoHang;
    @BindView(R.id.dibuView1)
    TextView dibuView1;
    @BindView(R.id.liJiGouTongTV)
    TextView liJiGouTongTV;
    @BindView(R.id.shenQingZhiwei)
    TextView shenQingZhiwei;
    @BindView(R.id.dibuView2)
    LinearLayout dibuView2;
    @BindView(R.id.dibuView3)
    TextView dibuView3;
    @BindView(R.id.dibuView4)
    TextView dibuView4;
    @BindView(R.id.llViewGongSi)
    LinearLayout llViewGongSi;

    @BindView(R.id.noGouTongView)
    LinearLayout noGouTongView;


    private Intent intent;

    String Cui = "1.独立Cover产品需求对接及解决方案制定，并高效输出相对应付物2.系统化完成需求挖掘、流程梳理（业务流、操作流、信息流）及相关产品规则制定1.独立Cover产品需求对接及解决方案制定，并高效输出相对应付物2.系统化完成需求挖掘、流程梳理（业务流、操作流、信息流）及相关产品规则制定1.独立Cover产品需求对接及解决方案制定，并高效输出相对应付物2.系统化完成需求挖掘、流程梳理（业务流、操作流、信息流）及相关产品规则制定";
    private int maxLine = 5;
    private SpannableString elipseString;//收起的文字
    private SpannableString notElipseString;//展开的文字
    private String lng;
    private String lat;
    private String qiYeID;
    private String pid;
    private String collected;


    private void getLastIndexForLimit(TextView tv, int maxLine, String content) {
        //获取TextView的画笔对象
        TextPaint paint = tv.getPaint();
        //每行文本的布局宽度
        int width = getResources().getDisplayMetrics().widthPixels - dip2px(this, 40);
        //实例化StaticLayout 传入相应参数
        StaticLayout staticLayout = new StaticLayout(content, paint, width, Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
        //判断content是行数是否超过最大限制行数3行
        if (staticLayout.getLineCount() > maxLine) {
            //定义展开后的文本内容
            String string1 = content + "    收起";
            notElipseString = new SpannableString(string1);
            //给收起两个字设成蓝色
            notElipseString.setSpan(new ForegroundColorSpan(Color.parseColor("#0079e2")), string1.length() - 2, string1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            //获取到第三行最后一个文字的下标
            int index = staticLayout.getLineStart(maxLine) - 1;
            //定义收起后的文本内容
            String substring = content.substring(0, index - 4) + "..." + "查看全部";
            elipseString = new SpannableString(substring);
            //给查看全部设成蓝色
            elipseString.setSpan(new ForegroundColorSpan(Color.parseColor("#0079e2")), substring.length() - 4, substring.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //设置收起后的文本内容
            tv.setText(elipseString);
            tv.setOnClickListener(this);
            //将textview设成选中状态 true用来表示文本未展示完全的状态,false表示完全展示状态，用于点击时的判断
            tv.setSelected(true);
        } else {
            //没有超过 直接设置文本
            tv.setText(content);
            tv.setOnClickListener(null);
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context mContext, float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    //llViewGongSi
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.gangweidetail_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        pid = getIntent().getStringExtra("pid");

        getZhiWeiDetail(pid);


        getLastIndexForLimit(tv7, maxLine, Cui);
    }

    //职位详情
    private void getZhiWeiDetail(String pid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pid", pid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.zhiWeiDetail, params, new SpotsCallBack<ZhiWeiDetailBean>(mContext) {
            @Override
            public void onSuccess(Response response, ZhiWeiDetailBean resultBean) {

                //是否收藏  image1
                collected = resultBean.getCollected();
                //1表示是，0表示否
                switch (collected) {
                    case "1":
                        image1.setImageResource(R.drawable.gangwei_shoucang2);
                        break;
                    case "0":
                        image1.setImageResource(R.drawable.gangwei_shoucang1);
                        break;
                }


                String positionType = resultBean.getPositionType();
                //职位类型  TODO  这个职位类型 是不是 用来判断 上面的文字显示和  下面的布局的显示隐藏

                  /* 底部按钮部分
                   立即沟通 dibuView1
                   立即沟通和申请职位 dibuView2  liJiGouTongTV   shenQingZhiwei
                   申请职位 dibuView3
                   预约面试 dibuView4
                   */
                String deliverResume = resultBean.getDeliverResume(); //是否已沟通 1 是 0 否

                switch (positionType) {
                    //职位类型1.需沟通，2.无需沟通，3.直接面试
                    case "1":
                        noGouTongView.setVisibility(View.GONE);

                        switch (deliverResume) {
                            case "1":
                                dibuView2.setVisibility(View.VISIBLE);
                                break;
                            case "0":
                                dibuView1.setVisibility(View.VISIBLE);
                                break;
                        }
                        break;
                    case "2":
                        noGouTongView.setVisibility(View.VISIBLE);
                        dibuView3.setVisibility(View.VISIBLE);
                        break;
                    case "3":
                        noGouTongView.setVisibility(View.VISIBLE);
                        dibuView4.setVisibility(View.VISIBLE);
                        break;
                }


                tv1.setText(resultBean.getName());
                tv2.setText(resultBean.getMinSalary() + "--" + resultBean.getMaxSalary() + "K");
                tv3.setText(resultBean.getLocation());
                tv4.setText(resultBean.getExperienceYear() + "年");
                tv5.setText(resultBean.getEducation().getName());
                tv7.setText(resultBean.getDuty());

                //TODO 专业技能 flowLiner1
                String skills = resultBean.getSkills();
                List<String> flowData = new ArrayList<>();
                if (skills.contains(",")) {
                    String[] split = skills.split(",");

                    for (int i = 0; i < split.length; i++) {
                        flowData.add(split[i]);
                    }

                    for (int i = 0; i < flowData.size(); i++) {
                        final TextView radioButton = new TextView(GangWeiDetailActivity.this);
                        FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT, FlowLiner.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(0, 0, ViewUtil.dp2px(GangWeiDetailActivity.this, 10), ViewUtil.dp2px(GangWeiDetailActivity.this, 10));
                        radioButton.setLayoutParams(layoutParams);
                        final String str = flowData.get(i);
                        radioButton.setText(str);
                        radioButton.setGravity(Gravity.CENTER);
                        radioButton.setTextSize(13);
                        radioButton.setPadding(ViewUtil.dp2px(GangWeiDetailActivity.this, 18), ViewUtil.dp2px(GangWeiDetailActivity.this, 6)
                                , ViewUtil.dp2px(GangWeiDetailActivity.this, 18), ViewUtil.dp2px(GangWeiDetailActivity.this, 6));
                        radioButton.setTextColor(getResources().getColorStateList(R.color.radio_text_selector_primary_4d4d4d));
                        //radioButton.setBackgroundResource(R.drawable.search_selector);
                        radioButton.setBackgroundResource(R.drawable.button_shape03);
                        radioButton.setFocusable(true);
                        radioButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //流布局文字的点击
                            }
                        });
                        flowLiner1.addView(radioButton);
                    }
                }
                //TODO 专业技能 flowLiner1


                //TODO 福利待遇 flowLiner2
                String workfare = resultBean.getWorkfare();
                List<String> flowData2 = new ArrayList<>();
                if (workfare.contains(",")) {
                    String[] workfareL = workfare.split(",");
                    for (int i = 0; i < workfareL.length; i++) {
                        flowData2.add(workfareL[i]);
                    }
                    for (int i = 0; i < flowData2.size(); i++) {
                        final TextView radioButton = new TextView(GangWeiDetailActivity.this);
                        FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT, FlowLiner.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(0, 0, ViewUtil.dp2px(GangWeiDetailActivity.this, 10), ViewUtil.dp2px(GangWeiDetailActivity.this, 10));
                        radioButton.setLayoutParams(layoutParams);
                        final String str = flowData2.get(i);
                        radioButton.setText(str);
                        radioButton.setGravity(Gravity.CENTER);
                        radioButton.setTextSize(13);
                        radioButton.setPadding(ViewUtil.dp2px(GangWeiDetailActivity.this, 18), ViewUtil.dp2px(GangWeiDetailActivity.this, 6)
                                , ViewUtil.dp2px(GangWeiDetailActivity.this, 18), ViewUtil.dp2px(GangWeiDetailActivity.this, 6));
                        radioButton.setTextColor(getResources().getColorStateList(R.color.radio_text_selector_primary_4d4d4d));
                        //radioButton.setBackgroundResource(R.drawable.search_selector);
                        radioButton.setBackgroundResource(R.drawable.button_shape03);
                        radioButton.setFocusable(true);
                        radioButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //流布局文字的点击
                            }
                        });
                        flowLiner2.addView(radioButton);
                    }
                }
                //TODO 福利待遇 flowLiner2


                Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror).error(R.mipmap.imageerror))
                        .load(resultBean.getCompany().getLogo()).into(roundedImageView);
                tv8.setText(resultBean.getCompany().getName());
                tv9.setText(resultBean.getCompany().getFinancing().getName());
                tv10.setText(resultBean.getCompany().getStaffNum() + "人");
                tv11.setText(resultBean.getCompany().getIndustry().getName());
                tv12.setText(resultBean.getCity().getName() + "" + resultBean.getCompany().getDistrict().getName());
                qiYeID = resultBean.getCompany().getId();
                lat = resultBean.getCompany().getLat();
                lng = resultBean.getCompany().getLng();


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


    /*
    底部按钮部分
    立即沟通 dibuView1
    立即沟通和申请职位 dibuView2  liJiGouTongTV   shenQingZhiwei
    申请职位 dibuView3
    预约面试 dibuView4
    */

    @OnClick({R.id.back, R.id.image1, R.id.image2, R.id.image3, R.id.dibuView1, R.id.liJiGouTongTV, R.id.shenQingZhiwei, R.id.dibuView2, R.id.dibuView3, R.id.dibuView4, R.id.daoHang, R.id.llViewGongSi, R.id.tv7})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.image1:
                //收藏
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    shouCangZhiWei(pid, collected);
                } else {
                    ToastFactory.getToast(mContext, "请先登录").show();
                    startActivity(new Intent(mContext, Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.image2:
                //分享
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    ToastFactory.getToast(mContext, "分享").show();
                } else {
                    ToastFactory.getToast(mContext, "请先登录").show();
                    startActivity(new Intent(mContext, Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.image3:
                //举报
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    intent = new Intent(mContext, JuBaoActivity.class);
                    intent.putExtra("pid", pid);
                    startActivity(intent);
                } else {
                    ToastFactory.getToast(mContext, "请先登录").show();
                    startActivity(new Intent(mContext, Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.daoHang:
                gotoGaode(lat, lng);
                break;
            case R.id.dibuView1:
                //立即沟通
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    ToastFactory.getToast(mContext, "立即沟通长的").show();
                } else {
                    ToastFactory.getToast(mContext, "请先登录").show();
                    startActivity(new Intent(mContext, Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.liJiGouTongTV:
                //立即沟通
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    ToastFactory.getToast(mContext, "立即沟通短的").show();
                } else {
                    ToastFactory.getToast(mContext, "请先登录").show();
                    startActivity(new Intent(mContext, Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.shenQingZhiwei:
                //申请职位
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    ToastFactory.getToast(mContext, "申请职位").show();
                } else {
                    ToastFactory.getToast(mContext, "请先登录").show();
                    startActivity(new Intent(mContext, Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.dibuView2:
                //不要
                break;
            case R.id.dibuView3:
                //申请职位
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    ToastFactory.getToast(mContext, "申请职位哈哈").show();
                } else {
                    ToastFactory.getToast(mContext, "请先登录").show();
                    startActivity(new Intent(mContext, Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.dibuView4:
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    fabuMethod();
                    lightoff();
                } else {
                    ToastFactory.getToast(mContext, "请先登录").show();
                    startActivity(new Intent(mContext, Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.llViewGongSi:
                //公司
                if (!TextUtils.isEmpty(qiYeID)) {
                    intent = new Intent(mContext, QiYeInfoActivity.class);
                    intent.putExtra("qiYeID", qiYeID);
                    startActivity(intent);
                }
                break;
            case R.id.tv7:
                //岗位详情的文本,查看全部
                if (view.isSelected()) {
                    //如果是收起的状态
                    tv7.setText(Cui);
                    tv7.setSelected(false);
                } else {
                    //如果是展开的状态
                    tv7.setText(Cui);
                    tv7.setSelected(true);
                }
                break;
        }
    }


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

    private PopupWindow popupWindow1;
    private View popupView1;
    private TranslateAnimation animation1;


    //TODO---------------------------------------------------------------
    private void fabuMethod() {
        if (popupWindow1 == null) {
            popupView1 = View.inflate(this, R.layout.pop_layout_shoplei, null);
            RecyclerView recyclerViewShopLei = popupView1.findViewById(R.id.recyclerViewShopLei);

            // 参数2,3：指明popupwindow的宽度和高度
            popupWindow1 = new PopupWindow(popupView1, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    lighton();
                }
            });

            getDataList(recyclerViewShopLei);

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


    //活动分类
    private void getDataList(final RecyclerView recyclerViewShopLei) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pid", pid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.getYuyueTimeList, params, new BaseCallback<YuYueMianListBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, YuYueMianListBean resultBean) {
                recyclerViewShopLei.setLayoutManager(new LinearLayoutManager(mContext));
                YuYueTimeListAdapter yuYueTimeListAdapter = new YuYueTimeListAdapter(mContext, resultBean.getDataList());
                recyclerViewShopLei.setAdapter(yuYueTimeListAdapter);

                yuYueTimeListAdapter.setOnItemClickListener(new YuYueTimeListAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClickListener(int i, String time, String id, String yuNumber) {

                        if (yuNumber.equals("0")) {
                            ToastFactory.getToast(mContext, "该时间段预约已满").show();
                            return;
                        } else {
                            //预约面试 YuYueMianShi
                            YuYueMianShiMe(id);

                        }

                    }
                });


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    private void YuYueMianShiMe(String dateId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pid", pid);
        params.put("dateId", dateId);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.YuYueMianShi, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


    //职位收藏/取消收藏 zhiWeiShouCang  1表示是，0表示否
    private void shouCangZhiWei(final String pid, final String collected) {

        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pid", pid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.zhiWeiShouCang, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                getZhiWeiDetail(pid);

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });


    }


    /*打开高德导航*/
    private void gotoGaode(String lat, String lon) {
        if (isAvilible(mContext, "com.autonavi.minimap")) {
            try {
                //116.304521,40.003865
                Intent intent = Intent.getIntent("androidamap://navi?sourceApplication=慧医&poiname=我的目的地&lat=" + lat + "&lon=" + lon + "&dev=0");
                startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(mContext, "您尚未安装高德地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

    }


    /* 检查手机上是否安装了指定的软件
     * @param context
     * @param packageName：应用包名
     * @return
     */
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }
}
