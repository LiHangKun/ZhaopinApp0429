package com.lx.zhaopin.adapter;

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
import com.lx.zhaopin.activity.MianShiDetailType2Activity;
import com.lx.zhaopin.activity.QiuZhiFeedActivity;
import com.lx.zhaopin.bean.MianShiListBean;
import com.lx.zhaopin.view.ShoucangCircleImageview;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MianShiList_In_Adapter extends RecyclerView.Adapter<MianShiList_In_Adapter.ViewHolder> {



    private List<MianShiListBean.DataListBean.InterviewsBean> mData;
    private Context mContext;
    private Intent intent;

    public MianShiList_In_Adapter() {
    }

    public MianShiList_In_Adapter(Context context, List<MianShiListBean.DataListBean.InterviewsBean> allList) {
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
                .error(R.mipmap.imageerror)).load(mData.get(i).getRecruiter().getAvatar()).into(viewHolder.headImg);
        viewHolder.tv1.setText(mData.get(i).getPosition().getName());
        viewHolder.companyName.setText(mData.get(i).getCompany().getName());
        viewHolder.addressTv.setText(mData.get(i).getLocation());
//        if(null!=mData.get(i).getExperienceYear()){
//            if(!TextUtils.isEmpty(mData.get(i).getPosition().getExperienceYear().getName())){
//                viewHolder.yearTv.setText(mData.get(i).getPosition().getExperienceYear().getName());
//            }
//        }
        viewHolder.priceTv.setText(mData.get(i).getPosition().getMinSalary() + "k-" + mData.get(i).getPosition().getMaxSalary() + "k");
        viewHolder.nameTv.setText(mData.get(i).getRecruiter().getName());
        viewHolder.zhiweiName.setText(mData.get(i).getRecruiter().getPositionName());
        viewHolder.tv3.setText(mData.get(i).getInterviewDate().substring(11, mData.get(i).getInterviewDate().length()));
        viewHolder.tv2.setText("面试：" + mData.get(i).getPosition().getName() + " " + mData.get(i).getPosition().getMinSalary() + "-" + mData.get(i).getPosition().getMaxSalary() + "K");

        final String interviewStatus = mData.get(i).getInterviewStatus();
        //1 待接受  2 已拒绝 3 待面试 4 已超时 5 已到达 6 已取消 7 已录取 8 不合适
        switch (interviewStatus) {
            case "1":
                viewHolder.image1.setImageResource(R.drawable.daitongyi);
                break;
            case "2":
                viewHolder.image1.setImageResource(R.drawable.yijujue);
                break;
            case "3":
                viewHolder.image1.setImageResource(R.drawable.daimianshi);
                break;
            case "4":
                viewHolder.image1.setImageResource(R.drawable.yichaoshi);
                break;
            case "5":
                viewHolder.image1.setImageResource(R.drawable.yidaoda);
                break;
            case "6":
                viewHolder.image1.setImageResource(R.drawable.yiquxiao);
                break;
            case "7":
                viewHolder.image1.setImageResource(R.drawable.yitongyi);
                break;
            case "8":
                viewHolder.image1.setImageResource(R.drawable.buheshi);
                break;
        }


        viewHolder.offerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到求职者的面试记录

                switch (interviewStatus) {
                    case "7":
                        intent = new Intent(mContext, QiuZhiFeedActivity.class);
                        //userType  0 求职者  1 HR
                        intent.putExtra("userType", "0");
                        intent.putExtra("offerID", mData.get(i).getOffer());
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
        @BindView(R.id.image1)
        ImageView image1;
        @BindView(R.id.tv2)
        TextView tv2;
        @BindView(R.id.tv3)
        TextView tv3;
        @BindView(R.id.llView)
        LinearLayout llView;

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
        @BindView(R.id.offer_tv)
        TextView offerTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
