package com.lx.zhaopin.common;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.allenliu.versionchecklib.AppConstant;
import com.allenliu.versionchecklib.callback.APKDownloadListener;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.NotificationBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.VersionBean;
import com.lx.zhaopin.hr.HRHome1Fragment;
import com.lx.zhaopin.hr.HRHome2Fragment;
import com.lx.zhaopin.hr.HRHome3Fragment;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.other.ShadowDrawable;
import com.lx.zhaopin.utils.APKVersionCodeUtils;
import com.lx.zhaopin.utils.ActivityManager;
import com.lx.zhaopin.utils.DataCleanManager;
import com.lx.zhaopin.utils.DisplayUtil;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.WindowUtil;
import com.lx.zhaopin.view.NoScrollViewPager;
import com.lx.zhaopin.view.TabView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseActivity implements RongIM.UserInfoProvider {

    //public ViewPager viewPager;
    public NoScrollViewPager viewPager;
    private ArrayList<Fragment> fragments;
    //private MyPagerAdapter adapter;
    private TabLayout tabLayout;
    private static final String TAG = "MainActivity";


    //记录用户首次点击返回键的时间
    private long firstTime = 0;
    private String eventUid = "";
    private String eventUserHead = "";
    private String eventNickName = "";
    private String eventRongToken = "";
    private String duanUid;
    private String userType, currentUserType;
    private ImageView tabMoveImg;
    private TextView tabMessageNumTv;
    private int screenWidth;
    private int posSel;
    private int blueBcWidth;
    private int tabGap, tabMoveX;
    private RelativeLayout shadowRl;
    private List<TabView> tabViews = new ArrayList<>();

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
        setViews();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = false)
    public void getEventmessage(MessageEvent event) {
        int messageType = event.getMessageType();
        switch (messageType) {
            case 2:
                eventUid = event.getKeyWord1();
                eventNickName = event.getKeyWord2();
                eventUserHead = event.getKeyWord3();
                eventRongToken = event.getKeyWord4();
                duanUid = event.getKeyWord5();
                Log.i(TAG, "getEventmessage:用户  重新链接融云,和更新个人中心" + eventUid + "哈哈" + eventNickName + "哈哈" + eventUserHead + "哈哈" + eventRongToken + "短的" + duanUid);
                //setUserType(eventUid);
                setUserType(duanUid);
                getUnMessageNumber();
                //setUserRongInfoMethod0(eventRongToken);
                break;
            case 14:
                userType = SPTool.getSessionValue(AppSP.USER_TYPE);
                if (TextUtils.equals(userType, "0")){
                    userType="1";
                    SPTool.addSessionMap(AppSP.USER_TYPE, userType);
                }else {
                    userType="0";
                    SPTool.addSessionMap(AppSP.USER_TYPE, userType);
                }
                setUserType(duanUid);
                getUnMessageNumber();
                break;
        }
    }

    private void getUnMessageNumber() {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        if (TextUtils.equals("0", userType)){
            params.put("hr", "0");
        }else {
            params.put("hr", "1");
        }
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.newMessageCount, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                int totalCount = Integer.parseInt(resultBean.getChatApplyCount()) + Integer.parseInt(resultBean.getJobFeedbackCount()) + Integer.parseInt(resultBean.getSystemMessageCount());
                Log.i(TAG, "count=" + totalCount);
                if (totalCount == 0) {
                    tabMessageNumTv.setVisibility(View.INVISIBLE);
                } else {
                    tabMessageNumTv.setVisibility(View.VISIBLE);
                    tabMessageNumTv.setText(String.valueOf(totalCount));
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
                Log.i(TAG, "error");
            }
        });
    }

    private void init() {
        SPTool.addSessionMap(AppSP.chatWhere, "0");
        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }


        setUserType(duanUid);
        clearAllCachecatch();
        screenWidth = WindowUtil.getScreenWidth(this);
        posSel = 0;
        tabGap = (screenWidth - DisplayUtil.dip2px(mContext, 84) * 3) / 3;
        tabMoveX = -(tabGap + DisplayUtil.dip2px(mContext, 84));
        tabMoveImg.setTranslationX(tabMoveX);
        tabMessageNumTv.setTranslationX(-DisplayUtil.dip2px(mContext, 30));
        if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
            getUnMessageNumber();
        }
    }

    private void clearAllCachecatch() {
        DataCleanManager.clearAllCache(this);
        try {
            Log.i(TAG, "initViews: 剩余缓存" + DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUserType(String eventUid) {

        fragments = new ArrayList<>();

        userType = SPTool.getSessionValue(AppSP.USER_TYPE);
        Log.i(TAG, "setUserType: 用户" + userType);
        if (!TextUtils.equals(currentUserType, userType)) {
            fragments.clear();
            if (userType.equals("0")) {
                fragments.add(new Home1Fragment());
                fragments.add(new Home2Fragment());
                fragments.add(new Home3Fragment());
            } else if (userType.equals("1")) {
                fragments.add(new HRHome1Fragment());
                fragments.add(new HRHome2Fragment());
                fragments.add(new HRHome3Fragment());
            }

            FragmentDreamAdapter fragmentDreamAdapter = new FragmentDreamAdapter(getSupportFragmentManager(), fragments);
            viewPager.setAdapter(fragmentDreamAdapter);
            viewPager.setOffscreenPageLimit(fragments.size());
            tabLayout.setupWithViewPager(viewPager);
            setListeners();
        }

        if (!TextUtils.isEmpty(eventUid)) {
            setUserRongInfoMethod0(eventRongToken);
        } else {
            if (userType.equals("0")) {
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.USER_RongToken))) {
                    setUserRongInfoMethod(SPTool.getSessionValue(AppSP.USER_RongToken));
                }
            } else {
                //用招聘者连接融云
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.USER_HR_RongToken))) {
                    setUserRongInfoMethod(SPTool.getSessionValue(AppSP.USER_HR_RongToken));
                }
            }
        }

        //adapter = new MyPagerAdapter(getSupportFragmentManager());

    }

    private void setUserRongInfoMethod0(String eventRongToken) {

        Log.i(TAG, "setUserRongInfoMethod0: 第一次连接融云" + eventRongToken);
        initC(eventRongToken);
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

            @Override
            public UserInfo getUserInfo(String userId) {
                Log.e(TAG, "userId   =" + userId);
                MainActivity.this.getUserInfo(userId);
                return null;//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
            }

        }, true);
        RongIM.setUserInfoProvider(this, true);


        //RongIM.getInstance().refreshUserInfoCache(new UserInfo("e6151ce13783416bad54fffc10b19c0d", eventNickName, Uri.parse(eventUserHead)));
        RongIM.getInstance().refreshUserInfoCache(new UserInfo(duanUid, eventNickName, Uri.parse(eventUserHead)));
        Log.i(TAG, "setUserRongInfoMethod: 第一次登录 融云 getEventmessage00000" + eventNickName + "---" + eventUserHead + "--" + eventUid + "短的" + duanUid + "链接融云" + eventRongToken);

    }


    private void setUserRongInfoMethod(String eventRongToken) {

        eventNickName = SPTool.getSessionValue(AppSP.USER_NAME);
        eventUserHead = SPTool.getSessionValue(AppSP.USER_ICON);
        eventUid = SPTool.getSessionValue(AppSP.UID);
        duanUid = SPTool.getSessionValue(AppSP.UID_DUAN);
        Log.i(TAG, "setUserRongInfoMethod: 第2次连接融云222222222222222222222" + eventRongToken);
        Log.i(TAG, "setUserRongInfoMethod: 融云 getEventmessage" + eventNickName + "---" + eventUserHead + "--" + eventUid + "短的" + duanUid);
        Log.i(TAG, "setUserRongInfoMethod: 融云 getEventmessage 这个方法执行" + eventRongToken + "----");
        initC(eventRongToken);
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

            @Override
            public UserInfo getUserInfo(String userId) {
                Log.e(TAG, "userId=" + userId);
                MainActivity.this.getUserInfo(userId);
                return null;//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
            }

        }, true);
        RongIM.setUserInfoProvider(this, true);


        //RongIM.getInstance().refreshUserInfoCache(new UserInfo(eventUid, eventNickName, Uri.parse(eventUserHead)));
        RongIM.getInstance().refreshUserInfoCache(new UserInfo(duanUid, eventNickName, Uri.parse(eventUserHead)));


        //RongIM.getInstance().refreshUserInfoCache(new UserInfo("1000333", "电脑", Uri.parse(eventUserHead)));
    }


    private void initC(String eventRongToken) {
        Log.i(TAG, "initC: getEventmessage" + eventRongToken + "---");
        RongIM.connect(eventRongToken, new RongIMClient.ConnectCallback() {

            /**
             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            @Override
            public void onTokenIncorrect() {
                Log.e("RongIM ", " 融云链接 getEventmessage onTokenIncorrect");
            }

            /**
             * 连接融云成功
             * @param userid 当前 token 对应的用户 id
             */
            @Override
            public void onSuccess(String userid) {
                Log.e("RongIM", " 融云链接  getEventmessage onSuccess" + userid);
                //SharePrefUtil.saveString(MainActivity.this, AppConsts.RONGID, userid);


            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("RongIM", " 融云链接  getEventmessage onError" + errorCode);
                if (errorCode == RongIMClient.ErrorCode.RC_DISCONN_KICK) {

                }
            }
        });


        RongIM.getInstance().setConnectionStatusListener(new RongIMClient.ConnectionStatusListener() {
            @Override
            public void onChanged(ConnectionStatus connectionStatus) {
                switch (connectionStatus) {
                    case CONNECTED://连接成功。
                        break;
                    case DISCONNECTED://断开连接。
                        break;
                    case CONNECTING://连接中。
                        break;
                    case NETWORK_UNAVAILABLE://网络不可用。
                        break;
                    case KICKED_OFFLINE_BY_OTHER_CLIENT://用户账户在其他设备登录，本机会被踢掉线


                        StyledDialog.init(mContext);
                        StyledDialog.buildIosAlert("", "\r该账号在别处登录,请重新登录", new MyDialogListener() {
                            @Override
                            public void onFirst() {
                                logoutMe();
                            }

                            @Override
                            public void onSecond() {


                            }
                        }).setBtnColor(R.color.mainColor2, R.color.mainColor1, 0).setBtnText("确定").show();


                        break;
                }
            }
        });


    }


    private void logoutMe() {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.logout, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(intent);
                SPTool.addSessionMap(AppSP.UID, "");
                SPTool.addSessionMap(AppSP.USER_NAME, "");
                SPTool.addSessionMap(AppSP.USER_ICON, "");
                SPTool.addSessionMap(AppSP.USER_PHONE, "");
                SPTool.addSessionMap(AppSP.USER_RongToken, "");
                SPTool.addSessionMap(AppSP.USER_TYPE, "0");
                SPTool.addSessionMap(AppSP.isLogin, false);
                RongIM.getInstance().logout();
                ActivityManager.finishActivity();
                finish();

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }


    @Override
    public UserInfo getUserInfo(String s) {
        Log.e(TAG, "userId s = " + s);
        setUserInfo(s);
        return null;
    }

    private void setUserInfo(final String userId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("userId", userId);
        Log.e("setUserInfo mid=" + SPTool.getSessionValue(AppSP.UID), "userId=" + userId);
        OkHttpHelper.getInstance().post(MainActivity.this, NetClass.BASE_URL + NetCuiMethod.getRongUserInfo, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                RongIM.getInstance().refreshUserInfoCache(new UserInfo(userId, resultBean.getName(), Uri.parse(resultBean.getAvatar())));

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


    //通过监听viewpager滑动改变Checked的属性
    private void setListeners() {
        tabViews.clear();
        TabView tabView1 = new TabView(this);
        userType = SPTool.getSessionValue(AppSP.USER_TYPE);
        if (userType.equals("0")) {
            tabView1.setValues(R.mipmap.icon_tab_home, "职墙", null);
        } else {
            tabView1.setValues(R.mipmap.icon_tab_home, "人墙", null);
        }
        TabView tabView2 = new TabView(this);
        tabView2.setValues(R.mipmap.icon_tab_message, "消息", tabMessageNumTv);
        TabView tabView3 = new TabView(this);
        tabView3.setValues(R.mipmap.icon_tab_person, "我的", null);
        tabViews.add(tabView1);
        tabViews.add(tabView2);
        tabViews.add(tabView3);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setCustomView(tabViews.get(i));
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                int distance = (pos - posSel) * (tabGap + DisplayUtil.dip2px(mContext, 84));
                tabMoveX = tabMoveX + distance;
                tabMoveImg.clearAnimation();
                tabMoveImg.animate().translationX(tabMoveX).setDuration(200).start();
                Log.e("main", "target_x = " + distance);
                posSel = pos;
                tabViews.get(pos).setSelected(true);
                Log.e("main", "position=" + pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabViews.get(tab.getPosition()).setSelected(false);
                Log.e("main", "un position=" + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e(TAG, "onTabReselected = " + tab.getPosition());
            }
        });
    }


    private void setViews() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.lr_tab);
        tabMoveImg = findViewById(R.id.tab_move_img);
        tabMessageNumTv = findViewById(R.id.tab_message_num);
        shadowRl = findViewById(R.id.shadow_rl);
        ShadowDrawable drawable = new ShadowDrawable.Builder()
                .setShapeRadius(0)
                .setShadowRadius(DisplayUtil.dip2px(mContext, 4))
                .setShadowColor(Color.parseColor("#80dbdbdb"))
                .setOffsetX(0)
                .setOffsetY(- DisplayUtil.dip2px(mContext, 2))
                .builder();
        shadowRl.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        shadowRl.setBackground(drawable);
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


    @Override
    protected void onResume() {
        super.onResume();
        if (AppSP.isToShopCar) {
            Log.e(TAG, "onresume");
            viewPager.setCurrentItem(2);
            AppSP.isToShopCar = false;
        } else if (AppSP.isToHome) {
            viewPager.setCurrentItem(0);
            AppSP.isToHome = false;
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        versionUpdateMe();
    }

    private void versionUpdateMe() {
        Map<String, String> params = new HashMap<>();

        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.versionUpdate, params, new SpotsCallBack<VersionBean>(mContext) {
            @Override
            public void onSuccess(Response response, VersionBean resultBean) {

                String url = resultBean.getAndroidFile();
                String updateNo = resultBean.getNum();
                String versionNo = resultBean.getVersion();
                AppConstant.apkLength = String.valueOf(50 * 1024 * 1024);
                int numberServer = Integer.parseInt(updateNo);
                int verCode = APKVersionCodeUtils.getVersionCode(mContext);
                if (numberServer > verCode) {
                    Log.i(TAG, "xuanZe: 执行1111111111");
                    AllenVersionChecker
                            .getInstance()
                            .downloadOnly(UIData.create().setDownloadUrl(url).setTitle("发现新版本").setContent("新版本升级")).setNotificationBuilder(
                            NotificationBuilder.create()
                                    .setRingtone(true)
                                    .setIcon(R.mipmap.logo)
                                    .setTicker("版本更新")
                                    .setContentTitle(resultBean.getRemarks())
                    ).setApkDownloadListener(new APKDownloadListener() {
                        @Override
                        public void onDownloading(int progress) {
                            Log.e("download", "------>" + progress);
                        }

                        @Override
                        public void onDownloadSuccess(File file) {

                        }

                        @Override
                        public void onDownloadFail() {

                        }
                    }).setForceRedownload(true).setShowDownloadingDialog(false).executeMission(mContext);


                } else {
                    Log.i(TAG, "xuanZe: 执行22222222");
                }


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });


    }

}
