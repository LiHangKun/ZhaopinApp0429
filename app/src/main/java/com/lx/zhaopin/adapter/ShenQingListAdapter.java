package com.lx.zhaopin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
import com.lx.zhaopin.bean.ShenQingListBean;
import com.lx.zhaopin.utils.ViewUtil;
import com.lx.zhaopin.view.FlowLiner;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShenQingListAdapter extends RecyclerView.Adapter<ShenQingListAdapter.ViewHolder> {


    private List<ShenQingListBean.DataListBean> mData;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public ShenQingListAdapter() {
    }

    public ShenQingListAdapter(Context context, List<ShenQingListBean.DataListBean> allList) {
        mContext = context;
        mData = allList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shenqinglist_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int po) {
        String positionType = mData.get(po).getPosition().getPositionType();
        switch (positionType) {
            case "1":
                viewHolder.imageView1.setVisibility(View.GONE);
                break;
            case "2":
            case "3":
                viewHolder.imageView1.setVisibility(View.VISIBLE);
                break;
        }

        String chatApplyStatus = mData.get(po).getChatApplyStatus();
        //状态，1.未处理，2.同意，3.拒绝
        switch (chatApplyStatus) {
            case "1":
                viewHolder.caoZuoView.setVisibility(View.VISIBLE);
                break;
            case "2":
            case "3":
                viewHolder.caoZuoView.setVisibility(View.GONE);
                break;
        }

        String opened = mData.get(po).getPosition().getOpened();
        switch (opened) {
            case "1":
                viewHolder.imageView2.setVisibility(View.VISIBLE);
                break;
            case "2":
                viewHolder.imageView2.setVisibility(View.GONE);
                break;
        }


        viewHolder.tv1.setText(mData.get(po).getPosition().getName());
        viewHolder.tv2.setText(mData.get(po).getPosition().getMinSalary() + "K - " + mData.get(po).getPosition().getMaxSalary() + "K");
        viewHolder.tv3.setText(mData.get(po).getPosition().getCity().getName() + mData.get(po).getPosition().getDistrict().getName());
        viewHolder.tv4.setText(mData.get(po).getPosition().getExperienceYear().getName() + "年");
        viewHolder.tv5.setText(mData.get(po).getPosition().getEducation().getName());
        Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror).error(R.mipmap.imageerror))
                .load(mData.get(po).getCompany().getLogo()).into(viewHolder.roundedImageView);
        viewHolder.tv6.setText(mData.get(po).getCompany().getName());


        String workfare = mData.get(po).getPosition().getWorkfare();
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

        viewHolder.AllllView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClickListener(viewHolder.AllllView, po, mData.get(po).getPosition().getOpened(), mData.get(po).getId(), mData.get(po).getCompany().getId());
                }
            }
        });

        viewHolder.caoZuoViewTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClickListener(viewHolder.caoZuoViewTv1, po, mData.get(po).getPosition().getOpened(), mData.get(po).getId(), mData.get(po).getCompany().getId());
                }
            }
        });

        viewHolder.caoZuoViewTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClickListener(viewHolder.caoZuoViewTv2, po, mData.get(po).getPosition().getOpened(), mData.get(po).getId(), mData.get(po).getCompany().getId());
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

        @BindView(R.id.caoZuoViewTv1)
        TextView caoZuoViewTv1;

        @BindView(R.id.caoZuoViewTv2)
        TextView caoZuoViewTv2;

        @BindView(R.id.caoZuoView)
        LinearLayout caoZuoView;

        @BindView(R.id.flowLiner)
        FlowLiner flowLiner;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;
        @BindView(R.id.llView)
        LinearLayout llView;


        @BindView(R.id.AllllView)
        LinearLayout AllllView;

        @BindView(R.id.roundedImageView)
        RoundedImageView roundedImageView;
        @BindView(R.id.tv6)
        TextView tv6;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void OnItemClickListener(View view, int po, String open, String id, String qiYeID);
    }

    public void SetOnItemClickListener(OnItemClickListener OnItemClickListener) {
        onItemClickListener = OnItemClickListener;
    }

}
