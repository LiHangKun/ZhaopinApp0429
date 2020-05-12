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
import com.lx.zhaopin.bean.QiuZhiyiXiangBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QiuZhiYiXiangAdapter extends RecyclerView.Adapter<QiuZhiYiXiangAdapter.ViewHolder> {


    private Context mContext;
    private List<QiuZhiyiXiangBean.ResumeExpectationListBean> mData;
    private OnItemClickener itemClickener;

    public QiuZhiYiXiangAdapter() {
    }

    public QiuZhiYiXiangAdapter(Context context, List<QiuZhiyiXiangBean.ResumeExpectationListBean> list) {
        mContext = context;
        mData = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_yixiang_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv1.setText(mData.get(i).getPositionCategory3().getName());
        viewHolder.tv2.setText(mData.get(i).getCity().getName());
        viewHolder.tv3.setText(mData.get(i).getMinSalary() + "K - " + mData.get(i).getMaxSalary() + "K");

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

    public interface OnItemClickener {
        void onItemClick(String id);
    }

    public void setOnItemClickener(OnItemClickener OnItemClickener) {
        itemClickener = OnItemClickener;
    }

}
