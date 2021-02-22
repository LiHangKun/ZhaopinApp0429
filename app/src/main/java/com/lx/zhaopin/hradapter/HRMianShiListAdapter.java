package com.lx.zhaopin.hradapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.bean.HRMianShiListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//HRMianShiList_In_Adapter
public class HRMianShiListAdapter extends RecyclerView.Adapter<HRMianShiListAdapter.ViewHolder> {


    private Context mContext;
    private List<HRMianShiListBean.DataListBean> mData;

    public HRMianShiListAdapter() {
    }

    public HRMianShiListAdapter(Context context, List<HRMianShiListBean.DataListBean> allList) {
        mContext = context;
        mData = allList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mianshi_layout_all, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv1.setText(mData.get(i).getDate());
        List<HRMianShiListBean.DataListBean.InterviewsBean> interviews = mData.get(i).getInterviews();
        HRMianShiList_In_Adapter mianShiList_in_adapter = new HRMianShiList_In_Adapter(mContext, interviews);
        viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        viewHolder.recyclerView.setAdapter(mianShiList_in_adapter);
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
