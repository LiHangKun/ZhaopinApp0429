package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.common.NoticeDetailActivity;
import com.lx.zhaopin.common.SplashActivity;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.utils.DataCleanManager;
import com.lx.zhaopin.utils.ToastFactory;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout relView1, relView2, relView3, relView4, relView5, relViewCui1, relViewCui2;
    private TextView okID;
    private TextView text1;
    private static final String TAG = "SettingActivity";
    private Intent intent;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.setting_activity);
        init();
    }

    private void init() {
        topTitle.setText("设置");

        relView1 = findViewById(R.id.relView1);
        relView2 = findViewById(R.id.relView2);
        relView3 = findViewById(R.id.relView3);
        relView4 = findViewById(R.id.relView4);
        relView5 = findViewById(R.id.relView5);
        relViewCui1 = findViewById(R.id.relViewCui1);
        relViewCui2 = findViewById(R.id.relViewCui2);
        okID = findViewById(R.id.okID);

        text1 = findViewById(R.id.text1);

        relView1.setOnClickListener(this);
        relView2.setOnClickListener(this);
        relView3.setOnClickListener(this);
        relView4.setOnClickListener(this);
        relView5.setOnClickListener(this);
        okID.setOnClickListener(this);
        relViewCui1.setOnClickListener(this);
        relViewCui2.setOnClickListener(this);


        clearAllCachecatch();


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
                        ToastFactory.getToast(mContext, "退出").show();


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
        }
    }
}
