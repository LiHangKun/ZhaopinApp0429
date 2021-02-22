package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.SelectQiWangBean;
import com.lx.zhaopin.bean.ZhuCiZhiWuBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.http.BaseCallback;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import okhttp3.Response;

public class QiuZhiYiXiangChangeActivity extends BaseActivity {
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
    private String zhiwei, zhiweiId;
    private final static int GET_ZHIWEI_CODE = 1;
    private final static int GET_HANGYE_CODE = 2;
    private final static int GET_CITY_CODE = 3;
    private WageWheelDialog wageDialog;
    private String id;
    private String zhiWeiID;
    private String cityID;
    private String maxK;
    private String minK;
    private String editID;
    private String hangID = "";
    private String industries;
    List<String> HangIDList = new ArrayList<>();

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.qiuzhiqiwang_change_activity);
        ButterKnife.bind(this);
        init();
    }

    @OnClick({R.id.llView1, R.id.llView2, R.id.llView3, R.id.llView4, R.id.okID, R.id.delID})
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
                        }
                    });
                }
                wageDialog.show();
                //薪资
                break;
            case R.id.delID:
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
            case R.id.okID:
                String salary = tv4.getText().toString();
                if (TextUtils.equals(salary, "面议")) {
                    minK = "0";
                    maxK = "0";
                } else {
                    String[] salarys = salary.split("-");
                    minK = salarys[0].substring(0, salarys[0].length() - 1);
                    maxK = salarys[1].substring(0, salarys[1].length() - 1);
                }
                editQiWang(editID, minK, maxK, zhiweiId, industries, cityID);
                break;
        }
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GET_ZHIWEI_CODE:
                if (data != null) {
                    zhiwei = data.getStringExtra("zhiwei");
                    zhiweiId = data.getStringExtra("zhiweiId");
                    tv1.setText(zhiwei);
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
                break;
            case GET_CITY_CODE:
                if (data != null) {
                    cityID = data.getStringExtra("cityId");
                    tv3.setText(data.getStringExtra("cityName"));
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
        topTitle.setText("编辑求职意向");
        id = getIntent().getStringExtra("id");
        yiXiangDetail(id);
    }

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
                cityID = resultBean.getCity().getId();
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBuilder_ids = new StringBuilder();
                if (resultBean.getResumeExpectationIndustryList().size() == 0){
                    tv2.setText("不限");
                    industries = "不限";
                }else {
                    for (int i = 0; i < resultBean.getResumeExpectationIndustryList().size(); i++) {
                        stringBuilder.append(resultBean.getResumeExpectationIndustryList().get(i).getName());
                        stringBuilder_ids.append(resultBean.getResumeExpectationIndustryList().get(i).getId());
                        if (i != resultBean.getResumeExpectationIndustryList().size() - 1) {
                            stringBuilder.append(" ");
                            stringBuilder_ids.append(",");
                        }
                    }
                    tv2.setText(stringBuilder.toString());
                    industries = stringBuilder_ids.toString();
                }


                tv3.setText(resultBean.getCity().getName());
                if (TextUtils.equals(resultBean.getMinSalary(), "0")) {
                    tv4.setText("面议");
                } else {
                    tv4.setText(resultBean.getMinSalary() + "-" + resultBean.getMaxSalary() + "K");
                }

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

}
