package com.lx.zhaopin.common;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.DaiMianShiListActivity;
import com.lx.zhaopin.activity.Login1PhoneCodeActivity;
import com.lx.zhaopin.activity.MianShiListActivity;
import com.lx.zhaopin.activity.MyGuanZhuGangActivity;
import com.lx.zhaopin.activity.MyJianLiActivity;
import com.lx.zhaopin.activity.MyShouCangGangActivity;
import com.lx.zhaopin.activity.MyUserInfoActivity;
import com.lx.zhaopin.activity.MyYinSiActivity;
import com.lx.zhaopin.activity.PingBiGangActivity;
import com.lx.zhaopin.activity.QiuZhiYiXiangActivity;
import com.lx.zhaopin.activity.SelectUserTypeActivity;
import com.lx.zhaopin.activity.SettingActivity;
import com.lx.zhaopin.activity.YiLuQuActivity;
import com.lx.zhaopin.base.BaseFragment;
import com.lx.zhaopin.bean.QiuZhiZheMyInfoBean;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.StringUtil;
import com.lx.zhaopin.utils.TellUtil;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.view.CirclePercentView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionGrant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

//求职者界面
public class Home3Fragment extends BaseFragment implements View.OnClickListener {

    private CirclePercentView circlePercentView;
    private TextView tvJinDu;
    public static final int ANIMATOR_DURATION = 1000;
    public static final int cuInt = 0;
    private LinearLayout llView0;
    private LinearLayout llView1;
    private LinearLayout llView2;
    private LinearLayout llView3;
    private LinearLayout llView4;
    private RelativeLayout relView0;
    private RelativeLayout relView1;
    private RelativeLayout relView2;
    private RelativeLayout relView3;
    private RelativeLayout relView4;
    private RelativeLayout relView5;
    private RelativeLayout relView6;
    private RelativeLayout relView7;
    private Intent intent;
    private String phone = "";
    private SmartRefreshLayout smartRefreshLayout;
    private static final String TAG = "Home3Fragment";
    private RoundedImageView roundedImageView;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView jueSe;
    private String recruiter = "0";
    private TextView userPhone;
    private String eventUid = "";


    @Subscribe(threadMode = ThreadMode.POSTING, sticky = false)
    public void getEventmessage(MessageEvent event) {
        int messageType = event.getMessageType();
        switch (messageType) {
            case 2:
                //更新个人中心
                smartRefreshLayout.autoRefresh();
                eventUid = event.getKeyWord1();
                Log.e(TAG, "getEventmessage: 个人中心收到消息 保存数据  http" + SPTool.getSessionValue(AppSP.UID) + "---eventUid" + event.getKeyWord1());
                break;
        }
    }

