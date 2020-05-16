package com.lx.zhaopin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.MianShiDetailType1Activity;
import com.lx.zhaopin.bean.MianShiListBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaiMianShi_In_ListAdapter extends RecyclerView.Adapter<DaiMianShi_In_ListAdapter.ViewHolder> {

    private List<MianShiListBean.DataListBean.InterviewsBean> mData;
    private Context mContext;


    public DaiMianShi_In_ListAdapter() {
    }

    public DaiMianShi_In_ListAdapter(Context context, List<MianShiListBean.DataListBean.InterviewsBean> allList) {
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
                .error(R.mipmap.imageerror)).load(mData.get(i).getCompany().getLogo()).into(viewHolder.roundedImageView);

        viewHolder.tv1.setText(mData.get(i).getCompany().getName());
        viewHolder.tv3.setText(mData.get(i).getInterviewDate());
        viewHolder.tv2.setText("面试：" + mData.get(i).getPosition().getName() + " " + mData.get(i).getPosition().getMinSalary() + "-" + mData.get(i).getPosition().getMaxSalary() + "K");

        viewHolder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MianShiDetailType1Activity.class);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
