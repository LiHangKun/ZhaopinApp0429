package com.lx.zhaopin.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.ZhiWeiYaoYueInAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.GetGouTongZhiWeiBean;
import com.lx.zhaopin.bean.GongSiZhiWeiBean;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.rongmessage.RongUtil;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.StringUtil;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.utils.Utility;
import com.lx.zhaopin.view.MyDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.URISyntaxException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

public class ZhiWeiYaoYueActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout relView1;
    private RelativeLayout relView2;
    private RelativeLayout relView4;
    private TextView tv0;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView okID;
    private String time1;
    private String yaoYueGangWeiId;
    private String lng;
    private String lat;
    private String rid;
    private static final String TAG = "ZhiWeiYaoYueActivity";
    private String icon;
    private String name;
    private String gongSiID;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.zhiweiyaoyue_activity);
        Utility.setActionBar(this);
        //发送职位邀约
        init();
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = false)
    public void getEventmessage(MessageEvent event) {
        int messageType = event.getMessageType();
        switch (messageType) {
            case 1:
                time1 = event.getKeyWord1();
                pvNoLinkOptions.show();
                break;
            case 9:
                rid = SPTool.getSessionValue("rid");
                Log.i(TAG, "getEventmessage: 取到的信息" + rid);
                setUserInfo(rid);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    private void init() {
        ImageView finishBack = findViewById(R.id.finishBack);
        TextView titleName = findViewById(R.id.titleName);
        finishBack.setImageResource(R.drawable.fanhuibaicui);
        finishBack.setVisibility(View.VISIBLE);
        finishBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleName.setText("发送职位邀约");
        titleName.setTextColor(getResources().getColor(R.color.white));
        getNoLinkData();
        initNoLinkOptionsPicker();

        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }


        //rid = getIntent().getStringExtra("rid");
        rid = SPTool.getSessionValue("rid");
        Log.i(TAG, "getEventmessage: 取到的信息11>" + rid);
        //RenCaiDetailBean renCaiDetailBean = (RenCaiDetailBean) getIntent().getSerializableExtra("renCaiDetailBean");

        relView1 = findViewById(R.id.relView1);
        relView2 = findViewById(R.id.relView2);
        relView4 = findViewById(R.id.relView4);

        relView1.setOnClickListener(this);
        relView2.setOnClickListener(this);
        relView4.setOnClickListener(this);
        tv0 = findViewById(R.id.tv0);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        okID = findViewById(R.id.okID);
        tv3.setOnClickListener(this);
        okID.setOnClickListener(this);


        tv2.setText(SPTool.getSessionValue(AppSP.USER_PHONE));

        //getGongSiInfo(tv3, rid);
        setUserInfo(rid);


        getGongSiAllZhiWeiMid();


    }

    private void setUserInfo(final String userId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("userId", userId);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.getRongUserInfo, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                tv0.setText(resultBean.getName());

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


    private void getGongSiAllZhiWeiMid() {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.gongSiAllZhiWei, params, new BaseCallback<GongSiZhiWeiBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, GongSiZhiWeiBean resultBean) {

                if (resultBean.getDataList() != null) {
                    if (resultBean.getDataList().size() != 0) {
                        //String location = resultBean.getDataList().get(0).getLocation();
                        tv3.setText(resultBean.getDataList().get(0).getCompany().getLocation());
                        lat = resultBean.getDataList().get(0).getCompany().getLat();
                        lng = resultBean.getDataList().get(0).getCompany().getLng();
                        icon = resultBean.getDataList().get(0).getCompany().getLogo();
                        name = resultBean.getDataList().get(0).getCompany().getName();
                        gongSiID = resultBean.getDataList().get(0).getCompany().getId();


                    }
                }


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }

    private void getGongSiInfo(final TextView tv3, String rid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("rid", rid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.getGouTongInfo, params, new BaseCallback<GetGouTongZhiWeiBean>() {

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, GetGouTongZhiWeiBean resultBean) {
                tv3.setText(resultBean.getCompany().getLocation());
                lat = resultBean.getCompany().getLat();
                lng = resultBean.getCompany().getLng();

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }


    private OptionsPickerView pvNoLinkOptions;
    private ArrayList<String> food = new ArrayList<>();
    private ArrayList<String> clothes = new ArrayList<>();
    private ArrayList<String> computer = new ArrayList<>();

    private void initNoLinkOptionsPicker() {// 不联动的多级选项
        pvNoLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                //String str = "food:" + food.get(options1) + "\nclothes:" + clothes.get(options2) + "\ncomputer:" + computer.get(options3);
                String time2 = food.get(options1) + clothes.get(options2);
                tv4.setText(time1 + " " + time2);

                //Toast.makeText(mContext, time2, Toast.LENGTH_SHORT).show();
            }
        })
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                        //提示出的选择的索引                        String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
                        //Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
                    }
                })
                .setItemVisibleCount(5)
                // .setSelectOptions(0, 1, 1)
                .build();
        /*pvNoLinkOptions.setNPicker(food, clothes, computer);
        pvNoLinkOptions.setSelectOptions(0, 1, 1);*/

        pvNoLinkOptions.setNPicker(food, clothes);
        pvNoLinkOptions.setSelectOptions(0, 1);


    }


    private void getNoLinkData() {
        food.add("09点");
        food.add("10点");
        food.add("11点");
        food.add("14点");
        food.add("15点");
        food.add("16点");
        food.add("16点");
        food.add("17点");

        clothes.add("30分");
        clothes.add("30分");
        clothes.add("30分");
        clothes.add("30分");
        clothes.add("30分");
        clothes.add("30分");
        clothes.add("30分");

        computer.add("张三");
        computer.add("李四");
        computer.add("王五");
        computer.add("赵六");
        computer.add("周七");
        computer.add("哈哈");
        computer.add("嘻嘻");
    }


    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    /**
     * 日期选择
     *
     * @param activity
     * @param themeResId
     * @param calendar
     */
    public static void showDatePickerDialog(final Activity activity, int themeResId, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                String time1 = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + "";
                //ToastFactory.getToast(activity, "您选择了：" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日").show();
                //TODO   发送EventBus 展示时间段
                EventBus.getDefault().post(new MessageEvent(1, time1, null, null, null, null, null));

                //pvNoLinkOptions.show();
                //tv.setText("您选择了：" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    DateFormat format = DateFormat.getDateTimeInstance();
    Calendar calendar = Calendar.getInstance(Locale.CHINA);

    private PopupWindow popupWindow1;
    private View popupView1;
    private TranslateAnimation animation1;
    /*在子日记的pop上 点击发布 在次弹出发布页面*/

    /**
     * 设置手机屏幕亮度变暗
     */
    private void lightoff() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().setAttributes(lp);
    }

    /**
     * 设置手机屏幕亮度显示正常
     */
    private void lighton() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1f;
        getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relView1:
                //选择岗位
                AllGangwei();
                lightoff();
                break;
            case R.id.relView2:
                //电话
                View view = getLayoutInflater().inflate(R.layout.dialog_phone_mianshi, null);
                final MyDialog mMyDialog = new MyDialog(mContext, 0, 0, view, R.style.DialogTheme2);
                final EditText edit1 = view.findViewById(R.id.edit1);
                TextView okID = view.findViewById(R.id.okID);
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
                            ToastFactory.getToast(mContext, "电话号码不能为空").show();
                            return;
                        } else if (!StringUtil.isMobileNOCui(edit1.getText().toString().trim())) {
                            ToastFactory.getToast(mContext, "电话号码不正确").show();
                            return;
                        } else {
                            tv2.setText(edit1.getText().toString().trim());
                            mMyDialog.dismiss();
                        }
                    }
                });
                break;
            case R.id.relView4:
                //时间
                //ToastFactory.getToast(mContext, "时间").show();
                showDatePickerDialog(this, 4, calendar);

                break;
            case R.id.okID:
                //发送邀约  yaoQingMianShi
                if (TextUtils.isEmpty(yaoYueGangWeiId)) {
                    ToastFactory.getToast(mContext, "请先选择岗位").show();
                    return;
                } else if (TextUtils.isEmpty(tv2.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "电话号码不能为空").show();
                    return;
                } else if (tv4.getText().toString().trim().startsWith("请")) {
                    ToastFactory.getToast(mContext, "请先选择面试时间").show();
                    return;
                } else {
                    //发布邀约
                    fabuYaoYueMe(rid, yaoYueGangWeiId, tv2.getText().toString().trim(), tv4.getText().toString().trim().replace("点", ":").replace("分", ""));
                }
                break;
            case R.id.tv3:
                //导航
                gotoGaode(lat, lng);
                break;
        }

    }


    //yaoQingMianShi
    private void fabuYaoYueMe(final String rid, String pid, String mobile, String interviewDate) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("rid", rid);
        params.put("pid", pid);
        params.put("mobile", mobile);
        params.put("interviewDate", interviewDate);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.yaoQingMianShi, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {

                RongUtil.faYaoYue(rid, name, icon, SPTool.getSessionValue(AppSP.UID_DUAN), resultBean.getInterviewId());
                ToastFactory.getToast(mContext, resultBean.getResultNote()).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 500);

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }

    private void AllGangwei() {
        if (popupWindow1 == null) {
            popupView1 = View.inflate(this, R.layout.pop_layout_allgangwei_list, null);
            RecyclerView recyclerView = popupView1.findViewById(R.id.recyclerView);
            popupWindow1 = new PopupWindow(popupView1, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    lighton();
                }
            });


            getGongSiAllZhiWei(recyclerView);


            // 设置背景图片， 必须设置，不然动画没作用
            popupWindow1.setBackgroundDrawable(new BitmapDrawable());
            popupWindow1.setFocusable(true);
            // 设置点击popupwindow外屏幕其它地方消失
            popupWindow1.setOutsideTouchable(true);
            // 平移动画相对于手机屏幕的底部开始，X轴不变，Y轴从1变0
            animation1 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
            animation1.setInterpolator(new AccelerateInterpolator());
            animation1.setDuration(200);
            popupView1.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow1.dismiss();
                    lighton();
                }
            });
        }

        // 在点击之后设置popupwindow的销毁
        if (popupWindow1.isShowing()) {
            popupWindow1.dismiss();
            lighton();
        }

        // 设置popupWindow的显示位置，此处是在手机屏幕底部且水平居中的位置
        popupWindow1.showAtLocation(findViewById(R.id.setting), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        //popupWindow1.showAtLocation(findViewById(R.id.setting), Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupView1.startAnimation(animation1);

    }

    private void getGongSiAllZhiWei(final RecyclerView recyclerView) {

        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.gongSiAllZhiWei, params, new BaseCallback<GongSiZhiWeiBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, GongSiZhiWeiBean resultBean) {


                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                ZhiWeiYaoYueInAdapter zhiWeiYaoYueInAdapter = new ZhiWeiYaoYueInAdapter(mContext, resultBean.getDataList());
                recyclerView.setAdapter(zhiWeiYaoYueInAdapter);
                zhiWeiYaoYueInAdapter.setOnItemClickListener(new ZhiWeiYaoYueInAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClickListener(String id, String name) {
                        tv1.setText(name);
                        yaoYueGangWeiId = id;
                        popupWindow1.dismiss();
                    }
                });

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
