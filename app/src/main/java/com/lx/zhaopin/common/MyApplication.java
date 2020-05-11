package com.lx.zhaopin.common;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.awen.photo.FrescoImageLoader;
import com.lx.zhaopin.R;
import com.lx.zhaopin.utils.AppUtils;
import com.lx.zhaopin.utils.RxToast;
import com.lx.zhaopin.utils.SPTool;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;

public class MyApplication extends Application {


    public static boolean isDebug = true;


    private static MyApplication instance;
    private static final String TAG = "MyApplication";

    public static MyApplication getInstance() {
        return instance;
    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    public static Context mContext;
    public static MyApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this,"pvxdm17jpe7sr");//融云
        RxToast.setContext(this);
        mContext = this;
        SPTool.init(mContext, AppUtils.getAppName(this));
        // 主要是添加下面这句代码
        MultiDex.install(this); //65536

        context = (MyApplication) getApplicationContext();
        FrescoImageLoader.init(this, android.R.color.black);
        //极光
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        String registrationID = JPushInterface.getRegistrationID(this);
        SPTool.addSessionMap(AppSP.JupshID, registrationID);
        Log.i(TAG, "onCreate:极光信息 " + registrationID);


        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(true);

        /**
         * 设置日志加密
         * 参数：boolean 默认为false（不加密）
         */
        UMConfigure.setEncryptEnabled(false);

        //初始化友盟,Key在清单文件写过这里就不在需要了,推送不需要传入空字符串
        UMConfigure.init(this, "5de708d1570df3243e000b63", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");

        //三方登录
        PlatformConfig.setWeixin("wxfb8381274ed0fee4", "c8572c6ef231a4495703d96f0fec8e1c");
        PlatformConfig.setQQZone("1110049772", "NbnAvFzVYpJoiPiB");//
        PlatformConfig.setSinaWeibo("353419546", "a091c5a7c817086d9c7b1b5fc654810f", "https://api.weibo.com/oauth2/default.html");


    }


}
