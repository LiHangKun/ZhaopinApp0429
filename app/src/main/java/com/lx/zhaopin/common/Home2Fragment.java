package com.lx.zhaopin.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.Login1PhoneCodeActivity;
import com.lx.zhaopin.base.BaseFragment;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.home2.Message1Fragment;
import com.lx.zhaopin.home2.Message2Fragment;
import com.lx.zhaopin.home2.Message3Fragment;
import com.lx.zhaopin.home2.NewShenqingFragment;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;
import okhttp3.Response;

public class Home2Fragment extends BaseFragment implements View.OnClickListener {


    public ViewPager viewPager;
    TextView alreadyReadTv;
    View goutongView;
    View fankuiView;
    View xitongView;
    TextView xiyongCountTv;
    RelativeLayout msgLayout;
    Unbinder unbinder;
    View newLine;
    private ArrayList<Fragment> fragments;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private LinearLayout llView1;
    private LinearLayout llView2;
    private LinearLayout llView3;
    private MyPagerAdapter adapter;
    private TextView messageNumberTv1;
    private TextView messageNumberTv2;
    private TextView messageNumberTv3;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.home2fragment_layout, null);
        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }

        viewPager = view.findViewById(R.id.viewPager);

        llView1 = view.findViewById(R.id.llView1);
        llView2 = view.findViewById(R.id.llView2);
        llView3 = view.findViewById(R.id.llView3);

        image1 = view.findViewById(R.id.image1);
        image2 = view.findViewById(R.id.image2);
        image3 = view.findViewById(R.id.image3);
        newLine=view.findViewById(R.id.new_line);


        alreadyReadTv = view.findViewById(R.id.already_read_tv);
        goutongView = view.findViewById(R.id.goutong_view);
        fankuiView = view.findViewById(R.id.fankui_view);
        xitongView = view.findViewById(R.id.xitong_view);
        xiyongCountTv = view.findViewById(R.id.xiyong_count_tv);
        msgLayout = view.findViewById(R.id.msg_layout);

        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        tv3 = view.findViewById(R.id.tv3);
        messageNumberTv1 = view.findViewById(R.id.messageNumberTv1);
        messageNumberTv2 = view.findViewById(R.id.messageNumberTv2);
        messageNumberTv3 = view.findViewById(R.id.messageNumberTv3);


        llView1.setOnClickListener(this);
        llView2.setOnClickListener(this);
        llView3.setOnClickListener(this);
        alreadyReadTv.setOnClickListener(this);
        msgLayout.setOnClickListener(this);
        setListeners();

        fragments = new ArrayList<>();
        fragments.add(new Message1Fragment());
        fragments.add(new Message2Fragment());
        fragments.add(new Message3Fragment());
        fragments.add(new NewShenqingFragment());


        tv1.setTextColor(getResources().getColor(R.color.text_color));
        tv2.setTextColor(getResources().getColor(R.color.zhiwei_location_text));
        tv3.setTextColor(getResources().getColor(R.color.zhiwei_location_text));

        image1.setImageResource(R.drawable.hom2s);
        image2.setImageResource(R.drawable.xiaoxi_fankui1);
        image3.setImageResource(R.drawable.xiaoxi_xitxiaoxi1);


        adapter = new MyPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(fragments.size());


        unbinder = ButterKnife.bind(this, view);
        return view;

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                getUnMessageNumber();
            } else {
                startActivity(new Intent(getActivity(), Login1PhoneCodeActivity.class));
                getActivity().finish();
            }
        }
    }


    private static final String TAG = "Home2Fragment";

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = false)
    public void getEventmessage(MessageEvent event) {
        int messageType = event.getMessageType();
        switch (messageType) {
            case 13:
                getUnMessageNumber();
                Log.i(TAG, "getEventmessage: 更新未读消息的数量");
                break;
        }
    }


    //newMessageCount
    private void getUnMessageNumber() {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("hr", "0");
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.newMessageCount, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                if (resultBean.getChatApplyCount().equals("0")) {
                    xiyongCountTv.setVisibility(View.GONE);
                } else {
                    xiyongCountTv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int arg0) {
            return fragments.get(arg0);
        }

        public int getCount() {
            return fragments.size();
        }
    }


    //通过监听viewpager滑动改变Checked的属性
    private void setListeners() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tv1.setTextColor(getResources().getColor(R.color.text_color));
                        tv2.setTextColor(getResources().getColor(R.color.zhiwei_location_text));
                        tv3.setTextColor(getResources().getColor(R.color.zhiwei_location_text));
                        goutongView.setVisibility(View.VISIBLE);
                        fankuiView.setVisibility(View.GONE);
                        xitongView.setVisibility(View.GONE);
                        newLine.setVisibility(View.INVISIBLE);

                        image1.setImageResource(R.drawable.hom2s);
                        image2.setImageResource(R.drawable.xiaoxi_fankui1);
                        image3.setImageResource(R.drawable.xiaoxi_xitxiaoxi1);

                        break;
                    case 1:
                        tv2.setTextColor(getResources().getColor(R.color.text_color));
                        tv1.setTextColor(getResources().getColor(R.color.zhiwei_location_text));
                        tv3.setTextColor(getResources().getColor(R.color.zhiwei_location_text));

                        image1.setImageResource(R.drawable.hom2n);
                        image2.setImageResource(R.drawable.xiaoxi_fankui2);
                        image3.setImageResource(R.drawable.xiaoxi_xitxiaoxi1);
                        newLine.setVisibility(View.INVISIBLE);

                        goutongView.setVisibility(View.GONE);
                        fankuiView.setVisibility(View.VISIBLE);
                        xitongView.setVisibility(View.GONE);

                        break;
                    case 2:
                        tv3.setTextColor(getResources().getColor(R.color.text_color));
                        tv1.setTextColor(getResources().getColor(R.color.zhiwei_location_text));
                        tv2.setTextColor(getResources().getColor(R.color.zhiwei_location_text));

                        goutongView.setVisibility(View.GONE);
                        fankuiView.setVisibility(View.GONE);
                        xitongView.setVisibility(View.VISIBLE);
                        newLine.setVisibility(View.INVISIBLE);

                        image1.setImageResource(R.drawable.hom2n);
                        image2.setImageResource(R.drawable.xiaoxi_fankui1);
                        image3.setImageResource(R.drawable.xiaoxi_xitxiaoxi2);

                        break;

                    case 3:
                        tv3.setTextColor(getResources().getColor(R.color.zhiwei_location_text));
                        tv1.setTextColor(getResources().getColor(R.color.zhiwei_location_text));
                        tv2.setTextColor(getResources().getColor(R.color.zhiwei_location_text));

                        goutongView.setVisibility(View.GONE);
                        fankuiView.setVisibility(View.GONE);
                        xitongView.setVisibility(View.GONE);
                        newLine.setVisibility(View.VISIBLE);



                        image1.setImageResource(R.drawable.hom2n);
                        image2.setImageResource(R.drawable.xiaoxi_fankui1);
                        image3.setImageResource(R.drawable.xiaoxi_xitxiaoxi2);

                        break;

                    default:
                }
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llView1:
                //招聘沟通
                viewPager.setCurrentItem(0);
                break;
            case R.id.llView2:
                //求职反馈
                viewPager.setCurrentItem(1);
                break;
            case R.id.llView3:
                //系统消息
                viewPager.setCurrentItem(2);
                break;
            case R.id.already_read_tv:
                break;
            case R.id.msg_layout:
                //系统消息
                viewPager.setCurrentItem(3);
                break;
        }
    }
}
