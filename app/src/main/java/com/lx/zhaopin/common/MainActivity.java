package com.lx.zhaopin.common;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;

import java.util.ArrayList;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class MainActivity extends BaseActivity implements RongIM.UserInfoProvider {

    public ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private MyPagerAdapter adapter;
    private RadioButton rB1;
    private RadioButton rB2;
    private RadioButton rB3;


    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    /**
     * 第一种解决办法 通过监听keyUp
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
                return true;
            } else {
                finish();
            }
        }

        return super.onKeyUp(keyCode, event);
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        setViews();
        setListeners();
        fragments = new ArrayList<>();
        fragments.add(new Home1Fragment());
        fragments.add(new Home2Fragment());
        fragments.add(new Home3Fragment());
        initC();
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

            @Override
            public UserInfo getUserInfo(String userId) {
                //MainActivity.this.getUserInfo(userId);
                return null;//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
            }

        }, true);
        RongIM.setUserInfoProvider(this, true);
        final String nickName = "崔文乐";
        final String userHead = "https://himg2.huanqiucdn.cn/attachment2010/2020/0507/20200507011017575.jpg";
        final String uid = "123";
        RongIM.getInstance().refreshUserInfoCache(new UserInfo(uid, nickName, Uri.parse(userHead)));

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);


        viewPager.setOffscreenPageLimit(fragments.size());
    }


    private void initC() {
        RongIM.connect("5EzeuoxRkESv3vBru1S/BvTP18LHSVglo2ejizkCQP8=@z7fh.cn.rongnav.com;z7fh.cn.rongcfg.com", new RongIMClient.ConnectCallback() {

            /**
             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            @Override
            public void onTokenIncorrect() {
                Log.e("RongIM", "onTokenIncorrect");
            }

            /**
             * 连接融云成功
             * @param userid 当前 token 对应的用户 id
             */
            @Override
            public void onSuccess(String userid) {
                Log.e("RongIM", "onSuccess" + userid);
                //SharePrefUtil.saveString(MainActivity.this, AppConsts.RONGID, userid);


            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("RongIM", "onError" + errorCode);
                if (errorCode == RongIMClient.ErrorCode.RC_DISCONN_KICK) {

                }
            }
        });
    }

    @Override
    public UserInfo getUserInfo(String s) {
        setUserInfo(s);
        return null;
    }

    private void setUserInfo(String s) {

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
                        rB1.setChecked(true);
                        rB1.setTextColor(getResources().getColor(R.color.mainColor888));
                        rB2.setTextColor(getResources().getColor(R.color.txt_orange2));
                        rB3.setTextColor(getResources().getColor(R.color.txt_orange2));

                        break;
                    case 1:
                        rB2.setChecked(true);
                        rB2.setTextColor(getResources().getColor(R.color.mainColor888));
                        rB1.setTextColor(getResources().getColor(R.color.txt_orange2));
                        rB3.setTextColor(getResources().getColor(R.color.txt_orange2));
                        break;
                    case 2:
                        rB3.setChecked(true);
                        rB3.setTextColor(getResources().getColor(R.color.mainColor888));
                        rB1.setTextColor(getResources().getColor(R.color.txt_orange2));
                        rB2.setTextColor(getResources().getColor(R.color.txt_orange2));
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


    private void setViews() {
        viewPager = findViewById(R.id.viewPager);
        rB1 = findViewById(R.id.RadioButton1);
        rB2 = findViewById(R.id.RadioButton2);
        rB3 = findViewById(R.id.RadioButton3);


    }

    //监听RadioButton的点击
    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.RadioButton1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.RadioButton2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.RadioButton3:
                viewPager.setCurrentItem(2);
                break;

            default:
        }
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
