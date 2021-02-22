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

public class HRXiaoXiDetailAdapter extends RecyclerView.Adapter<HRXiaoXiDetailAdapter.ViewHolder> {


    private List<MessageDetailBean.DataListBean> mData;
    private Context mContext;
    private OnFeedClickListener feedClickListener;

    public HRXiaoXiDetailAdapter() {
    }

    public HRXiaoXiDetailAdapter(Context context, List<MessageDetailBean.DataListBean> list) {
        mContext = context;
        mData = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_feed_detail, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String chatStatus = mData.get(i).getChatStatus();
        String feedback = mData.get(i).getFeedback();  // feedback 是否已反馈1是0否
        //聊天状态 chatStatus
        // 1 已沟通 2 已投递 3 已预约 4 待面试 5 已到达
        // 6 面试已取消 7 已面试 8 同意入职 9 拒绝入职  10 拒绝面试 11 已录取  12 不合适 13 已邀约

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
                viewHolder.tv1.setText("已面试");
                // feedback 是否已反馈1是0否
                /*if (feedback.equals("0")) {
                    viewHolder.feedTv.setVisibility(View.VISIBLE);
                }*/

                break;
            case "8":
                viewHolder.tv1.setText("同意入职");
                break;
            case "9":
                viewHolder.tv1.setText("拒绝入职");
                break;
            case "10":
                viewHolder.tv1.setText("拒绝面试");
                break;
            case "11":
                viewHolder.tv1.setText("已录取");
                break;
            case "12":
                viewHolder.tv1.setText("不合适");
                break;
            case "13":
                viewHolder.tv1.setText("已邀约");
                break;
        }

        viewHolder.tv2.setText(mData.get(i).getChatDate());

        if (!TextUtils.isEmpty(mData.get(i).getSubhead())) {
            viewHolder.tv3.setText(mData.get(i).getSubhead());
        } else if (feedback.equals("0")) {
            viewHolder.tv3.setText("暂无反馈信息");
        } else if (feedback.equals("1")) {
            viewHolder.tv3.setText(mData.get(i).getFeedbackContent());
        }


        //imageState
        if (i == 0) {
            viewHolder.imageState.setImageResource(R.drawable.yuanquan);
        } else {
            viewHolder.imageState.setImageResource(R.drawable.yuanquan3);
        }

        viewHolder.feedTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (feedClickListener != null){
                    feedClickListener.feedClick(mData.get(i).getCorrelation());
                }
            }
        });


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

        @BindView(R.id.feedTv)
        TextView feedTv;

        @BindView(R.id.llView)
        LinearLayout llView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnFeedClickListener{
        void feedClick(String id);
    }

    public void setOnFeedClickListener(OnFeedClickListener onFeedClickListener){
        feedClickListener = onFeedClickListener;
    }



}
