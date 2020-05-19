package com.lx.zhaopin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.bean.MessageDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XiaoXiDetailAdapter extends RecyclerView.Adapter<XiaoXiDetailAdapter.ViewHolder> {


    private List<MessageDetailBean.DataListBean> mData;
    private Context mContext;

    public XiaoXiDetailAdapter() {
    }

    public XiaoXiDetailAdapter(Context context, List<MessageDetailBean.DataListBean> list) {
        mContext = context;
        mData = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_xiaoxi_detail, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String chatStatus = mData.get(i).getChatStatus();
        //聊天状态 chatStatus  1 已沟通 2 已投递 3 已预约 4 待面试 5 已到达 6 面试已取消 7 已反馈 8 同意入职 9 拒绝入职
        switch (chatStatus) {
            case "1":
                viewHolder.tv1.setText("已沟通");
                break;
            case "2":
                viewHolder.tv1.setText("已投递");
                break;
            case "3":
                viewHolder.tv1.setText("已预约");
                break;
            case "4":
                viewHolder.tv1.setText("待面试");
                break;
            case "5":
                viewHolder.tv1.setText("已到达");
                break;
            case "6":
                viewHolder.tv1.setText("面试已取消");
                break;
            case "7":
                viewHolder.tv1.setText("已反馈");
                break;
            case "8":
                viewHolder.tv1.setText("同意入职");
                break;
            case "9":
                viewHolder.tv1.setText("拒绝入职");
                break;
        }

        viewHolder.tv2.setText(mData.get(i).getChatDate());

        if (!TextUtils.isEmpty(mData.get(i).getSubhead())) {
            viewHolder.tv3.setText(mData.get(i).getSubhead());
        } else {
            viewHolder.tv3.setText("暂无反馈信息");
        }


        //imageState
        if (i == 0) {
            viewHolder.imageState.setImageResource(R.drawable.yuanquan);
        } else {
            viewHolder.imageState.setImageResource(R.drawable.yuanquan3);
        }


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageState)
        ImageView imageState;
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


}
