package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.home1.QiYe1Fragment;
import com.lx.zhaopin.home1.QiYe2Fragment;

import java.util.ArrayList;

public class QiYeInfoActivity extends BaseActivity {


    private ViewPager viewPager;
    private TabLayout tablayout;


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
    private String qiYeID = "测试数据";

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.qiyeinfo_activity);
        init();
    }

    private void init() {
        topTitle.setVisibility(View.INVISIBLE);

        //qiYeID = getIntent().getStringExtra("qiYeID");

        tablayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPager);

        mTabTitles[0] = "企业信息";
        mTabTitles[1] = "在招岗位";
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        mFragmentArrays[0] = QiYe1Fragment.newInstance(qiYeID);
        mFragmentArrays[1] = QiYe2Fragment.newInstance(qiYeID);


        //TODO https://www.cnblogs.com/zhangqie/p/6404890.html
        PagerAdapter pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        //将ViewPager和TabLayout绑定
        tablayout.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(mTabTitles.length);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
