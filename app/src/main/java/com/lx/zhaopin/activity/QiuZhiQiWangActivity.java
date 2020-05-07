package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.utils.ToastFactory;

import java.util.ArrayList;

public class QiuZhiQiWangActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.qiuzhiqiwang_activity);
        init();
    }

    private void init() {
        topTitle.setText("编辑求职期望");
        rightText.setText("删除");
        rightText.setVisibility(View.VISIBLE);
        rightText.setOnClickListener(this);

        getNoLinkData();
        initNoLinkOptionsPicker();

        LinearLayout llView1 = findViewById(R.id.llView1);
        LinearLayout llView2 = findViewById(R.id.llView2);
        LinearLayout llView3 = findViewById(R.id.llView3);
        LinearLayout llView4 = findViewById(R.id.llView4);

        llView1.setOnClickListener(this);
        llView2.setOnClickListener(this);
        llView3.setOnClickListener(this);
        llView4.setOnClickListener(this);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);


    }


    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    private OptionsPickerView pvNoLinkOptions;
    private ArrayList<String> food = new ArrayList<>();
    private ArrayList<String> clothes = new ArrayList<>();
    private ArrayList<String> computer = new ArrayList<>();


    private void getNoLinkData() {
        food.add("3K");
        food.add("4K");
        food.add("5K");
        food.add("6K");
        food.add("7K");
        food.add("8K");
        food.add("9K");
        food.add("10K");

        clothes.add("3K");
        clothes.add("4K");
        clothes.add("5K");
        clothes.add("6K");
        clothes.add("7K");
        clothes.add("8K");
        clothes.add("9K");
        clothes.add("10K");

        computer.add("张三");
        computer.add("李四");
        computer.add("王五");
        computer.add("赵六");
        computer.add("周七");
        computer.add("哈哈");
        computer.add("嘻嘻");
    }

    private void initNoLinkOptionsPicker() {// 不联动的多级选项
        pvNoLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                //String str = "food:" + food.get(options1) + "\nclothes:" + clothes.get(options2) + "\ncomputer:" + computer.get(options3);
                String time2 = food.get(options1) + " - " + clothes.get(options2);
                tv4.setText(time2);

                //Toast.makeText(mContext, time2, Toast.LENGTH_SHORT).show();
            }
        })
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                        //提示出的选择的索引                        String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
                        //Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
                    }
                })
                .setItemVisibleCount(5)
                // .setSelectOptions(0, 1, 1)
                .build();
        /*pvNoLinkOptions.setNPicker(food, clothes, computer);
        pvNoLinkOptions.setSelectOptions(0, 1, 1);*/

        pvNoLinkOptions.setNPicker(food, clothes);
        pvNoLinkOptions.setSelectOptions(0, 1);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rightText:
                ToastFactory.getToast(mContext, "删除").show();
                break;
            case R.id.llView1:
                //期望职位
                ToastFactory.getToast(mContext, "期望职位").show();
                break;
            case R.id.llView2:
                //期望行业
                ToastFactory.getToast(mContext, "期望行业").show();
                break;
            case R.id.llView3:
                //工作城市
                ToastFactory.getToast(mContext, "工作城市").show();
                break;
            case R.id.llView4:
                //薪资要求
                pvNoLinkOptions.show();
                break;
        }
    }
}
