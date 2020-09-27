package com.lx.zhaopin.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.QiuZhiYiXiangAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.QiuZhiyiXiangBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.other.WheelDialog;
import com.lx.zhaopin.utils.SPTool;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import okhttp3.Response;

public class QiuZhiYiXiangActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.llView1)
    LinearLayout llView1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.llView2)
    LinearLayout llView2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.llView3)
    LinearLayout llView3;
    @BindView(R.id.add_qiuzhi_view)
    RelativeLayout addQiuzhiView;
    private List<QiuZhiyiXiangBean.ResumeExpectationListBean> allList;
    private QiuZhiYiXiangAdapter qiuZhiYiXiangAdapter;
    private WheelDialog workStateDialog;
    private WheelDialog workArrivalDialog;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.qiuzhiyixiang_activity);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    private void init() {
        topTitle.setText("求职意向");
        rightNextParentText.setVisibility(View.VISIBLE);
        rightNextParentText.setTextSize(14);
        rightNextParentText.setTextColor(Color.parseColor("#151413"));
        setRightTextNum(0);
        getDataList();

        allList = new ArrayList<>();
        qiuZhiYiXiangAdapter = new QiuZhiYiXiangAdapter(mContext, allList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(qiuZhiYiXiangAdapter);
        qiuZhiYiXiangAdapter.setOnItemClickener(new QiuZhiYiXiangAdapter.OnItemClickener() {
            @Override
            public void onItemClick(String id) {
                Intent intent = new Intent(mContext, QiuZhiQiWangActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }


    }

    private void setRightTextNum(int num) {
        String str = num + "/3";
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#1678FF")), 0, 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        rightNextParentText.setText(spannableString);
    }

    private static final String TAG = "QiuZhiYiXiangActivity";

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = false)
    public void getEventmessage(MessageEvent event) {
        int messageType = event.getMessageType();
        switch (messageType) {
            case 8:
                getDataList();
                Log.i(TAG, "getEventmessage: 刷新求职意向");
                break;
        }
    }

    private void getDataList() {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.qiuZhiYiXiang, params, new SpotsCallBack<QiuZhiyiXiangBean>(mContext) {
            @Override
            public void onSuccess(Response response, QiuZhiyiXiangBean resultBean) {

                if (resultBean.getResumeExpectationList() != null) {
                    if (resultBean.getResumeExpectationList().size() != 0) {
                        setRightTextNum(resultBean.getResumeExpectationList().size());
                        allList.clear();
                        allList.addAll(resultBean.getResumeExpectationList());
                        qiuZhiYiXiangAdapter.notifyDataSetChanged();
                    } else {
                        setRightTextNum(0);
                    }
                }


                String jobNature = resultBean.getJobNature();
                //工作性质 1.全职，2.兼职
                switch (jobNature) {
                    case "1":
                        tv1.setText("全职");
                        break;
                    case "2":
                        tv1.setText("兼职");
                        break;
                }

                String jobStatus = resultBean.getJobStatus();
                //工作状态 1.离职-随时到岗，2.在职-月内到岗 3.在职-考虑机会 4.在职-暂不考虑
                switch (jobStatus) {
                    case "1":
                        tv2.setText("离职-随时到岗");
                        break;
                    case "2":
                        tv2.setText("在职-月内到岗");
                        break;
                    case "3":
                        tv2.setText("在职-考虑机会");
                        break;
                    case "4":
                        tv2.setText("在职-暂不考虑");
                        break;
                }


                String arrivalTime = resultBean.getArrivalTime();
                //到岗时间 1.一周 2.半个月 3.一个月
                switch (arrivalTime) {
                    case "1":
                        tv3.setText("一周");
                        break;
                    case "2":
                        tv3.setText("半个月");
                        break;
                    case "3":
                        tv3.setText("一个月");
                        break;
                }


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }


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

    // 8342740fa1fe419dac350c34b6031adf
    @OnClick({R.id.llView1, R.id.llView2, R.id.llView3, R.id.add_qiuzhi_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llView1:
                //工作性质
                fabuMethod1();
                lightoff();
                break;
            case R.id.llView2:
                //我的状态
                fabuMethod2();
                break;
            case R.id.llView3:
                //到岗时间
                fabuMethod3();
                break;
            case R.id.add_qiuzhi_view:
                Intent intent = new Intent(this, QiuZhiYiXiangAddActivity.class);
                startActivity(intent);
                break;
        }
    }


    private PopupWindow popupWindow1;
    private View popupView1;
    private TranslateAnimation animation1;


    //TODO---------------------------------------------------------------
    private void fabuMethod1() {
        if (popupWindow1 == null) {
            popupView1 = View.inflate(this, R.layout.pop_layout_ben1_layout, null);
            TextView tv1Click = popupView1.findViewById(R.id.tv1);
            TextView tv2Click = popupView1.findViewById(R.id.tv2);

            // 参数2,3：指明popupwindow的宽度和高度
            popupWindow1 = new PopupWindow(popupView1, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    lighton();
                }
            });

            //全职
            tv1Click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv1.setText("全职");
                    editYiXiangMe("1", "1");
                    popupWindow1.dismiss();
                    lighton();
                }
            });
            //兼职
            tv2Click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv1.setText("兼职");
                    editYiXiangMe("1", "2");
                    popupWindow1.dismiss();
                    lighton();
                }
            });


            // 设置背景图片， 必须设置，不然动画没作用
            popupWindow1.setBackgroundDrawable(new BitmapDrawable());
            popupWindow1.setFocusable(true);

            // 设置点击popupwindow外屏幕其它地方消失
            popupWindow1.setOutsideTouchable(true);

            // 平移动画相对于手机屏幕的底部开始，X轴不变，Y轴从1变0
            animation1 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);

            animation1.setInterpolator(new AccelerateInterpolator());
            animation1.setDuration(200);

            popupView1.findViewById(R.id.tvCancel).setOnClickListener(new View.OnClickListener() {
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


    //------------------------------------------------------
    private PopupWindow popupWindow2;
    private View popupView2;
    private TranslateAnimation animation2;


    //TODO---------------------------------------------------------------
    private void fabuMethod2() {
//        if (popupWindow2 == null) {
//            popupView2 = View.inflate(this, R.layout.pop_layout_ben2_layout, null);
//            TextView tv1Click = popupView2.findViewById(R.id.tv1);
//            TextView tv2Click = popupView2.findViewById(R.id.tv2);
//            TextView tv3Click = popupView2.findViewById(R.id.tv3);
//            TextView tv4Click = popupView2.findViewById(R.id.tv4);
//
//            // 参数2,3：指明popupwindow的宽度和高度
//            popupWindow2 = new PopupWindow(popupView2, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//            popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
//                @Override
//                public void onDismiss() {
//                    lighton();
//                }
//            });
//
//            //离职-随时到岗
//            tv1Click.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    tv3.setText("离职-随时到岗");
//                    editYiXiangMe("2", "1");
//                    popupWindow2.dismiss();
//                    lighton();
//                }
//            });
//            //在职-月内到岗
//            tv2Click.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    tv3.setText("在职-月内到岗");
//                    editYiXiangMe("2", "2");
//                    popupWindow2.dismiss();
//                    lighton();
//                }
//            });
//            //在职-考虑机会
//            tv3Click.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    tv3.setText("在职-考虑机会");
//                    editYiXiangMe("2", "3");
//                    popupWindow2.dismiss();
//                    lighton();
//                }
//            });
//            //在职-暂不考虑
//            tv4Click.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    tv3.setText("在职-暂不考虑");
//                    editYiXiangMe("2", "4");
//                    popupWindow2.dismiss();
//                    lighton();
//                }
//            });
//
//
//            // 设置背景图片， 必须设置，不然动画没作用
//            popupWindow2.setBackgroundDrawable(new BitmapDrawable());
//            popupWindow2.setFocusable(true);
//
//            // 设置点击popupwindow外屏幕其它地方消失
//            popupWindow2.setOutsideTouchable(true);
//
//            // 平移动画相对于手机屏幕的底部开始，X轴不变，Y轴从1变0
//            animation2 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
//
//            animation2.setInterpolator(new AccelerateInterpolator());
//            animation2.setDuration(200);
//
//            popupView2.findViewById(R.id.tvCancel).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    popupWindow2.dismiss();
//                    lighton();
//                }
//            });
//            popupView2.findViewById(R.id.cancelImage).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    popupWindow2.dismiss();
//                    lighton();
//                }
//            });
//        }
//
//        // 在点击之后设置popupwindow的销毁
//        if (popupWindow2.isShowing()) {
//            popupWindow2.dismiss();
//            lighton();
//        }
//
//        // 设置popupWindow的显示位置，此处是在手机屏幕底部且水平居中的位置
//        popupWindow2.showAtLocation(findViewById(R.id.setting), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//        //popupWindow1.showAtLocation(findViewById(R.id.setting), Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
//        popupView2.startAnimation(animation2);

        if (workStateDialog == null) {
            workStateDialog = new WheelDialog(this);
            workStateDialog.setItems(new String[]{"离职—随时到岗", "在职—月内到岗", "在职—考虑机会", "在职—暂不考虑"});
            workStateDialog.setTitle("工作状态");
            workStateDialog.setDialogListener(new WheelDialog.DialogListener() {
                @Override
                public void confirm(String s, int num) {
                    tv2.setText(s);
                    editYiXiangMe("2", String.valueOf(num + 1));
                }
            });
        }
        workStateDialog.show();
    }


    //-------------------------------------------------------
    private PopupWindow popupWindow3;
    private View popupView3;
    private TranslateAnimation animation3;


    //TODO---------------------------------------------------------------
    private void fabuMethod3() {
//        if (popupWindow3 == null) {
//            popupView3 = View.inflate(this, R.layout.pop_layout_ben3_layout, null);
//            TextView tv1Click = popupView3.findViewById(R.id.tv1);
//            TextView tv2Click = popupView3.findViewById(R.id.tv2);
//            TextView tv3Click = popupView3.findViewById(R.id.tv3);
//
//            // 参数2,3：指明popupwindow的宽度和高度
//            popupWindow3 = new PopupWindow(popupView3, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//            popupWindow3.setOnDismissListener(new PopupWindow.OnDismissListener() {
//                @Override
//                public void onDismiss() {
//                    lighton();
//                }
//            });
//
//            //一周
//            tv1Click.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    tv4.setText("一周");
//                    editYiXiangMe("3", "1");
//                    popupWindow3.dismiss();
//                    lighton();
//                }
//            });
//            //半个月
//            tv2Click.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    tv4.setText("半个月");
//                    editYiXiangMe("3", "2");
//                    popupWindow3.dismiss();
//                    lighton();
//                }
//            });
//            //一个月
//            tv3Click.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    tv4.setText("一个月");
//                    editYiXiangMe("3", "3");
//                    popupWindow3.dismiss();
//                    lighton();
//                }
//            });
//
//
//            // 设置背景图片， 必须设置，不然动画没作用
//            popupWindow3.setBackgroundDrawable(new BitmapDrawable());
//            popupWindow3.setFocusable(true);
//
//            // 设置点击popupwindow外屏幕其它地方消失
//            popupWindow3.setOutsideTouchable(true);
//
//            // 平移动画相对于手机屏幕的底部开始，X轴不变，Y轴从1变0
//            animation3 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
//
//            animation3.setInterpolator(new AccelerateInterpolator());
//            animation3.setDuration(200);
//
//            popupView3.findViewById(R.id.tvCancel).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    popupWindow3.dismiss();
//                    lighton();
//                }
//            });
//            popupView3.findViewById(R.id.cancelImage).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    popupWindow3.dismiss();
//                    lighton();
//                }
//            });
//        }
//
//        // 在点击之后设置popupwindow的销毁
//        if (popupWindow3.isShowing()) {
//            popupWindow3.dismiss();
//            lighton();
//        }
//
//        // 设置popupWindow的显示位置，此处是在手机屏幕底部且水平居中的位置
//        popupWindow3.showAtLocation(findViewById(R.id.setting), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//        //popupWindow1.showAtLocation(findViewById(R.id.setting), Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
//        popupView3.startAnimation(animation3);

        if (workArrivalDialog == null) {
            workArrivalDialog = new WheelDialog(this);
            workArrivalDialog.setItems(new String[]{"一周", "半个月", "一个月"});
            workArrivalDialog.setTitle("到岗时间");
            workArrivalDialog.setDialogListener(new WheelDialog.DialogListener() {
                @Override
                public void confirm(String s, int num) {
                    tv3.setText(s);
                    editYiXiangMe("3", String.valueOf(num + 1));
                }
            });
        }
        workArrivalDialog.show();

    }
    //-------------------------------------------------------


    private void editYiXiangMe(String type, String states) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));

        switch (type) {
            case "1":
                params.put("jobNature", states);
                break;
            case "2":
                params.put("jobStatus", states);
                break;
            case "3":
                params.put("arrivalTime", states);
                break;
        }

        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.editYiXiang, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                getDataList();

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


}
