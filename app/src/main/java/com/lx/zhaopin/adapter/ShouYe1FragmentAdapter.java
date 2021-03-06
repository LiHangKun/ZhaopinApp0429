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
import com.lx.zhaopin.bean.ShouYeQiuZhiZheBean;
import com.lx.zhaopin.utils.ViewUtil;
import com.lx.zhaopin.view.FlowLiner;
import com.makeramen.roundedimageview.RoundedImageView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShouYe1FragmentAdapter extends RecyclerView.Adapter<ShouYe1FragmentAdapter.ViewHolder> {


    private List<ShouYeQiuZhiZheBean.DataListBean> mData;
    private Context mContext;
    private OnItemClickListener itemClickListener;
    private String dataType;

    public ShouYe1FragmentAdapter() {
    }

    public String getDataType(String type) {
        dataType = type;
        return type;
    }

    public ShouYe1FragmentAdapter(Context context, List<ShouYeQiuZhiZheBean.DataListBean> allList) {
        mContext = context;
        mData = allList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shouye_layout, viewGroup, false));//这个是招聘者看到的是人才信息
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_select_gangwei_layout, viewGroup, false));//求职者看到的是岗位信息
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int po) {

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
        viewHolder.tv1.setText(mData.get(po).getName());
        viewHolder.tv2.setText(mData.get(po).getMinSalary() + "K-" + mData.get(po).getMaxSalary() + "K");
        viewHolder.tv3.setText(mData.get(po).getCity().getName() + mData.get(po).getDistrict().getName());
        viewHolder.tv4.setText(mData.get(po).getExperienceYear().getName());
        viewHolder.tv5.setText(mData.get(po).getEducation().getName());
        viewHolder.tv6.setText(mData.get(po).getCompany().getName());
        Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                .error(R.mipmap.imageerror)).load(mData.get(po).getCompany().getLogo()).into(viewHolder.roundedImageView);


        // 1.卡片式推荐，2.列表式推荐，3.最新，4.最近
        switch (dataType) {
            case "1":
            case "2":
            case "3":
                viewHolder.tv7.setVisibility(View.GONE);
                break;
            case "4":
                viewHolder.tv7.setVisibility(View.VISIBLE);
                int i = Integer.parseInt(mData.get(po).getDistance());
                if (i < 1000) {
                    viewHolder.tv7.setText("<1km");
                } else {
                    BigDecimal num01 = new BigDecimal(mData.get(po).getDistance());//距离 单位 米
                    BigDecimal num02 = new BigDecimal("1000");//计算距离  num3.setScale(2, RoundingMode.UP).toString()
                    // 1.345----->1.3
                    viewHolder.tv7.setText(num01.divide(num02).setScale(1, RoundingMode.UP).toString() + "km");
                }
                break;
        }


        String workfare = mData.get(po).getWorkfare();

        if (!TextUtils.isEmpty(workfare)) {
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
                radioButton.setTextSize(13);
                radioButton.setPadding(ViewUtil.dp2px(mContext, 10), ViewUtil.dp2px(mContext, 6), ViewUtil.dp2px(mContext, 10), ViewUtil.dp2px(mContext, 6));
                radioButton.setTextColor(mContext.getResources().getColorStateList(R.color.radio_text_selector_primary_4d4d4d));
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
        } else {
            viewHolder.flowLiner.setVisibility(View.GONE);
        }


        viewHolder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.ItemClickListener(mData.get(po).getId());
                }
            }
        });


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
        @BindView(R.id.roundedImageView)
        RoundedImageView roundedImageView;
        @BindView(R.id.tv6)
        TextView tv6;

        @BindView(R.id.tv7)
        TextView tv7;

        @BindView(R.id.llView)
        LinearLayout llView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void ItemClickListener(String id);
    }

    public void SetOnItemClickListener(OnItemClickListener onItemClickListener) {
        itemClickListener = onItemClickListener;
    }

}
