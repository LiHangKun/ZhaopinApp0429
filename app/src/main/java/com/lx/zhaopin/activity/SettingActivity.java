package com.lx.zhaopin.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.NoticeDetailActivity;
import com.lx.zhaopin.common.SplashActivity;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.ActivityManager;
import com.lx.zhaopin.utils.DataCleanManager;
import com.lx.zhaopin.utils.SPTool;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import okhttp3.Request;
import okhttp3.Response;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_tv)
    TextView titleTv;
    private RelativeLayout relView1, relView2, relView3, relView4, relView5, relViewCui1, relViewCui2, relViewCui3;
    private TextView okID;
    private TextView text1;
    private static final String TAG = "SettingActivity";
    private Intent intent;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.setting_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        titleTv.setText("我的设置");
        baseTop.setVisibility(View.GONE);

        relView1 = findViewById(R.id.relView1);
        relView2 = findViewById(R.id.relView2);
        relView3 = findViewById(R.id.relView3);
        relView4 = findViewById(R.id.relView4);
        relView5 = findViewById(R.id.relView5);
        relViewCui1 = findViewById(R.id.relViewCui1);
        relViewCui2 = findViewById(R.id.relViewCui2);
        relViewCui3 = findViewById(R.id.relViewCui3);
        okID = findViewById(R.id.okID);
        okID.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        okID.getPaint().setAntiAlias(true);//抗锯齿

        text1 = findViewById(R.id.text1);

        relView1.setOnClickListener(this);
        relView2.setOnClickListener(this);
        relView3.setOnClickListener(this);
        relView4.setOnClickListener(this);
        relView5.setOnClickListener(this);
        okID.setOnClickListener(this);
        relViewCui1.setOnClickListener(this);
        relViewCui2.setOnClickListener(this);
        relViewCui3.setOnClickListener(this);

        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(this);
            text1.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    private void clearAllCachecatch() {
        DataCleanManager.clearAllCache(this);
        try {
            Log.i(TAG, "initViews: 剩余缓存" + DataCleanManager.getTotalCacheSize(this));
            text1.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.okID:
                //退出
                StyledDialog.init(mContext);
                StyledDialog.buildIosAlert("", "\r是否退出登录?", new MyDialogListener() {
                    @Override
                    public void onFirst() {

                    }

                    @Override
                    public void onSecond() {
                        logoutMe();


                    }
                }).setBtnColor(R.color.mainColor2, R.color.mainColor1, 0).setBtnText("取消", "确定").show();
                break;
            case R.id.relView1:
                //修改密码
                startActivity(new Intent(mContext, XiuGaiMiMaActivity.class));
                break;
            case R.id.relView2:
                //常见问题
                startActivity(new Intent(mContext, ChangJianWenTiActivity.class));
                break;
            case R.id.relView3:
                //意见反馈
                startActivity(new Intent(mContext, FeedActivity.class));
                break;
            case R.id.relView4:
                //清除缓存
                clearAllCachecatch();
                break;
            case R.id.relView5:
                //关于我们
                startActivity(new Intent(mContext, AboutMeActivity.class));
                break;
            case R.id.relViewCui1:
                //用户协议
                intent = new Intent(SettingActivity.this, NoticeDetailActivity.class);
                intent.putExtra("title", "用户协议");
                intent.putExtra("titleUrl", NetClass.Web_XieYi1);
                startActivity(intent);
                break;
            case R.id.relViewCui2:
                //隐私政策
                intent = new Intent(SettingActivity.this, NoticeDetailActivity.class);
                intent.putExtra("title", "隐私政策");
                intent.putExtra("titleUrl", NetClass.Web_XieYi2);
                startActivity(intent);
                break;
            case R.id.relViewCui3:
                //注销用户
                startActivity(new Intent(mContext, ZhuXiaoActivity.class));
                break;
        }
    }

    private void logoutMe() {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.logout, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                intent = new Intent(SettingActivity.this, SplashActivity.class);
                startActivity(intent);
                SPTool.addSessionMap(AppSP.UID, "");
                SPTool.addSessionMap(AppSP.USER_NAME, "");
                SPTool.addSessionMap(AppSP.USER_ICON, "");
                SPTool.addSessionMap(AppSP.USER_PHONE, "");
                SPTool.addSessionMap(AppSP.USER_RongToken, "");
                SPTool.addSessionMap(AppSP.USER_TYPE, "0");
                SPTool.addSessionMap(AppSP.isLogin, false);
                RongIM.getInstance().logout();
                ActivityManager.finishActivity();
                finish();

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.left_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_layout:
                onBackPressed();
                break;
        }
    }
}
