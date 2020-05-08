package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.view.ClearEditText;

public class SearchSuccessActivity extends BaseActivity implements View.OnClickListener {

    private TextView searchTv;
    private ImageView back;
    private ClearEditText clearEditText;
    private String keyWord;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.searchsuccess_activity);
        init();
    }

    private void init() {
        keyWord = getIntent().getStringExtra("KeyWord");

        back = findViewById(R.id.back);
        searchTv = findViewById(R.id.searchTv);
        clearEditText = findViewById(R.id.clearEditText);
        clearEditText.setText(keyWord);
        back.setOnClickListener(this);
        searchTv.setOnClickListener(this);


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
            case R.id.back:
                finish();
                break;
            case R.id.searchTv:
                if (TextUtils.isEmpty(clearEditText.getText().toString().trim())) {
                    ToastFactory.getToast(mContext, "请输入关键词").cancel();
                    return;
                } else {
                    //开始搜索
                    String edStr = clearEditText.getText().toString().trim();

                    ToastFactory.getToast(mContext, "开始------" + edStr).show();

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
                ToastFactory.getToast(mContext, "请输入关键词").show();
            } else {
                //开始搜索
                ToastFactory.getToast(mContext, "开始------" + edStr).show();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
