package com.lx.zhaopin.common;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.SearchActivity;
import com.lx.zhaopin.base.BaseFragment;
import com.lx.zhaopin.home1.ShouYe1Fragment;
import com.lx.zhaopin.home1.ShouYe2Fragment;
import com.lx.zhaopin.home2.Message1Fragment;
import com.lx.zhaopin.home2.Message2Fragment;

import java.util.ArrayList;

public class Home1Fragment extends BaseFragment implements View.OnClickListener {

    public ViewPager viewPager;
    private TextView tv1;
    private TextView tv2;
    private ArrayList<Fragment> fragments;
    private MyPagerAdapter adapter;
    private LinearLayout llSearchView;


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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(container.getContext(), R.layout.home1fragment_layout, null);
        viewPager = view.findViewById(R.id.viewPager);
        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        llSearchView = view.findViewById(R.id.llSearchView);
        llSearchView.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);

        setListeners();

        fragments = new ArrayList<>();
        fragments.add(new ShouYe1Fragment());
        fragments.add(new ShouYe2Fragment());

        adapter = new MyPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(fragments.size());

        return view;

    }


    //通过监听viewpager滑动改变Checked的属性
    private void setListeners() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tv1.setTextColor(getResources().getColor(R.color.txt_c333));
                        tv2.setTextColor(getResources().getColor(R.color.txt_lv2));

                        tv1.setTextSize(18);
                        tv2.setTextSize(16);

                        tv1.setTypeface(null, Typeface.BOLD);
                        tv2.setTypeface(null, Typeface.NORMAL);


                        break;
                    case 1:
                        tv2.setTextColor(getResources().getColor(R.color.txt_c333));
                        tv1.setTextColor(getResources().getColor(R.color.txt_lv2));

                        tv1.setTextSize(16);
                        tv2.setTextSize(18);

                        tv2.setTypeface(null, Typeface.BOLD);
                        tv1.setTypeface(null, Typeface.NORMAL);

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
            case R.id.tv1:
                //推荐
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv2:
                //最新
                viewPager.setCurrentItem(1);
                break;
            case R.id.llSearchView:
                //搜索
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
        }
    }
}
