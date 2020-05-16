package com.lx.zhaopin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.lx.zhaopin.bean.GongSiZaiZhaoBean;
import com.lx.zhaopin.utils.ViewUtil;
import com.lx.zhaopin.view.FlowLiner;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZaiZhaoGangAdapter extends RecyclerView.Adapter<ZaiZhaoGangAdapter.ViewHolder> {


    private List<GongSiZaiZhaoBean.DataListBean> mData;
    private Context mContext;
    private OnItemClickener itemClickener;
    private static final String TAG = "ZaiZhaoGangAdapter";

    public ZaiZhaoGangAdapter() {
    }

    public ZaiZhaoGangAdapter(Context context, List<GongSiZaiZhaoBean.DataListBean> list) {
        mContext = context;
        mData = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_select_gangwei_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        String positionType = mData.get(i).getPositionType();
        //职位类型1.需沟通，2.无需沟通，3.直接面试
        switch (positionType) {
            case "1":
                viewHolder.imageView1.setVisibility(View.INVISIBLE);
                break;
            case "2":
                viewHolder.imageView1.setVisibility(View.VISIBLE);
                break;
            case "3":
                viewHolder.imageView1.setVisibility(View.VISIBLE);
                break;
        }

        viewHolder.tv1.setText(mData.get(i).getName());
        viewHolder.tv2.setText(mData.get(i).getMinSalary() + "-" + mData.get(i).getMaxSalary() + "K");
        viewHolder.tv3.setText(mData.get(i).getCity().getName() + mData.get(i).getDistrict().getName());
        viewHolder.tv4.setText(mData.get(i).getExperienceYear().getName() + "年");
        viewHolder.tv5.setText(mData.get(i).getEducation().getName());

        Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror).error(R.mipmap.imageerror))
                .load(mData.get(i).getCompany().getLogo()).into(viewHolder.roundedImageView);
        viewHolder.tv6.setText(mData.get(i).getCompany().getName());


        String workfare = mData.get(i).getWorkfare();
        String[] split = workfare.split(",");
        List<String> flowData = new ArrayList<>();
        for (int j = 0; j < split.length; j++) {
            flowData.add(split[j]);
        }

        viewHolder.flowLiner.removeAllViews();
        for (int k = 0; k < flowData.size(); k++) {
            final TextView radioButton = new TextView(mContext);
            FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT,
                    FlowLiner.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, ViewUtil.dp2px(mContext, 10),
                    ViewUtil.dp2px(mContext, 10));
            radioButton.setLayoutParams(layoutParams);
            final String str = flowData.get(k);
            radioButton.setText(str);
            radioButton.setGravity(Gravity.CENTER);
            radioButton.setTextSize(13);
            radioButton.setPadding(ViewUtil.dp2px(mContext, 18), ViewUtil.dp2px(mContext, 6), ViewUtil.dp2px(mContext, 18), ViewUtil.dp2px(mContext, 6));
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

        viewHolder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickener != null) {
                    itemClickener.onItemClick(mData.get(i).getId());
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
        @BindView(R.id.llView)
        LinearLayout llView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public interface OnItemClickener {
        void onItemClick(String id);
    }

    public void setOnItemClickener(OnItemClickener onItemClickener) {
        itemClickener = onItemClickener;
    }

}
