package com.lx.zhaopin.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.QiuZhiZheMyInfoBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.KeyAllboardUtil;
import com.lx.zhaopin.utils.NetUtil;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.StringUtil;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.view.SingleChooseDialog;
import com.lxkj.qiqihunshe.app.interf.UpLoadFileCallBack;
import com.lxkj.qiqihunshe.app.retrofitnet.UpFileUtil;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.Request;
import okhttp3.Response;
import top.zibin.luban.Luban;

public class MyUserInfoActivity extends BaseActivity implements View.OnClickListener, UpLoadFileCallBack {

    private RelativeLayout relView1, relView2, relView3, relView4, relView5, relView6;
    private CircleImageView circleImageView;
    private EditText edit1;
    private EditText edit2;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private static final String TAG = "MyUserInfoActivity";

    List<String> fLList = new ArrayList<>();
    private String breed;
    String userSelectIcon = "";
    private String sex = "1";//1.男，2.女
    private ArrayList<String> mSelectPath;
    private UpFileUtil upFileUtil;
    private String dataType = "1";


    //-----------时间选择器--------------------------
    private TimePickerView pvTime, pvCustomTime, pvCustomLunar, pvCustomLunar2;

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    private void initTimePicker() {//Dialog 模式下，在底部弹出

        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Toast.makeText(MyUserInfoActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
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
                Log.i(TAG, "onTimeSelect: 用户年龄" + userAge);
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

    private void initLunarPicker2() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1949, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2099, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomLunar2 = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                //Toast.makeText(UserInforActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
                String userAge = getTime(date).substring(0, 11);
                Log.i(TAG, "onTimeSelect: 用户年龄" + userAge);
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
                                pvCustomLunar2.returnData();
                                pvCustomLunar2.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunar2.dismiss();
                            }
                        });
                        //公农历切换
                        CheckBox cb_lunar = (CheckBox) v.findViewById(R.id.cb_lunar);
                        cb_lunar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                pvCustomLunar2.setLunarCalendar(!pvCustomLunar2.isLunarCalendar());
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


    //-----------时间选择器--------------------------

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.myuserinfo_activity);
        init();
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void initPhotoError() {
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void init() {
        upFileUtil = new UpFileUtil(this, this);
        topTitle.setText("个人信息");
        rightText.setText("保存");
        rightText.setVisibility(View.VISIBLE);
        rightText.setOnClickListener(this);
        initPhotoError();
        initTimePicker();
        initLunarPicker();
        initLunarPicker2();
        circleImageView = findViewById(R.id.circleImageView);

       /* if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }*/


        relView1 = findViewById(R.id.relView1);
        relView2 = findViewById(R.id.relView2);
        relView3 = findViewById(R.id.relView3);
        relView4 = findViewById(R.id.relView4);
        relView5 = findViewById(R.id.relView5);
        relView6 = findViewById(R.id.relView6);

        relView1.setOnClickListener(this);
        relView2.setOnClickListener(this);
        relView3.setOnClickListener(this);
        relView4.setOnClickListener(this);
        relView5.setOnClickListener(this);
        relView6.setOnClickListener(this);

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            MPermissions.requestPermissions(this, AppSP.PMS_CAMERA, Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else {
            pmsLocationSuccess();
        }

        fLList.add("男");
        fLList.add("女");

        getQiuZhiMyInfo(SPTool.getSessionValue(AppSP.UID));


    }

    //求职者个人信息
    private void getQiuZhiMyInfo(String mid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", mid);
        Log.i(TAG, "求职者个人信息: " + NetClass.BASE_URL + NetCuiMethod.qiuZhiMyInfo + "---" + new Gson().toJson(params));
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.checkPhone, params, new BaseCallback<QiuZhiZheMyInfoBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, QiuZhiZheMyInfoBean resultBean) {


                Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror).error(R.mipmap.imageerror))
                        .load(resultBean.getAvatar()).into(circleImageView);
                edit1.setText(resultBean.getName());
                String sex = resultBean.getSex();
                //性别，1.男，2.女
                switch (sex) {
                    case "1":
                        tv1.setText("男");
                        break;
                    case "2":
                        tv1.setText("女");
                        break;
                }
                tv2.setText(resultBean.getWorkDate());
                tv3.setText(resultBean.getBirthday());
                edit2.setText(resultBean.getMobile());


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


    @PermissionGrant(AppSP.PMS_CAMERA)
    public void pmsLocationSuccess() {
        //权限授权成功
        //checkOnlyPermissons4();
        //pickIntent4();
    }

    @PermissionDenied(AppSP.PMS_CAMERA)
    public void pmsLocationError() {
        ToastFactory.getToast(MyUserInfoActivity.this, "权限被拒绝，无法使用该功能！").show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rightText:
                //保存
                if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "姓名不能为空").show();
                    return;
                } else if (tv2.getText().toString().trim().startsWith("请")) {
                    ToastFactory.getToast(mContext, "参加工作时间不能为空").show();
                    return;
                } else if (tv3.getText().toString().trim().startsWith("请")) {
                    ToastFactory.getToast(mContext, "出生年月时间不能为空").show();
                    return;
                } else {
                    xiuGiaMethod(edit1.getText().toString().trim(), userSelectIcon, sex, tv3.getText().toString().trim(), tv2.getText().toString().trim());
                }

                break;
            case R.id.relView1:
                checkOnlyPermissons4();
                break;
            case R.id.relView3:
                //性别
                //1.男，2.女
                new SingleChooseDialog(mContext, "请选择性别", fLList, new SingleChooseDialog.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        breed = fLList.get(position);
                        tv1.setText("请选择性别");
                        tv1.setText(breed);
                        //性别 0女 1男
                        if (breed.equals("男")) {
                            //显示男
                            sex = "1";
                        } else if (breed.equals("女")) {
                            //显示女
                            sex = "2";
                        }
                    }
                }).show();
                break;
            case R.id.relView4:
                KeyAllboardUtil.hideKeyboard(MyUserInfoActivity.this);
                pvCustomLunar.show();
                break;
            case R.id.relView5:
                KeyAllboardUtil.hideKeyboard(MyUserInfoActivity.this);
                pvCustomLunar2.show();
                break;

        }
    }

    //修改个人信息
    private void xiuGiaMethod(String name, String avatar, String sex, String birthday, String workDate) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("name", name);
        params.put("avatar", avatar);
        params.put("sex", sex);
        params.put("birthday", birthday);
        params.put("workDate", workDate);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.xiugaiQiuZhiMyInfo, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                EventBus.getDefault().post(new MessageEvent(2, null, null, null, null, null, null));
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


    //系统设置权限申请返回码
    private int requestCodeSer = 123;
    private static final int REQUEST_IMAGE1 = 1;//修改头像

    /*判断是否有相机权限4*/
    private void checkOnlyPermissons4() {
        if (ContextCompat.checkSelfPermission(MyUserInfoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MyUserInfoActivity.this);
            builder.setTitle("提示");
            builder.setMessage("请先开启读写手机存储权限!").setCancelable(false).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    goToAppSetting();
                }
            }).show();
        } else {
            pickIntent4();
        }
    }

    // 跳转到当前应用的设置界面
    private void goToAppSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, requestCodeSer);
    }


    /*选择照片4*/
    private void pickIntent4() {
        Intent intent = new Intent(MyUserInfoActivity.this, MultiImageSelectorActivity.class);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
        String type = "4";
        startActivityForResult(intent, REQUEST_IMAGE1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {
            if (requestCode == REQUEST_IMAGE1) {
                try {
                    mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                    if (mSelectPath.size() > 0) {
                        //上传图片
                        String imgPath5 = mSelectPath.get(0);
                        Log.i(TAG, "onActivityResult: 图片地址5" + imgPath5);

                        Bitmap bitmap = StringUtil.decodeFile(new File(imgPath5));

                        List<File> files = Luban.with(mContext).load(mSelectPath).get();
                        circleImageView.setImageBitmap(bitmap);
                        circleImageView.setVisibility(View.VISIBLE);
                        if (NetUtil.isNetWorking(mContext)) {
                            showLoading(false);
                            upFileUtil.upLoad(files.get(0).getAbsolutePath());
                        } else {
                            ToastFactory.getToast(mContext, "网络错误,请稍后重试").show();
                            return;
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void uoLoad(@NotNull String url) {
        dismissLoading();
        Log.e(TAG, "http 上传图片:" + url);
        userSelectIcon = url;
        //TODO  头像上传成功,修改头像

    }

    @Override
    public void uoLoad(@NotNull List<String> url) {
        dismissLoading();
    }
}
