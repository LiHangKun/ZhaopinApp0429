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
import com.lx.zhaopin.bean.YuYueMianListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YuYueTimeListAdapter extends RecyclerView.Adapter<YuYueTimeListAdapter.ViewHolder> {


    private List<YuYueMianListBean.DataListBean> mData;
    private Context mContext;
    private OnItemClickListener itemClickListener;

    public YuYueTimeListAdapter() {
    }

    public YuYueTimeListAdapter(Context context, List<YuYueMianListBean.DataListBean> list) {
        mContext = context;
        mData = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mianshi_time_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv1.setText(mData.get(i).getInterviewDate() + "( 还剩" + mData.get(i).getRemainCount() + " 个名额)");

        viewHolder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.OnItemClickListener(i, mData.get(i).getInterviewDate(), mData.get(i).getId(), mData.get(i).getRemainCount());
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
        @BindView(R.id.llView)
        LinearLayout llView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void OnItemClickListener(int i, String time, String id, String yuNumber);
    }

    public void setOnItemClickListener(OnItemClickListener OnItemClickListener) {
        itemClickListener = OnItemClickListener;
    }


}
