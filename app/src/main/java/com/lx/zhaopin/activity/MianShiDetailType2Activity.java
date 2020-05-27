package com.lx.zhaopin.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.MianShiDetailBean;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.rongmessage.RongUtil;
import com.lx.zhaopin.utils.GaoDeUtils;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.TellUtil;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.view.MyDialog;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionGrant;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
import okhttp3.Response;

//求职者--面试详情
public class MianShiDetailType2Activity extends BaseActivity {
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.imageState)
    ImageView imageState;
    @BindView(R.id.roundedImageView)
    RoundedImageView roundedImageView;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.llView1OnClick)
    LinearLayout llView1OnClick;
    @BindView(R.id.llView2OnClick)
    LinearLayout llView2OnClick;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6Click)
    TextView tv6Click;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.quxiaoTV)
    TextView quxiaoTV;
    @BindView(R.id.okID)
    TextView okID;
    @BindView(R.id.qiuZhiView)
    LinearLayout qiuZhiView;
    @BindView(R.id.quxiaoTv)
    TextView quxiaoTv;
    private String lng;
    private String lat;
    private String id;
    private String interviewId2;
    private String phone;

    private static final String TAG = "MianShiDetailType2Activ";

    /*----------高德定位---------------*/
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();
    private String s_sPjing;
    private String s_spWei_sp;
    private String lng_fan;
    private String lat_fan;
    private String hrid;
    private String hrName;

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

    @Override
    protected void onResume() {
        super.onResume();
        s_sPjing = SPTool.getSessionValue(AppSP.sStringJ);
        s_spWei_sp = SPTool.getSessionValue(AppSP.sStringW);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.miaoshidetailtype2_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        topTitle.setText("面试详情");
        /*if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }*/
        interviewId2 = getIntent().getStringExtra("interviewId");

        getMianShiDetail(interviewId2);


    }


    //求职者--面试详情
    private void getMianShiDetail(String interviewId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("interviewId", interviewId);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.mianshiDetail_qiuZhi1, params, new SpotsCallBack<MianShiDetailBean>(mContext) {
            @Override
            public void onSuccess(Response response, MianShiDetailBean resultBean) {
                tv1.setText(resultBean.getCompany().getName());
                tv2.setText(resultBean.getInterviewDate() + " 面试");
                hrid = resultBean.getHRID();
                hrName = resultBean.getHRName();
                Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                        .error(R.mipmap.imageerror)).load(resultBean.getCompany().getLogo()).into(roundedImageView);

                lat_fan = resultBean.getLat();
                lng_fan = resultBean.getLng();

                id = resultBean.getId();
                phone = resultBean.getMobile();
                String interviewStatus = resultBean.getInterviewStatus();
                tv3.setVisibility(View.GONE);
                //1 待接受  2 已拒绝 3 待面试 4 已超时 5 已到达 6 已取消 7 已录取 8 不合适
                switch (interviewStatus) {
                    case "1":
                        imageState.setImageResource(R.drawable.daitongyi);
                        qiuZhiView.setVisibility(View.GONE);
                        break;
                    case "2":
                        imageState.setImageResource(R.drawable.yijujue);
                        qiuZhiView.setVisibility(View.GONE);
                        break;
                    case "3":
                        imageState.setImageResource(R.drawable.daimianshi);
                        qiuZhiView.setVisibility(View.VISIBLE);
                        break;
                    case "4":
                        imageState.setImageResource(R.drawable.yichaoshi);
                        qiuZhiView.setVisibility(View.GONE);
                        break;
                    case "5":
                        imageState.setImageResource(R.drawable.yidaoda);
                        qiuZhiView.setVisibility(View.GONE);
                        break;
                    case "6":
                        imageState.setImageResource(R.drawable.yiquxiao);
                        tv3.setText("取消原因:" + resultBean.getCancelReason());
                        tv3.setVisibility(View.VISIBLE);
                        qiuZhiView.setVisibility(View.GONE);
                        break;
                    case "7":
                        imageState.setImageResource(R.drawable.yitongyi);
                        qiuZhiView.setVisibility(View.GONE);
                        break;
                    case "8":
                        imageState.setImageResource(R.drawable.buheshi);
                        tv3.setText("不合适原因:" + resultBean.getDenyReason());
                        tv3.setVisibility(View.VISIBLE);
                        qiuZhiView.setVisibility(View.GONE);
                        break;
                }

                tv4.setText(resultBean.getInterviewDate());
                tv5.setText(resultBean.getPosition().getName());
                tv6Click.setText(resultBean.getLocation());


                if (!TextUtils.isEmpty(resultBean.getRemarks())) {
                    tv7.setText(resultBean.getRemarks());
                } else {
                    tv7.setText("暂无备注信息");
                }

                MianShiDetailType2Activity.this.lat = resultBean.getLat();
                lng = resultBean.getLng();

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }


    private static double EARTH_RADIUS = 6378.137;//地球半径

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;//单位千米
        Log.e("s", "s=" + s);
        return s;
    }


    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @PermissionGrant(AppSP.PMS_CALL_PHONE)
    public void pmsLocationSuccess() {
        //权限授权成功
        TellUtil.tell(mContext, phone);
    }

    /*拨打电话*/
    private void callPhone() {
        if (null != phone) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                MPermissions.requestPermissions(this, AppSP.PMS_CALL_PHONE, Manifest.permission.CALL_PHONE);
            } else {
                pmsLocationSuccess();
            }
        }
    }


    private void goLiaoTianMethod() {
        String userId = SPTool.getSessionValue(AppSP.UID);
        String nickName = SPTool.getSessionValue(AppSP.USER_NAME);
        String userHead = SPTool.getSessionValue(AppSP.USER_ICON);

        Log.i(TAG, "onClick: " + userId + "<>" + nickName + "<>" + userHead);
        if (null != userId && null != nickName && null != userHead)
            RongIM.getInstance().setCurrentUserInfo(new UserInfo(userId, nickName, Uri.parse(userHead)));
        RongIM.getInstance().setMessageAttachedUserInfo(true);
        //对方的ID 姓名
        //RongIM.getInstance().startPrivateChat(mContext, hrid, "张三");
        RongIM.getInstance().startConversation(mContext, Conversation.ConversationType.PRIVATE, hrid, hrName);
    }

    @OnClick({R.id.llView1OnClick, R.id.llView2OnClick, R.id.tv6Click, R.id.quxiaoTV, R.id.okID, R.id.qiuZhiView, R.id.quxiaoTv})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llView1OnClick:
                //ToastFactory.getToast(mContext, "聊天").show();
                goLiaoTianMethod();
                break;
            case R.id.llView2OnClick:
                if (!TextUtils.isEmpty(phone)) {
                    callPhone();
                }
                break;
            case R.id.tv6Click:
                //ToastFactory.getToast(mContext, "导航").show();
                gotoGaode(lat, lng);
                break;
            case R.id.okID:
                String JuliMI = String.valueOf(GetDistance(Double.parseDouble(s_spWei_sp), Double.parseDouble(s_sPjing), Double.parseDouble(lat_fan), Double.parseDouble(lng_fan)));
                BigDecimal num1 = new BigDecimal(JuliMI);
                BigDecimal num2 = new BigDecimal(AppSP.DaKaMi);
                int i = num1.compareTo(num2);
                Log.i(TAG, "onClick: 距离" + JuliMI + "对比" + i);

                if (i < 0) {
                    //我已到达
                    //qiuZheZheDaoDa
                    myDaoDa(interviewId2);
                } else {
                    ToastFactory.getToast(mContext, "您不在公司附近").show();
                    return;
                }


                break;
            case R.id.qiuZhiView:
                //这个不要了
                break;
            case R.id.quxiaoTV:
                //取消面试的弹框
                View view = getLayoutInflater().inflate(R.layout.dialog_quxiao_mianshi, null);
                final MyDialog mMyDialog = new MyDialog(mContext, 0, 0, view, R.style.DialogTheme2);
                final EditText edit1 = view.findViewById(R.id.edit1);
                mMyDialog.setCancelable(true);
                mMyDialog.show();

                view.findViewById(R.id.quxiaoTV).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMyDialog.dismiss();
                    }
                });

                view.findViewById(R.id.okID).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                            ToastFactory.getToast(mContext, "取消原因不能为空").show();
                            return;
                        } else {
                            quXiaoMianShiMe(id, edit1.getText().toString().trim());
                            mMyDialog.dismiss();
                        }
                    }
                });
        }
    }


    private void myDaoDa(String interviewId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("interviewId", interviewId);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.qiuZheZheDaoDa, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                getMianShiDetail(interviewId2);
                EventBus.getDefault().post(new MessageEvent(2, null, null, null, null, null, null));


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    //取消面试
    private void quXiaoMianShiMe(String interviewId, String cancelReason) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("interviewId", interviewId);
        params.put("cancelReason", cancelReason);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.quXiaoMianShi_Type1, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                RongUtil.qiuZhiQuXiao(hrid, interviewId2);
                EventBus.getDefault().post(new MessageEvent(10, null, null, null, null, null, null));
                ToastFactory.getToast(mContext, resultBean.getResultNote()).show();
                getMianShiDetail(interviewId2);

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });


    }


    /*打开高德导航*/
    private void gotoGaode(String lat, String lon) {
        if (isAvilible(mContext, "com.autonavi.minimap")) {
            try {
                //116.304521,40.003865
                Intent intent = Intent.getIntent("androidamap://navi?sourceApplication=慧医&poiname=我的目的地&lat=" + lat + "&lon=" + lon + "&dev=0");
                startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(mContext, "您尚未安装高德地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

    }


    /* 检查手机上是否安装了指定的软件
     * @param context
     * @param packageName：应用包名
     * @return
     */
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

}
