package com.lx.zhaopin.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.XueLiListAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.MyJiaoYuInfoBean;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.XueLiListBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.KeyAllboardUtil;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;

import org.feezu.liuli.timeselector.Utils.TextUtil;
import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import okhttp3.Response;

public class MyJiaoYuActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.edit1)
    EditText edit1;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.llView1OnClick)
    LinearLayout llView1OnClick;
    @BindView(R.id.edit2)
    EditText edit2;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.llView2OnClick)
    LinearLayout llView2OnClick;
    @BindView(R.id.llView3OnClick)
    LinearLayout llView3OnClick;
    @BindView(R.id.edit3)
    EditText edit3;
    @BindView(R.id.okID)
    TextView okID;


    private static final String TAG = "MyJiaoYuActivity";

    private TimePickerView pvTime, pvCustomTime, pvCustomLunar, pvCustomLunarEnd;
    private RecyclerView recyclerViewShopLei;
    private String xueLiName;
    private String xueLiID = "";
    private String eid;

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    private void initTimePicker() {//Dialog 模式下，在底部弹出

        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Toast.makeText(MyJiaoYuActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
                Log.i("pvTime", "onTimeSelect");

            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .addOnCancelClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("pvTime", "onCancelClickListener");
                    }
                })
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
    }


    /**
     * 农历时间已扩展至 ： 1900 - 2100年
     */

    private void initLunarPicker() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1949, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2099, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomLunar = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                //Toast.makeText(UserInforActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
                String userAge = getTime(date).substring(0, 11);
                tv2.setText(userAge);

            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_lunar, new CustomListener() {

                    @Override
                    public void customLayout(final View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunar.returnData();
                                pvCustomLunar.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunar.dismiss();
                            }
                        });
                        //公农历切换
                        CheckBox cb_lunar = (CheckBox) v.findViewById(R.id.cb_lunar);
                        cb_lunar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                pvCustomLunar.setLunarCalendar(!pvCustomLunar.isLunarCalendar());
                                //自适应宽
                                setTimePickerChildWeight(v, isChecked ? 0.8f : 1f, isChecked ? 1f : 1.1f);
                            }
                        });

                    }

                    /**
                     * 公农历切换后调整宽
                     * @param v
                     * @param yearWeight
                     * @param weight
                     */
                    private void setTimePickerChildWeight(View v, float yearWeight, float weight) {
                        ViewGroup timePicker = (ViewGroup) v.findViewById(R.id.timepicker);
                        View year = timePicker.getChildAt(0);
                        LinearLayout.LayoutParams lp = ((LinearLayout.LayoutParams) year.getLayoutParams());
                        lp.weight = yearWeight;
                        year.setLayoutParams(lp);
                        for (int i = 1; i < timePicker.getChildCount(); i++) {
                            View childAt = timePicker.getChildAt(i);
                            LinearLayout.LayoutParams childLp = ((LinearLayout.LayoutParams) childAt.getLayoutParams());
                            childLp.weight = weight;
                            childAt.setLayoutParams(childLp);
                        }
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setRangDate(startDate, selectedDate)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.RED)
                .build();
    }


    /**
     * 农历时间已扩展至 ： 1900 - 2100年
     */

    private void initLunarPickerEnd() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1949, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2099, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomLunarEnd = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                //Toast.makeText(UserInforActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
                String userAge = getTime(date).substring(0, 11);
                tv3.setText(userAge);

            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_lunar, new CustomListener() {

                    @Override
                    public void customLayout(final View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunarEnd.returnData();
                                pvCustomLunarEnd.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunarEnd.dismiss();
                            }
                        });
                        //公农历切换
                        CheckBox cb_lunar = (CheckBox) v.findViewById(R.id.cb_lunar);
                        cb_lunar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                pvCustomLunarEnd.setLunarCalendar(!pvCustomLunarEnd.isLunarCalendar());
                                //自适应宽
                                setTimePickerChildWeight(v, isChecked ? 0.8f : 1f, isChecked ? 1f : 1.1f);
                            }
                        });

                    }

                    /**
                     * 公农历切换后调整宽
                     * @param v
                     * @param yearWeight
                     * @param weight
                     */
                    private void setTimePickerChildWeight(View v, float yearWeight, float weight) {
                        ViewGroup timePicker = (ViewGroup) v.findViewById(R.id.timepicker);
                        View year = timePicker.getChildAt(0);
                        LinearLayout.LayoutParams lp = ((LinearLayout.LayoutParams) year.getLayoutParams());
                        lp.weight = yearWeight;
                        year.setLayoutParams(lp);
                        for (int i = 1; i < timePicker.getChildCount(); i++) {
                            View childAt = timePicker.getChildAt(i);
                            LinearLayout.LayoutParams childLp = ((LinearLayout.LayoutParams) childAt.getLayoutParams());
                            childLp.weight = weight;
                            childAt.setLayoutParams(childLp);
                        }
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setRangDate(startDate, selectedDate)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.RED)
                .build();
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.myjiaoyu_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        topTitle.setText("教育经历");
        initTimePicker();
        initLunarPicker();
        initLunarPickerEnd();

        eid = getIntent().getStringExtra("eid");

        if (!TextUtils.isEmpty(eid)) {
            //请求我的学历信息
            chaEidInfo(eid);
            rightText.setText("删除");
            rightText.setVisibility(View.VISIBLE);
            rightText.setOnClickListener(this);
        }


        /*if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }*/


    }

    //
    private void chaEidInfo(String eid) {
        Map<String, String> params = new HashMap<>();
        params.put("eid", eid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.chaEidInfo, params, new BaseCallback<MyJiaoYuInfoBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, MyJiaoYuInfoBean resultBean) {

                edit1.setText(resultBean.getSchool());
                tv1.setText(resultBean.getEducation().getName());
                edit2.setText(resultBean.getMajor());
                tv2.setText(resultBean.getBeginDate());
                tv3.setText(resultBean.getEndDate());
                edit3.setText(resultBean.getExperience());

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


    @OnClick({R.id.llView1OnClick, R.id.llView2OnClick, R.id.llView3OnClick, R.id.okID})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rightText:
                //删除
                delMeEid(eid);
                break;
            case R.id.llView1OnClick:
                //获取学历
                KeyAllboardUtil.hideKeyboard(MyJiaoYuActivity.this);
                selectType1Method();
                lightoff();
                break;
            case R.id.llView2OnClick:
                //开始时间
                KeyAllboardUtil.hideKeyboard(MyJiaoYuActivity.this);
                pvCustomLunar.show();
                break;
            case R.id.llView3OnClick:
                //结束时间
                KeyAllboardUtil.hideKeyboard(MyJiaoYuActivity.this);
                pvCustomLunarEnd.show();
                break;
            case R.id.okID:
                //保存
                if (TextUtils.isEmpty(eid)) {
                    //新增
                    if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                        ToastFactory.getToast(mContext, "学校名称不能为空").show();
                        return;
                    } else if (TextUtil.isEmpty(xueLiID)) {
                        ToastFactory.getToast(mContext, "学历不能为空").show();
                        return;
                    } else if (TextUtils.isEmpty(edit2.getText().toString().trim())) {
                        ToastFactory.getToast(mContext, "专业名称不能为空").show();
                        return;
                    } else if (tv2.getText().toString().trim().startsWith("请")) {
                        ToastFactory.getToast(mContext, "请先选择开始时间").show();
                        return;
                    } else if (tv3.getText().toString().trim().startsWith("请")) {
                        ToastFactory.getToast(mContext, "请先选择结束时间").show();
                        return;
                    } else {
                        xueliMethod(edit1.getText().toString().trim(), xueLiID,
                                edit2.getText().toString().trim(), tv2.getText().toString().trim(),
                                tv3.getText().toString().trim(), edit3.getText().toString().trim());
                    }
                } else {
                    //修改
                    if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                        ToastFactory.getToast(mContext, "学校名称不能为空").show();
                        return;
                    } else if (TextUtil.isEmpty(xueLiID)) {
                        ToastFactory.getToast(mContext, "学历不能为空").show();
                        return;
                    } else if (TextUtils.isEmpty(edit2.getText().toString().trim())) {
                        ToastFactory.getToast(mContext, "专业名称不能为空").show();
                        return;
                    } else if (tv2.getText().toString().trim().startsWith("请")) {
                        ToastFactory.getToast(mContext, "请先选择开始时间").show();
                        return;
                    } else if (tv3.getText().toString().trim().startsWith("请")) {
                        ToastFactory.getToast(mContext, "请先选择结束时间").show();
                        return;
                    } else {
                        EdidXueliMethod(
                                edit1.getText().toString().trim(),//学校
                                eid, //教育经历id
                                xueLiID,//学历ID
                                edit2.getText().toString().trim(),//专业
                                tv2.getText().toString().trim(),//开始时间
                                tv3.getText().toString().trim(),//结束时间
                                edit3.getText().toString().trim());//在校经历
                    }
                }

                break;
        }
    }

    //删除教育经历
    private void delMeEid(String eid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("eid", eid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.delEidInfo, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                EventBus.getDefault().post(new MessageEvent(3, null, null, null, null, null, null));
                ToastFactory.getToast(mContext, resultBean.getAuthCode()).show();
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

    //修改教育经历
    private void EdidXueliMethod(String school, String eid, String educationId, String major, String beginDate, String endDate, String experience) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("eid", eid);
        params.put("school", school);
        params.put("major", major);
        params.put("educationId", educationId);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        params.put("experience", experience);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.xiuGaiEidInfo, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                EventBus.getDefault().post(new MessageEvent(3, null, null, null, null, null, null));
                ToastFactory.getToast(mContext, resultBean.getAuthCode()).show();
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

    //新增教育信息
    private void xueliMethod(String school, String educationId, String major, String beginDate, String endDate, String experience) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("school", school);
        params.put("educationId", educationId);
        params.put("major", major);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        params.put("experience", experience);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.addJiaoYuJIngLi, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                EventBus.getDefault().post(new MessageEvent(3, null, null, null, null, null, null));
                ToastFactory.getToast(mContext, resultBean.getAuthCode()).show();
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

    private PopupWindow popupWindow1;
    private View popupView1;
    private TranslateAnimation animation1;

    //学历   学历返回文字 tv1
    private void selectType1Method() {
        if (popupWindow1 == null) {
            popupView1 = View.inflate(this, R.layout.pop_layout_shoplei, null);
            recyclerViewShopLei = popupView1.findViewById(R.id.recyclerViewShopLei);
            TextView tvTitleName = popupView1.findViewById(R.id.tvTitleName);
            tvTitleName.setText("请选择学历");
            // 参数2,3：指明popupwindow的宽度和高度
            popupWindow1 = new PopupWindow(popupView1, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    lighton();
                }
            });


            //TODO 设置数据
            getXueLiDataList(tv1);

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


    //学历列表
    private void getXueLiDataList(final TextView tv1) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        Log.i(TAG, "学历列表: " + NetClass.BASE_URL + NetCuiMethod.xueLiList + "---" + new Gson().toJson(params));
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.xueLiList, params, new BaseCallback<XueLiListBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, XueLiListBean resultBean) {
                XueLiListAdapter xueLiListAdapter = new XueLiListAdapter(mContext, resultBean.getDataList());
                recyclerViewShopLei.setLayoutManager(new LinearLayoutManager(mContext));
                recyclerViewShopLei.setAdapter(xueLiListAdapter);

                xueLiListAdapter.setOnItemClickener(new XueLiListAdapter.onItemClickener() {


                    @Override
                    public void itemClick(String name, String id) {
                        xueLiName = name;
                        xueLiID = id;
                        popupWindow1.dismiss();

                        tv1.setText(xueLiName);

                        Log.i(TAG, "itemClick: 用户选择的学历信息" + xueLiName + "---" + xueLiID);
                    }
                });

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


}
