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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.YuYueTimeListAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.GnagWeiBean;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.YuYueMianListBean;
import com.lx.zhaopin.bean.ZhiWeiDetailBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.common.ShareUtils;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.rongmessage.RongUtil;
import com.lx.zhaopin.utils.AppUtils;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.utils.ViewUtil;
import com.lx.zhaopin.view.FlowLiner;
import com.lx.zhaopin.view.MyDialog;
import com.makeramen.roundedimageview.RoundedImageView;

import org.greenrobot.eventbus.EventBus;

import java.net.URISyntaxException;
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

    String Cui = "";
    private int maxLine = 5;
    private SpannableString elipseString;//收起的文字
    private SpannableString notElipseString;//展开的文字
    private String lng;
    private String lat;
    private String qiYeID;
    private String pid;
    private String collected;
    private String hrid;
    private String hrName;
    private String delivered;
    private String jianliID;
    private String shareTitle;
    private String positionType;
    private String deliverResume;
    private String chat;
    private String jutiStrAdd;


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



        /*if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }*/
        getZhiWeiDetail(pid);


    }

    //职位详情
    private void getZhiWeiDetail(String pid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pid", pid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.zhiWeiDetail, params, new SpotsCallBack<ZhiWeiDetailBean>(mContext) {
            @Override
            public void onSuccess(Response response, ZhiWeiDetailBean resultBean) {

                shareTitle = resultBean.getCompany().getName() + "" + resultBean.getName();

                jutiStrAdd = resultBean.getCompany().getLocation();
                delivered = resultBean.getDelivered();
                hrid = resultBean.getHRID();
                hrName = resultBean.getHRName();
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


                positionType = resultBean.getPositionType();
                //职位类型  TODO  这个职位类型 是不是 用来判断 上面的文字显示和  下面的布局的显示隐藏

                  /* 底部按钮部分
                   立即沟通 dibuView1
                   立即沟通和申请职位 dibuView2  liJiGouTongTV   shenQingZhiwei
                   申请职位 dibuView3
                   预约面试 dibuView4
                   */
                //是否已沟通 1 是 0 否
                deliverResume = resultBean.getDeliverResume();

                //是否可直接聊天1是 0否
                chat = resultBean.getChat();

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
                        tv6.setText("该岗位无需沟通，可直接发送简历，并到指定位置面试");
                        break;
                    case "3":
                        noGouTongView.setVisibility(View.VISIBLE);
                        dibuView4.setVisibility(View.VISIBLE);
                        tv6.setText("该岗位无需沟通，可直接预约面试，并到指定位置面试");
                        break;
                }


                tv1.setText(resultBean.getName());
                tv2.setText(resultBean.getMinSalary() + "-" + resultBean.getMaxSalary() + "K");
                tv3.setText(resultBean.getCity().getName() + resultBean.getDistrict().getName());
                tv4.setText(resultBean.getExperienceYear().getName());
                tv5.setText(resultBean.getEducation().getName());

                Cui = resultBean.getDuty();
                getLastIndexForLimit(tv7, maxLine, resultBean.getDuty()/*+Cui*/);

                //TODO 专业技能 flowLiner1
                String skills = resultBean.getSkills();

                if (!TextUtils.isEmpty(skills)) {
                    List<String> flowData = new ArrayList<>();
                    String[] split = skills.split(",");

                    for (int i = 0; i < split.length; i++) {
                        flowData.add(split[i]);
                    }
                    flowLiner1.removeAllViews();
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
                } else {
                    flowLiner1.setVisibility(View.GONE);
                }


                //TODO 专业技能 flowLiner1


                //TODO 福利待遇 flowLiner2
                String workfare = resultBean.getWorkfare();

                if (!TextUtils.isEmpty(workfare)) {
                    List<String> flowData2 = new ArrayList<>();
                    String[] workfareL = workfare.split(",");
                    for (int i = 0; i < workfareL.length; i++) {
                        flowData2.add(workfareL[i]);
                    }
                    flowLiner2.removeAllViews();
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
                } else {
                    flowLiner2.setVisibility(View.GONE);
                }


                //TODO 福利待遇 flowLiner2


                Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror).error(R.mipmap.imageerror))
                        .load(resultBean.getCompany().getLogo()).into(roundedImageView);
                tv8.setText(resultBean.getCompany().getName());
                tv9.setText(resultBean.getCompany().getFinancing().getName());
                tv10.setText(resultBean.getCompany().getStaffNum() + "人");
                tv11.setText(resultBean.getCompany().getIndustry().getName());
                tv12.setText(resultBean.getCompany().getLocation());
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


    //RongIM.getInstance().startPrivateChat(getActivity(), "9527", title);
    /*Bundle bundle = new Bundle();
    bundle.putString("Info1", mData.get(i).getUser_Id_ext().getHead_Url());
    bundle.putString("Info2", mData.get(i).getUser_Id_ext().getReal_Name());
    bundle.putString("Info3", mData.get(i).getUser_Id_ext().getType_Code());
    bundle.putString("Info4", mData.get(i).getUser_Id_ext().getUser_Name());
    RongIM.getInstance().startConversation(mContext, Conversation.ConversationType.PRIVATE, hrid + "", "张三的", bundle);*/


    private static final String TAG = "GangWeiDetailActivity";

    @OnClick({R.id.back, R.id.image1, R.id.image2, R.id.image3, R.id.dibuView1, R.id.liJiGouTongTV, R.id.shenQingZhiwei, R.id.dibuView2, R.id.dibuView3, R.id.dibuView4, R.id.daoHang, R.id.llViewGongSi, R.id.tv7})
    public void onClick(View v) {
        switch (v.getId()) {
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
                    if (!TextUtils.isEmpty(shareTitle)) {
                        String shareUrl = NetClass.Share_Gang + pid;
                        Log.i(TAG, "onClick: 分享" + shareUrl);
                        new ShareUtils(GangWeiDetailActivity.this).share(shareUrl, AppUtils.getAppName(GangWeiDetailActivity.this), shareTitle, NetClass.Share_AppLogo);
                    }
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
                //gotoGaode(lat, lng);

                fabuMethod3();
                lightoff();


                break;
            case R.id.dibuView1:
                //立即沟通
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    //ToastFactory.getToast(mContext, "立即沟通长的").show();
                    /*if (delivered.equals("0")) {

                        View view3 = getLayoutInflater().inflate(R.layout.dialog_goutong3, null);
                        final MyDialog mMyDialog = new MyDialog(mContext, 0, 0, view3, R.style.DialogTheme2);
                        mMyDialog.setCancelable(true);
                        mMyDialog.show();

                        view3.findViewById(R.id.guanbi).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMyDialog.dismiss();
                            }
                        });

                        view3.findViewById(R.id.okID).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                touPid(pid);
                                mMyDialog.dismiss();
                            }
                        });
                    } else {
                        goLiaoTianMethod();
                    }*/

                    //是否可直接聊天1是 0否
                    bindUserAndPid(SPTool.getSessionValue(AppSP.UID), hrid, pid);
                    if (chat.equals("1")) {
                        gouTongMe();
                        goLiaoTianMethod();
                    } else {
                        gouTongMe();
                    }


                } else {
                    ToastFactory.getToast(mContext, "请先登录").show();
                    startActivity(new Intent(mContext, Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.liJiGouTongTV:
                //立即沟通
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    //ToastFactory.getToast(mContext, "立即沟通短的").show();

                    goLiaoTianMethod();


                } else {
                    ToastFactory.getToast(mContext, "请先登录").show();
                    startActivity(new Intent(mContext, Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.shenQingZhiwei:
                //申请职位
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    //申请职位  短的

                    View view0 = getLayoutInflater().inflate(R.layout.dialog_goutong0, null);
                    final MyDialog mMyDialog = new MyDialog(mContext, 0, 0, view0, R.style.DialogTheme2);
                    mMyDialog.setCancelable(true);
                    mMyDialog.show();

                    view0.findViewById(R.id.quxiaoTV).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMyDialog.dismiss();
                        }
                    });

                    view0.findViewById(R.id.okID).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            touPid(pid);
                            mMyDialog.dismiss();
                        }
                    });
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

                    //申请职位 长的
                    View view0 = getLayoutInflater().inflate(R.layout.dialog_goutong0, null);
                    final MyDialog mMyDialog = new MyDialog(mContext, 0, 0, view0, R.style.DialogTheme2);
                    mMyDialog.setCancelable(true);
                    mMyDialog.show();

                    view0.findViewById(R.id.quxiaoTV).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMyDialog.dismiss();
                        }
                    });

                    view0.findViewById(R.id.okID).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            touPid(pid);
                            mMyDialog.dismiss();
                        }
                    });

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
                if (v.isSelected()) {
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

    private void gouTongMe() {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pid", pid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.quGouTong, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                ToastFactory.getToast(mContext, "已提交沟通申请").show();
                String chatRecordId = resultBean.getChatRecordId();
                //沟通记录id


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }

    private void touPid(String pid3) {
        //shenQingZhiwei
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pid", pid3);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.shenQingZhiwei, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                getZhiWeiDetail(pid);
                jianliID = resultBean.getId();
                //pid
                RongUtil.sendZhiWeiMessage(hrid, jianliID, SPTool.getSessionValue(AppSP.USER_ICON));
                View view1 = getLayoutInflater().inflate(R.layout.dialog_goutong1, null);
                final MyDialog mMyDialog = new MyDialog(mContext, 0, 0, view1, R.style.DialogTheme2);
                mMyDialog.setCancelable(true);
                mMyDialog.show();

                view1.findViewById(R.id.quxiaoTV).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMyDialog.dismiss();
                    }
                });

                view1.findViewById(R.id.okID).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goLiaoTianMethod();

                        mMyDialog.dismiss();
                    }
                });

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    private void goLiaoTianMethod() {
        String userId = SPTool.getSessionValue(AppSP.UID);
        String nickName = SPTool.getSessionValue(AppSP.USER_NAME);
        String userHead = SPTool.getSessionValue(AppSP.USER_ICON);

        Log.i(TAG, "onClick: " + userId + "<>" + nickName + "<>" + userHead);
        if (null != userId && null != nickName && null != userHead)
            RongIM.getInstance().setCurrentUserInfo(new UserInfo(userId, nickName, Uri.parse(userHead)));
        RongIM.getInstance().setMessageAttachedUserInfo(true);


        //对方的ID 姓名
        //RongIM.getInstance().startPrivateChat(mContext, hrid, "张三");
        RongIM.getInstance().startConversation(mContext, Conversation.ConversationType.PRIVATE, hrid, hrName);

        SPTool.addSessionMap(AppSP.pid, pid);
        SPTool.addSessionMap(AppSP.hrid, hrid);
        //发送自定义消息 岗位详情
        //RongUtil.addBenDiMessage(hrid, pid);

        bindUserAndPid(SPTool.getSessionValue(AppSP.UID), hrid, pid);


    }

    private void bindUserAndPid(String mid, String userId, String pid) {

        Map<String, String> params = new HashMap<>();
        params.put("mid", mid);
        params.put("userId", userId);
        params.put("pid", pid);
        OkHttpHelper.getInstance().post(this, NetClass.BASE_URL + NetCuiMethod.updatePosition, params, new BaseCallback<GnagWeiBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, GnagWeiBean resultBean) {


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
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

            getDataList(popupWindow1, recyclerViewShopLei);

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


    //获取预约面试时间
    private void getDataList(final PopupWindow popupWindow1, final RecyclerView recyclerViewShopLei) {
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
                            YuYueMianShiMe(popupWindow1, id);

                        }

                    }
                });


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    private void YuYueMianShiMe(final PopupWindow popupWindow1, String dateId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pid", pid);
        params.put("dateId", dateId);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.YuYueMianShi, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {
                EventBus.getDefault().post(new MessageEvent(2, null, null, null, null, null, null));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow1.dismiss();
                    }
                });
            }

            @Override
            public void onResponse(Response response) {
                EventBus.getDefault().post(new MessageEvent(2, null, null, null, null, null, null));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow1.dismiss();
                    }
                });
            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                bindUserAndPid(SPTool.getSessionValue(AppSP.UID), hrid, pid);
                EventBus.getDefault().post(new MessageEvent(2, null, null, null, null, null, null));
                ToastFactory.getToast(mContext, resultBean.getResultNote()).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow1.dismiss();
                    }
                });
            }

            @Override
            public void onError(Response response, int code, Exception e) {
                EventBus.getDefault().post(new MessageEvent(2, null, null, null, null, null, null));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow1.dismiss();
                    }
                });
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


    private PopupWindow popupWindow3;
    private View popupView3;
    private TranslateAnimation animation3;


    //TODO---------------------------------------------------------------
    private void fabuMethod3() {
        if (popupWindow3 == null) {
            popupView3 = View.inflate(this, R.layout.pop_layout_dao_layout, null);
            TextView tv1Click = popupView3.findViewById(R.id.tv1);
            TextView tv2Click = popupView3.findViewById(R.id.tv2);
            TextView tv3Click = popupView3.findViewById(R.id.tv3);

            // 参数2,3：指明popupwindow的宽度和高度
            popupWindow3 = new PopupWindow(popupView3, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            popupWindow3.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    lighton();
                }
            });

            //一周
            tv1Click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv4.setText("高德地图");
                    gaoDe(mContext, lat, lng, jutiStrAdd);
                    popupWindow3.dismiss();
                    lighton();
                }
            });
            //半个月
            tv2Click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv4.setText("百度地图");
                    goToBaiduActivity(mContext, jutiStrAdd, lat, lng);
                    popupWindow3.dismiss();
                    lighton();
                }
            });
            //一个月
            tv3Click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv4.setText("腾讯地图");
                    gotoTengxun(mContext, jutiStrAdd, lat, lng);
                    popupWindow3.dismiss();
                    lighton();
                }
            });


            // 设置背景图片， 必须设置，不然动画没作用
            popupWindow3.setBackgroundDrawable(new BitmapDrawable());
            popupWindow3.setFocusable(true);

            // 设置点击popupwindow外屏幕其它地方消失
            popupWindow3.setOutsideTouchable(true);

            // 平移动画相对于手机屏幕的底部开始，X轴不变，Y轴从1变0
            animation3 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);

            animation3.setInterpolator(new AccelerateInterpolator());
            animation3.setDuration(200);

            popupView3.findViewById(R.id.tvCancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow3.dismiss();
                    lighton();
                }
            });
            popupView3.findViewById(R.id.cancelImage).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow3.dismiss();
                    lighton();
                }
            });
        }

        // 在点击之后设置popupwindow的销毁
        if (popupWindow3.isShowing()) {
            popupWindow3.dismiss();
            lighton();
        }

        // 设置popupWindow的显示位置，此处是在手机屏幕底部且水平居中的位置
        popupWindow3.showAtLocation(findViewById(R.id.setting), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        //popupWindow1.showAtLocation(findViewById(R.id.setting), Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupView3.startAnimation(animation3);

    }


    //腾讯地图
    private static void gotoTengxun(Context context, String address, String latStr, String lonStr) {
        if (isAvilible(context, "com.tencent.map")) {
            double lat=0 ,lon=0;
            if (!TextUtils.isEmpty(latStr)){
                lat=Double.parseDouble(latStr);
            }
            if (!TextUtils.isEmpty(lonStr)){
                lon=Double.parseDouble(lonStr);
            }
            // 启动路径规划页面
            Intent naviIntent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("qqmap://map/routeplan?type=drive&from=&fromcoord=&to="+ address + "&tocoord=" + lat + "," + lon + "&policy=0&referer=appName"));
            context.startActivity(naviIntent);
        }else {
            ToastFactory.getToast(context,"您尚未安装腾讯地图").show();
            Uri uri = Uri.parse("market://details?id=com.tencent.map");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }
    }

    //百度地图
    private static void goToBaiduActivity(Context context, String address, String latStr, String lonStr) {
        if (isAvilible(context, "com.baidu.BaiduMap")) {
            double lat = 0, lon = 0;
            if (!TextUtils.isEmpty(latStr)) {
                lat = Double.parseDouble(latStr);
            }
            if (!TextUtils.isEmpty(lonStr)) {
                lon = Double.parseDouble(lonStr);
            }

            //启动路径规划页面
            String url = "baidumap://map/direction?origin=我的位置&destination=" + address + "&mode=driving&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end";
            Intent naviIntent = new Intent("android.intent.action.VIEW", android.net.Uri.parse(url));
            context.startActivity(naviIntent);
        } else {
            ToastFactory.getToast(context, "您尚未安装百度地图").show();
            Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }
    }


    //高德地图
    private static void gaoDe(Context mContext, String lat, String lng, String adr) {
        if (isAvilible(mContext, "com.autonavi.minimap")) {
            try {
                String url = "amapuri://route/plan/?sid=BGVIS1&slat=&slon=&sname=&did=&dlat=" + lat + "&dlon=" + lng + "&dname=" + adr + "&dev=0&t=0";
                Intent intent = new Intent("android.intent.action.VIEW", android.net.Uri.parse(url));
                mContext.startActivity(intent);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(mContext, "您尚未安装高德地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            mContext.startActivity(intent);
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
