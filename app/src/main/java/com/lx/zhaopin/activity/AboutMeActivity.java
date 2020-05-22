package com.lx.zhaopin.activity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.AboutMeBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.TellUtil;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionGrant;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import okhttp3.Response;

public class AboutMeActivity extends BaseActivity {
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.relView1)
    RelativeLayout relView1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.relView2)
    RelativeLayout relView2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.relView3)
    RelativeLayout relView3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.relView4)
    RelativeLayout relView4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.relView5)
    RelativeLayout relView5;
    private String phone;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.aboutme_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        topTitle.setText("关于我们");

        aboutMe();

    }

    private void aboutMe() {
        Map<String, String> params = new HashMap<>();
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.aboutMe, params, new BaseCallback<AboutMeBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, AboutMeBean resultBean) {
                tv1.setText(resultBean.getCompany());
                tv2.setText(resultBean.getPhone() + " >");
                tv3.setText(resultBean.getEmail());
                tv4.setText(resultBean.getWebsite());
                tv5.setText(resultBean.getAddress());

                phone = resultBean.getPhone();

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


    @OnClick(R.id.relView2)
    public void onClick() {
        if (!TextUtils.isEmpty(phone)) {
            callPhone();
        }
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
                MPermissions.requestPermissions(this, AppSP.PMS_LOCATION, Manifest.permission.CALL_PHONE);
            } else {
                pmsLocationSuccess();
            }
        }
    }
}
