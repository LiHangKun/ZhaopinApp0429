package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
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

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private ClearEditText clearEditText;
    private TextView searchTv;
    private FlowLiner flowLiner1;
    private String oldSearchStr = "";
    private ImageView delImage1;
    private List<String> flowData = new ArrayList<>();
    private Intent intent;
    private static final String TAG = "SearchActivity";

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

        oldSearchStr = (String) SharedPreferencesUtil.getData(SearchActivity.this, AppSP.SEARCH, "");
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
            final TextView radioButton = new TextView(SearchActivity.this);
            FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT,
                    FlowLiner.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, ViewUtil.dp2px(SearchActivity.this, 10),
                    ViewUtil.dp2px(SearchActivity.this, 10));
            radioButton.setLayoutParams(layoutParams);
            final String str = flowData.get(i);
            radioButton.setText(str);
            radioButton.setGravity(Gravity.CENTER);
            radioButton.setTextSize(13);
            radioButton.setPadding(ViewUtil.dp2px(SearchActivity.this, 14),
                    ViewUtil.dp2px(SearchActivity.this, 8)
                    , ViewUtil.dp2px(SearchActivity.this, 14),
                    ViewUtil.dp2px(SearchActivity.this, 8));
            radioButton.setTextColor(getResources().getColorStateList(R.color.radio_text_selector_primary_4d4d4d));
            //radioButton.setBackgroundResource(R.drawable.search_selector);
            radioButton.setBackgroundResource(R.drawable.button_shape666);
            radioButton.setFocusable(true);
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*TODO ACTION*/
                    // doSearch(str);

                    //点击流布局上的文字直接搜索
                    intent = new Intent(SearchActivity.this, SearchSuccessActivity.class);
                    intent.putExtra("keyWord", str);
                    intent.putExtra("cid", "");
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

                StyledDialog.init(mContext);
                StyledDialog.buildIosAlert("", "\r是否清除历史记录?", new MyDialogListener() {
                    @Override
                    public void onFirst() {

                    }

                    @Override
                    public void onSecond() {
                        SharedPreferencesUtil.saveData(SearchActivity.this, AppSP.SEARCH, "");
                        flowData = new ArrayList<>();
                        flowLiner1.removeAllViews();


                    }
                }).setBtnColor(R.color.mainColor2, R.color.mainColor1, 0).setBtnText("取消", "确定").show();


            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            //加载历史
            oldSearchStr = (String) SharedPreferencesUtil.getData(SearchActivity.this, AppSP.SEARCH, "");
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
                    SharedPreferencesUtil.saveData(SearchActivity.this, AppSP.SEARCH, oldSearchStr);
                    intent = new Intent(mContext, SearchSuccessActivity.class);
                    intent.putExtra("keyWord", clearEditText.getText().toString().trim());
                    intent.putExtra("cid", "");
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
                showToast("请输入关键词");
            } else {
                if (TextUtils.isEmpty(oldSearchStr)) {
                    oldSearchStr = edStr;
                } else {
                    oldSearchStr = oldSearchStr + "," + edStr;
                }
                SharedPreferencesUtil.saveData(SearchActivity.this, AppSP.SEARCH, oldSearchStr);
                intent = new Intent(SearchActivity.this, SearchSuccessActivity.class);
                intent.putExtra("keyWord", edStr);
                intent.putExtra("cid", "");
                startActivityForResult(intent, 100);
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
