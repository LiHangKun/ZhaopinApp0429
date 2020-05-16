package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.QiYeInfoBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.home1.QiYe1Fragment;
import com.lx.zhaopin.home1.QiYe2Fragment;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

public class QiYeInfoActivity extends BaseActivity implements View.OnClickListener {


    private ViewPager viewPager;
    private TabLayout tablayout;
    private RoundedImageView roundedImageView;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView okID;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.okID:
                //关注  guanZhuGongsi
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    guanZhuGongsiMe(qiYeID);
                } else {
                    ToastFactory.getToast(mContext, "请先登录").show();
                    startActivity(new Intent(mContext, Login1PhoneCodeActivity.class));
                    return;
                }

                break;
        }
    }

    private void guanZhuGongsiMe(String cid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("cid", cid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.guanZhuGongsi, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                getQiYeInfo(qiYeID);

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


    final class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentArrays[position];
        }


        @Override
        public int getCount() {
            return mFragmentArrays.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];

        }
    }

    private Fragment[] mFragmentArrays = new Fragment[2];

    private String[] mTabTitles = new String[3];
    private String qiYeID = "";

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.qiyeinfo_activity);
        init();
    }

    private void init() {
        topTitle.setVisibility(View.INVISIBLE);

        qiYeID = getIntent().getStringExtra("qiYeID");

        tablayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPager);

        mTabTitles[0] = "企业信息";
        mTabTitles[1] = "在招岗位";
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        mFragmentArrays[0] = QiYe1Fragment.newInstance(qiYeID);
        mFragmentArrays[1] = QiYe2Fragment.newInstance(qiYeID);

        roundedImageView = findViewById(R.id.roundedImageView);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        okID = findViewById(R.id.okID);

        okID.setOnClickListener(this);

        //TODO https://www.cnblogs.com/zhangqie/p/6404890.html
        PagerAdapter pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        //将ViewPager和TabLayout绑定
        tablayout.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(mTabTitles.length);


        getQiYeInfo(qiYeID);


    }

    private void getQiYeInfo(String cid) {
        Map<String, String> params = new HashMap<>();
        params.put("cid", cid);
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.companyInfo, params, new BaseCallback<QiYeInfoBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, QiYeInfoBean resultBean) {

                Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror).error(R.mipmap.imageerror))
                        .load(resultBean.getLogo()).into(roundedImageView);
                tv1.setText(resultBean.getName());
                tv2.setText(resultBean.getFinancing().getName());
                tv3.setText(resultBean.getStaffNum() + "人");
                tv4.setText(resultBean.getIndustry().getName());

                String collected = resultBean.getCollected();
                switch (collected) {
                    case "1":
                        okID.setText("取消关注");
                        break;
                    case "0":
                        okID.setText("关注");
                        break;
                }


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
}
