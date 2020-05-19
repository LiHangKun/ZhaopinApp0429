package com.lx.zhaopin.common;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.lx.zhaopin.R;
import com.lx.zhaopin.bean.QiuZhiZheMyInfoBean;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.ActivityManager;
import com.lx.zhaopin.utils.GaoDeUtils;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.view.ActionDialog;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Request;
import okhttp3.Response;


public class SplashActivity extends AppCompatActivity {


    private static final String TAG = "SplashActivity";
    private ActionDialog actionDialog;
    private Intent intent;
    private Handler handler;

    /**
     * 主题沉浸栏设置透明 * @return
     */
    public void initSystemBar2(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // 透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSystemBar2(this);
        //setTheme(R.style.AppWelcome);
        //setContentView(R.layout.splash_activity);
        //Utility2.setActionBar(this);
        ActivityManager.addActivity(this);
        ImageView imageView = findViewById(R.id.imageView);
        //imageView.setImageResource(R.drawable.yindao1);
        //initLocation();

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        String registrationID = JPushInterface.getRegistrationID(this);
        SPTool.addSessionMap(AppSP.JupshID, registrationID);
        Log.i(TAG, "onCreate: 极光信息" + registrationID);
        handler = new Handler();

        xieYi();

        if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))){
            getQiuZhiMyInfo();
        }



    }



    //求职者个人信息
    private void getQiuZhiMyInfo() {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("hr", "0");
        Log.e(TAG, "getQiuZhiMyInfo: http getEventmessage 个人中心请求" + SPTool.getSessionValue(AppSP.UID) + "---");
        OkHttpHelper.getInstance().post(SplashActivity.this, NetClass.BASE_URL + NetCuiMethod.qiuZhiMyInfo, params, new BaseCallback<QiuZhiZheMyInfoBean>() {
            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onResponse(Response response) {
            }

            @Override
            public void onSuccess(Response response, QiuZhiZheMyInfoBean resultBean) {

                SPTool.addSessionMap(AppSP.USER_NAME, resultBean.getName());
                SPTool.addSessionMap(AppSP.USER_ICON, resultBean.getAvatar());
                SPTool.addSessionMap(AppSP.UID, SPTool.getSessionValue(AppSP.UID));




            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }

    @PermissionGrant(AppSP.PMS_LOCATION)
    public void pmsLocationSuccess() {
        //权限授权成功
    }

    @PermissionDenied(AppSP.PMS_LOCATION)
    public void pmsLocationError() {
        ToastFactory.getToast(SplashActivity.this, "权限被拒绝，无法使用该功能！").show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            MPermissions.requestPermissions(this, AppSP.PMS_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            );
        } else {
            pmsLocationSuccess();
        }



        initLocation();//高德定位
    }

    private void xieYi() {
        actionDialog = new ActionDialog(SplashActivity.this, "温馨提示", "请您在使用前仔细阅读并同意" + "《注册协议》" + "和" + "《隐私政策》" + ",其中的重点条款已为您标注，方便您了解自己的权利。", "不同意", "同意并使用");
        actionDialog.setOnxieyiClickListener(new ActionDialog.OnxieyiClickListener() {
            @Override
            public void onLeftClick() {
                actionDialog.dismiss();
                finish();
            }

            @Override
            public void onRightClick() {
                actionDialog.dismiss();
                SPTool.addSessionMap(AppSP.xieyi, "1");
                actionDialog.dismiss();

                goHome();

            }

            @Override
            public void onZhuce() {
                intent = new Intent(SplashActivity.this, NoticeDetailActivity.class);
                intent.putExtra("title", "用户协议");
                intent.putExtra("titleUrl", NetClass.Web_XieYi1);
                startActivity(intent);
            }

            @Override
            public void onyinsi() {
                intent = new Intent(SplashActivity.this, NoticeDetailActivity.class);
                intent.putExtra("title", "隐私政策");
                intent.putExtra("titleUrl", NetClass.Web_XieYi2);
                startActivity(intent);
            }
        });
        if (SPTool.getSessionValue(AppSP.xieyi).equals("1")) {
            actionDialog.dismiss();
            goHome();
        } else {
            actionDialog.show();
        }
        Log.i(TAG, "onSuccess: " + "走onSuccess");
    }

    private void goHome() {

        final boolean isLogin = SPTool.getSessionValue(AppSP.isLogin, false);

        boolean isFirstIndex = SPTool.getSessionValue(AppSP.isFirstIndex, false);

        if (isFirstIndex) {
            Thread myThread = new Thread() {//创建子线程
                @Override
                public void run() {
                    try {
                        sleep(500);//使程序休眠五秒
                        if (!isLogin) {
                            intent = new Intent(SplashActivity.this, MainActivity.class);//启动MainActivity
                            SPTool.addSessionMap(AppSP.USER_TYPE, "0");
                            startActivity(intent);
                            finish();//关闭当前活动
                        } else {
                            //intent = new Intent(SplashActivity.this, LoginActivity.class);//启动LoginActivity
                            intent = new Intent(SplashActivity.this, MainActivity.class);//启动LoginActivity
                            SPTool.addSessionMap(AppSP.USER_TYPE, "0");
                            startActivity(intent);
                            finish();//关闭当前活动
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            myThread.start();//启动线程
        } else {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    intent = new Intent(SplashActivity.this, GuideActivity.class);//启动MainActivity
                    startActivity(intent);
                    finish();//关闭当前活动
                }
            }, 500);


        }


    }


    /*----------高德定位---------------*/
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();

    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        //设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);

        //locationClient.startLocation();
        startLocation();
    }

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        //根据控件的选择，重新设置定位参数
        //resetOption();
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocation();
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }


    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(10000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }


    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (null != loc) {
                //解析定位结果
                String result = GaoDeUtils.getLocationStr(loc);

                Log.i(TAG, "onLocationChanged: " + result);
            } else {
                Log.i(TAG, "onLocationChanged: 定位失败");
            }
        }
    };


    /*----------高德定位---------------*/


}
