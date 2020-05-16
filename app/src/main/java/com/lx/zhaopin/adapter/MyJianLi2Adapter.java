package com.lx.zhaopin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.bean.MyJianLiBean;
import com.lx.zhaopin.utils.ViewUtil;
import com.lx.zhaopin.view.FlowLiner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyJianLi2Adapter extends RecyclerView.Adapter<MyJianLi2Adapter.ViewHolder> {


    private List<MyJianLiBean.ExperienceWorkListBean> mData;
    private Context mContext;
    private OnItemClickListener itemClickListener;

    public MyJianLi2Adapter() {
    }

    public MyJianLi2Adapter(Context context, List<MyJianLiBean.ExperienceWorkListBean> list) {
        mContext = context;
        mData = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_myjianli2_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int po) {
        viewHolder.tv1.setText(mData.get(po).getCompanyName());
        viewHolder.tv2.setText(mData.get(po).getBeginDate() + "--" + mData.get(po).getEndDate());
        viewHolder.tv3.setText(mData.get(po).getPositionName());

        viewHolder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null){
                    itemClickListener.OnItemClickListener(mData.get(po).getId());
                }
            }
        });


        String skills = mData.get(po).getSkills();
        if (skills.contains(",")) {
            String[] split = skills.split(",");

            for (int i = 0; i < split.length; i++) {
                final TextView radioButton = new TextView(mContext);
                FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT,  FlowLiner.LayoutParams.WRAP_CONTENT);

                layoutParams.setMargins(0, 0, ViewUtil.dp2px(mContext, 10), ViewUtil.dp2px(mContext, 10));
                radioButton.setLayoutParams(layoutParams);
                final String str = split[i];
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
                        /*TODO ACTION*/
                        // doSearch(str);

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
        @BindView(R.id.tv2)
        TextView tv2;
        @BindView(R.id.tv3)
        TextView tv3;
        @BindView(R.id.tv4)
        TextView tv4;
        @BindView(R.id.flowLiner)
        FlowLiner flowLiner;
        @BindView(R.id.llView)
        LinearLayout llView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


    public interface OnItemClickListener{
        void OnItemClickListener(String id);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        itemClickListener = onItemClickListener;
    }

}
