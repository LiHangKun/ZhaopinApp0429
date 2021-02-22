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
import com.lx.zhaopin.bean.HRYiLuQuBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//YiLuQu_In_Adapter
public class HRYiLuQuAdapter extends RecyclerView.Adapter<HRYiLuQuAdapter.ViewHolder> {


    private List<HRYiLuQuBean.DataListBean> mData;
    private Context mContext;

    public HRYiLuQuAdapter() {
    }

    public HRYiLuQuAdapter(Context context, List<HRYiLuQuBean.DataListBean> allList) {
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
        List<HRYiLuQuBean.DataListBean.OfferListBean> offerList = mData.get(i).getOfferList();
        HRYiLuQu_In_Adapter yiLuQu_in_adapter = new HRYiLuQu_In_Adapter(mContext, offerList);
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
