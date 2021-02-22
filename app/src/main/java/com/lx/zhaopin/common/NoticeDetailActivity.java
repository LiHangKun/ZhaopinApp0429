package com.lx.zhaopin.common;

import android.Manifest;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.utils.TellUtil;
import com.lx.zhaopin.view.MyWebView;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionGrant;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class NoticeDetailActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;
    private WebView webView;
    private String phone;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.noticedetail_activity);
        ButterKnife.bind(this);
        init();
    }


    private void init() {
        String title = getIntent().getStringExtra("title");
        String titleUrl = getIntent().getStringExtra("titleUrl");
        baseTop.setVisibility(View.GONE);


        MyWebView webView1 = findViewById(R.id.webView);
        webView = webView1.getWebView();


        titleTv.setText(title);
        titleLayout.setBackgroundColor(getResources().getColor(R.color.white));
        webView.loadUrl(titleUrl);

        WebSettings settings = webView1.getWebView().getSettings();
        // 设置可以支持缩放
        settings.setSupportZoom(true);
        // 设置支持js
        settings.setJavaScriptEnabled(true);
        // 关闭缓存
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 支持自动加载图片
        settings.setLoadsImagesAutomatically(true);
        // 设置出现缩放工具
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        // 扩大比例的缩放
        settings.setUseWideViewPort(true);
        // 自适应屏幕
        settings.setLoadWithOverviewMode(true);

        settings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("用户单击超连接", url);
                //判断用户单击的是那个超连接
                String tag = "tel:";
                if (url.contains(tag)) {
                    String mobile = url.substring(url.lastIndexOf("/") + 1);

                    String a = mobile.replaceAll("%EF%BC%8D", "");
                    Log.e("mobile----------->", a);
                    phone = a;
                    callPhone();
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });


    }

    @PermissionGrant(AppSP.PMS_LOCATION)
    public void pmsLocationSuccess() {
        //权限授权成功
        TellUtil.tell(mContext, phone);
    }

    /*拨打电话*/
    private void callPhone() {
        if (null != phone) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                MPermissions.requestPermissions(this, AppSP.PMS_LOCATION,
                        Manifest.permission.CALL_PHONE
                );
            } else {
                pmsLocationSuccess();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();//返回上个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);//退出H5界面
    }


    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

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
