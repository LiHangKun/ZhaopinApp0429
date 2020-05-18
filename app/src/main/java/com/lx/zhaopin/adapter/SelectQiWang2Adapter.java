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
import com.lx.zhaopin.bean.SelectQiWang2Bean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectQiWang2Adapter extends RecyclerView.Adapter<SelectQiWang2Adapter.ViewHolder> {


    private List<SelectQiWang2Bean.DataListBean> mData;
    private Context mContext;
    private OnItemClickListener itemClickListener;
    private static final String TAG = "SelectQiWang2Adapter";

    public SelectQiWang2Adapter() {
    }

    public SelectQiWang2Adapter(Context context, List<SelectQiWang2Bean.DataListBean> list) {
        mContext = context;
        mData = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_zhiwu_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv1.setText(mData.get(i).getName());

        viewHolder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.OnItemClickListener(i, mData.get(i).getId(), mData.get(i).getName());
                }

                /*Intent intent = new Intent(mContext, SelectQiWangType1_2Activity.class);
                intent.putExtra("parentid2", mData.get(i).getId());
                Log.i(TAG, "OnItemClickListener: 输出的信息" + mData.get(i).getName() + "----------" + mData.get(i).getId());
                mContext.startActivity(intent);*/


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
        void OnItemClickListener(int i, String id, String name);
    }

    public void SetOnItemClickListener(OnItemClickListener OnItemClickListener) {
        itemClickListener = OnItemClickListener;
    }

}
