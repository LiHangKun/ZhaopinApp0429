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
import com.lx.zhaopin.activity.AboutMeActivity;
import com.lx.zhaopin.activity.AddZhuanYeJiNengActivity;
import com.lx.zhaopin.activity.ChangJianWenTiActivity;
import com.lx.zhaopin.activity.HangYeLeiXingActivity;
import com.lx.zhaopin.activity.MyShouCangGangActivity;
import com.lx.zhaopin.activity.MyYinSiActivity;
import com.lx.zhaopin.activity.PingBiGangActivity;
import com.lx.zhaopin.activity.PingBiRenActivity;
import com.lx.zhaopin.activity.QiuZhiFeedActivity;
import com.lx.zhaopin.activity.QiuZhiQiWangActivity;
import com.lx.zhaopin.activity.QiuZhiYiXiangActivity;
import com.lx.zhaopin.activity.SettingActivity;
import com.lx.zhaopin.activity.YiLuQuActivity;
import com.lx.zhaopin.activity.YuLanJianLiActivity;
import com.lx.zhaopin.activity.ZhiWuLeiXingActivity;
import com.lx.zhaopin.base.BaseFragment;
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
                intent = new Intent(getActivity(), QiuZhiQiWangActivity.class);
                startActivity(intent);
                break;
            case R.id.llView1:
                //已收藏
                intent = new Intent(getActivity(), ZhiWuLeiXingActivity.class);
                startActivity(intent);
                break;
            case R.id.llView2:
                //待面试
                intent = new Intent(getActivity(), HangYeLeiXingActivity.class);
                startActivity(intent);
                break;
            case R.id.llView3:
                //已录取
                intent = new Intent(getActivity(), AddZhuanYeJiNengActivity.class);
                startActivity(intent);
                break;
            case R.id.llView4:
                //不合适
                intent = new Intent(getActivity(), YuLanJianLiActivity.class);
                startActivity(intent);
                break;
            case R.id.relView0:
                //我的资料进度
                intent = new Intent(getActivity(), QiuZhiFeedActivity.class);
                startActivity(intent);
                break;
            case R.id.relView1:
                //已屏蔽记录
                intent = new Intent(getActivity(), PingBiGangActivity.class);
                startActivity(intent);
                break;
            case R.id.relView2:
                //我的关注
                intent = new Intent(getActivity(), PingBiRenActivity.class);
                startActivity(intent);
                break;
            case R.id.relView3:
                //求职意向
                intent = new Intent(getActivity(), MyShouCangGangActivity.class);
                startActivity(intent);
                break;
            case R.id.relView4:
                //在线客服
                intent = new Intent(getActivity(), ChangJianWenTiActivity.class);
                startActivity(intent);
                break;
            case R.id.relView5:
                //隐私设置
                intent = new Intent(getActivity(), MyYinSiActivity.class);
                startActivity(intent);
                break;
            case R.id.relView6:
                //切换身份
                intent = new Intent(getActivity(), AboutMeActivity.class);
                startActivity(intent);
                break;
            case R.id.relView7:
                //我的设置
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
