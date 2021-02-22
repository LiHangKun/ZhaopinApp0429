package com.lx.zhaopin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.bean.RenCaiDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RenCaiDetail1Adapter extends RecyclerView.Adapter<RenCaiDetail1Adapter.ViewHolder> {


    private List<RenCaiDetailBean.ResumeExpectationListBean> mData;
    private Context mContext;

    public RenCaiDetail1Adapter() {
    }

    public RenCaiDetail1Adapter(Context context, List<RenCaiDetailBean.ResumeExpectationListBean> resumeExpectationList) {
        mContext = context;
        mData = resumeExpectationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rencai1_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv1.setText(mData.get(i).getPositionCategory3().getName());
        viewHolder.tv2.setText(mData.get(i).getCity().getName());
        viewHolder.tv3.setText(mData.get(i).getMinSalary() + "K - " + mData.get(i).getMaxSalary() + "K");
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
        @BindView(R.id.llView)
        LinearLayout llView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
