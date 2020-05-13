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
import com.lx.zhaopin.bean.YiLuQuBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//YiLuQu_In_Adapter
public class YiLuQuAdapter extends RecyclerView.Adapter<YiLuQuAdapter.ViewHolder> {


    private List<YiLuQuBean.DataListBean> mData;
    private Context mContext;

    public YiLuQuAdapter() {
    }

    public YiLuQuAdapter(Context context, List<YiLuQuBean.DataListBean> allList) {
        mContext = context;
        mData = allList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_yiluqu_layout_all, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv1.setText(mData.get(i).getDate());
        List<YiLuQuBean.DataListBean.OfferListBean> offerList = mData.get(i).getOfferList();
        YiLuQu_In_Adapter yiLuQu_in_adapter = new YiLuQu_In_Adapter(mContext, offerList);
        viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        viewHolder.recyclerView.setAdapter(yiLuQu_in_adapter);

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
