package com.lx.zhaopin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.bean.MianShiListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//DaiMianShi_In_ListAdapter
public class DaiMianShiListAdapter extends RecyclerView.Adapter<DaiMianShiListAdapter.ViewHolder> {

    private Context mContext;
    private List<MianShiListBean.DataListBean> mData;

    public DaiMianShiListAdapter() {
    }

    public DaiMianShiListAdapter(Context context, List<MianShiListBean.DataListBean> allList) {
        mContext = context;
        mData = allList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_daimianshi_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv1.setText(mData.get(i).getDate());
        List<MianShiListBean.DataListBean.InterviewsBean> interviews = mData.get(i).getInterviews();
        DaiMianShi_In_ListAdapter daiMianShi_in_listAdapter = new DaiMianShi_In_ListAdapter(mContext, interviews);
        viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        viewHolder.recyclerView.setAdapter(daiMianShi_in_listAdapter);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv1)
        TextView tv1;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
