package com.lx.zhaopin.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lx.zhaopin.R;
import com.lxj.xpopup.core.DrawerPopupView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;


public class CustomDrawerPopupView extends DrawerPopupView {
    private static final String TAG = "CustomDrawerPopupView";

    private String[] mVals = new String[]
            {"HelloHelloHelloHelloHelloHello", "AndroidAndroid", "WeclomeWeclome Hi ", "ButtonButtonButton", "TextViewTextView", "Hello",
                    "Android", "WeclomeWeclomeWeclomeWeclomeWeclome", "Button ImageView", "TextView", "Helloworld",
                    "AndroidAndroidAndroidAndroid", "Weclome Hello", "ButtonButtonButton Text", "TextView","HelloHelloHelloHelloHelloHello", "AndroidAndroid", "WeclomeWeclome Hi ", "ButtonButtonButton", "TextViewTextView", "Hello",
                    "Android", "WeclomeWeclomeWeclomeWeclomeWeclome", "Button ImageView", "TextView", "Helloworld",
                    "AndroidAndroidAndroidAndroid" ,"HelloHelloHelloHelloHelloHello", "AndroidAndroid", "WeclomeWeclome Hi ", "ButtonButtonButton", "TextViewTextView", "Hello",
                    "Android", "WeclomeWeclomeWeclomeWeclomeWeclome", "Button ImageView", "TextView", "Helloworld",
                    "AndroidAndroidAndroidAndroid" ,"HelloHelloHelloHelloHelloHello", "AndroidAndroid", "WeclomeWeclome Hi ", "ButtonButtonButton", "TextViewTextView", "Hello",
                    "Android", "WeclomeWeclomeWeclomeWeclomeWeclome", "Button ImageView", "TextView", "Helloworld",
                    "AndroidAndroidAndroidAndroid" ,"HelloHelloHelloHelloHelloHello", "AndroidAndroid", "WeclomeWeclome Hi ", "ButtonButtonButton", "TextViewTextView", "Hello",
                    "Android", "WeclomeWeclomeWeclomeWeclomeWeclome", "Button ImageView", "TextView", "Helloworld",
                    "AndroidAndroidAndroidAndroid","Weclome Hello", "ButtonButtonButton Text", "TextView","HelloHelloHelloHelloHelloHello", "AndroidAndroid", "WeclomeWeclome Hi ", "ButtonButtonButton", "TextViewTextView", "Hello",
                    "Android", "WeclomeWeclomeWeclomeWeclomeWeclome", "Button ImageView", "TextView", "Helloworld",
                    "AndroidAndroidAndroidAndroid" ,"HelloHelloHelloHelloHelloHello", "AndroidAndroid", "WeclomeWeclome Hi ", "ButtonButtonButton", "TextViewTextView", "Hello",
                    "Android", "WeclomeWeclomeWeclomeWeclomeWeclome", "Button ImageView", "TextView", "Helloworld",
                    "AndroidAndroidAndroidAndroid" ,"HelloHelloHelloHelloHelloHello", "AndroidAndroid", "WeclomeWeclome Hi ", "ButtonButtonButton", "TextViewTextView", "Hello",
                    "Android", "WeclomeWeclomeWeclomeWeclomeWeclome", "Button ImageView", "TextView", "Helloworld",
                    "AndroidAndroidAndroidAndroid" ,"HelloHelloHelloHelloHelloHello", "AndroidAndroid", "WeclomeWeclome Hi ", "ButtonButtonButton", "TextViewTextView", "Hello",
                    "Android", "WeclomeWeclomeWeclomeWeclomeWeclome", "Button ImageView", "TextView", "Helloworld",
                    "AndroidAndroidAndroidAndroid","Weclome Hello", "ButtonButtonButton Text", "TextView","HelloHelloHelloHelloHelloHello", "AndroidAndroid", "WeclomeWeclome Hi ", "ButtonButtonButton", "TextViewTextView", "Hello",
                    "Android", "WeclomeWeclomeWeclomeWeclomeWeclome", "Button ImageView", "TextView", "Helloworld",
                    "AndroidAndroidAndroidAndroid" ,"HelloHelloHelloHelloHelloHello", "AndroidAndroid", "WeclomeWeclome Hi ", "ButtonButtonButton", "TextViewTextView", "Hello",
                    "Android", "WeclomeWeclomeWeclomeWeclomeWeclome", "Button ImageView", "TextView", "Helloworld",
                    "AndroidAndroidAndroidAndroid" ,"HelloHelloHelloHelloHelloHello", "AndroidAndroid", "WeclomeWeclome Hi ", "ButtonButtonButton", "TextViewTextView", "Hello",
                    "Android", "WeclomeWeclomeWeclomeWeclomeWeclome", "Button ImageView", "TextView", "Helloworld",
                    "AndroidAndroidAndroidAndroid" ,"HelloHelloHelloHelloHelloHello", "AndroidAndroid", "WeclomeWeclome Hi ", "ButtonButtonButton", "TextViewTextView", "Hello",
                    "Android", "WeclomeWeclomeWeclomeWeclomeWeclome", "Button ImageView", "TextView", "Helloworld",
                    "AndroidAndroidAndroidAndroid", };
    private final Context mContext;


    public CustomDrawerPopupView(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_drawer_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        final TagFlowLayout tagFlowLayout = findViewById(R.id.tagFlowLayout);
        Log.e("tag", "CustomDrawerPopupView onCreate");


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
    protected void onShow() {
        super.onShow();
        Log.e(TAG, "CustomDrawerPopupView onShow");
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        Log.e(TAG, "CustomDrawerPopupView onDismiss");
    }
}