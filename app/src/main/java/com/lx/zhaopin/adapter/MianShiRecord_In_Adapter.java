package com.lx.zhaopin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.MianShiDetailType2Activity;
import com.lx.zhaopin.activity.QiuZhiFeedActivity;
import com.lx.zhaopin.bean.MianShiRecordBean;
import com.lx.zhaopin.view.ShoucangCircleImageview;
import com.lx.zhaopin.view.TimeUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MianShiRecord_In_Adapter extends RecyclerView.Adapter<MianShiRecord_In_Adapter.ViewHolder> {


    private List<MianShiRecordBean.DataListBean.InterviewsBean> mData;
    private Context mContext;
    private Intent intent;

    public MianShiRecord_In_Adapter() {
    }

    public MianShiRecord_In_Adapter(Context context, List<MianShiRecordBean.DataListBean.InterviewsBean> allList) {
        mContext = context;
        mData = allList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mianshi_layout, viewGroup, false));
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mianshi_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        if (mData.get(i).getPosition().getRecruiter() != null) {
            Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                    .error(R.mipmap.imageerror)).load(mData.get(i).getPosition().getRecruiter().getAvatar()).into(viewHolder.headImg);
            viewHolder.nameTv.setText(mData.get(i).getPosition().getRecruiter().getName());
            viewHolder.zhiweiName.setText(mData.get(i).getPosition().getRecruiter().getPositionName());
        }
        try {
            int hourTime = 0;
            if (!TextUtils.isEmpty(mData.get(i).getInterviewDate())) {
                String[] times = mData.get(i).getInterviewDate().split(" ");
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = formatter.parse(mData.get(i).getInterviewDate());
            long time = date.getTime();
            String yue = TimeUtils.getMonthTime_(time);
            String day = TimeUtils.getDayTime_(time);
            String hour = TimeUtils.getHour(time);
            String minite = TimeUtils.getMiniteTime(time);
            if (Integer.valueOf(hour) > 12) {
                hourTime = Integer.valueOf(hour) - 12;
                hour = "下午" + hourTime + "点";
            } else {
                hourTime = Integer.valueOf(hour);
                hour = "上午" + hour + "点";
            }
            if (hourTime < 10) {
                viewHolder.mianshiTime.setText("0" + hourTime + ":" + minite);
            } else {
                viewHolder.mianshiTime.setText(hourTime + ":" + minite);
            }
            viewHolder.timeTv.setText(yue + "月" + day + "日 " + hour + minite + "分");
        } catch (Exception e) {
            e.printStackTrace();
        }

        viewHolder.mianshitimeLayout.setVisibility(View.VISIBLE);
        viewHolder.priceTv.setVisibility(View.GONE);

        viewHolder.tv1.setText(mData.get(i).getPosition().getName());
        viewHolder.addressTv.setText(mData.get(i).getPosition().getLocation());
        viewHolder.yearTv.setText(mData.get(i).getPosition().getExperienceYear().getName());
        viewHolder.companyName.setText(mData.get(i).getCompany().getName());
        viewHolder.priceTv.setText(mData.get(i).getPosition().getMinSalary() + "K - " + mData.get(i).getPosition().getMaxSalary() + "K");
        viewHolder.workTv.setText(mData.get(i).getPosition().getEducation().getName());
        viewHolder.tv3.setText(mData.get(i).getInterviewDate().substring(11, mData.get(i).getInterviewDate().length()));
        viewHolder.tv2.setText("面试：" + mData.get(i).getPosition().getName() + " " + mData.get(i).getPosition().getMinSalary() + "-" + mData.get(i).getPosition().getMaxSalary() + "K");

        final String interviewStatus = mData.get(i).getInterviewStatus();
        //1 待接受  2 已拒绝 3 待面试 4 已超时 5 已到达 6 已取消 7 已录取 8 不合适
        viewHolder.mianshiImg.setVisibility(View.VISIBLE);
        switch (interviewStatus) {
            case "1":
                viewHolder.mianshiImg.setImageResource(R.drawable.daitongyi);
                break;
            case "2":
                viewHolder.mianshiImg.setImageResource(R.drawable.yijujue);
                break;
            case "3":
                viewHolder.mianshiImg.setImageResource(R.drawable.daimianshi);
                break;
            case "4":
                viewHolder.mianshiImg.setImageResource(R.drawable.yichaoshi);
                break;
            case "5":
                viewHolder.mianshiImg.setImageResource(R.drawable.yidaoda);
                break;
            case "6":
                viewHolder.mianshiImg.setImageResource(R.drawable.yiquxiao);
                break;
            case "7":
                viewHolder.mianshiImg.setImageResource(R.drawable.yitongyi);
                break;
            case "8":
                viewHolder.mianshiImg.setImageResource(R.drawable.buheshi);
                break;
            default:
                viewHolder.mianshiImg.setVisibility(View.GONE);
                break;
        }


        viewHolder.weimianshiLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到求职者的面试记录

                switch (interviewStatus) {
                    case "7":
                        intent = new Intent(mContext, QiuZhiFeedActivity.class);
                        //userType  0 求职者  1 HR
                        intent.putExtra("userType", "0");
                        intent.putExtra("offerID", mData.get(i).getOfferStatus());
                        mContext.startActivity(intent);
                        break;
                    default:
                        intent = new Intent(mContext, MianShiDetailType2Activity.class);
                        intent.putExtra("interviewId", mData.get(i).getId());
                        mContext.startActivity(intent);
                        break;
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.roundedImageView)
        RoundedImageView roundedImageView;
        @BindView(R.id.tv1)
        TextView tv1;
        //        @BindView(R.id.image1)
//        ImageView image1;
        @BindView(R.id.tv2)
        TextView tv2;
        @BindView(R.id.tv3)
        TextView tv3;
        @BindView(R.id.llView)
        LinearLayout llView;

        @BindView(R.id.time_tv)
        TextView timeTv;
        @BindView(R.id.price_tv)
        TextView priceTv;
        @BindView(R.id.company_name)
        TextView companyName;
        @BindView(R.id.address_tv)
        TextView addressTv;
        @BindView(R.id.year_tv)
        TextView yearTv;
        @BindView(R.id.work_tv)
        TextView workTv;
        @BindView(R.id.head_img)
        ShoucangCircleImageview headImg;
        @BindView(R.id.name_tv)
        TextView nameTv;
        @BindView(R.id.zhiwei_name)
        TextView zhiweiName;
        @BindView(R.id.weimianshi_layout)
        LinearLayout weimianshiLayout;
        @BindView(R.id.mianshi_time)
        TextView mianshiTime;
        @BindView(R.id.mianshi_img)
        ImageView mianshiImg;
        @BindView(R.id.mianshitime_layout)
        LinearLayout mianshitimeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
