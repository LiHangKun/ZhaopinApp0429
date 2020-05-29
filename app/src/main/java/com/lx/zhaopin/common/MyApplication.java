package com.lx.zhaopin.common;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.awen.photo.FrescoImageLoader;
import com.lx.zhaopin.R;
import com.lx.zhaopin.rong.MyExtensionModule;
import com.lx.zhaopin.rongmessage.Custome01Message;
import com.lx.zhaopin.rongmessage.Custome01MessageItemProvider;
import com.lx.zhaopin.rongmessage.Custome03Message;
import com.lx.zhaopin.rongmessage.Custome03MessageItemProvider;
import com.lx.zhaopin.rongmessage.Custome04Message;
import com.lx.zhaopin.rongmessage.Custome04MessageItemProvider;
import com.lx.zhaopin.rongmessage.Custome05Message;
import com.lx.zhaopin.rongmessage.Custome05MessageItemProvider;
import com.lx.zhaopin.rongmessage.Custome1Message;
import com.lx.zhaopin.rongmessage.Custome1MessageItemProvider;
import com.lx.zhaopin.rongmessage.Custome3Message;
import com.lx.zhaopin.rongmessage.Custome3MessageItemProvider;
import com.lx.zhaopin.rongmessage.Custome4Message;
import com.lx.zhaopin.rongmessage.Custome4MessageItemProvider;
import com.lx.zhaopin.rongmessage.Custome5Message;
import com.lx.zhaopin.rongmessage.Custome5MessageItemProvider;
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

import java.util.List;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.sight.SightExtensionModule;

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


        //------TODO-------融云部分------
        RongIM.init(this, AppSP.RongToken);//融云

        //发送简历：RCD:SendJianLiMsg    ---->   JianLiMsg    1
        //接收简历：RCD:ReceiveJianLiMsg
        //拒绝接收简历：RCD:RefuseJianLiMsg
        //面试邀约：RCD:SendMianShiMsg      ---->   MianShiMsg  4
        //接受面试：RCD:ReceiveMianShiMsg
        //拒绝面试：RCD:RefuseMianShiMsg
        //求职者取消面试：RCD:CancleMianShiMsg
        //HR取消面试：RCD:HRCancleMianShiMsg

        //HR 看到求职者给我发送的一份求职简历,HR的操作是拒绝或者同意
        RongIM.registerMessageType(Custome1Message.class);//简历：RCD:SendJianLiMsg
        RongIM.registerMessageTemplate(new Custome1MessageItemProvider());
        RongIM.registerMessageType(Custome01Message.class);//接收简历：RCD:ReceiveJianLiMsg
        RongIM.registerMessageTemplate(new Custome01MessageItemProvider());
        //---------TODO 自定义消息1的类型结束--------------


        //所有人的灰底白字的提示 content 就是提示文本类型
        RongIM.registerMessageType(Custome3Message.class);//拒绝接收简历：RCD:RefuseJianLiMsg
        RongIM.registerMessageTemplate(new Custome3MessageItemProvider());
        RongIM.registerMessageType(Custome03Message.class);//RCD:SendMianShiMsg
        RongIM.registerMessageTemplate(new Custome03MessageItemProvider());
        //---------TODO 自定义消息3的类型结束--------------

        //发出面试邀请,点击进入面试详情,拒绝和同意按钮的操作
        RongIM.registerMessageType(Custome4Message.class); //接受面试：RCD:ReceiveMianShiMsg
        RongIM.registerMessageTemplate(new Custome4MessageItemProvider());
        RongIM.registerMessageType(Custome04Message.class);//拒绝面试：RCD:RefuseMianShiMsg
        RongIM.registerMessageTemplate(new Custome04MessageItemProvider());
        //---------TODO 自定义消息3的类型结束--------------


        //发出面试邀请,点击进入面试详情,拒绝和同意按钮的操作
        RongIM.registerMessageType(Custome5Message.class);//求职者取消面试：RCD:CancleMianShiMsg
        RongIM.registerMessageTemplate(new Custome5MessageItemProvider());
        RongIM.registerMessageType(Custome05Message.class);//HR取消面试：RCD:HRCancleMianShiMsg
        RongIM.registerMessageTemplate(new Custome05MessageItemProvider());
        //---------TODO 自定义消息4的类型结束--------------



        setInputProvider();
        RongExtensionManager.getInstance().registerExtensionModule(new SightExtensionModule());

        //------TODO-------融云部分------

        //设置组件化的Log开关  参数: boolean 默认为false，如需查看LOG设置为true
        UMConfigure.setLogEnabled(true);
        //设置日志加密  参数：boolean 默认为false（不加密）
        UMConfigure.setEncryptEnabled(false);

        //初始化友盟,Key在清单文件写过这里就不在需要了,推送不需要传入空字符串
        UMConfigure.init(this, "5de708d1570df3243e000b63", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        //三方登录
        PlatformConfig.setWeixin("wx97944e0b3dab9df1", "c8572c6ef231a4495703d96f0fec8e1c");
        PlatformConfig.setQQZone("1110409775", "2EzUEeVxyRt6PCaO");//
        PlatformConfig.setSinaWeibo("353419546", "a091c5a7c817086d9c7b1b5fc654810f", "https://api.weibo.com/oauth2/default.html");


    }

    private void setInputProvider() {
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                RongExtensionManager.getInstance().registerExtensionModule(new MyExtensionModule());
            }
        }
    }


}
