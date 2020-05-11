package com.lx.zhaopin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.bean.WenTiBean;
import com.lx.zhaopin.common.NoticeDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangJianWenTiAdapter extends RecyclerView.Adapter<ChangJianWenTiAdapter.ViewHolder> {


    private List<WenTiBean.DataListBean> mData;
    private Context mContext;

    public ChangJianWenTiAdapter() {
    }

    public ChangJianWenTiAdapter(Context context, List<WenTiBean.DataListBean> list) {
        mContext = context;
        mData = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_wenti_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv1.setText(mData.get(i).getTitle());
        viewHolder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NoticeDetailActivity.class);
                intent.putExtra("title", mData.get(i).getTitle());
                intent.putExtra("titleUrl", mData.get(i).getUrl());
                mContext.startActivity(intent);

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

}
