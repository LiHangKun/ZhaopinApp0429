package com.lx.zhaopin.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.utils.Utility;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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
        getNoLinkData();
        initNoLinkOptionsPicker();

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


    private OptionsPickerView pvNoLinkOptions;
    private ArrayList<String> food = new ArrayList<>();
    private ArrayList<String> clothes = new ArrayList<>();
    private ArrayList<String> computer = new ArrayList<>();

    private void initNoLinkOptionsPicker() {// 不联动的多级选项
        pvNoLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                String str = "food:" + food.get(options1)
                        + "\nclothes:" + clothes.get(options2)
                        + "\ncomputer:" + computer.get(options3);

                Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
            }
        })
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                        String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
                        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
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


    private void getNoLinkData() {
        food.add("09点");
        food.add("10点");
        food.add("11点");
        food.add("14点");
        food.add("15点");
        food.add("16点");
        food.add("16点");
        food.add("17点");

        clothes.add("30");
        clothes.add("30");
        clothes.add("30");
        clothes.add("30");
        clothes.add("30");
        clothes.add("30");
        clothes.add("30");

        computer.add("张三");
        computer.add("李四");
        computer.add("王五");
        computer.add("赵六");
        computer.add("周七");
        computer.add("哈哈");
        computer.add("嘻嘻");
    }


    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    /**
     * 日期选择
     *
     * @param activity
     * @param themeResId
     * @param calendar
     */
    public static void showDatePickerDialog(final Activity activity, int themeResId, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                ToastFactory.getToast(activity, "您选择了：" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日").show();
                //TODO   发送EventBus 展示时间段
                //pvNoLinkOptions.show();
                //tv.setText("您选择了：" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    DateFormat format = DateFormat.getDateTimeInstance();
    Calendar calendar = Calendar.getInstance(Locale.CHINA);

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
                showDatePickerDialog(this, 4, calendar);
                pvNoLinkOptions.show();
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
