package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.SelectQiWangBean;
import com.lx.zhaopin.bean.ZhuCiZhiWuBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.event.TypeEvent;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

public class QiuZhiQiWangActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView okID;
    private String zhiWeiID;
    private String cityNameID;
    private static final String TAG = "QiuZhiQiWangActivity";
    private String maxK;
    private String minK;
    private Intent intent;
    private String id;
    private String editID;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.qiuzhiqiwang_activity);
        init();
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = false)
    public void getEventmessage(MessageEvent event) {
        int messageType = event.getMessageType();
        switch (messageType) {
            case 6:
                //期望的职位ID
                zhiWeiID = event.getKeyWord1();
                String name = event.getKeyWord2();
                tv1.setText(name);
                Log.i(TAG, "getEventmessage: " + zhiWeiID + name + "--");
                break;
            case 4:
                String cityName = event.getKeyWord1();
                cityNameID = event.getKeyWord2();
                tv3.setText(cityName);
                Log.i(TAG, "getEventmessage: " + "--" + cityName + "---" + cityNameID);
                break;

        }
    }

    List<String> HangIDList = new ArrayList<>();
    List<String> HangNameList = new ArrayList<>();

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = false)
    public void getEventmessage(TypeEvent event) {
        List<SelectQiWangBean.DataListBean> dataListBeans = event.getmSelectedData();
        for (int i = 0; i < dataListBeans.size(); i++) {
            Log.i(TAG, "getEventmessage: " + dataListBeans.get(i).getName() + "--" + dataListBeans.get(i).getId());
            HangIDList.add(dataListBeans.get(i).getId());
            HangNameList.add(dataListBeans.get(i).getName());
        }
        hangID = HangIDList.toString().replace("[", "").replace("]", "").replace(" ", "").trim();
        hangName = HangNameList.toString().replace("[", "").replace("]", "").replace(" ", "").trim();
        tv2.setText(hangName);
        Log.i(TAG, "getEventmessage: 选择的ID" + hangID + "----" + hangName);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void init() {
        topTitle.setText("编辑求职期望");
        rightText.setText("删除");

        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }

        id = getIntent().getStringExtra("id");
        if (!TextUtils.isEmpty(id)) {
            rightText.setVisibility(View.VISIBLE);
            yiXiangDetail(id);
        } else {
            rightText.setVisibility(View.INVISIBLE);
        }

        rightText.setOnClickListener(this);
        getNoLinkData();
        initNoLinkOptionsPicker();

        LinearLayout llView1 = findViewById(R.id.llView1);
        LinearLayout llView2 = findViewById(R.id.llView2);
        LinearLayout llView3 = findViewById(R.id.llView3);
        LinearLayout llView4 = findViewById(R.id.llView4);

        llView1.setOnClickListener(this);
        llView2.setOnClickListener(this);
        llView3.setOnClickListener(this);
        llView4.setOnClickListener(this);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        okID = findViewById(R.id.okID);
        okID.setOnClickListener(this);


    }


    //zhuCiZhiWei 获取主次职位
    private void yiXiangDetail(String exId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("exId", exId);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.zhuCiZhiWei, params, new BaseCallback<ZhuCiZhiWuBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, ZhuCiZhiWuBean resultBean) {
                minK = resultBean.getMinSalary();
                maxK = resultBean.getMaxSalary();
                editID = resultBean.getId();
                zhiWeiID = resultBean.getPositionCategory3().getId();
                tv1.setText(resultBean.getPositionCategory3().getName());
                cityNameID = resultBean.getCity().getId();
                if (resultBean.getResumeExpectationIndustryList().size() >= 2) {
                    tv2.setText(resultBean.getResumeExpectationIndustryList().get(0).getName() + "," + resultBean.getResumeExpectationIndustryList().get(1).getName());
                } else {
                    tv2.setText(resultBean.getResumeExpectationIndustryList().get(0).getName());
                }

                for (int i = 0; i < resultBean.getResumeExpectationIndustryList().size(); i++) {
                    resultBean.getResumeExpectationIndustryList().get(i).getId();
                    HangIDList.add(resultBean.getResumeExpectationIndustryList().get(i).getId());
                }
                hangID = HangIDList.toString().replace("[", "").replace("]", "").replace(" ", "").trim();


                tv3.setText(resultBean.getCity().getName());
                tv4.setText(resultBean.getMinSalary() + "-" + resultBean.getMaxSalary() + "K");


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

    private OptionsPickerView pvNoLinkOptions;
    private ArrayList<String> food = new ArrayList<>();
    private ArrayList<String> clothes = new ArrayList<>();
    private ArrayList<String> computer = new ArrayList<>();


    private void getNoLinkData() {
        food.add("3K");
        food.add("4K");
        food.add("5K");
        food.add("6K");
        food.add("7K");
        food.add("8K");
        food.add("9K");
        food.add("10K");

        clothes.add("3K");
        clothes.add("4K");
        clothes.add("5K");
        clothes.add("6K");
        clothes.add("7K");
        clothes.add("8K");
        clothes.add("9K");
        clothes.add("10K");

        computer.add("张三");
        computer.add("李四");
        computer.add("王五");
        computer.add("赵六");
        computer.add("周七");
        computer.add("哈哈");
        computer.add("嘻嘻");
    }

    private void initNoLinkOptionsPicker() {// 不联动的多级选项
        pvNoLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                //String str = "food:" + food.get(options1) + "\nclothes:" + clothes.get(options2) + "\ncomputer:" + computer.get(options3);
                String time2 = food.get(options1) + " - " + clothes.get(options2);
                minK = food.get(options1).substring(0, food.get(options1).length() - 1);
                maxK = clothes.get(options2).substring(0, clothes.get(options2).length() - 1);

                Log.i(TAG, "onOptionsSelect: " + minK + maxK);

                BigDecimal num1 = new BigDecimal(minK);
                BigDecimal num2 = new BigDecimal(maxK);

                int i = num1.compareTo(num2); // 0 相等
                Log.i(TAG, "onOptionsSelect: 1==" + i);
                if (i > 0) {
                    ToastFactory.getToast(mContext, "薪资范围错误,请重新选择").show();
                    return;
                } else {
                    tv4.setText(time2);
                }


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


    String hangID = "";
    String hangName = "";

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rightText:
                //delzhuCiZhiWei
                StyledDialog.init(mContext);
                StyledDialog.buildIosAlert("", "\r是否删除职位信息?", new MyDialogListener() {
                    @Override
                    public void onFirst() {

                    }

                    @Override
                    public void onSecond() {
                        delZhiWeiMe(id);
                    }
                }).setBtnColor(R.color.mainColor2, R.color.mainColor1, 0).setBtnText("取消", "确定").show();

                break;
            case R.id.llView1:
                //期望职位
                intent = new Intent(mContext, SelectQiWangType1_1Activity.class);
                startActivity(intent);
                break;
            case R.id.llView2:
                //期望行业
                startActivity(new Intent(mContext, SelectQiWangType2Activity.class));
                break;
            case R.id.llView3:
                //工作城市
                startActivity(new Intent(mContext, SelectCityPro1ListActivity.class));
                break;
            case R.id.llView4:
                //薪资要求
                pvNoLinkOptions.show();
                break;
            case R.id.okID:
                //保存
                if (TextUtils.isEmpty(id)) {
                    //新增
                    if (TextUtils.isEmpty(zhiWeiID)) {
                        ToastFactory.getToast(mContext, "请选择职位").show();
                        return;
                    } else if (TextUtils.isEmpty(hangID)) {
                        ToastFactory.getToast(mContext, "请选择行业").show();
                        return;
                    } else if (TextUtils.isEmpty(cityNameID)) {
                        ToastFactory.getToast(mContext, "请选择工作城市").show();
                        return;
                    } else if (tv4.getText().toString().trim().startsWith("请")) {
                        ToastFactory.getToast(mContext, "请选择薪资范围").show();
                        return;
                    } else {
                        addQiWang(minK, maxK, zhiWeiID, hangID, cityNameID);
                    }
                } else {
                    //编辑
                    if (TextUtils.isEmpty(zhiWeiID)) {
                        ToastFactory.getToast(mContext, "请选择职位").show();
                        return;
                    } else if (TextUtils.isEmpty(hangID)) {
                        ToastFactory.getToast(mContext, "请选择行业").show();
                        return;
                    } else if (TextUtils.isEmpty(cityNameID)) {
                        ToastFactory.getToast(mContext, "请选择工作城市").show();
                        return;
                    } else if (tv4.getText().toString().trim().startsWith("请")) {
                        ToastFactory.getToast(mContext, "请选择薪资范围").show();
                        return;
                    } else {
                        editQiWang(editID, minK, maxK, zhiWeiID, hangID, cityNameID);
                    }

                }


                break;
        }
    }

    //xiuGaizhuCiZhiWei
    private void editQiWang(String exId, String minSalary, String maxSalary, String positionCategoryId, String industries, String cityId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("exId", exId);
        params.put("minSalary", minSalary);
        params.put("maxSalary", maxSalary);
        params.put("positionCategoryId", positionCategoryId);
        params.put("industries", industries);
        params.put("cityId", cityId);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.xiuGaizhuCiZhiWei, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                EventBus.getDefault().post(new MessageEvent(8, null, null, null, null, null, null));
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

    private void delZhiWeiMe(String exId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("exId", exId);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.delzhuCiZhiWei, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                EventBus.getDefault().post(new MessageEvent(8, null, null, null, null, null, null));
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

    //addzhuCiZhiWei
    private void addQiWang(String minSalary, String maxSalary, String positionCategoryId, String industries, String cityId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("minSalary", minSalary);
        params.put("maxSalary", maxSalary);
        params.put("positionCategoryId", positionCategoryId);
        params.put("industries", industries);
        params.put("cityId", cityId);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.addzhuCiZhiWei, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                EventBus.getDefault().post(new MessageEvent(8, null, null, null, null, null, null));
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
}
