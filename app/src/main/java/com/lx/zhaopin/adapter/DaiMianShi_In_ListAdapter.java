package com.lx.zhaopin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.MianShiDetailType2Activity;
import com.lx.zhaopin.bean.WeimianshiDetailBean;
import com.lx.zhaopin.view.ShoucangCircleImageview;
import com.lx.zhaopin.view.TimeUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaiMianShi_In_ListAdapter extends RecyclerView.Adapter<DaiMianShi_In_ListAdapter.ViewHolder> {



    private List<WeimianshiDetailBean.DataListBean.InterviewsBean> mData;
    private Context mContext;


    public DaiMianShi_In_ListAdapter() {
    }

    public DaiMianShi_In_ListAdapter(Context context, List<WeimianshiDetailBean.DataListBean.InterviewsBean> allList) {
        mContext = context;
        mData = allList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mianshi_layout, viewGroup, false));
        //return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_yiluqu_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                .error(R.mipmap.imageerror)).load(mData.get(i).getPosition().getRecruiter().getAvatar()).into(viewHolder.headImg);

        try {
            if(!TextUtils.isEmpty(mData.get(i).getInterviewDate())){
                String[] times=mData.get(i).getInterviewDate().split(" ");
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = formatter.parse(mData.get(i).getInterviewDate());
            long time = date.getTime();
            String yue = TimeUtils.getMonthTime_(time);
            String day = TimeUtils.getDayTime_(time);
            String hour = TimeUtils.getHour(time);
            String minite = TimeUtils.getMiniteTime(time);
            if (Integer.valueOf(hour) > 12) {
                int hourTime = Integer.valueOf(hour) - 12;
                hour = "下午" + hourTime + "点";
            } else {
                hour = "上午" + hour + "点";
            }
            viewHolder.timeTv.setText(yue + "月" + day + "日 " + hour + minite + "分");
        } catch (Exception e) {
            e.printStackTrace();
        }


        viewHolder.nameTv.setText(mData.get(i).getPosition().getRecruiter().getName());
        viewHolder.zhiweiName.setText(mData.get(i).getPosition().getRecruiter().getPositionName());
        viewHolder.tv1.setText(mData.get(i).getPosition().getName());
        viewHolder.addressTv.setText(mData.get(i).getPosition().getLocation());
        viewHolder.yearTv.setText(mData.get(i).getPosition().getExperienceYear().getName());
        viewHolder.companyName.setText(mData.get(i).getCompany().getName());
        viewHolder.priceTv.setText(mData.get(i).getPosition().getMinSalary() + "K - " + mData.get(i).getPosition().getMaxSalary() + "K");
        viewHolder.workTv.setText(mData.get(i).getPosition().getEducation().getName());
        viewHolder.tv3.setText(mData.get(i).getInterviewDate().substring(11, mData.get(i).getInterviewDate().length()));
        viewHolder.tv2.setText("面试：" + mData.get(i).getPosition().getName() + " " + mData.get(i).getPosition().getMinSalary() + "-" + mData.get(i).getPosition().getMaxSalary() + "K");

        viewHolder.weimianshiLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MianShiDetailType2Activity.class);
                intent.putExtra("interviewId", mData.get(i).getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.llView)
        LinearLayout llView;
        @BindView(R.id.roundedImageView)
        RoundedImageView roundedImageView;
        @BindView(R.id.tv1)
        TextView tv1;
        @BindView(R.id.tv2)
        TextView tv2;
        @BindView(R.id.tv3)
        TextView tv3;
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
