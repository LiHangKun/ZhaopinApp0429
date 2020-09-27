package com.lx.zhaopin.common;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.view.ActionDialog;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Request;
import okhttp3.Response;


public class SplashActivity extends AppCompatActivity implements AMapLocationListener {


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
        setContentView(R.layout.splash_activity);
        //Utility2.setActionBar(this);
        ActivityManager.addActivity(this);
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.mipmap.qidong0);
        //initLocation();

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        String registrationID = JPushInterface.getRegistrationID(this);
        SPTool.addSessionMap(AppSP.JupshID, registrationID);
        Log.i(TAG, "onCreate: 极光信息" + registrationID);
        handler = new Handler();

        xieYi();

        if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
            getQiuZhiMyInfo();
        }


        //声明mLocationOption对象
        AMapLocationClientOption mLocationOption = null;
        mlocationClient = new AMapLocationClient(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mlocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        mLocationOption.setOnceLocationLatest(true);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位


        if (ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
            mlocationClient.startLocation();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 200://刚才的识别码
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作
                    mlocationClient.startLocation();
                } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                    Toast.makeText(SplashActivity.this, "未开启定位权限,请手动到设置去开启权限", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                SPTool.addSessionMap(AppSP.sCity, amapLocation.getCity());
                Log.i(TAG, "onLocationChanged: hahahhahhah" + amapLocation.getAddress());

                String city = amapLocation.getCity();
                double latitude = amapLocation.getLatitude();
                double longitude = amapLocation.getLongitude();
                String address = amapLocation.getAddress();


                SPTool.addSessionMap(AppSP.sStringJ, Double.toString(amapLocation.getLongitude()));
                SPTool.addSessionMap(AppSP.sStringW, Double.toString(amapLocation.getLatitude()));

                Log.i(TAG, "onLocationChanged: " + city + "----" + latitude + "---" + longitude + "---" + address);

            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:" + amapLocation.getErrorCode() + ", errInfo:" + amapLocation.getErrorInfo());
            }
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
                SPTool.addSessionMap(AppSP.sex, resultBean.getSex());


            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();


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
                            //SPTool.addSessionMap(AppSP.USER_TYPE, "0");
                            SPTool.addSessionMap(AppSP.USER_TYPE, SPTool.getSessionValue(AppSP.USER_TYPE));
                            startActivity(intent);
                            finish();//关闭当前活动
                        } else {
                            //intent = new Intent(SplashActivity.this, LoginActivity.class);//启动LoginActivity
                            intent = new Intent(SplashActivity.this, MainActivity.class);//启动LoginActivity
                            //SPTool.addSessionMap(AppSP.USER_TYPE, "0");
                            SPTool.addSessionMap(AppSP.USER_TYPE, SPTool.getSessionValue(AppSP.USER_TYPE));
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

    //TODO---------------------


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocation();
    }

    private void stopLocation() {
        // 停止定位
        mlocationClient.stopLocation();
    }

    private AMapLocationClient mlocationClient;
    // 要申请的权限
    private String[] permissions = {
            android.Manifest.permission.LOCATION_HARDWARE,//位置
            android.Manifest.permission.ACCESS_FINE_LOCATION,//位置
            android.Manifest.permission.ACCESS_COARSE_LOCATION,//位置
    };
    //权限数组下标
    //权限申请返回码
    private int requestCodePre = 321;
    //系统设置权限申请返回码
    private int requestCodeSer = 123;

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    //TODO---------------------


    /*----------高德定位---------------*/


    /*----------高德定位---------------*/


}
