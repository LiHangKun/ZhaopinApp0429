package com.lx.zhaopin.hractivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.utils.SharedPreferencesUtil;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.utils.ViewUtil;
import com.lx.zhaopin.view.ClearEditText;
import com.lx.zhaopin.view.FlowLiner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HRSearchActivity extends BaseActivity implements View.OnClickListener {

    private ClearEditText clearEditText;
    private TextView searchTv;
    private FlowLiner flowLiner1;
    private String oldSearchStr = "";
    private ImageView delImage1;
    private List<String> flowData = new ArrayList<>();
    private Intent intent;
    private static final String TAG = "HRSearchActivity";

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.search_activity);
        init();
    }

    private void init() {
        clearEditText = findViewById(R.id.clearEditText);
        searchTv = findViewById(R.id.searchTv);

        searchTv.setOnClickListener(this);
        flowLiner1 = findViewById(R.id.flowLiner1);
        delImage1 = findViewById(R.id.delImage1);

        oldSearchStr = (String) SharedPreferencesUtil.getData(HRSearchActivity.this, AppSP.SEARCH_USER_NAME, "");
        if (!TextUtils.isEmpty(oldSearchStr)) {
            delImage1.setVisibility(View.VISIBLE);
            String oldArray[] = oldSearchStr.split(",");
            List list = Arrays.asList(oldArray);
            Set set = new HashSet(list);
            oldArray = (String[]) set.toArray(new String[0]);
            flowData = Arrays.asList(oldArray);
            setUpFlowLinear();
        }

    }


    private void setUpFlowLinear() {
        flowLiner1.removeAllViews();

        for (int i = 0; i < flowData.size(); i++) {
            final TextView radioButton = new TextView(HRSearchActivity.this);
            FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT, FlowLiner.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, ViewUtil.dp2px(HRSearchActivity.this, 10), ViewUtil.dp2px(HRSearchActivity.this, 10));
            radioButton.setLayoutParams(layoutParams);
            final String str = flowData.get(i);
            radioButton.setText(str);
            radioButton.setGravity(Gravity.CENTER);
            radioButton.setTextSize(13);
            radioButton.setPadding(ViewUtil.dp2px(HRSearchActivity.this, 18), ViewUtil.dp2px(HRSearchActivity.this, 6), ViewUtil.dp2px(HRSearchActivity.this, 18), ViewUtil.dp2px(HRSearchActivity.this, 6));
            radioButton.setTextColor(getResources().getColorStateList(R.color.radio_text_selector_primary_4d4d4d));
            //radioButton.setBackgroundResource(R.drawable.search_selector);
            radioButton.setBackgroundResource(R.drawable.button_shape03);
            radioButton.setFocusable(true);
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*TODO ACTION*/
                    // doSearch(str);

                    //点击流布局上的文字直接搜索
                    intent = new Intent(HRSearchActivity.this, HRSearchSuccessActivity.class);
                    intent.putExtra("KeyWord", str);
                    startActivityForResult(intent, 100);
                    //隐藏软键盘
                    hideInputMethod();
                }
            });
            flowLiner1.addView(radioButton);
        }


        delImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtil.saveData(HRSearchActivity.this, AppSP.SEARCH_USER_NAME, "");
                flowData = new ArrayList<>();
                flowLiner1.removeAllViews();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            //加载历史
            oldSearchStr = (String) SharedPreferencesUtil.getData(HRSearchActivity.this, AppSP.SEARCH_USER_NAME, "");
            if (!TextUtils.isEmpty(oldSearchStr)) {
                delImage1.setVisibility(View.VISIBLE);
                String oldArray[] = oldSearchStr.split(",");
                List list = Arrays.asList(oldArray);
                Set set = new HashSet(list);
                oldArray = (String[]) set.toArray(new String[0]);
                flowData = Arrays.asList(oldArray);
                setUpFlowLinear();
            }
            Log.i(TAG, "onActivityResult: 执行方法");
        }
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchTv:
                //搜索
                if (TextUtils.isEmpty(clearEditText.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "请输入关键词").cancel();
                    return;
                } else {
                    //开始搜索
                    String edStr = clearEditText.getText().toString().trim();
                    if (TextUtils.isEmpty(oldSearchStr)) {
                        oldSearchStr = edStr;
                    } else {
                        oldSearchStr = oldSearchStr + "," + edStr;
                    }
                    SharedPreferencesUtil.saveData(HRSearchActivity.this, AppSP.SEARCH_USER_NAME, oldSearchStr);
                    intent = new Intent(mContext, HRSearchSuccessActivity.class);
                    intent.putExtra("KeyWord", clearEditText.getText().toString().trim());
                    startActivityForResult(intent, 100);

                }
                break;
        }
    }


    //这个是处理输入法键盘上的搜索按钮
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction()) {
            String edStr = clearEditText.getText().toString().trim();
            if (TextUtils.isEmpty(edStr)) {
                ToastFactory.getToast(mContext, "请输入关键词").cancel();
            } else {
                if (TextUtils.isEmpty(oldSearchStr)) {
                    oldSearchStr = edStr;
                } else {
                    oldSearchStr = oldSearchStr + "," + edStr;
                }
                SharedPreferencesUtil.saveData(HRSearchActivity.this, AppSP.SEARCH_USER_NAME, oldSearchStr);
                intent = new Intent(HRSearchActivity.this, HRSearchSuccessActivity.class);
                intent.putExtra("KeyWord", edStr);
                startActivityForResult(intent, 100);
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
