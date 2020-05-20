package com.lx.zhaopin.rong;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

public class ConversationActivity extends AppCompatActivity implements View.OnClickListener {

    private String param;
    private static final String TAG = "ConversationActivity";
    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hua_activity);
        init();
    }

    private void init() {
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(this);
        initPhotoError();
        TextView titleName = findViewById(R.id.titleName);


        Uri uri = getIntent().getData();
        if (uri != null) {
            param = uri.getQueryParameter("title");
            if (null != param) {
            }
            userId = uri.getQueryParameter("targetId");

            Log.i(TAG, "initView: " + param + "-----" + userId);
        }

        getTitleName(titleName, userId);


        //titleName.setText(param);

    }

    private void getTitleName(final TextView titleName, String userId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("userId", userId);
        OkHttpHelper.getInstance().post(ConversationActivity.this, NetClass.BASE_URL + NetCuiMethod.getRongUserInfo, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                titleName.setText(resultBean.getName());

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void initPhotoError() {
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

}
