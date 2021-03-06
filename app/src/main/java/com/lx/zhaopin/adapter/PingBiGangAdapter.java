package com.lx.zhaopin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.bean.PingBiGangWeiBean;
import com.lx.zhaopin.utils.ViewUtil;
import com.lx.zhaopin.view.FlowLiner;
import com.lx.zhaopin.view.ShoucangCircleImageview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PingBiGangAdapter extends RecyclerView.Adapter<PingBiGangAdapter.ViewHolder> {



    private List<PingBiGangWeiBean.DataListBean> mData;
    private Context mContext;
    private OnItemClickListener itemClickListener;

    public PingBiGangAdapter() {
    }

    public PingBiGangAdapter(Context context, List<PingBiGangWeiBean.DataListBean> allList) {
        mContext = context;
        mData = allList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pingbigang_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int po) {
        String positionType = mData.get(po).getPositionType();
        switch (positionType) {
            case "1":
            case "2":
                viewHolder.imageView1.setVisibility(View.GONE);
                break;
            case "3":
                viewHolder.imageView1.setVisibility(View.VISIBLE);
                break;
        }
        //0.已停招，1.未停招
        String opened = mData.get(po).getOpened();
        switch (opened) {
            case "0":
                viewHolder.imageView2.setVisibility(View.VISIBLE);
                viewHolder.tv1.setTextColor(mContext.getResources().getColor(R.color.zhiwei_location_text));
                viewHolder.tv2.setTextColor(mContext.getResources().getColor(R.color.wode_save_color));
                viewHolder.tv3.setTextColor(mContext.getResources().getColor(R.color.zhiwei_location_text));
                viewHolder.tv4.setTextColor(mContext.getResources().getColor(R.color.zhiwei_location_text));
                viewHolder.tv5.setTextColor(mContext.getResources().getColor(R.color.zhiwei_location_text));
                viewHolder.tv6.setTextColor(mContext.getResources().getColor(R.color.zhiwei_location_text));
                viewHolder.tv8.setTextColor(mContext.getResources().getColor(R.color.zhiwei_location_text));
                viewHolder.tv9.setTextColor(mContext.getResources().getColor(R.color.zhiwei_location_text));
                viewHolder.pointTv.setBackgroundColor(mContext.getResources().getColor(R.color.zhiwei_location_text));
                viewHolder.mengbanImageView.setVisibility(View.VISIBLE);
                break;
            case "1":
                viewHolder.imageView2.setVisibility(View.GONE);
                viewHolder.tv1.setTextColor(mContext.getResources().getColor(R.color.text_color));
                viewHolder.tv2.setTextColor(mContext.getResources().getColor(R.color.wode_xiaoxi_color));
                viewHolder.tv3.setTextColor(mContext.getResources().getColor(R.color.text_color));
                viewHolder.tv4.setTextColor(mContext.getResources().getColor(R.color.text_color));
                viewHolder.tv5.setTextColor(mContext.getResources().getColor(R.color.text_color));
                viewHolder.tv6.setTextColor(mContext.getResources().getColor(R.color.text_color));
                viewHolder.tv8.setTextColor(mContext.getResources().getColor(R.color.text_color));
                viewHolder.tv9.setTextColor(mContext.getResources().getColor(R.color.text_color));
                viewHolder.pointTv.setBackgroundColor(mContext.getResources().getColor(R.color.text_color));
                viewHolder.mengbanImageView.setVisibility(View.GONE);
                break;
        }

        viewHolder.tv1.setText(mData.get(po).getName());
        viewHolder.tv2.setText(mData.get(po).getMinSalary() + "K - " + mData.get(po).getMaxSalary() + "K");
        viewHolder.tv3.setText(mData.get(po).getCity().getName());
        viewHolder.tv4.setText(mData.get(po).getExperienceYear().getName());
        viewHolder.tv5.setText(mData.get(po).getEducation().getName());
        viewHolder.tv8.setText(mData.get(po).getCompany().getName());
        viewHolder.tv6.setText(mData.get(po).getRecruiter().getName());
        viewHolder.tv9.setText(mData.get(po).getRecruiter().getPositionName());
        Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                .error(R.mipmap.imageerror)).load(mData.get(po).getRecruiter().getAvatar()).into(viewHolder.roundedImageView);


        viewHolder.tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.OnItemClickListener(viewHolder.tv7, mData.get(po).getId(), mData.get(po).getOpened());
                }
            }
        });

        viewHolder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.OnItemClickListener(viewHolder.llView, mData.get(po).getId(), mData.get(po).getOpened());
                }
            }
        });


        String workfare = mData.get(po).getWorkfare();

        if (TextUtils.isEmpty(workfare)) {
            viewHolder.flowLiner.setVisibility(View.GONE);
        } else {
            String[] split = workfare.split(",");
            List<String> flowData = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                flowData.add(split[i]);
            }
            viewHolder.flowLiner.removeAllViews();
            for (int i = 0; i < flowData.size(); i++) {
                final TextView radioButton = new TextView(mContext);
                FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT, FlowLiner.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 0, ViewUtil.dp2px(mContext, 10), ViewUtil.dp2px(mContext, 10));
                radioButton.setLayoutParams(layoutParams);
                final String str = flowData.get(i);
                radioButton.setText(str);
                radioButton.setGravity(Gravity.CENTER);
                radioButton.setTextSize(12);
                radioButton.setPadding(ViewUtil.dp2px(mContext, 10), ViewUtil.dp2px(mContext, 6), ViewUtil.dp2px(mContext, 10), ViewUtil.dp2px(mContext, 6));
                if(opened.equals("0")){
                    radioButton.setTextColor(mContext.getResources().getColorStateList(R.color.zhiwei_location_text));
                }else if(opened.equals("1")){
                    radioButton.setTextColor(mContext.getResources().getColorStateList(R.color.text_color));
                }

                //radioButton.setBackgroundResource(R.drawable.search_selector);
                radioButton.setBackgroundResource(R.drawable.button_shape03);
                radioButton.setFocusable(true);
                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                viewHolder.flowLiner.addView(radioButton);
            }
        }


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv1)
        TextView tv1;
        @BindView(R.id.imageView1)
        ImageView imageView1;
        @BindView(R.id.imageView2)
        ImageView imageView2;
        @BindView(R.id.tv2)
        TextView tv2;
        @BindView(R.id.tv3)
        TextView tv3;
        @BindView(R.id.tv4)
        TextView tv4;
        @BindView(R.id.tv5)
        TextView tv5;
        @BindView(R.id.flowLiner)
        FlowLiner flowLiner;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;
        @BindView(R.id.llView)
        LinearLayout llView;
        @BindView(R.id.roundedImageView)
        ShoucangCircleImageview roundedImageView;
        @BindView(R.id.tv6)
        TextView tv6;
        @BindView(R.id.tv7)
        TextView tv7;
        @BindView(R.id.tv8)
        TextView tv8;
        @BindView(R.id.tv9)
        TextView tv9;
        @BindView(R.id.mengbanImageView)
        ShoucangCircleImageview mengbanImageView;
        @BindView(R.id.point_tv)
        View pointTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void OnItemClickListener(View view, String id, String open);
    }

    public void setOnItemClickListener(OnItemClickListener OnItemClickListener) {
        itemClickListener = OnItemClickListener;
    }

}
