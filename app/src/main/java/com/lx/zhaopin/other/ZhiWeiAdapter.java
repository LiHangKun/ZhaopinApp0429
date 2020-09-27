package com.lx.zhaopin.other;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.SelectQiWang1Adapter;
import com.lx.zhaopin.bean.SelectQiWangBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhiWeiAdapter extends RecyclerView.Adapter<ZhiWeiAdapter.ViewHolder> {


    private List<SelectQiWangBean.DataListBean> mData;
    private Context mContext;
    private OnItemClickListener itemClickListener;
    private ZhiWeiAdapter.ViewHolder lastSelViweHolder;
    private int type;

    public ZhiWeiAdapter() {
    }

    public ZhiWeiAdapter(Context context, List<SelectQiWangBean.DataListBean> list, int type) {
        mContext = context;
        mData = list;
        this.type = type;
    }

    @NonNull
    @Override
    public ZhiWeiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ZhiWeiAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_zhiwei_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ZhiWeiAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.tv1.setText(mData.get(i).getName());
        viewHolder.selView.setVisibility(View.GONE);
        if (type != 1) {
            viewHolder.moreImg.setVisibility(View.GONE);
        }
        viewHolder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.OnItemClickListener(type, mData.get(i).getId(), mData.get(i).getName());
                    viewHolder.tv1.setTextColor(mContext.getResources().getColor(R.color.main_blue));
                    if (lastSelViweHolder != null) {
                        lastSelViweHolder.selView.setVisibility(View.GONE);
                        lastSelViweHolder.tv1.setTextColor(Color.parseColor("#151413"));
                    }
                    if (type != 3){
                        viewHolder.selView.setVisibility(View.VISIBLE);
                        lastSelViweHolder = viewHolder;
                    }
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
        @BindView(R.id.select_view)
        View selView;
        @BindView(R.id.more_img)
        ImageView moreImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void OnItemClickListener(int type, String id, String name);
    }

    public void SetOnItemClickListener(OnItemClickListener OnItemClickListener) {
        itemClickListener = OnItemClickListener;
    }

}
