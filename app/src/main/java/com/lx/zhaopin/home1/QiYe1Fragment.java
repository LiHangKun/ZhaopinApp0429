package com.lx.zhaopin.home1;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.PingJiaImageAdapter;
import com.lx.zhaopin.bean.QiYeInfoBean;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.ToastFactory;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.ImageViewerPopupView;
import com.lxj.xpopup.interfaces.OnSrcViewUpdateListener;
import com.lxj.xpopup.interfaces.XPopupImageLoader;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Request;
import okhttp3.Response;

public class QiYe1Fragment extends Fragment {

    private static final String TAG = "QiYe1Fragment";
    private static String shopJiaID;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.daoHang)
    LinearLayout daoHang;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6)
    TextView tv6;
    Unbinder unbinder;
    private String lat;
    private String lng;
    private String jutiStrAdd;

    public static Fragment newInstance(String id) {
        QiYe1Fragment fragment = new QiYe1Fragment();
        shopJiaID = id;
        Log.i(TAG, "newInstance: 企业ID" + shopJiaID);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.qiye1fragment_layout, null);
        getQiYeInfo(shopJiaID);
        unbinder = ButterKnife.bind(this, view);

        return view;

    }

    private List<String> images = new ArrayList<>();

    private void getQiYeInfo(String cid) {
        Map<String, String> params = new HashMap<>();
        params.put("cid", cid);
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.companyInfo, params, new BaseCallback<QiYeInfoBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, QiYeInfoBean resultBean) {
                tv1.setText(resultBean.getIntro());
                tv2.setText(resultBean.getCity().getName() + resultBean.getLocation());
                lat = resultBean.getLat();
                lng = resultBean.getLng();
                jutiStrAdd = resultBean.getLocation();
                tv3.setText(resultBean.getName());
                tv4.setText(resultBean.getFund());
                tv5.setText(resultBean.getLegalPerson());
                tv6.setText(resultBean.getService());


                String imagesLen = resultBean.getImages();
                if (imagesLen.contains("|")) {
                    String[] split = imagesLen.split("\\|");
                    for (int i = 0; i < split.length; i++) {
                        images.add(split[i]);
                    }
                } else {
                    images.add(imagesLen);
                }


                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//
                recyclerView.setLayoutManager(linearLayoutManager);
                PingJiaImageAdapter pingJiaImageAdapter = new PingJiaImageAdapter(getActivity(), images);
                recyclerView.setAdapter(pingJiaImageAdapter);
                pingJiaImageAdapter.setOnItemClickListener(new PingJiaImageAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClickListener(int position) {
                        showImage(new ImageView(getActivity()), position);
                    }
                });

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


    private void showImage(final ImageView iv, int position) {
        List<Object> urls = new ArrayList<>();
        urls.addAll(images);
        new XPopup.Builder(getActivity()).asImageViewer(iv, position, urls, new OnSrcViewUpdateListener() {
            @Override
            public void onSrcViewUpdate(ImageViewerPopupView popupView, int position) {
                popupView.updateSrcView(iv);
            }
        }, new ImageLoader())
                .show();
    }

    class ImageLoader implements XPopupImageLoader {
        @Override
        public void loadImage(int position, @NonNull Object url, @NonNull ImageView imageView) {
            //必须指定Target.SIZE_ORIGINAL，否则无法拿到原图，就无法享用天衣无缝的动画
            Glide.with(imageView).load(url).apply(new RequestOptions().placeholder(R.mipmap.imageerror).override(Target.SIZE_ORIGINAL)).into(imageView);
            //Picasso.with(mContext).load((String) url).memoryPolicy(MemoryPolicy.NO_CACHE).fit().into(imageView);

        }

        @Override
        public File getImageFile(@NonNull Context context, @NonNull Object uri) {
            try {
                return Glide.with(context).downloadOnly().load(uri).submit().get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.daoHang)
    public void onClick() {
        //导航
        //gotoGaode(lng, lat);
        fabuMethod3();
        lightoff();
    }


    private PopupWindow popupWindow3;
    private View popupView3;
    private TranslateAnimation animation3;

    /**
     * 设置手机屏幕亮度变暗
     */
    private void lightoff() {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.3f;
        getActivity().getWindow().setAttributes(lp);
    }

    /**
     * 设置手机屏幕亮度显示正常
     */
    private void lighton() {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 1f;
        getActivity().getWindow().setAttributes(lp);
    }

    //TODO---------------------------------------------------------------
    private void fabuMethod3() {
        if (popupWindow3 == null) {
            popupView3 = View.inflate(getActivity(), R.layout.pop_layout_dao_layout, null);
            TextView tv1Click = popupView3.findViewById(R.id.tv1);
            TextView tv2Click = popupView3.findViewById(R.id.tv2);
            TextView tv3Click = popupView3.findViewById(R.id.tv3);

            // 参数2,3：指明popupwindow的宽度和高度
            popupWindow3 = new PopupWindow(popupView3, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            popupWindow3.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    lighton();
                }
            });

            //一周
            tv1Click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv4.setText("高德地图");
                    gaoDe(getActivity(), lat, lng, jutiStrAdd);
                    popupWindow3.dismiss();
                    lighton();
                }
            });
            //半个月
            tv2Click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv4.setText("百度地图");
                    goToBaiduActivity(getActivity(), jutiStrAdd, lat, lng);
                    popupWindow3.dismiss();
                    lighton();
                }
            });
            //一个月
            tv3Click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv4.setText("腾讯地图");
                    gotoTengxun(getActivity(), jutiStrAdd, lat, lng);
                    popupWindow3.dismiss();
                    lighton();
                }
            });


            // 设置背景图片， 必须设置，不然动画没作用
            popupWindow3.setBackgroundDrawable(new BitmapDrawable());
            popupWindow3.setFocusable(true);

            // 设置点击popupwindow外屏幕其它地方消失
            popupWindow3.setOutsideTouchable(true);

            // 平移动画相对于手机屏幕的底部开始，X轴不变，Y轴从1变0
            animation3 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);

            animation3.setInterpolator(new AccelerateInterpolator());
            animation3.setDuration(200);

            popupView3.findViewById(R.id.tvCancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow3.dismiss();
                    lighton();
                }
            });
            popupView3.findViewById(R.id.cancelImage).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow3.dismiss();
                    lighton();
                }
            });
        }

        // 在点击之后设置popupwindow的销毁
        if (popupWindow3.isShowing()) {
            popupWindow3.dismiss();
            lighton();
        }

        // 设置popupWindow的显示位置，此处是在手机屏幕底部且水平居中的位置
        popupWindow3.showAtLocation(getActivity().findViewById(R.id.setting), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        //popupWindow1.showAtLocation(findViewById(R.id.setting), Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupView3.startAnimation(animation3);

    }


    //腾讯地图
    private static void gotoTengxun(Context context, String address, String latStr, String lonStr) {
        if (isAvilible(context, "com.tencent.map")) {
            double lat = 0, lon = 0;
            if (!TextUtils.isEmpty(latStr)) {
                lat = Double.parseDouble(latStr);
            }
            if (!TextUtils.isEmpty(lonStr)) {
                lon = Double.parseDouble(lonStr);
            }
            // 启动路径规划页面
            Intent naviIntent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("qqmap://map/routeplan?type=drive&from=&fromcoord=&to=" + address + "&tocoord=" + lat + "," + lon + "&policy=0&referer=appName"));
            context.startActivity(naviIntent);
        } else {
            ToastFactory.getToast(context, "您尚未安装腾讯地图").show();
            Uri uri = Uri.parse("market://details?id=com.tencent.map");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }
    }

    //百度地图
    private static void goToBaiduActivity(Context context, String address, String latStr, String lonStr) {
        if (isAvilible(context, "com.baidu.BaiduMap")) {
            double lat = 0, lon = 0;
            if (!TextUtils.isEmpty(latStr)) {
                lat = Double.parseDouble(latStr);
            }
            if (!TextUtils.isEmpty(lonStr)) {
                lon = Double.parseDouble(lonStr);
            }

            //启动路径规划页面
            String url = "baidumap://map/direction?origin=我的位置&destination=" + address + "&mode=driving&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end";
            Intent naviIntent = new Intent("android.intent.action.VIEW", android.net.Uri.parse(url));
            context.startActivity(naviIntent);
        } else {
            ToastFactory.getToast(context, "您尚未安装百度地图").show();
            Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }
    }


    //高德地图
    private static void gaoDe(Context mContext, String lat, String lng, String adr) {
        if (isAvilible(mContext, "com.autonavi.minimap")) {
            try {
                String url = "amapuri://route/plan/?sid=BGVIS1&slat=&slon=&sname=&did=&dlat=" + lat + "&dlon=" + lng + "&dname=" + adr + "&dev=0&t=0";
                Intent intent = new Intent("android.intent.action.VIEW", android.net.Uri.parse(url));
                mContext.startActivity(intent);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(mContext, "您尚未安装高德地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            mContext.startActivity(intent);
        }
    }





    /*打开高德导航*/
    private void gotoGaode(String lat, String lon) {
        if (isAvilible(getActivity(), "com.autonavi.minimap")) {
            try {
                //116.304521,40.003865
                Intent intent = Intent.getIntent("androidamap://navi?sourceApplication=慧医&poiname=我的目的地&lat=" + lat + "&lon=" + lon + "&dev=0");
                startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "您尚未安装高德地图", Toast.LENGTH_LONG).show();
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
