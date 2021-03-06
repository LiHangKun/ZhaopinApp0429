package com.lx.zhaopin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.bean.YuLanBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YuLan3Adapter extends RecyclerView.Adapter<YuLan3Adapter.ViewHolder> {


    private List<YuLanBean.ExperienceEducationListBean> mData;
    private Context mContext;

    public YuLan3Adapter() {
    }

    public YuLan3Adapter(Context context, List<YuLanBean.ExperienceEducationListBean> list) {
        mContext = context;
        mData = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_yulan3_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv1.setText(mData.get(i).getSchool());
        viewHolder.tv2.setText(mData.get(i).getBeginDate() + "-" + mData.get(i).getEndDate());
        viewHolder.tv3.setText(mData.get(i).getEducation().getName());
        viewHolder.tv4.setText(mData.get(i).getMajor());
        viewHolder.tv5.setText(mData.get(i).getExperience());

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
        @BindView(R.id.tv5)
        TextView tv5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}
