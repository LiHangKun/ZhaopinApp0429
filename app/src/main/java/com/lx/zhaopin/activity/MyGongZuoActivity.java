package com.lx.zhaopin.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
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
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.MyWorkBean;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.KeyAllboardUtil;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.SharedPreferencesUtil;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.utils.ViewUtil;
import com.lx.zhaopin.view.FlowLiner;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import okhttp3.Response;

public class MyGongZuoActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.edit1)
    EditText edit1;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.llView1OnClick)
    LinearLayout llView1OnClick;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.llView2OnClick)
    LinearLayout llView2OnClick;
    @BindView(R.id.llView3OnClick)
    LinearLayout llView3OnClick;

    @BindView(R.id.llView4OnClick)
    LinearLayout llView4OnClick;

    @BindView(R.id.flowLiner)
    FlowLiner flowLiner;
    @BindView(R.id.edit2)
    EditText edit2;

    @BindView(R.id.edit0)
    EditText edit0;

    @BindView(R.id.okID)
    TextView okID;
    private String oldSearchStr = "";
    private static final String TAG = "MyGongZuoActivity";

    private TimePickerView pvTime, pvCustomTime, pvCustomLunar, pvCustomLunarEnd;
    private Intent intent;
    private String workID;

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    private void initTimePicker() {//Dialog 模式下，在底部弹出

        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Toast.makeText(MyGongZuoActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
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
        setContainer(R.layout.mygongzuo_activity);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void init() {
        topTitle.setText("工作经验");
        rightText.setText("删除");
        workID = getIntent().getStringExtra("workID");
        if (!TextUtils.isEmpty(workID)) {
            rightText.setVisibility(View.VISIBLE);

            getWorkMyJingLi(workID);

        } else {
            rightText.setVisibility(View.INVISIBLE);
        }
        rightText.setOnClickListener(this);

        /*if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }*/

        initTimePicker();
        initLunarPicker();
        initLunarPickerEnd();


        oldSearchStr = (String) SharedPreferencesUtil.getData(MyGongZuoActivity.this, AppSP.WORK_JINENG, "");
        if (!TextUtils.isEmpty(oldSearchStr)) {
            String oldArray[] = oldSearchStr.split(",");
            List list = Arrays.asList(oldArray);
            Set set = new HashSet(list);
            oldArray = (String[]) set.toArray(new String[0]);
            flowData = Arrays.asList(oldArray);
            setUpFlowLinear();
        }

    }

    private List<String> flowData = new ArrayList<>();

    private void getWorkMyJingLi(String wid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("wid", wid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.getWokJingLi, params, new BaseCallback<MyWorkBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, MyWorkBean resultBean) {

                edit1.setText(resultBean.getCompanyName());
                edit0.setText(resultBean.getPositionName());
                tv2.setText(resultBean.getBeginDate());
                tv3.setText(resultBean.getEndDate());
                edit2.setText(resultBean.getExperience());

                String skills = resultBean.getSkills();
                String[] split = skills.split(",");
                for (int i = 0; i < split.length; i++) {
                    flowData.add(split[i]);
                }

                for (int i = 0; i < flowData.size(); i++) {
                    final TextView radioButton = new TextView(mContext);
                    FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT, FlowLiner.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(0, 0, ViewUtil.dp2px(mContext, 10), ViewUtil.dp2px(mContext, 10));
                    radioButton.setLayoutParams(layoutParams);
                    final String str = flowData.get(i);
                    radioButton.setText(str);
                    radioButton.setGravity(Gravity.CENTER);
                    radioButton.setTextSize(13);
                    radioButton.setPadding(ViewUtil.dp2px(mContext, 10), ViewUtil.dp2px(mContext, 6), ViewUtil.dp2px(mContext, 10), ViewUtil.dp2px(mContext, 6));
                    radioButton.setTextColor(getResources().getColorStateList(R.color.radio_text_selector_primary_4d4d4d));
                    //radioButton.setBackgroundResource(R.drawable.search_selector);
                    radioButton.setBackgroundResource(R.drawable.button_shape03);
                    radioButton.setFocusable(true);
                    radioButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, AddZhuanYeJiNengActivity.class);
                            intent.putExtra("id", "");
                            intent.putExtra("name", str);
                            startActivity(intent);
                        }
                    });
                    flowLiner.addView(radioButton);
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


    @OnClick({R.id.llView1OnClick, R.id.llView2OnClick, R.id.llView3OnClick, R.id.llView4OnClick, R.id.okID, R.id.rightText})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rightText:
                StyledDialog.init(mContext);
                StyledDialog.buildIosAlert("", "\r是否删除工作经历?", new MyDialogListener() {
                    @Override
                    public void onFirst() {

                    }

                    @Override
                    public void onSecond() {
                        delWorkJingLiMe(workID);


                    }
                }).setBtnColor(R.color.mainColor2, R.color.mainColor1, 0).setBtnText("取消", "确定").show();

                break;
            case R.id.llView1OnClick:
                /*KeyAllboardUtil.hideKeyboard(MyGongZuoActivity.this);
                selectType1Method();
                lightoff();*/
                break;
            case R.id.llView2OnClick:
                KeyAllboardUtil.hideKeyboard(MyGongZuoActivity.this);
                pvCustomLunar.show();
                break;
            case R.id.llView3OnClick:
                KeyAllboardUtil.hideKeyboard(MyGongZuoActivity.this);
                pvCustomLunarEnd.show();
                break;
            case R.id.llView4OnClick:
                //添加专业技能

                intent = new Intent(mContext, AddZhuanYeJiNengWorkActivity.class);
                intent.putExtra("id", "");
                intent.putExtra("name", "");
                startActivityForResult(intent, 100);
                break;
            case R.id.okID:
                if (TextUtils.isEmpty(workID)) {
                    //新增工作经历
                    if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                        ToastFactory.getToast(mContext, "公司名称不能为空").show();
                        return;
                    } else if (TextUtils.isEmpty(edit0.getText().toString().trim())) {
                        ToastFactory.getToast(mContext, "职位名称不能为空").show();
                        return;
                    } else if (tv2.getText().toString().trim().startsWith("请")) {
                        ToastFactory.getToast(mContext, "开始时间不能为空").show();
                        return;
                    } else if (tv3.getText().toString().trim().startsWith("请")) {
                        ToastFactory.getToast(mContext, "结束时间不能为空").show();
                        return;
                    } else if (TextUtils.isEmpty(oldSearchStr)) {
                        ToastFactory.getToast(mContext, "专业技能不能为空").show();
                        return;
                    } else if (TextUtils.isEmpty(edit2.getText().toString().trim())) {
                        ToastFactory.getToast(mContext, "工作描述不能为空").show();
                        return;
                    } else {
                        addWorkJingYan(edit1.getText().toString().trim(),
                                edit0.getText().toString().trim(),
                                oldSearchStr,
                                tv2.getText().toString().trim(),
                                tv3.getText().toString().trim(),
                                edit2.getText().toString().trim());
                    }
                } else {
                    //编辑工作经历
                    if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                        ToastFactory.getToast(mContext, "公司名称不能为空").show();
                        return;
                    } else if (TextUtils.isEmpty(edit0.getText().toString().trim())) {
                        ToastFactory.getToast(mContext, "职位名称不能为空").show();
                        return;
                    } else if (tv2.getText().toString().trim().startsWith("请")) {
                        ToastFactory.getToast(mContext, "开始时间不能为空").show();
                        return;
                    } else if (tv3.getText().toString().trim().startsWith("请")) {
                        ToastFactory.getToast(mContext, "结束时间不能为空").show();
                        return;
                    } else if (TextUtils.isEmpty(oldSearchStr)) {
                        ToastFactory.getToast(mContext, "专业技能不能为空").show();
                        return;
                    } else if (TextUtils.isEmpty(edit2.getText().toString().trim())) {
                        ToastFactory.getToast(mContext, "工作描述不能为空").show();
                        return;
                    } else {
                        editWorkJingYan(workID, edit1.getText().toString().trim(),
                                edit0.getText().toString().trim(),
                                oldSearchStr,
                                tv2.getText().toString().trim(),
                                tv3.getText().toString().trim(),
                                edit2.getText().toString().trim());
                    }
                }

                break;
        }
    }

    //编辑工作经历
    private void editWorkJingYan(String wid, String companyName, String positionName, String skills, String beginDate, String endDate, String experience) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("wid", wid);
        params.put("companyName", companyName);
        params.put("positionName", positionName);
        params.put("skills", skills);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        params.put("experience", experience);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.editWokJingLi, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                ToastFactory.getToast(mContext, resultBean.getResultNote()).show();
                EventBus.getDefault().post(new MessageEvent(3, null, null, null, null, null, null));
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


    private void setUpFlowLinear() {
        flowLiner.removeAllViews();

        for (int i = 0; i < flowData.size(); i++) {
            final TextView radioButton = new TextView(mContext);
            FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT, FlowLiner.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, ViewUtil.dp2px(mContext, 10), ViewUtil.dp2px(mContext, 10));
            radioButton.setLayoutParams(layoutParams);
            final String str = flowData.get(i);
            radioButton.setText(str);
            radioButton.setGravity(Gravity.CENTER);
            radioButton.setTextSize(13);
            radioButton.setPadding(ViewUtil.dp2px(mContext, 10), ViewUtil.dp2px(mContext, 6), ViewUtil.dp2px(mContext, 10), ViewUtil.dp2px(mContext, 6));
            radioButton.setTextColor(getResources().getColorStateList(R.color.radio_text_selector_primary_4d4d4d));
            //radioButton.setBackgroundResource(R.drawable.search_selector);
            radioButton.setBackgroundResource(R.drawable.button_shape03);
            radioButton.setFocusable(true);
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            flowLiner.addView(radioButton);
        }


    }


    @Override
    protected void onResume() {
        super.onResume();
        /*if (!TextUtils.isEmpty(oldSearchStr)) {
            String oldArray[] = oldSearchStr.split(",");
            List list = Arrays.asList(oldArray);
            Set set = new HashSet(list);
            oldArray = (String[]) set.toArray(new String[0]);
            flowData = Arrays.asList(oldArray);
            setUpFlowLinear();
        }*/
    }

    /**
     * 所有的Activity对象的返回值都是由这个方法来接收
     *
     * @param requestCode 表示的是启动一个Activity时传过去的requestCode值
     * @param resultCode  表示的是启动后的Activity回传值时的resultCode值
     * @param data        表示的是启动后的Activity回传过来的Intent对象
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 判断请求码和返回码是不是正确的，这两个码都是我们自己设置的
        if (requestCode == 100) {

            oldSearchStr = (String) SharedPreferencesUtil.getData(MyGongZuoActivity.this, AppSP.WORK_JINENG, "");
            if (!TextUtils.isEmpty(oldSearchStr)) {
                String oldArray[] = oldSearchStr.split(",");
                List list = Arrays.asList(oldArray);
                Set set = new HashSet(list);
                oldArray = (String[]) set.toArray(new String[0]);
                flowData = Arrays.asList(oldArray);
                setUpFlowLinear();
            }

        }
    }


    //新增工作经历
    private void addWorkJingYan(String companyName, String positionName, String skills, String beginDate, String endDate, String experience) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("companyName", companyName);
        params.put("positionName", positionName);
        params.put("skills", skills);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        params.put("experience", experience);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.addWokJingLi, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                ToastFactory.getToast(mContext, resultBean.getResultNote()).show();
                EventBus.getDefault().post(new MessageEvent(3, null, null, null, null, null, null));
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


    //删除工作经历
    private void delWorkJingLiMe(String wid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("wid", wid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.delWorkJingLi, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                ToastFactory.getToast(mContext, resultBean.getResultNote()).show();
                EventBus.getDefault().post(new MessageEvent(3, null, null, null, null, null, null));
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

    //职位名称   职位名称返回文字 tv1
    private void selectType1Method() {
        if (popupWindow1 == null) {
            popupView1 = View.inflate(this, R.layout.pop_layout_shoplei, null);
            RecyclerView recyclerViewShopLei = popupView1.findViewById(R.id.recyclerViewShopLei);
            TextView tvTitleName = popupView1.findViewById(R.id.tvTitleName);
            tvTitleName.setText("请选择职位名称");
            // 参数2,3：指明popupwindow的宽度和高度
            popupWindow1 = new PopupWindow(popupView1, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
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

}