    private boolean isFirst = true;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                        getQiuZhiMyInfo();
                    }

                }
            }, 500);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.home3fragment_layout, null);

        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }


        tvJinDu = view.findViewById(R.id.tvJinDu);
        circlePercentView = view.findViewById(R.id.circlePercentView);
        roundedImageView = view.findViewById(R.id.roundedImageView);

        llView0 = view.findViewById(R.id.llView0);
        llView1 = view.findViewById(R.id.llView1);
        llView2 = view.findViewById(R.id.llView2);
        llView3 = view.findViewById(R.id.llView3);
        llView4 = view.findViewById(R.id.llView4);
        jueSe = view.findViewById(R.id.jueSe);


        relView0 = view.findViewById(R.id.relView0);
        relView1 = view.findViewById(R.id.relView1);
        relView2 = view.findViewById(R.id.relView2);
        relView3 = view.findViewById(R.id.relView3);
        relView4 = view.findViewById(R.id.relView4);
        relView5 = view.findViewById(R.id.relView5);
        relView6 = view.findViewById(R.id.relView6);
        relView7 = view.findViewById(R.id.relView7);


        userPhone = view.findViewById(R.id.userPhone);
        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        tv3 = view.findViewById(R.id.tv3);

        llView0.setOnClickListener(this);
        llView1.setOnClickListener(this);
        llView2.setOnClickListener(this);
        llView3.setOnClickListener(this);
        llView4.setOnClickListener(this);

        relView0.setOnClickListener(this);
        relView1.setOnClickListener(this);
        relView2.setOnClickListener(this);
        relView3.setOnClickListener(this);
        relView4.setOnClickListener(this);
        relView5.setOnClickListener(this);
        relView6.setOnClickListener(this);
        relView7.setOnClickListener(this);

        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setEnableLoadMore(false);


        if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
            getQiuZhiMyInfo();
        }


        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
              /*  if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    getQiuZhiMyInfo(SPTool.getSessionValue(AppSP.UID));
                }*/
                getQiuZhiMyInfo();
                Log.e(TAG, "onRefresh: http  执行下拉刷新方法");
            }
        });


        return view;

    }

    //求职者个人信息
    private void getQiuZhiMyInfo() {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("hr", "0");
        Log.e(TAG, "getQiuZhiMyInfo: http getEventmessage 个人中心请求" + SPTool.getSessionValue(AppSP.UID) + "---");
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.qiuZhiMyInfo, params, new BaseCallback<QiuZhiZheMyInfoBean>() {
            @Override
            public void onFailure(Request request, Exception e) {
                smartRefreshLayout.finishRefresh();
            }

            @Override
            public void onResponse(Response response) {
                smartRefreshLayout.finishRefresh();
            }

            @Override
            public void onSuccess(Response response, QiuZhiZheMyInfoBean resultBean) {
                smartRefreshLayout.finishRefresh();
                userPhone.setText(StringUtil.replacePhoneCui(resultBean.getMobile()));
                Glide.with(getActivity()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror).error(R.mipmap.imageerror))
                        .load(resultBean.getAvatar()).into(roundedImageView);
                tv1.setText(resultBean.getName());
                recruiter = resultBean.getRecruiter();

                SPTool.addSessionMap(AppSP.USER_NAME, resultBean.getName());
                SPTool.addSessionMap(AppSP.USER_ICON, resultBean.getAvatar());
                SPTool.addSessionMap(AppSP.UID, SPTool.getSessionValue(AppSP.UID));

                //是否具有hr权限
               /* switch (recruiter) {
                    case "1":
                        tv2.setText("HR");
                        jueSe.setText("招聘者");
                        break;
                    case "0":
                        tv2.setText("求职者");
                        jueSe.setText("求职者");
                        break;
                }*/

                tv2.setText("求职者");
                jueSe.setText("求职者");

                tvJinDu.setText(resultBean.getImprovedDegree() + "%");
                setData1(circlePercentView, 100, Integer.parseInt(resultBean.getImprovedDegree()));

                if (!resultBean.getInterviewCount().equals("0")) {
                    tv3.setText(resultBean.getInterviewCount());
                    tv3.setVisibility(View.VISIBLE);
                } else {
                    tv3.setVisibility(View.INVISIBLE);
                }


                phone = resultBean.getServicePhone();


            }

            @Override
            public void onError(Response response, int code, Exception e) {
                smartRefreshLayout.finishRefresh();
            }
        });
    }


    private void setData1(CirclePercentView circlePercentView, int max, int current) {
        float percentage = (100f * current) / max;
        ObjectAnimator animator = ObjectAnimator.ofFloat(circlePercentView, "percentage", 0, percentage);
        animator.setDuration(ANIMATOR_DURATION);
        animator.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llView0:
                //个人资料
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    intent = new Intent(getActivity(), MyUserInfoActivity.class);
                    startActivity(intent);
                } else {
                    ToastFactory.getToast(getActivity(), "请先登录").show();
                    startActivity(new Intent(getActivity(), Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.llView1:
                //已收藏
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    intent = new Intent(getActivity(), MyShouCangGangActivity.class);
                    startActivity(intent);
                } else {
                    ToastFactory.getToast(getActivity(), "请先登录").show();
                    startActivity(new Intent(getActivity(), Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.llView2:
                //待面试
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    intent = new Intent(getActivity(), DaiMianShiListActivity.class);
                    startActivity(intent);
                } else {
                    ToastFactory.getToast(getActivity(), "请先登录").show();
                    startActivity(new Intent(getActivity(), Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.llView3:
                //已录取
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    intent = new Intent(getActivity(), YiLuQuActivity.class);
                    startActivity(intent);
                } else {
                    ToastFactory.getToast(getActivity(), "请先登录").show();
                    startActivity(new Intent(getActivity(), Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.llView4:
                //不合适
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    intent = new Intent(getActivity(), MianShiListActivity.class);
                    startActivity(intent);
                } else {
                    ToastFactory.getToast(getActivity(), "请先登录").show();
                    startActivity(new Intent(getActivity(), Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.relView0:
                //我的资料进度
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    intent = new Intent(getActivity(), MyJianLiActivity.class);
                    startActivity(intent);
                } else {
                    ToastFactory.getToast(getActivity(), "请先登录").show();
                    startActivity(new Intent(getActivity(), Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.relView1:
                //已屏蔽记录
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    intent = new Intent(getActivity(), PingBiGangActivity.class);
                    startActivity(intent);
                } else {
                    ToastFactory.getToast(getActivity(), "请先登录").show();
                    startActivity(new Intent(getActivity(), Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.relView2:
                //我的关注
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    intent = new Intent(getActivity(), MyGuanZhuGangActivity.class);
                    startActivity(intent);
                } else {
                    ToastFactory.getToast(getActivity(), "请先登录").show();
                    startActivity(new Intent(getActivity(), Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.relView3:
                //求职意向
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    intent = new Intent(getActivity(), QiuZhiYiXiangActivity.class);
                    startActivity(intent);
                } else {
                    ToastFactory.getToast(getActivity(), "请先登录").show();
                    startActivity(new Intent(getActivity(), Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.relView4:
                //在线客服
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    if (!TextUtils.isEmpty(phone)) {
                        callPhone();
                    }
                } else {
                    ToastFactory.getToast(getActivity(), "请先登录").show();
                    startActivity(new Intent(getActivity(), Login1PhoneCodeActivity.class));
                    return;
                }

               /* intent = new Intent(getActivity(), SelectHangYeActivity.class);
                startActivity(intent);*/

                break;
            case R.id.relView5:
                //隐私设置


                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    intent = new Intent(getActivity(), MyYinSiActivity.class);
                    startActivity(intent);
                } else {
                    ToastFactory.getToast(getActivity(), "请先登录").show();
                    startActivity(new Intent(getActivity(), Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.relView6:
                //切换身份
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {

                    if (recruiter.equals("0")) {
                        ToastFactory.getToast(getActivity(), "您还不是HR").show();
                        return;
                    } else {
                        intent = new Intent(getActivity(), SelectUserTypeActivity.class);
                        startActivity(intent);
                    }


                } else {
                    ToastFactory.getToast(getActivity(), "请先登录").show();
                    startActivity(new Intent(getActivity(), Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.relView7:
                //我的设置
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    intent = new Intent(getActivity(), SettingActivity.class);
                    startActivity(intent);
                } else {
                    ToastFactory.getToast(getActivity(), "请先登录").show();
                    startActivity(new Intent(getActivity(), Login1PhoneCodeActivity.class));
                    return;
                }
                break;
        }
    }

    @PermissionGrant(AppSP.PMS_LOCATION)
    public void pmsLocationSuccess() {
        //权限授权成功
        TellUtil.tell(getActivity(), phone);
    }

    /*拨打电话*/
    private void callPhone() {
        if (null != phone) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                MPermissions.requestPermissions(this, AppSP.PMS_LOCATION, Manifest.permission.CALL_PHONE);
            } else {
                pmsLocationSuccess();
            }
        }
    }
}
