package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.utils.Utility;

public class ZhiWeiYaoYueActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout relView1;
    private RelativeLayout relView2;
    private RelativeLayout relView4;
    private TextView tv0;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView okID;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.zhiweiyaoyue_activity);
        Utility.setActionBar(this);
        //发送职位邀约
        init();
    }

    private void init() {
        ImageView finishBack = findViewById(R.id.finishBack);
        TextView titleName = findViewById(R.id.titleName);
        finishBack.setImageResource(R.drawable.fanhuibaicui);
        finishBack.setVisibility(View.VISIBLE);
        finishBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleName.setText("发送职位邀约");
        titleName.setTextColor(getResources().getColor(R.color.white));


        relView1 = findViewById(R.id.relView1);
        relView2 = findViewById(R.id.relView2);
        relView4 = findViewById(R.id.relView4);

        relView1.setOnClickListener(this);
        relView2.setOnClickListener(this);
        relView4.setOnClickListener(this);
        tv0 = findViewById(R.id.tv0);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        okID = findViewById(R.id.okID);
        tv3.setOnClickListener(this);
        okID.setOnClickListener(this);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relView1:
                //选择岗位
                ToastFactory.getToast(mContext, "选择岗位").show();
                break;
            case R.id.relView2:
                //电话
                ToastFactory.getToast(mContext, "电话").show();
                break;
            case R.id.relView4:
                //时间
                ToastFactory.getToast(mContext, "时间").show();
                break;
            case R.id.okID:
                //发送邀约
                ToastFactory.getToast(mContext, "发送邀约").show();
                break;
            case R.id.tv3:
                //导航
                ToastFactory.getToast(mContext, "导航").show();
                break;
        }

    }
}
