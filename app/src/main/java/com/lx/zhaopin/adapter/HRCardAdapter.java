package com.lx.zhaopin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.RenCaiDetailActivity;
import com.lx.zhaopin.bean.RenCaiListBean;
import com.lx.zhaopin.utils.ViewUtil;
import com.lx.zhaopin.view.FlowLiner;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * HR看到的界面
 */

public class HRCardAdapter extends RecyclerView.Adapter<HRCardAdapter.CardHolder> {



    private List<RenCaiListBean.DataListBean> allList;
    private RequestOptions mRequestOptions;
    private final Context mContext;

    public HRCardAdapter(Context context, List<RenCaiListBean.DataListBean> cardBeanList) {
        mContext = context;
        allList = cardBeanList;
        /*mRequestOptions = new RequestOptions();
        mRequestOptions.placeholder(R.mipmap.imageerror).error(R.mipmap.imageerror).diskCacheStrategy(DiskCacheStrategy.NONE);*/
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_rencai22, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardHolder holder, final int position) {
        /*final CardBean bean = mCardBeanList.get(position);
        Glide.with(holder.itemView).load(bean.getUrl()).apply(mRequestOptions).into(holder.img);
        holder.text.setText(bean.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "click " + bean.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(),"点击了 img",Toast.LENGTH_SHORT).show();
            }
        });*/


        if (allList.get(position).getExperienceWorkList() != null) {
            if (allList.get(position).getExperienceWorkList().size() != 0) {
                holder.tv6.setText(allList.get(position).getExperienceWorkList().get(0).getCompanyName());
                holder.tv7.setText(allList.get(position).getExperienceWorkList().get(0).getPositionName());
                holder.tv8.setText(allList.get(position).getExperienceWorkList().get(0).getExperience());
                String skills = allList.get(position).getExperienceWorkList().get(0).getSkills();


                if (!TextUtils.isEmpty(skills)) {
                    String[] split = skills.split(",");
                    List<String> flowData = new ArrayList<>();
                    for (int i = 0; i < split.length; i++) {
                        flowData.add(split[i]);
                    }

                    holder.flowLiner1.removeAllViews();
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
                        holder.flowLiner1.addView(radioButton);
                    }
                } else {
                    holder.flowLiner1.setVisibility(View.GONE);
                    holder.tv6.setText("");
                    holder.tv7.setText("");
                    holder.tv8.setText("");
                }
            }
        }

        if (allList.get(position).getResumeSkillList() != null) {
            if (allList.get(position).getResumeSkillList().size() != 0) {
                List<String> flowData2 = new ArrayList<>();
                for (int i = 0; i < allList.get(position).getResumeSkillList().size(); i++) {
                    flowData2.add(allList.get(position).getResumeSkillList().get(i).getName());
                }
                holder.flowLiner2.removeAllViews();
                for (int i = 0; i < flowData2.size(); i++) {
                    final TextView radioButton = new TextView(mContext);
                    FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT, FlowLiner.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(0, 0, ViewUtil.dp2px(mContext, 10), ViewUtil.dp2px(mContext, 10));
                    radioButton.setLayoutParams(layoutParams);
                    final String str = flowData2.get(i);
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
                    holder.flowLiner2.addView(radioButton);
                }
            }
        } else {
            holder.flowLiner2.setVisibility(View.GONE);
        }


        Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                .error(R.mipmap.imageerror)).load(allList.get(position).getAvatar()).into(holder.roundedImageView);

        holder.tv1.setText(allList.get(position).getName());


        holder.tv2.setText(allList.get(position).getEducation().getName());
        holder.tv3.setText(allList.get(position).getAge() + "岁");
        holder.tv4.setText(allList.get(position).getWorkYears() + "年");
        holder.tv5.setText(allList.get(position).getLatestCity().getName());

        holder.tv9.setText(allList.get(position).getExperienceEducation().getSchool());


        holder.onClickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    /*if (itemCliCkListener != null) {
                        itemCliCkListener.onItemClickListener(position, allList.get(position).getId());
                    }*/

                Intent intent = new Intent(mContext, RenCaiDetailActivity.class);
                intent.putExtra("rid", allList.get(position).getId());
                mContext.startActivity(intent);


            }
        });


    }

    private static final String TAG = "CardAdapter";

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: 集合" + allList.size());
        return allList == null ? 0 : allList.size();
    }

    static class CardHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.roundedImageView)
        RoundedImageView roundedImageView;
        @BindView(R.id.tv1)
        TextView tv1;
        @BindView(R.id.tv2)
        TextView tv2;
        @BindView(R.id.tv3)
        TextView tv3;
        @BindView(R.id.tv4)
        TextView tv4;
        @BindView(R.id.tv5)
        TextView tv5;
        @BindView(R.id.llViewGongSi)
        LinearLayout llViewGongSi;
        @BindView(R.id.tv6)
        TextView tv6;
        @BindView(R.id.tv7)
        TextView tv7;
        @BindView(R.id.tv8)
        TextView tv8;
        @BindView(R.id.flowLiner1)
        FlowLiner flowLiner1;
        @BindView(R.id.tv9)
        TextView tv9;
        @BindView(R.id.flowLiner2)
        FlowLiner flowLiner2;
        @BindView(R.id.llViewALL)
        LinearLayout llViewALL;
        @BindView(R.id.onClickView)
        RelativeLayout onClickView;


        public CardHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
