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
import com.lx.zhaopin.activity.QiuZhiFeedActivity;
import com.lx.zhaopin.bean.HRYiLuQuBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HRYiLuQu_In_Adapter extends RecyclerView.Adapter<HRYiLuQu_In_Adapter.ViewHolder> {


    private List<HRYiLuQuBean.DataListBean.OfferListBean> mData;
    private Context mContext;

    public HRYiLuQu_In_Adapter() {
    }

    public HRYiLuQu_In_Adapter(Context context, List<HRYiLuQuBean.DataListBean.OfferListBean> allList) {
        mContext = context;
        mData = allList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_yiluqu_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror).error(R.mipmap.imageerror))
                .load(mData.get(i).getMember().getAvatar()).into(viewHolder.roundedImageView);

        viewHolder.tv1.setText(mData.get(i).getMember().getName());
        viewHolder.tv2.setText("面试：" + mData.get(i).getPosition().getName() + " " + mData.get(i).getPosition().getMinSalary() + " - " + mData.get(i).getPosition().getMaxSalary() + "K");
        viewHolder.tv3.setText(mData.get(i).getSendDate());


        String offerStatus = mData.get(i).getOfferStatus();
        //offerStatus  1待处理 2 已接受 3 已拒绝
        switch (offerStatus) {
            case "1":
                viewHolder.image1.setImageResource(R.drawable.daitongyi);
                break;
            case "2":
                viewHolder.image1.setImageResource(R.drawable.yitongyi);
                break;
            case "3":
                viewHolder.image1.setImageResource(R.drawable.yijujue);
                break;
        }

        viewHolder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, QiuZhiFeedActivity.class);
                intent.putExtra("offerID", mData.get(i).getId());
                intent.putExtra("userType", "1");
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
