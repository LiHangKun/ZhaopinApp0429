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
import com.lx.zhaopin.bean.YuLanBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YuLan4Adapter extends RecyclerView.Adapter<YuLan4Adapter.ViewHolder> {


    private List<YuLanBean.ResumeSkillListBean> mData;
    private Context mContext;
    private OnItemClickener itemClickener;

    public YuLan4Adapter() {
    }

    public YuLan4Adapter(Context context, List<YuLanBean.ResumeSkillListBean> list) {
        mContext = context;
        mData = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_jinneg_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv1.setText(mData.get(i).getName());
        viewHolder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickener != null) {
                    itemClickener.OnItemClickener(mData.get(i).getId(), mData.get(i).getName());
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

    public interface OnItemClickener {
        void OnItemClickener(String id, String name);
    }

    public void setOnItemClickener(OnItemClickener OnItemClickener) {
        itemClickener = OnItemClickener;
    }

}
