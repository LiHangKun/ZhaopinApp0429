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

public class ConversationActivity extends AppCompatActivity implements View.OnClickListener {

    private String param;
    private static final String TAG = "ConversationActivity";

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
            String userId = uri.getQueryParameter("targetId");

            Log.i(TAG, "initView: " + param + "-----" + userId);
        }
        titleName.setText(param);

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
