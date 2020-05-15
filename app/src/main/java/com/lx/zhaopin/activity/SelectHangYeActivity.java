package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

//首页的选择行业
public class SelectHangYeActivity extends BaseActivity {


    private String[] mVals = new String[]
            {"HelloHelloHelloHelloHelloHello", "AndroidAndroid", "WeclomeWeclome Hi ", "ButtonButtonButton", "TextViewTextView", "Hello",
                    "Android", "WeclomeWeclomeWeclomeWeclomeWeclome", "Button ImageView", "TextView", "Helloworld",
                    "AndroidAndroidAndroidAndroid", "Weclome Hello", "ButtonButtonButton Text", "TextView",};


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.selecthangye_activity);
        initMe();
    }

    private void initMe() {
        final TagFlowLayout tagFlowLayout = findViewById(R.id.tagFlowLayout);

        tagFlowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "FlowLayout Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        tagFlowLayout.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.tv, tagFlowLayout, false);

                tv.setText(s);
                return tv;
            }

        });

    }

    @Override
    protected void initEvent() {



    }

    @Override
    protected void initData() {

    }
}
