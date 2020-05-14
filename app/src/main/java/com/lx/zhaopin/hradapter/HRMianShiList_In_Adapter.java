package com.lx.zhaopin.hradapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.MianShiDetailType1Activity;
import com.lx.zhaopin.bean.HRMianShiListBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HRMianShiList_In_Adapter extends RecyclerView.Adapter<HRMianShiList_In_Adapter.ViewHolder> {


    private List<HRMianShiListBean.DataListBean.IntervierListBean> mData;
    private Context mContext;

    public HRMianShiList_In_Adapter() {
    }

    public HRMianShiList_In_Adapter(Context context, List<HRMianShiListBean.DataListBean.IntervierListBean>  allList) {
        mContext = context;
        mData = allList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mianshi_layout, viewGroup, false));
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_yiluqu_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                .error(R.mipmap.imageerror)).load(mData.get(i).getJobhunter().getAvatar()).into(viewHolder.roundedImageView);

        viewHolder.tv1.setText(mData.get(i).getJobhunter().getName());
        viewHolder.tv3.setText(mData.get(i).getInterviewDate());
        viewHolder.tv2.setText("面试：" + mData.get(i).getPosition().getName() + " " + mData.get(i).getPosition().getMinSalary() + "-" + mData.get(i).getPosition().getMaxSalary() + "K");

        String interviewStatus = mData.get(i).getInterviewStatus();
        //1 待接受  2 已拒绝 3 待面试 4 已超时 5 已到达 6 已取消 7 已录取 8 不合适
        switch (interviewStatus) {
            case "1":
                viewHolder.image1.setImageResource(R.drawable.daitongyi);
                break;
            case "2":
                viewHolder.image1.setImageResource(R.drawable.yijujue);
                break;
            case "3":
                viewHolder.image1.setImageResource(R.drawable.hom1s);
                break;
            case "4":
                viewHolder.image1.setImageResource(R.drawable.yichaoshi);
                break;
            case "5":
                viewHolder.image1.setImageResource(R.drawable.hom1s);
                break;
            case "6":
                viewHolder.image1.setImageResource(R.drawable.yiquxiao);
                break;
            case "7":
                viewHolder.image1.setImageResource(R.drawable.yitongyi);
                break;
            case "8":
                viewHolder.image1.setImageResource(R.drawable.hom1s);
                break;
        }


        viewHolder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到HR的面试详情
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

        @BindView(R.id.roundedImageView)
        RoundedImageView roundedImageView;
        @BindView(R.id.tv1)
        TextView tv1;
        @BindView(R.id.image1)
        ImageView image1;
        @BindView(R.id.tv2)
        TextView tv2;
        @BindView(R.id.tv3)
        TextView tv3;
        @BindView(R.id.llView)
        LinearLayout llView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
