package com.lx.zhaopin.common;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.lx.zhaopin.R;
import com.lx.zhaopin.utils.ActivityManager;
import com.lx.zhaopin.utils.GaoDeUtils;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import java.util.ArrayList;


/*引导页*/
public class GuideActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private int[] mImageIds = new int[]{R.mipmap.qidong1, R.mipmap.qidong2, R.mipmap.qidong3};
    private ArrayList<ImageView> mImageViewList;
    private LinearLayout llContainer;
    private ImageView ivRedPoint;
    private int mPaintDis;
    private Button start_btn;
    private boolean isFirstIndex;

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
        setContentView(R.layout.guide_activity);
        ActivityManager.addActivity(this);
        /*isFirstIndex = SPTool.getSessionValue(DanShopSP.isFirstIndex, false);
        if (isFirstIndex) {
            startActivity(new Intent(GuideActivity.this, SplashActivity.class));
            finish();
        } else {
            initView();
        }*/

        initView();


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

    /*----------高德定位---------------*/
    private static final String TAG = "GuideActivity";

    @PermissionGrant(AppSP.PMS_LOCATION)
    public void pmsLocationSuccess() {
        //权限授权成功
    }

    @PermissionDenied(AppSP.PMS_LOCATION)
    public void pmsLocationError() {
        ToastFactory.getToast(GuideActivity.this, "权限被拒绝，无法使用该功能！").show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

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


    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        llContainer = (LinearLayout) findViewById(R.id.ll_container);
        ivRedPoint = (ImageView) findViewById(R.id.iv_red);
        start_btn = (Button) findViewById(R.id.start_btn);

        start_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //点击进入的时候直接跳转到登录界面
                SPTool.addSessionMap(AppSP.isFirstIndex, true);
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                SPTool.addSessionMap(AppSP.USER_TYPE, "0");
                startActivity(intent);
                finish();
            }
        });

        initData();
        GuideAdapter adapter = new GuideAdapter();
        //添加动画效果
        //mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setAdapter(adapter);

        //监听布局是否已经完成  布局的位置是否已经确定
        ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //避免重复回调        出于兼容性考虑，使用了过时的方法
                ivRedPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                //布局完成了就获取第一个小灰点和第二个之间left的距离
                mPaintDis = llContainer.getChildAt(1).getLeft() - llContainer.getChildAt(0).getLeft();
                System.out.println("距离：" + mPaintDis);
            }
        });


        //ViewPager滑动Pager监听
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滑动过程中的回调
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //当滑到第二个Pager的时候，positionOffset百分比会变成0，position会变成1，所以后面要加上position*mPaintDis
                int letfMargin = (int) (mPaintDis * positionOffset) + position * mPaintDis;
                //在父布局控件中设置他的leftMargin边距
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivRedPoint.getLayoutParams();
                params.leftMargin = letfMargin;
                ivRedPoint.setLayoutParams(params);
            }


            /**
             * 设置按钮最后一页显示，其他页面隐藏
             * @param position
             */
            @Override
            public void onPageSelected(int position) {
                System.out.println("position:" + position);
                if (position == mImageViewList.size() - 1) {
                    start_btn.setVisibility(View.VISIBLE);
                } else {
                    start_btn.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.println("state:" + state);
            }
        });

    }

    private void initData() {
        mImageViewList = new ArrayList<>();
        /*for (int i = 0; i < mImageIds.length; i++) {
            //创建ImageView把mImgaeViewIds放进去
            ImageView view = new ImageView(this);
            view.setBackgroundResource(mImageIds[i]);
            //添加到ImageView的集合中
            mImageViewList.add(view);
            //小圆点
            ImageView pointView = new ImageView(this);
            pointView.setImageResource(R.drawable.shape_point1);
            //初始化布局参数，父控件是谁，就初始化谁的布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                //当添加的小圆点的个数超过一个的时候就设置当前小圆点的左边距为20dp;
                params.leftMargin = 20;
            }
            //设置小灰点的宽高包裹内容
            pointView.setLayoutParams(params);
            //将小灰点添加到LinearLayout中
            llContainer.addView(pointView);

        }*/


        for (int i = 0; i < mImageIds.length; i++) {
            //创建ImageView把mImgaeViewIds放进去
            ImageView view = new ImageView(this);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setImageResource(mImageIds[i]);
            //添加到ImageView的集合中
            mImageViewList.add(view);
            //小圆点
            ImageView pointView = new ImageView(this);
            pointView.setImageResource(R.drawable.shape_point1);
            //初始化布局参数，父控件是谁，就初始化谁的布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                //当添加的小圆点的个数超过一个的时候就设置当前小圆点的左边距为20dp;
                params.leftMargin = 20;
            }
            //设置小灰点的宽高包裹内容
            pointView.setLayoutParams(params);
            //将小灰点添加到LinearLayout中
            llContainer.addView(pointView);

        }


    }

    class GuideAdapter extends PagerAdapter {

        //item的个数
        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //初始化item布局
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = mImageViewList.get(position);
            container.addView(view);
            return view;
        }

        //销毁item
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
