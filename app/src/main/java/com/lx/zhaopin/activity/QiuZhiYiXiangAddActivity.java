package com.lx.zhaopin.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.lx.zhaopin.other.WageWheelDialog;
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
    private String zhiwei, zhiweiId;
    private final static int GET_ZHIWEI_CODE = 1;
    private final static int GET_ZHANGYE_CODE = 2;
    private WageWheelDialog wageDialog;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.qiuzhiqiwang_add_activity);
        ButterKnife.bind(this);
        init();
    }

    @OnClick({R.id.llView1, R.id.llView2, R.id.llView3, R.id.llView4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llView1:
                //职位
                Intent intent = new Intent(mContext, SelectZhiWeiActivity.class);
                startActivityForResult(intent, GET_ZHIWEI_CODE);
                break;
            case R.id.llView2:
                Intent intent2 = new Intent(mContext, SelectUserHangYeActivity.class);
                startActivityForResult(intent2, GET_ZHIWEI_CODE);
                //行业
                break;
            case R.id.llView3:
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
                }
                wageDialog.show();
                //薪资
                break;

        }
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
            case GET_ZHANGYE_CODE:
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


}
