package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.utils.ToastFactory;

public class GangWeiDetailActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.gangweidetail_activity);
        init();
    }

    private void init() {
        ImageView back = findViewById(R.id.back);
        ImageView image1 = findViewById(R.id.image1);
        ImageView image2 = findViewById(R.id.image2);
        ImageView image3 = findViewById(R.id.image3);

        back.setOnClickListener(this);
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
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
            case R.id.image1:
                //收藏
                ToastFactory.getToast(mContext, "收藏").show();
                break;
            case R.id.image2:
                //分享
                ToastFactory.getToast(mContext, "分享").show();
                break;
            case R.id.image3:
                //举报
                ToastFactory.getToast(mContext, "举报").show();
                break;
        }
    }
}
