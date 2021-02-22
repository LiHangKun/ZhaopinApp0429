package com.lx.zhaopin.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.SelectQiWangBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.other.WageWheelDialog;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Response;

public class QiuZhiYiXiangAddActivity extends BaseActivity {
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
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.llView4)
    LinearLayout llView4;
    private String zhiweiId;
    private String cityID;
    private String industries;
    private String maxK;
    private String minK;
    private final static int GET_ZHIWEI_CODE = 1;
    private final static int GET_HANGYE_CODE = 2;
    private final static int GET_CITY_CODE = 3;
    private WageWheelDialog wageDialog;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.qiuzhiqiwang_add_activity);
        ButterKnife.bind(this);
        init();
    }

    @OnClick({R.id.llView1, R.id.llView2, R.id.llView3, R.id.llView4, R.id.okID})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llView1:
                //职位
                Intent intent = new Intent(mContext, SelectZhiWeiActivity.class);
                startActivityForResult(intent, GET_ZHIWEI_CODE);
                break;
            case R.id.llView2:
                Intent intent2 = new Intent(mContext, SelectUserHangYeActivity.class);
                startActivityForResult(intent2, GET_HANGYE_CODE);
                //行业
                break;
            case R.id.llView3:
                Intent intent3 = new Intent(mContext, SelectCityActivity.class);
                startActivityForResult(intent3, GET_CITY_CODE);
                //城市
                break;
            case R.id.llView4:
                if (wageDialog == null) {
                    wageDialog = new WageWheelDialog(this);
                    String[] wageLeft = new String[61];
                    String[] wageRight = new String[60];
                    wageLeft[0] = "面议";
                    for (int i = 1; i < wageLeft.length; i++) {
                        wageLeft[i] = i + "k";
                    }
                    for (int i = 0; i < wageRight.length; i++) {
                        wageRight[i] = 2 + i + "k";
                    }
                    wageDialog.setItemsLeft(wageLeft);
                    wageDialog.setItemsRight(wageRight);
                    wageDialog.setTitle("薪资要求（月薪，单位：千元）");
                    wageDialog.setDialogListener(new WageWheelDialog.DialogListener() {
                        @Override
                        public void confirm(String result) {
                            tv4.setText(result);
                            tv4.setTextColor(Color.parseColor("#151413"));
                        }
                    });
                }
                wageDialog.show();
                //薪资
                break;
            case R.id.okID:
                if (TextUtils.isEmpty(zhiweiId)) {
                    Log.e("add", "1");
                } else if (TextUtils.isEmpty(industries)) {
                    Log.e("add", "2");
                } else if (TextUtils.isEmpty(cityID)) {
                    Log.e("add", "3");
                } else if (TextUtils.equals(tv4.getText().toString(), "选择薪资范围")) {
                    Log.e("add", "4");
                } else {
                    Log.e("add", "5");
                    String salary = tv4.getText().toString();
                    if (TextUtils.equals(salary, "面议")) {
                        minK = "0";
                        maxK = "0";
                    } else {
                        String[] salarys = salary.split("-");
                        minK = salarys[0].substring(0, salarys[0].length() - 1);
                        maxK = salarys[1].substring(0, salarys[1].length() - 1);
                    }
                    addQiWang(minK, maxK, zhiweiId, industries, cityID);

                }
                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GET_ZHIWEI_CODE:
                if (data != null) {
                    zhiweiId = data.getStringExtra("zhiweiId");
                    tv1.setText(data.getStringExtra("zhiwei"));
                    tv1.setTextColor(Color.parseColor("#151413"));
                }
                break;
            case GET_HANGYE_CODE:
                if (data != null && data.hasExtra("selHangye")) {
                    List<SelectQiWangBean.DataListBean> list = (List<SelectQiWangBean.DataListBean>) data.getSerializableExtra("selHangye");
                    StringBuilder stringBuilder = new StringBuilder();
                    StringBuilder stringBuilder_ids = new StringBuilder();
                    for (int i = 0; i < list.size(); i++) {
                        stringBuilder.append(list.get(i).getName());
                        stringBuilder_ids.append(list.get(i).getId());
                        if (i != list.size() - 1) {
                            stringBuilder.append(" ");
                            stringBuilder_ids.append(",");
                        }
                    }
                    tv2.setText(stringBuilder.toString());
                    industries = stringBuilder_ids.toString();
                } else if (data != null && data.hasExtra("selAll")) {
                    tv2.setText("不限");
                    industries = "不限";
                }
                tv2.setTextColor(Color.parseColor("#151413"));
                break;
            case GET_CITY_CODE:
                if (data != null) {
                    cityID = data.getStringExtra("cityId");
                    tv3.setText(data.getStringExtra("cityName"));
                    tv3.setTextColor(Color.parseColor("#151413"));
                }
                break;
        }
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }


    private void init() {
        topTitle.setText("添加求职意向");
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
                Log.e("add", "6");
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
