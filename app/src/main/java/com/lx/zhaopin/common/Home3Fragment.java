package com.lx.zhaopin.common;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.MyGongZuoActivity;
import com.lx.zhaopin.activity.MyJianLiActivity;
import com.lx.zhaopin.activity.MyJiaoYuActivity;
import com.lx.zhaopin.activity.MyUserInfoActivity;
import com.lx.zhaopin.activity.MyYinSiActivity;
import com.lx.zhaopin.activity.SettingActivity;
import com.lx.zhaopin.activity.XinPhoneActivity;
import com.lx.zhaopin.activity.YanSFActivity;
import com.lx.zhaopin.base.BaseFragment;
import com.lx.zhaopin.utils.RxToast;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.view.CirclePercentView;

public class Home3Fragment extends BaseFragment implements View.OnClickListener {

    private CirclePercentView circlePercentView;
    private TextView tvJinDu;
    public static final int ANIMATOR_DURATION = 1000;
    public static final int cuInt = 19;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.home3fragment_layout, null);

        tvJinDu = view.findViewById(R.id.tvJinDu);
        circlePercentView = view.findViewById(R.id.circlePercentView);

        llView0 = view.findViewById(R.id.llView0);
        llView1 = view.findViewById(R.id.llView1);
        llView2 = view.findViewById(R.id.llView2);
        llView3 = view.findViewById(R.id.llView3);
        llView4 = view.findViewById(R.id.llView4);


        relView0 = view.findViewById(R.id.relView0);
        relView1 = view.findViewById(R.id.relView1);
        relView2 = view.findViewById(R.id.relView2);
        relView3 = view.findViewById(R.id.relView3);
        relView4 = view.findViewById(R.id.relView4);
        relView5 = view.findViewById(R.id.relView5);
        relView6 = view.findViewById(R.id.relView6);
        relView7 = view.findViewById(R.id.relView7);

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


        tvJinDu.setText(cuInt + "%");
        setData1(circlePercentView, 100, cuInt);

        return view;

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
                intent = new Intent(getActivity(), MyUserInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.llView1:
                //已收藏
                intent = new Intent(getActivity(), YanSFActivity.class);
                startActivity(intent);
                break;
            case R.id.llView2:
                //待面试
                intent = new Intent(getActivity(), XinPhoneActivity.class);
                startActivity(intent);
                break;
            case R.id.llView3:
                //已录取
                intent = new Intent(getActivity(), MyJianLiActivity.class);
                startActivity(intent);
                break;
            case R.id.llView4:
                //不合适
                intent = new Intent(getActivity(), MyJiaoYuActivity.class);
                startActivity(intent);
                break;
            case R.id.relView0:
                //我的资料进度
                intent = new Intent(getActivity(), MyGongZuoActivity.class);
                startActivity(intent);
                break;
            case R.id.relView1:
                //已屏蔽记录
                intent = new Intent(getActivity(), MyYinSiActivity.class);
                startActivity(intent);
                break;
            case R.id.relView2:
                //我的关注
                ToastFactory.getToast(getActivity(), "我的关注").show();
                break;
            case R.id.relView3:
                //求职意向
                ToastFactory.getToast(getActivity(), "求职意向").show();
                break;
            case R.id.relView4:
                //在线客服
                ToastFactory.getToast(getActivity(), "在线客服").show();
                break;
            case R.id.relView5:
                //隐私设置
                ToastFactory.getToast(getActivity(), "隐私设置").show();
                break;
            case R.id.relView6:
                //切换身份
                ToastFactory.getToast(getActivity(), "切换身份").show();
                break;
            case R.id.relView7:
                //我的设置
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
