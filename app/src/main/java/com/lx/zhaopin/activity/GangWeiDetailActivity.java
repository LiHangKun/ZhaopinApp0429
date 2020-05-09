package com.lx.zhaopin.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.view.FlowLiner;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GangWeiDetailActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.image3)
    ImageView image3;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6)
    TextView tv6;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.flowLiner1)
    FlowLiner flowLiner1;
    @BindView(R.id.flowLiner2)
    FlowLiner flowLiner2;
    @BindView(R.id.roundedImageView)
    RoundedImageView roundedImageView;
    @BindView(R.id.tv8)
    TextView tv8;
    @BindView(R.id.tv9)
    TextView tv9;
    @BindView(R.id.tv10)
    TextView tv10;
    @BindView(R.id.tv11)
    TextView tv11;
    @BindView(R.id.tv12)
    TextView tv12;
    @BindView(R.id.daoHang)
    LinearLayout daoHang;
    @BindView(R.id.dibuView1)
    TextView dibuView1;
    @BindView(R.id.liJiGouTongTV)
    TextView liJiGouTongTV;
    @BindView(R.id.shenQingZhiwei)
    TextView shenQingZhiwei;
    @BindView(R.id.dibuView2)
    LinearLayout dibuView2;
    @BindView(R.id.dibuView3)
    TextView dibuView3;
    @BindView(R.id.dibuView4)
    TextView dibuView4;
    @BindView(R.id.llViewGongSi)
    LinearLayout llViewGongSi;
    private Intent intent;

    String Cui = "1.独立Cover产品需求对接及解决方案制定，并高效输出相对应付物2.系统化完成需求挖掘、流程梳理（业务流、操作流、信息流）及相关产品规则制定1.独立Cover产品需求对接及解决方案制定，并高效输出相对应付物2.系统化完成需求挖掘、流程梳理（业务流、操作流、信息流）及相关产品规则制定1.独立Cover产品需求对接及解决方案制定，并高效输出相对应付物2.系统化完成需求挖掘、流程梳理（业务流、操作流、信息流）及相关产品规则制定";
    private int maxLine = 5;
    private SpannableString elipseString;//收起的文字
    private SpannableString notElipseString;//展开的文字


    private void getLastIndexForLimit(TextView tv, int maxLine, String content) {
        //获取TextView的画笔对象
        TextPaint paint = tv.getPaint();
        //每行文本的布局宽度
        int width = getResources().getDisplayMetrics().widthPixels - dip2px(this, 40);
        //实例化StaticLayout 传入相应参数
        StaticLayout staticLayout = new StaticLayout(content, paint, width, Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
        //判断content是行数是否超过最大限制行数3行
        if (staticLayout.getLineCount() > maxLine) {
            //定义展开后的文本内容
            String string1 = content + "    收起";
            notElipseString = new SpannableString(string1);
            //给收起两个字设成蓝色
            notElipseString.setSpan(new ForegroundColorSpan(Color.parseColor("#0079e2")), string1.length() - 2, string1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            //获取到第三行最后一个文字的下标
            int index = staticLayout.getLineStart(maxLine) - 1;
            //定义收起后的文本内容
            String substring = content.substring(0, index - 4) + "..." + "查看全部";
            elipseString = new SpannableString(substring);
            //给查看全部设成蓝色
            elipseString.setSpan(new ForegroundColorSpan(Color.parseColor("#0079e2")), substring.length() - 4, substring.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //设置收起后的文本内容
            tv.setText(elipseString);
            tv.setOnClickListener(this);
            //将textview设成选中状态 true用来表示文本未展示完全的状态,false表示完全展示状态，用于点击时的判断
            tv.setSelected(true);
        } else {
            //没有超过 直接设置文本
            tv.setText(content);
            tv.setOnClickListener(null);
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context mContext, float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    //llViewGongSi
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.gangweidetail_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        getLastIndexForLimit(tv7, maxLine, Cui);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }


    /*
    底部按钮部分
    立即沟通 dibuView1
    立即沟通和申请职位 dibuView2  liJiGouTongTV   shenQingZhiwei
    申请职位 dibuView3
    预约面试 dibuView4
    */

    @OnClick({R.id.back, R.id.image1, R.id.image2, R.id.image3, R.id.dibuView1, R.id.liJiGouTongTV, R.id.shenQingZhiwei, R.id.dibuView2, R.id.dibuView3, R.id.dibuView4, R.id.daoHang, R.id.llViewGongSi, R.id.tv7})
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
            case R.id.daoHang:
                ToastFactory.getToast(mContext, "导航").show();
                break;
            case R.id.dibuView1:
                //立即沟通
                ToastFactory.getToast(mContext, "立即沟通长的").show();
                break;
            case R.id.liJiGouTongTV:
                //立即沟通
                ToastFactory.getToast(mContext, "立即沟通端的").show();
                break;
            case R.id.shenQingZhiwei:
                //申请职位
                ToastFactory.getToast(mContext, "申请职位").show();
                break;
            case R.id.dibuView2:
                //不要
                break;
            case R.id.dibuView3:
                //申请职位
                ToastFactory.getToast(mContext, "申请职位").show();
                break;
            case R.id.dibuView4:
                ToastFactory.getToast(mContext, "预约面试").show();
                break;
            case R.id.llViewGongSi:
                //公司
                intent = new Intent(mContext, QiYeInfoActivity.class);
                intent.putExtra("qiYeID", "崔崔");
                startActivity(intent);
                break;
            case R.id.tv7:
                //岗位详情的文本,查看全部
                if (view.isSelected()) {
                    //如果是收起的状态
                    tv7.setText(Cui);
                    tv7.setSelected(false);
                } else {
                    //如果是展开的状态
                    tv7.setText(Cui);
                    tv7.setSelected(true);
                }
                break;
        }
    }
}
